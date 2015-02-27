/**
 * This class represents the Queen chess piece.
 */

package main;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

public class ChessPieceQueen extends ChessPiece
{
	public ChessPieceQueen(Color color, ImageIcon representation, int x, int y)
	{
		super(color, representation, x, y);
	}

	@Override
	public void getPath(ChessBoard board, int x, int y)
	{
		List<ChessSquare> path = new ArrayList<>();

		//Queens behave as rooks and bishops
		//CHECK TO SEE IF QUEEN MOVED IN ROOK MOTION
		if (this.x == x && this.y != y)
			path = ChessPieceRook.getTiles(board, x, this.y, y);

		else if (this.x != x && this.y == y)
			path = ChessPieceRook.getTiles(board, y, this.x, x);

		//CHECK TO SEE IF QUEEN MOVED IN BISHOP MOTION
		else if (Math.abs(this.x - x) == Math.abs(this.y - y))
		{
			/*We know that this is a valid bishop movement since the absolute values of the differences 
			  of source and destination of x and y are equal */

			int i, j;
			//Bishop moves upper right
			if (x > this.x && y < this.y)
			{
				for (i = this.x, j = this.y; i <= x; i++, j--)
					path.add(board.getChessSquare(i, j));
			}

			//Bishop moves lower right
			else if (x > this.x && y > this.y)
			{
				for (i = this.x, j = this.y; i <= x; i++, j++)
					path.add(board.getChessSquare(i, j));
			}

			//Bishop moves upper left
			else if (x < this.x && y < this.y)
			{
				for (i = this.x, j = this.y; i >= x; i--, j--)
					path.add(board.getChessSquare(i, j));
			}

			//Bishop moves lower left
			else //if (x < this.x && y > this.y)
			{
				for (i = this.x, j = this.y; i >= x; i--, j++)
					path.add(board.getChessSquare(i, j));
			}
		}

		validatePath(path, x, y);
	}
}
