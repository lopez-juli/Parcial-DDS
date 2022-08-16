package domain;

public abstract class Estados {
   public abstract boolean chequear(Reserva reserva);
   public abstract void manejar(Reserva reserva,Producto producto);
}
