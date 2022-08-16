package domain;

public class Producto {

    private String nombre;
    private int precio;
    private boolean stock;
    private boolean esPrototype;
    
    
    public int getPrecio(){
       return precio;
    } 
    
    public boolean getStock() {
       return stock;
    }
    
    public boolean getEsPrototype() {
       return esPrototype;
    }
    public String getNombre(){
        return nombre;
    }
    
    public Producto(String nom, int pre, boolean sto, boolean valor){
        this.nombre = nom;
        this.precio = pre;
        this.stock = sto;
        this.esPrototype = valor;
    }
    
}
