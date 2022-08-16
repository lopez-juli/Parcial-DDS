package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class RepoReservas {
     private static RepoReservas repoReserva = null;
    private RepoReservas(){}
    public static RepoReservas getInstance(){
        if(repoReserva == null){
            repoReserva = new RepoReservas();}
    return repoReserva;
    }
   
    private List<Reserva> reservas = new ArrayList();
    
      public List<Reserva> getReservas() {
        return reservas;
    }
   
    public void agregarReservas(List<Reserva> res){
        reservas.addAll(res);
    }
    
    public void agregarReserva(Reserva res){
        reservas.add(res);
    }
    
    public int getMaxCod(){
        reservas.sort(Comparator.comparing(Reserva::getCodReserva));
        Collections.sort(reservas, Collections.reverseOrder());
        return reservas.stream().findFirst().get().getCodReserva();
    }
}

