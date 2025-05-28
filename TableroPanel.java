import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.GridLayout;

public class TableroPanel extends JPanel 
{
    private Tablero tablero;
    private JuegoOthello juego;

    public TableroPanel(Tablero tablero, JuegoOthello juego)
    {
        this.tablero = tablero;
        this.juego = juego;

        setBackground(Color.BLACK);
        setLayout(new GridLayout(tablero.getTamanio(), tablero.getTamanio(), 2, 2));
        Ficha[][] matrizFichas = tablero.getTablero();

        for (int i = 0; i < tablero.getTamanio(); i++) {
            for (int j = 0; j < tablero.getTamanio(); j++) {

                add(matrizFichas[i][j].getBoton());
                
                
            }
        }

    }
    
    public void actualizar(){
        Ficha[][] matrizFichas = tablero.getTablero();
        for (int i = 0; i < tablero.getTamanio(); i++) {
            for (int j = 0; j < tablero.getTamanio(); j++) {
                Ficha ficha = matrizFichas[i][j];
                int color = ficha.getColor();
                JButton boton = ficha.getBoton();
                
                

                
                boton.setIcon(null); 
                ficha.actualizarBoton(color);
                
            }
        }

        revalidate();
        repaint();
    }

    public void reiniciarJuego(){
        tablero.iniciarTablero();
        actualizar();
    }
}
