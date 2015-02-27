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
	
	public static List<ChessSquare> getTiles(ChessBoard board, int fixed, int source, int destination)
	{
		List<ChessSquare> tiles = new ArrayList<>();
		
		if (source > destination)
		{
			for (int i = source; i >= destination; i--)
				tiles.add(board.getChessSquare(fixed, i));
		}
		else
		{
			for (int i = source; i <= destination; i++)
				tiles.add(board.getChessSquare(fixed, i));
		}
		
		return tiles;
	}
	
	@Override
	public void getPath(ChessBoard board, int x, int y)
	{
		List<ChessSquare> path = new ArrayList<>();
		
		if (this.x == x && this.y != y)
			path = ChessPieceRook.getTiles(board, x, this.y, y);
		
		if (this.x != x && this.y == y)
			path = ChessPieceRook.getTiles(board, y, this.x, x);
		
		validatePath(path, x, y);
	}
}
