package GUI;
import Constants.Constants;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.*;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import Mapa.Mapa;
import Microorganismos.Jugador;

/*
 * Los microorganismos van a ser de color verde
 * Los alimentos van a ser de diferentes tonalidades de gris
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
                boton.setToolTipText(Integer.toString(fila) + ", " + Integer.toString(columna));
                boton.addActionListener(this);
                mapButton[fila][columna] = boton;
                panelMapa.add(mapButton[fila][columna]);

            }
        }
        //JButton prueba = mapButton[0][0];
        //prueba.setBackground(Color.GREEN);
        
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

        JLabel colorAlimento = new JLabel();
        JLabel textoAlimento = new JLabel(" Alimentos");
        colorAlimento.setOpaque(true);
        colorAlimento.setBackground(Color.gray);

        JLabel colorJugador = new JLabel();
        JLabel textoJugador = new JLabel(" Jugador");
        colorJugador.setOpaque(true);
        colorJugador.setBackground(Color.blue);
        //colorJugador.setMaximumSize(new Dimension(1,1));

        JLabel colorVacio = new JLabel();
        JLabel textoVacio = new JLabel(" Casilla vacia");
        colorVacio.setOpaque(true);
        colorVacio.setBackground(Color.white);

        panelColores.add(colorMicro);
        panelColores.add(textoMicro);
        panelColores.add(colorAlimento);
        panelColores.add(textoAlimento);
        panelColores.add(colorJugador);
        panelColores.add(textoJugador);
        panelColores.add(colorVacio);
        panelColores.add(textoVacio);


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

    private void desactivarBotones(JButton pBoton){
        for(Component component : panelFlechas.getComponents()){
            if(component != pBoton){
                component.setEnabled(false);
            }  
        }    
    }

    private void activarBotones(){
        for(Component component : panelFlechas.getComponents()){
            component.setEnabled(true);
            
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // mientras que el jugador se pueda mover
        while(gameMap.getJugador().posibilidadMoverse()){
            if(e.getSource().equals(botonAbajo)){
                //System.out.println("Entra aqui");
                vaciarCasilla(gameMap.getJugador().getxLocation(), gameMap.getJugador().getyLocation());
                gameMap.getJugador().moverAbajo();
                insertarJugador(gameMap.getJugador().getxLocation(), gameMap.getJugador().getyLocation(), gameMap);
                //desactivarBotones(botonAbajo);
                actualizarInterfaz();   // el actualizar interfaz puede quitarse y ponerse solo una vez al final del turno
            
            }
            if(e.getSource().equals(botonArriba)){
                vaciarCasilla(gameMap.getJugador().getxLocation(), gameMap.getJugador().getyLocation());
                gameMap.getJugador().moverArriba();
                insertarJugador(gameMap.getJugador().getxLocation(), gameMap.getJugador().getyLocation(), gameMap);
                actualizarInterfaz();   // el actualizar interfaz puede quitarse y ponerse solo una vez al final del turno
            
            }
            if(e.getSource().equals(botonDerecha)){
                vaciarCasilla(gameMap.getJugador().getxLocation(), gameMap.getJugador().getyLocation());
                gameMap.getJugador().moverDerecha();
                insertarJugador(gameMap.getJugador().getxLocation(), gameMap.getJugador().getyLocation(), gameMap);
                actualizarInterfaz();   // el actualizar interfaz puede quitarse y ponerse solo una vez al final del turno
            
            }
            if(e.getSource().equals(botonIzquierda)){
                vaciarCasilla(gameMap.getJugador().getxLocation(), gameMap.getJugador().getyLocation());
                gameMap.getJugador().moverIzquierda();
                insertarJugador(gameMap.getJugador().getxLocation(), gameMap.getJugador().getyLocation(), gameMap);
                actualizarInterfaz();   // el actualizar interfaz puede quitarse y ponerse solo una vez al final del turno
            
            }
            
            JButton botonPresionado = (JButton) e.getSource();
            Color colorBoton = botonPresionado.getBackground();
            // MICROORGANISMOS
            if(colorBoton == Color.GREEN){
                JOptionPane.showMessageDialog(ventanaMapa, "Aqui vamos a mostrar la info del micro");
            }
            if(colorBoton == Color.BLUE){
                String infoPlayer = gameMap.getJugador().obtenerInfo();
                JOptionPane.showMessageDialog(ventanaMapa, infoPlayer);
            }
            

        }     
    }
    
}
