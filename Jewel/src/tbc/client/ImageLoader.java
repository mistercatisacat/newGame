package tbc.client;

import java.io.File;
import java.util.HashMap;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class ImageLoader {

	Image image = null;
	private HashMap<String, Image> images = new HashMap<String, Image>();

	public ImageLoader(String imagesDirectory) {
		File dir = new File(imagesDirectory);
		String[] imgs = dir.list();
		System.out.println("does " + imagesDirectory + " exist? "+dir.exists());
		for (int i = 0; i < imgs.length; i++) {
			System.out.println("loading: " + imgs[i]);
			try {
				image = new Image(imagesDirectory + imgs[i]);
			} catch (SlickException e) {
				e.printStackTrace();
			}
			if (imgs[i].toLowerCase().endsWith("gif")) {
				imgs[i] = imgs[i].substring(0, imgs[i].indexOf('.'));
					images.put(imgs[i], image);
			}
		}
	}
	
	public Image getImage(String name){
		return images.get(name);
	}
}
