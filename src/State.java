import java.awt.Graphics;

public abstract class State 
{
 public static State currentState = null;
 public Game game;
 
    public State (Game game)
    {
    	this.game = game;
    }
	public static void setState(State state)
	{
		currentState = state;
	}
	
	public static State getState()
	{
		return currentState;
	}
	
	//CLASS
	public abstract void tick();
	
	public abstract void render(Graphics g);
}
