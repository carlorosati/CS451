/**
 * This class represents the Bishop chess piece.
 */

package main;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

public class ChessPieceBishop extends ChessPiece
{
	/**
	 * STANDARD JAVA CONVENTION
	 */
	private static final long serialVersionUID = 1L;

	public ChessPieceBishop(Color color, ImageIcon representation, int x, int y)
	{
		super(color, representation, x, y, "B");
		ImageIcon image;
		//Used to update piece, based on color and actual piece
		if (color == Color.BLACK){
			image = new ImageIcon( getClass().getResource("/resources/ChessPieces/blackBishop.png") );
			this.representation = image;
		}else{
			image = new ImageIcon( getClass().getResource("/resources/ChessPieces/whiteBishop.png") );
			this.representation = image;
		}
	}
	
	@Override
	public List<ChessSquare> getPath(ChessBoard board, int x, int y)
	{
		List<ChessSquare> path = new ArrayList<>();
		
		if (Math.abs(this.x - x) == Math.abs(this.y - y))
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
