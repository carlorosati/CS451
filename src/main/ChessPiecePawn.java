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
	public ChessPiecePawn(Color color, ImageIcon representation, int x, int y)
	{
		super(color, representation, x, y);
	}
	
	@Override
	public void getPath(ChessBoard board, int x, int y)
	{
		List<ChessSquare> path = new ArrayList<>();
		
		if (this.x == x && y == this.y-1)
		{
			path.add(board.getChessSquare(this.x, this.y));
			path.add(board.getChessSquare(x, y));
		}
		
		validatePath(path, x, y);
	}
}
