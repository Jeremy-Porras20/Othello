import javax.swing.*;

/**
 * @brief Clase que representa el menú principal del juego Othello.
 * 
 * Permite al usuario elegir entre los modos de juego: jugador contra jugador o jugador contra la computadora.
 * También ofrece la opción de salir del juego.
 */
class Menu extends JFrame {
    private JButton salirButton;             ///< Botón para salir del juego.
    private JButton modoDeJuegoButton;       ///< Botón para seleccionar el modo de juego.
    private String nombreJugador1 = "Jugador1"; ///< Nombre por defecto del Jugador 1.
    private String nombreJugador2 = "Jugador2"; ///< Nombre por defecto del Jugador 2.

    /**
     * @brief Constructor que inicializa la interfaz del menú.
     */
    public Menu() {
        setTitle("Menu Principal");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        modoDeJuegoButton = new JButton("Modo de Juego");
        modoDeJuegoButton.setBounds(120, 90, 140, 30);
        add(modoDeJuegoButton);

        salirButton = new JButton("Salir");
        salirButton.setBounds(150, 170, 70, 30);
        add(salirButton);

        // Acción para cerrar el juego
        salirButton.addActionListener(e -> System.exit(0));

        // Acción para mostrar opciones de modo de juego
        modoDeJuegoButton.addActionListener(e -> mostrarOpcionesModoJuego());

        setVisible(true);
    }

    /**
     * @brief Muestra las opciones de modo de juego al usuario.
     */
    private void mostrarOpcionesModoJuego() {
        String[] opciones = {"1 vs 1", "Contra la Máquina"};
        int seleccion = JOptionPane.showOptionDialog(this,
                "Seleccione modo de juego:",
                "Modo de Juego",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                opciones,
                opciones[0]);

        if (seleccion == 0) {
            iniciarJuego1vs1();
        } else if (seleccion == 1) {
            iniciarJuegoVsComputador();
        }
    }

    /**
     * @brief Inicia el juego en modo 1 contra 1 (dos jugadores).
     */
    private void iniciarJuego1vs1() {
        String[] opcionesTamanio = {"8x8", "10x10", "12x12", "10x14", "14x10", "16x13"};

        int seleccion = JOptionPane.showOptionDialog(this,
                "Seleccione el tamaño del tablero:",
                "Tamaño del tablero",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                opcionesTamanio,
                opcionesTamanio[0]);

        if (seleccion == JOptionPane.CLOSED_OPTION) {
            return;
        }

        int filas = 8;
        int columnas = 8;
        String seleccionado = opcionesTamanio[seleccion];
        String[] partes = seleccionado.split("x");

        try {
            filas = Integer.parseInt(partes[0]);
            columnas = Integer.parseInt(partes[1]);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al leer tamaño seleccionado.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        nombreJugador1 = JOptionPane.showInputDialog(this, "Ingrese el nombre del Jugador 1:");
        if (nombreJugador1 == null || nombreJugador1.trim().isEmpty()) {
            nombreJugador1 = "Jugador1";
        }

        nombreJugador2 = JOptionPane.showInputDialog(this, "Ingrese el nombre del Jugador 2:");
        if (nombreJugador2 == null || nombreJugador2.trim().isEmpty()) {
            nombreJugador2 = "Jugador2";
        }

        JuegoOthello juego = new JuegoOthello(new Jugador(nombreJugador1, 0), new Jugador(nombreJugador2, 1), filas, columnas, false);
        juego.iniciarJuego();
        TableroPanel tableroPanel = new TableroPanel(juego.getTablero(), juego);
        new GUI(tableroPanel);
        this.dispose();
    }

    /**
     * @brief Inicia el juego en modo jugador contra la computadora.
     */
    public void iniciarJuegoVsComputador() {
        String[] opcionesTamanio = {"8x8", "10x10", "12x12", "10x14", "14x10", "16x13"};

        int seleccion = JOptionPane.showOptionDialog(this,
                "Seleccione el tamaño del tablero:",
                "Tamaño del tablero",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                opcionesTamanio,
                opcionesTamanio[0]);

        if (seleccion == JOptionPane.CLOSED_OPTION) {
            return;
        }

        int filas = 8;
        int columnas = 8;
        String seleccionado = opcionesTamanio[seleccion];
        String[] partes = seleccionado.split("x");

        try {
            filas = Integer.parseInt(partes[0]);
            columnas = Integer.parseInt(partes[1]);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al leer tamaño seleccionado.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        nombreJugador1 = JOptionPane.showInputDialog(this, "Ingrese el nombre: ");
        if (nombreJugador1 == null || nombreJugador1.trim().isEmpty()) {
            nombreJugador1 = "Jugador";
        }

        JuegoOthello juego = new JuegoOthello(new Jugador(nombreJugador1, 0), new Jugador("Computador", 1), filas, columnas, true);
        juego.iniciarJuego();
        TableroPanel tableroPanel = new TableroPanel(juego.getTablero(), juego);
        new GUI(tableroPanel);
        this.dispose();
    }
}
