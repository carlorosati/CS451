package main;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class Client {
	
	public static void main(String[] args) throws IOException
	{
		
		try (
				// Open a client socket and connect to the server on localhost on port 4401
			    Socket socket =
			    	new Socket( InetAddress.getByName("localhost"), 4401 );
				
				// Open an object output stream reader
				ObjectOutputStream out =
			        new ObjectOutputStream( socket.getOutputStream() );
				
				// Open object input stream reader
				ObjectInputStream in =
			        new ObjectInputStream( socket.getInputStream() );
			) {
			
			// Create a new chess board
			ChessBoard cb = new NormalChessBoard();
			
			// Write a Chess board to the Server
			out.writeObject( cb );
			
			// Close the socket when done
			socket.close();
		}
		
	}
}

