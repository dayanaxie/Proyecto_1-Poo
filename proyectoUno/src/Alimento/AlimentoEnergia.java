package Alimento;
import Constants.EnumAlimentos;
import Microorganismos.Microorganismo;


public class AlimentoEnergia extends Alimento{
    public AlimentoEnergia(int pSize){
        super(pSize, EnumAlimentos.ENERGIA);
    }    

    public void AlimentarMicro(Microorganismo pMicroorganismo){
        pMicroorganismo.setEnergia(pMicroorganismo.getEnergia() + super.getSize());
        pMicroorganismo.decrementarVelocidad();
    }
    
}