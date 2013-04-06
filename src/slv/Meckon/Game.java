package slv.Meckon;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import slv.Meckon.graphics.Screen;
import slv.Meckon.input.Keyboard;
import slv.Meckon.objects.Arena;
import slv.Meckon.objects.Player;

public class Game extends Canvas implements Runnable{
	private static final long serialVersionUID = 1L;

	public static int width = 1920/6;//320
	public static int height = width/16*9;//180
	public static int scale = 3;
	public static String title = "Meckon";
	
	private Thread thread;
	private boolean running = false;
	private Screen screen;
	private JFrame frame;
	private Keyboard key;
	
	private BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();
	
	private Player player1,player2;
	private Arena arena;
	
	public Game(){
		Dimension size = new Dimension(width*scale,height*scale);
		setPreferredSize(size);
		
		screen = new Screen(width,height);
		frame = new JFrame();
		key = new Keyboard();
		player1 = new Player(1,width/2-10,height/2-4);
		player2 = new Player(2,width/2+2,height/2-4);
		arena = new Arena(0,height,width/2,height/2);
		
		addKeyListener(key);
	}
	
	public synchronized void start() {
		running = true;
		thread = new Thread(this,"Meckon");
		thread.start();
	}
	
	public synchronized void stop(){
		running = false;
		try
		{
			thread.join();
		}catch(InterruptedException ex){
			ex.printStackTrace();
		}
	}
	
	public void run() {
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		double ns = 1000000000.0/60.0;
		double delta = 0;
		int frames = 0;
		int updates = 0;
		requestFocus();
		while(running)
		{
			long now = System.nanoTime();
			delta += (now-lastTime)/ns;
			lastTime = now;
			while(delta>=1)
			{
				update();
				updates++;
				delta--;
			}
			render();
			frames++;
			if(System.currentTimeMillis()-timer >1000)
			{
				timer+=1000;
				frame.setTitle(title+ " | UPS: " + updates+" | FPS: " + frames);
				frames = 0;
				updates = 0;
			}
		}
	}
	
	int x = 0,y=0;
	public void update(){
		key.update();
		if(key.up)y++;
		if(key.down)y--;
		if(key.left)x++;
		if(key.right)x--;
		player1.update(key,screen);
		player2.update(key, screen);
	}
	
	public void render(){
		BufferStrategy bs = getBufferStrategy();
		if(bs == null)
		{
			createBufferStrategy(3);
			return;
		}
		
		screen.render(x,y);
		arena.render(screen);
		player1.render(screen);
		player2.render(screen);
		
		for(int i = 0;i<pixels.length;i++)
			pixels[i] = screen.pixels[i];
		
		Graphics g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, getWidth(), getHeight(),null);
		g.dispose();
		bs.show();
	}
	
	public static void main(String[] args){
		Game game = new Game();
		game.frame.setResizable(false);
		game.frame.setTitle(Game.title);
		game.frame.add(game);
		game.frame.pack();
		game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.frame.setLocationRelativeTo(null);
		game.frame.setVisible(true);
		
		game.start();
	}
	
}
