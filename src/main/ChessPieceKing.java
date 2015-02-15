package main;

import java.awt.Color;
import java.awt.Image;

public class ChessPieceKing extends ChessPiece
{
	public ChessPieceKing(Color color, Image representation, int x, int y)
	{
		super(color, representation, x, y);
	}

	@Override
	public boolean isMoveValid(ChessBoard board, int x, int y)
	{
		return false;
	}
}
