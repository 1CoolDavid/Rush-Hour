import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;


public class Graphic extends JFrame
{
	private JPanel select;
	private ImageIcon red;
	private ImageIcon grey;
	private ImageIcon blue;
	private ImageIcon green;
	private ImageIcon orange;
	private ImageIcon purple;
	private ImageIcon brown;
	public static Board board;
	public static RushButton[][] layout;

	public Graphic()
	{
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setFocusable(true);
		setSize(400, 400);
		layout = new RushButton[6][6];
		select = new JPanel();
		red = new ImageIcon("red.jpg");
		grey = new ImageIcon("grey.jpg");
		blue = new ImageIcon("blue.jpg");
		green = new ImageIcon("green.jpg");
		orange = new ImageIcon("orange.jpg");
		purple = new ImageIcon("purple.jpg");
		brown = new ImageIcon("brown.jpg");
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
					RushButton button = new RushButton("0", grey, r, c);
					layout[r][c] = button;
					select.add(button);
				}
				else if(b[r][c] != 0)
				{
					if(b[r][c] == 1 && board.getVehicleByHead(r, c).getColor().equals("red"))
					{
						board.getVehicleByHead(r, c).setColor("red");
						RushButton button = new RushButton("1", red, r, c);
						layout[r][c] = button;
						select.add(button);
						RushButton button1 = new RushButton("2", red, r, c+1);
						layout[r][c+1] = button1; 
						c++;
						select.add(button1);
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
								board.getVehicleByHead(r, c).setColor("blue");
								RushButton button = new RushButton("1", blue, r, c);
								layout[r][c] = button;
								select.add(button);
								board.getVehicleByHead(r, c).setColor("blue");
								RushButton button1 = new RushButton("2", blue, r, c+1);
								layout[r][c+1] = button1;
								select.add(button1);
								layout[r][c] = button1;
								if( c< 4 && b[r][c+2] == 3)
								{
									RushButton button2 = new RushButton("3", blue, r, c+2);
									select.add(button2);
									layout[r][c+2] = button2;
									c++;
								}
								c++;
							}
							else if(z == 2 || board.getVehicleByHead(r, c).getColor().equals("orange"))
							{
								board.getVehicleByHead(r, c).setColor("orange");
								RushButton button = new RushButton("1", orange, r, c);
								layout[r][c] = button;
								select.add(button);
								RushButton button1 = new RushButton("2", orange, r, c+1);
								layout[r][c+1] = button1;
								select.add(button1);
								if( c< 4 && b[r][c+2] == 3)
								{
									RushButton button2 = new RushButton("3", orange, r, c+2);
									layout[r][c+2] = button2;
									select.add(button2);
									c++;
								}
								c++;
								
							}
							else if(z == 3 || board.getVehicleByHead(r, c).getColor().equals("green"))
							{
								board.getVehicleByHead(r, c).setColor("green");
								RushButton button = new RushButton("1", green, r, c);
								select.add(button);
								layout[r][c] = button;
								RushButton button1 = new RushButton("2", green, r, c+1);
								layout[r][c+1] = button1;
								select.add(button1);
								if( c< 4 && b[r][c+2] == 3)
								{
									RushButton button2 = new RushButton("3", green, r, c+2);
									layout[r][c+2] = button2;
									select.add(button2);
									c++;
								}
								c++;
							}
							else
							{
								board.getVehicleByHead(r, c).setColor("brown");
								RushButton button = new RushButton("1", brown, r, c);
								layout[r][c] = button;
								select.add(button);
								RushButton button1 = new RushButton("2", brown, r, c+1);
								layout[r][c+1] = button1;
								select.add(button1);
								if( c< 4 && b[r][c+2] == 3)
								{
									RushButton button2 = new RushButton("3", brown, r, c+2);
									layout[r][c+2] = button2;
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
							{
								num = "4";
								board.getVehicleByHead(r, c).setColor("purple");
							}
							else if(b[r][c] == 5)
								num = "5";
							else
							{
								num = "6";
							}
							RushButton button = new RushButton(num, purple, r, c);
							layout[r][c] = button;
							select.add(button);
						}
						
					}
				}
			}
		}
		add(select);
		setVisible(true);
	}

	public Graphic(RushButton[][] btn)
	{
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setFocusable(true);
		setSize(400, 400);
		layout = new RushButton[6][6];
		select = new JPanel();
		red = new ImageIcon("red.jpg");
		grey = new ImageIcon("grey.jpg");
		blue = new ImageIcon("blue.jpg");
		green = new ImageIcon("green.jpg");
		orange = new ImageIcon("orange.jpg");
		purple = new ImageIcon("purple.jpg");
		brown = new ImageIcon("brown.jpg");
		select.setLayout(new GridLayout(6, 6, 5, 5));
		select.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		select = new JPanel();
		for(int r = 0; r<6; r++)
		{
			for(int c = 0; c<6; c++)
			{
				select.add(btn[r][c]);
			}
		}
		add(select); 
	}

	public static void main(String[] arg)
	{
		Graphic gui = new Graphic();
	}
	
}
