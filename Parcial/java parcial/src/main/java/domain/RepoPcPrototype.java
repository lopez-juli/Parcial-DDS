package domain;

import java.util.ArrayList;
import java.util.List;

public class RepoPcPrototype {
    private static RepoPcPrototype repoPcPrototype = null;
    private RepoPcPrototype(){}
    public static RepoPcPrototype getInstance(){
        if(repoPcPrototype == null){
            repoPcPrototype = new RepoPcPrototype();}
    return repoPcPrototype;
    }
   
    private List<PCPrototype> listPc = new ArrayList();
      
    public PCPrototype getPcPorNombre(String nombre) throws Exception {
        return listPc.stream().filter(PCEnRepo ->nombre.equals(PCEnRepo.getNombre())).findAny().get();
    }
    
    public void agregarPcPrototype(List<PCPrototype> pc){
        listPc.addAll(pc);
    }
    
    public void agregarPcPrototype(PCPrototype pc){
        listPc.add(pc);
    }
}

