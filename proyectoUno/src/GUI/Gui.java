package GUI;
import Constants.Constants;

import java.awt.BorderLayout;
import java.awt.Color;

import java.awt.GridLayout;
import java.awt.event.*;
import javax.swing.JOptionPane;
import javax.swing.JLabel;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import Mapa.Mapa;

/*
 * Los microorganismos van a ser de color verde
 * Los alimentos van a ser de diferentes tonalidades de gris
 * El jugador va a ser de color azul
 */

public class Gui implements ActionListener{
    Mapa gameMap;
    JFrame ventanaMapa;
    JPanel panelMapa;
    JPanel panelFlechas;
    JButton [][] mapButton;
    JButton botonArriba;
    JButton botonAbajo;
    JButton botonDerecha;
    JButton botonIzquierda;

    public Gui(){
        ventanaMapa = new JFrame("Simulacion de microorganismos");
        ventanaMapa.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventanaMapa.setExtendedState(JFrame.MAXIMIZED_BOTH);
        agregarComponentes();

        ventanaMapa.pack();
        ventanaMapa.setVisible(true);
    }

    private void crearMatrizBotones(){
        panelMapa.setLayout(new GridLayout(Constants.MAP_SIZE, Constants.MAP_SIZE));
        // matriz de botones del tamanio del mapa
        for(int fila = 0;  fila < Constants.MAP_SIZE; ++ fila){
            for(int columna = 0; columna < Constants.MAP_SIZE; ++ columna){
                JButton boton = new JButton();
                //boton.setSize(Constants.MAP_SIZE / Constants.MAP_SIZE, Constants.MAP_SIZE / Constants.MAP_SIZE);
                boton.setBackground(Color.WHITE);
                boton.setToolTipText(Integer.toString(fila) + ", " + Integer.toString(columna));
                boton.addActionListener(this);
                mapButton[fila][columna] = boton;
                panelMapa.add(mapButton[fila][columna]);

            }
        }
        //JButton prueba = mapButton[0][0];
        //prueba.setBackground(Color.GREEN);
        
    }


    private void crearControles(){
        
        panelFlechas.setLayout(new GridLayout(10,1));
        botonArriba = new JButton("↑");
        botonArriba.addActionListener(this);
        botonAbajo = new JButton("↓");
        botonAbajo.addActionListener(this);
        botonDerecha = new JButton("→");
        botonDerecha.addActionListener(this);
        botonIzquierda = new JButton("←");
        botonIzquierda.addActionListener(this);

        panelFlechas.add(botonArriba);
        panelFlechas.add(botonAbajo);
        panelFlechas.add(botonDerecha);
        panelFlechas.add(botonIzquierda);
    }

    public void insertarJugador(int xLocation, int yLocation, Mapa pMap){
        JButton jugador = mapButton[xLocation][yLocation];
        jugador.setBackground(Color.BLUE);
        mapButton[xLocation][yLocation] = jugador;
        gameMap = pMap;


    }



    public void agregarComponentes(){
        mapButton = new JButton[Constants.MAP_SIZE][Constants.MAP_SIZE];
        panelMapa = new JPanel();
        panelFlechas = new JPanel();
        
        //FlechaArriba = new ImageIcon("Images/arrowUp.png");
        //JButton boton = new JButton(FlechaArriba);
        
        //botonArriba.setSize(5, 2);
        crearControles();
        ventanaMapa.add(panelFlechas, BorderLayout.EAST);
        crearMatrizBotones();
        ventanaMapa.add(panelMapa, BorderLayout.CENTER);
        
        //otroPanel = new JPanel();
        //otroPanel.add(boton);

        //ventanaMapa.add(otroPanel);

    }

    public void actualizarInterfaz(){
        panelMapa.validate();
        panelMapa.repaint();
    }
    

    private void vaciarCasilla(int xLocation, int yLocation){
        JButton boton = mapButton[xLocation][yLocation];
        boton.setBackground(Color.white);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        //System.out.println("ESTOY PRESIONANDDOOOOOOO");

        if(e.getSource().equals(botonAbajo)){
            //JOptionPane.showMessageDialog(ventanaMapa, "TIENE QUE BAJAR");
            // esto tengo que revisarlo
            vaciarCasilla(gameMap.getJugador().getxLocation(), gameMap.getJugador().getyLocation());
            gameMap.getJugador().moverAbajo(gameMap.getMap());
            insertarJugador(gameMap.getJugador().getxLocation(), gameMap.getJugador().getyLocation(), gameMap);
            actualizarInterfaz();

        }
        
        JButton botonPresionado = (JButton) e.getSource();
        Color colorBoton = botonPresionado.getBackground();
        // MICROORGANISMOS
        if(colorBoton == Color.GREEN){
            JOptionPane.showMessageDialog(ventanaMapa, "Aqui vamos a mostrar la info del micro");
        }
        
        
   
        
    }
    
}
