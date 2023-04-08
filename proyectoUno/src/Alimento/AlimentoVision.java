package Alimento;

import Constants.EnumAlimentos;
import Microorganismos.Microorganismo;


public class AlimentoVision extends Alimento{
    public AlimentoVision(int pSize){
        super(pSize, EnumAlimentos.VISION);
    }    

    public void AlimentarMicro(Microorganismo pMicroorganismo){
        pMicroorganismo.setVision(pMicroorganismo.getVision() + super.getSize());
    }
    
}
