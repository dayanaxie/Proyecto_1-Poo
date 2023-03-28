package Mapa;
import Constants.Constants;
import Microorganismos.Microorganismo;
import GUI.Gui;

public class Mapa {
    private Object map [][];
    private Microorganismo jugador;

    public Mapa(){
        this.map = new Object[Constants.MAP_SIZE][Constants.MAP_SIZE];

    }

    public void insertarJugador(Gui pGui, Microorganismo pJugador){
        jugador = pJugador;
        this.map[pJugador.getxLocation()][pJugador.getyLocation()] = pJugador;
        pGui.insertarJugador(pJugador.getxLocation(), pJugador.getyLocation(), this);


    }

    public Object[][] getMap() {
        return map;
    }

    public Microorganismo getJugador() {
        return jugador;
    }

    public void setJugador(Microorganismo jugador) {
        this.jugador = jugador;
    }
    
    
}
