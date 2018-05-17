import java.util.Scanner;
public class Driver
{
 //Class used for command-line testing
 public static void main (String[] args)
 {
  Board b = new Board();
  b.initRandom(4);
  b.print();
 }
}
