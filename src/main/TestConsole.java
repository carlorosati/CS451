package main;

import java.awt.Color;

import javax.swing.ImageIcon;

import java.util.Scanner;

public class TestConsole
{
	private static void drawBoard(ChessBoard board)
	{
		for (int i = 0; i < board.getWidth(); i++)
		{
			System.out.print(i+" ");
			for (int j = 0; j < board.getHeight(); j++)
			{
				if (board.getChessSquare(j, i).isEmpty())
					System.out.print(" _ ");
				else
					System.out.print(" " + board.getChessSquare(j, i).getChessPiece().getRep() + " ");
			}
			System.out.println();
		}
		System.out.println("   0  1  2  3  4  5  6  7\n");
	}
	
	
	public static void main(String[] args)
	{
		ChessBoard board = new NormalChessBoard();
		Player player = new Player(Color.WHITE, "Chris");
		board.initialize(player);
		TestConsole.drawBoard(board);
		
		ChessPiece br1 = new ChessPieceRook(Color.BLACK, new ImageIcon(), 0, 0);
		board.update(br1, br1.getX(), br1.getY());
		
		ChessPiece br2 = new ChessPieceRook(Color.BLACK, new ImageIcon(), 0, 7);
		board.update(br2, br2.getX(), br2.getY());
		
		ChessPiece bb1 = new ChessPieceBishop(Color.BLACK, new ImageIcon(), 0, 2);
		board.update(bb1, bb1.getX(), bb1.getY());
		
		ChessPiece bb2 = new ChessPieceBishop(Color.BLACK, new ImageIcon(), 0, 5);
		board.update(bb2, bb2.getX(), bb2.getY());
		
		ChessPiece bk1 = new ChessPieceKnight(Color.BLACK, new ImageIcon(), 0, 1);
		board.update(bk1, bk1.getX(), bk1.getY());
		
		ChessPiece bk2 = new ChessPieceKnight(Color.BLACK, new ImageIcon(), 0, 6);
		board.update(bk2, bk2.getX(), bk2.getY());
		
		ChessPiece bking = new ChessPieceKing(Color.BLACK, new ImageIcon(), 0, 4);
		board.update(bking, bking.getX(), bking.getY());
		
		ChessPiece bq = new ChessPieceQueen(Color.BLACK, new ImageIcon(), 0, 3);
		board.update(bq, bq.getX(), bq.getY());
		
		//white pieces
		ChessPiece wr1 = new ChessPieceRook(Color.WHITE, new ImageIcon(), 7, 0);
		board.update(wr1, wr1.getX(), wr1.getY());
		
		ChessPiece wr2 = new ChessPieceRook(Color.WHITE, new ImageIcon(), 7, 7);
		board.update(wr2, wr2.getX(), wr2.getY());
		
		ChessPiece wb1 = new ChessPieceBishop(Color.WHITE, new ImageIcon(), 7, 2);
		board.update(wb1, wb1.getX(), wb1.getY());
		
		ChessPiece wb2 = new ChessPieceBishop(Color.WHITE, new ImageIcon(), 7, 5);
		board.update(wb2, wb2.getX(), wb2.getY());
		
		ChessPiece wk1 = new ChessPieceKnight(Color.WHITE, new ImageIcon(), 7, 1);
		board.update(wk1, wk1.getX(), wk1.getY());
		
		ChessPiece wk2 = new ChessPieceKnight(Color.WHITE, new ImageIcon(), 7, 6);
		board.update(wk2, wk2.getX(), wk2.getY());
		
		ChessPiece wking = new ChessPieceKing(Color.WHITE, new ImageIcon(), 7, 3);
		board.update(wking, wking.getX(), wking.getY());
		
		ChessPiece wq = new ChessPieceQueen(Color.WHITE, new ImageIcon(), 7, 4);
		board.update(wq, wq.getX(), wq.getY());
		
		
		
		
		
		TestConsole.drawBoard(board);
		
		Scanner scan = new Scanner(System.in);
		boolean validMove;
		do
		{
			int oldX = scan.nextInt();
			int oldY = scan.nextInt();
			
			int newX = scan.nextInt();
			int newY = scan.nextInt();
			
			validMove = board.getChessSquare(oldX, oldY).getChessPiece().move(board, newX, newY);
			System.out.println(validMove);
			
		} while (!validMove);
		
		TestConsole.drawBoard(board);
		
		scan.close();
	}
}
