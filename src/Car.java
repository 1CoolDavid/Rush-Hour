import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Car implements Vehicle
{ 
 private int size;
 private int X;
 private int Y;
 public float graphicX;
 public float graphicY;
 private Game game;
 private BufferedImage texture;
 private String direction; //Replacement for enum and simpler. Could be boolean/char if needed
 public Car(int X, int Y, float graphicX, float graphicY, String direction, Game game)
 {
  this.X = X;
  this.Y = Y;
  this.graphicX = graphicX;
  this.graphicY = graphicY;
  this.game = game;
  size = 2;
  this.direction = direction;
  if(direction.equals("HORIZONTAL"))
	  texture = Assets.hpurplecar;
  else
	  texture = Assets.vgreencar;
 }
 public Car(int x, int y, String str) //just in case
 {
   X = x;
   Y = y;
  direction = str;
 }
 
 public Car (int x, int y)
 {
	 X = x;
	 Y = y;
	  size = 2;
	  int rand = (int)(Math.random()*2); //0 or 1 to decide direction
	  if(rand == 0)
	    direction = "HORIZONTAL";
	  else
	    direction = "VERTICAL";
 }
 /**
  * Sets the size -_-
  * @param s is new size
  */
 public void setSize(int s)
 { 
   size = s; 
 }

 /**
  * @return direction of Car
  */
 public String getDirection()
 {
   return direction;
 }

 public void setDirection(String str)
 {
   direction = str;
 }
 /**
  * @return size of the car
  */
 public int getSize()
 { 
   return size;
 }
 
 /**
  * @return X coordinate of Car
  */
 public int getX()
 {
  return X;
 }
 
 /**
  * @return y coordinate of Car
  */
 public int getY()
 {
  return Y;
 }
 
 public void setX(int a)
 {
  X = a;
 }
 
 public void setY(int a)
 {
  Y = a;
 }
 
 public float getGraphicX()
 {
	 return graphicX;
 }
 
 public float getGraphicY()
 {
	 return graphicY;
 }
 /**
  * @param spaces car will move. Changes coordinate accordingly
  */
 public void move(int spaces)
 {
  if(direction.equals("VERTICAL")) //Now checks string
  {
    Y+=spaces;
  }
  else //"HORIZONTAL"
  {
    X+=spaces;
  }
  
 }
 
 public void tick()
 {
	 /*
	  if(game.getKeyManager().up)
	  {
		  graphicY -= 3;
	  }
	  if(game.getKeyManager().down)
	  {
		  graphicY += 3;
	  }
	  if(game.getKeyManager().left)
	  {
		  graphicX -= 3;
	  }
	  if(game.getKeyManager().right)
	  {
		  graphicX += 3;
	  }
	  */
 }
 
 public void graphicMove(int a)
 {
	 if(direction.equals("HORIZONTAL") && graphicX + a >= 0 && graphicX + a <= 144)
	 {
		 graphicX += a;
	 }
	 else if(direction.equals("VERTICAL") && graphicY + a >= 0 && graphicY + a <= 144)
	 {
		 graphicY += a;
	 }
 }
 
 public void render(Graphics g)
 {
	  g.drawImage(texture, (int)graphicX, (int)graphicY, null);
 }

 public void setTexture(BufferedImage b)
 {
	 texture = b;
 }
 
 public boolean equals(Object o)
 {
	 Car other = (Car)o;
	 return this.graphicX == other.graphicX && this.graphicY == other.graphicY;
 }
}
