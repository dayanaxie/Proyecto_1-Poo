package GUI;
import ConstantsAndEnums.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.*;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Alimento.Alimento;
import Mapa.Mapa;
import Microorganismos.Jugador;
import Microorganismos.Microorganismo;

/*
 * Los microorganismos van a ser de color verde
 * Los alimentos van a ser de diferentes tonalidades de gris (light gray, gray y dark gray)
 * El jugador va a ser de color azul
 */

public class Gui implements ActionListener{
    Mapa gameMap;
    JLabel labelTurno;
    JFrame ventanaMapa;
    JPanel panelTurno;
    JPanel panelMapa;
    JPanel panelFlechas;
    JPanel panelColores;
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
                boton.setToolTipText(Integer.toString(fila) + "," + Integer.toString(columna));
                boton.addActionListener(this);
                mapButton[fila][columna] = boton;
                panelMapa.add(mapButton[fila][columna]);

            }
        }
        
    }

    public void mostrarTurno(int turno){
        panelTurno.setLayout(new GridLayout(1,2));
        labelTurno = new JLabel(" Turno: " + Integer.toString(turno));
        panelTurno.add(labelTurno);
        ventanaMapa.add(panelTurno, BorderLayout.AFTER_LAST_LINE);
    }

    public void actualizarTurno(int turno){
        // para obtener el label del panel
        JLabel labelTurno = (JLabel)panelTurno.getComponent(0);
        labelTurno.setText("Turno: " + Integer.toString(turno));
        panelTurno.add(labelTurno);
        ventanaMapa.add(panelTurno, BorderLayout.AFTER_LAST_LINE);



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

    private void representarColores(){
        panelColores.setLayout(new GridLayout(1,6));
        JLabel colorMicro = new JLabel();
        JLabel textoMicro = new JLabel(" Microorganismo");
        colorMicro.setOpaque(true);
        colorMicro.setBackground(Color.GREEN);

        JLabel colorAlimentoP = new JLabel();
        JLabel textoAlimentoP = new JLabel(" AlimEnergia Pequeño");
        colorAlimentoP.setOpaque(true);
        colorAlimentoP.setBackground(Color.LIGHT_GRAY);

        JLabel colorAlimentoM = new JLabel();
        JLabel textoAlimentoM = new JLabel(" Alimentos");
        colorAlimentoM.setOpaque(true);
        colorAlimentoM.setBackground(Color.gray);

        JLabel colorAlimentoG = new JLabel();
        JLabel textoAlimentoG = new JLabel(" AlimEnergia Grande");
        colorAlimentoG.setOpaque(true);
        colorAlimentoG.setBackground(Color.DARK_GRAY);

        JLabel colorJugador = new JLabel();
        JLabel textoJugador = new JLabel(" Jugador");
        colorJugador.setOpaque(true);
        colorJugador.setBackground(Color.blue);

        JLabel colorVacio = new JLabel();
        JLabel textoVacio = new JLabel(" Casilla vacia");
        colorVacio.setOpaque(true);
        colorVacio.setBackground(Color.white);

        panelColores.add(colorMicro);
        panelColores.add(textoMicro);
        panelColores.add(colorAlimentoP);
        panelColores.add(textoAlimentoP);
        panelColores.add(colorAlimentoM);
        panelColores.add(textoAlimentoM);
        panelColores.add(colorAlimentoG);
        panelColores.add(textoAlimentoG);
        panelColores.add(colorJugador);
        panelColores.add(textoJugador);
        panelColores.add(colorVacio);
        panelColores.add(textoVacio);


    }

    public void insertarAlimentos(int xLocation, int yLocation, EnumAlimentos type, int size, Mapa pMap){
        JButton alimento = mapButton[xLocation][yLocation];
        if(type == EnumAlimentos.ENERGIA){
           // si es energia su tamanio viene en diferentes tamanios = colores
            switch(size){
                case 1: 
                    alimento.setBackground(Color.LIGHT_GRAY);
                
                case 2:
                    alimento.setBackground(Color.darkGray);

            }      
        }else{
            alimento.setBackground(Color.gray);
        }
           
        mapButton[xLocation][yLocation] = alimento;
        gameMap = pMap;

    }

    public void moverMicroorganismo(Mapa pMap, Microorganismo pMicroorganismo){
        pMap.insertarMicroorganismos(this, pMicroorganismo);

    }



    public void insertarMicroorganismos(int xLocation, int yLocation, Mapa pMap){
        JButton micro = mapButton[xLocation][yLocation];
        micro.setBackground(Color.GREEN);
        mapButton[xLocation][yLocation] = micro;
        gameMap = pMap;
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
        panelTurno = new JPanel();
        panelFlechas = new JPanel();
        panelColores = new JPanel();
        representarColores();
        ventanaMapa.add(panelColores, BorderLayout.NORTH);
        
        
        crearControles();
        ventanaMapa.add(panelFlechas, BorderLayout.EAST);
        crearMatrizBotones();
        ventanaMapa.add(panelMapa, BorderLayout.CENTER);
    }

    public void actualizarInterfaz(){
        panelMapa.validate();
        panelMapa.repaint();
    }
    

    public void vaciarCasilla(int xLocation, int yLocation){
        JButton boton = mapButton[xLocation][yLocation];
        boton.setBackground(Color.white);
    }

    private void desactivarBotones(JButton pBoton){
        for(Component component : panelFlechas.getComponents()){
            if(component != pBoton){
                component.setEnabled(false);
            }  
        }    
    }

    public void activarBotones(){
        for(Component component : panelFlechas.getComponents()){
            component.setEnabled(true);
            
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(botonAbajo)){
            gameMap.vaciarCasilla(this, gameMap.getJugador().getxLocation(), gameMap.getJugador().getyLocation());
            gameMap.getJugador().moverAbajo();
            insertarJugador(gameMap.getJugador().getxLocation(), gameMap.getJugador().getyLocation(), gameMap);
            desactivarBotones(botonAbajo);

            
        
        }
        if(e.getSource().equals(botonArriba)){
            gameMap.vaciarCasilla(this, gameMap.getJugador().getxLocation(), gameMap.getJugador().getyLocation());
            gameMap.getJugador().moverArriba();
            insertarJugador(gameMap.getJugador().getxLocation(), gameMap.getJugador().getyLocation(), gameMap);
            desactivarBotones(botonArriba);
            
        }
        if(e.getSource().equals(botonDerecha)){
            gameMap.vaciarCasilla(this, gameMap.getJugador().getxLocation(), gameMap.getJugador().getyLocation());
            gameMap.getJugador().moverDerecha();
            insertarJugador(gameMap.getJugador().getxLocation(), gameMap.getJugador().getyLocation(), gameMap);
            desactivarBotones(botonDerecha);
            
        }
        if(e.getSource().equals(botonIzquierda)){
            gameMap.vaciarCasilla(this, gameMap.getJugador().getxLocation(), gameMap.getJugador().getyLocation());
            gameMap.getJugador().moverIzquierda();
            insertarJugador(gameMap.getJugador().getxLocation(), gameMap.getJugador().getyLocation(), gameMap);
            desactivarBotones(botonIzquierda);
            
        }
        
        JButton botonPresionado = (JButton) e.getSource();
        botonPresionado.getToolTipText();
        Color colorBoton = botonPresionado.getBackground();
        // MICROORGANISMOS
        if(colorBoton == Color.GREEN){
            String tip = botonPresionado.getToolTipText();
            String[] texto = tip.split(",");
            int x = Integer.parseInt(texto[0]);
            int y = Integer.parseInt(texto[1]);
            Object map [][] = gameMap.getMap();
            Microorganismo micro = (Microorganismo)map[x][y];
            String infoMicro = micro.obtenerInfo();
            JOptionPane.showMessageDialog(ventanaMapa, infoMicro);
        }
        if(colorBoton == Color.BLUE){
            String infoPlayer = gameMap.getJugador().obtenerInfo();
            JOptionPane.showMessageDialog(ventanaMapa, infoPlayer);
        }
            

           
    }
    
}
