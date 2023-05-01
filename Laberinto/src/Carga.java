import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Carga{
	
	private int[][] matriz;
	
	public Carga(String ruta) throws IOException {		

		BufferedImage imagen = ImageIO.read(new File(ruta));
		
		int ancho = imagen.getWidth();
		int alto = imagen.getHeight();
		
		matriz = new int[alto][ancho];
		
		for (int i = 0; i < alto; i++) {
			for (int j = 0; j < ancho; j++) {
				Color colorPixel = new Color(imagen.getRGB(j, i));
				if (colorPixel.equals(Color.WHITE)) {
					matriz[i][j] = 0;
				} else {
					matriz[i][j] = 1;
				}
				if (colorPixel.equals(Color.RED)) {
					matriz[i][j] = -2;
				}
				if (colorPixel.equals(Color.BLUE)) {
					matriz[i][j] = -1;
				}
			}
		}
	}

	public int[][] getMatriz() {
		return matriz;
	} 
}
