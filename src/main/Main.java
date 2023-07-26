package main;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Main {

	BufferedImage image;
	BufferedImage output;
	
	final char[][][] patterns = {
			{
				{'x', '1', '1', '1'},
				{'1', '1', '2', '2'},
				{'1', '1', '1', '1'},
				{'x', '1', 'x', '1'},
			},
			{
				{'1', '1', '1', 'x'},
				{'2', '2', '1', '1'},
				{'1', '1', '1', '1'},
				{'1', 'x', '1', 'x'},
			},
			{
				{'1', '1', '1', 'x'},
				{'2', '2', '1', '1'},
				{'1', '1', '1', '1'},
				{'1', '1', '1', 'x'},
				{'1', 'x', '1', 'x'},
			},
			{
				{'x', '1', '1', '1'},
				{'1', '1', '2', '2'},
				{'1', '1', '1', '1'},
				{'x', '1', '1', '1'},
				{'x', '1', 'x', '1'},
			},
	};
	
	public static void main(String[] args) {
		new Main();
	}
	
	public Main() {
		try {
			image = ImageIO.read(Main.class.getResourceAsStream("image.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		output = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
		
		for (char[][] p : patterns) {
			for (int x = 0; x < image.getWidth() - p[0].length; x++) {
				y: for (int y = 0; y < image.getHeight() - p.length; y++) {
					int color1 = 0x7FB6FF;
					int color2 = 0x68A4FF;
					for (int py = 0; py < p.length; py++) {
						for (int px = 0; px < p[0].length; px++) {
							if (color1 == color2)
								continue y;
							switch (p[py][px]) {
							case 'x':
								continue;
							case '1':
								if (color1 == 0x7FB6FF) {
									color1 = image.getRGB(x + px, y + py);
								} else if (color1 != image.getRGB(x + px, y + py)) {
									continue y;
								}
								break;
							case '2':
								if (color2 == 0x68A4FF) {
									color2 = image.getRGB(x + px, y + py);
								} else if (color2 != image.getRGB(x + px, y + py)) {
									continue y;
								}
								break;
							}
						}
					}
					for (int py = 0; py < p.length; py++) {
						for (int px = 0; px < p[0].length; px++) {
							if (p[py][px] != 'x')
								output.setRGB(x + px, y + py, image.getRGB(x + px, y + py));
						}
					}
				}
			}
		}
		try {
			ImageIO.write(output, "png", new File("output.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
