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
	public ChessPieceKnight(Color color, ImageIcon representation, int x, int y)
	{
		super(color, representation, x, y);
	}
	
	@Override
	public boolean validatePath(List<ChessSquare> path, int x, int y)
	{
		//Path will only contain one chess square
		if (path.get(0).isEmpty() || (!path.get(0).isEmpty() && path.get(0).getChessPiece().getColor() != this.color))
			return true;	//We can move here if the chess square is empty or if the chess square contains an enemy piece
		return false;		//If this return statement is reached, we know there is an ally piece here, so we cannot move here
	}
	
	@Override
	public void getPath(ChessBoard board, int x, int y)
	{
		List<ChessSquare> path = new ArrayList<>();
		
		if (((x == this.x - 2 || x == this.x + 2) && (y == this.y - 1 || y == this.y + 1)) || ((x == this.x - 1 || x == this.x + 1) && (y == this.y - 2 || y == this.y + 2)))
			path.add(board.getChessSquare(x, y));
		
		validatePath(path, x, y);
	}
}
