package test.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import junit.framework.Assert;

public class ServerThread extends Thread {
	SocketTests socketTests;
	boolean keepAlive = true;
	
	public ServerThread(SocketTests tests){
		socketTests = tests;
	}
	
	public void Run(){
		ServerSocket socket;
		try {
			socket = new ServerSocket(4401);
			Socket newSock = socket.accept();
			socketTests.SetSocket(newSock);
			while(keepAlive){}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void Stop(){
		keepAlive = false;
	}
}