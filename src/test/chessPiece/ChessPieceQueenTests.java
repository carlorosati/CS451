package test.chessPiece;

import java.awt.Color;

import javax.swing.ImageIcon;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import main.*;


public class ChessPieceQueenTests {
	public static ChessBoard board;
	
	@BeforeClass
	public static void BoardPrep(){
		//Get Default ChessBoard
		board = new NormalChessBoard();
	}
	
	@Before
	public void ResetBoard(){
		//Set up board
		board.initialize();
		
		//Get rid of pawns
		for(int y = 0; y < 8; y++){
			board.getChessSquare(1, y).setChessPiece(null);
		}
	}
	
	@Test
	public void QueenCanMoveToDiagonalPosition(){
		ChessPiece Queen = board.getChessSquare(0, 4).getChessPiece();
		
		//Check that the piece is a Queen
		Assert.assertEquals(Queen.getClass(), ChessPieceQueen.class);
		
		boolean canMove = Queen.move(board, 1, 3);
		
		//Check that the piece can move
		Assert.assertTrue(canMove);
		
		//Check that the piece position is updated
		Assert.assertEquals(Queen.getX(), 1);
		Assert.assertEquals(Queen.getY(), 3);
		Assert.assertEquals(Queen, board.getChessSquare(1,3).getChessPiece());
	}	
	
	@Test
	public void QueenCanMoveToEmptyHorizontalPosition(){
		ChessPiece Queen = board.getChessSquare(0, 4).getChessPiece();
		
		//Check that the piece is a Queen
		Assert.assertEquals(Queen.getClass(), ChessPieceQueen.class);
		
		boolean canMove = Queen.move(board, 1, 4);
		
		//Check that the piece can move
		Assert.assertTrue(canMove);
		
		//Check that the piece position is updated
		Assert.assertEquals(Queen.getX(), 1);
		Assert.assertEquals(Queen.getY(), 4);
		Assert.assertEquals(Queen, board.getChessSquare(1,4).getChessPiece());
	}
	

	
	@Test
	public void QueenCanMoveToEmptyVerticalPosition(){
		//Put Queen at board position (1,0)
		ChessPiece Queen = new ChessPieceQueen(Color.BLACK, new ImageIcon(), 1, 0);
		
		board.getChessSquare(1, 0).setChessPiece(Queen);
		
		//Check that the piece is a Queen
		Assert.assertEquals(Queen.getClass(), ChessPieceQueen.class);
		
		boolean canMove = Queen.move(board, 1, 1);
		
		//Check that the piece can move
		Assert.assertTrue(canMove);
		
		//Check that the piece position is updated
		Assert.assertEquals(Queen.getX(), 1);
		Assert.assertEquals(Queen.getY(), 1);
		Assert.assertEquals(Queen, board.getChessSquare(1,1).getChessPiece());
	}	
	
	
	@Test
	public void QueenCanCaptureEnemyPiece(){
		ChessPiece Queen = board.getChessSquare(0, 4).getChessPiece();
		
		//Check that the piece is a Queen
		Assert.assertEquals(Queen.getClass(), ChessPieceQueen.class);
		
		//Put Enemy Pawn at (1,4)
		ChessPiece pawn = new ChessPiecePawn(Color.WHITE, new ImageIcon(), 1, 4);
		board.getChessSquare(1, 4).setChessPiece(pawn);
		Assert.assertNotNull(pawn);
		Assert.assertNotSame(pawn.getColor(), Queen.getColor());
		
		boolean canMove = Queen.move(board, 1, 4);
		
		//Check that the piece can move
		Assert.assertTrue(canMove);
		
		//Check that the piece position is updated
		Assert.assertEquals(Queen.getX(), 1);
		Assert.assertEquals(Queen.getY(), 4);
		Assert.assertEquals(Queen, board.getChessSquare(1,4).getChessPiece());
	}
	
	
	@Test
	public void QueenCannotMoveToInvalidPosition(){
		ChessPiece Queen = board.getChessSquare(0, 4).getChessPiece();
		
		//Check that the piece is a Queen
		Assert.assertEquals(Queen.getClass(), ChessPieceQueen.class);
		
		boolean canMove = Queen.move(board, 3, 3);
		
		//Check that the piece can move
		Assert.assertFalse(canMove);
		
		//Check that the piece position is the same
		Assert.assertEquals(Queen.getX(), 0);
		Assert.assertEquals(Queen.getY(), 4);
		Assert.assertEquals(Queen, board.getChessSquare(0,4).getChessPiece());
	}
	
	
	@Test
	public void QueenCannotMoveToLocationWithFriendlyPiece(){
		ChessPiece Queen = board.getChessSquare(0, 4).getChessPiece();
		
		//Check that the piece is a Queen
		Assert.assertEquals(Queen.getClass(), ChessPieceQueen.class);
		
		//Get Friendly piece and make sure it is at position that queen could normally move 
		ChessPiece king = board.getChessSquare(0, 3).getChessPiece();
		Assert.assertNotNull(king);
		Assert.assertSame(king.getColor(), Queen.getColor());
		
		boolean canMove = Queen.move(board, 0, 3);	
		
		//Check that the piece can move
		Assert.assertFalse(canMove);
		
		//Check that the piece position is the same
		Assert.assertEquals(Queen.getX(), 0);
		Assert.assertEquals(Queen.getY(), 4);
		Assert.assertEquals(Queen, board.getChessSquare(0,4).getChessPiece());
	}
	
}
