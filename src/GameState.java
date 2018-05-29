import java.awt.Graphics;
import java.util.ArrayList;

public class GameState extends State
{
	public Game game;
    private ArrayList<Vehicle> lot;
    private GameBoard gb;
	public GameState (Game game)
	{
		super(game);
		this.game = game;
		gb = new GameBoard();
		lot = game.board.getLot();
	}
	@Override
	public void tick() 
	{
		gb.tick();
	}

	@Override
	public void render(Graphics g) 
	{
		gb.render(g);
		for(Vehicle v: lot)
		{
			if(v.getClass().getSimpleName().equals("Car"))
				((Car)v).render(g);
			else
				((Truck)v).render(g);
		}
	}
 
}
