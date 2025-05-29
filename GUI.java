import javax.swing.*;

/**
 * @brief Clase que representa la interfaz gráfica principal del juego Othello.
 * 
 * Encapsula la ventana principal (`JFrame`) donde se muestra el tablero del juego.
 */
public class GUI {
    JFrame ventana;        ///< Ventana principal del juego.
    TableroPanel panel;    ///< Panel que contiene el tablero.
    JuegoOthello juego;    ///< Referencia al juego Othello.

    /**
     * @brief Constructor que inicializa la ventana y añade el panel del tablero.
     * 
     * @param panel Panel del tablero que se mostrará en la ventana.
     */
    public GUI(TableroPanel panel) {
        inicializarVentana();
        this.panel = panel;
        ventana.add(panel);
        ventana.setVisible(true);
    }

    /**
     * @brief Inicializa la ventana del juego.
     */
    private void inicializarVentana() {
        ventana = new JFrame();
        ventana.setTitle("Othello");
        ventana.setSize(600, 600);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * @brief Actualiza visualmente el tablero.
     */
    public void actualizarTablero() {
        panel.actualizar();
    }

    /**
     * @brief Muestra un mensaje con el jugador ganador.
     * 
     * @param jugador El jugador que ganó el juego.
     * 
     * @note Este método solo crea el `JLabel`, pero no lo añade a la ventana ni lo muestra.
     *       Se recomienda usar `JOptionPane.showMessageDialog` o añadir el `JLabel` al frame.
     */
    public void mostrarGanador(Jugador jugador) {
        
        if (jugador != null){
            JLabel cuadro = new JLabel("El ganador es " + jugador.getNombre() + 
                    " con " + panel.getTablero().contarFichas(jugador.getColor()) + " fichas");
             JOptionPane.showMessageDialog(ventana, cuadro.getText(), "Resultado", JOptionPane.INFORMATION_MESSAGE);
        }
        else if(jugador == null){
            JLabel cuadro = new JLabel("Hubo un empate");
             JOptionPane.showMessageDialog(ventana, cuadro.getText(), "Resultado", JOptionPane.INFORMATION_MESSAGE);
        }
    
    }
}
