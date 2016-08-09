package tbc.client;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class ImageLoader {

	private HashMap<String, BufferedImage> images = new HashMap<String, BufferedImage>();

	public ImageLoader(String imagesDirectory) {
		File dir = new File(imagesDirectory);
		File[] imgs = dir.listFiles();
		for (int i = 0; i < imgs.length; i++) {
			String name = imgs[i].getName();
			if (name.toLowerCase().endsWith("gif")) {
				name = name.substring(0, name.indexOf('.'));
				try {
					BufferedImage image = ImageIO.read(imgs[i]);
					images.put(name, image);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public BufferedImage getImage(String name){
		return images.get(name);
	}
}
