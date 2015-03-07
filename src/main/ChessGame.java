package main;

import java.awt.Color;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ChessGame implements Runnable
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
		peerSocket= null;
	}

	public void mainLoop()
	{
		try {
			ObjectOutputStream out = new ObjectOutputStream(peerSocket.getOutputStream());
			out.flush();
			ObjectInputStream in = new ObjectInputStream(peerSocket.getInputStream());
			System.out.println("ML called");
			while(!isOver()){
				if(chessBoard.getCurrent().equals(guiChessBoard.getPlayerColor())){
					//System.out.println("your turn");
					if (guiChessBoard.getMoved()){
						System.out.println("move made");
						chessBoard.setCurrent(chessBoard.getCurrent().equals(Color.BLACK) ? Color.WHITE:Color.BLACK);
						out.writeObject(chessBoard);
						guiChessBoard.setMoved(false);
					}
				} else {
					System.out.println("Waiting for opponent turn");
					chessBoard = (ChessBoard) in.readObject();
					System.out.println("after");
					guiChessBoard.setBoard(chessBoard);
				}	
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
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
		//		ChessGame var = new ChessGame(Color.white);
		//		var.getGUI().setVisible(true);
		//Player p = new Player(Color.BLACK, "Dude");
	}

	@Override
	public void run() {
		mainLoop();
	}
}
