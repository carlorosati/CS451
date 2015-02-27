/**
 * This class represents the chess squares that make up the chess board.  Each chess square may or may not contain a chess piece.
 */

package main;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

public class ChessSquare extends JPanel implements MouseListener
{
	/**
	 * STANDARD JAVA CONVENTION
	 */
	private static final long serialVersionUID = 1L;
	
	//INSTANCE VARIABLES
	private ChessPiece piece;	//Chess piece that may or may not be contained on this class square.  If no chess piece on this square exists, its 
								//value will be set to null.
	public static final int SQUARE_SIZE = 64;
	private Color color;
	private int pixX, pixY;		//Starting pixel coordinates of x and y for this particular chess square object
	
	public ChessSquare(Color color, int pixX, int pixY)
	{
		this.color = color;
		piece = null;
		this.pixX = pixX;
		this.pixY = pixY;
	}
	
	public ChessPiece getChessPiece()
	{
		return piece;
	}
	
	public void setChessPiece(ChessPiece piece)
	{
		this.piece = piece;
	}
	
	public Color getColor()
	{
		return color;
	}
	
	/**
	 * This method checks to see whether or not this specific instance of the chess square contains a chess piece of not.
	 */
	public boolean isEmpty()
	{
		return (getChessPiece() == null);
	}
	
	public int getPixX()
	{
		return pixX;
	}
	
	public int getPixY()
	{
		return pixY;
	}

	@Override
	public void mouseClicked(MouseEvent e)
	{
		
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
