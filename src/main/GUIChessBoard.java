package main;

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
		
		guiBoardButtons = new JButton[8][8];
		for(int x = 0; x < 8; x++){
			for(int y = 0; y < 8; y++){
				guiBoardButtons[x][y] = new JButton("Insert Chess Piece Here");
				guiBoardButtons[x][y].setBackground(board.getChessSquare(x, y).getColor());
				guiBoardButtons[x][y].addMouseListener(this);
				if(this.board.getChessSquare(x, y).getChessPiece() != null && this.board.getChessSquare(x,y).getChessPiece().getRepresentation() != null){
					guiBoardButtons[x][y].setIcon(this.board.getChessSquare(x, y).getChessPiece().getRepresentation());
				}
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
}
