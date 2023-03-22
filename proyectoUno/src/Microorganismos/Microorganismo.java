package Microorganismos;
import java.util.Random;
import Alimento.Alimento;
import Constants.*;
import Mapa.Mapa;

// luego podemos reacomodar los metodos de la clase para que no este tan desordenado

public class Microorganismo {
    private int vision;
    private int velocidad;
    private int xLocation;
    private int yLocation;
    

    private Random rand = new Random(12345678);

    public Microorganismo(int xLocation, int yLocation){
        this.velocidad = CharConstants.MIN_VISION + rand.nextInt(CharConstants.MAX_VISION);
        this.vision = CharConstants.MIN_VISION + rand.nextInt(CharConstants.MAX_VISION);  // este rand hay que revisarlo
        // la ubicacion del microorganismo en el mapa
        this.xLocation = xLocation;
        this.yLocation = yLocation;
        

    }

    private int verificarFila(int pFila, Object[][] pMapGame){
        // metodo para verificar que a la hora de recorrer las filas del mapa
        // no se salga
        if(pFila >= pMapGame.length){
            pFila = pMapGame.length -1;
        }
        else if(pFila < 0){
            pFila = 0;
        }
        return pFila;

    }

    private int verificarColumna(int pColumna, Object[][] pMapGame){
        // metodo para verificar que a la hora de recorrer las columnas del mapa
        // no se salga
        if(pColumna >= pMapGame[0].length){
            pColumna = pMapGame[0].length - 1;
        }
        else if(pColumna < 0){
            pColumna = 0;
        }

        return pColumna;
    }

    // pueden haber dos metodos comer, uno de alimento y otro de microorganismos
    public void comer(Alimento pAlimento){
        pAlimento.AlimentarMicro(this);

    }

    public void comer(Microorganismo pMicroorganismo){
        
    }

    public void moverse(){
        // falta

    }


    public void visualizar(Mapa pMap){
        // esto se ve un poco feo, hay que ver como mejorarlo
        Object mapGame [][] = pMap.getMap();
        int limiteFila = this.xLocation + this.vision;
        limiteFila = verificarFila(limiteFila, mapGame);
        int inicioFila = this.xLocation - this.vision;
        inicioFila = verificarFila(inicioFila, mapGame);
        int limiteColumna = this.yLocation + this.vision;
        limiteColumna = verificarColumna(limiteColumna, mapGame);
        int inicioColumna = this.yLocation - this.vision;
        inicioColumna = verificarColumna(inicioColumna, mapGame);

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

    private void decrementarVelocidad(){
        // apenas el microorganismo aumente de energia llame a este metodo para decrementar la velocidad
        this.velocidad -= IncDecConstants.DEC_CHARS;
        if(this.velocidad < 0){
            this.velocidad = 0;
        }

        
        
    }
    

    public void decrementarVision(){
        // debe de haber un metodo moverse en el microorganismo en donde cada vez que se alimente
        // y aumente su vida, llame a este metodo para decrementar su vision
        this.vision -= IncDecConstants.DEC_CHARS; 
        if(this.vision < 0){
            this.vision = 0;
        }
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


}
