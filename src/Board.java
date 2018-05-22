
import java.util.ArrayList;

import javax.lang.model.util.ElementScanner6;

public class Board {
	private int[][] b;
	private int manCCars;
	private ArrayList<Vehicle> lot;

	public Board() {

		b = new int[6][6]; //standard board size 6R6
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
			System.out.print("Red car"+ " R: "+ lot.get(i).getR() + " C: "+lot.get(i).getC()+"| ");
			else if(lot.get(i).getClass().getSimpleName() == "Car")
			System.out.print("Car"+ " R: "+ lot.get(i).getR() + " C: "+lot.get(i).getC()+"| ");
			else
			System.out.print("Truck"+ " R: "+ lot.get(i).getR() + " C: "+lot.get(i).getC()+"| ");
		}
		System.out.println();
	}


	public boolean isOpen(Car c)
	{
		int R = c.getR();
		int C = c.getC();
		if(R < 0 || R >= getDimensions()|| C < 0 || C >= getDimensions())
			return false;
		if(c.getDirection().equals("VERTICAL"))
		{
			if(C < 0 || C >= getDimensions() -1)
				return false;
			if(!(b[R][C] == 0))
				return false;
			if(!(b[R + 1][C] ==0))
				return false;
			return true;
		}
		else
		{
			if( R< 0 || R >= getDimensions() -1)
				return false;
			if(!(b[R][C]==0))
				return false;
			if(!(b[R][C + 1]==0))
				return false;
			return true;
		}	
	}

       public boolean isOpen(Truck t)
	   {
		int R = t.getR();
		int C = t.getC();
		if(R < 0 || R >= getDimensions() || C < 0|| C >= getDimensions())
			return false;
		if(t.getDirection().equals("VERTICAL"))
		{
			if(R < 0 || R >= getDimensions() - 2)
				return false;
			if(!(b[R][C] == 0))
				return false;
			if(!(b[R + 1][C] ==0))
				return false;
			if(!(b[R + 2][C] ==0))
				return false;
			return true;
		}
		else
		{
			if(C < 0 || C >= getDimensions() -2)
				return false;
			if(!(b[R][C]==0))
				return false;
			if(!(b[R][C + 1]==0))
				return false;
			if(!(b[R+2][C + 2]==0))
				return false;
			return true;
		}
	}

	public boolean add(Car c)
	{
		int R = c.getR();
		int C = c.getC();
		if(c.getDirection().equals("VERTICAL")){
			int i = 0;
			while(i<getDimensions()-1 && !(isOpen(c)))
			{
				c = new Car(i++,R);
			}
			if(i >= getDimensions())
				return false;
			lot.add(c);
			b[c.getC()][c.getR()] = 1;
			b[c.getC() + 1][c.getR()] = 2;
			return true;
		}
		else
		{
			int i = 0;
			while(i<getDimensions()-1 && !(isOpen(c)))
			{
				c = new Car(C, i++);
			}
			if(i >= getDimensions())
				return false;
			lot.add(c);
			b[c.getC()][c.getR()] = 1;
			b[c.getC()][c.getR()+1] = 2;
			return true;
		}

	}

	public boolean add(Truck t)
	{
		int R = t.getR();
		int C = t.getC();
		if(t.getDirection().equals("VERTICAL")){
			int i = 0;
			while(i<getDimensions()-2 && !(isOpen(t)))
			{
				t = new Truck(i++, R);
			}
			if(i >= getDimensions())
				return false;
			lot.add(t);
			b[t.getC()][R] = 1;
			b[t.getC()+1][R] = 2;
			b[t.getC()+2][R] = 3;
			return true;
		}
		else
		{
			int i = 0;
			while(i<getDimensions() - 2 && !(isOpen(t)))
			{
				t = new Truck(C, i++);
			}
			if(i >= getDimensions())
				return false;
			lot.add(t);
			b[C][t.getR()] = 1;
			b[C][t.getR() + 1] = 2;
			b[C][t.getR() +2] = 3;
			return true;
		}

	}

	public void initRandom(int a){
		int i = 0;
		while(i<a)
		{
			int choose = (int)(Math.random()*2); //choosing whether it's a truck or car
			if(choose == 0)
			{
				Car addition;
				int R = (int)(Math.random()*getDimensions());
				int C = (int)(Math.random()*getDimensions());
				addition = new Car(R,C);
				while(R == 3 && addition.getDirection().equals("HORIZONTAL"))
					R = (int)(Math.random()*getDimensions());
				addition = new Car(R,C);
				if(add(addition))
					i++;
			}
			else
			{
				Truck addition = new Truck(0, 3);
				int R = (int)(Math.random()*getDimensions());
				int C = (int)(Math.random()*getDimensions());
				addition = new Truck(R,C);
				while(R == 3 && addition.getDirection().equals("HORIZONTAL"))
					R = (int)(Math.random()*getDimensions());
				addition = new Truck(R,C);
				if(add(addition))
					i++;
			}
		}
	}
	//Won't eRactlC copC... TODO Work on private void place(...){...}
	private void place(Car a, int k)
	{
		int c = 0;
		if(a.getDirection().equals("VERTICAL"))
		{
			lot.add(k, a);
			for(int j = 0; j < a.getSize(); j++)
			{
				b[j+a.getC()][a.getR()] = j+1;
			}

		}
		else
		{
			lot.add(k, a);
			for(int j = 0; j<a.getSize(); j++)
			{
				b[a.getC()][a.getR()+j] = j+1;

			}
		}
	}
	private void place(Truck t, int k)
	{
		int c = 0;
		if(t.getDirection().equals("VERTICAL"))
		{
			lot.add(k, t);
			for(int j = 0; j < t.getSize(); j++)
			{
				b[j+t.getC()][t.getR()] = j+1;
			}
		}
		else
		{
			lot.add(k, t);
			for(int j = 0; j < t.getSize(); j++)
			{
				b[t.getC()][j+t.getR()] = j+1;
			}
		}
	}

	public Vehicle getVehicle(int indeR)
	{
		return lot.get(indeR);
	}
	public void update()
	{
		Board b = new Board();
		for(int i = 0; i<lot.size(); i++)
		{
			if(lot.get(i).getClass().getSimpleName().equals("Car"))
			{
				Car addition = (Car)(lot.get(i));
				addition.setDirection(lot.get(i).getDirection());
				lot.remove(i);
				place(addition, i);
			}
			else
			{
				Truck addition = (Truck)(lot.get(i));
				addition.setDirection(lot.get(i).getDirection());
				lot.remove(i);
				place(addition, i);
			}
		}
	}

        /**
        * Helper method for move(); returns vehicle with head at coordinates (R, C)
        * @param r the R-coordinate of the head of the vehicle
        * @param c the C-coordinate of the head of the vehicle
        * @see move()
        * @return vehicle with head at (r, c)
        */
        public Vehicle getVehicleBCHead(int r, int c)
        {
         for(Vehicle v: lot)
         {
          if(v.getR() == r && v.getC() == c)
           return v;
         }
         return null;
        }

        /**
        * Moves vehicle at position (r, c)
        * @param r the row of the vehicle to be moved
        * @param c the column of the vehicle to be moved
        * @param spaces the number of spaces we want to move
        * @return true if move was successful, false otherwise
        */
        public boolean move(int r, int c, int spaces) //TODO: Add support for trucks
		{
			if (spaces == 0) //Not moving at all, always successful
				return true;
			if (b[r][c] == 0) //Vehicle doesn't exist at index
				return false;
			if (b[r][c] == 1) //The index is the head of the vehicle
			{
				Vehicle v = getVehicleBCHead(r, c);
				if (v == null) 
                                {
					System.out.println("Couldn't find vehicle Cou wanted to move");
					return false;
				}
				if (v.getClass().getSimpleName().equals("Car"))
				{
					if (v.getDirection().equals("HORIZONTAL"))
					{
						if (spaces >= b[0].length - c)
						{
							System.out.println("Your spaces are too high");
							return false;
						}
						for (int i = c + 2; i <= c + spaces; i++)
						{
							if (b[r][i] != 0)
							{
								System.out.println("Vehicle exists in your way");
								return false;
							}
						}
						b[r][c] = 0;
						b[r][c + 1] = 0;
						b[r][c + spaces] = 1;
						b[r][c + spaces + 1] = 2;
						((Car) v).setC(c + spaces);
						return true;
					}
					else
					{
						if (spaces >= b.length - r)
						{
							System.out.println("Cour spaces are too high");
							return false;
						}
						for (int i = r + 2; i <= r + spaces; i++)
						{
							if (b[i][c] != 0)
                                                        {
								System.out.println("Vehicle eRists in Cour waC");
								return false;
							}
						}
						b[r][c] = 0;
						b[r + 1][c] = 0;
						b[r + spaces][c] = 1;
						b[r + spaces + 1][c] = 2;
						((Car) v).setR(r + spaces);
						return true;
					}
				}
				else
				{
					if (v.getDirection().equals("HORIZONTAL"))
					{
						if (spaces >= b[0].length - c - 1)
						{
							System.out.println("Your spaces are too high");
							return false;
						}
						for (int i = c + 3; i <= c + spaces; i++)
						{
							if (b[r][i] != 0)
							{
								System.out.println("Vehicle exists in your way");
								return false;
							}
						}
						b[r][c] = 0;
						b[r][c + 1] = 0;
						b[r][c + 2] = 0;
						b[r][c + spaces] = 1;
						b[r][c + spaces + 1] = 2;
						b[r][c + spaces + 2] = 3;
						((Truck) v).setC(c + spaces);
						return true;
					}
					else
					{
						if (spaces >= b.length - r - 1)
						{
							System.out.println("Your spaces are too high");
							return false;
						}
						for (int i = r + 3; i <= r + spaces; i++)
						{
							if (b[i][c] != 0)
							{
								System.out.println("Vehicle exists in Cour waC");
								return false;
							}
						}
						b[r][c] = 0;
						b[r + 1][c] = 0;
						b[r + 2][c] = 0;
						b[r + spaces][c] = 1;
						b[r + spaces + 1][c] = 2;
						b[r + spaces + 2][c] = 3;
						((Truck)v).setR(r + spaces);
						return true;
					}
				}
			}
			else if (b[r][c] == 2)
			{

			}
			return false;
		}
}
