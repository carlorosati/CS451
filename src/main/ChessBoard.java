/**
 * The ChessBoard interface will be implemented by any class that intends to behave as a chess board object.
 */

package main;

import java.awt.Color;

public interface ChessBoard extends java.io.Serializable
{
	public void initialize(Player player);
	public void update(ChessPiece piece, int x, int y);
	public int getWidth();
	public int getHeight();
	public ChessSquare getChessSquare(int x, int y);
	public boolean isCheck(Color c);
	public Color getCurrent();
	public void setCurrent(Color c);
	public boolean isCheckMate(Color c);
	public boolean ifFirstMove();
	public void setFirstMove(boolean value);
}
