package Simulacion;

import Alimento.AlimentoVision;
import Microorganismos.Microorganismo;
import GUI.Gui;

 
public class Simulacion {
    public static void main(String[] args) throws Exception {
        //Gui interfaz = new Gui();
        // va a haber un metodo para mover derecha, izq, arriba y abajo
        int map [][] = {{0,0,1,0}, {0,0,0,0}, {1,0,7,1}, {0,0,0,0}, {0,0,1,0}};
        int vision = 2;
        int coordsX = 2;
        int coordsY = 2;
        System.out.println("Yo: " + map[coordsX][coordsY]);


        
    }

    public void moverArriba(int vision, int x, int map [][]){
        x -= vision;
        if(x < 0){
            x = 0;
        }




        
    }
    
}
