public class Truck implements Vehicle
{
 private int size;
 private int R;
 private int C;
 private String direction;
 public Truck(int X, int Y)
 {
  R = X;
  C = Y;
  size = 3;
  int rand = (int)(Math.random()*2); //0 or 1 to decide direction
  if(rand == 0)
    direction = "HORIZONTAL";
  else
    direction = "VERTICAL";
 }
 
 /**
  * @return X coordinate of Truck
  */
 public int getR()
 {
  return R;
 }
 
 /**
  * @return Y coordinate of Truck
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
  * @return direction of Truck
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
  * sets the size of Truck -_-
  * @param s is new size
  */
 public void setSize(int s)
 { 
   size = s;
 }
 /**
  * @return size of Truck
  */
 public int getSize()
 {
   return size;
 }

 /**
  * @param spaces truck will move. Coordinates changed accordingly
  */
 public void move(int spaces)
 {
  if(direction.equals("VERTICAL"))
  {
    R+=spaces;
  }
  else //"Horizontal"
  {
    C+=spaces;
  }
  
 }
}
