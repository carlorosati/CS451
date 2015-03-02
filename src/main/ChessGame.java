package main;

import java.awt.Color;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JFrame;

public class ChessGame
{
	//INSTANCE VARIABLES	
	private Player player;					//Variable representing the player object
	private ChessBoard chessBoard;			//Internal chess board structure
	private GUIChessBoard guiChessBoard;	//How the chess board will be displayed to the user
	private Socket peerSocket;
	
	public ChessGame(Color color)
	{
		player = new Player(color, "Chris");
		chessBoard = new NormalChessBoard();
		chessBoard.initialize(player);
		guiChessBoard = new GUIChessBoard(chessBoard);
		initializeGUI();
		mainLoop();
	}
	
	private void initializeGUI()
	{
		JFrame frame = new JFrame("CHESS");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(700,  600);
		frame.setResizable(false);
		frame.setVisible(true);

		frame.add(guiChessBoard);
	}
	
	private void mainLoop()
	{
		
	}
	
	public GUIChessBoard getGUI(){
		return guiChessBoard;
	}
	
	public void SetSocket(Socket newSocket){
		peerSocket = newSocket;
	}
	
	/** PROTOTYPE METHOD */
	public boolean isOver()
	{
		return false;
	}
	
	public static void main(String[] args) throws IOException
	{
		ConnectionScreen myScreen = new ConnectionScreen();
		myScreen.setVisible(true);
		//Player p = new Player(Color.BLACK, "Dude");
	}
}
