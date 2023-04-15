package Alimento;

import ConstantsAndEnums.EnumAlimentos;
import Microorganismos.Microorganismo;


public class Alimento {
    private int xLocation;
    private int yLocation;
    private int size;
    private EnumAlimentos type;

    public Alimento(int pSize, EnumAlimentos pType, int xLocation, int yLocation) {
        this.size = pSize;
        this.type = pType;
        this.xLocation = xLocation;
        this.yLocation = yLocation;

    }

    public Alimento() {
        this.size = 0;
        this.xLocation = 0;
        this.yLocation = 0;
    }

    // metodo en el que se va a aplicar polimorfismo
    public void AlimentarMicro(Microorganismo pMicroorganismo) {
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

    public int getxLocation() {
        return xLocation;
    }

    public void setxLocation(int xLocation) {
        this.xLocation = xLocation;
    }

    public int getyLocation() {
        return yLocation;
    }

    public void setyLocation(int yLocation) {
        this.yLocation = yLocation;
    }

}
