package com.brantmeierz.visiontracking;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.image.BufferedImage;

public class ImageOperation {

	public static BufferedImage grayscale(BufferedImage image) {
		BufferedImage newImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
		for (int i = 0; i < image.getWidth(); i++) {
			for (int j = 0; j < image.getHeight(); j++) {
				Color color = new Color(image.getRGB(i, j));
				int value = (int)(color.getRed() * 0.3) + (int)(color.getGreen() * 0.3) + (int)(color.getBlue() * 0.3);
				Color newColor = new Color(value, value, value);
				newImage.setRGB(i, j, newColor.getRGB());
			}
		}
		return newImage;
	}
	
	public static BufferedImage gaussian(BufferedImage image, int level) {
		BufferedImage newImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
		for (int i = 0; i < image.getWidth(); i++) {
			for (int j = 0; j < image.getHeight(); j++) {
				Color newColor;
				if (i < level || j < level || i > image.getWidth() -  (level + 1) || j > image.getHeight() - (level + 1)) {
					newColor = Color.black;
				} else {
					int newRed = 0;
					int newGreen = 0;
					int newBlue = 0;
					
					for (int i2 = -level; i2 <= level; i2++) {
						for (int j2 = -level; j2 <= level; j2++) {
							Color color = new Color(image.getRGB(i + i2, j + j2));
							newRed += color.getRed();
							newGreen += color.getGreen();
							newBlue += color.getBlue();
						}
					}
					newRed /= Math.pow(level * 2 + 1, 2);
					newGreen /= Math.pow(level * 2 + 1, 2);
					newBlue /= Math.pow(level * 2 + 1, 2);
					
					//System.out.println(newRed + "," + newGreen + "," + newBlue);
					newColor = new Color(newRed, newGreen, newBlue);
				}
				newImage.setRGB(i, j, newColor.getRGB());
			}
		}
		return newImage;
	}
	
	public static BufferedImage exponentiate(BufferedImage image, int power) {
		BufferedImage newImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
		for (int i = 0; i < image.getWidth(); i++) {
			for (int j = 0; j < image.getHeight(); j++) {
				Color color = new Color(image.getRGB(i, j));
				Color newColor = new Color((int)(Math.pow(color.getRed() / 255.0, power) * 255), (int)(Math.pow(color.getGreen() / 255.0, power) * 255), (int)(Math.pow(color.getBlue() / 255.0, power) * 255));
				newImage.setRGB(i, j, newColor.getRGB());
			}
		}
		return newImage;
	}
	
	public static BufferedImage edgeDetect(BufferedImage image) {
		//BufferedImage newImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
		for (int i = 0; i < image.getWidth() - 1; i++) {
			for (int j = 0; j < image.getHeight(); j++) {
				Color color1 = new Color(image.getRGB(i, j));
				Color color2 = new Color(image.getRGB(i + 1, j));
				if (Math.abs(color2.getRed() - color1.getRed()) > 45 || Math.abs(color2.getGreen() - color1.getGreen()) > 45 || Math.abs(color2.getBlue() - color1.getBlue()) > 45) {
					image.setRGB(i, j, Color.RED.getRGB());
				}
			}
		}
		return image;
	}
	
	public static Dimension findCenterCoords(BufferedImage image) {
		int avgx = 0;
		int avgy = 0;
		int total = 0;
		for (int i = 0; i < image.getWidth() - 1; i++) {
			for (int j = 0; j < image.getHeight() - 1; j++) {
				Color color = new Color(image.getRGB(i, j));
				if (color.getRed() > 30) {
					total++;
					avgx += i;
					avgy += j;
				}
			}
		}
		if (total != 0) {
			avgx /= total;
			avgy /= total;
		}
		return new Dimension(avgx, avgy);
	}
	
	public static BufferedImage findCenter(BufferedImage image) {
		BufferedImage newImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
		Dimension d = findCenterCoords(image);
		for (int i = -2; i <= 2; i++) {
			for (int j = -2; j <= 2; j++) {
				newImage.setRGB((int)d.getWidth() + i, (int)d.getHeight() + j, Color.RED.getRGB());
			}
		}
		return newImage;
	}
	
	/*public static Dimension findBottomLeftCoords(BufferedImage image) {
		int avgx = 0;
		int avgy = 0;
		int total = 0;
		for (int i = 0; i < image.getWidth() - image.getWidth() / 12; i++) {
			for (int j = image.getHeight() / 12; j < image.getHeight(); j++) {
				Color color = new Color(image.getRGB(i, j));
				if (color.getRed() > 30) {
					total++;
					avgx += i;
					avgy += j;
				}
			}
		}
		if (total != 0) {
			avgx /= total;
			avgy /= total;
		}
		return new Dimension(avgx, avgy);
	}*/
	
	public static BufferedImage lighten(BufferedImage image) {
		for (int i = 0; i < image.getWidth(); i++) {
			for (int j = 0; j < image.getHeight(); j++) {
				Color color = new Color(image.getRGB(i, j));
				image.setRGB(i, j, new Color((color.getRed() + 75) & 0xFF, (color.getGreen() + 75) & 0xFF, (color.getBlue() + 75) & 0xFF).getRGB());
			}
		}
		return image;
	}
	
	public static BufferedImage derp(BufferedImage image) {
		for (int i = 0; i < image.getWidth(); i++) {
			for (int j = 0; j < image.getHeight(); j++) {
				Color color = new Color(image.getRGB(i, j));
				image.setRGB(i, j, new Color(color.getGreen(), color.getBlue(), color.getRed()).getRGB());
			}
		}
		return image;
	}
}
