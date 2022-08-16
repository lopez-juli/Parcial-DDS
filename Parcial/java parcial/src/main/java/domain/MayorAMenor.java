package domain;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MayorAMenor extends Strategy{
    @Override
    public List<Producto> ordenar(List<Producto> productos){
        productos.sort(Comparator.comparing(Producto::getPrecio));
        Collections.sort(productos, Collections.reverseOrder());
        return productos;
    }
    
    @Override
    public String getNombre(){
        return "MayorAMenor";
    }
}
