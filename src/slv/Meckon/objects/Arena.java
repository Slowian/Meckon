package slv.Meckon.objects;

import java.util.ArrayList;

import slv.Meckon.graphics.Screen;

public class Arena {
	public ArrayList<ArenaItem>items = new ArrayList<ArenaItem>();
	private int startX,startY;
	public Arena(int level,int diameter,int startX,int startY)
	{
		this.startX = startX-2;
		this.startY = startY;
		loadItems(level,diameter/2-4);
	}
	
	private void loadItems(int level,int radius){
		int circleCirc = (int) (2*Math.PI*radius)/7;
		//int dirs = circleCirc/4;
		//int dir = 0;
		for(int i = 0;i<circleCirc;i++)
		{
			int xCalculated = (int) (startX+radius*(Math.cos((2*Math.PI*i)/circleCirc)));
			int yCalculated = (int) (startY+radius*(Math.sin((2*Math.PI*i)/circleCirc)));
			if(i == 0)
				items.add(new ArenaItem(level,xCalculated-1,yCalculated-4));
			else
				items.add(new ArenaItem(level,xCalculated,yCalculated-4));
			//if((i-dirs/2-1)%dirs == 0){
			//	dir++;
			//}
		}
	}
	
	public void render(Screen screen){
		screen.renderArena(this);
	}
}
