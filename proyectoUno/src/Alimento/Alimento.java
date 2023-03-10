package Alimento;
import Microorganismos.Microorganismo;


public class Alimento {
    private int size;
    private int amount;

    public Alimento(int pSize){
        this.size = pSize;

    }

    // metodo en el que se va a aplicar polimorfismo
    public void AlimentarMicro(Microorganismo pMicroorganismo){
    };

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    
    
    
}
