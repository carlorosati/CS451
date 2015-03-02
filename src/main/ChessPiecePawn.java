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

	public ChessPiecePawn(Color color, ImageIcon representation, int x, int y)
	{
		super(color, representation, x, y, "P");
	}
	
	@Override
	public List<ChessSquare> getPath(ChessBoard board, int x, int y)
	{
		List<ChessSquare> path = new ArrayList<>();
		
		//Check to see if pawns can only move forward
		if (this.x == x && y == this.y-1)
			path.add(board.getChessSquare(x, y));
		
		//Check to see if pawn is attempting to capture another piece
		//If the pawn moves diagonal, it must be capturing a piece.  First check to see if the destination square is empty and if it is not, then check to make sure the color of the piece on that square is opposite of the pawn's color
		else if (Math.abs(this.x - x) == 1 && Math.abs(this.y - y) == 1 && !board.getChessSquare(x, y).isEmpty() && board.getChessSquare(x, y).getChessPiece().getColor() != this.color)
			path.add(board.getChessSquare(x, y));
		
		return path;
	}
}
