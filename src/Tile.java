import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Tile
{
 public BufferedImage texture;
 public final static int WIDTH = 36;
 public final static int HEIGHT = 36;
 
 public Tile()
 {
  texture = Assets.tile;
 }
 
 public void tick()
 {
	 
 }
 
 public void render(Graphics g, int x, int y)
 {
	 g.drawImage(texture, x, y, WIDTH, HEIGHT, null);
 }
 
}
