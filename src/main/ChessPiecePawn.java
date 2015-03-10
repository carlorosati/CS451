/**
 * This class represents the Pawn chess piece.
 */

package main;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

public class ChessPiecePawn extends ChessPiece
{
	/**
	 * STANDARD JAVA CONVENTION
	 */
	private static final long serialVersionUID = 1L;

	
	//Boolean variable representing whether or not the pawn has made its first move or not
	private boolean firstMove;
	
	public ChessPiecePawn(Color color, ImageIcon representation, int x, int y)
	{
		super(color, representation, x, y, "P");
		firstMove = true;
		ImageIcon image;
		//Used to update piece, based on color and actual piece
		if (color.equals(Color.BLACK)){
			image = new ImageIcon( getClass().getResource("/resources/ChessPieces/blackPawn.png") );
			this.representation = image;
		}else{
			image = new ImageIcon( getClass().getResource("/resources/ChessPieces/whitePawn.png") );
			this.representation = image;
		}
	}
	
	@Override
	public List<ChessSquare> getPath(ChessBoard board, int x, int y)
	{
		List<ChessSquare> path = new ArrayList<ChessSquare>();
		
		//Check to see if this is the first move of the game
		if (this.x == 1 && this.getColor().equals(Color.BLACK) && x - this.x == 2 && board.getChessSquare(x, y).isEmpty())
		{
			path.add(board.getChessSquare(x-1, y));
			path.add(board.getChessSquare(x, y));
		}
		else if (this.x == 6 && this.getColor().equals(Color.WHITE) && this.x - x == 2 &&board.getChessSquare(x, y).isEmpty())
		{
			path.add(board.getChessSquare(x+1, y));
			path.add(board.getChessSquare(x, y));
		}
		
		//Check first to see if a pawn is moving diagonal (it is attempting to capture another piece)
		else if (((this.color.equals(Color.WHITE) && x == this.x-1 && Math.abs(y - this.y) == 1) || (this.color.equals(Color.BLACK) && x == this.x+1 && Math.abs(y - this.y) == 1)) && !board.getChessSquare(x, y).isEmpty())
			path.add(board.getChessSquare(x, y));
		
		//If pawns are not attempting to capture an enemy piece, check to see if they can only move forward
		else if (((this.color.equals(Color.WHITE) && x == this.x-1 && y == this.y) || (this.color.equals(Color.BLACK) && x == this.x+1 && y == this.y)) && board.getChessSquare(x,  y).isEmpty())
			path.add(board.getChessSquare(x, y));
		
		//Check to see if pawn is attempting to capture another piece
		//If the pawn moves diagonal, it must be capturing a piece.  First check to see if the destination square is empty and if it is not, then check to make sure the color of the piece on that square is opposite of the pawn's color
		//else if (Math.abs(this.x - x) == 1 && Math.abs(this.y - y) == 1 && !board.getChessSquare(x, y).isEmpty() && board.getChessSquare(x, y).getChessPiece().getColor() != this.color)
			//path.add(board.getChessSquare(x, y));
		
		return path;
	}
	
	@Override
	public ImageIcon getRepresentation()
	{
		return representation;
	}
}
