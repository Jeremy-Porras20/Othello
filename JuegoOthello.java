import java.util.List;

/**
 * @brief Clase principal que gestiona la lógica del juego Othello.
 * 
 * Administra el estado del juego, los jugadores, el tablero, los turnos y la lógica para determinar el ganador.
 */
public class JuegoOthello {
    private Tablero tablero;             ///< Representación del tablero de juego.
    private Jugador jugador1;            ///< Primer jugador.
    private Jugador jugador2;            ///< Segundo jugador o computador.
    private Jugador turnoActual;         ///< Jugador que tiene el turno actual.
    private boolean vsComputador;        ///< Indica si el juego es contra la computadora.

    /**
     * @brief Constructor de la clase JuegoOthello.
     * @param jugador1 Jugador 1.
     * @param jugador2 Jugador 2 o la computadora.
     * @param filas Número de filas del tablero.
     * @param columnas Número de columnas del tablero.
     * @param vsComputador True si el segundo jugador es la computadora.
     */
    public JuegoOthello(Jugador jugador1, Jugador jugador2, int filas, int columnas, boolean vsComputador) {
        this.jugador1 = jugador1;
        this.jugador2 = jugador2;
        this.vsComputador = vsComputador;
        tablero = new Tablero(filas, columnas);
        iniciarJuego();
        turnoActual = (jugador1.getColor() == 0) ? jugador1 : jugador2;
    }

    /**
     * @brief Inicializa el tablero con las fichas iniciales.
     */
    public void iniciarJuego() {
        tablero.iniciarTablero();
    }

    /**
     * @brief Permite a un jugador realizar un turno si el movimiento es válido.
     * @param fila Fila donde se desea colocar la ficha.
     * @param columna Columna donde se desea colocar la ficha.
     * @return True si el movimiento fue válido, false en caso contrario.
     */
    public boolean jugarTurno(int fila, int columna) {
        boolean valido = false;

        List<Coordenada> movimientosDisponibles = tablero.encontrarMovimientosValidos(turnoActual);
        if (movimientosDisponibles.isEmpty()) {
            System.out.println("No hay movimientos disponibles para el jugador " + turnoActual.getNombre());
            cambiarTurno();
            return false;
        }

        for (Coordenada coordenada : movimientosDisponibles) {
            if (coordenada.getFila() == fila && coordenada.getColumna() == columna) {
                tablero.getFicha(fila, columna).setColor(turnoActual.getColor());
                tablero.voltearFichas(turnoActual, fila, columna);
                cambiarTurno();
                valido = true;
            }
        }
        return valido;
    }

    /**
     * @brief Ejecuta el turno automático de la computadora, eligiendo el primer movimiento válido.
     */
    private void turnoComputador() {
        List<Coordenada> movimientos = tablero.encontrarMovimientosValidos(jugador2);
        if (!movimientos.isEmpty()) {
            Coordenada jugada = movimientos.get(0);
            jugarTurno(jugada.getFila(), jugada.getColumna());
        }
    }

    /**
     * @brief Cambia el turno al siguiente jugador.
     * 
     * Si el siguiente jugador es la computadora, ejecuta su turno automáticamente.
     */
    private void cambiarTurno() {
        if (turnoActual == jugador1) {
            turnoActual = jugador2;
        } else {
            turnoActual = jugador1;
        }

        if (turnoActual.getNombre().equals("Computador")) {
            turnoComputador();
        }
    }

    /**
     * @brief Verifica si el juego ha terminado.
     * @return True si ambos jugadores no tienen movimientos válidos, false en caso contrario.
     */
    public boolean elJuegoTermino() {
        boolean jugador1SinMovimientos = tablero.encontrarMovimientosValidos(jugador1).isEmpty();
        boolean jugador2SinMovimientos = tablero.encontrarMovimientosValidos(jugador2).isEmpty();
        return jugador1SinMovimientos && jugador2SinMovimientos;
    }

    /**
     * @brief Determina el jugador ganador del juego.
     * @return El jugador con más fichas en el tablero, o null si hay empate.
     */
    public Jugador determinarGanador() {
        int fichasJ1 = tablero.contarFichas(jugador1.getColor());
        int fichasJ2 = tablero.contarFichas(jugador2.getColor());

        if (fichasJ1 > fichasJ2) {
            return jugador1;
        } else if (fichasJ1 < fichasJ2) {
            return jugador2;
        } else {
            return null;
        }
    }

    /**
     * @brief Obtiene el tablero actual del juego.
     * @return El tablero de juego.
     */
    public Tablero getTablero() {
        return tablero;
    }
}
