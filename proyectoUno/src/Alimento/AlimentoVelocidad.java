package Alimento;
import Microorganismos.Microorganismo;


public class AlimentoVelocidad extends Alimento{
    public AlimentoVelocidad(int pSize){
        super(pSize);
    }    

    public void AlimentarMicro(Microorganismo pMicroorganismo){
        pMicroorganismo.setVelocidad(pMicroorganismo.getVelocidad() + super.getSize());
    }
    
}
