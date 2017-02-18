package org.usfirst.frc.team6574.robot;

import java.awt.image.BufferedImage;

import org.opencv.core.Mat;

public class FakeImage {

	Mat mat;
	
	public FakeImage(Mat mat) {
		this.mat = mat;
	}
	
	public int getWidth() {
		return mat.width();
	}
	
	public int getHeight() {
		return mat.height();
	}
	
	public int getRGB(int row, int col) {
		int[] data = null;
		mat.get(row, col, data);
		return data[0] << 16 & data[1] << 8 & data[2];
	}
	
	public void setRGB(int row, int col, int color) {
		mat.put(row, color, new int[]{color & 0xFF0000, color & 0x00FF00, color & 0x0000FF});
	}
	
	public Mat toMat() {
		return mat;
	}
	
	public BufferedImage toBufferedImage() {
		BufferedImage image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
		for (int i = 0; i < image.getHeight(); i++) {
			for (int j = 0; j < image.getWidth(); j++) {
				image.setRGB(j, i, getRGB(i, j));
			}
		}
		return image;
	}
	
	public Mat fromBufferedImage(BufferedImage image) {
		FakeImage mat = new FakeImage(new Mat());
		for (int i = 0; i < image.getHeight(); i++) {
			for (int j = 0; j < image.getWidth(); j++) {
				mat.setRGB(i, j, image.getRGB(j, i));
			}
		}
		return mat.toMat();
	}
	
}
