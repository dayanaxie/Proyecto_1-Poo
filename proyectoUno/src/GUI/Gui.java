package GUI;
import Constants.Constants;

import java.awt.GridLayout;
import java.awt.event.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;


public class Gui implements ActionListener{
    JFrame ventana;
    JPanel panel;

    public Gui(){
        ventana = new JFrame("Simulacion de microorganismos");
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        agregarComponentes();

        ventana.pack();
        ventana.setVisible(true);
    }

    private void crearMatrizBotones(){
        panel = new JPanel();
        panel.setLayout(new GridLayout(Constants.MAP_SIZE, Constants.MAP_SIZE));
        // matriz de botones del tamanio del mapa
        JButton [][] mapButton = new JButton[Constants.MAP_SIZE][Constants.MAP_SIZE];
        for(int fila = 0;  fila < Constants.MAP_SIZE; ++ fila){
            for(int columna = 0; columna < Constants.MAP_SIZE; ++ columna){
                JButton boton = new JButton();
                boton.setSize(Constants.MAP_SIZE / Constants.MAP_SIZE, Constants.MAP_SIZE / Constants.MAP_SIZE);
                boton.setToolTipText(Integer.toString(fila) + ", " + Integer.toString(columna));
                boton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e ){
                        // solo para ver que sirve, luego lo cambio 
                        System.out.print("Soy el boton: " + boton.getToolTipText());
                    }
                });
                mapButton[fila][columna] = boton;
                panel.add(mapButton[fila][columna]);

            }
        }
    }




    public void agregarComponentes(){

        crearMatrizBotones();
        ventana.add(panel);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        
   
        
    }
    
}
