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
	
	public GUIChessBoard(ChessBoard board)
	{
		this.board = board;
		this.setLayout(new GridLayout(8,8));
		
		//Prepare guibuttons on board
		guiBoardButtons = new JButton[8][8];
		for(int y = 0; y < 8; y++){
			for(int x = 0; x < 8; x++){
				guiBoardButtons[x][y] = new JButton();
				guiBoardButtons[x][y].setBackground(board.getChessSquare(x, y).getColor());
				guiBoardButtons[x][y].addMouseListener(this);
				add(guiBoardButtons[x][y]);
			}
		}

		setSize(800,800);
	}

	
	/** The logic stored in this method will determine which chess square the user clicked on.  This method uses the x and y values of the user's 
	 *  last "click" to determine which chess square is accessed.
	 *  @param value: a coordinate
	 *  @return: the appropriate chess square or -999 if the user clicked on a portion of the frame that did not include any of the chess board
	 */
	public void processInput(int x, int y)
	{
		if (x == -999 || y == -999)
			System.out.println("  INVALID INPUT AT (" + x + ", " + y + ")\n");
		else{
			System.out.println("Clicked on chess square:  (" + x + ", " + y + ")");
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
	public void update() {
		for (int i=0; i<8; i++) {
			for (int j=0; j<8; j++) {
				if (this.board.getChessSquare(i, j).isEmpty())
					this.guiBoardButtons[i][j].setIcon(null);
				else
					this.guiBoardButtons[i][j].setIcon(this.board.getChessSquare(i, j).getChessPiece().getRepresentation());
			}
				
		}
	}
}
