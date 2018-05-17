
public class Board {
	private boolean[][] b;
	private int manyCars;
	
	public Board() {
		b = new boolean[6][6]; //standard board size 6x6
		b[2][0] = true;
		b[2][1] = true;
	}
	/**
	 * @return length of height (and width if square)
	 */
	public int getDimensions() {
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
				if(b[i+y][x] == true)
				   return false;
			}
		}
		else
		{
			for(int i = 0; i < c.getSize(); i++)
			{
				if(b[y][i+x] == true)
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
				if(b[y+i][x] == true)
				   return false;
			}
		}
		else
		{
			for(int i = 0; i < t.getSize(); i++)
			{
				if(b[y][i+x] == true)
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
			for(int j = 0; j < c.getSize(); j++)
			{
				b[j+c.getY()][c.getX()] = true;
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
			for(int j = 0; j < c.getSize(); j++)
			{
				b[c.getY()][j+c.getX()] = true;
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
			for(int j = 0; j < t.getSize(); j++)
			{
				b[j+t.getY()][t.getX()] = true;
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
			for(int j = 0; j < t.getSize(); j++)
			{
				b[t.getY()][j+t.getX()] = true;
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
}

