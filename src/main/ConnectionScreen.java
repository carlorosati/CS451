package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.*;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;

public class ConnectionScreen extends JFrame implements ActionListener, MouseListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JButton connectToGame, createGame;
	JPanel gamePanel;
	JTextField portField, hostField;
	JLabel portLabel, hostLabel;
	ChessGame game;
	
	public ConnectionScreen(){
		//Creating the GAME UI
		setLayout(new BorderLayout());
		createGame = new JButton("Create new game.");
		createGame.addMouseListener(this);
		connectToGame = new JButton("Connect to game.");
		connectToGame.addMouseListener(this);
		
		JLabel chessLabel = new JLabel("Chess Game", JLabel.CENTER);
		chessLabel.setFont(new Font("Serif", Font.PLAIN, 24));
		this.add(chessLabel, BorderLayout.NORTH);
		this.add(createGame, BorderLayout.WEST);
		this.add(connectToGame, BorderLayout.EAST);
		this.setSize(new Dimension(500,200));
		
		//Creating elements of panel that will be shown
		portLabel = new JLabel("Port Number");
		hostLabel = new JLabel("Host Name");
		portField = new JTextField(5);
		hostField = new JTextField(20);
		
		//Panel that will be shown when a button is clicked
		gamePanel = new JPanel();
		gamePanel.add(portLabel);
		gamePanel.add(portField);
		gamePanel.add(Box.createHorizontalStrut(10));
		gamePanel.add(hostLabel);
		gamePanel.add(hostField);
		
		game = new ChessGame(Color.WHITE);
		game.getGUI().setVisible(false);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == createGame){
			hostLabel.setVisible(false);
			hostField.setVisible(false);
			int op = JOptionPane.showConfirmDialog(null, gamePanel, "Create a Game", JOptionPane.OK_CANCEL_OPTION);
			
			if(op == JOptionPane.OK_OPTION){
				int port = -1;
	
				try{
					port = Integer.parseInt(portField.getText());
				} catch(NumberFormatException ex){
					JOptionPane.showMessageDialog(this, "Please enter a valid number");
				}
				
				//If a proper port, make server socket
				if(port > 0 && port <= 65535){
					System.out.println("Creating a game with port " + port);
					
					JLabel label = new JLabel("Waiting for opponent...");
					add(label, BorderLayout.CENTER);
					// Open the server socket
					ServerSocket serverSocket;
					Socket clientSocket = null;
					try {
						serverSocket = new ServerSocket( port );
						
						// Connect the client
						clientSocket = serverSocket.accept();
					}catch(IOException ex){
						ex.printStackTrace();
					}
					
					if(clientSocket != null){
						game.getGUI().setVisible(true);
						game.SetSocket(clientSocket);
						this.setVisible(false);
					}
				}
			}
		}
		
		if(e.getSource() == connectToGame){
			hostLabel.setVisible(true);
			hostField.setVisible(true);
			
			int op = JOptionPane.showConfirmDialog(null, gamePanel, "Create a Game", JOptionPane.OK_CANCEL_OPTION);
			
			if(op == JOptionPane.OK_OPTION){
				int port = -1;
	
				//If a string was returned, say so.
				try{
					port = Integer.parseInt(portField.getText());
				} catch(NumberFormatException ex){
					JOptionPane.showMessageDialog(this, "Please enter a valid number");
				}
				
				Socket socket = null;
				
				if(port > 0 && port <= 65535){
					System.out.println("Connecting to a game with port " + port + " and host name " + hostField.getText());
					try{
					    socket =
						    	new Socket( InetAddress.getByName(hostField.getText()), port );
					}catch(IOException ex){
						ex.printStackTrace();
					}
				}
				
				if(socket != null){
					game.getGUI().setVisible(true);
					game.SetSocket(socket);
					this.setVisible(false);
				}
				
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
