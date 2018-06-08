import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import sun.audio.*;
public class Game implements Runnable {

	private Display display;
	public int width, height;
	public String title;
	
	private boolean running = false;
	private Thread thread;
	
	private BufferStrategy bs;
	private Graphics g;
	
	//States
	private State gameState;
	
	//Input
	private KeyManager keyManager;
	private MouseManager mouseManager;
	
	public Board board;
	public Vehicle clickedOn = null;
	public ArrayList<Vehicle> lot;
	
	public Game(String title, int width, int height)
	{
		this.width = width;
		this.height = height;
		this.title = title;
		keyManager = new KeyManager();
		mouseManager = new MouseManager();
		board = new Board();
		board.start(4);
		lot = board.getLot();
		music();
	}
	
    public static void music() 
    {       
        AudioPlayer MGP = AudioPlayer.player;
        AudioStream BGM;
        AudioData MD;

        ContinuousAudioDataStream loop = null;

        try
        {
            InputStream test = new FileInputStream("/home/arnav/Desktop/RushHour/Rush-Hour/res/music.wav");
            BGM = new AudioStream(test);
            AudioPlayer.player.start(BGM);
            //MD = BGM.getData();
            //loop = new ContinuousAudioDataStream(MD);

        }
        catch(FileNotFoundException e)
        {
            System.out.print(e.toString());
        }
        catch(IOException error)
        {
            System.out.print(error.toString());
        }
        MGP.start(loop);
    }
    
	private void init()
	{
		display = new Display(title, width, height);
		display.getFrame().addKeyListener(keyManager);
		display.getFrame().addMouseListener(mouseManager);
		display.getFrame().addMouseMotionListener(mouseManager);
		display.getCanvas().addMouseListener(mouseManager);
		display.getCanvas().addMouseMotionListener(mouseManager);
		Assets.init();
		for(int i = 0; i < lot.size(); i++) //Initializing values for rendering
		{
			Vehicle v = lot.get(i);
			if(i == 0)
			{
				int x = ((Car)v).getX();
				int y = ((Car)v).getY();
				lot.set(0, new Car(x, y, x * 36, y * 36, "HORIZONTAL", this));
				((Car)lot.get(0)).setTexture(Assets.hredcar);
			}
			else if(v.getClass().getSimpleName().equals("Car") && v.getDirection().equals("HORIZONTAL"))
			{
				int x = ((Car)v).getX();
				int y = ((Car)v).getY();
				lot.set(i, new Car(x, y, x * 36, y * 36, "HORIZONTAL", this));
				((Car)lot.get(i)).setTexture(Assets.hpurplecar);
			}
			else if(v.getClass().getSimpleName().equals("Car") && v.getDirection().equals("VERTICAL"))
			{
				int x = ((Car)v).getX();
				int y = ((Car)v).getY();
				lot.set(i, new Car(x, y, x * 36, y * 36, "VERTICAL", this));
				((Car)lot.get(i)).setTexture(Assets.vgreencar);	
			}
			else if(v.getClass().getSimpleName().equals("Truck") && v.getDirection().equals("HORIZONTAL"))
			{
				int x = ((Truck)v).getX();
				int y = ((Truck)v).getY();
				lot.set(i, new Truck(x, y, x * 36, y * 36, "HORIZONTAL", this));
				((Truck)lot.get(i)).setTexture(Assets.hyellowtruck);
			}
			else if(v.getClass().getSimpleName().equals("Truck") && v.getDirection().equals("VERTICAL"))
			{
				int x = ((Truck)v).getX();
				int y = ((Truck)v).getY();
				lot.set(i, new Truck(x, y, x * 36, y * 36, "VERTICAL", this));
				((Truck)lot.get(i)).setTexture(Assets.vbluetruck);
			}
			//music();

		}
		//board.print();
		gameState = new GameState(this);
		State.setState(gameState);
	}
	
	public boolean checkWin()
	{
		if(((Car)lot.get(0)).getGraphicX() + 72 == 216)
			return true;
		return false;
	}
	
	public boolean forwardOccupied(int x, int y)
	{
		for(int i = 0; i < lot.size(); i++)
		{
			Vehicle v = lot.get(i);
			if(v.getGraphicX() == clickedOn.getGraphicX() && v.getGraphicY() == clickedOn.getGraphicY())
				continue;
				if(v.getClass().getSimpleName().equals("Car") && v.getDirection().equals("HORIZONTAL")
						&& x >= v.getGraphicX() && x <= v.getGraphicX() + 71
						&& y >= v.getGraphicY() && y <= v.getGraphicY() + 35)
				{
					//System.out.println("Collision detected at x " + x + " and y " + y + " with horizontal car");
					return true;
				}
				if(v.getClass().getSimpleName().equals("Car") && v.getDirection().equals("VERTICAL")
						&& x >= v.getGraphicX() && x <= v.getGraphicX() + 35
						&& y >= v.getGraphicY() && y <= v.getGraphicY() + 71)
				{
					//System.out.println("Collision detected at x " + x + " and y " + y + " with vertical car");
					return true;
				}
				if(v.getClass().getSimpleName().equals("Truck") && v.getDirection().equals("HORIZONTAL")
						&& x >= v.getGraphicX() && x <= v.getGraphicX() + 107
						&& y >= v.getGraphicY() && y <= v.getGraphicY() + 35)
				{
					//System.out.println("Collision detected at x " + x + " and y " + y + " with horizontal truck");
					return true;
				}
				if(v.getClass().getSimpleName().equals("Truck") && v.getDirection().equals("VERTICAL")
						&& x >= v.getGraphicX() && x <= v.getGraphicX() + 35
						&& y >= v.getGraphicY() && y <= v.getGraphicY() + 107)
				{
					//System.out.println("Collision detected at x " + x + " and y " + y + " with vertical truck");
					return true;
				}
		}
		return false;
	}
	
	private void tick()
	{
		keyManager.tick();
		if(State.getState() != null)
			State.getState().tick();
		if(mouseManager.isLeftPressed())
		{
           for(Vehicle v : lot)
           {
               if(v.getClass().getSimpleName().equals("Car") && v.getDirection() == "HORIZONTAL")
               {
            	   if(mouseManager.getMouseX() >= v.getGraphicX() && mouseManager.getMouseX() <= v.getGraphicX() + 72 && mouseManager.getMouseY() >= v.getGraphicY() && mouseManager.getMouseY()<= v.getGraphicY() + 36)
            	   {
            		   clickedOn = (Car)v;
            		   //System.out.println("Horizontal car clicked on");
            		   break;
            	   }
               }
               else if(v.getClass().getSimpleName().equals("Car") && v.getDirection() == "VERTICAL")
               {
            	   if(mouseManager.getMouseX() >= v.getGraphicX() && mouseManager.getMouseX() <= v.getGraphicX() + 36 && mouseManager.getMouseY() >= v.getGraphicY() && mouseManager.getMouseY()<= v.getGraphicY() + 72)
            	   {
            		   clickedOn = (Car)v;
            		   //System.out.println("Vertical car clicked on");
            		   break;
            	   }
               }
               else if(v.getClass().getSimpleName().equals("Truck") && v.getDirection() == "HORIZONTAL")
               {
            	   if(mouseManager.getMouseX() >= v.getGraphicX() && mouseManager.getMouseX() <= v.getGraphicX() + 108 && mouseManager.getMouseY() >= v.getGraphicY() && mouseManager.getMouseY()<= v.getGraphicY() + 36)
            	   {
            		   clickedOn = (Truck)v;
            		   //System.out.println("Horizontal truck clicked on");
            		   break;
            	   }
               }
               else if(v.getClass().getSimpleName().equals("Truck") && v.getDirection() == "VERTICAL")
               {
            	   if(mouseManager.getMouseX() >= v.getGraphicX() && mouseManager.getMouseX() <= v.getGraphicX() + 72 && mouseManager.getMouseY() >= v.getGraphicY() && mouseManager.getMouseY()<= v.getGraphicY() + 108)
            	   {
            		   clickedOn = (Truck)v;
            		   //System.out.println("Vertical truck clicked on");
            		   break;
            	   }
               }
           }
           
		}
		
        if(clickedOn != null)
        {
     	   //System.out.println("Vehicle clicked on is not null");
     	   if(clickedOn.getClass().getSimpleName().equals("Car") && clickedOn.getDirection().equals("HORIZONTAL"))
     	   {
     		   //System.out.println("Car currently clicked on is horizontal");
     		   if(keyManager.right && !forwardOccupied(((int)clickedOn.getGraphicX()) + 72, ((int)clickedOn.getGraphicY())))
     		   {
     			  // System.out.println("Right key pressed");
     			   ((Car)clickedOn).graphicMove(36);
     		   }
     		   else if(keyManager.left && !forwardOccupied(((int)clickedOn.getGraphicX()) - 36, ((int)clickedOn.getGraphicY())))
     		   {
     			   ((Car)clickedOn).graphicMove(-36);
     		   }
     	   }
     	   else if(clickedOn.getClass().getSimpleName().equals("Car") && clickedOn.getDirection().equals("VERTICAL"))
     	   {
     		   //System.out.println("Car currently clicked on is horizontal");
     		   if(keyManager.down && !forwardOccupied(((int)clickedOn.getGraphicX()), ((int)clickedOn.getGraphicY()) + 72))
     		   {
     			  // System.out.println("Right key pressed");
     			   ((Car)clickedOn).graphicMove(36);
     		   }
     		   else if(keyManager.up && !forwardOccupied(((int)clickedOn.getGraphicX()), ((int)clickedOn.getGraphicY()) - 36))
     		   {
     			   ((Car)clickedOn).graphicMove(-36);
     		   }
     	   }
     	   else if(clickedOn.getClass().getSimpleName().equals("Truck") && clickedOn.getDirection().equals("HORIZONTAL"))
     	   {
     		   //System.out.println("Car currently clicked on is horizontal");
     		   if(keyManager.right && !forwardOccupied(((int)clickedOn.getGraphicX()) + 108, ((int)clickedOn.getGraphicY())))
     		   {
     			  // System.out.println("Right key pressed");
     			   ((Truck)clickedOn).graphicMove(36);
     		   }
     		   else if(keyManager.left && !forwardOccupied(((int)clickedOn.getGraphicX()) - 36, ((int)clickedOn.getGraphicY())))
     		   {
     			   ((Truck)clickedOn).graphicMove(-36);
     		   }
     	   }
     	   else if(clickedOn.getClass().getSimpleName().equals("Truck") && clickedOn.getDirection().equals("VERTICAL"))
     	   {
     		   //System.out.println("Car currently clicked on is horizontal");
     		   if(keyManager.down && !forwardOccupied(((int)clickedOn.getGraphicX()), ((int)clickedOn.getGraphicY()) + 108))
     		   {
     			  // System.out.println("Right key pressed");
     			   ((Truck)clickedOn).graphicMove(36);
     		   }
     		   else if(keyManager.up && !forwardOccupied(((int)clickedOn.getGraphicX()), ((int)clickedOn.getGraphicY()) - 36))
     		   {
     			   ((Truck)clickedOn).graphicMove(-36);
     		   }
     	   }
        }
        if(checkWin())
        {
        	System.out.println("You win!");
        	//System.exit(0);
        }
	}
	
	private void render()
	{
		bs = display.getCanvas().getBufferStrategy();
		if(bs == null)
		{
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();
		//Clear Screen
		g.clearRect(0, 0, width, height);
		//Draw Here!
		
		if(State.getState() != null)
			State.getState().render(g);
		
		//End Drawing!
		bs.show();
		g.dispose();
	}
	
	public void run()
	{
		
		init();
		
		int fps = 60;
		double timePerTick = 1000000000 / fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;
		int ticks = 0;
		
		while(running)
		{
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			timer += now - lastTime;
			lastTime = now;
			
			if(delta >= 1)
			{
				tick();
				render();
				ticks++;
				delta--;
			}
			
			if(timer >= 1000000000)
			{
				ticks = 0;
				timer = 0;
			}
		}
		
		stop();
		
	}
	
	public KeyManager getKeyManager()
	{
		return keyManager;
	}
	
	public MouseManager getMouseManager()
	{
		return mouseManager;
	}
	public synchronized void start()
	{
		if(running)
			return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public synchronized void stop()
	{
		if(!running)
			return;
		running = false;
		try 
		{
			thread.join();
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
	}
	
}