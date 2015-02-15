package main;

import java.awt.Color;
import java.awt.Image;

public class ChessPiecePawn extends ChessPiece
{
	public ChessPiecePawn(Color color, Image representation, int x, int y)
	{
		super(color, representation, x, y);
	}

	@Override
	public boolean isMoveValid(ChessBoard board, int x, int y)
	{
		return false;
	}
}
