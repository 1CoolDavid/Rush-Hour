import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


import java.util.*;

import javax.swing.*;
@SuppressWarnings("serial")
public class RushButton extends JButton implements ActionListener, KeyListener
{
    private ArrayList<Vehicle> track;
    private boolean selected;
    private int rows;
    private int cols;
    private ImageIcon grey;
	private ImageIcon blue;
	private ImageIcon green;
	private ImageIcon orange;
	private ImageIcon purple;
	private ImageIcon brown;
    public RushButton(String str, Icon i, int r, int c)
    {
        super(str, i);
        selected = false;
        rows = r;
        cols = c;
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        addActionListener(this);
        addKeyListener(this);
    }
    public static final long SerialVersionUID = 3;

    public void actionPerformed(ActionEvent e)
    {
        selected = !selected;
    }

    public void keyPressed(KeyEvent e)
    {
        Graphic gui;
        int[][] b = Graphic.board.getBoard();
        if(selected = true && b[rows][cols] == 1)
        {
            int c = e.getKeyCode();
            if(c == KeyEvent.VK_LEFT && Graphic.board.getVehicleByHead(rows, cols).getDirection().equals("HORIZONTAL"))
            {
                if(Graphic.board.move(rows, cols, -1))
                    gui = new Graphic(Graphic.layout);
            }
            else if(c == KeyEvent.VK_RIGHT && Graphic.board.getVehicleByHead(rows, cols).getDirection().equals("HORIZONTAL"))
            {
                if(Graphic.board.move(rows, cols, 1))
                    gui = new Graphic(Graphic.layout);
            }
            else if(c == KeyEvent.VK_UP && Graphic.board.getVehicleByHead(rows, cols).getDirection().equals("VERTICAL"))
            {
                if(Graphic.board.move(rows, cols, 1))
                    gui = new Graphic(Graphic.layout);
            }
            else if(c == KeyEvent.VK_DOWN && Graphic.board.getVehicleByHead(rows, cols).getDirection().equals("VERTICAL"))
            {
                if(Graphic.board.move(rows, cols, -1))
                    gui = new Graphic(Graphic.layout);
            }
        }
    }
    public void keyReleased(KeyEvent e)
    {

    }
    public void keyTyped(KeyEvent e)
    {

    }

}