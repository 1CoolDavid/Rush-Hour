import java.awt.List;
import java.util.Scanner;
public class Driver
{
 //Class used for command-line testing
 public static void main (String[] args)
 {
  Board b = new Board();
 // b.init(); Isn't this already done in the constructor?
  b.initRandom(5); //creates 5 + red car.
  b.print();
  b.printLot();
  b.update();
  b.printLot();
  b.print();
 }
}
