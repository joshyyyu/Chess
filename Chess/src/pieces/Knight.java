package pieces;

import chess.Cell;
/**
 * Knight Class
 * @author Joshua Yu
 * @author Jie Ouyang
 *
 */
public class Knight extends ChessPiece{

	/**
	 * Return chess name N
	 */
	@Override
	public String getChessName() {
		// TODO Auto-generated method stub
		return "N";
	}

	/**
	 * Validate knight movement in 2 square in either x or y direction and 1 in y or x respectively
	 */
	@Override
	public boolean isValid(Cell target) {
		
		int x = Math.abs(target.getX() - getPosition().getX());
		int y = Math.abs(target.getY() - getPosition().getY());
		
		if(x == 2 && y == 1){
			return true;
		}
		if(x == 1 && y == 2){

			return true;
		}
		
		return false;
	}

}
