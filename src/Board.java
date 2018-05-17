import java.util.ArrayList;

import javax.lang.model.util.ElementScanner6;

public class Board {
	private int[][] b;
	private int manyCars;
        private final int XGOAL = 3;
        private final int YGOAL = 3;	
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
			for(int i = y; i<a.getSize(); i++) 
                       {
			for(int j = 0; j<a.getSize(); j++)
			{
			   if(b[y+j][x] == true)
				return false;
			}
                       }
			return true;
	       	}
		else{
			for(int i = 0; i<a.getSize(); i++)
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
                      try //Using try catch because the coordinates are random and we don't want to go off the board
                      {
			for(int i = 0; i<t.getSize(); i++)
			{
			   if(b[y + i][x] == true)
				return false;
			}
			return true;
                      }
                      catch(Exception e)
                      {
                        return false;
                      }
		}
		else
                {
                       try
                       {
			for(int i = 0; i<t.getSize(); i++)
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
			return true;
                       }
                       catch(Exception e)
                       {
                         return false;
                       }
                       
		}
		return true;
	}

	public boolean add(Car c)
	{
		if(!isntTaken(a))
		{
			return;           
		}
		if(a.getDirection().equals("VERTICAL"))
		{
                      
			for(int i = 0; i < a.getSize(); i++)
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
	
	/**
	 * Creates an Arraylist randomly filled with Vehicles
	 * Places objects into the board
	 * see full parameter checking with methods public void place(Vehicle v){...}
	 * and public boolean isntTaken(Vehicle aVehicle){...}   
	 */
	public void init(){
		manyCars = getDimensions();
		for(int c = 0; c < 5; c ++){
			//creates random coordinates and size
			int size = (int)(Math.random()*2); //0 or 1
			int posx = (int)(Math.random()*(b.length));
            int posy = (int)(Math.random()*(b[0].length));
            if(size == 0) {
                Car a = new Car(posx, posy);
                vehicles.add(a);
            }
            else {
                Truck b = new Truck(posx, posy);
                vehicles.add(b);
            }
		}
		//loops through arraylist to verify and place objects with place(v) method
		for (Vehicle v : vehicles)

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
             
              System.out.println(vehicles.size()); //debugging
			   
	}
	//Won't exactly copy... TODO Work on private void place(...){...}
	private void place(Car a, int k)
	{
		int c = 0;
		if(a.getDirection().equals("VERTICAL"))
		{
			for(int j = 0; j<b.length; j++)
			 {
				 System.out.print(b[i][j] + " ");
			 }
			System.out.println();
			for(int i = a.getY(); i<a.getY()+1; i++)
			{
				b[i][a.getX()] = ++c;
			}
			lot.add(k, a);
		}
		else      
		{
			for(int i = a.getX(); i<a.getX()+1; i++)
			{
				b[a.getY()][i] = ++c;
			}	
			lot.add(k, a);
		}		
	}
	private void place(Truck t, int k)
	{
		int c = 0;
		if(t.getDirection().equals("VERTICAL"))
		{
			for(int i = t.getY(); i<t.getY() + 2; i++)
			{
				b[i][t.getX()] = ++c;
			}
			lot.add(k, t);
		}
		else      
		{
			for(int i = t.getX(); i<t.getX() + 2; i++)
			{
				b[t.getY()][i] = ++c;
				
			}	
			lot.add(k, t);
		}	
	}

	public Vehicle getVehicle(int index)
	{
		return lot.get(index);
	}
	public void update()
	{ //Currently doesn't work; Create method place. More static than the dynamic add. 
		Board b = new Board();
		for(int i = 0; i<lot.size(); i++)
		{
			if(lot.get(i).getClass().getSimpleName().equals("Car"))
			{
				Car addition = (Car)(lot.get(i));
				lot.remove(i);
				place(addition, i);
			}
			else
			{
				Truck addition = (Truck)(lot.get(i));
				lot.remove(i);
				place(addition, i);
			}
		}
	}  
}

