

public class Jugador
{
    private String nombre;
    private int color; 
  
    public Jugador(String nombre, int color)
    {
        this.nombre = nombre; 
        this.color = color; 
    }
    
    public void setColor(int color){
        this.color = color;
    }
    
    public int getColor(){
        return this.color;
    }
    
    public String getNombre(){
        return this.nombre;
    }
    
}
