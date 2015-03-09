package main;

import java.awt.Color;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;

public class ChessGame implements Runnable
{
	//INSTANCE VARIABLES	
	private Player player;					//Variable representing the player object
	private ChessBoard chessBoard;			//Internal chess board structure
	private GUIChessBoard guiChessBoard;	//How the chess board will be displayed to the user
	private Socket peerSocket;
	private boolean over=false;				//Boolean variable representing whether or not the game has ended (by victory or by early termination)

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
			// System.out.println("ML called");
			while(!isOver()){
				if(chessBoard.getCurrent().equals(guiChessBoard.getPlayerColor())){
					Thread.sleep(100);
					//System.out.println("your turn");
					if (guiChessBoard.getMoved()){
						System.out.println("move made");

						// Before we send the board check for pawn promotion
						

						chessBoard.setCurrent(
							chessBoard.getCurrent().equals(Color.BLACK)
							? Color.WHITE
							: Color.BLACK
						);
						out.writeObject(chessBoard);
						out.flush();
						over=chessBoard.isCheckMate(chessBoard.getCurrent());
						if(over) {
							guiChessBoard.showVictory();
						}
					}
				} else {
					System.out.println("Waiting for opponent turn");
					chessBoard = (ChessBoard) in.readObject();
					guiChessBoard.setBoard(chessBoard);
					over=chessBoard.isCheckMate(chessBoard.getCurrent());
					if(over) {
						guiChessBoard.showLoss();
					}
					else if(chessBoard.isCheck(chessBoard.getCurrent())){
						guiChessBoard.showCheck();
					}
					guiChessBoard.setMoved(false);
				}	 
			}
		}catch(SocketException e) {
			over = true;
			guiChessBoard.showDisconnected();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println("end");
	}

	public GUIChessBoard getGUI()
	{
		return guiChessBoard;
	}

	public void SetSocket(Socket newSocket)
	{
		peerSocket = newSocket;
	}

	/** PROTOTYPE METHOD */
	public boolean isOver()
	{
		return over;
	}

	@Override
	public void run()
	{
		mainLoop();
	}
	
	public void promotion(Color c)
	{
		ChessPiece p;
		ChessPiece newp;
		String selection = "";
		if (c.equals(Color.WHITE)){
			for(int i=0;i<8;i++) {
				p = chessBoard.getChessSquare(0, i).getChessPiece();
				if(p!=null && p instanceof ChessPiecePawn){
					selection = guiChessBoard.promotion();
					break;
				}
			}
		}
		else {
			for(int i=0;i<8;i++) {
				p = chessBoard.getChessSquare(7, i).getChessPiece();
				if(p!=null && p instanceof ChessPiecePawn){
					selection = guiChessBoard.promotion();
					break;
				}
			}
		}
		
		switch(selection) {
		case "Queen":
			newp= new ChessPieceQueen(p.getColor(),null,p.getX(),p.getY());
			break;
		}
		
	}
	
	//MAIN
	public static void main(String[] args) throws IOException
	{
		ConnectionScreen myScreen = new ConnectionScreen();
		myScreen.setVisible(true);
	}
}
