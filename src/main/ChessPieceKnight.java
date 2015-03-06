/**
 * This class represents the Knight chess piece.
 */

package main;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

public class ChessPieceKnight extends ChessPiece
{
	/**
	 * STANDARD JAVA CONVENTION
	 */
	private static final long serialVersionUID = 1L;

	public ChessPieceKnight(Color color, ImageIcon representation, int x, int y)
	{
		super(color, representation, x, y, "K");
		ImageIcon image;
		//Used to update piece, based on color and actual piece
		if (color == Color.BLACK){
			image = new ImageIcon( getClass().getResource("/resources/ChessPieces/blackKnight.png") );
			this.representation = image;
		}else{
			image = new ImageIcon( getClass().getResource("/resources/ChessPieces/whiteKnight.png") );
			this.representation = image;
		}
	}
	
	/*@Override
	public boolean validatePath(List<ChessSquare> path, int x, int y)
	{
		//Path will only contain one chess square
		if (path.get(0).isEmpty() || (!path.get(0).isEmpty() && path.get(0).getChessPiece().getColor() != this.color))
			return true;	//We can move here if the chess square is empty or if the chess square contains an enemy piece
		return false;		//If this return statement is reached, we know there is an ally piece here, so we cannot move here
	}*/
	
	@Override
	public List<ChessSquare> getPath(ChessBoard board, int x, int y)
	{
		List<ChessSquare> path = new ArrayList<>();
		
		if (this.x != x && this.y != y && Math.abs(this.x -x) + Math.abs(this.y - y) == 3)
			path.add(board.getChessSquare(x, y));
		
		return path;
	}
}
