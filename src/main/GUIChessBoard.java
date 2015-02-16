package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

public class GUIChessBoard extends JPanel implements MouseListener
{
	/**
	 * STANDARD JAVA CONVENTION
	 */
	private static final long serialVersionUID = 1L;
	
	//CONSTANT
	private static final int SQUARE_SIZE = 48;

	public GUIChessBoard()
	{
	}

	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		this.addMouseListener(this);
		g.setColor(Color.BLUE);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		drawSquares(g);
	}
	
	private void drawSquares(Graphics g)
	{
		int x = 0; int y = 0; int turn = 0;
		for (int i = 0; i < 8; i++)
		{
			for (int j = 0; j < 8; j++)
			{
				if (turn++ % 2 == 0)
					g.setColor(Color.BLACK);
				else
					g.setColor(Color.WHITE);
				
				g.fillRect(x, y, SQUARE_SIZE, SQUARE_SIZE);
				x += SQUARE_SIZE;
			}
			turn++;
			y += SQUARE_SIZE;
			x = 0;
		}
	}
	
	public int getCoordinate(int value)
	{
		for (int i = 0; i < 8; i++)
		{
			if (value > SQUARE_SIZE * i && value < SQUARE_SIZE * (i+1))
			{
				return i;
			}
		}
		return -999;
	}
	
	public void processInput(int x, int y)
	{
		if (x == -999 || y == -999)
			System.out.println("  INVALID INPUT AT (" + x + ", " + y + ")\n");
		else
			System.out.println("Clicked on chess square:  (" + x + ", " + y + ")");
	}

	@Override
	public void mouseClicked(MouseEvent e)
	{
		int x = getCoordinate(e.getX());
		int y = getCoordinate(e.getY());
		
		processInput(x, y);
	}


	@Override
	public void mouseEntered(MouseEvent e) 
	{

	}


	@Override
	public void mouseExited(MouseEvent e)
	{

	}


	@Override
	public void mousePressed(MouseEvent e)
	{

	}


	@Override
	public void mouseReleased(MouseEvent e)
	{

	}
}
