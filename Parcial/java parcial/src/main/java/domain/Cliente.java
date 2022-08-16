package domain;

public class Cliente {   
    private String nombre;
    private String contraseña;
    private String email;
    private Strategy strategy;
    
    public Cliente(String nNombre, String nContraseña, String nEmail, Strategy nStrategy){
        this.nombre = nNombre;
        this.contraseña = nContraseña;
        this.email = nEmail;
        this.strategy = nStrategy;
    }
    public void setStrategy(Strategy news){
        strategy = news;
    }
    public Strategy getStrategy(){
        return strategy;
    }
    
    public String getNombre(){
        return nombre;
    }
    
    public boolean validarContrasenaCorrecta(String contrasena) throws Exception{
       return this.contraseña.equals(contrasena);
    }
    
    public String getEmail(){
        return email;
    }
    
    public String getContraseña(){
        return contraseña;
    }
}
