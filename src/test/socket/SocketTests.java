package test.socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import junit.framework.Assert;

import main.ChessBoard;
import main.NormalChessBoard;

import org.junit.Test;

public class SocketTests {
	public Socket socket1, socket2;
	public ObjectInputStream inputStream;
	public ObjectOutputStream outputStream;
	public ChessBoard board;
	ServerThread t;
	
	public SocketTests(){
		try {
			t = new ServerThread(this);
			t.start();
			Thread.sleep(300);
			socket2 = new Socket("localhost",4401);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		board = new NormalChessBoard();
		board.initialize(null);
	}
	
	public void SetSocket(Socket sock){
		socket1 = sock;
	}
	
	@Test
	public void canSendBoardOverSocket(){
		try {
			//Set up socket streams
			outputStream = new ObjectOutputStream(socket1.getOutputStream());
			outputStream.flush();
			inputStream = new ObjectInputStream(socket2.getInputStream());
			
			//Send board over socket
			outputStream.writeObject(board);
			ChessBoard socketBoard = (ChessBoard)inputStream.readObject();
			
			//Assert that board sent over stream and one received is the same
			Assert.assertEquals(socketBoard, board);
			

			t.Stop();
			t.join();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Assert.fail();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Assert.fail();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Assert.fail();
		}
	}
}

