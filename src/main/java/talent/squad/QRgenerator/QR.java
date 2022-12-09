package talent.squad.QRgenerator;

import java.awt.EventQueue;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.swing.JInternalFrame;
import javax.swing.JTextField;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JLabel;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;

public class QR extends JInternalFrame {
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QR frame = new QR();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws WriterException
	 * @throws IOException 
	 */
	public QR() throws WriterException, IOException {
		setBounds(100, 100, 450, 300);

		textField = new JTextField();
		getContentPane().add(textField, BorderLayout.NORTH);
		textField.setColumns(10);

		JButton btnNewButton = new JButton("Generar");
		getContentPane().add(btnNewButton, BorderLayout.SOUTH);

		JLabel lblNewLabel = new JLabel("QRlabel");
		getContentPane().add(lblNewLabel, BorderLayout.CENTER);

		int size = 1000;
		String fileType = "png";

		// ruta de imagen
		String filePath = "";
		String codigo = textField.getText().trim();
		JFileChooser chooser = new JFileChooser();
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
			filePath = chooser.getSelectedFile().getAbsolutePath();
		}

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
		ImageIcon icono = new ImageIcon(imagen.getScaledInstance(300, 300, 0));
		
		lblNewLabel.setIcon(icono);
		

	}

}
