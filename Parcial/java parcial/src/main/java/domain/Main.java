package domain;

import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;

public class Main {
    public void main (String[] args) throws Exception {
        Scanner entrada = new Scanner(System.in);
        int seleccion;
        do{
            System.out.println("0.Salir\n1.Login\n");
            seleccion = entrada.nextInt();
            switch (seleccion){
                case 0:
                    System.out.println("Saliendo del programa\n");
                    break;
                case 1:
                    login();
                    break;
                default:
                    System.out.println("Operacion invalida\n");
                    break;
            }
        }while(seleccion != 0);
    }

    private void login() {
        Scanner entrada = new Scanner(System.in);

        System.out.println("Ingrese usuario: \n");
        String nombre = entrada.nextLine();
        System.out.println("Ingrese contrasena: \n");
        String contrasena = entrada.nextLine();

        RepoClientes repoCliente = RepoClientes.getInstance();
        try{
            Cliente clienteEnRepo = repoCliente.getClientePorNombre(nombre);
            boolean loginValido = clienteEnRepo.validarContrasenaCorrecta(contrasena);
            if (loginValido) {
                System.out.println("Login exitoso.\n");
                mostrarOperacionesTrasLogin(clienteEnRepo);
            }
            else {
                System.out.println("Error al intentar realizar el Login.\n");
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    private void mostrarOperacionesTrasLogin(Cliente cliente){
        Scanner entrada = new Scanner(System.in);
        int seleccion;
        do{
            System.out.println("0.Salir\n1.Empezar Reserva\n2.Cambiar strategy\n");
            seleccion = entrada.nextInt();
            switch (seleccion){
                case 0:
                    System.out.println("Saliendo del login\n");
                    break;
                case 1:
                    empezarReserva(cliente);
                    break;
                case 2:
                    cambiarStrategy(cliente);
                    break;
                default:
                    System.out.println("Operacion invalida\n");
                    break;
            }
        }while(seleccion != 0);
    }
    
    public void empezarReserva(Cliente cliente){
        Reserva reserva = new Reserva(cliente);
        AgregarProd(cliente,reserva);
        mostrarOpcionesReserva(cliente, reserva);
    }

    private void AgregarProd(Cliente cliente, Reserva reserva) {
        Scanner entrada = new Scanner(System.in);
        String seleccion;
        RepoProductos repoProd = RepoProductos.getInstance();
        List<Producto> productos = repoProd.getProductos();
        productos = cliente.getStrategy().ordenar(productos);
        Producto producto;
        
        System.out.println("Productos: \n");
        productos.forEach(new Consumer<Producto>() {
            @Override
            public void accept(Producto prod) {
                System.out.println("Nombre:"+prod.getNombre()+" Precio:"+String.valueOf(prod.getPrecio())+"\n");
            }
        });
        System.out.println("Ingresar nombre del producto a agregar.\n");
        seleccion = entrada.nextLine();
        try {
            producto = repoProd.getProductoPorNombre(seleccion);
            if (producto.getEsPrototype()){
                RepoPcPrototype repoPC = RepoPcPrototype.getInstance();
                PCPrototype Pc = repoPC.getPcPorNombre(seleccion);
                Pc = Pc.clone();
                reserva.getEstado().manejar(reserva,Pc);
                if(reserva.getEstado().chequear(reserva)){ 
                } else { CambiarProducto(cliente,reserva);}
            } else{
            reserva.getEstado().manejar(reserva,producto);
            if(reserva.getEstado().chequear(reserva)){ 
            } else { CambiarProducto(cliente,reserva);}
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            AgregarProd(cliente,reserva);
        }
    }
    
    private void mostrarOpcionesReserva(Cliente cliente, Reserva reserva){
        Scanner entrada = new Scanner(System.in);
        int seleccion;
        do{
            System.out.println("0.Agregar Producto\n1.Eliminar producto\n2.Finalizar Reserva\n");
            seleccion = entrada.nextInt();
            switch (seleccion){
                case 0:
                    AgregarProd(cliente,reserva);
                    break;
                case 1:
                    EliminarProd(cliente,reserva);
                    break;
                case 2:
                    FinalizarReserva(cliente,reserva);
                    break;
                default:
                    System.out.println("Operacion invalida\n");
                    break;
            }
        }while(seleccion != 0);
    }
    
    private void FinalizarReserva(Cliente cliente, Reserva reserva){
        if(reserva.getEstado().chequear(reserva)){
            System.out.println("Reserva finalizada enviando mail.\n");
            reserva.enviarMail();
            RepoReservas repoRes = RepoReservas.getInstance();
            repoRes.agregarReserva(reserva);
        } else {
            CambiarProducto(cliente,reserva);
        }
    }
    
    private void CambiarProducto(Cliente cliente, Reserva reserva){
        Producto prodSinStock = reserva.getProdSinStock();
        reserva.getEstado().manejar(reserva, prodSinStock);
        System.out.println(prodSinStock.getNombre()+" sin stock.\n");
        if(reserva.getEstado().chequear(reserva)){
            AgregarProd(cliente,reserva);
        } else {
            CambiarProducto(cliente,reserva);
        }
    }
    
    private void EliminarProd(Cliente cliente, Reserva reserva){
        Scanner entrada = new Scanner(System.in);
        String seleccion;
        Producto producto;
        
        System.out.println("Productos: \n");
        reserva.getProductos().forEach(new Consumer<Producto>() {
            @Override
            public void accept(Producto prod) {
                System.out.println("Nombre:"+prod.getNombre()+" Precio:"+String.valueOf(prod.getPrecio())+"\n");
            }
        });
        System.out.println("Ingresar nombre del producto a eliminar.\n");
        seleccion = entrada.nextLine();
        
        try {
             producto = reserva.getProductoPorNombre(seleccion);
             reserva.eliminar(producto);
             if(reserva.getProductos().size() == 0){
                System.out.println("Reserva sin Productos.\n"); 
                AgregarProd(cliente,reserva); 
             }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            EliminarProd(cliente, reserva);
        }
        
        if(reserva.getProductos().size() == 0){
           System.out.println("Reserva no pudede estar sin productos.\n"); 
           AgregarProd(cliente,reserva);
        }
    } 
    
    private void cambiarStrategy(Cliente cliente){
        Scanner entrada = new Scanner(System.in);
        int seleccion,cod = 0;
        do{
            System.out.println("1.Mayor a menor\n1.Menor a mayor\n");
            seleccion = entrada.nextInt();
            switch (seleccion){
                case 1:
                    cliente.setStrategy(new MayorAMenor());
                    cod = 1;
                    break;
                case 2:
                    cliente.setStrategy(new MenorAMayor());
                    cod = 1;
                    break;
                default:
                    System.out.println("Operacion invalida\n");
                    break;
            }
        }while(cod == 0);
    }
}

