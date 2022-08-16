package domain;

import java.util.ArrayList;
import java.util.List;

public class Reserva {
    private Cliente cliente;
    private int codReserva;
    private List<Producto> productos = new ArrayList<>();
    private Estados estado; 

   public Reserva(Cliente nCliente){
       cliente = nCliente;
       estado = new Posible();
       codReserva = nuevoCodReserva();
   }
    public void agregar(Producto producto) {
        productos.add(producto);
    }
    
    public void agregar(List<Producto> productos) {
        productos.addAll(productos);
    }
    
    public void eliminar(Producto producto) {
        productos.remove(producto);
    }

    List<Producto> getProductos() {
       return productos;
    }
    
    public Estados getEstado(){
        return estado;
    }
    
    public void setEstado(Estados nEstado){
        estado = nEstado;
    }
    
    public Producto getProdSinStock(){
        return productos.stream().filter(prod -> false == prod.getStock()).findAny().get();
    }
    
    public Producto getProductoPorNombre(String nombre) throws Exception {
        return productos.stream().filter(productoEnRepo ->nombre.equals(productoEnRepo.getNombre())).findAny().get();
    }
    
    public void enviarMail(){
        EmailSender emailSender = new EmailSender();
        emailSender.send("dds2022grupo8@gmail.com", cliente.getEmail(), "Numero de reserva: ",String.valueOf(codReserva));
    }
    
    public int nuevoCodReserva(){
        RepoReservas repo = RepoReservas.getInstance();
        int cod = repo.getMaxCod();
        cod = cod+1;
        return cod;
    }
    public int getCodReserva(){
        return codReserva;
    }
    
}
