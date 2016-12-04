package pieces;

import chess.Cell;
/**
 * Queen Class
 * @author Joshua Yu
 * @author Jie Ouyang
 *
 */
public class Queen extends ChessPiece{

	/**
	 * Returns chess name Q
	 */
	@Override
	public String getChessName() {
		// TODO Auto-generated method stub
		return "Q";
	}

	/**
	 * Checks direction
	 */
	@Override
	public boolean isValid(Cell target) {
		
		if(validDiagonalPosition(target)){
			if(!checkMoveDiagonal(target)){
				//System.out.println("Invalid Move: Queen Diagonal");
				return false;
			}

			return true;
		} else if(validXYPosition(target)){
			
			if(!checkMoveXY(target)){
				//System.out.println("Invalid Move: Queen XY");

				return false;
			}
			
			return true;
		}
		/*if(!checkMoveDiagonal(target)){
			System.out.println("Invalid Move: Bishop");
			return false;
		}*/
		
		return false;
	}
	
	/**
	 * Checks XY direction
	 * @param target destination from user input
	 * @return true, false
	 */
	public boolean validXYPosition(Cell target){
		if(target.getY() == getPosition().getY()||target.getX() == getPosition().getX()){
			return true;
		}
		
		return false;
	}
	
	/**
	 * Check diagonal direction
	 * @param target destination from user input
	 * @return true, false
	 */
	public boolean validDiagonalPosition(Cell target){
		int x = Math.abs(target.getX() - getPosition().getX());
		int y = Math.abs(target.getY() - getPosition().getY());
		
		if(x != y){
			return false;
		}
		return true;
		
		
	}

}
