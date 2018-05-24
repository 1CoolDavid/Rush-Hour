import java.util.ArrayList;

public class Board {
	private int[][] b;
	private ArrayList<Vehicle> lot;

	/**
	 * Creates 2Darray of integers with an arraylist of vehicles while adding red car
	 */
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
	/**
	 * Prints the arraylist of vehicles "lot"
	 */
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
	/**
	 * generates a random vehicle and adds it to the board if valid
	 * @return true if generated vehicle is added, false otherwise
	 */
	public boolean initRandom(){
		int choose = (int)(Math.random()*2);
		if(choose == 0)
		{
			Car addition;
			int x = (int)(Math.random()*getDimensions());
			int y = (int)(Math.random()*getDimensions());
			addition = new Car(x,y);
			if(addition.getDirection().equals("HORIZONTAL"))
			{
				if(x < 0 || x > 4)
					return false;
				while(x < 5 &&!(horizontalIsOpen(addition)))
				{
					x++;
				}
				if(x >= 5)
					return false;
				addition.setX(x);
				if(horizontalIsOpen(addition))
				{
					lot.add(addition);
					b[y][x] = 1;
					b[y][x+1] = 2;
					return true;
				}
				return false;
			}
			else
			{
				if(y < 0 || y > 4)
					return false;
				while(y < 5 && !(verticalIsOpen(addition)))
				{
					y++;
				}
				if(y >= 5)
					return false;
				if(verticalIsOpen(addition))
				{
					addition.setY(y);
					lot.add(addition);
					b[y][x] = 1;
					b[y+1][x] = 2;
					return true;
				}
				return false;
			}
		}
		else
		{
			Truck addition = new Truck(0, 3);
			int x = (int)(Math.random()*getDimensions());
			int y = (int)(Math.random()*getDimensions());
			addition = new Truck(x,y);
			if(addition.getDirection().equals("HORIZONTAL"))
			{
				if(x < 0 || x > 3)
					return false;
				while(x < 3 && !(horizontalIsOpen(addition)))
				{
					x++;
				}
				if(x >= 4)
					return false;
				addition.setX(x);
				if(horizontalIsOpen(addition))
				{
					lot.add(addition);
					b[y][x] = 1;
					b[y][x+1] = 2;
					b[y][x+2] = 3;
					return true;
				}
				return false;
			}
			else
			{
				if(y < 0 || y > 3)
					return false;
				while(y < 4 && !(verticalIsOpen(addition)))
				{
					y++;
				}
				if(y >= 4)
					return false;
				if(verticalIsOpen(addition))
				{
					addition.setY(y);
					lot.add(addition);
					b[y][x] = 1;
					b[y+1][x] = 2;
					b[y+2][x] = 3;
					return true;
				}
				return false;
			}
		}
	}
	/**
	 * Checks if car's location is valid
	 * @param c : Vertical Car
	 * @return true if location's valid, false otherwise
	 */
	public boolean verticalIsOpen(Car c)
	{
		int y = c.getY();
		int x = c.getX();
		if(b[y][x] != 0)
			return false;
		else if(b[y + 1][x] !=0)
			return false;
		else
			return true;
	}
	/**
	 * Checks if truck's location is valid
	 * @param t : Vertical Truck
	 * @return true if location's valid, false otherwise
	 */
	public boolean verticalIsOpen(Truck t)
	{
		int y = t.getY();
		int x = t.getX();
		if(b[y][x] != 0)
			return false;
		else if(b[y+1][x] !=0)
			return false;
		else if(b[y+2][x]!=0)
			return false;
		else
			return true;
	}
	/**
	* Checks if car's location is valid
	* @param c : Horizontal Car
	* @return true if location's valid, false otherwise
	*/
	public boolean horizontalIsOpen(Car c)
	{
		int y = c.getY();
		int x = c.getX();
		if(y == 2)
			return false;
		if(b[y][x] != 0)
			return false;
		else if(b[y][x+1] !=0)
			return false;
		else
			return true;
	}
	/**
	 * Checks if truck's location is valid
	 * @param t : Horizontal Truck
	 * @return true if location's valid, false otherwise
	 */
	public boolean horizontalIsOpen(Truck t)
	{
		int y = t.getY();
		int x = t.getX();
		if(y == 2)
			return false;
		if(b[y][x] != 0)
			return false;
		else if(b[y][x+1] !=0)
			return false;
		else if(b[y][x+2] != 0)
			return false;
		else
			return true;
	}
	/**
	 * Inits vehicles on the board
	 * @see initRandom()
	 * @param a : amount of cars being added (not including red car)
	 */
	public void start(int a)
	{
		for(int j = 0; j < a; j++)
		{
			boolean worked = initRandom();
			if(!worked)
				j--;
		}
	}
	

	public Vehicle getVehicle(int index)
	{
		return lot.get(index);
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
	 * Finds the front of any vehicle in the 2D array
	 * @param r : row of vehicle
	 * @param c : column of vehicle
	 * @return correlating vehicle in arraylist 
	 */
	public Vehicle findFront(int r, int c)
	{
		if(b[r][c] == 0)
			return null;
		if(b[r][c] == 1)
			return getVehicleByHead(r, c);
		if(b[r][c] == 2)
		{
			if(r+1 < 6 && b[r+1][c] == 1 && getVehicleByHead(r+1, c).getDirection().equals("VERTICAL"))
				return getVehicleByHead(r+1, c);
			if(r-1 > 0 && b[r-1][c] == 1 && getVehicleByHead(r-1, c).getDirection().equals("VERTICAL"))
				return getVehicleByHead(r-1, c);
			if(c+1 < 6 && b[r][c+1] == 1 && getVehicleByHead(r, c+1).getDirection().equals("HORIZONTAL"))
				return getVehicleByHead(r, c+1);
			if(c-1 > 0 && b[r][c-1] == 1 && getVehicleByHead(r, c-1).getDirection().equals("HORIZONTAL"))
				return getVehicleByHead(r, c-1);
			System.out.println("This shouldn't happen");
			return null;
		}
		else
		{
			if(r+2 < 6 && b[r+2][c] == 1 && getVehicleByHead(r+2, c).getDirection().equals("VERTICAL"))
				return getVehicleByHead(r+2, c);
			if(r-2 > 0 && b[r-2][c] == 1 && getVehicleByHead(r-2, c).getDirection().equals("VERTICAL"))
				return getVehicleByHead(r-2, c);
			if(c+2 < 6 && b[r][c+2] == 1 && getVehicleByHead(r, c+2).getDirection().equals("HORIZONTAL"))
				return getVehicleByHead(r, c+2);
			if(c-2 > 0 && b[r][c-2] == 1 && getVehicleByHead(r, c-2).getDirection().equals("HORIZONTAL"))
				return getVehicleByHead(r, c-2);
			System.out.println("This shouldn't happen either");
			return null;
		}

			
	}

        /**
        * Moves vehicle at position (r, c)
        * @param r the row of the vehicle to be moved
        * @param c the column of the vehicle to be moved
        * @param spaces the number of spaces we want to move
        * @return true if move was successful, false otherwise
        */
    public boolean move(int r, int c, int spaces) 
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
                    if(spaces >= 1)
                    {
                        if(c + spaces + 1 >= b.length)
                        {
    	                    System.out.println("Your spaces are too high");
                            return false;
                        }
                    }
                    else
                    {
                        if(c + spaces < 0)
         	            {
                            System.out.println("Your spaces are too low");
            	            return false;
                        }
                    }
                    if(spaces >= 1)
                    {
						for (int i = c + 2; i <= c + spaces + 1; i++)
						{
							if (b[r][i] != 0)
							{
								System.out.println("Vehicle exists in your way");
								return false;
							}
						}
                    }
                    else
                    {
                        for(int i = c - 1; i >= c + spaces; i--)
                        {
                            if(b[r][i] != 0)
                            { 
                                System.out.println("Vehicle exists in your way");
                                return false;
                            }
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
                    if(spaces >= 1)
                    {
                        if(r + spaces + 1 >= b.length)
                        {
                            System.out.println("Your spaces are too high");
                            return false;
                        }
                    }
            	    else
                    {
                        if(r + spaces < 0)
                        {
                            System.out.println("Your spaces are too low");
                            return false;
                        }
                    }
			       if(spaces >= 1)
                    {
						for (int i = r + 2; i <= r + spaces + 1; i++)
						{
							if (b[i][c] != 0)
                            {
								System.out.println("Vehicle exists in your way");
								return false;
							}
						}
                    }
                    else
                    {
         	           for(int i = r - 1; i >= r + spaces; i--)
                       {
                            if(b[i][c] != 0)
                            {
                                System.out.println("Vehicle exists in your way");
                                return false;
                            }
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
			        if(spaces >= 1)
                    {
                       if(c + spaces + 2 >= b.length)
                        {
                            System.out.println("Your spaces are too high");
                            return false;
                        }
                    }
                    else
                    {
                        if(c + spaces < 0)
                        {
                            System.out.println("Your spaces are too low");
                            return false;
                        }
                    }
                    if(spaces >= 1)
                    {
						for (int i = c + 3; i <= c + spaces + 2; i++)
						{
							if (b[r][i] != 0)
							{
								System.out.println("Vehicle exists in your way");
								return false;
							}
						}
                    }
                    else
                    {
                        for(int i = c - 1; i >= c + spaces; i--)
                        {
                            if(b[r][i] != 0)
                            {
								System.out.println("Vehicle exists in your way");
								return false;
                            }
    					}
                    }
					b[r][c] = 0;
					b[r][c + 1] = 0;
					b[r][c + 2] = 0;
					b[r][c + spaces] = 1;
					b[r][c + spaces + 1] = 2;
					b[r][c + spaces + 2] = 3;
					((Truck) v).setX(c + spaces);
					return true;
				}
				else
				{
			        if(spaces >= 1)
                    {
                        if(r + spaces + 2 >= b.length)
                        {
                            System.out.println("Your spaces are too high");
                            return false;
                        }
                    }
                    else
                    {
                        if(r + spaces < 0)
                        {
                            System.out.println("Your spaces are too low");
                            return false;
                        }
                    }
                    if(spaces >= 1)
                    {
						for (int i = r + 3; i <= r + spaces + 2; i++)
						{
							if (b[i][c] != 0)
							{
								System.out.println("Vehicle exists in your way");
								return false;
							}
						}
                    }
                    else
                    {
 	                    for(int i = r - 1; i >= r + spaces; i--)
                        {
                            if(b[i][c] != 0)
                            {
    	                        System.out.println("Vehicle exists in your way");
                                return false;
                            }
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
		return false;
	}
}
