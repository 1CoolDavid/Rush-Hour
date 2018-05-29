public interface Vehicle
{ 

/**
 * @param spaces Vehicle object will move
 * @see Truck and Car classes
 */
 void move(int spaces);
 
 int getX(); 

 int getY();

 String getDirection();

 int getSize();
 
 float getGraphicX();
 
 float getGraphicY();
 
 void graphicMove(int a);

}
