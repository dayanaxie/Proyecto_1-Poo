package Alimento;
import Constants.EnumAlimentos;
import Microorganismos.Microorganismo;


public class Alimento {
    private int size;
    private EnumAlimentos type;

    public Alimento(int pSize, EnumAlimentos pType){
        this.size = pSize;
        this.type = pType;


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

    public EnumAlimentos getType() {
        return type;
    }

    public void setType(EnumAlimentos type) {
        this.type = type;
    }

    
    
    
}
