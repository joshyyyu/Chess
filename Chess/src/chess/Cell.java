package chess;


import pieces.ChessPiece;
/**
 * Cell from the 8 x8 chess board
 * @author Joshua Yu
 * @author Jie Ouyang
 *
 */
public class Cell {
	/**
	 *  x horizontal coordination
	 *  y vertical coordination
	 */
	
	private int x;
	private int y;
	
	/**
	 *  piece individual chess piece
	 */
	private ChessPiece piece;
	
	/**
	 * 
	 * @return x coordinate
	 */
	public int getX(){
		return x;
	}
	
	/**
	 * 
	 * @return y coordinate
	 */
	public int getY(){
		return y;
	}
	
	/**
	 * 
	 * @param row y coordinate
	 * @param col x coordinate
	 */
	public Cell(int row, int col){
		this.x = col;
		this.y = row;
	}
	
	/**
	 * 
	 * @return chess piece on this cell coordinate
	 */
	public ChessPiece getPiece(){
		return piece;
	}
	
	/**
	 * 
	 * @param piece set a chess piece in the cell coordinate
	 */
	public void setPiece(ChessPiece piece){
		this.piece = piece;
	}
	
}
