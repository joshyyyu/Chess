package pieces;

import chess.Cell;
/**
 * Bishop Chess Piece
 * @author Joshua Yu
 * @author Jie Ouyang
 *
 */
public class Bishop extends ChessPiece{

	/**
	 * Return chess name B
	 */
	@Override
	public String getChessName() {
		// TODO Auto-generated method stub
		return "B";
	}

	/**
	 * Validate Bishop move to target
	 */
	@Override
	public boolean isValid(Cell target) {
		
		int x = Math.abs(target.getX() - getPosition().getX());
		int y = Math.abs(target.getY() - getPosition().getY());
		
		if(x != y){
			return false;
		}
		
		if(!checkMoveDiagonal(target)){
			//System.out.println("Invalid Move: Bishop");
			return false;
		}
		
		return true;
	}

}
