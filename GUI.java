import javax.swing.*;

public class GUI
{
    JFrame ventana; 
    TableroPanel panel;
    JuegoOthello juego;

    
    public GUI(TableroPanel panel)
    {
      inicializarVentana();
      
      this.panel = panel;
      ventana.add(panel);
      ventana.setVisible(true);
    }
    
    private void inicializarVentana(){
        ventana = new JFrame();
        ventana.setTitle("Othello");
        ventana.setSize(600,600);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
    }
    
    public void actualizarTablero(){
        panel.actualizar();
    }
    
    public void mostrarGanador(Jugador jugador){
        JLabel cuadro = new JLabel("El ganador es" + jugador.getNombre());
    }
    
    
}
