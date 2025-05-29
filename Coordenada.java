/**
 * @brief Representa una coordenada dentro del tablero de juego.
 * 
 * Esta clase se utiliza para identificar una posición específica en el tablero mediante una fila y una columna.
 */
public class Coordenada {
    private int fila;     ///< La fila de la coordenada
    private int columna;  ///< La columna de la coordenada

    /**
     * @brief Constructor de la clase Coordenada.
     * @param fila La fila donde se encuentra la coordenada.
     * @param columna La columna donde se encuentra la coordenada.
     */
    public Coordenada(int fila, int columna) {
        this.fila = fila;
        this.columna = columna;
    }

    /**
     * @brief Obtiene la fila de la coordenada.
     * @return El valor de la fila.
     */
    public int getFila() {
        return fila;
    }

    /**
     * @brief Obtiene la columna de la coordenada.
     * @return El valor de la columna.
     */
    public int getColumna() {
        return columna;
    }
}
