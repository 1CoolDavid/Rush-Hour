import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;


public class Graphic 
{
	private JFrame frame;
	private JButton select;
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
		select = new JButton();
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
		board.start(5);
		int[][] b = board.getBoard();
		for(int r = 0; r < 6; r++)
		{
			for(int c = 0; c < 6; c++)
			{
				if(b[r][c] == 0)
				{
					JButton button = new JButton("0", grey);
					select.add(button);
				}
				else if(b[r][c] != 0)
				{
					if(r == 2 && c == 0)
					{
						JButton button = new JButton("1", red);
						select.add(button);
						JButton button1 = new JButton("2", red); 
						select.add(button1);
						c++;
					}
					else
					{
						if(b[r][c] == 1 && c<5 && b[r][c+1] == 2)
						{
							select.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
							int z = (int)(Math.random()*4) + 1;
							if(z == 1)
							{
								JButton button = new JButton("1", blue);
								select.add(button);
								JButton button1 = new JButton("2", blue);
								select.add(button1);
								if( c< 4 && b[r][c+2] == 3)
								{
									JButton button2 = new JButton("3", blue);
									select.add(button2);
									c++;
								}
								c++;
							}
							else if(z == 2)
							{
								JButton button = new JButton("1", purple);
								select.add(button);
								JButton button1 = new JButton("2", purple);
								select.add(button1);
								if( c< 4 && b[r][c+2] == 3)
								{
									JButton button2 = new JButton("3", purple);
									select.add(button2);
									c++;
								}
								c++;
							}
							else if(z == 3)
							{
								JButton button = new JButton("1", green);
								select.add(button);
								JButton button1 = new JButton("2", green);
								select.add(button1);
								if( c< 4 && b[r][c+2] == 3)
								{
									JButton button2 = new JButton("3", green);
									select.add(button2);
									c++;
								}
								c++;
							}
							else
							{
								JButton button = new JButton("1", brown);
								select.add(button);
								JButton button1 = new JButton("2", brown);
								select.add(button1);
								if( c< 4 && b[r][c+2] == 3)
								{
									JButton button2 = new JButton("3", brown);
									select.add(button2);
									c++;
								}
								c++;
							}
						}
						else
						{
							String num;
							if(b[r][c] == 1)
								num = "1";
							else if(b[r][c] == 2)
								num = "2";
							else
							{
								num = "3";
							}
							JButton button = new JButton(num, orange);
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
