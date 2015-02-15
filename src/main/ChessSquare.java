/**
 * This class represents the chess squares that make up the chess board.  Each chess square may or may not contain a chess piece.
 */

package main;

public class ChessSquare
{
	//INSTANCE VARIABLES
	private ChessPiece piece;	//Chess piece that may or may not be contained on this class square.  If no chess piece on this square exists, its 
								//value will be set to null.
	
	public ChessSquare()
	{
		piece = null;
	}
	
	public ChessPiece getChessPiece()
	{
		return piece;
	}
	
	public void setChessPiece(ChessPiece piece)
	{
		this.piece = piece;
	}
	
	/**
	 * This method checks to see whether or not this specific instance of the chess square contains a chess piece of not.
	 */
	public boolean isEmpty()
	{
		return (getChessPiece() == null);
	}
}
