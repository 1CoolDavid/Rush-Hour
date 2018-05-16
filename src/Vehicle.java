public interface Vehicle
{ 
 void move(int spaces, String direction);
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

}
