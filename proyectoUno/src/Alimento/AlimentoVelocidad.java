package Alimento;
import Constants.EnumAlimentos;
import Microorganismos.Microorganismo;


public class AlimentoVelocidad extends Alimento{
    public AlimentoVelocidad(int pSize){
        super(pSize, EnumAlimentos.VELOCIDAD);
    }    

    public void AlimentarMicro(Microorganismo pMicroorganismo){
        pMicroorganismo.setVelocidad(pMicroorganismo.getVelocidad() + super.getSize());
    }
    
}
