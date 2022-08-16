package domain;

import java.util.ArrayList;
import java.util.List;

class RepoClientes {
    private static RepoClientes repoClientes = null;
    private RepoClientes(){}
    public static RepoClientes getInstance(){
        if(repoClientes == null){
            repoClientes = new RepoClientes();}
    return repoClientes;
    }
   
    private List<Cliente> clientes = new ArrayList();
    
      public List<Cliente> getClientes() {
        return clientes;
    }
      
    public Cliente getClientePorNombre(String nombre) throws Exception {
        return clientes.stream().filter(clienteEnRepo ->nombre.equals(clienteEnRepo.getNombre())).findAny().get();
    }
    
    public void agregarCliente(List<Cliente> clies){
        clientes.addAll(clies);
    }
    
    public void agregarCliente(Cliente clie){
        clientes.add(clie);
    }
}
