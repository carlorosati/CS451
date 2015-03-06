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
		for(int x = 0; x < 8; x++){
			for(int y = 0; y < 8; y++){
				guiBoardButtons[x][y] = new JButton();
				guiBoardButtons[x][y].setBackground(board.getChessSquare(x, y).getColor());
				guiBoardButtons[x][y].addMouseListener(this);
				
				//Conditional for starting line up
				//Setting the Pawn
				if (y == 1){
					this.board.getChessSquare(x,y).setChessPiece(new ChessPiecePawn(Color.BLACK, null, x, y));
					//guiBoardButtons[x][y].setIcon(this.board.getChessSquare(new ChessPiecePawn(Color.BLACK, null, x, y))
				}else if(y == 6){
					this.board.getChessSquare(x,y).setChessPiece(new ChessPiecePawn(Color.WHITE, null, x, y));
				//Every piece but the pawn
				}else if(y == 0 || y == 7){
					Color color; 
					if(y == 0){
						color = Color.BLACK;
					}else{
						color = Color.WHITE;
					}
					
					//Setting the Rook
					if (x == 0 || x == 7){
						this.board.getChessSquare(x,y).setChessPiece(new ChessPieceRook(color, null, x, y));
					
					//Setting the Knight
					}else if(x == 1 || x == 6){
						this.board.getChessSquare(x,y).setChessPiece(new ChessPieceKnight(color, null, x, y));
					
					//Setting the Bishop
					}else if(x == 2 || x == 5){
						this.board.getChessSquare(x,y).setChessPiece(new ChessPieceBishop(color, null, x, y));
					
					//Setting the Queen
					}else if(x == 3){
						this.board.getChessSquare(x,y).setChessPiece(new ChessPieceKing(color, null, x, y));
					
					//Setting the King
					}else if(x == 4){
						this.board.getChessSquare(x,y).setChessPiece(new ChessPieceQueen(color, null, x, y));
					}
					
					guiBoardButtons[x][y].setIcon(this.board.getChessSquare(x, y).getChessPiece().getRepresentation());
				}
				
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
