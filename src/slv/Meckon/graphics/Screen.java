package slv.Meckon.graphics;

import java.util.Random;

import slv.Meckon.objects.Player;
//import java.io

public class Screen {
	public int width,height;
	private Random rand = new Random();
	public final int MAP_SIZE = 64;
	public final int MAP_SIZE_MASK = MAP_SIZE-1;
	public int[] pixels;
	public int[] tiles = new int[MAP_SIZE*MAP_SIZE];
	
	public Screen(int width,int height){
		this.width = width;
		this.height = height;
		pixels = new int[width*height];
		tiles = new int[width*height];
		
		update();
	}
	public void clear(){
		for(int i = 0;i<pixels.length;i++)
			pixels[i] = 0;
	}
	
	public void update(){
		for(int i = 0;i<tiles.length;i++)
			tiles[i] = rand.nextInt(0xFFFFFF);
	}
	
	public void render(int xOffset,int yOffset){
		clear();
		for(int y = 0;y<height;y++){
			//int yp = y+yOffset;
			//if(yp<0||yp>=height)continue;
			for(int x = 0;x<width;x++){
				//int xp  = x + xOffset;
				//if(xp<0||xp>=width)continue;
				pixels[x +y*width] = Sprite.surface.pixels[(x&7)+(y&7)*Sprite.surface.SIZE];
			}
		}
	}
	
	public void renderPlayer(Player player)
	{
		for(int y = 0;y<8;y++){
			for(int x = 0;x<8;x++){
				pixels[(int)(x+player.x + (y+player.y)*width)] = player.sprite.pixels[(x)+(y)*player.sprite.SIZE];
			}
		}
	}
}
