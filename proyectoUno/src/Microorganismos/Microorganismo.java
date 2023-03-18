package Microorganismos;
import java.util.Random;
import Alimento.Alimento;
import Constants.*;
import Mapa.Mapa;

public class Microorganismo {
    private int vision;
    private int xLocation;
    private int yLocation;
    

    private Random rand = new Random(12345678);

    public Microorganismo(int xLocation, int yLocation){
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
    
    // pueden haber dos metodos comer, uno de alimento y otro de microorganismos
    public void comer(Alimento pAlimento){
        pAlimento.AlimentarMicro(this);

    }

    public void decrementarVision(){
        // debe de haber un metodo moverse en el microorganismo en donde cada vez que se alimente
        // y aumente su vida, llame a este metodo para decrementar su vision
        this.vision -= Constants.IncDecConstants.DEC_VISION; 
    }

    

    public int getVision() {
        return vision;
    }

    public void setVision(int vision) {
        this.vision = vision;
    }


}
