package main;

public class NormalChessBoard implements ChessBoard
{
	private static final int WIDTH = 8;
	private static final int HEIGHT = 8;
	private ChessSquare[][] board;
	
	public NormalChessBoard()
	{
		board = new ChessSquare[WIDTH][HEIGHT];
	}

	@Override
	public void initialize()
	{
		
	}

	@Override
	public void display()
	{

	}

	@Override
	public void update(ChessPiece piece, int x, int y)
	{
		//GET THE OLD X AND Y VALUES OF THE CHESS PIECE-- WE MUST UPDATE THE OLD CHESS SQUARE TO NULL AND THE NEW CHESS SQUARE TO HOLD THE NEW PIECE
		int oldX = piece.getX();
		int oldY = piece.getY();
		
		board[oldX][oldY].setChessPiece(null);
		board[x][y].setChessPiece(piece);
	}

	@Override
	public ChessSquare getChessSquare(int x, int y)
	{
		return board[x][y];
	}
}
