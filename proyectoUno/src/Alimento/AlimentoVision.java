package Alimento;

import Microorganismos.Microorganismo;


public class AlimentoVision extends Alimento{
    public AlimentoVision(int pSize){
        super(pSize);
    }    

    public void AlimentarMicro(Microorganismo pMicroorganismo){
        pMicroorganismo.setVision(pMicroorganismo.getVision() + super.getSize());
    }
    
}
