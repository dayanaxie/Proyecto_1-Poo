package Microorganismos;
import java.util.Random;
import Alimento.Alimento;
import Alimento.AlimentoEnergia;
import ConstantsAndEnums.*;
import GUI.*;
import Excepcion.MiExcepcion;
import Mapa.Mapa;
import Personalidad.Personalidad;

import java.util.ArrayList; 
import Interface.Comparable;

// luego podemos reacomodar los metodos de la clase para que no este tan desordenado

public class Microorganismo implements Comparable{
    private int vision;
    private int velocidad;
    private int energia;
    private int edad;
    private int xLocation;
    private int yLocation;
    private int pasos;
    private Personalidad personalidad;
    private Random rand;

    public Microorganismo(int xLocation, int yLocation, Random pRand){
        rand = pRand;
        pasos = 0; 
        velocidad = Constants.MIN_VELOCIDAD + rand.nextInt(Constants.MAX_VELOCIDAD + 1);
        vision = Constants.MIN_VISION + rand.nextInt(Constants.MAX_VISION + 1);  
        edad = Constants.MIN_EDAD + rand.nextInt(Constants.MAX_EDAD + 1);  
        energia = Constants.MIN_ENERGIA + rand.nextInt(Constants.MAX_ENERGIA + 1);  
        this.xLocation = xLocation;
        this.yLocation = yLocation;
        personalidad = new Personalidad(rand);
        
    }

    public Microorganismo(){
        
    }

    /*
     * la idea es que el mismo microorganismo sepa limitar sus propios movimientos
     */
    public boolean posibilidadMoverse(){
        if(pasos < velocidad && energia > 0 ){
            return true;
        }else{
            return false;
        }
    }

    public void resetPasos(){
        pasos = 0;
    }

    private void verificarPosicion() throws MiExcepcion{
        if(xLocation >= Constants.MAP_SIZE || yLocation >= Constants.MAP_SIZE || xLocation <  0 || yLocation < 0 ){
            throw new MiExcepcion();
        }
    }



    private int verificarLimiteVision(int pNum){
        // metodo para verificar que a la hora de recorrer las filas del mapa
        // no se salga
        if(pNum >= Constants.MAP_SIZE){
            pNum = Constants.MAP_SIZE -1;
        }
        else if(pNum < 0){
            pNum = 0;
        }
        return pNum;

    }

    // pueden haber dos metodos comer, uno de alimento y otro de microorganismos
    public void comer(Alimento pAlimento){
        pAlimento.AlimentarMicro(this);

    }

    public void comer(Microorganismo pMicroorganismo, Mapa map, Gui pGui){
        energia += pMicroorganismo.getEnergia() / 2;
        vision += pMicroorganismo.getVision() / 2;
        velocidad += pMicroorganismo.getVelocidad() / 2;
        map.vaciarCasilla(pGui, xLocation, yLocation);
        map.insertarMicroorganismos(pGui, this);

        
    }

    public void enfrentarMicroorganismo(Microorganismo pMicroorganismo, Mapa map, Gui pGui){
        EnumMicroId idComparacion;
        ArrayList<Integer> miListaComp = obtenerListaComparacion();
        ArrayList<Integer> listaPorComp = pMicroorganismo.obtenerListaComparacion();
        int index = 0;
        boolean enfrentamientoTerminado = false;
        while(index < miListaComp.size()){
            idComparacion = compareTo(miListaComp.get(index), listaPorComp.get(index));
            if(idComparacion == EnumMicroId.SON_IGUALES){
                ++ index;
            }else if(idComparacion == EnumMicroId.ESTE_MICRO){
                comer(pMicroorganismo, map, pGui);
                enfrentamientoTerminado = true;
                break;
            }else{
                pMicroorganismo.comer(this, map, pGui);
                enfrentamientoTerminado = true;
                break;
            }
        }
        if(!enfrentamientoTerminado){
            Random nuevoRandom = new Random(123465);
            int random = 0 + nuevoRandom.nextInt(2);
            if(random == 0){
                comer(pMicroorganismo, map, pGui);
            }else{
                pMicroorganismo.comer(this, map, pGui);
            }
        }

    }

    public EnumMicroId analizarMicroorganismo(ArrayList<Integer> miListaComp, ArrayList<Integer> listaPorComp){
        EnumMicroId idComparacion = EnumMicroId.SON_IGUALES;
        int index = 0;
        while(index < miListaComp.size()){
            idComparacion = compareTo(miListaComp.get(index), listaPorComp.get(index));
            if(idComparacion == EnumMicroId.SON_IGUALES){
                ++ index;
            }else{
                if(idComparacion == EnumMicroId.ESTE_MICRO || idComparacion == EnumMicroId.OTRO_MICRO){
                    return idComparacion;
                }
            }
            
        }
        return idComparacion;


    }

    public ArrayList<Integer> obtenerListaComparacion(){
        ArrayList<Integer> caracteristicas = new ArrayList<Integer>();
        caracteristicas.add(energia);
        caracteristicas.add(velocidad);
        caracteristicas.add(edad);
        return caracteristicas;


    }

    public EnumMicroId compareTo(int miCaracteristica, int caracteristicaPorComparar){
        if(miCaracteristica < caracteristicaPorComparar){
            return EnumMicroId.OTRO_MICRO;
        }else if(miCaracteristica > caracteristicaPorComparar){
            return EnumMicroId.ESTE_MICRO;
        }else{
            return EnumMicroId.SON_IGUALES;
        }
    }
    

    public void moverse(Mapa pGameMap, Gui pGui){
        pGameMap.vaciarCasilla(pGui, xLocation, yLocation);
        Object miVision [][] = visualizar(pGameMap.getMap());
        int destinoX = 0;
        int destinoY = 0;
        boolean gotDestination = false;
        
        outerloop: for(int fila = 0; fila < miVision.length; ++fila){
            for(int columna = 0; columna < miVision[0].length; ++columna){
                if(miVision[fila][columna] != null){  
                    // todos los microorganismos van a huir de peligro
                    if(miVision[fila][columna].getClass().equals(this.getClass())){
                        Microorganismo microPeligroso = (Microorganismo)miVision[fila][columna];
                        EnumMicroId peligrosidad = analizarMicroorganismo(this.obtenerListaComparacion(), microPeligroso.obtenerListaComparacion());
                        destinoX = microPeligroso.getxLocation();
                        destinoY = microPeligroso.getyLocation();

                        if(peligrosidad == EnumMicroId.OTRO_MICRO){
                            alejarseDePeligro(destinoX, destinoY);
                            return;
                        }
                    }
                    // los microorganismos van a priotizar los alimentos que tienen energia
                    AlimentoEnergia alimentoEnergy = new AlimentoEnergia();
                    if(miVision[fila][columna].getClass().equals(alimentoEnergy.getClass())){
                        AlimentoEnergia alimento = (AlimentoEnergia)miVision[fila][columna];
                        destinoX = alimento.getxLocation();
                        destinoY = alimento.getyLocation();
                        gotDestination = true;
                        break outerloop;
                    }
                    Alimento alimentoPreferencia = personalidad.generarPreferencia();
                    if(miVision[fila][columna].getClass().equals(alimentoPreferencia.getClass())){
                        destinoX = alimentoPreferencia.getxLocation();
                        destinoY = alimentoPreferencia.getyLocation();
                        gotDestination = true;
                        break outerloop;
                    }
                }
            }
        }
        if(!gotDestination){
            moverseAleatoriamente();
        }else{
            guiarMovimientosAlimento(destinoX, destinoY);
        }
    }


    private void moverseAleatoriamente(){
        int movimientoRand = 0 + rand.nextInt(4);
        switch(movimientoRand){
            case 0:
                moverArriba();
                break;
            case 1:
                moverAbajo();
                break;
            case 2:
                moverDerecha();
                break;
            case 3:
                moverIzquierda();
                break;
        }


    }

    private void alejarseDePeligro(int pDestinoX, int pDestinoY){        
    // este metodo es para que el microorganismo pueda huir.
        if(pDestinoX >= xLocation){
            moverArriba();
            return;
        }
        if(pDestinoX < xLocation ){
            moverAbajo();
            return;
        }
        if(pDestinoY <= yLocation){
            moverDerecha();
            return;
        }
        if(pDestinoY >= yLocation){
            moverIzquierda();
            return;
        }
        
    }

    private void guiarMovimientosAlimento(int pDestinoX, int pDestinoY){
        // este metodo es para que el microorganismo se acerque para comerse a alguien
        if(pDestinoX > xLocation){
            moverAbajo();
        }
        if(pDestinoX < xLocation ){
            moverArriba();
        }
        if(pDestinoY < yLocation){
            moverIzquierda();
        }
        if(pDestinoY > yLocation){
            moverDerecha();
        }
        
        

    }

    // despues de todos los moverse se actualiza el mapa
    public void moverArriba(){
        while(posibilidadMoverse()){
            this.xLocation -= 1;
            try{
                verificarPosicion();
            }catch(MiExcepcion e){
                this.xLocation = 0;
            }

            //this.xLocation = verificarFila(this.xLocation, map);
            pasos += 1;
            decrementarEnergia();
        }
        
        
    }

    public void moverAbajo(){
        
        while(posibilidadMoverse()){
            this.xLocation += 1;
            try{
                verificarPosicion();
            }catch(MiExcepcion e){
                this.xLocation = Constants.MAP_SIZE -1;
            }
            //this.xLocation = verificarFila(this.xLocation, map);
            pasos += 1;
            decrementarEnergia(); 
        }


    }

    public void moverIzquierda(){
        while(posibilidadMoverse()){
            this.yLocation -= 1;
            try{
                verificarPosicion();
            }catch(MiExcepcion e){
                this.yLocation = 0;
            }
            //this.yLocation = verificarColumna(this.yLocation, map);
            pasos += 1;
            decrementarEnergia();    
        }
        
    }

    public void moverDerecha(){
        while(posibilidadMoverse()){
            this.yLocation += 1;
            try{
                verificarPosicion();
            }catch(MiExcepcion e){
                this.yLocation = Constants.MAP_SIZE -1;
            }
            //this.yLocation = verificarColumna(this.yLocation, map);
            pasos += 1;
            decrementarEnergia();  
        }
        
    }

    


    public Object[][] visualizar(Object mapGame [][]){
        // va a retornar una matriz con su campo de vision
        int limiteFila = this.xLocation + this.vision;
        limiteFila = verificarLimiteVision(limiteFila);
        int inicioFila = this.xLocation - this.vision;
        inicioFila = verificarLimiteVision(inicioFila);
        int limiteColumna = this.yLocation + this.vision;
        limiteColumna = verificarLimiteVision(limiteColumna);
        int inicioColumna = this.yLocation - this.vision;
        inicioColumna = verificarLimiteVision(inicioColumna);

        Object miVision [][] = new Object [limiteFila + 1][limiteColumna + 1];

        int miFila = 0;
        int miColumna = 0;
        for(int fila = inicioFila; fila <= limiteFila; ++fila){
            //columna
            for(int columna = inicioColumna; columna <= limiteColumna; ++columna){
                if(fila != this.xLocation || columna != this.yLocation){
                    miVision[miFila][miColumna] = mapGame[fila][columna];
                }
                ++ miColumna;
            }
            ++ miFila;
            miColumna = 0;
        }
        return miVision;
    }

    private void decrementarVelocidad(){
        // apenas el microorganismo aumente de energia llame a este metodo para decrementar la velocidad
        this.velocidad -= Constants.DEC_CHARS;
        if(this.velocidad < 0){
            this.velocidad = 0;
        }        
    }

    private void decrementarEnergia(){
        this.energia -= Constants.DEC_CHARS;
        if(this.energia < 0){
            this.energia = 0;
        }
    }

    public void aumentarEdad(){
        this.edad += Constants.INC_CHARS;
    }

    private void decrementarEdad(){
        decrementarVision();
        this.edad -= Constants.DEC_CHARS;
        if(this.edad < 0){
            this.edad = 0;
        }
    }

    private void decrementarVision(){
        this.vision -= Constants.DEC_CHARS;
        if(this.vision < 0){
            this.vision = 0;
        }
    }

    public String obtenerInfo(){
        String info = "Tiene: " + "velocidad: " + Integer.toString(velocidad) 
        + ", " +  "vision: " + Integer.toString(vision) + ", " + "energia: " 
        + Integer.toString(energia) + ", " + "edad: " + Integer.toString(edad);
    
        return info;

    }
    

    

    public int getxLocation() {
        return xLocation;
    }

    public void setxLocation(int xLocation) {
        this.xLocation = xLocation;
    }

    public int getyLocation() {
        return yLocation;
    }

    public void setyLocation(int yLocation) {
        this.yLocation = yLocation;
    }

    public int getVision() {
        return vision;
    }

    public void setVision(int vision) {
        this.vision = vision;
    }

    public int getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }

    public int getPasos() {
        return pasos;
    }

    public void setPasos(int pasos) {
        this.pasos = pasos;
    }

    public int getEnergia() {
        return energia;
    }

    public void setEnergia(int energia) {
        this.energia = energia;
        velocidad += Constants.INC_CHARS;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }



}
