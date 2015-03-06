package main;

import java.awt.Color;

//import javax.swing.ImageIcon;

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
		GUIChessBoard gui = new GUIChessBoard(board);
		gui.show();
		TestConsole.drawBoard(board);
		gui.update();
		
		Scanner scan = new Scanner(System.in);
		boolean validMove;
		int count=0;
		do
		{
			int oldX = scan.nextInt();
			int oldY = scan.nextInt();
			
			int newX = scan.nextInt();
			int newY = scan.nextInt();
			
			validMove = board.getChessSquare(oldX, oldY).getChessPiece().move(board, newX, newY);
			System.out.println(validMove);
			if(validMove)
				count++;
			TestConsole.drawBoard(board);
			gui.update();
		} while (count<10);
		
		TestConsole.drawBoard(board);
		
		scan.close();
	}
}
