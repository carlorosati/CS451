/**
 * This class represents a "normal" chess board.  By "normal," we mean a regular 8x8 chess board that abides by all chess rules.
 */

package main;

public class NormalChessBoard implements ChessBoard
{
	//CONSTANTS
	private static final int WIDTH = 8;		//WIDTH OF A NORMAL CHESS BOARD
	private static final int HEIGHT = 8;	//HEIGHT OF A NORMAL CHESS BOARD
	
	//INSTANCE VARIABLES
	private ChessSquare[][] board;			//Two-dimensional array of chess squares that will make up the board.
	
	public NormalChessBoard()
	{
		board = new ChessSquare[WIDTH][HEIGHT];
		initialize();
	}

	@Override
	public void initialize()
	{
		for (int i = 0; i < WIDTH; i++)
		{
			for (int j = 0; j < HEIGHT; j++)
			{
				board[i][j] = new ChessSquare();
			}
		}
	}

	/**
	 * This method updates the location of a chess piece to new position (x, y).  The chess square that previously contained this chess piece is set 
	 * to null.
	 * @param piece: ChessPiece object in need of a position update.
	 * @param x: new x value to update the chess piece
	 * @param y: new y value to update the chess piece
	 */
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
	public int getWidth()
	{
		return WIDTH;
	}
	
	@Override
	public int getHeight()
	{
		return HEIGHT;
	}

	@Override
	public ChessSquare getChessSquare(int x, int y)
	{
		return board[x][y];
	}
}
