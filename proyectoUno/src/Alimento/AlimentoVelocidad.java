package Alimento;
import ConstantsAndEnums.EnumAlimentos;
import Microorganismos.Microorganismo;


public class AlimentoVelocidad extends Alimento{
    public AlimentoVelocidad(int pSize, int xLocation, int yLocation){
        super(pSize, EnumAlimentos.VELOCIDAD, xLocation, yLocation);
    }    
    public AlimentoVelocidad(){
        super();

    }

    public void AlimentarMicro(Microorganismo pMicroorganismo){
        pMicroorganismo.setVelocidad(pMicroorganismo.getVelocidad() + super.getSize());
    }
    
}
