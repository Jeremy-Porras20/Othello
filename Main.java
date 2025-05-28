import javax.swing.*;

public class Main
{
    public static void main(String[] args) {
       
        
        JuegoOthello juego = new JuegoOthello(new Jugador("jeremy",0), new Jugador("Emily",1),8);
        juego.iniciarJuego();
        Tablero tablero = juego.getTablero();
        TableroPanel tableroPanel = new TableroPanel(tablero,juego); // tablero 8x8
        GUI interfaz = new GUI(tableroPanel);

    
    }
}
