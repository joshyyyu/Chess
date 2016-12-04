package chess;

import java.util.ArrayList;

import pieces.ChessPiece;
/**
 * Players Profile
 * @author Joshua Yu
 * @author Jie Ouyang
 *
 */
public class Player {

	/**
	 * Color of the player
	 */
	private PlayerColor color;

	/**
	 * ArrayList of all the player's pieces
	 */
	private ArrayList<ChessPiece> chessPieces = new ArrayList<>();
	
	/**
	 * player's opponent
	 */
	private Player opponent;
	
	/**
	 * player's king
	 */
	private ChessPiece king;
	
	/**
	 * Player constructor indicated by color
	 * @param color player's color
	 */
	public Player(PlayerColor color){
		this.color = color;
		
	}
	
	/**
	 * Retrieving player's color
	 * @return white, black
	 */
	public PlayerColor getColor() {
		return color;
	}
	
	/**
	 * Set player's opponent
	 * @param opponent opposite color, white or black
	 */
	public void setOpponent(Player opponent){
		this.opponent = opponent;
		
	}
	
	/**
	 * Retrieve player's opponent
	 * @return white,black
	 */
	public Player getOpponent() {
		return opponent;
	}
	
	/**
	 * Add pieces to the arraylist of player's chess pieces
	 * @param piece current piece under work
	 */
	public void setPieces(ChessPiece piece){
		chessPieces.add(piece);
	}
	
	/**
	 * Get piece from the arraylist of player's chess pieces
	 * @return arraylist of chess pieces
	 */
	public ArrayList<ChessPiece> getPieces(){
		return chessPieces;
	}
	
	/**
	 * Set player's king
	 * @param king king piece
	 */
	public void setKing(ChessPiece king){
		this.king = king;
		
	}
	
	/**
	 * Retrieve king piece
	 * @return king piece
	 */
	public ChessPiece getKing() {
		return king;
	}
	
}
