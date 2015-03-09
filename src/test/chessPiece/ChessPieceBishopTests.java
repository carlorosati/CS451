package test.chessPiece;

import java.awt.Color;

import javax.swing.ImageIcon;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import main.*;


public class ChessPieceBishopTests {
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
	public void BishopCanMoveToDiagonalPosition(){
		ChessPiece Bishop = board.getChessSquare(0, 2).getChessPiece();
		
		//Check that the piece is a Bishop
		Assert.assertEquals(Bishop.getClass(), ChessPieceBishop.class);
		
		boolean canMove = Bishop.move(board, 1, 1);
		
		//Check that the piece can move
		Assert.assertTrue(canMove);
		
		//Check that the piece position is updated
		Assert.assertEquals(Bishop.getX(), 1);
		Assert.assertEquals(Bishop.getY(), 1);
		Assert.assertEquals(Bishop, board.getChessSquare(1,1).getChessPiece());
	}	
	
	@Test
	public void BishopCanCaptureEnemyPiece(){
		ChessPiece Bishop = board.getChessSquare(0, 2).getChessPiece();
		
		//Check that the piece is a Bishop
		Assert.assertEquals(Bishop.getClass(), ChessPieceBishop.class);
		
		//Put Enemy Pawn at (1,1)
		ChessPiece pawn = new ChessPiecePawn(Color.WHITE, new ImageIcon(), 1, 1);
		board.getChessSquare(1, 1).setChessPiece(pawn);
		Assert.assertNotNull(pawn);
		Assert.assertNotSame(pawn.getColor(), Bishop.getColor());
		
		boolean canMove = Bishop.move(board, 1, 1);
		
		//Check that the piece can move
		Assert.assertTrue(canMove);
		
		//Check that the piece position is updated
		Assert.assertEquals(Bishop.getX(), 1);
		Assert.assertEquals(Bishop.getY(), 1);
		Assert.assertEquals(Bishop, board.getChessSquare(1,1).getChessPiece());
	}
	
	
	@Test
	public void BishopCannotMoveToNonDiagonalPosition(){
		ChessPiece Bishop = board.getChessSquare(0, 2).getChessPiece();
		
		//Check that the piece is a Bishop
		Assert.assertEquals(Bishop.getClass(), ChessPieceBishop.class);
		
		boolean canMove = Bishop.move(board, 1, 2);
		
		//Check that the piece can move
		Assert.assertFalse(canMove);
		
		//Check that the piece position is the same
		Assert.assertEquals(Bishop.getX(), 0);
		Assert.assertEquals(Bishop.getY(), 2);
		Assert.assertEquals(Bishop, board.getChessSquare(0,2).getChessPiece());
	}
	
	
	@Test
	public void BishopCannotMoveToLocationWithFriendlyPiece(){
		ChessPiece Bishop = board.getChessSquare(0, 2).getChessPiece();
		
		//Check that the piece is a Bishop
		Assert.assertEquals(Bishop.getClass(), ChessPieceBishop.class);
		
		//Put Friendly Pawn at (1,1)
		ChessPiece pawn = new ChessPiecePawn(Color.BLACK, new ImageIcon(), 1, 1);
		board.getChessSquare(1, 1).setChessPiece(pawn);
		Assert.assertNotNull(pawn);
		Assert.assertSame(pawn.getColor(), Bishop.getColor());
		
		boolean canMove = Bishop.move(board, 1, 1);	
		
		//Check that the piece can move
		Assert.assertFalse(canMove);
		
		//Check that the piece position is the same
		Assert.assertEquals(Bishop.getX(), 0);
		Assert.assertEquals(Bishop.getY(), 2);
		Assert.assertEquals(Bishop, board.getChessSquare(0,2).getChessPiece());
	}
	
}
