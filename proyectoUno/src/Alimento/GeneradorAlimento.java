package Alimento;

import java.util.Random;


public class GeneradorAlimento {
    private Random rand;
    private Alimento alimento;

    public Alimento generarAlimento(Random pRand){
        rand = pRand;
        int tipoAlimento  = 1 + rand.nextInt(2);  // luego se cambia cuando ya esten todos los alimentos
        //System.out.println("TIPO" + " " + tipoAlimento);
        int size  = 1 + rand.nextInt(2);
        //System.out.println("SIZW" + " " + size);
        switch(tipoAlimento){
            case 1: 
                alimento = new AlimentoVelocidad(size);

            case 2:
                alimento = new AlimentoVision(size);
            
            //case 3:
            //   alimento = new AlimentoEnergia(size);

        }     
        return alimento;



    }
    
}
