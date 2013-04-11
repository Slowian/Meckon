package slv.Meckon.objects;

import java.awt.Rectangle;
import java.util.ArrayList;

import slv.Meckon.graphics.Screen;

public class Arena {
	public ArrayList<ArenaItem>items = new ArrayList<ArenaItem>();
	private int startX,startY;
	private int itemsSize;
	public Arena(int level,int diameter,int startX,int startY)
	{
		this.startX = startX-3;
		this.startY = startY;
		this.itemsSize =1;
		loadItems(level,diameter/2-7+itemsSize/2);
	}
	
	private void loadItems(int level,int radius){
		int size = itemsSize;
		//int dirs = circleCirc/4;
		//int dir = 0;
		int radBuff = radius;
		for(int j = 0;j<=8-size;j++){
			radius = radBuff+j;
			float circleCirc = ((int) (2*Math.PI*radius)/(size))*(2.1f-size*0.1f);
			for(int i = 0;i<circleCirc;i++)
			{
				int xCalculated = (int) (startX+radius*(Math.cos((2*Math.PI*i)/circleCirc)));
				int yCalculated = (int) (startY+radius*(Math.sin((2*Math.PI*i)/circleCirc)));
				yCalculated -=size/2;
				if(i == 0)
					items.add(new ArenaItem(level,xCalculated-1,yCalculated,size));
				else
				{
					boolean exists = false;
					for(ArenaItem item:items){
						if(item.x == xCalculated&&item.y == yCalculated)
							exists = true;
					}
					if(!exists)
						items.add(new ArenaItem(level,xCalculated,yCalculated,size));
				}
				//if((i-dirs/2-1)%dirs == 0){
				//	dir++;
				//}
			}
		}
		return;
	}
	
	public boolean checkCollision(Rectangle objectRect){
		for(ArenaItem item:items){
			if(objectRect.intersects(item.rect))
				return true;
		}
		return false;
	}
	
	public void render(Screen screen){
		screen.renderArena(this);
	}
}
