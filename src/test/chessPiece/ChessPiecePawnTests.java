package test.chessPiece;

import java.awt.Color;

import javax.swing.ImageIcon;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import main.*;


public class ChessPiecePawnTests {
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
	}
	
	@Test
	public void PawnCanMoveToHorizontalPosition(){
		ChessPiece Pawn = board.getChessSquare(1, 0).getChessPiece();
		
		//Check that the piece is a Pawn
		Assert.assertEquals(Pawn.getClass(), ChessPiecePawn.class);
		
		boolean canMove = Pawn.move(board, 2, 0);
		
		//Check that the piece can move
		Assert.assertTrue(canMove);
		
		//Check that the piece position is updated
		Assert.assertEquals(Pawn.getX(), 2);
		Assert.assertEquals(Pawn.getY(), 0);
		Assert.assertEquals(Pawn, board.getChessSquare(2,0).getChessPiece());
	}
	
	@Test
	public void PawnCanMoveTwoSpacesOnFirstMoveAndNotOnSecond(){
		ChessPiece Pawn = board.getChessSquare(1, 0).getChessPiece();
		
		//Check that the piece is a Pawn
		Assert.assertEquals(Pawn.getClass(), ChessPiecePawn.class);
		
		boolean canMove = Pawn.move(board, 3, 0);
		
		//Check that the piece can move
		Assert.assertTrue(canMove);
		
		//Check that the piece position is updated
		Assert.assertEquals(Pawn.getX(), 3);
		Assert.assertEquals(Pawn.getY(), 0);
		Assert.assertEquals(Pawn, board.getChessSquare(3,0).getChessPiece());
		
		canMove = Pawn.move(board, 5, 0);
		
		//Now that it's its second turn, make sure it can't move two spaces
		Assert.assertFalse(canMove);
		
		Assert.assertEquals(Pawn.getX(), 3);
		Assert.assertEquals(Pawn.getY(), 0);
		Assert.assertEquals(Pawn, board.getChessSquare(3,0).getChessPiece());
	}
	


	@Test
	public void PawnCanCaptureEnemyPiece(){
		ChessPiece Pawn = board.getChessSquare(1, 0).getChessPiece();
		
		//Check that the piece is a Pawn
		Assert.assertEquals(Pawn.getClass(), ChessPiecePawn.class);
		
		//Check that there is a piece at (6,0) and that it is a separate color
		ChessPiece pawn2 = new ChessPiecePawn(Color.WHITE, new ImageIcon(), 2,1); 
		board.getChessSquare(2,1).setChessPiece(pawn2);
		Assert.assertNotNull(pawn2);
		Assert.assertNotSame(pawn2.getColor(), Pawn.getColor());
		
		boolean canMove = Pawn.move(board, 2, 1);
		
		//Check that the piece can move
		Assert.assertTrue(canMove);
		
		//Check that the piece position is updated
		Assert.assertEquals(Pawn.getX(), 2);
		Assert.assertEquals(Pawn.getY(), 1);
		Assert.assertEquals(Pawn, board.getChessSquare(2,1).getChessPiece());
	}

	
	@Test
	public void PawnCannotMoveToDiagonalPosition(){
		ChessPiece Pawn = board.getChessSquare(1, 0).getChessPiece();
		
		//Check that the piece is a Pawn
		Assert.assertEquals(Pawn.getClass(), ChessPiecePawn.class);
		
		boolean canMove = Pawn.move(board, 2, 1);
		
		//Check that the piece can move
		Assert.assertFalse(canMove);
		
		//Check that the piece position is the same
		Assert.assertEquals(Pawn.getX(), 1);
		Assert.assertEquals(Pawn.getY(), 0);
		Assert.assertEquals(Pawn, board.getChessSquare(1,0).getChessPiece());
	}
	
	@Test
	public void PawnCannotMoveBackwards(){
		ChessPiece Pawn = new ChessPiecePawn(Color.BLACK, new ImageIcon(), 2,0);
		
		board.getChessSquare(2, 0).setChessPiece(Pawn);
		
		boolean canMove = Pawn.move(board, 1, 0);
		
		//Check that the piece cannot move
		Assert.assertFalse(canMove);
		
		Assert.assertEquals(Pawn.getX(), 2);
		Assert.assertEquals(Pawn.getY(), 0);
		Assert.assertEquals(Pawn, board.getChessSquare(2,0).getChessPiece());
	}
	
	@Test
	public void PawnCannotMoveForwardIfLocationIsOccupied(){
		ChessPiece Pawn = board.getChessSquare(1, 0).getChessPiece();
		
		//Check that the piece is a Pawn
		Assert.assertEquals(Pawn.getClass(), ChessPiecePawn.class);
		
		ChessPiece pawn2 = new ChessPiecePawn(Color.WHITE, new ImageIcon(), 2,0);
		board.getChessSquare(2, 0).setChessPiece(pawn2);
		
		//Make sure it's set properly
		Assert.assertNotNull(board.getChessSquare(2,0).getChessPiece());
		
		boolean canMove = Pawn.move(board, 2, 0);
		
		//Check that the piece can move
		Assert.assertFalse(canMove);
		
		//Check that the piece position is the same
		Assert.assertEquals(Pawn.getX(), 1);
		Assert.assertEquals(Pawn.getY(), 0);
		Assert.assertEquals(Pawn, board.getChessSquare(1,0).getChessPiece());
	}
	
	
	@Test
	public void PawnCannotMoveToLocationWithFriendlyPiece(){
		ChessPiece Pawn = board.getChessSquare(1, 0).getChessPiece();
		
		//Check that the piece is a Pawn
		Assert.assertEquals(Pawn.getClass(), ChessPiecePawn.class);
		
		ChessPiece pawn2 = new ChessPiecePawn(Color.BLACK, new ImageIcon(), 2,0);
		board.getChessSquare(2, 0).setChessPiece(pawn2);
		
		//Make sure it's set properly
		Assert.assertNotNull(board.getChessSquare(2,0).getChessPiece());
		
		boolean canMove = Pawn.move(board, 2, 0);
		
		//Check that the piece can move
		Assert.assertFalse(canMove);
		
		//Check that the piece position is the same
		Assert.assertEquals(Pawn.getX(), 1);
		Assert.assertEquals(Pawn.getY(), 0);
		Assert.assertEquals(Pawn, board.getChessSquare(1,0).getChessPiece());
	}
	
}
