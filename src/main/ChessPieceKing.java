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
	public ChessPieceKing(Color color, ImageIcon representation, int x, int y)
	{
		super(color, representation, x, y);
	}
	
	@Override
	public void getPath(ChessBoard board, int x, int y)
	{
		List<ChessSquare> path = new ArrayList<>();
		
		//Kings can only move one square adjacent to their current position at a time
		if ((x == this.x - 1 || x == this.x || x == this.x + 1) && (y == this.y - 1 || y == this.y || y == this.y + 1))
			path.add(board.getChessSquare(x, y));
		
		validatePath(path, x, y);
	}
}