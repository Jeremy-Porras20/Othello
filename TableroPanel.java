import javax.swing.*;
import java.awt.*;

/**
 * @brief Panel gráfico que representa el tablero del juego Othello.
 * 
 * Esta clase extiende JPanel y se encarga de mostrar el tablero visualmente usando botones,
 * manejar eventos de clic para realizar movimientos, y actualizar la interfaz según el estado del juego.
 */
public class TableroPanel extends JPanel {
    private Tablero tablero; ///< Instancia del tablero lógico.

    /**
     * @brief Constructor que inicializa el panel del tablero.
     * 
     * @param tablero Objeto Tablero que contiene la lógica del juego.
     * @param juego Objeto JuegoOthello para interactuar con la lógica del juego desde la interfaz.
     */
    public TableroPanel(Tablero tablero, JuegoOthello juego) {
        this.tablero = tablero;

        setBackground(Color.BLACK);
        setLayout(new GridLayout(tablero.getFilas(), tablero.getColumnas(), 2, 2));

        Ficha[][] matrizFichas = tablero.getTablero();

        for (int i = 0; i < tablero.getFilas(); i++) {
            for (int j = 0; j < tablero.getColumnas(); j++) {
                JButton boton = matrizFichas[i][j].getBoton();
                final int fila = i;
                final int columna = j;

                // Acción al hacer clic sobre un botón del tablero
                boton.addActionListener(e -> {
                    juego.jugarTurno(fila, columna);
                    actualizar();

                    if (juego.elJuegoTermino()) {
                        Jugador ganador = juego.determinarGanador();

                        if (ganador != null) {
                            JOptionPane.showMessageDialog(this, "¡El ganador es " + ganador.getNombre() + "!");
                        } else {
                            JOptionPane.showMessageDialog(this, "El juego terminó en empate.");
                        }

                        // Cerrar la ventana del juego
                        Window ventana = SwingUtilities.getWindowAncestor(this);
                        if (ventana != null) {
                            ventana.dispose();
                        }

                        // Volver al menú principal
                        SwingUtilities.invokeLater(() -> new Menu());
                    }
                });

                add(boton);
            }
        }
    }

    /**
     * @brief Actualiza la interfaz gráfica del tablero según el estado actual de las fichas.
     */
    public void actualizar() {
        Ficha[][] matrizFichas = tablero.getTablero();
        for (int i = 0; i < tablero.getFilas(); i++) {
            for (int j = 0; j < tablero.getColumnas(); j++) {
                Ficha ficha = matrizFichas[i][j];
                ficha.actualizarBoton(ficha.getColor());
            }
        }

        revalidate();
        repaint();
    }

    /**
     * @brief Reinicia el juego reinicializando el tablero y actualizando la vista.
     */
    public void reiniciarJuego() {
        tablero.iniciarTablero();
        actualizar();
    }

    /**
     * @brief Obtiene el tablero lógico asociado al panel.
     * @return Objeto Tablero del juego.
     */
    public Tablero getTablero() {
        return tablero;
    }
}
