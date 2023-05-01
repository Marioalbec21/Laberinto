import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;

public class Laberinto extends JFrame {
	
	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Laberinto frame = new Laberinto();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public Laberinto() throws IOException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 460);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(128, 255, 255));
		contentPane.add(panel_1, BorderLayout.WEST);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(128, 255, 255));
		contentPane.add(panel_2, BorderLayout.EAST);
		
		Reloj reloj = new Reloj();
		reloj.setBackground(new Color(128, 255, 255));
		contentPane.add(reloj, BorderLayout.NORTH);
		
		//Mapas
		Carga lab1 = new Carga("lab1.png");
		Carga lab2 = new Carga("lab2.png");

		reloj.iniciar();
		Mapa juego = new Mapa(lab1.getMatriz());
		contentPane.add(juego, BorderLayout.CENTER);
		
		//Metodos teclado
        juego.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyPressed(KeyEvent e) {
			    char tecla = e.getKeyChar();
			    if (!juego.isMapaCompletado()) {

				    //Mueve al jugador 1 casilla a la izquierda
				    if (tecla == 'a') {
				        if (juego.getColumnaJugador() > 0 && juego.getMapa()[juego.getFilaJugador()][juego.getColumnaJugador() - 1] != 1) {
				        	juego.setColumnaJugador(juego.getColumnaJugador()-1);
				            repaint();
				        }
				    }
				    //Mueve al jugador 1 casilla abajo
				    if (tecla == 's') {
				        if (juego.getFilaJugador() < juego.getFilas() - 1 && juego.getMapa()[juego.getFilaJugador() + 1][juego.getColumnaJugador()] != 1) {
				        	juego.setFilaJugador(juego.getFilaJugador()+1);
				            repaint();
				        }
				    }
				    //Mueve al jugador 1 casilla arriba
				    if (tecla == 'w') {
				    	if (juego.getFilaJugador() > 0 && juego.getMapa()[juego.getFilaJugador() - 1][juego.getColumnaJugador()] != 1) {
				        	juego.setFilaJugador(juego.getFilaJugador()-1);
				    		repaint();
				    	}
				    }
				    //Mueve al jugador 1 casilla a la derecha
				    if (tecla == 'd') {
				        if (juego.getColumnaJugador() < juego.getColumnas() - 1 && juego.getMapa()[juego.getFilaJugador()][juego.getColumnaJugador() + 1] != 1) {
				        	juego.setColumnaJugador(juego.getColumnaJugador()+1);
				            repaint();
				        }
				    }
			        if(juego.comprobarSalida()) {
			        	reloj.detener();
			    	    JOptionPane.showMessageDialog(null, "¡Felicidades, has ganado!");

			        	if(juego.eleccionSalida()) {	
			        		reloj.reiniciar();
			        		juego.actualizarMapa(lab2.getMatriz());
			        	}
			        }
			    }
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		requestFocus(true);
		JPanel opciones = new JPanel();
		opciones.setBackground(new Color(128, 255, 128));
		contentPane.add(opciones, BorderLayout.SOUTH);
		
		JButton btnNewButton = new JButton("Reiniciar");
		btnNewButton.setFont(new Font("Brock", Font.PLAIN, 16));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		        juego.reiniciarJuego();
		        reloj.reiniciar();
			}
		});
		opciones.add(btnNewButton);
	}
}