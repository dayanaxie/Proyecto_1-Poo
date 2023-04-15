package Simulacion;

import Microorganismos.*;
import GUI.*;
import Mapa.*;
import java.util.Random;
import java.util.*;
import Alimento.AlimentoEnergia;

/*
nada de aqui es definitivo aun, es solo para ir probando las cosas
 */ 

public class Simulacion {
    public static void main(String[] args) throws Exception {
        // la semilla se debe de definir al inicio y pasarla a todos los demas
        Random rand = new Random(12345678);
        int turno = 1;
        Gui interfaz = new Gui();
        // el jugador siempre va a salir arriba
        Jugador jugador = new Jugador(0, 0, rand);
        Mapa mapa = new Mapa(rand);
        mapa.insertarJugador(interfaz, jugador);
        mapa.definirPosiciones();
        mapa.insertarEnElMapa(interfaz);
        interfaz.mostrarTurno(turno);
        List<Microorganismo> microorganismos = mapa.getMicroorganismos();
        while(true){
            while(jugador.posibilidadMoverse()){

                if(!jugador.posibilidadMoverse()){
                    break;
                }

            }
            for(Microorganismo micro : microorganismos){
                micro.moverse(mapa, interfaz);
                interfaz.moverMicroorganismo(mapa, micro);
            }
            

            jugador.resetPasos();
            //mapa.revisarEncuentros(interfaz);
            jugador.aumentarEdad();
            ++ turno;
            interfaz.actualizarTurno(turno);
            interfaz.actualizarInterfaz();
            interfaz.activarBotones();
        }

        

    }    
}
