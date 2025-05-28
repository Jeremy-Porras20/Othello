import javax.swing.JButton;
import java.awt.Color;
import javax.swing.ImageIcon;


public class Ficha
{
    private int color; // 0 = negro , 1= blanco -1 = vacio
    private JButton boton;
    private int fila;
    private int columna;
    
    public Ficha(int color, int fila, int columna)
    {
       this.color = color;
       this.fila = fila;
       this.columna = columna;
       crearBoton();
    }
    
    public void crearBoton(){
      boton = new JButton();
        boton.setEnabled(true);
        boton.setBackground(new Color(0, 128, 0));
        actualizarBoton(color);
    }
    
    public void actualizarBoton(int color){
        //boton.setIcon(null);
        
        ImageIcon fichaNegra = new ImageIcon("C:\\Users\\jerem\\OneDrive\\Escritorio\\Progra1 2.0\\Peroyecto 2\\fichaNEGRA.jpg");
        ImageIcon fichaBlanca = new ImageIcon("C:\\Users\\jerem\\OneDrive\\Escritorio\\Progra1 2.0\\Peroyecto 2\\fichaBLANCA.png");
        
        if (color == 1){
            boton.setIcon(fichaBlanca);
        }
        else if (color == 0){
            boton.setIcon(fichaNegra);
        }
        
    } 

    public void setColor(int color){
        this.color = color;
        actualizarBoton(color);
    }
    
    public int getColor(){
        return this.color;
    }
    
    public JButton getBoton(){
        return boton;
    }
    
    public void setFila(int fila){
        this.fila = fila;
    }
    
    public int getFila(){
        return fila;
    }
    
    public void setColuman(int columna){
        this.columna = columna;
    }
    
    public int getColumna(){
        return columna;
    }
}
