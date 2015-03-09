package test.chessPiece;

import java.awt.Color;

import javax.swing.ImageIcon;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import main.*;


public class ChessPieceRookTests {
	public static ChessBoard board;
	
	@BeforeClass
	public static void BoardPrep(){
		//Get Default ChessBoard
		board = new NormalChessBoard();
	}
	
	@Before
	public void ResetBoard(){
		//Set up board
		board.initialize(null);
		
		//Get rid of pawns
		for(int y = 0; y < 8; y++){
			board.getChessSquare(1, y).setChessPiece(null);
		}
	}
	
	@Test
	public void RookCanMoveToEmptyHorizontalPosition(){
		ChessPiece rook = board.getChessSquare(0, 0).getChessPiece();
		
		//Check that the piece is a rook
		Assert.assertEquals(rook.getClass(), ChessPieceRook.class);
		
		boolean canMove = rook.move(board, 1, 0);
		
		//Check that the piece can move
		Assert.assertTrue(canMove);
		
		//Check that the piece position is updated
		Assert.assertEquals(rook.getX(), 1);
		Assert.assertEquals(rook.getY(), 0);
		Assert.assertEquals(rook, board.getChessSquare(1,0).getChessPiece());
	}
	

	
	@Test
	public void RookCanMoveToEmptyVerticalPosition(){
		//Put rook at board position (1,0)
		ChessPiece rook = new ChessPieceRook(Color.BLACK, new ImageIcon(), 1, 0);
		
		board.getChessSquare(1, 0).setChessPiece(rook);
		
		//Check that the piece is a rook
		Assert.assertEquals(rook.getClass(), ChessPieceRook.class);
		
		boolean canMove = rook.move(board, 1, 1);
		
		//Check that the piece can move
		Assert.assertTrue(canMove);
		
		//Check that the piece position is updated
		Assert.assertEquals(rook.getX(), 1);
		Assert.assertEquals(rook.getY(), 1);
		Assert.assertEquals(rook, board.getChessSquare(1,1).getChessPiece());
	}	
	
	@Test
	public void RookCanCaptureEnemyPiece(){
		ChessPiece rook = board.getChessSquare(0, 0).getChessPiece();
		
		//Check that the piece is a rook
		Assert.assertEquals(rook.getClass(), ChessPieceRook.class);
		
		//Check that there is a piece at (6,0) and that it is a separate color
		ChessPiece pawn = board.getChessSquare(6,0).getChessPiece();
		Assert.assertNotNull(pawn);
		Assert.assertNotSame(pawn.getColor(), rook.getColor());
		
		boolean canMove = rook.move(board, 6, 0);
		
		//Check that the piece can move
		Assert.assertTrue(canMove);
		
		//Check that the piece position is updated
		Assert.assertEquals(rook.getX(), 6);
		Assert.assertEquals(rook.getY(), 0);
		Assert.assertEquals(rook, board.getChessSquare(6,0).getChessPiece());
	}
	
	@Test
	public void RookCannotMoveToDiagonalPosition(){
		ChessPiece rook = board.getChessSquare(0, 0).getChessPiece();
		
		//Check that the piece is a rook
		Assert.assertEquals(rook.getClass(), ChessPieceRook.class);
		
		boolean canMove = rook.move(board, 1, 1);
		
		//Check that the piece can move
		Assert.assertFalse(canMove);
		
		//Check that the piece position is the same
		Assert.assertEquals(rook.getX(), 0);
		Assert.assertEquals(rook.getY(), 0);
		Assert.assertEquals(rook, board.getChessSquare(0,0).getChessPiece());
	}
	
	@Test
	public void RookCannotMoveToLocationWithFriendlyPiece(){
		ChessPiece rook = board.getChessSquare(0, 0).getChessPiece();
		
		//Check that the piece is a rook
		Assert.assertEquals(rook.getClass(), ChessPieceRook.class);
		
		boolean canMove = rook.move(board, 0, 1);
		
		//Check that the piece can move
		Assert.assertFalse(canMove);
		
		//Check that the piece position is the same
		Assert.assertEquals(rook.getX(), 0);
		Assert.assertEquals(rook.getY(), 0);
		Assert.assertEquals(rook, board.getChessSquare(0,0).getChessPiece());
	}
}
