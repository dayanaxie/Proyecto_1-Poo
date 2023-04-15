package Alimento;

import java.util.Random;


public class GeneradorAlimento {
    private Random rand;
    private Alimento alimento;

    public Alimento generarAlimento(Random pRand, int xLocation, int yLocation){
        rand = pRand;
        int tipoAlimento  = 1 + rand.nextInt(3); 
        int size  = 1 + rand.nextInt(2);
        switch(tipoAlimento){
            case 1: 
                alimento = new AlimentoVelocidad(size, xLocation, yLocation);

            case 2:
                alimento = new AlimentoVision(size, xLocation, yLocation);
            
            case 3:
               alimento = new AlimentoEnergia(size, xLocation, yLocation);

        }     
        return alimento;



    }
    
}
