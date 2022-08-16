/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repository;

import domain.Cliente;
import domain.MayorAMenor;
import domain.MenorAMayor;
import domain.Strategy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author julia
 */
public class ClienteDAO {
    public static Integer insertCourse(Cliente cliente) throws SQLException {
        Connection connection = ConeccionaBD.initDb();
        String sql = "INSERT INTO Cliente (name, email, password,strategy) values (?,?,?,?)";
        PreparedStatement stm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        stm.setString(1, cliente.getNombre());
        stm.setString(2, cliente.getEmail());
        stm.setString(3, cliente.getContraseña());
        stm.setString(3, cliente.getStrategy().getNombre());
        stm.executeUpdate();

        // obtener último id generado
        ResultSet generatedKeys = stm.getGeneratedKeys();
        Integer id = -1;
        if (generatedKeys.next()) {
            id = generatedKeys.getInt(1);
            System.out.println("Cliente " + id.toString() + " creado con exito\n");
        }
        else {
            id = 0;
            System.out.println("error al crear curso\n");
        }
        connection.close();
        return id;
    }

    public static Cliente selectClienteByNombre(String nombre) throws SQLException {
        Connection connection = ConeccionaBD.initDb();
        String sql = "select c.Id_Cliente, c.nombre from Cliente c " +
                "where c.nombre = ?";
        PreparedStatement stm = connection.prepareStatement(sql);
        stm.setString(1, nombre);
        ResultSet resultSet = stm.executeQuery();

        if (resultSet.next()) {
            Strategy strat;
            if(resultSet.getString("startegy") == "MayorAMenor"){
                strat = new MayorAMenor();
            } else {
                strat = new MenorAMayor();
            }
            Cliente clie = new Cliente(resultSet.getString("nombre"), resultSet.getString("password"),resultSet.getString("email"),strat);
            return clie;
        }

        connection.close();
        return null;
    }
}
