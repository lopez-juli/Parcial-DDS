package domain;

public class Finalizado extends Estados{
    
    @Override
     public boolean chequear(Reserva reserva){
        return true;        
     }
     
    @Override
    public void manejar(Reserva reserva,Producto producto){
       System.out.println("Reserva ya fue finalizada.\n");       
    }
}
