import java.util.ArrayList;

import javax.lang.model.util.ElementScanner6;

public class Board {
	private int[][] b;
	private int manyCars;
	private ArrayList<Vehicle> lot;

	public Board() {

		b = new int[6][6]; //standard board size 6x6
		lot = new ArrayList<Vehicle>();
		Car red = new Car(0, 2, "HORIZONTAL");
		lot.add(red);
		b[2][0] = 1;
		b[2][1] = 2;
	}
	/**
	 * @return length of height (and width if square)
	 */
	public int getDimensions() 
	{
		return b.length;
	}
	public void print()
	{
	   for(int i = 0; i<b.length; i++)
		{
			for(int j = 0; j<b.length; j++)
			 {
				 System.out.print(b[i][j] + " | ");
			 }
			System.out.println();
		}		
	}
	
	public void printLot()
	{
		for(int i = 0; i< lot.size(); i++)
		{
			if(i == 0)
			System.out.print("Red car"+ " X: "+ lot.get(i).getX() + " Y: "+lot.get(i).getY()+"| ");
			else if(lot.get(i).getClass().getSimpleName() == "Car")
			System.out.print("Car"+ " X: "+ lot.get(i).getX() + " Y: "+lot.get(i).getY()+"| ");
			else
			System.out.print("Truck"+ " X: "+ lot.get(i).getX() + " Y: "+lot.get(i).getY()+"| ");
		}
		System.out.println();
	}
