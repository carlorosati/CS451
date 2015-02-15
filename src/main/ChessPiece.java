/**
 * This class represents individual chess pieces that will be placed on the chess board.  There are several different types of chess pieces that will 
 * be used during the game- subclasses have been created for each specific type of chess piece and inherit from this base class.
 */

package main;

import java.awt.Color;
import java.awt.Image;

public abstract class ChessPiece
{
	//INSTANCE VARIABLES
	private Color color;			//Color of the chess piece
	private Image representation;	//Image representation each chess piece will have on the chess board
	private int x, y;				//Integer values representing the (x, y) position of the chess square that contains this piece on the chess board
	
	public ChessPiece(Color color, Image representation, int x, int y)
	{
		this.color = color;
		this.representation = representation;
		this.x = x;
		this.y = y;
	}
	
	public Color getColor()
	{
		return color;
	}
	
	public Image getRepresentation()
	{
		return representation;
	}
	
	public int getX()
	{
		return x;
	}
	
	public int getY()
	{
		return y;
	}
	
	/**
	 * This method accepts a new x and a new y value that will be used to replace this chess piece object's existing x and y values.  This method 
	 * will be called whenever a chess piece moves to a new (x, y) position on the chess board.
	 * @param x:  new x value that will update previous x value.
	 * @param y:  new y value that will update previous y value.
	 */
	public void updatePosition(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	/**
	 * This abstract method determines whether or not this specific type of chess piece can move to location (x, y) on the chess board
	 * @param board: board object used for movement checking
	 * @param x: (attempted) new x position on board
	 * @param y: (attempted) new y position on board
	 * @return: true or false value depending on whether or not this chess piece can move to position (x, y) on the chess board
	 */
	public abstract boolean isMoveValid(ChessBoard board, int x, int y);
}
