
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


	public boolean isOpen(Car c)
	{
		int x = c.getX();
		int y = c.getY();
		if(x < 0 || x > getDimensions()|| y < 0 || y > getDimensions())
			return false;
		if(c.getDirection().equals("VERTICAL"))
		{
			if(y < 0 || y >= getDimensions() -1)
				return false;
			if(!(b[x][y] == 0))
				return false;
			if(!(b[x][y+1] ==0))
				return false;
			return true;
		}
		else
		{
			if( x< 0 || x >= getDimensions() -1)
				return false;
			if(!(b[x][y]==0))
				return false;
			if(!(b[x+1][y]==0))
				return false;
			return true;
		}	
	}

        public boolean isOpen(Truck t)
	{
		int x = t.getX();
		int y = t.getY();
		if(x < 0 || x > getDimensions() || y < 0|| y > getDimensions())
			return false;
		if(t.getDirection().equals("VERTICAL"))
		{
			if(y < 0 || y >= getDimensions() - 2)
				return false;
			if(!(b[x][y] == 0))
				return false;
			if(!(b[x][y+1] ==0))
				return false;
			if(!(b[x][y+2] ==0))
				return false;
			return true;
		}
		else
		{
			if(x < 0 || x >= getDimensions() -2)
				return false;
			if(!(b[x][y]==0))
				return false;
			if(!(b[x+1][y]==0))
				return false;
			if(!(b[x+2][y]==0))
				return false;
			return true;
		}
	}

	public boolean add(Car c)
	{
		int x = c.getX();
		int y = c.getY();
		if(c.getDirection().equals("VERTICAL")){
			int i = 0;
			while(i<getDimensions()-1 && !(isOpen(c)))
			{
				c = new Car(i++,x);
			}
			if(i >= getDimensions())
				return false;
			lot.add(c);
			b[c.getY()][c.getX()] = 1;
			b[c.getY() + 1][c.getX()] = 2;
			return true;
		}
		else
		{
			int i = 0;
			while(i<getDimensions()-1 && !(isOpen(c)))
			{
				c = new Car(y, i++);
			}
			if(i >= getDimensions())
				return false;
			lot.add(c);
			b[c.getY()][c.getX()] = 1;
			b[c.getY()][c.getX()+1] = 2;
			return true;
		}

	}

	public boolean add(Truck t)
	{
		int x = t.getX();
		int y = t.getY();
		if(t.getDirection().equals("VERTICAL")){
			int i = 0;
			while(i<getDimensions()-2 && !(isOpen(t)))
			{
				t = new Truck(i++, x);
			}
			if(i >= getDimensions())
				return false;
			lot.add(t);
			b[t.getY()][x] = 1;
			b[t.getY()+1][x] = 2;
			b[t.getY()+2][x] = 3;
			return true;
		}
		else
		{
			int i = 0;
			while(i<getDimensions() - 2 && !(isOpen(t)))
			{
				t = new Truck(y, i++);
			}
			if(i >= getDimensions())
				return false;
			lot.add(t);
			b[y][t.getX()] = 1;
			b[y][t.getX() + 1] = 2;
			b[y][t.getX() +2] = 3;
			return true;
		}

	}

	public void initRandom(int a){
		int i = 0;
		while(i<a)
		{
			int choose = (int)(Math.random()*2);
			if(choose == 0)
			{
				Car addition;
				int x = (int)(Math.random()*getDimensions());
				int y = (int)(Math.random()*getDimensions());
				addition = new Car(x,y);
				while(x == 3 && addition.getDirection().equals("HORIZONTAL"))
					x = (int)(Math.random()*getDimensions());
				addition = new Car(x,y);
				if(add(addition))
					i++;
			}
			else
			{
				Truck addition = new Truck(0, 3);
				int x = (int)(Math.random()*getDimensions());
				int y = (int)(Math.random()*getDimensions());
				addition = new Truck(x,y);
				while(x == 3 && addition.getDirection().equals("HORIZONTAL"))
					x = (int)(Math.random()*getDimensions());
				addition = new Truck(x,y);
				if(add(addition))
					i++;
			}
		}
	}
	//Won't exactly copy... TODO Work on private void place(...){...}
	private void place(Car a, int k)
	{
		int c = 0;
		if(a.getDirection().equals("VERTICAL"))
		{
			lot.add(k, a);
			for(int j = 0; j < a.getSize(); j++)
			{
				b[j+a.getY()][a.getX()] = j+1;
			}

		}
		else
		{
			lot.add(k, a);
			for(int j = 0; j<a.getSize(); j++)
			{
				b[a.getY()][a.getX()+j] = j+1;

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
				b[j+t.getY()][t.getX()] = j+1;
			}
		}
		else
		{
			lot.add(k, t);
			for(int j = 0; j < t.getSize(); j++)
			{
				b[t.getY()][j+t.getX()] = j+1;
			}
		}
	}

	public Vehicle getVehicle(int index)
	{
		return lot.get(index);
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
        * Helper method for move(); returns vehicle with head at coordinates (x, y)
        * @param r the x-coordinate of the head of the vehicle
        * @param c the y-coordinate of the head of the vehicle
        * @see move()
        * @return vehicle with head at (x, y)
        */
        public Vehicle getVehicleByHead(int r, int c)
        {
         for(Vehicle v: lot)
         {
          if(v.getX() == c && v.getY() == r)
           return v;
         }
         return null;
        }

        /**
        * Moves vehicle at position (x, y)
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
				Vehicle v = getVehicleByHead(r, c);
				if (v == null) 
                                {
					System.out.println("Couldn't find vehicle you wanted to move");
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
						((Car) v).setX(c + spaces);
						return true;
					}
					else
					{
						if (spaces >= b.length - r)
						{
							System.out.println("Your spaces are too high");
							return false;
						}
						for (int i = r + 2; i <= r + spaces; i++)
						{
							if (b[i][c] != 0)
                                                        {
								System.out.println("Vehicle exists in your way");
								return false;
							}
						}
						b[r][c] = 0;
						b[r + 1][c] = 0;
						b[r + spaces][c] = 1;
						b[r + spaces + 1][c] = 2;
						((Car) v).setY(r + spaces);
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
						((Truck) v).setX(r + spaces);
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
								System.out.println("Vehicle exists in your way");
								return false;
							}
						}
						b[r][c] = 0;
						b[r + 1][c] = 0;
						b[r + 2][c] = 0;
						b[r + spaces][c] = 1;
						b[r + spaces + 1][c] = 2;
						b[r + spaces + 2][c] = 3;
						((Truck)v).setY(r + spaces);
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
