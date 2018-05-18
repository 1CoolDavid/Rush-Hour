public class Car implements Vehicle
{ 
 private int size;
 private int X;
 private int Y;
 private String direction; //Replacement for enum and simpler. Could be boolean/char if needed
 public Car(int X, int Y)
 {
  this.X = X;
  this.Y = Y;
  size = 2;
  int rand = (int)(Math.random()*2); //0 or 1 to decide direction
  if(rand == 0)
    direction = "HORIZONTAL";
  else
    direction = "VERTICAL";
 }
 public Car(int x, int y, String str) //just in case
 {
   X = x;
   Y = y;
  direction = str;
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

 
}
