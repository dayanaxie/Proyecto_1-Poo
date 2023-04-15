package Alimento;
import ConstantsAndEnums.EnumAlimentos;
import Microorganismos.Microorganismo;

public class AlimentoEnergia extends Alimento{

    public AlimentoEnergia(int pSize, int xLocation, int yLocation){
        super(pSize, EnumAlimentos.ENERGIA, xLocation, yLocation);
    }    

    public AlimentoEnergia(){
        super();
    }

    public void AlimentarMicro(Microorganismo pMicroorganismo){
        pMicroorganismo.setVelocidad(pMicroorganismo.getEnergia() + super.getSize());
    }
    
}
