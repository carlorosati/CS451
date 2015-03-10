package main;

import java.awt.Color;
import java.io.EOFException;
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
		//notify the white player of their first turn
		if(chessBoard.getCurrent().equals(guiChessBoard.getPlayerColor()))
			guiChessBoard.setStatus("Status: It is your turn.");
		try {
			//get output stream from the socket
			ObjectOutputStream out = new ObjectOutputStream(peerSocket.getOutputStream());
			out.flush();
			//get input stream from the socket
			ObjectInputStream in = new ObjectInputStream(peerSocket.getInputStream());
			//loop while the game is not over
			while(!isOver()){
				//check to see if it is the user's turn
				if(chessBoard.getCurrent().equals(guiChessBoard.getPlayerColor())){
					Thread.sleep(100);
					//check to see if a move was made in the gui
					if (guiChessBoard.getMoved()){
						System.out.println("move made");
						// Before sending the board check for pawn promotion
						promotion(chessBoard.getCurrent());
						//set it to the opponents turn
						chessBoard.setCurrent(
							chessBoard.getCurrent().equals(Color.BLACK)
							? Color.WHITE
							: Color.BLACK
						);
						//send the board to opponent
						out.writeObject(chessBoard);
						out.flush();
						//check to see if the user won
						over=chessBoard.isCheckMate(chessBoard.getCurrent());
						if(over) {
							guiChessBoard.showVictory();
						}
					}
				} else {
					//opponent turn
					guiChessBoard.setStatus("Status: Your Opponent's Turn.");
					System.out.println("Waiting for opponent turn");
					//get board from opponent
					chessBoard = (ChessBoard) in.readObject();
					//pass board to gui
					guiChessBoard.setBoard(chessBoard);
					//check to see if the user lost
					over=chessBoard.isCheckMate(chessBoard.getCurrent());
					if(over) {
						guiChessBoard.showLoss();
					}
					//check if the user are in check
					else if(chessBoard.isCheck(chessBoard.getCurrent())){
						guiChessBoard.showCheck();
					}
					//reset the moved boolean in the gui to allow the user to make a move
					guiChessBoard.setMoved(false);
					guiChessBoard.setStatus("Status: It is your turn.");
				}	 
			}
			//handle opponent disconnect
		}catch(SocketException e) {
			over = true;
			guiChessBoard.showDisconnected();
		}
		catch(EOFException e) {
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
	
	//pawn promotion
	public void promotion(Color c)
	{
		ChessPiece p = null;
		ChessPiece newp = null;
		String selection = "";
		//check for a white promotable pawn in column 0
		if (c.equals(Color.WHITE)){
			//loop over column
			for(int i=0;i<8;i++) {
				//get piece or null
				p = chessBoard.getChessSquare(0, i).getChessPiece();
				//check to see if square has a piece and it is a pawn
				if(p!=null && p instanceof ChessPiecePawn){
					//prompt user for promotion selection
					selection = guiChessBoard.promotion();
					break;
				}
			}
		}
		//check for a black promotable pawn in column 7
		else {
			//loop over column
			for(int i=0;i<8;i++) {
				//get piece or null
				p = chessBoard.getChessSquare(7, i).getChessPiece();
				//check to see if square has a piece and it is a pawn
				if(p!=null && p instanceof ChessPiecePawn){
					//prompt user for promotion selection
					selection = guiChessBoard.promotion();
					break;
				}
			}
		}
		//create new piece if any
		switch(selection)
		{
			//create Queen
			case "Queen":
				newp= new ChessPieceQueen(p.getColor(),null,p.getX(),p.getY());
				break;
			//create Rook
			case "Rook":
				newp= new ChessPieceRook(p.getColor(),null,p.getX(),p.getY());
				break;
			//create Bishop
			case "Bishop":
				newp= new ChessPieceBishop(p.getColor(),null,p.getX(),p.getY());
				break;
			//create Knight
			case "Knight":
				newp= new ChessPieceKnight(p.getColor(),null,p.getX(),p.getY());
				break;
			//nothing to create
			default:
				selection="";
				break;
		}
		//replace pawn with new piece if any
		if (!selection.equals(""))
		{
			chessBoard.update(newp, p.getX(), p.getY());
			guiChessBoard.updateBoard();
		}
		
	}
	
	//MAIN
	public static void main(String[] args) throws IOException
	{
		ConnectionScreen myScreen = new ConnectionScreen();
		myScreen.setVisible(true);
	}
}
