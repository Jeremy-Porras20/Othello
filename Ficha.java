import javax.swing.JButton;
import java.awt.Color;
import javax.swing.ImageIcon;

/**
 * @brief Representa una ficha en el juego Othello/Reversi.
 * 
 * Una ficha puede ser negra (0), blanca (1) o estar vacía (-1). 
 * Cada ficha se representa gráficamente mediante un botón (`JButton`) con su respectivo color.
 */
public class Ficha {
    private int color; ///< Color de la ficha: 0 = negro, 1 = blanco, -1 = vacío
    private JButton boton; ///< Botón asociado a la ficha para mostrarla en la interfaz gráfica
    private int fila; ///< Fila en la que se encuentra la ficha
    private int columna; ///< Columna en la que se encuentra la ficha

    /**
     * @brief Constructor de la clase Ficha.
     * @param color Color inicial de la ficha (-1, 0 o 1)
     * @param fila Fila donde se colocará la ficha
     * @param columna Columna donde se colocará la ficha
     */
    public Ficha(int color, int fila, int columna) {
        this.color = color;
        this.fila = fila;
        this.columna = columna;
        crearBoton();
    }

    /**
     * @brief Crea e inicializa el botón asociado a la ficha.
     */
    public void crearBoton() {
        boton = new JButton();
        boton.setEnabled(true);
        boton.setBackground(new Color(0, 128, 0)); // Verde oscuro
        actualizarBoton(color);
    }

    /**
     * @brief Actualiza el icono del botón de acuerdo al color de la ficha.
     * @param color El color que se desea mostrar.
     */
    public void actualizarBoton(int color) {
        

        if (color == 1) {
            boton.setBackground(Color.WHITE);
        } else if (color == 0) {
            boton.setBackground(Color.BLACK);
        }
    }

    /**
     * @brief Asigna un nuevo color a la ficha y actualiza su representación gráfica.
     * @param color El nuevo color de la ficha.
     */
    public void setColor(int color) {
        this.color = color;
        actualizarBoton(color);
    }

    /**
     * @brief Obtiene el color actual de la ficha.
     * @return El color de la ficha.
     */
    public int getColor() {
        return this.color;
    }

    /**
     * @brief Obtiene el botón asociado a esta ficha.
     * @return El objeto JButton que representa visualmente a la ficha.
     */
    public JButton getBoton() {
        return boton;
    }

    /**
     * @brief Establece la fila de la ficha.
     * @param fila La fila donde se encuentra la ficha.
     */
    public void setFila(int fila) {
        this.fila = fila;
    }

    /**
     * @brief Obtiene la fila de la ficha.
     * @return La fila actual de la ficha.
     */
    public int getFila() {
        return fila;
    }

    /**
     * @brief Establece la columna de la ficha.
     * @param columna La columna donde se encuentra la ficha.
     */
    public void setColuman(int columna) {
        this.columna = columna;
    }

    /**
     * @brief Obtiene la columna de la ficha.
     * @return La columna actual de la ficha.
     */
    public int getColumna() {
        return columna;
    }
}
