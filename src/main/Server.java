package main;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	
	public static void main(String[] args) throws IOException
	{
		ServerSocket sock = new ServerSocket(4401);
		System.out.println("Waiting for socket");
		Socket clientSock = sock.accept();
		System.out.println("Message received.");
		
		sock.close();
		clientSock.close();
	}
}