package slv.Meckon.objects;

import slv.Meckon.graphics.Sprite;
import slv.Meckon.graphics.SpriteSheet;

public class ArenaItem {
	public Sprite sprite;
	public int x,y;
	public ArenaItem(int level,int x,int y){
		sprite = new Sprite(8,0,level,SpriteSheet.arenas);
		this.x = x;
		this.y = y;
	}
}
