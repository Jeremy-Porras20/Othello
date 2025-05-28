import java.util.ArrayList;
import java.util.List;

public class JuegoOthello
{
    private Tablero tablero; 
    private Jugador jugador1;
    private Jugador jugador2;
    private Jugador turnoActual;
    
    public JuegoOthello(Jugador jugador1, Jugador jugador2, int tamano)
    {
        this.jugador1 = jugador1;
        this.jugador2 = jugador2;
        tablero = new Tablero(tamano);
        
    }

    public void iniciarJuego(){
        tablero.iniciarTablero();
    }

    public boolean jugarTurno(int fila, int columna){
        boolean turnoJugado = false;
        List<Coordenada> movimientosDisponibles = tablero.encontrarMovimientosValidos(turnoActual);
        for (Coordenada coordenada : movimientosDisponibles){
            if (coordenada.getFila() == fila && coordenada.getColumna() == columna){
                tablero.voltearFichas(turnoActual,fila, columna);
                tablero.colocarFicha(turnoActual,fila, columna);

                cambiarTurno();
                turnoJugado = true;
            }
        }

        return turnoJugado;
    }

    public void cambiarTurno(){
        if (turnoActual == jugador1){
            turnoActual = jugador2;
        }
        else {
            turnoActual = jugador1;
        }

    }

    public boolean elJuegoTermino(){

        boolean jugador1SinMovimientos = tablero.encontrarMovimientosValidos(jugador1).isEmpty();
        boolean jugador2SinMovimientos = tablero.encontrarMovimientosValidos(jugador2).isEmpty();
        
        if (jugador1SinMovimientos == true && jugador2SinMovimientos == true){
            return true;
        }
        else {
            return false;
        }

    }
    public Jugador determinarGanador(){
        if (tablero.contarFichas(jugador1.getColor()) > tablero.contarFichas(jugador2.getColor())){
            return jugador1;
        }

        else if (tablero.contarFichas(jugador1.getColor()) < tablero.contarFichas(jugador2.getColor())) {
            return jugador2;

        }           
        else {
            return null;
        }
    }
    
    public Tablero getTablero(){
        return tablero;
    }
}
