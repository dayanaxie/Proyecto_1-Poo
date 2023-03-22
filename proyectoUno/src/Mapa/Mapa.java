package Mapa;

public class Mapa {
    private int size;
    private Object map [][];

    public Mapa(){
        this.size = 50;
        this.map = new Object[this.size][this.size];

    }

    public Object[][] getMap() {
        return map;
    }
    
}
