package test.chessPiece;

import java.awt.Color;

import javax.swing.ImageIcon;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import main.*;


public class ChessPieceKingTests {
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
	public void KingCanMoveToEmptyHorizontalPosition(){
		ChessPiece King = board.getChessSquare(0, 3).getChessPiece();
		
		//Check that the piece is a King
		Assert.assertEquals(King.getClass(), ChessPieceKing.class);
		
		boolean canMove = King.move(board, 1, 3);
		
		//Check that the piece can move
		Assert.assertTrue(canMove);
		
		//Check that the piece position is updated
		Assert.assertEquals(King.getX(), 1);
		Assert.assertEquals(King.getY(), 3);
		Assert.assertEquals(King, board.getChessSquare(1,3).getChessPiece());
	}
	

	
	@Test
	public void KingCanMoveToEmptyVerticalPosition(){
		//Put King at board position (1,0)
		ChessPiece King = new ChessPieceKing(Color.BLACK, new ImageIcon(), 1, 0);
		
		board.getChessSquare(1, 0).setChessPiece(King);
		
		//Check that the piece is a King
		Assert.assertEquals(King.getClass(), ChessPieceKing.class);
		
		boolean canMove = King.move(board, 1, 1);
		
		//Check that the piece can move
		Assert.assertTrue(canMove);
		
		//Check that the piece position is updated
		Assert.assertEquals(King.getX(), 1);
		Assert.assertEquals(King.getY(), 1);
		Assert.assertEquals(King, board.getChessSquare(1,1).getChessPiece());
	}	
	
	
	@Test
	public void KingCanCaptureEnemyPiece(){
		ChessPiece King = board.getChessSquare(0, 3).getChessPiece();
		
		//Check that the piece is a King
		Assert.assertEquals(King.getClass(), ChessPieceKing.class);
		
		//Check that there is a piece at (6,0) and that it is a separate color
		ChessPiece pawn = new ChessPiecePawn(Color.WHITE, new ImageIcon(), 1, 3); 
		board.getChessSquare(1,3).setChessPiece(pawn);
		Assert.assertNotNull(pawn);
		Assert.assertNotSame(pawn.getColor(), King.getColor());
		
		boolean canMove = King.move(board, 1, 3);
		
		//Check that the piece can move
		Assert.assertTrue(canMove);
		
		//Check that the piece position is updated
		Assert.assertEquals(King.getX(), 1);
		Assert.assertEquals(King.getY(), 3);
		Assert.assertEquals(King, board.getChessSquare(1,3).getChessPiece());
	}
	
	
	@Test
	public void KingCanMoveToDiagonalPosition(){
		ChessPiece King = board.getChessSquare(0, 3).getChessPiece();
		
		//Check that the piece is a King
		Assert.assertEquals(King.getClass(), ChessPieceKing.class);
		
		boolean canMove = King.move(board, 1, 2);
		
		//Check that the piece can move
		Assert.assertTrue(canMove);
		
		//Check that the piece position is the same
		Assert.assertEquals(King.getX(), 1);
		Assert.assertEquals(King.getY(), 2);
		Assert.assertEquals(King, board.getChessSquare(1,2).getChessPiece());
	}
	
	
	@Test
	public void KingCannotMoveToLocationWithFriendlyPiece(){
		ChessPiece King = board.getChessSquare(0, 3).getChessPiece();
		
		//Check that the piece is a King
		Assert.assertEquals(King.getClass(), ChessPieceKing.class);
		
		boolean canMove = King.move(board, 0, 2);
		
		//Check that the piece can move
		Assert.assertFalse(canMove);
		
		//Check that the piece position is the same
		Assert.assertEquals(King.getX(), 0);
		Assert.assertEquals(King.getY(), 3);
		Assert.assertEquals(King, board.getChessSquare(0,3).getChessPiece());
	}
	
	@Test 
	public void KingCannotMoveToLocationMoreThanOneSquareAway(){
		ChessPiece King = board.getChessSquare(0, 3).getChessPiece();
		
		//Check that the piece is a King
		Assert.assertEquals(King.getClass(), ChessPieceKing.class);
		
		boolean canMove = King.move(board, 2, 3);
		
		//Check that the piece can move
		Assert.assertFalse(canMove);
		
		//Check that the piece position is the same
		Assert.assertEquals(King.getX(), 0);
		Assert.assertEquals(King.getY(), 3);
		Assert.assertEquals(King, board.getChessSquare(0,3).getChessPiece());
	}
	
}
