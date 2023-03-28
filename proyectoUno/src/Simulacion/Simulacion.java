package Simulacion;

import Alimento.AlimentoVision;
import Microorganismos.Jugador;
import Microorganismos.Microorganismo;
import GUI.Gui;
import Mapa.Mapa;

 
public class Simulacion {
    public static void main(String[] args) throws Exception {
        Gui interfaz = new Gui();
        Jugador jugador = new Jugador(0, 0);
        Mapa mapa = new Mapa();
        mapa.insertarJugador(interfaz, jugador);

        /*\
        1. Se genera el mapa, incluyendo al jugador, partículas de alimento.
        2. Se crea la lista de organismos en posiciones aleatorias en el mapa.
        3. Después de cada movimiento del jugador:
        a) Se recorre la estructura de organismos para determinar su
        siguiente acción, i.e. hacia donde se moverán, etc.

        b) Si una partícula de alimento es consumida, se genera una nue-
        va para reemplazarla.

        c) Si un organismo es consumido se genera uno nuevo para reem-
        plazarlo.

        d) Se actualiza la interfaz gráfica.
         */

    }
    
}
