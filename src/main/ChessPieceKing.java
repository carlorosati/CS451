/**
 * This class represents the King chess piece.
 */

package main;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

public class ChessPieceKing extends ChessPiece
{
	/**
	 * STANDARD JAVA CONVENTION
	 */
	private static final long serialVersionUID = 1L;

	public ChessPieceKing(Color color, ImageIcon representation, int x, int y)
	{
		super(color, representation, x, y, "X");
		ImageIcon image;
		//Used to update piece, based on color and actual piece
		if (color.equals(Color.BLACK)){
			image = new ImageIcon( getClass().getResource("/resources/ChessPieces/blackKing.png") );
			this.representation = image;
		}else{
			image = new ImageIcon( getClass().getResource("/resources/ChessPieces/whiteKing.png") );
			this.representation = image;
		}
	}
	
	@Override
	public List<ChessSquare> getPath(ChessBoard board, int x, int y)
	{
		List<ChessSquare> path = new ArrayList<ChessSquare>();
		
		//Kings can only move one square adjacent to their current position at a time
		if (Math.abs(this.x - x) <=1 && Math.abs(this.y - y) <= 1)
			path.add(board.getChessSquare(x, y));
		
		return path;
	}
}