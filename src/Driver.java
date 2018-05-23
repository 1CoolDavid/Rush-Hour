import java.util.Scanner;
public class Driver
{
 //Class used for command-line testing
 public static void main (String[] args)
 {
  Board b = new Board();
 // b.init(); Isn't this already done in the constructor?
 //creates 4 + red car.
   b.start(4);
   b.print();
   b.printLot();
   Scanner s = new Scanner(System.in);
   System.out.println("Row of vehicle you want to move");
   int r = s.nextInt();
   System.out.println("Column of vehicle you want to move");
   int c = s.nextInt();
   System.out.println("Spaces you want to move");
   int spaces = s.nextInt();
   boolean moved = b.move(r, c, spaces);
   s.close();
   if(!moved)
   System.out.println("Move unsuccessful");
   else
   {
       System.out.println("Move successful");
       b.print();
   }
 }
}
