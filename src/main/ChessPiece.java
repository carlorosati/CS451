package main;

import java.awt.Color;
import java.awt.Image;

public abstract class ChessPiece
{
	private Color color;
	private Image representation;
	private int x, y;
	
	public ChessPiece(Color color, Image representation, int x, int y)
	{
		this.color = color;
		this.representation = representation;
		this.x = x;
		this.y = y;
	}
	
	public Color getColor()
	{
		return color;
	}
	
	public Image getRepresentation()
	{
		return representation;
	}
	
	public int getX()
	{
		return x;
	}
	
	public int getY()
	{
		return y;
	}
	
	public void updatePosition(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	/**
	 * This abstract method determines whether or not this specific type of chess piece can move to location (x, y) on the chess board
	 * @param board: board object used for movement checking
	 * @param x: (attempted) new x position on board
	 * @param y: (attempted) new y position on board
	 */
	public abstract boolean isMoveValid(ChessBoard board, int x, int y);
}
