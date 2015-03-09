package test.chessPiece;

import java.awt.Color;

import javax.swing.ImageIcon;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import main.*;


public class ChessPieceKnightTests {
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
	public void KnightCanMoveToEmptyLPosition(){
		ChessPiece Knight = board.getChessSquare(0, 1).getChessPiece();
		
		//Check that the piece is a Knight
		Assert.assertEquals(Knight.getClass(), ChessPieceKnight.class);
		
		boolean canMove = Knight.move(board, 2, 0);
		
		//Check that the piece can move
		Assert.assertTrue(canMove);
		
		//Check that the piece position is updated
		Assert.assertEquals(Knight.getX(), 2);
		Assert.assertEquals(Knight.getY(), 0);
		Assert.assertEquals(Knight, board.getChessSquare(2,0).getChessPiece());
	}	
	
	@Test
	public void KnightCanCaptureEnemyPiece(){
		
		ChessPiece Knight = board.getChessSquare(0, 1).getChessPiece();
		
		//Check that the piece is a Knight
		Assert.assertEquals(Knight.getClass(), ChessPieceKnight.class);
		
		//Put pawn at (2,0)
		ChessPiece pawn = new ChessPiecePawn(Color.WHITE, new ImageIcon(), 2, 0);
		board.getChessSquare(2, 0).setChessPiece(pawn);
		Assert.assertNotNull(pawn);
		Assert.assertNotSame(pawn.getColor(), Knight.getColor());
		
		boolean canMove = Knight.move(board, 2, 0);
		
		//Check that the piece can move
		Assert.assertTrue(canMove);
		
		//Check that the piece position is updated
		Assert.assertEquals(Knight.getX(), 2);
		Assert.assertEquals(Knight.getY(), 0);
		Assert.assertEquals(Knight, board.getChessSquare(2,0).getChessPiece());
	}
	
	
	@Test
	public void KnightCannotMoveToPositionNotInL(){
		ChessPiece Knight = board.getChessSquare(0, 1).getChessPiece();
		
		//Check that the piece is a Knight
		Assert.assertEquals(Knight.getClass(), ChessPieceKnight.class);
		
		boolean canMove = Knight.move(board, 1, 1);
		
		//Check that the piece can't move
		Assert.assertFalse(canMove);
		
		//Check that the piece position is the same
		Assert.assertEquals(Knight.getX(), 0);
		Assert.assertEquals(Knight.getY(), 1);
		Assert.assertEquals(Knight, board.getChessSquare(0,1).getChessPiece());
	}
	
	
	@Test
	public void KnightCannotMoveToLocationWithFriendlyPiece(){
		ChessPiece Knight = board.getChessSquare(0, 1).getChessPiece();
		
		//Check that the piece is a Knight
		Assert.assertEquals(Knight.getClass(), ChessPieceKnight.class);
		
		boolean canMove = Knight.move(board, 1, 3);
		
		//Check that the piece can move
		Assert.assertFalse(canMove);
		
		//Check that the piece position is the same
		Assert.assertEquals(Knight.getX(), 0);
		Assert.assertEquals(Knight.getY(), 1);
		Assert.assertEquals(Knight, board.getChessSquare(0,1).getChessPiece());
	}
	
}
