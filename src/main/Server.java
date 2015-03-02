package main;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	
	public static void main(String[] args) throws IOException
	{	
		// Listen on port for connections
		int portNumber = 4401;
		try (
				// Open the server socket
				ServerSocket serverSocket = new ServerSocket(portNumber);
				
				// Connect the client
				Socket clientSocket = serverSocket.accept();
		
				// Create an object output stream
				ObjectOutputStream out =
						new ObjectOutputStream(clientSocket.getOutputStream());
				
				// Create an object input stream
				ObjectInputStream in =
						new ObjectInputStream(clientSocket.getInputStream());
		) {
			// Server stop listening for connections
			serverSocket.close();
			
			// Read a chess board from the client
			//String cb = (String) in.readObject();
			
			String h = "Well hello there!";
			out.writeObject(h);
			
			// Close the client socket
			clientSocket.close();
		}
		
	}
}