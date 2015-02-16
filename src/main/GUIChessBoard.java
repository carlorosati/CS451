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
		this.addMouseListener(this);	//Add a mouse listener to this panel so we can interact with the user
		g.setColor(Color.BLUE);			//Make the background blue
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		drawSquares(g);
	}
	
	private void drawSquares(Graphics g)
	{
		int x = 0; int y = 0; int turn = 0;		//Use the turn variable to determine whether or not the next square drawn should be black or white
		for (int i = 0; i < 8; i++)
		{
			for (int j = 0; j < 8; j++)
			{
				//Use this if statement to alternate colors
				if (turn++ % 2 == 0)
					g.setColor(Color.BLACK);
				else
					g.setColor(Color.WHITE);
				
				g.fillRect(x, y, SQUARE_SIZE, SQUARE_SIZE);
				x += SQUARE_SIZE;	//Increment the x coordinate to the proper location to draw the next square
			}
			turn++;		//Increment turn to ensure that the first square drawn on the new line is the opposite color as the previous square drawn
			y += SQUARE_SIZE;		//Increment the y coordinate to the proper location to draw the next square on the new line
			x = 0;		//Set the x coordinate to 0 since we are drawing squares starting on a new line
		}
	}
	
	
	/** The logic stored in this method will determine which chess square the user clicked on.  This method uses the x and y values of the user's 
	 *  last "click" to determine which chess square is accessed.
	 *  @param value: a coordinate
	 *  @return: the appropriate chess square or -999 if the user clicked on a portion of the frame that did not include any of the chess board
	 */
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
