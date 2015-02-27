/**
 * This class represents individual player objects that will play a virtual game of chess against each other.
 */


package main;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class Player
{
	//INSTANCE VARIABLES
	private Color color;
	private String name;
	private List<ChessPiece> pieces;
	
	public Player(Color color, String name)
	{
		this.color = color;
		this.name = name;
		this.pieces = new ArrayList<>();
	}
	
	public Color getColor()
	{
		return color;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void addPieces(ChessPiece piece)
	{
		pieces.add(piece);
	}
}
