import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

public class Mapa extends JPanel {

    private static final long serialVersionUID = 1L;
    private int[][] mapa;
    private int filas = 0;
    private int columnas = 0;
    
    //Ubicación del jugador
    private int filaJugador;
    private int columnaJugador;
    
    public Mapa(int[][] mapa) {
        this.mapa = mapa;
        this.filas = mapa.length;
        this.columnas = mapa[0].length;

        setLayout(new GridLayout(filas, columnas));
        setVisible(true);
		setFocusable(true);
		
		//Busca la posición inicial del jugador
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if (mapa[i][j] == -1) {
                	filaJugador = i;
                	columnaJugador = j;
                    break;
                }
            }
        }

        //Metodos teclado
        addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyPressed(KeyEvent e) {
			    char tecla = e.getKeyChar();
			    if (tecla == 's') {
			        
			    	//Mueve al jugador 1 casilla abajo
			        if (filaJugador < filas - 1 && mapa[filaJugador + 1][columnaJugador] != 1) {
			        	filaJugador++;
			            repaint();
			        }
			    }
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		requestFocus(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int anchoCelda = getWidth() / columnas;
        int altoCelda = getHeight() / filas;

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
            	
            	//Dibuja el laberinto
                if (mapa[i][j] == 1) {
                    g.setColor(Color.BLACK);
                } 
                if (mapa[i][j] == 0 || mapa[i][j] == -1 ||  mapa[i][j] == -2) {
                    g.setColor(Color.WHITE);
                }
                g.fillRect(j * anchoCelda, i * altoCelda, anchoCelda, altoCelda);
                
                //Pinta al jugador en la nueva posición
                g.setColor(Color.BLUE);
                g.fillRect(columnaJugador * anchoCelda, filaJugador * altoCelda, anchoCelda, altoCelda);
            }
        }
    }
}
