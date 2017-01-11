package com.brantmeierz.visiontracking;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.IOException;

import com.github.sarxos.webcam.Webcam;

public class Main {

	public static void main(String[] args) throws IOException {
		System.out.println(Webcam.getWebcams().toString());
		Webcam webcam = Webcam.getDefault();
		webcam.open();
		BufferedImage image;
		ImageWindow window;
		window = new ImageWindow("Result", webcam.getImage());
		for (;;) {
			image = webcam.getImage();
			
			//String fileName = "blurry2.jpg";
			//BufferedImage image = ImageIO.read(new File("input/" + fileName));
			//BufferedImage gauss = ImageOperation.gaussian(image, 1);
			//System.out.println("2");
//			//new ImageWindow("Unmodified", image);
//			BufferedImage gray = ImageOperation.grayscale(image);
//				//BufferedImage gray = ImageOperation.grayscale(image);
//			//new ImageWindow("Grayscale", gray);
//			//new ImageWindow("Edge detect", ImageOperation.edgeDetect(image));
//				BufferedImage exponent = ImageOperation.exponentiate(gray, 4);
//			//new ImageWindow("Exponent", exponent);
////				Dimension center = ImageOperation.findCenterCoords(exponent);
//				for (int i = -3; i <= 3; i++)
//					for (int j = -3; j <= 3; j++)
//						if (center.getWidth() + i < exponent.getWidth() && center.getHeight() + j < exponent.getHeight() && center.getWidth() + i >= 0 && center.getHeight() + j >= 0)
//							image.setRGB((int)center.getWidth() + i, (int)center.getHeight() + j, Color.BLUE.getRGB());
//			//new ImageWindow("Center", ImageOperation.findCenter(exponent));
			window.updateImage(ImageOperation.edgeDetect(image));
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}
