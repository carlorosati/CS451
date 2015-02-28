/**
 * This class represents a "normal" chess board.  By "normal," we mean a regular 8x8 chess board that abides by all chess rules.
 */

package main;

import java.awt.Color;
import java.io.Serializable;

public class NormalChessBoard implements ChessBoard, Cloneable, Serializable
{
	/**
	 * STANDARD JAVA CONVENTION
	 */
	private static final long serialVersionUID = 1L;
	
	//CONSTANTS
	private static final int WIDTH = 8;		//WIDTH OF A NORMAL CHESS BOARD
	private static final int HEIGHT = 8;	//HEIGHT OF A NORMAL CHESS BOARD
	
	//INSTANCE VARIABLES
	private ChessSquare[][] board;			//Two-dimensional array of chess squares that will make up the board.
	//TODO:  CREATE A LIST OF CHESS PIECES
	
	public NormalChessBoard()
	{
		board = new ChessSquare[WIDTH][HEIGHT];
	}

	@Override
	public void initialize(Player player)
	{
		int turn = 0;
		//INITIALIZE BOARD
		for (int i = 0; i < WIDTH; i++)
		{
			for (int j = 0; j < HEIGHT; j++)
			{
				if (turn++ % 2 == 0)
					board[i][j] = new ChessSquare(Color.BLACK, i * ChessSquare.SQUARE_SIZE, j * ChessSquare.SQUARE_SIZE);
				else
					board[i][j] = new ChessSquare(Color.WHITE, i * ChessSquare.SQUARE_SIZE, j * ChessSquare.SQUARE_SIZE);
			}
			turn++;
		}
		
		//INITIALIZE PIECES
		for (int i = 0; i < 8; i++)
		{
			
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
	
	public ChessBoard clone() {
		ChessBoard cb = new NormalChessBoard();
		//TODO: implement cloning of the board
		return cb;
	}
	public boolean isCheck(Player player) {
		//find players king and get containing chess sq
		
		//loop over opponents pieces and check if they could capture the king
			// if any can capture return true
		
		return false;
		
	}
}
