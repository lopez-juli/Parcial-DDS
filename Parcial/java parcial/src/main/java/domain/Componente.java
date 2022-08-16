package domain;

public class Componente extends Producto{
    private Tipo tipo;
    
    public Componente(String nom, int pre, boolean sto,Tipo ti){
        super(nom,pre,sto,false);
        this.tipo = ti;
    }
    
    public Tipo getTipo(){
    return tipo;
    }
}
