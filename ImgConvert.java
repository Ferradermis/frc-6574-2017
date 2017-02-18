package org.usfirst.frc.team6574.robot;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;

import org.opencv.core.CvType;
import org.opencv.core.Mat;

public class ImgConvert {

	public static Mat img2Mat(BufferedImage image) {
		byte[] data = ((DataBufferByte)image.getRaster().getDataBuffer()).getData();
		Mat mat = new Mat(image.getHeight(), image.getWidth(), CvType.CV_8UC3);
		mat.put(0, 0, data);
		return mat;
	}
	
	public static BufferedImage mat2Image(Mat mat) {
		BufferedImage image = new BufferedImage(mat.width(), mat.height(), BufferedImage.TYPE_3BYTE_BGR);
		WritableRaster raster = image.getRaster();
		DataBufferByte dataBuffer = (DataBufferByte)raster.getDataBuffer();
		byte[] data = dataBuffer.getData();
		mat.get(0, 0, data);
		return image;
	}
	
	public static BufferedImage MatToBufferedImage(Mat matrix) {   
        int cols = matrix.cols();  
        int rows = matrix.rows();  
        int elemSize = (int)matrix.elemSize();  
        byte[] data = new byte[cols * rows * elemSize];  
        int type;  
        matrix.get(0, 0, data);  
        switch (matrix.channels()) {  
          case 1:  
            type = BufferedImage.TYPE_BYTE_GRAY;  
            break;  
          case 3:   
            type = BufferedImage.TYPE_3BYTE_BGR;  
            // bgr to rgb  
            byte b;  
            for(int i = 0; i < data.length; i = i + 3) {  
              b = data[i];  
              data[i] = data[i + 2];  
              data[i + 2] = b;  
            }
            break;
           default:
        	   type = 0;
        }  
        BufferedImage image = new BufferedImage(cols, rows, type);  
        image.getRaster().setDataElements(0, 0, cols, rows, data);  
        return image;
	}
	
	public static Mat BufferedImageToMat(BufferedImage image) {
		byte[] pixels = ((DataBufferByte)image.getRaster().getDataBuffer()).getData();
		Mat mat = new Mat();
		mat.put(0, 0, pixels);
		return mat;
	}
	
}
