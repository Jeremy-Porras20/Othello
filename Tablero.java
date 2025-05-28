import javax.swing.*;
import java.util.List;
import java.util.ArrayList;

public class Tablero{
    private Ficha[][] tablero; // tablero va a ser de numeros enteros donde el numero indica el color de la ficha
    private int tamanio;
    public Tablero(int tamanio)
    {
        tablero = new Ficha[tamanio][tamanio];
        this.tamanio = tamanio;
  
    }

    public List<Coordenada> encontrarMovimientosValidos(Jugador jugador){
        List<Coordenada> movimientosValidos = new ArrayList<>();
        for (int fil = 0; fil < tablero.length; fil++){

            for (int col = 0; col< tablero.length; col++){
                if (validarMovimiento(jugador, fil, col) == true){
                    movimientosValidos.add(new Coordenada(fil, col));
                }
            }

        }
        return movimientosValidos;
    }

    public boolean validarMovimiento(Jugador jugador, int fila, int columna){//el turno será un entero (1 o 0) en funcico del color
        boolean movimientoValido = false;

        if (dentroTablero(fila,columna ) == false){
            return movimientoValido;
        }
        if (tablero[fila][columna].getColor() != -1){
            return movimientoValido;
        }

        int colorJugador = jugador.getColor();
        int colorOpuesto; 
        if (colorJugador == 0){
            colorOpuesto = 1;
        }
        else{
            colorOpuesto = 0; 
        }

        int [] cF = {-1,-1,-1,0,0,1,1,1};
        int [] cC = {-1,0,1,-1,1,-1,0,1};

        for (int i = 0; i < tablero.length; i++){
            // nos movemos en una direccion
            int nuevaFila = fila + cF[1];
            int nuevaColumna = columna + cC[i];

            boolean hayFichaOpuesta = false;

            while(dentroTablero(nuevaFila,nuevaColumna) == true && tablero[nuevaFila][nuevaColumna].getColor() == colorOpuesto){
                // iteramos en la fila y la columna en la direccion encontrada
                nuevaFila = nuevaFila + cF[i]; 
                nuevaColumna = nuevaColumna + cC[i];

                hayFichaOpuesta = true; // como entró al while entonces encontro una ficha opuesta 
            }
            // si sale del while quiere decir que la ficha siguiente que va en esa direccion contiene el color opuesto o esta vacia             
            if (dentroTablero(nuevaFila, nuevaColumna) == true && hayFichaOpuesta == true && tablero[nuevaFila][nuevaColumna].getColor() == colorJugador){
                return movimientoValido = true;
            }

        }
        return movimientoValido;
    }

    public void voltearFichas(Jugador jugador, int fila, int columna){// este metodo es muy parecido al metodo validarMovimientos() solo que aqui cuando encuentra un movimiento valido voltea las fichas 
        int colorJugador = jugador.getColor();
        int colorOpuesto;
        
        if (colorJugador == 0){
            colorOpuesto = 1;
        }
        else{
            colorOpuesto = 0; 
        }
        
        int [] cF = {-1,-1,-1,0,0,1,1,1};
        int [] cC = {-1,0,1,-1,1,-1,0,1};
        
        for(int i = 0; i < tablero.length; i++){
        
            int nuevaFila = fila + cF[1];
            int nuevaColumna = columna + cC[i];
            
            // creamos una lista para guardar las coordenadas que podriamos cambiar
            List<Coordenada> posiblesCambios = new ArrayList<>();
            while (dentroTablero(nuevaFila,nuevaColumna) == true && tablero[nuevaFila][nuevaColumna].getColor() == colorOpuesto){
                posiblesCambios.add(new Coordenada(nuevaFila,nuevaColumna));
                // nos movemos en la misma direccion 
                nuevaFila = nuevaFila + cF[i];
                nuevaColumna = nuevaColumna + cC[i];
            }
            
            // cuando ya salio del while verificamos que haya un una posicion del jugador  y si la hay volteamos las fichas que tenemos dentro de los posibles volteos
            if (dentroTablero(nuevaFila, nuevaColumna) == true && tablero[nuevaFila][nuevaColumna].getColor() == colorJugador){
                for (Coordenada coordenada : posiblesCambios){
                    tablero[coordenada.getFila()][coordenada.getColumna()].setColor(colorJugador);
                }
            }
        
        }
    }
    
    public boolean colocarFicha(Jugador jugador, int fila, int columna){
        if (validarMovimiento(jugador, fila, columna) == false){
            return false;
        }
        tablero[fila][columna].setColor(jugador.getColor());
        voltearFichas(jugador, fila, columna);
        
        return true; 
    }

    public boolean dentroTablero(int fila, int col){
        boolean estaDentro = true;
        if (fila >= tablero.length || col >= tablero.length || fila < 0 || col < 0){
            estaDentro = false;
        }
        return estaDentro; 
    }

    public int contarFichas(int color){
        int cantidad = 0;
        for (int fila = 0; fila < tablero.length; fila ++){
            for (int col = 0; col < tablero[0].length; col++){
                if(tablero[fila][col].getColor() == color){
                    cantidad++;
                }
            }
        }
        return cantidad;
    }
    
    public void iniciarTablero(){
        
        for (int fila = 0; fila < tablero.length; fila++){
            for (int columna = 0; columna < tablero.length; columna++){
                this.tablero[fila][columna] = new Ficha(-1,fila,columna);
            }
        
        }
        
        // formula para acceder a las cuatro posiciones del centro es tablero.legnth/2 y (tablero.length/2)+1 tanto en horizontal como en vertical 
        
        //llenar los cuatro cuadros del centro con los valores correspondientes
        
        // 1: blaco
        this.tablero[(tablero.length/2)-1][(tablero.length/2)-1].setColor(1);
        this.tablero[(tablero.length/2)][(tablero.length/2)].setColor(1);
        
        // 0: negro
        this.tablero[(tablero.length/2)-1][(tablero.length/2)].setColor(0);;
        this.tablero[(tablero.length/2)][(tablero.length/2)-1].setColor(0);;
    }
    
    public Ficha getFicha(int fila, int columna){
        return tablero[fila][columna];
    }
    
    public int getTamanio(){
        return tamanio;
    }
    
    public Ficha[][] getTablero(){
        return tablero;
    }
}
