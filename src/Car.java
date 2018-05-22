public class Car implements Vehicle
{ 
 private int size;
 private int R;
 private int C;
 private String direction; //Replacement for enum and simpler. Could be boolean/char if needed
 public Car(int R, int C)
 {
  this.R = R;
  this.C = C;
  size = 2;
  int rand = (int)(Math.random()*2); //0 or 1 to decide direction
  if(rand == 0)
    direction = "HORIZONTAL";
  else
    direction = "VERTICAL";
 }
 public Car(int x, int y, String str) //just in case
 {
   R = x;
   C = y;
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
 public int getR()
 {
  return R;
 }
 
 /**
  * @return y coordinate of Car
  */
 public int getC()
 {
  return C;
 }
 
 public void setR(int a)
 {
  R = a;
 }
 
 public void setC(int a)
 {
  C = a;
 }
 /**
  * @param spaces car will move. Changes coordinate accordingly
  */
 public void move(int spaces)
 {
  if(direction.equals("VERTICAL")) //Now checks string
  {
    R+=spaces;
  }
  else //"HORIZONTAL"
  {
    C+=spaces;
  }
 }

 
}
