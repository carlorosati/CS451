/**
 * This class represents the Bishop chess piece.
 */

package main;

import java.awt.Color;
import java.awt.Image;

public class ChessPieceBishop extends ChessPiece
{
	public ChessPieceBishop(Color color, Image representation, int x, int y)
	{
		super(color, representation, x, y);
	}

	@Override
	public boolean isMoveValid(ChessBoard board, int x, int y)
	{
		return false;
	}
}
