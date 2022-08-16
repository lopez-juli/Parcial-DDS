package domain;

import java.util.ArrayList;
import java.util.List;

public class RepoProductos {
    private static RepoProductos repoProductos = null;
    private RepoProductos(){}
    public static RepoProductos getInstance(){
        if(repoProductos == null){
            repoProductos = new RepoProductos();}
    return repoProductos;
    }
   
    private List<Producto> productos = new ArrayList();
    
      public List<Producto> getProductos() {
        return productos;
    }
      
   public Producto getProductoPorNombre(String nombre) throws Exception {
        return productos.stream().filter(productoEnRepo ->nombre.equals(productoEnRepo.getNombre())).findAny().get();
    }
   
   public void agregarProducto(List<Producto> prods){
        productos.addAll(prods);
    }
    
    public void agregarProducto(Producto prod){
        productos.add(prod);
    }
   
}
