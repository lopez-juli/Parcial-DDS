package domain;

import java.util.ArrayList;
import java.util.List;

public class Paquete extends Producto{
    private List<Producto> productos = new ArrayList<>();
    
    public Paquete(String nom, int pre, boolean sto,List<Producto> prods){
        super(nom,pre,sto,false);
        productos = prods;
    }
    
    public List<Producto> getProductos(){
        return productos;
    }
}
