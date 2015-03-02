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
	/**
	 * STANDARD JAVA CONVENTION
	 */
	private static final long serialVersionUID = 1L;

	public ChessPieceQueen(Color color, ImageIcon representation, int x, int y)
	{
		super(color, representation, x, y, "Q");
	}

	@Override
	public List<ChessSquare> getPath(ChessBoard board, int x, int y)
	{
		List<ChessSquare> path = new ArrayList<>();

		//Queens behave as rooks and bishops
		//CHECK TO SEE IF QUEEN MOVED IN ROOK MOTION
		if (this.x == x && this.y != y)
		{
			//x is fixed, y is changing
			//If current y is less than new y, then we know we are moving down
			if (this.y < y)
			{
				for (int i = this.y + 1; i <= y; i++)
					path.add(board.getChessSquare(x, i));
			}
			//Otherwise we are moving up
			else
			{
				for (int i = this.y - 1; i >= y; i--)
					path.add(board.getChessSquare(x, i));
			}
		}
		else if (this.x != x && this.y == y)
		{
			//y is fixed, x is changing
			//If current x is less than new x, then we know we are moving right
			if (this.x < x)
			{
				for (int i = this.x + 1; i <= x; i++)
					path.add(board.getChessSquare(i, y));
			}
			//Otherwise we are moving left
			else
			{
				for (int i = this.x- 1; i >= x; i--)
					path.add(board.getChessSquare(i, y));
			}
		}

		//CHECK TO SEE IF QUEEN MOVED IN BISHOP MOTION
		else if (Math.abs(this.x - x) == Math.abs(this.y - y))
		{
			/*We know that this is a valid bishop movement since the absolute values of the differences 
			  of source and destination of x and y are equal */

			int i, j;
			//Bishop moves upper right
			if (x > this.x && y < this.y)
			{
				for (i = this.x + 1, j = this.y - 1; i <= x; i++, j--)
					path.add(board.getChessSquare(i, j));
			}

			//Bishop moves lower right
			else if (x > this.x && y > this.y)
			{
				for (i = this.x + 1, j = this.y + 1; i <= x; i++, j++)
					path.add(board.getChessSquare(i, j));
			}

			//Bishop moves upper left
			else if (x < this.x && y < this.y)
			{
				for (i = this.x - 1, j = this.y - 1; i >= x; i--, j--)
					path.add(board.getChessSquare(i, j));
			}

			//Bishop moves lower left
			else //if (x < this.x && y > this.y)
			{
				for (i = this.x - 1, j = this.y + 1; i >= x; i--, j++)
					path.add(board.getChessSquare(i, j));
			}
		}

		return path;
	}
}
