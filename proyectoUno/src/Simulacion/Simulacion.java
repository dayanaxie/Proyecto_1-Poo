package Simulacion;

import Alimento.AlimentoVision;
import Microorganismos.Jugador;
import Microorganismos.Microorganismo;
import GUI.Gui;
import Mapa.Mapa;
import java.awt.Point;
import java.util.*;
import Alimento.*;
import java.util.Random;

/*
nada de aqui es definitivo aun, es solo para ir probando las cosas
 */ 

public class Simulacion {
    public static void main(String[] args) throws Exception {
        // la semilla se debe de definir al inicio y pasarla a todos los demas
        Random rand = new Random(12345678);
        int turno = 1;
        boolean seguir = true;
        Gui interfaz = new Gui();
        // el jugador siempre va a salir arriba
        Jugador jugador = new Jugador(0, 0, rand);
        Mapa mapa = new Mapa(rand);
        mapa.insertarJugador(interfaz, jugador);
        mapa.definirPosiciones();
        mapa.insertarEnElMapa(interfaz);
        interfaz.mostrarTurno(turno);
        

    }

    /* 
    public static void main(String[] args) throws Exception {
        int turno = 1;
        boolean seguir = true;
        Gui interfaz = new Gui();
        Jugador jugador = new Jugador(0, 0);
        Mapa mapa = new Mapa();
        mapa.insertarJugador(interfaz, jugador);
        interfaz.mostrarTurno(turno);
        //while(seguir){
            // aqui se va a ir estructurando la simulacion

        //     turno += 1; 
        //     interfaz.actualizarTurno(turno);


        //}
        

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
         

    }
*/
    
}
