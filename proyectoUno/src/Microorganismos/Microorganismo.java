package Microorganismos;
import java.util.Random;

import javax.lang.model.util.ElementScanner6;

import Alimento.Alimento;
import Constants.*;
import Excepcion.MiExcepcion;
import Mapa.Mapa;

// luego podemos reacomodar los metodos de la clase para que no este tan desordenado

public class Microorganismo {
    private int energia;
    private int vision;
    private int velocidad;
    private int edad;
    private int xLocation;
    private int yLocation;
    private int pasos;
    

    private Random rand;

    public Microorganismo(int xLocation, int yLocation, Random pRand){
        rand = pRand;
        pasos = 0; 
        energia = Constants.MIN_ENERGIA + rand.nextInt(Constants.MAX_ENERGIA);
        velocidad = Constants.MIN_VELOCIDAD + rand.nextInt(Constants.MAX_VELOCIDAD);
        edad = Constants.MIN_EDAD + rand.nextInt(Constants.MAX_EDAD);
        this.vision = Constants.MIN_VISION + rand.nextInt(Constants.MAX_VISION);  
        this.xLocation = xLocation;
        this.yLocation = yLocation;
    }

    /*
     * la idea es que el mismo microorganismo sepa limitar sus propios movimientos
     */
    public boolean posibilidadMoverse(){
        if(pasos < velocidad){
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


    public void comer(Microorganismo pMicroorganismo){
        if(this.energia == pMicroorganismo.energia){
            if (this.velocidad == pMicroorganismo.velocidad){
                if(this.edad == pMicroorganismo.vision){

                }else if(this.edad < pMicroorganismo.edad){
                    pMicroorganismo.energia += this.energia / 2;
                    pMicroorganismo.velocidad += this.velocidad / 2;
                    pMicroorganismo.vision += this.vision / 2;
                }
                this.energia += pMicroorganismo.energia / 2;
                this.velocidad += pMicroorganismo.velocidad / 2;
                this.vision += pMicroorganismo.vision / 2;
            }else if(this.velocidad < pMicroorganismo.velocidad){
                pMicroorganismo.energia += this.energia / 2;
                pMicroorganismo.velocidad += this.velocidad / 2;
                pMicroorganismo.vision += this.vision / 2;
            }
            this.energia += pMicroorganismo.energia / 2;
            this.velocidad += pMicroorganismo.velocidad / 2;
            this.vision += pMicroorganismo.vision / 2;

        }else if(this.energia < pMicroorganismo.energia){
            pMicroorganismo.energia += this.energia / 2;
            pMicroorganismo.velocidad += this.velocidad / 2;
            pMicroorganismo.vision += this.vision / 2;
        }
        this.energia += pMicroorganismo.energia / 2;
        this.velocidad += pMicroorganismo.velocidad / 2;
        this.vision += pMicroorganismo.vision / 2;
    }

    public void moverse(){
        if(this.energia > 0){
            ;
        }  
        decrementarEnergia();
        // se aplica la personalidad aqui

    }

    // despues de todos los moverse se actualiza el mapa
    public void moverArriba(){
        this.xLocation -= 1;
        try{
            verificarPosicion();
        }catch(MiExcepcion e){
            this.xLocation = 0;
        }

        //this.xLocation = verificarFila(this.xLocation, map);
        pasos += 1;
    }

    public void moverAbajo(){
        this.xLocation += 1;
        try{
            verificarPosicion();
        }catch(MiExcepcion e){
            this.xLocation = Constants.MAP_SIZE -1;
        }
        //this.xLocation = verificarFila(this.xLocation, map);
        pasos += 1;
    }

    public void moverIzquierda(){
        this.yLocation -= 1;
        try{
            verificarPosicion();
        }catch(MiExcepcion e){
            this.yLocation = 0;
        }
        //this.yLocation = verificarColumna(this.yLocation, map);
        pasos += 1;
    }

    public void moverDerecha(){
        this.yLocation += 1;
        try{
            verificarPosicion();
        }catch(MiExcepcion e){
            this.yLocation = Constants.MAP_SIZE -1;
        }
        //this.yLocation = verificarColumna(this.yLocation, map);
        pasos += 1;
    }


    public void visualizar(Mapa pMap){
        // esto se ve un poco feo, hay que ver como mejorarlo
        Object mapGame [][] = pMap.getMap();
        int limiteFila = this.xLocation + this.vision;
        limiteFila = verificarLimiteVision(limiteFila);
        int inicioFila = this.xLocation - this.vision;
        inicioFila = verificarLimiteVision(inicioFila);
        int limiteColumna = this.yLocation + this.vision;
        limiteColumna = verificarLimiteVision(limiteColumna);
        int inicioColumna = this.yLocation - this.vision;
        inicioColumna = verificarLimiteVision(inicioColumna);

        for(int fila = inicioFila; fila <= limiteFila; ++fila){
            //columna
            for(int columna = inicioColumna; columna <= limiteColumna; ++columna){
                System.out.println("fila: " + fila);
                System.out.println("columna: " + columna);
                if(fila != this.xLocation || columna != this.yLocation){
                    // para que no se revise a si mismo
                    System.out.println("Elementos dentro de la vision del microorganismo: " + mapGame[fila][columna]);
                    // luego se adapta a la interfaz, esto es provisional
                }
            }
        }
    }

    public void decrementarEnergia(){
        // apenas el microorganismo se mueva llame a este metodo para decrementar la energia
        this.energia -= Constants.DEC_CHARS;
        if(this.energia < 0){
            this.energia = 0;
        }        
    }

    public void decrementarVelocidad(){
        // apenas el microorganismo aumente de energia llame a este metodo para decrementar la velocidad
        this.velocidad -= Constants.DEC_CHARS;
        if(this.velocidad < 0){
            this.velocidad = 0;
        }        
    }

    public String obtenerInfo(){
        String info = "Tiene: " + "velocidad: " + Integer.toString(velocidad) 
        + ", " +  "vision: " + Integer.toString(vision);
    
        return info;
    }
    
    public void decrementarVision(){
        // debe de haber un metodo moverse en el microorganismo en donde cada vez que se alimente
        // y aumente su vida, llame a este metodo para decrementar su vision
        this.vision -= Constants.DEC_CHARS; 
        if(this.vision < 0){
            this.vision = 0;
        }
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

    public int getEdad() {
        return vision;
    }

    public void setEdad(int vision) {
        this.vision = vision;
    }

    public int getEnergia() {
        return vision;
    }

    public void setEnergia(int vision) {
        this.vision = vision;
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


}
