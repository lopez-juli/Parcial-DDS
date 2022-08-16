package domain;

import java.util.List;

public class Posible extends Estados {
     
    @Override
     public boolean chequear(Reserva reserva){
        List<Producto> prod = reserva.getProductos();
        if(prod.stream().anyMatch(Producto -> Producto.getStock() == false)){
            reserva.setEstado(new SinStock());
            return false;
        }
        else {
            return true;
        }        
     }
     
     public void manejar(Reserva reserva,Producto producto){
        reserva.agregar(producto);
     }
    
}
