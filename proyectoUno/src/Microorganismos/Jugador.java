package Microorganismos;
import java.util.Random;

public class Jugador extends Microorganismo{
    // no esta del todo estructurado, solo lo basico que hemos discutido

    public Jugador(int xLocation, int yLocation, Random pRand){
        // importante recordar, no pueden haber dos cosas en una casilla del mapa
        // tenemos que hacer una validacion de eso
        super(xLocation,yLocation, pRand); 

    }

    
}
