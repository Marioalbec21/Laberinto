import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import javax.swing.JPanel;

public class Mapa extends JPanel {

    private static final long serialVersionUID = 1L;
    private int[][] mapa;
    private int filas = 0;
    private int columnas = 0;
    
    public Mapa(int[][] mapa) {
        this.mapa = mapa;
        this.filas = mapa.length;
        this.columnas = mapa[0].length;

        setLayout(new GridLayout(filas, columnas));
        setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int anchoCelda = getWidth() / columnas;
        int altoCelda = getHeight() / filas;

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if (mapa[i][j] == 1) {
                    g.setColor(Color.BLACK);
                } else {
                    g.setColor(Color.WHITE);
                }
                g.fillRect(j * anchoCelda, i * altoCelda, anchoCelda, altoCelda);
            }
        }
    }
}
