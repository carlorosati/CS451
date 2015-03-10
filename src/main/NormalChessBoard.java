/**
 * This class represents a "normal" chess board.  By "normal," we mean a regular 8x8 chess board that abides by all chess rules.
 */

package main;

import java.awt.Color;
import java.io.Serializable;
import java.util.List;

import javax.swing.ImageIcon;

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
	private Color current;
	
	public NormalChessBoard()
	{
		board = new ChessSquare[WIDTH][HEIGHT];
		current = Color.WHITE;
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
		//black pieces
		for(int i=0;i<8;i++)
		{
			ChessPiece bp = new ChessPiecePawn(Color.BLACK, new ImageIcon(), 1, i);
			update(bp, bp.getX(), bp.getY());
		}
		
		ChessPiece br1 = new ChessPieceRook(Color.BLACK, new ImageIcon(), 0, 0);
		update(br1, br1.getX(), br1.getY());
		
		ChessPiece br2 = new ChessPieceRook(Color.BLACK, new ImageIcon(), 0, 7);
		update(br2, br2.getX(), br2.getY());
		
		ChessPiece bb1 = new ChessPieceBishop(Color.BLACK, new ImageIcon(), 0, 2);
		update(bb1, bb1.getX(), bb1.getY());
		
		ChessPiece bb2 = new ChessPieceBishop(Color.BLACK, new ImageIcon(), 0, 5);
		update(bb2, bb2.getX(), bb2.getY());
		
		ChessPiece bk1 = new ChessPieceKnight(Color.BLACK, new ImageIcon(), 0, 1);
		update(bk1, bk1.getX(), bk1.getY());
		
		ChessPiece bk2 = new ChessPieceKnight(Color.BLACK, new ImageIcon(), 0, 6);
		update(bk2, bk2.getX(), bk2.getY());
	
		ChessPiece bking = new ChessPieceKing(Color.BLACK, new ImageIcon(), 0, 3);
		update(bking, bking.getX(), bking.getY());
		
		ChessPiece bq = new ChessPieceQueen(Color.BLACK, new ImageIcon(), 0, 4);
		update(bq, bq.getX(), bq.getY());
		
		//white pieces
		for(int i=0;i<8;i++)
		{
			ChessPiece wp = new ChessPiecePawn(Color.WHITE, new ImageIcon(), 6, i);
			update(wp, wp.getX(), wp.getY());
		}
		
		ChessPiece wr1 = new ChessPieceRook(Color.WHITE, new ImageIcon(), 7, 0);
		update(wr1, wr1.getX(), wr1.getY());
		
		ChessPiece wr2 = new ChessPieceRook(Color.WHITE, new ImageIcon(), 7, 7);
		update(wr2, wr2.getX(), wr2.getY());
		
		ChessPiece wb1 = new ChessPieceBishop(Color.WHITE, new ImageIcon(), 7, 2);
		update(wb1, wb1.getX(), wb1.getY());
		
		ChessPiece wb2 = new ChessPieceBishop(Color.WHITE, new ImageIcon(), 7, 5);
		update(wb2, wb2.getX(), wb2.getY());
		
		ChessPiece wk1 = new ChessPieceKnight(Color.WHITE, new ImageIcon(), 7, 1);
		update(wk1, wk1.getX(), wk1.getY());
		
		ChessPiece wk2 = new ChessPieceKnight(Color.WHITE, new ImageIcon(), 7, 6);
		update(wk2, wk2.getX(), wk2.getY());
		
		ChessPiece wking = new ChessPieceKing(Color.WHITE, new ImageIcon(), 7, 3);
		update(wking, wking.getX(), wking.getY());
		
		ChessPiece wq = new ChessPieceQueen(Color.WHITE, new ImageIcon(), 7, 4);
		update(wq, wq.getX(), wq.getY());
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
		
		piece.updatePosition(x, y);
		
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
	
	public boolean isCheck(Color c) {
		ChessPiece king = null;
		ChessPiece opponentPiece=null;
		for(int i=0;i<WIDTH;i++){
			for(int j=0;j<HEIGHT;j++) {
				ChessSquare cs = getChessSquare(i, j);
				if(!cs.isEmpty() && cs.getChessPiece() instanceof ChessPieceKing && cs.getChessPiece().getColor().equals(c)) {
					king = cs.getChessPiece();
					//This is being done to break out of both for loops
					i=WIDTH;
					j=HEIGHT;
				}
			}
		}
		//System.out.println("king at ("+king.getX()+","+king.getY()+")");
		
		
		//loop over opponents pieces and check if they could capture the king
		for(int i=0;i<WIDTH;i++){
			for(int j=0;j<HEIGHT;j++) {
				ChessSquare cs = getChessSquare(i, j);
				if(!cs.isEmpty()){
					opponentPiece=cs.getChessPiece();
					List<ChessSquare> path = opponentPiece.getPath(this, king.getX(), king.getY());
					if(opponentPiece.validatePath(path, king.getX(), king.getY())) {
						//System.out.println("("+opponentPiece.getX()+","+opponentPiece.getY()+") can take king");	
						return true;
					}
				}
			}
		}
			// if any can capture return true
		
		return false;
		
	}
	
	public boolean isCheckMate(Color c) {
		System.out.println("checking checkmate");
		for(int i=0;i<WIDTH;i++){
			for(int j=0;j<HEIGHT;j++) {
				if(!getChessSquare(i, j).isEmpty() && getChessSquare(i, j).getChessPiece().getColor().equals(c)){
					ChessPiece p = getChessSquare(i,j).getChessPiece();
					int oldx = p.getX();
					int oldy = p.getY();
					for(int x=0; x<WIDTH; x++) {
						for(int y=0; y<HEIGHT; y++){
							ChessPiece dest = getChessSquare(x,y).getChessPiece();
							if (p.move(this, x, y)) {
								update(p, oldx, oldy);
								if(dest!=null)
									update(dest, x, y);
								return false;
							}
						}
					}
				}
			}
		}
		return true;
	}
	
	public Color getCurrent()
	{
		return current;
	}
	
	public void setCurrent(Color c)
	{
		current = c;
	}
}
