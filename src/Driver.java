import java.util.Scanner;
public class Driver
{
 //Class used for command-line testing
 public static void main (String[] args)
 {
  Board b = new Board();
  for(int i = 0; i < b.b.length; i++)
  {
   for(int j = 0; j < b.b[0].length; j++)
   {
    System.out.println(b.b[i][j]);
   }
  System.out.println();
  }
 }
}
