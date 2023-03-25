package Mapa;
import Constants.Constants;


public class Mapa {
    private Object map [][];

    public Mapa(){
        this.map = new Object[Constants.MAP_SIZE][Constants.MAP_SIZE];

    }

    public Object[][] getMap() {
        return map;
    }
    
}
