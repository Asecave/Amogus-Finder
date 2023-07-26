package main;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Main {

	BufferedImage image;
	
	public static void main(String[] args) {
		new Main();
	}
	
	public Main() {
		try {
			image = ImageIO.read(Main.class.getResourceAsStream("image.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
