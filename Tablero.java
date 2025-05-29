import java.util.List;
import java.util.ArrayList;

/**
 * @brief Clase que representa el tablero del juego Othello.
 */
public class Tablero {
    private Ficha[][] tablero; ///< Matriz de fichas que representa el tablero.
    private int filas, columnas;

    /**
     * @brief Constructor de la clase Tablero.
     * @param filas Cantidad de filas del tablero.
     * @param columnas Cantidad de columnas del tablero.
     */
    public Tablero(int filas, int columnas) {
        tablero = new Ficha[filas][columnas];
        this.filas = filas;
        this.columnas = columnas;
        iniciarTablero();
    }

    /**
     * @brief Encuentra todos los movimientos válidos para el jugador dado.
     * @param jugador Jugador actual.
     * @return Lista de coordenadas donde el jugador puede colocar una ficha.
     */
    public List<Coordenada> encontrarMovimientosValidos(Jugador jugador) {
        List<Coordenada> movimientosValidos = new ArrayList<>();
        for (int fil = 0; fil < tablero.length; fil++) {
            for (int col = 0; col < tablero[0].length; col++) {
                if (validarMovimiento(jugador, fil, col)) {
                    movimientosValidos.add(new Coordenada(fil, col));
                }
            }
        }
        return movimientosValidos;
    }

    /**
     * @brief Valida si una jugada en una posición dada es válida para un jugador.
     * @param jugador Jugador que desea hacer la jugada.
     * @param fila Fila en la que desea jugar.
     * @param columna Columna en la que desea jugar.
     * @return true si es un movimiento válido, false en caso contrario.
     */
    public boolean validarMovimiento(Jugador jugador, int fila, int columna) {
        boolean valido = false;
        if (!(fila < 0 || columna < 0 || jugador == null || tablero == null || !dentroTablero(fila, columna) || tablero[fila][columna].getColor() != -1)) {
            int colorJugador = jugador.getColor();
            int colorOpuesto = (colorJugador == 0) ? 1 : 0;

            int[] cF = {-1,-1,-1,0,0,1,1,1};
            int[] cC = {-1,0,1,-1,1,-1,0,1};

            for (int i = 0; i < cF.length; i++) {
                int nuevaFila = fila + cF[i];
                int nuevaColumna = columna + cC[i];

                boolean hayFichaOpuesta = false;

                while (dentroTablero(nuevaFila, nuevaColumna) && tablero[nuevaFila][nuevaColumna].getColor() == colorOpuesto) {
                    nuevaFila += cF[i];
                    nuevaColumna += cC[i];
                    hayFichaOpuesta = true;
                }

                if (dentroTablero(nuevaFila, nuevaColumna) && hayFichaOpuesta && tablero[nuevaFila][nuevaColumna].getColor() == colorJugador) {
                    valido = true;
                }
            }
        }
        return valido;
    }

    /**
     * @brief Voltea las fichas del oponente después de una jugada válida.
     * @param jugador Jugador que realiza la jugada.
     * @param fila Fila en la que se coloca la ficha.
     * @param columna Columna en la que se coloca la ficha.
     */
    public void voltearFichas(Jugador jugador, int fila, int columna) {
        int colorJugador = jugador.getColor();
        int colorOpuesto = (colorJugador == 0) ? 1 : 0;

        int[] cF = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] cC = {-1, 0, 1, -1, 1, -1, 0, 1};

        for (int i = 0; i < 8; i++) {
            int nuevaFila = fila + cF[i];
            int nuevaColumna = columna + cC[i];

            List<Coordenada> posiblesCambios = new ArrayList<>();

            while (dentroTablero(nuevaFila, nuevaColumna) && tablero[nuevaFila][nuevaColumna].getColor() == colorOpuesto) {
                posiblesCambios.add(new Coordenada(nuevaFila, nuevaColumna));
                nuevaFila += cF[i];
                nuevaColumna += cC[i];
            }

            if (dentroTablero(nuevaFila, nuevaColumna) && tablero[nuevaFila][nuevaColumna].getColor() == colorJugador) {
                for (Coordenada c : posiblesCambios) {
                    tablero[c.getFila()][c.getColumna()].setColor(colorJugador);
                }
            }
        }
    }

    /**
     * @brief Coloca una ficha en el tablero si el movimiento es válido.
     * @param jugador Jugador que hace la jugada.
     * @param fila Fila de la jugada.
     * @param columna Columna de la jugada.
     * @return true si se colocó la ficha correctamente, false en caso contrario.
     */
    public boolean colocarFicha(Jugador jugador, int fila, int columna) {
        boolean valido = false;
        if (validarMovimiento(jugador, fila, columna)) {
            valido = true;
            tablero[fila][columna].setColor(jugador.getColor());
            voltearFichas(jugador, fila, columna);
        }
        return valido;
    }

    /**
     * @brief Verifica si una coordenada está dentro del tablero.
     * @param fila Fila a verificar.
     * @param col Columna a verificar.
     * @return true si está dentro del tablero, false en caso contrario.
     */
    public boolean dentroTablero(int fila, int col) {
        return fila >= 0 && col >= 0 && fila < getFilas() && col < getColumnas();
    }

    /**
     * @brief Cuenta cuántas fichas de un color específico hay en el tablero.
     * @param color Color de ficha a contar.
     * @return Cantidad de fichas del color especificado.
     */
    public int contarFichas(int color) {
        int cantidad = 0;
        for (int fila = 0; fila < tablero.length; fila++) {
            for (int col = 0; col < tablero[0].length; col++) {
                if (tablero[fila][col].getColor() == color) {
                    cantidad++;
                }
            }
        }
        return cantidad;
    }

    /**
     * @brief Inicializa el tablero con las fichas del centro en la posición inicial del juego.
     */
    public void iniciarTablero() {
        for (int fila = 0; fila < getFilas(); fila++) {
            for (int columna = 0; columna < getColumnas(); columna++) {
                this.tablero[fila][columna] = new Ficha(-1, fila, columna);
            }
        }

        // Posiciones centrales
        tablero[(getFilas() / 2) - 1][(getColumnas() / 2) - 1].setColor(1); // blanco
        tablero[(getFilas() / 2)][(getColumnas() / 2)].setColor(1);         // blanco
        tablero[(getFilas() / 2) - 1][(getColumnas() / 2)].setColor(0);     // negro
        tablero[(getFilas() / 2)][(getColumnas() / 2) - 1].setColor(0);     // negro
    }

    /**
     * @brief Obtiene una ficha específica del tablero.
     * @param fila Fila de la ficha.
     * @param columna Columna de la ficha.
     * @return Ficha en la posición especificada.
     */
    public Ficha getFicha(int fila, int columna) {
        return tablero[fila][columna];
    }

    /**
     * @brief Devuelve la cantidad de filas del tablero.
     * @return Número de filas.
     */
    public int getFilas() {
        return filas;
    }

    /**
     * @brief Devuelve la cantidad de columnas del tablero.
     * @return Número de columnas.
     */
    public int getColumnas() {
        return columnas;
    }

    /**
     * @brief Devuelve la matriz completa del tablero.
     * @return Matriz de fichas.
     */
    public Ficha[][] getTablero() {
        return tablero;
    }
}
