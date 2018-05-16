public interface Vehicle
{
 public enum Direction
 {
  FORWARD, BACKWARD, UP, DOWN
 }
 
 void move(int spaces Direction d);
}
