package domain;

import java.util.List;

public abstract class Strategy {
    public abstract List<Producto> ordenar(List<Producto> productos);
    public abstract String getNombre();
}
