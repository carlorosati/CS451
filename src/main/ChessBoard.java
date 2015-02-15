package main;

public interface ChessBoard
{
	public void initialize();
	public void display();
	public void update(ChessPiece piece, int x, int y);
	public ChessSquare getChessSquare(int x, int y);
}
