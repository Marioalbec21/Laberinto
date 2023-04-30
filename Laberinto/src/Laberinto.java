import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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
	
	public Laberinto() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 400);
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
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(128, 255, 255));
		contentPane.add(panel_3, BorderLayout.NORTH);
		
		//Mapas
		int [][] mapa1 = 
			{ {1,-1,1,1,1,1,1,1,1,1,1,1,1},
			  {1,0,1,0,1,0,1,0,0,0,0,0,1},
			  {1,0,1,0,0,0,1,0,1,1,1,0,1},
			  {1,0,0,0,1,1,1,0,0,0,0,0,1},
			  {1,0,1,0,0,0,0,0,1,1,1,0,1},
			  {1,0,1,0,1,1,1,0,1,0,0,0,1},
			  {1,0,1,0,1,0,0,0,1,1,1,0,1},
			  {1,0,1,0,1,1,1,0,1,0,1,0,1},
			  {1,0,0,0,0,0,0,0,0,0,1,0,1},
			  {1,1,1,1,1,1,1,1,1,1,1,-2,1}
			};
		
		Mapa juego = new Mapa(mapa1);
		contentPane.add(juego, BorderLayout.CENTER);
		
		JPanel opciones = new JPanel();
		opciones.setBackground(new Color(128, 255, 128));
		contentPane.add(opciones, BorderLayout.SOUTH);
		
		JButton btnNewButton = new JButton("Reiniciar");
		btnNewButton.setFont(new Font("Brock", Font.PLAIN, 16));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		        juego.reiniciarJuego();
			}
		});
		opciones.add(btnNewButton);
	}
}