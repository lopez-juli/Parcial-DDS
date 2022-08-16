package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PCPrototype extends Producto {
   
   private List<Componente> componentes = new ArrayList<>();
   private String color;
   
   public PCPrototype(String nom, int pre, boolean sto,List<Componente> nComponentes){
       super(nom,pre,sto,true);
       componentes = nComponentes;
   } 
   
   public PCPrototype clone(){
    PCPrototype PC = new PCPrototype(this.getNombre(),this.getPrecio(),this.getStock(),this.componentes);
    Scanner entrada = new Scanner(System.in);
    System.out.println("Ingresar color del gabinete deseado.\n");
    PC.SetColor(entrada.nextLine());
    return PC;
   }
   
   private void SetColor(String nColor){
       color = nColor;
   }
   
   public List<Componente> GetComponentes(){
       return componentes;
   }
   
   public String getColor(){
       return color;
   }
}
