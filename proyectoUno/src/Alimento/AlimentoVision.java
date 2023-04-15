package Alimento;

import ConstantsAndEnums.EnumAlimentos;
import Microorganismos.Microorganismo;


public class AlimentoVision extends Alimento{
    public AlimentoVision(int pSize, int xLocation, int yLocation){
        super(pSize, EnumAlimentos.VISION, xLocation, yLocation);
    }    

    public AlimentoVision(){
        super();

    }

    public void AlimentarMicro(Microorganismo pMicroorganismo){
        pMicroorganismo.setVision(pMicroorganismo.getVision() + super.getSize());
    }
    
}
