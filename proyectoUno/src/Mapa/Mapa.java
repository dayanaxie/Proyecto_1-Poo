package Mapa;
import Constants.Constants;
import Microorganismos.Microorganismo;
import GUI.Gui;
import java.awt.Point;
import java.util.*;
import Alimento.*;

import Alimento.Alimento;


public class Mapa {
    private Object map [][];
    private Microorganismo jugador;
    private List<Point> posicionesMicro;
    private List<Point> posicionesAlimento;
    private List<Point> posicionesMapa;
    private Random rand;

    public Mapa(Random pRand){
        this.map = new Object[Constants.MAP_SIZE][Constants.MAP_SIZE];
        posicionesMicro = new ArrayList<>();
        posicionesAlimento = new ArrayList<>();
        posicionesMapa = new ArrayList<>();
        rand = pRand;

    }

    public boolean revisarRepetidos(int x, int y){
        for(Point point : posicionesMapa){
            if((int)point.getX() == x && (int)point.getY() == y){
                return true;
            }
        }
        return false;


    }

    // tengo que probar si sirve
    public void definirPosiciones(){
        int index = 0;
        while(index < Constants.CANT_MICRO + Constants.CANT_ALIMENTO){
            int x = 0 + rand.nextInt(Constants.MAP_SIZE);
            int y = 0 + rand.nextInt(Constants.MAP_SIZE);
            // si no estan repetidos
            if(!revisarRepetidos(x, y)){
                Point location = new Point(x, y);
                if(posicionesMicro.size() < Constants.CANT_MICRO){
                    posicionesMicro.add(location);
                }else if(posicionesAlimento.size() < Constants.CANT_ALIMENTO){
                    posicionesAlimento.add(location);
                }
                posicionesMapa.add(location);
                ++ index;
            }

        }

    }


    public void vaciarCasilla(Gui pGui, int x, int y){
        this.map[x][y] = null;
        Point location = new Point(x, y);
        posicionesMapa.remove(location);
        pGui.vaciarCasilla(x, y);


    }

    // los metodos insertar espara meterlos al mapa apenas se genera las posiciones

    public void insertarEnElMapa(Gui pGui){
        // se tiene que crear aqui los microorganismos y alimentos
        for(Point pointMicro : posicionesMicro){
            int x = (int)pointMicro.getX();
            int y = (int)pointMicro.getY();
            // crear el micro y meterlo a map
            Microorganismo micro = new Microorganismo(x, y, rand); //hay que hacer un generador para las diferentes personaidades
            map[x][y] = micro;
            pGui.insertarMicroorganismos(x, y, this);
        }

        for(Point pointAlimento : posicionesAlimento){
            int x = (int)pointAlimento.getX();
            int y = (int)pointAlimento.getY();
            GeneradorAlimento generador = new GeneradorAlimento();
            Alimento alimento = generador.generarAlimento(rand);
            map[x][y] = alimento;
            pGui.insertarAlimentos(x, y,alimento. getType() ,alimento.getSize(), this);
        }

    }


    // los metodos de moverEnElMapa es para actualizar su posicion cuando se mueven

    public void insertarJugador(Gui pGui, Microorganismo pJugador){
        // asumo que la posicion del jugador ya viene revisada
        jugador = pJugador;
        this.map[pJugador.getxLocation()][pJugador.getyLocation()] = pJugador;
        Point location = new Point(pJugador.getxLocation(), pJugador.getyLocation());
        posicionesMapa.add(location);
        pGui.insertarJugador(pJugador.getxLocation(), pJugador.getyLocation(), this);
    }

    public void moverEnElMapa(Gui pGui, Microorganismo pMicroorganismo){
      


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

    public List<Point> getPosicionesMicro() {
        return posicionesMicro;
    }

    public void setPosicionesMicro(List<Point> posicionesMicro) {
        this.posicionesMicro = posicionesMicro;
    }

    public List<Point> posicionesAlimento() {
        return posicionesAlimento;
    }

    public void posicionesAlimento(List<Point> posicionesAlimeto) {
        this.posicionesAlimento = posicionesAlimeto;
    }

    public List<Point> getPosicionesMapa() {
        return posicionesMapa;
    }

    public void setPosicionesMapa(List<Point> posicionesMapa) {
        this.posicionesMapa = posicionesMapa;
    }
    
    
}
