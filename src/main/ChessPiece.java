/**
 * This class represents individual chess pieces that will be placed on the chess board.  There are several different types of chess pieces that will 
 * be used during the game- subclasses have been created for each specific type of chess piece and inherit from this base class.
 */

package main;

import java.awt.Color;
import java.io.Serializable;
import java.util.List;

import javax.swing.ImageIcon;

public abstract class ChessPiece implements Serializable
{
	/**
	 * STANDARD JAVA CONVENTION
	 */
	private static final long serialVersionUID = 1L;
	
	//INSTANCE VARIABLES
	protected Color color;			//Color of the chess piece
	protected ImageIcon representation;	//Image representation each chess piece will have on the chess board
	protected int x, y;				//Integer values representing the (x, y) position of the chess square that contains this piece on the chess board
	protected String rep;
	
	public ChessPiece(Color color, ImageIcon representation, int x, int y, String rep)
	{
		this.color = color;
		this.representation = representation;
		this.x = x;
		this.y = y;
		this.rep = rep;
	}
	
	public String getRep()
	{
		if(this.getColor().equals(Color.BLACK))
			return rep;
		else
			return rep.toLowerCase();
		
	}
	
	public Color getColor()
	{
		return color;
	}
	
	public ImageIcon getRepresentation()
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
	public boolean validatePath(List<ChessSquare> path, int x, int y)
	{
		if (path.isEmpty())
			return false;
		
		if (x == -999 || y == -999 || (this.x == x && this.y == y))
			return false;
		
		for (ChessSquare square : path)
		{
			if (!square.isEmpty() && square != path.get(path.size() - 1))
				return false;
			if (!square.isEmpty() && square.getChessPiece().getColor().equals(this.color) && square == path.get(path.size()-1))
				return false;
		}
		
		return true;
		
		
		/* TODO: GENERIC PSEUDOCODE FOR ANY PIECE'S MOVE
		 * 
		 * foreach tile in path (path is defined as the chess squares the piece must traverse to get to its destination)
		 *     if (tile is occupied and tile is not the destination tile)
		 *         return false (basically, continue through the loop as long as the current tile is empty and is not the destination)
		 *     if (tile is destination tile and tile contains an ALLY piece)
		 *         return false (if the current tile is the destination tile, we know that the path leading up to this tile is empty since the loop 
		 *                       checks to make sure of this.  If the destination contains an ally piece (same color piece), we cannot move here)
		 * return true (we know once we get here that the tiles leading up to the destination tile are empty and that the destination tile itself is 
		 *              either empty or contains an enemy piece- if the destination tile contains an enemy piece we will capture it and update this 
		 *              piece's position.  If the destination tile is empty, we will simply update this piece's position since obviously no capture 
		 *              will be involved in this case)
		 */
	}
	
	public abstract List<ChessSquare> getPath(ChessBoard board, int x, int y);
	
	public boolean move(ChessBoard board, int x, int y)
	{
		int oldx = getX();
		int oldy = getY();
		List<ChessSquare> path = getPath(board, x, y);
		System.out.println("VALIDATE PATH:  " + validatePath(path, x, y));
		if (validatePath(path, x, y))
		{
			ChessPiece p = board.getChessSquare(x, y).getChessPiece();
			board.update(this, x, y);
			if(!board.isCheck(getColor()))
				return true;
			else {
				board.update(this,oldx,oldy);
				if(p!=null)
					board.update(p, p.getX(), p.getY());
				return false;
			}
		}
		return false;
	}
}
