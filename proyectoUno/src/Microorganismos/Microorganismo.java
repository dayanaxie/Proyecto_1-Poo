package Microorganismos;
import java.util.Random;
import Alimento.Alimento;
import Constants.*;

public class Microorganismo {
    private int vision;
    private int xLocation;
    private int yLocation;

    private Random rand = new Random(12345678);

    public Microorganismo(int xLocation, int yLocation){
        this.vision = CharConstants.MIN_VISION + rand.nextInt(CharConstants.MAX_VISION);
        // la ubicacion del microorganismo en el mapa
        this.xLocation = xLocation;
        this.yLocation = yLocation;
        

    }

    private void visualizar(){
        // idea principal, falta adaptarlo al contexto del proyecto y si es posible hacerlo mas efectivo
        //Object mapGame [][] = pMap.getMap();
        int map [][] = {{1,1,1,1,1},{1,1,1,1,1}, {0,0,0,1,1}, {0,0,0,1,1},  {0,0,0,1,1}};
        int vision = 1;
        int ubicacionX = 3;
        int ubicacionY = 1;
        // fila
        for(int fila = ubicacionX - vision; fila <= ubicacionX + vision; ++fila){
            //columna
            for(int columna = ubicacionY - vision; columna <= ubicacionY + vision; ++columna){
                System.out.println("fila: " + fila);
                System.out.println("columna: " + columna);
                System.out.println(map[fila][columna]);
                System.out.println("-------------");
            }


        }


        

    }
    
    // pueden haber dos metodos comer, uno de alimento y otro de microorganismos
    public void comer(Alimento pAlimento){
        System.out.println("antes " + this.vision);     //un print de prueba
        pAlimento.AlimentarMicro(this);
        System.out.println("despues " + this.vision);

    }

    public void decrementarVision(){
        // debe de haber un metodo moverse en el microorganismo en donde cada vez que se alimente
        // y aumente su edad, llame a este metodo para decrementar su vision
        this.vision -= Constants.IncDecConstants.DEC_VISION; 
    }

    

    public int getVision() {
        return vision;
    }

    public void setVision(int vision) {
        this.vision = vision;
    }

    
}
