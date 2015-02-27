/**
 * This class represents the Rook chess piece.
 */

package main;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

public class ChessPieceRook extends ChessPiece
{
	public ChessPieceRook(Color color, ImageIcon representation, int x, int y)
	{
		super(color, representation, x, y);
	}
	
	@Override
	public void getPath(ChessBoard board, int x, int y)
	{
		List<ChessSquare> path = new ArrayList<>();
		
		if (this.x == x && this.y != y)
		{
			//x is fixed, y is changing
			//If current y is less than new y, then we know we are moving down
			if (this.y < y)
			{
				for (int i = this.y; i <= y; i++)
					path.add(board.getChessSquare(x, i));
			}
			//Otherwise we are moving up
			else
			{
				for (int i = this.y; i >= y; i--)
					path.add(board.getChessSquare(x, i));
			}
		}
		if (this.x != x && this.y == y)
		{
			//y is fixed, x is changing
			//If current x is less than new x, then we know we are moving right
			if (this.x < x)
			{
				for (int i = this.x; i <= x; i++)
					path.add(board.getChessSquare(i, y));
			}
			//Otherwise we are moving left
			else
			{
				for (int i = this.y; i >= x; i--)
					path.add(board.getChessSquare(i, y));
			}
		}
		
		validatePath(path, x, y);
	}
}
