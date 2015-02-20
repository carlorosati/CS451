package main;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class Client {
	
	public static void main(String[] args) throws IOException{
		System.out.println("Attempting to connect");
		Socket sock = new Socket(InetAddress.getByName("localhost"),4401);

		System.out.println("Connected");
		
		sock.close();
	}
}
