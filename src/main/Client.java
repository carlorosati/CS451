package main;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class Client {

	ChessGame cg;
	
	public static void main(String[] args) throws IOException, ClassNotFoundException
	{
		
		// Client gets to be Black
		cg = new ChessGame(Color.BLACK);

		int port = 4401;
		String ipAddress;

		// Get the IP address from the first argument
		if ( args.length > 0 ) {

			ipAddress = args[0];

		}

		else if ( args.length > 1 ) {

			ipAddress = args[0];
			port = Integer.parseInt( args[1] );
			
		}

		else {

			ipAddress = InetAddress.getLocalHost().getHostAddress();

		}

		System.out.println("Connecting to " + ipAddress + " on port " + port);

		try (
				// Open a client socket and connect to the server on localhost on port 4401
			    Socket socket =
			    	new Socket( ipAddress, port );
				
				// Open an object output stream reader
				ObjectOutputStream out =
			        new ObjectOutputStream( socket.getOutputStream() );
				
				// Open object input stream reader
				ObjectInputStream in =
			        new ObjectInputStream( socket.getInputStream() );
			) {
			
			// Create a new chess board
			String s;// = new String("Hello, World!");
			
			// Write a Chess board to the Server
			// 
			s = (String) in.readObject();
			System.out.println(s);
			
			// Close the socket when done
			socket.close();
		}
		
	}
}

