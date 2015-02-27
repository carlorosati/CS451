/**
 * The ChessBoard interface will be implemented by any class that intends to behave as a chess board object.
 */

package main;

public interface ChessBoard extends java.io.Serializable
{
	public void initialize(Player player);
	public void update(ChessPiece piece, int x, int y);
	public int getWidth();
	public int getHeight();
	public ChessSquare getChessSquare(int x, int y);
}
