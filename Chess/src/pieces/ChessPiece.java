package pieces;

import chess.Player;
import chess.PlayerColor;
import chess.Cell;
/**
 * Abstract class for all chess pieces
 * @author Joshua Yu
 * @author Jie Ouyang
 *
 */
public abstract class ChessPiece {

	/**
	 * stores current board
	 */
	private Cell[][] board;
	
	/**
	 * position of chess piece
	 */
	private Cell position;
	
	/**
	 * player that owns the piece
	 */
	private Player player;
	
	/**
	 * if the piece is at it's initial position. used for castling and pawn
	 */
	private boolean initialPosition = true;

	/**
	 * promotion for pawn
	 */
	private boolean promo = false;
	
	/**
	 * indicates whether has been castling
	 */
	private boolean castling = false;
	
	/**
	 * indicates whether pawn has moved two spaces for enpassant
	 */
	private boolean pawnTwo = false;
	
	/**
	 * Indentification of chess piece
	 * @return chess letter
	 */
	public abstract String getChessName();
	
	/**
	 * Validates the piece on hand
	 * @param target validates if the piece can legally move to the target position
	 * @return true,false
	 */
	public abstract boolean isValid(Cell target);

	/**
	 * Whether or not pawn can move two spaces
	 * @param pawnTwo true,false
	 */
	public void setPawnTwo(Boolean pawnTwo){
		this.pawnTwo = pawnTwo;
	}
	
	/**
	 * Retrieve if pawn can move two spaces
	 * @return true,false
	 */
	public boolean getPawnTwo(){
		return pawnTwo;
	}
	
	/**
	 * Set piece under castling
	 * @param castling true,false
	 */
	public void setCastling(Boolean castling){
		this.castling = castling;
	}
	
	/**
	 * Know if the pieces can be castled
	 * @return true,false
	 */
	public boolean getCastling(){
		return castling;
	}
	
	/**
	 * Set pawn in promotion
	 * @param promo true,false
	 */
	public void setPromo(Boolean promo){
		this.promo = promo;
	}
	
	/**
	 * Know if pawn can be promoted
	 * @return true,false
	 */
	public boolean getPromo(){
		return promo;
	}
	
	/**
	 * Set whether or not chess piece is in it's initial position
	 * @param initialPosition true, false
	 */
	public void setInitialPosition(Boolean initialPosition){
		this.initialPosition = initialPosition;
	}
	
	/**
	 * Whether or not chess piece is in intial position
	 * @return true, false
	 */
	public boolean getInitialPosition(){
		return initialPosition;
	}
	
	/**
	 * Set the current board in game class
	 * @param board board with all current chess pieces
	 */
	public void setBoard(Cell[][] board) {
		this.board = board; 
		}
	
	/**
	 * Get the board with chess piece movement
	 * @return board
	 */
	public Cell[][] getBoard(){
		return board;
	}
	
	/**
	 * Set player for the chess piece
	 * @param player playerWhite, playerBlack
	 */
	public void setPlayer(Player player){
		this.player = player;
	}
	
	/**
	 * Get chess piece's owner
	 * @return playerWhite, playerBlack
	 */
	public Player getPlayer(){
		return player;
	}
	
	/**
	 * Sets position of the chess piece
	 * @param position Cell x y coordination
	 */
	public void setPosition(Cell position){
		this.position = position;
	}
	
	/**
	 * Get position of the chess piece
	 * @return Cell x y coordination
	 */
	public Cell getPosition(){
		return position;
	}
	
	/**
	 * Check whether moving in X or Y direction is valid
	 * @param target destination from user input
	 * @return true, false
	 */
	public boolean checkMoveXY(Cell target){
		int direction = 0;
		int x = 0;
		int y = 0;
		if(target.getY() == getPosition().getY()){
			direction = -1;
			if(position.getX() < target.getX()){
				x = 1;
			} else {
				x = -1;
			}
			
		} else if(target.getX() == getPosition().getX()){
			
			direction = 1;
			if(position.getY() < target.getY()){
				y = 1;
			} else {
				y = -1;
			}
		}
		if(direction == 1){
			int posY = position.getY()+y;
			while((y == 1 && posY < target.getY()) || (y == -1 && posY > target.getY())){
				

				if(board[posY][position.getX()].getPiece() != null){


					return false;
				}
				
				posY += y;
				
			}
		} else if(direction == -1){
			int posX = position.getX()+x;
			while((x == 1 && posX < target.getX()) || (x == -1 && posX > target.getX())){
				
				if(board[position.getY()][posX].getPiece() != null){


					return false;
				}
				
				posX += x;
				
			}
		}
		
		return true;
	}
	
	/**
	 * Checks whether diagonal movements are valid
	 * @param target destination of user input
	 * @return true,false
	 */
	public boolean checkMoveDiagonal(Cell target){
		int x;
		int y;
		if(position.getX() < target.getX()){
			x = 1;
		} else {
			x = -1;
		}
		if(position.getY() < target.getY()){
			y = 1;
		} else {
			y = -1;
		}
		int posX = position.getX()+x;
		int posY = position.getY()+y;
		while((x == 1 && posX < target.getX()) || (x == -1 && posX > target.getX())){
			if(board[posY][posX].getPiece() != null){


				return false;
			}
			
			posY += y;
			posX += x;
			
		}
		return true;
		
	}
	
}
