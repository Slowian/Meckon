package slv.Meckon.objects;

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
	
	public Player(int number,int x,int y){
		this.x = x;
		this.y = y;
		this.number = number;
		if(number == 1)this.sprite =Sprite.player1;
		if(number == 2)this.sprite =Sprite.player2;
	}
	
	public void update(Keyboard key,Screen screen){
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
		if((number == 2&&key.up)||(number == 1&&key.w))y-=speed;
		if((number == 2&&key.down)||(number == 1&&key.s))y+=speed;
		if((number == 2&&key.left)||(number == 1&&key.a))x-=speed;
		if((number == 2&&key.right)||(number == 1&&key.d))x+=speed;
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
	
	public void render(Screen screen){
		screen.renderPlayer(this);
	}
}
