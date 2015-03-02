package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;
import javax.swing.table.*;

public class GUIChessBoard extends JFrame implements MouseListener
{
	/**
	 * STANDARD JAVA CONVENTION
	 */
	private static final long serialVersionUID = 1L;
	private ChessBoard board;
	private JTable table;
	private JButton[][] guiBoardButtons;
	
	public GUIChessBoard(ChessBoard board)
	{
		this.board = board;
		
		guiBoardButtons = new JButton[8][8];
		for(int x = 0; x < 8; x++){
			for(int y = 0; y < 8; y++){
				guiBoardButtons[x][y] = new JButton("Insert Chess Piece Here");
				guiBoardButtons[x][y].setBackground(board.getChessSquare(x, y).getColor());
			}
		}
	      
		TableModel dataModel = new AbstractTableModel() {
			public int getColumnCount() { return 8; }
	          public int getRowCount() { return 8;}
	          public Object getValueAt(int row, int col) { return guiBoardButtons[row][col]; }
	     };
		
	     table = new JTable(dataModel){
    	    public TableCellRenderer getCellRenderer( int row, int column ) {
    	        return new JButtonTableRenderer();
    	    }
	    };

		
		table.addMouseListener(this);
		add(table);
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
		int x = table.getSelectedColumn();
		int y = table.getSelectedRow();
		
		processInput(x, y);
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
