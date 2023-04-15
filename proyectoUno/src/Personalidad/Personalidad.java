package Personalidad;

import java.util.Random;
import Alimento.*;

public class Personalidad {
    private boolean preferenciaEnergia;
    private boolean priotizarVelocidad;
    private boolean priotizarVision;
    private Random rand;


    public Personalidad(Random rand){
        this.rand = rand;
        preferenciaEnergia = true;
        priotizarVelocidad = rand.nextBoolean();
        priotizarVision = !priotizarVelocidad;
    }

    public Alimento generarPreferencia(){
        Alimento alimentoPreferencia;
        if(priotizarVelocidad){
            alimentoPreferencia = new AlimentoVelocidad();
        }else{
            alimentoPreferencia = new AlimentoVision();
        }
        return alimentoPreferencia;
    }


    public boolean isPreferenciaEnergia() {
        return preferenciaEnergia;
    }


    public void setPreferenciaEnergia(boolean preferenciaEnergia) {
        this.preferenciaEnergia = preferenciaEnergia;
    }


    public boolean isPriotizarVelocidad() {
        return priotizarVelocidad;
    }


    public void setPriotizarVelocidad(boolean priotizarVelocidad) {
        this.priotizarVelocidad = priotizarVelocidad;
    }


    public boolean isPriotizarVision() {
        return priotizarVision;
    }


    public void setPriotizarVision(boolean priotizarVision) {
        this.priotizarVision = priotizarVision;
    }


    
    
}
