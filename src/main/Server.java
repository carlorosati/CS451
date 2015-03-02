package main;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	ChessGame cg;
	
	public static void main(String[] args) throws IOException
	{

		// Server gets to be white
		cg = new ChessGame(Color.WHITE);
		
		// Listen on port for connections
		int portNumber = 4401;

		if ( args.length > 0 ) {

			portNumber = Integer.parseInt(args[0]);

		}

		System.out.println("Opening connection at " + InetAddress.getLocalHost().getHostAddress() + " on port " + portNumber);

		try (
				// Open the server socket
				ServerSocket serverSocket = new ServerSocket( portNumber );
				
				// Connect the client
				Socket clientSocket = serverSocket.accept();
		
				// Create an object output stream
				ObjectOutputStream out =
						new ObjectOutputStream( clientSocket.getOutputStream() );
				
				// Create an object input stream
				ObjectInputStream in =
						new ObjectInputStream( clientSocket.getInputStream() );
		) {
			// Server stop listening for connections
			serverSocket.close();

			// Get the initial chessboard from the client
			ChessBoard cb = (ChessBoard) in.readObject();

			while( cb ) {

				// Wait for the first board to come from the client
				ChessBoard cb = (ChessBoard) in.readObject();
				System.out.println('Got chessboard from client..');

				// Make our move
					// TODO:

				// Send the chess board back to the client
				out.writeObject( cb );
				System.out.println('Sent chessboard to client..')

			}
			

			// Server gets to be white
			ChessGame cg;
			// Close the client socket
			clientSocket.close();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}