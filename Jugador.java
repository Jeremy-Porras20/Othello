/**
 * @brief Representa un jugador del juego Othello.
 * 
 * Cada jugador tiene un nombre y un color asociado (0 para negro, 1 para blanco).
 */
public class Jugador {
    private String nombre; ///< Nombre del jugador
    private int color; ///< Color del jugador: 0 = negro, 1 = blanco

    /**
     * @brief Constructor de la clase Jugador.
     * @param nombre El nombre del jugador.
     * @param color El color del jugador (0 para negro, 1 para blanco).
     */
    public Jugador(String nombre, int color) {
        this.nombre = nombre;
        this.color = color;
    }

    /**
     * @brief Establece el color del jugador.
     * @param color El nuevo color del jugador.
     */
    public void setColor(int color) {
        this.color = color;
    }

    /**
     * @brief Obtiene el color del jugador.
     * @return El color asignado al jugador.
     */
    public int getColor() {
        return this.color;
    }

    /**
     * @brief Obtiene el nombre del jugador.
     * @return El nombre del jugador.
     */
    public String getNombre() {
        return this.nombre;
    }
}
