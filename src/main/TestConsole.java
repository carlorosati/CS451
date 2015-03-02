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
			for (int j = 0; j < board.getHeight(); j++)
			{
				if (board.getChessSquare(j, i).isEmpty())
					System.out.print(" _ ");
				else
					System.out.print(" " + board.getChessSquare(j, i).getChessPiece().getRep() + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	
	public static void main(String[] args)
	{
		ChessBoard board = new NormalChessBoard();
		Player player = new Player(Color.WHITE, "Chris");
		board.initialize(player);
		TestConsole.drawBoard(board);
		
		ChessPiece br = new ChessPieceRook(Color.BLACK, new ImageIcon(), 4, 4);
		board.update(br, br.getX(), br.getY());
		ChessPiece wr = new ChessPieceRook(Color.WHITE, new ImageIcon(), 0, 4);
		board.update(wr, wr.getX(), wr.getY());
		ChessPiece bk = new ChessPieceKing(Color.BLACK, new ImageIcon(), 7, 4);
		board.update(bk, bk.getX(), bk.getY());
		
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
