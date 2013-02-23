package slv.Meckon.objects;

import slv.Meckon.graphics.Screen;
import slv.Meckon.graphics.Sprite;
import slv.Meckon.input.Keyboard;

public class Player {
	
	public int x,y;
	private int speed = 1;
	public Sprite sprite = Sprite.player;
	Keyboard previous;
	int count = 0;
	
	public Player(int x,int y){
		this.x = x;
		this.y = y;
	}
	
	public void update(Keyboard key,Screen screen){
		if(previous == null)previous = key;
		if(previous.down||previous.up||previous.left||previous.right)
		{
			count++;
			if(speed<3&&count>3)
			{
				speed++;
				count = 0;
			}
		}
		else
		{
			if(speed>1)
			{
				speed--;
				count = 0;
			}
		}
		if(key.up)y-=speed;
		if(key.down)y+=speed;
		if(key.left)x-=speed;
		if(key.right)x+=speed;
		if(x<0){
			x=0;
			speed = 1;
		}
		if((x+sprite.SIZE)>screen.width){
			x=screen.width-sprite.SIZE;
			speed = 1;
		}
		if(y<0){
			y=0;
			speed = 1;
		}
		if((y+sprite.SIZE>screen.height)){
			y=screen.height-sprite.SIZE;
			speed = 1;
		}
		System.out.println("Player x: " + x);
		System.out.println("Player y: " + y);
		previous = key;
	}
	
	public void render(Screen screen){
		screen.renderPlayer(this);
	}
}
