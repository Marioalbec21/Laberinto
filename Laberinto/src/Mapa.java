import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Mapa extends JPanel {

    private static final long serialVersionUID = 1L;
    private int[][] mapa;
    private int filas = 0;
    private int columnas = 0;
    
    //Ubicación inicial del jugador
    private int inicioFilaJugador;
    private int inicioColumnaJugador;
    
    //Ubicación del jugador
    private int filaJugador;
    private int columnaJugador;
    
    //Ubicacion de la salida
    private int filaSalida;
    private int columnaSalida;;
    
    private boolean mapaCompletado = false;
    
    public Mapa(int[][] mapa) {
        this.mapa = mapa;
        this.filas = mapa.length;
        this.columnas = mapa[0].length;

        setLayout(new GridLayout(filas, columnas));
        setVisible(true);
		setFocusable(true);
		
		encontrarJugador();
		encontrarSalida();
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
    
    public void encontrarJugador() {
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
    	inicioFilaJugador = filaJugador;
    	inicioColumnaJugador = columnaJugador;
    }
    
    public void encontrarSalida() {
    	//Busca la posición inicial del jugador
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if (mapa[i][j] == -2) {
                	filaSalida = i;
                	columnaSalida = j;
                    break;
                }
            }
        }
    }
    
    public void comprobarSalida() {
    	if (filaJugador == filaSalida && columnaJugador == columnaSalida && !mapaCompletado) {
    	    JOptionPane.showMessageDialog(this, "¡Felicidades, has ganado!");
    	    mapaCompletado = true;
    	    
    	    int opcion = JOptionPane.showConfirmDialog(null, "¿Deseas jugar otro nivel?", "Nuevo nivel", JOptionPane.YES_NO_OPTION);
	        
	        if (opcion == JOptionPane.YES_OPTION) {
	        	 Carga lab2;
				try {
					lab2 = new Carga("lab2.png");
					actualizarMapa(lab2.getMatriz());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        } else {
	            System.exit(0);
	        }
    	}
    }

    public void reiniciarJuego() {
        filaJugador = inicioFilaJugador;
        columnaJugador = inicioColumnaJugador;
        mapaCompletado = false;
        repaint();
        requestFocusInWindow();
    }
    
    public void actualizarMapa(int[][] mapa) {
        this.mapa = mapa;
        this.filas = mapa.length;
        this.columnas = mapa[0].length;
        this.filaJugador = this.columnaJugador = 1;
        this.mapaCompletado = false;
        
		encontrarJugador();
		encontrarSalida();
		
        this.repaint();
    }
    
	public int[][] getMapa() {
		return mapa;
	}
	
	public int getColumnas() {
		return columnas;
	}

	public void setColumnas(int columnas) {
		this.columnas = columnas;
	}

	public int getFilas() {
		return filas;
	}

	public void setFilas(int filas) {
		this.filas = filas;
	}

	public void setColumnaJugador(int columnaJugador) {
		this.columnaJugador = columnaJugador;
	}

	public void setFilaJugador(int filaJugador) {
		this.filaJugador = filaJugador;
	}

	public int getFilaJugador() {
		return filaJugador;
	}

	public int getColumnaJugador() {
		return columnaJugador;
	}

	public void setMapaCompletado(boolean mapaCompletado) {
		this.mapaCompletado = mapaCompletado;
	}

	public boolean isMapaCompletado() {
		return mapaCompletado;
	}
}
