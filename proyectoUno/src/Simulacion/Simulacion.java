package Simulacion;

import Alimento.AlimentoVision;
import Microorganismos.Microorganismo;

public class Simulacion {
    public static void main(String[] args) throws Exception {
        // probando lo implementado, luego lo quito
        Microorganismo micro = new Microorganismo(1, 2);
        AlimentoVision alimento = new AlimentoVision(7);
        micro.comer(alimento);

        

    }
    
}
