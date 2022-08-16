package domain;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MenorAMayor extends Strategy {
    
    @Override
    public List<Producto> ordenar(List<Producto> productos){
        productos.sort(Comparator.comparing(Producto::getPrecio));
        return productos;
    }
    
    @Override
    public String getNombre(){
        return "MenorAMayor";
    }
}
