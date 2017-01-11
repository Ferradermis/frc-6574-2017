package com.brantmeierz.visiontracking;

import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class ImageWindow extends JFrame {
	private static final long serialVersionUID = 1L;
	
	JLabel label;
	
	public ImageWindow(String title, BufferedImage image) {
		super(title);
		setSize(image.getWidth() * 5 + 50, image.getHeight() * 5 + 50);
		label = new JLabel(new ImageIcon(image.getScaledInstance(image.getWidth() * 5, image.getHeight() * 5, BufferedImage.SCALE_SMOOTH)));
		label.setSize(image.getWidth() * 3, image.getHeight() * 3);
		getContentPane().add(label);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//pack();
		setVisible(true);
	}
	
	public void updateImage(BufferedImage image) {
		label.setIcon(new ImageIcon(image.getScaledInstance(image.getWidth() * 5, image.getHeight() * 5, BufferedImage.SCALE_SMOOTH)));
		repaint();
	}
	
}
