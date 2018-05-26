import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;


public class Graphic 
{
	private JFrame frame;
	private JToggleButton select;
	private ImageIcon red;
	private ImageIcon grey;
	private ImageIcon blue;
	private ImageIcon green;
	private ImageIcon orange;
	private ImageIcon purple;
	private ImageIcon brown;
	private Board board;

	public Graphic()
	{
		frame = new JFrame("Grid Layout");
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1000, 1000);
		select = new JToggleButton();
		red = new ImageIcon("red.jpg");
		grey = new ImageIcon("grey.jpg");
		blue = new ImageIcon("blue.jpg");
		green = new ImageIcon("green.jpg");
		orange = new ImageIcon("orange.jpg");
		purple = new ImageIcon("purple.jpg");
		brown = new ImageIcon("brown.jpg");
		//JPanel panel = new JPanel();
		select.setLayout(new GridLayout(6, 6, 5, 5));
		select.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		board = new Board();
		board.start(7);
		int[][] b = board.getBoard();
		for(int r = 0; r < 6; r++)
		{
			for(int c = 0; c < 6; c++)
			{
				if(b[r][c] == 0)
				{
					JToggleButton button = new JToggleButton("0", grey);
					select.add(button);
				}
				else if(b[r][c] != 0)
				{
					if(b[r][c] == 1 && board.getVehicleByHead(r, c).getColor().equals("red"))
					{
						JToggleButton button = new JToggleButton("1", red);
						select.add(button);
						JToggleButton button1 = new JToggleButton("2", red); 
						select.add(button1);
						c++;
					}
					else
					{
						if(b[r][c] == 1)
						{
							int z = -1;
							if(board.getVehicleByHead(r,c).getColor().equals("grey"))
								z = (int)(Math.random()*4) + 1;
							if(z == 1 || board.getVehicleByHead(r,c).getColor().equals("blue"))
							{
								JToggleButton button = new JToggleButton("1", blue);
								select.add(button);
								board.getVehicleByHead(r, c).setColor("blue");
								JToggleButton button1 = new JToggleButton("2", blue);
								select.add(button1);
								if( c< 4 && b[r][c+2] == 3)
								{
									JToggleButton button2 = new JToggleButton("3", blue);
									select.add(button2);
									c++;
								}
								c++;
							}
							else if(z == 2)
							{
								JToggleButton button = new JToggleButton("1", purple);
								select.add(button);
								JToggleButton button1 = new JToggleButton("2", purple);
								select.add(button1);
								if( c< 4 && b[r][c+2] == 3)
								{
									JToggleButton button2 = new JToggleButton("3", purple);
									select.add(button2);
									c++;
								}
								c++;
							}
							else if(z == 3)
							{
								JToggleButton button = new JToggleButton("1", green);
								select.add(button);
								JToggleButton button1 = new JToggleButton("2", green);
								select.add(button1);
								if( c< 4 && b[r][c+2] == 3)
								{
									JToggleButton button2 = new JToggleButton("3", green);
									select.add(button2);
									c++;
								}
								c++;
							}
							else
							{
								JToggleButton button = new JToggleButton("1", brown);
								select.add(button);
								JToggleButton button1 = new JToggleButton("2", brown);
								select.add(button1);
								if( c< 4 && b[r][c+2] == 3)
								{
									JToggleButton button2 = new JToggleButton("3", brown);
									select.add(button2);
									c++;
								}
								c++;
							}
						}
						else if (b[r][c] == 4 || b[r][c] == 5 || b[r][c] == 6)
						{
							String num;
							if(b[r][c] == 4)
								num = "4";
							else if(b[r][c] == 5)
								num = "5";
							else
							{
								num = "6";
							}
							JToggleButton button = new JToggleButton(num, orange);
							select.add(button);
						}
						
					}
				}
			}
		}
		frame.add(select);
	}
	public static void main(String[] arg)
	{
		Graphic gui = new Graphic();
	}
	
}
