package slv.Meckon.objects;

import java.awt.Rectangle;

import slv.Meckon.graphics.Sprite;
import slv.Meckon.graphics.SpriteSheet;

public class ArenaItem {
	public Sprite sprite;
	public int x,y;
	public Rectangle rect;
	public int size; 
	public ArenaItem(int level,int x,int y,int  size){
		sprite = new Sprite(size,0,level,SpriteSheet.arenas);
		this.size = size;
		this.x = x;
		this.y = y;
		this.rect = new Rectangle(x,y,size,size);
	}
}
