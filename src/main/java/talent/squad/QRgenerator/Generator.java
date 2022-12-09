package talent.squad.QRgenerator;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

public class Generator {
	
	public ImageIcon generarQr(String codigo) {
		int size = 1000;
		String fileType = "png";

		// ruta de imagen
		ImageIcon icono = null;
		try {
			String filePath = "";
			JFileChooser chooser = new JFileChooser();
			chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

			filePath = chooser.getSelectedFile().getAbsolutePath();
			

			// Generar nombre
			UUID uuid = UUID.randomUUID();
			String randomName = uuid.toString();

			// Generar QR
			QRCodeWriter qrCode = new QRCodeWriter();
			BitMatrix matrix = qrCode.encode(codigo, BarcodeFormat.QR_CODE, size, size);
			File f = new File(filePath + "/" + randomName + "." + fileType);

			int matrixWidth = matrix.getWidth();
			BufferedImage image = new BufferedImage(matrixWidth, matrixWidth, BufferedImage.TYPE_INT_RGB);
			image.createGraphics();
			Graphics2D gd = (Graphics2D) image.getGraphics();
			gd.setColor(Color.WHITE); // Fondo
			gd.fillRect(0, 0, size, size);
			gd.setColor(Color.black); // Qr

			// coger los valores de la matriz y colocarlos en el gráfico:

			for (int b = 0; b < matrixWidth; b++) {
				for (int j = 0; j < matrixWidth; j++) {
					if (matrix.get(b, j)) {
						gd.fillRect(b, j, 1, 1);
					}
				}
			}

			// Mostrar imagen
			ImageIO.write(image, fileType, f);
			Image imagen = new ImageIcon(filePath + "/" + randomName + "." + fileType).getImage();
			icono = new ImageIcon(imagen.getScaledInstance(300, 300, 0));
		} catch (WriterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return icono;
	}
	
	
	
	

}
