package domain;

import java.util.List;

public class SinStock extends Estados{
    
    @Override
    public boolean chequear(Reserva reserva){
        List<Producto> prod = reserva.getProductos();
        if(prod.stream().anyMatch(Producto -> Producto.getStock() == false)){
            return false;
        } else{
            reserva.setEstado(new Posible());
            return true;
        }        
     }
    
    @Override
    public void manejar(Reserva reserva,Producto producto){
       reserva.eliminar(producto);       
    }
}
