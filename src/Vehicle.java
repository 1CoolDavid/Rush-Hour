public interface Vehicle
{ 
 void move(int spaces);

/**
 * @param spaces Vehicle object will move
 * @see Truck and Car classes
 */
// void move(int spaces); Too lazy to take care of this method right now

 int getX(); 

 int getY();

 String getDirection();

 int getSize();

}
