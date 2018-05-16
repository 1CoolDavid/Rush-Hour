import java.util.ArrayList;

public class Board {
	boolean[][] b;
	ArrayList<Vehicle> vehicles;
	int manyCars;
	
	public Board() {
		b = new boolean[6][6]; //standard board size 6x6
		vehicles = new ArrayList<Vehicle>();
		init(); //standard amount of cars.
	}
	/*public Board(int size) {
		b = new boolean[size][size]; //creates custom size board
		vehicles = new ArrayList<Vehicle>();
		init(); //standard amount of cars
	}
	public Board(int size, int amount) {
		b = new boolean[size][size]; //Creates custom size board
		vehicles = new ArrayList<Vehicle>();
		init(amount); //how many cars user wants
	}
	*/ //Only doing basic board for now

	/**
	 * @return length of height (and width if square)
	 */
	public int getDimensions() {
		return b.length;
	}

	/**
	 * Checks if all coordinates of object Vehicle is valid
	 * @param Truck/Car (Vehicle)
	 * @return boolean: true if has valid coordinates, false otherwise
	 * @see public void place(Vehicle v){...} for usage
	 */
	public boolean isntTaken(Car a)
	{
		int x = a.getX();
		int y = a.getY();
		if(a.getDirection().equals("VERTICAL"))
		{
			for(int i = y; i<a.getSize(); i++)
			{
			   if(b[i][x] == true)
				return false;
			}
			return true;
		}
		else{
			for(int i = x; i<a.getSize(); i++)
			{
				if(b[y][i] == true){
					return false;
				}
			}
			return true;
		}
			
	}

	public boolean isntTaken(Truck t)
	{
		int x = t.getX();
		int y = t.getY();
		if(t.getDirection().equals("VERTICAL"))
		{
			for(int i = y; i<t.getSize(); i++)
			{
			   if(b[i][x] == true)
				return false;
			}
			return true;
		}
		else{
			for(int i = x; i<t.getSize(); i++)
			{
				if(b[y][i] == true){
					return false;
				}
			}
			return true;
		}
			
	}
	
	/**
	 * Places vehicle in board 2DArray after verifying coordinates (sets coordinates to true)
	 * @param Car
	 * @see public void init(){...} for usage 
	 */
	public void place(Car a)
	{
		if(!isntTaken(a))
		{
			return;           
		}
		if(a.getDirection().equals("VERTICAL"))
		{
			for(int i = a.getY(); i < a.getSize(); i++)
			{
				b[i][a.getX()] = true; 		   	
			}
		}
		else
		{	
			for(int i = a.getX(); i < a.getSize(); i++)
			{
				b[a.getY()][i] = true;
			}
		}
	}

	public void place(Truck t)
	{
		if(!isntTaken(t))
		{
			return;           
		}
		if(t.getDirection().equals("VERTICAL"))
		{
			for(int i = t.getY(); i < t.getSize(); i++)
			{
				b[i][t.getX()] = true; 		   	
			}
		}
		else
		{	
			for(int i = t.getX(); i < t.getSize(); i++)
			{
				b[t.getY()][i] = true;
			}
		}
	}
	
	/**
	 * Creates an Arraylist randomly filled with Vehicles
	 * Places objects into the board
	 * @see full parameter checking with methods public void place(Vehicle v){...} 
	 * and public boolean isntTaken(Vehicle aVehicle){...}   
	 */
	public void init(){
		manyCars = getDimensions();
		for(int c = 0; c < 5; c ++){
			//creates random coordinates and size
			int size = (int)(Math.random()*2); //0 or 1
			int posx = (int)(Math.random()*(manyCars+1));
            int posy = (int)(Math.random()*(manyCars+1));
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
		{
			if(v.getDirection() == "VERTICAL")
			{
				if(v.getY() >= 0 && v.getY() < manyCars - 1)
				{
					if(v.getClass().getSimpleName().equals("Car"))
					   place((Car)v);
					else
					   place((Truck)v);
				}	
			}
			else
			{
				if(v.getX() >= 0 && v.getX() < manyCars - 1)
				{
					if(v.getClass().getSimpleName().equals("Car"))
						place((Car)v);
					else
						place((Truck)v);
				}
			}
			   
		}
	}

	/*
	public void init(int a){
		manyCars = getDimensions();
		//incorporate car creation later
		
	}
	*/ //maybe later if we do more than the standard
}
