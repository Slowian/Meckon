package slv.Meckon.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.*;

public class SpriteSheet {

	private String path;
	public final int SIZE;
	public int[] pixels;
	
	public static SpriteSheet players = new SpriteSheet("/players/spritesheet.png",64);
	public static SpriteSheet tiles = new SpriteSheet("/textures/spritesheet.png",64);
	public static SpriteSheet arenas = new SpriteSheet("/arenas/spritesheet.png",64);
	public SpriteSheet(String path,int size){
		this.path = path;
		this.SIZE = size;
		pixels = new int[SIZE*SIZE];
		load();
	}
	
	private void load(){
		try{
			BufferedImage image = ImageIO.read(SpriteSheet.class.getResource(path));
			int w = image.getWidth();
			int h = image.getHeight();
			image.getRGB(0,0,w,h,pixels,0,w);
		}catch(IOException ex){
			ex.printStackTrace();
		}
		
	}
}
