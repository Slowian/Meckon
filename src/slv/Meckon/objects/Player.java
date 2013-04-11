package slv.Meckon.objects;

import java.awt.Rectangle;

import slv.Meckon.graphics.Screen;
import slv.Meckon.graphics.Sprite;
import slv.Meckon.input.Keyboard;

public class Player {
	
	public int x,y;
	private int speed = 1;
	public Sprite sprite;
	Keyboard previous;
	int count = 0;
	int number = 0;
	int armor;
	int maxArmor;
	
	public Player(int number,int x,int y){
		this.x = x;
		this.y = y;
		this.number = number;
		if(number == 1)this.sprite =Sprite.player1;
		if(number == 2)this.sprite =Sprite.player2;
	}
	
	public void update(Keyboard key,Screen screen,Arena arena,Player player){
		if(previous == null)previous = key;
		if((number == 2 &&(previous.down||previous.up||previous.left||previous.right))||
				(number == 1&&(previous.w||previous.s||previous.a||previous.d)))
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
		if((number == 2&&key.up)||(number == 1&&key.w)){
			if(!arena.checkCollision(new Rectangle(x,y-speed,sprite.SIZE,sprite.SIZE))
					&&!player.checkCollision(new Rectangle(x,y-speed,sprite.SIZE,sprite.SIZE)))
				y-=speed;
			else
			if(!arena.checkCollision(new Rectangle(x,y-1,sprite.SIZE,sprite.SIZE))
					&&!player.checkCollision(new Rectangle(x,y-1,sprite.SIZE,sprite.SIZE)))
				y--;
		}
		if((number == 2&&key.down)||(number == 1&&key.s)){
			if(!arena.checkCollision(new Rectangle(x,y+speed,sprite.SIZE,sprite.SIZE))
					&&!player.checkCollision(new Rectangle(x,y+speed,sprite.SIZE,sprite.SIZE)))
				y+=speed;
			else
			if(!arena.checkCollision(new Rectangle(x,y+1,sprite.SIZE,sprite.SIZE))
					&&!player.checkCollision(new Rectangle(x,y+1,sprite.SIZE,sprite.SIZE)))
				y++;
		}
		if((number == 2&&key.left)||(number == 1&&key.a)){
			if(!arena.checkCollision(new Rectangle(x-speed,y,sprite.SIZE,sprite.SIZE))
					&&!player.checkCollision(new Rectangle(x-speed,y,sprite.SIZE,sprite.SIZE)))
				x-=speed;
			else
			if(!arena.checkCollision(new Rectangle(x-1,y,sprite.SIZE,sprite.SIZE))
					&&!player.checkCollision(new Rectangle(x-1,y,sprite.SIZE,sprite.SIZE)))
				x--;
		}
		if((number == 2&&key.right)||(number == 1&&key.d)){
			if(!arena.checkCollision(new Rectangle(x+speed,y,sprite.SIZE,sprite.SIZE))
					&&!player.checkCollision(new Rectangle(x+speed,y,sprite.SIZE,sprite.SIZE)))
				x+=speed;
			else
			if(!arena.checkCollision(new Rectangle(x+1,y,sprite.SIZE,sprite.SIZE))
					&&!player.checkCollision(new Rectangle(x+1,y,sprite.SIZE,sprite.SIZE)))
				x++;
		}
		
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
		System.out.println("Player "+number+" x: " + x);
		System.out.println("Player "+number+" y: " + y);
		previous = key;
	}
	
	public boolean checkCollision(Rectangle objectRect){
		if(objectRect.intersects(new Rectangle(x,y,sprite.SIZE,sprite.SIZE)))
				return true;
		return false;
	}
	
	public void render(Screen screen){
		screen.renderPlayer(this);
	}
}
