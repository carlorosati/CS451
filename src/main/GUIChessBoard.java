package main;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

public class GUIChessBoard extends JFrame implements MouseListener
{
	/**
	 * STANDARD JAVA CONVENTION
	 */
	private static final long serialVersionUID = 1L;
	private ChessBoard board;
	private JButton[][] guiBoardButtons;
	private boolean firstClick = true;
	private int x,y;
	private Color me;;
	private boolean moved;
	private Color origBG;
	
	public GUIChessBoard(ChessBoard board)
	{
		this.board = board;
		this.setLayout(new GridLayout(8,8));
		moved = false;
		
		//Prepare guibuttons on board
		guiBoardButtons = new JButton[8][8];
		for(int y = 0; y < 8; y++){
			for(int x = 0; x < 8; x++){
				//Get internal representation
				ChessSquare sq = board.getChessSquare(x, y);
				ChessPiece pc = sq.getChessPiece();
				
				//Create GUI Representation
				guiBoardButtons[x][y] = new JButton();
				guiBoardButtons[x][y].setBackground(sq.getColor());

				// Turn off default button display fill and border
				guiBoardButtons[x][y].setOpaque(true);
				guiBoardButtons[x][y].setBorder(BorderFactory.createEmptyBorder());
				// guiBoardButtons[x][y].setContentAreaFilled(false);

				if(pc != null){
					guiBoardButtons[x][y].setIcon(pc.getRepresentation());
				}
				guiBoardButtons[x][y].addMouseListener(this);
				add(guiBoardButtons[x][y]);
			}
		}

		setSize(500,500);
	}

	
	/** The logic stored in this method will determine which chess square the user clicked on.  This method uses the x and y values of the user's 
	 *  last "click" to determine which chess square is accessed.
	 *  @param value: a coordinate
	 *  @return: the appropriate chess square or -999 if the user clicked on a portion of the frame that did not include any of the chess board
	 */
	public void processInput(int x, int y)
	{
		
		ChessSquare sq = board.getChessSquare(x, y);
		ChessPiece pc = sq.getChessPiece();
		
		if(pc != null){
			System.out.println("Clicked on " + pc.getClass().getSimpleName() + ": (" + x + ", " + y + ")");
		}else{
			System.out.println("Clicked on empty chess square:  (" + x + ", " + y + ")");
		}

		if(firstClick){
			if(pc != null && pc.getColor().equals(me) && board.getCurrent().equals(me)){
				System.out.println("test!!!");
				firstClick = false;
				this.x = x;
				this.y = y;
				origBG = guiBoardButtons[x][y].getBackground();
				guiBoardButtons[x][y].setBackground(Color.YELLOW);
				System.out.println("Clicking on first piece");
			}
		}else{
			firstClick = true;
			ChessPiece origPiece = board.getChessSquare(this.x, this.y).getChessPiece();
			boolean validMove = origPiece.move(board, x, y);
			if(validMove){
				System.out.println(origPiece.getClass().getSimpleName() + " can move to (" + x + ", " + y + ")");
				moved=true;
			}else{
				JOptionPane.showMessageDialog(this, "That isn't a valid move!", "Movement Validation", JOptionPane.WARNING_MESSAGE);
				System.out.println(origPiece.getClass().getSimpleName() + " can't move to (" + x + ", " + y + ")");
			}
			
			//Update board
			guiBoardButtons[this.x][this.y].setBackground(origBG);
			updateBoard();
		}
	}

	@Override
	public void mouseClicked(MouseEvent e)
	{
		for(int x = 0; x < 8; x++){
			for(int y = 0; y < 8; y++){
				if(e.getSource() == guiBoardButtons[x][y]){
					processInput(x,y);
				}
			}
		}
	}
	
	public void updateBoard(){
		for(int y = 0; y < 8; y++){
			for(int x = 0; x < 8; x++){
				//Get internal representation
				ChessSquare sq = board.getChessSquare(x, y);
				ChessPiece pc = sq.getChessPiece();
				
				//Update external representation
				if(pc != null){
					guiBoardButtons[x][y].setIcon(pc.getRepresentation());
				}else{
					guiBoardButtons[x][y].setIcon(null);
				}
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) 
	{

	}

	@Override
	public void mouseExited(MouseEvent e)
	{

	}

	@Override
	public void mousePressed(MouseEvent e)
	{

	}

	@Override
	public void mouseReleased(MouseEvent e)
	{

	}
	
	public Color getPlayerColor() {
		return me;
	}
	public void setBoard(ChessBoard b) {
		board = b;
		updateBoard();
	}
	public boolean getMoved() {
		return moved;
	}
	public void setMoved(boolean b) {
		moved = b;
	}
	public void setMe(Color c) {
		me = c;
	}
}
