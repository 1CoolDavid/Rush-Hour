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
		if(x < 0 || x > getDimensions()-c.getSize() || y < 0 || y > getDimensions()-c.getSize())
			return false;
		if(c.getDirection().equals("VERTICAL"))
		{
			for(int i = 0; i < c.getSize(); i++)
			{
				if(b[i+y][x] == 1 || b[i+y][x] == 2 || b[i+y][x] == 3 || (i+y == 2 && (x == 0 || x == 1)))
				   return false;
			}
		}
		else
		{
			for(int i = 0; i < c.getSize(); i++)
			{
				if(b[y][x+i] == 1 || b[y][x+i] == 2 || b[y][x+i] == 3 || (i+x == 2 && (x == 0 || x == 1)))
				   return false;
			}
		}
		return true;
	}

        public boolean isOpen(Truck t)
	{
		int x = t.getX();
		int y = t.getY();
		if(x < 0 || x > getDimensions() - t.getSize() || y < 0 || y > getDimensions() - t.getSize())
			return false;
		if(t.getDirection().equals("VERTICAL"))
		{
			for(int i = 0; i < t.getSize(); i++)
			{
				if(b[i+y][x] == 1 || b[i+y][x] == 2 || b[i+y][x] == 3 || (i+y == 2 && (x == 0 || x == 1)))
				   return false;
			}
		}
		else
		{
			for(int i = 0; i < t.getSize(); i++)
			{
				if(b[y][x+i] == 1 || b[y][x+i] == 2 || b[y][x+i] == 3 || (i+x == 2 && (x == 0 || x == 1)))
				   return false;
			}
		}
		return true;
	}

	public boolean add(Car c)
	{
		if(c.getDirection().equals("VERTICAL")){
			int i = 0;
			while(!(isOpen(c)) && i<getDimensions())
			{
				c = new Car(i++,c.getX());
			}
			if(i >= getDimensions())
				return false;
			lot.add(c);
			for(int j = 0; j < c.getSize(); j++)
			{
				b[j+c.getY()][c.getX()] = j+1;
			}
			return true;
		}
		else
		{
			int i = 0;
			while(!(isOpen(c)) && i<getDimensions())
			{
				c = new Car(c.getY(), i++);
			}
			if(i >= getDimensions())
				return false;
			lot.add(c);
			for(int j = 0; j < c.getSize(); j++)
			{
				b[c.getY()][j+c.getX()] = j+1;
			}
			return true;
		}
		
	}

	public boolean add(Truck t)
	{
		if(t.getDirection().equals("VERTICAL")){
			int i = 0;
			while(!(isOpen(t)) && i<getDimensions())
			{
				t = new Truck(i++,t.getX());
			}
			if(i >= getDimensions())
				return false;
			lot.add(t);
			for(int j = 0; j < t.getSize(); j++)
			{
				b[j+t.getY()][t.getX()] = j+1;
			}
			return true;
		}
		else
		{
			int i = 0;
			while(!(isOpen(t)) && i<getDimensions())
			{
				t = new Truck(t.getY(), i++);
			}
			if(i >= getDimensions())
				return false;
			lot.add(t);
			for(int j = 0; j < t.getSize(); j++)
			{
				b[t.getY()][j+t.getX()] = j+1;
			}
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
        * @param x the x-coordinate of the head of the vehicle
        * @param y the y-coordinate of the head of the vehicle
        * @see move
        * @return vehicle with head at (x, y)
        */ 
        public Vehicle getVehicleByHead(int x, int y)
        {
         for(Vehicle v: lot)
         {
          if(v.getX() == x && v.getY() == y)
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
        public boolean move(int r, int r, int spaces) //TODO: Add support for negative values
        {
         if(spaces == 0) //Not moving at all, always successful
          return true;
         if(b[r][c] == 0) //Vehicle doesn't exist at index
          return false;
         if(b[r][c] == 1) //The index is the head of the vehicle
         {
          Vehicle v = getVehicleByHead(x, y);
          if(v == null)
           return false;
          if(v.getClass().simpleName().equals("Car"))
          {
           if(v.getDirection().equals("HORIZONTAL")
           {
            if(spaces >= b[0].length - c) //Spaces is greater than distance between column index and end
              return false;
            b[r][c] = 0;
            b[r][c + 1] = 0;
            b[r][c + spaces] = 1;
            b[r][c + spaces - 1] = 0;
           }
          } 
         }
         return false; 
        }
}

