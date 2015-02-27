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
		
		validatePath(path, x, y);
	}
}
