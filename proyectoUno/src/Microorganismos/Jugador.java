package Microorganismos;

public class Jugador extends Microorganismo{
    // no esta del todo estructurado, solo lo basico que hemos discutido

    public Jugador(int xLocation, int yLocation){
        // importante recordar, no pueden haber dos cosas en una casilla del mapa
        // tenemos que haccer una validacion de eso
        super(xLocation,yLocation); 
    }


    // aqui se hace la sobreescritura del metodo que ya se encarga el jugador de controlar
    @Override
    public void moverse(){
        // falta completarlo

    }
    
}