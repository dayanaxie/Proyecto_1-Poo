package Mapa;
import ConstantsAndEnums.Constants;
import Microorganismos.Microorganismo;
import GUI.Gui;
import java.awt.Point;
import java.util.*;
import Alimento.*;

import Alimento.Alimento;


public class Mapa {
    private Object map [][];
    private Microorganismo jugador;
    private List<Alimento> alimentos;
    private List<Microorganismo> microorganismos;
    private List<Point> posicionesMicro;
    private List<Point> posicionesAlimento;
    private List<Point> posicionesMapa;
    private Random rand;

    public Mapa(Random pRand){
        this.map = new Object[Constants.MAP_SIZE][Constants.MAP_SIZE];
        alimentos = new ArrayList<>();
        microorganismos = new ArrayList<>();
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

    

    public void revisarEncuentros(Gui pGui){
        
        for(Point ubicacion : posicionesMapa){
            for(Point ubicacion2 : posicionesMapa){
                if(ubicacion == ubicacion2){
                    if(posicionesAlimento.contains(ubicacion) && posicionesMicro.contains(ubicacion)){
                        Microorganismo micro = encontrarMicroorganismo((int)ubicacion.getX(), (int)ubicacion.getY());
                        Alimento alimento = encontrarAlimento((int)ubicacion.getX(), (int)ubicacion.getY());
                        encuentro(pGui, micro, alimento);
                    }else if(posicionesMicro.contains(ubicacion)){
                        Microorganismo micro = encontrarMicroorganismo((int)ubicacion.getX(), (int)ubicacion.getY());
                        Microorganismo micro2 = encontrarMicroorganismo(micro);
                        encuentro(micro, micro2, pGui);
                    }
                }

            }
            


        }
    }

    public void revisarRegeneracion(Gui pGui){
        if(posicionesAlimento.size() < Constants.CANT_ALIMENTO){
            while(posicionesAlimento.size() < Constants.CANT_ALIMENTO ){
                Point location = crearPosicion();
                int x = (int)location.getX();
                int y = (int)location.getY();
                GeneradorAlimento generador = new GeneradorAlimento();
                Alimento alimento = generador.generarAlimento(rand, x, y);
                map[x][y] = alimento;
                pGui.insertarAlimentos(x, y,alimento. getType() ,alimento.getSize(), this);
            }
        }else if(posicionesMicro.size() < Constants.CANT_MICRO){
            while(posicionesMicro.size() < Constants.CANT_MICRO ){
                Point location = crearPosicion();
                int x = (int)location.getX();
                int y = (int)location.getY();
                Microorganismo micro = new Microorganismo(x, y, rand);
                map[x][y] = micro;
                posicionesMicro.add(location);
                pGui.insertarMicroorganismos(x, y, this);
            }
        }
    }

    

    public void encuentro(Microorganismo micro1, Microorganismo micro2, Gui pGui){
        micro1.enfrentarMicroorganismo(micro2, this, pGui);


    }
    public void encuentro(Gui gui, Microorganismo micro1, Alimento alimento){
        alimento.AlimentarMicro(micro1);
        vaciarCasilla(gui, alimento.getxLocation(), alimento.getyLocation());
        insertarMicroorganismos(gui, micro1);
    }

   

    public void insertarEnElMapa(Gui pGui){
        // se tiene que crear aqui los microorganismos y alimentos
        for(Point pointMicro : posicionesMicro){
            int x = (int)pointMicro.getX();
            int y = (int)pointMicro.getY();
            // crear el micro y meterlo a map
            Microorganismo micro = new Microorganismo(x, y, rand); 
            map[x][y] = micro;
            pGui.insertarMicroorganismos(x, y, this);
        }

        for(Point pointAlimento : posicionesAlimento){
            int x = (int)pointAlimento.getX();
            int y = (int)pointAlimento.getY();
            GeneradorAlimento generador = new GeneradorAlimento();
            Alimento alimento = generador.generarAlimento(rand, x, y);
            map[x][y] = alimento;
            pGui.insertarAlimentos(x, y, alimento.getType(),alimento.getSize(), this);
        }

    }
    
    public Point crearPosicion(){
        Point location = new Point();
        boolean creado = false;
        while(!creado){
            int x = 0 + rand.nextInt(Constants.MAP_SIZE);
            int y = 0 + rand.nextInt(Constants.MAP_SIZE);
            if(!revisarRepetidos(x, y)){
                location = new Point(x, y);
                posicionesMapa.add(location);
                creado = true;
            }
    

        }  
        return location;      
        

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

    private Alimento encontrarAlimento(int x, int y){
        Alimento alimentoEncontrado = new Alimento();
        for(Alimento alimento : alimentos){
            if(alimento.getxLocation() == x && alimento.getyLocation() == y){
                alimentoEncontrado =  alimento;
            }
        }
        return alimentoEncontrado;

    }

    private Microorganismo encontrarMicroorganismo(int x, int y){
        Microorganismo microEncontrado = new Microorganismo();
        for(Microorganismo microorganismo : microorganismos){
            if(microorganismo.getxLocation() == x && microorganismo.getyLocation() == y){
                microEncontrado =  microorganismo;
            }
        }
        return microEncontrado;

    }

    private Microorganismo encontrarMicroorganismo(Microorganismo micro){
        Microorganismo microEncontrado = new Microorganismo();
        for(Microorganismo microorganismo : microorganismos){
            if(microorganismo.getxLocation() == micro.getxLocation() && microorganismo.getyLocation() == micro.getyLocation()){
                if(microorganismo != micro){
                    System.out.println("lo encontre");
                    microEncontrado =  microorganismo;
                }
            }
        }
        return microEncontrado;

    }


    public void vaciarCasilla(Gui pGui, int x, int y){
        this.map[x][y] = null;
        Point location = new Point(x, y);
        posicionesMapa.remove(location);
        if(posicionesAlimento.contains(location)){
            posicionesAlimento.remove(location);
            Alimento alimento = encontrarAlimento(x, y);
            alimentos.remove(alimento);
        }else if(posicionesMicro.contains(location)){
            posicionesMicro.remove(location);
            Microorganismo micro = encontrarMicroorganismo(x, y);
            microorganismos.remove(micro);
        }
        pGui.vaciarCasilla(x, y);

    }

    // los metodos insertar espara meterlos al mapa apenas se genera las posiciones

    public void insertarAlimentos(Gui pGui, Alimento pAlimento){
        this.map[pAlimento.getxLocation()][pAlimento.getyLocation()] = pAlimento;
        alimentos.add(pAlimento);
        Point location = new Point(pAlimento.getxLocation(), pAlimento.getyLocation());
        posicionesMapa.add(location);
        posicionesAlimento.add(location);
        pGui.insertarAlimentos(pAlimento.getxLocation(), pAlimento.getyLocation(), pAlimento.getType(), pAlimento.getSize(),this);

    }
    public void insertarMicroorganismos(Gui pGui, Microorganismo pMicroorganismo){
        microorganismos.add(pMicroorganismo);
        this.map[pMicroorganismo.getxLocation()][pMicroorganismo.getyLocation()] = pMicroorganismo;
        Point location = new Point(pMicroorganismo.getxLocation(), pMicroorganismo.getyLocation());
        posicionesMapa.add(location);
        posicionesMicro.add(location);
        pGui.insertarMicroorganismos(pMicroorganismo.getxLocation(), pMicroorganismo.getyLocation(), this);

    }

    public void insertarJugador(Gui pGui, Microorganismo pJugador){
        jugador = pJugador;
        this.map[pJugador.getxLocation()][pJugador.getyLocation()] = pJugador;
        Point location = new Point(pJugador.getxLocation(), pJugador.getyLocation());
        posicionesMapa.add(location);
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

    public List<Alimento> getAlimentos() {
        return alimentos;
    }

    public void setAlimentos(List<Alimento> alimentos) {
        this.alimentos = alimentos;
    }

    public List<Microorganismo> getMicroorganismos() {
        return microorganismos;
    }

    public void setMicroorganismos(List<Microorganismo> microorganismos) {
        this.microorganismos = microorganismos;
    }
    
    
}
