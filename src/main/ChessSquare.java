package main;

public class ChessSquare
{
	private ChessPiece piece;
	
	public ChessSquare()
	{
		piece = null;
	}
	
	public ChessPiece getChessPiece()
	{
		return piece;
	}
	
	public void setChessPiece(ChessPiece piece)
	{
		this.piece = piece;
	}
	
	public boolean isEmpty()
	{
		return (getChessPiece() == null);
	}
}
