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
		
		validatePath(path, x, y);
	}
}
