import java.awt.*;
import javax.swing.*;

public class Graphic 
{
	public static void main(String[] arg)
	{
		JFrame frame = new JFrame("Grid Layout");
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(300, 400);
		JButton select = new JButton();
		//JPanel panel = new JPanel();
		select.setLayout(new GridLayout(6, 6, 5, 5));
		select.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		Board board = new Board();
		board.start(5);
		int[][] b = board.getBoard();
		for(int r = 0; r < 6; r++)
		{
			for(int c = 0; c < 6; c++)
			{
				if(b[r][c] == 0)
				{
					JButton button = new JButton("0");
					select.add(button);
				}
				else if(b[r][c] == 1)
				{
					JButton button = new JButton("1");
					select.add(button);
				}
				else if(b[r][c] == 2)
				{
					JButton button = new JButton("2");
					select.add(button);
				}
				else if(b[r][c] == 3)
				{
					JButton button = new JButton("3");
					select.add(button);
				}
			}
		}
		frame.add(select);
	}
	
}
