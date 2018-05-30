import java.awt.Color;
import java.awt.Graphics;

public class GameBoard 
{
 public Tile[][] tiles = new Tile[6][6];
 
 public GameBoard()
 {
	 for(int i = 0; i < tiles.length; i++)
	 {
		 for(int j = 0; j < tiles[0].length; j++)
		 {
			 tiles[i][j] = new Tile();
		 }
	 }
 }
 
 public void tick()
 {
	 
 }
 
 public void render(Graphics g)
 {
	 for(int i = 0; i < tiles.length; i++)
	 {
		 for(int j = 0; j < tiles[0].length; j++)
		 {
			 tiles[i][j].render(g, j * Tile.WIDTH, i * Tile.HEIGHT);
		 }
	 }
	 
	 //Adding horizontal lines to distinguish rows from each other
	 g.setColor(Color.BLACK);
	 g.drawLine(0, 36, 216, 36);
	 g.drawLine(0, 72, 216, 72);
	 g.drawLine(0, 108, 216, 108);
	 g.drawLine(0, 144, 216, 144);
	 g.drawLine(0, 180, 216, 180);
 }
}
