package pieces;

import chess.Cell;
/**
 * Rook Class
 * @author Joshua Yu
 * @author Jie Ouyang
 *
 */
public class Rook extends ChessPiece{

	/**
	 * Returns chess name R
	 */
	@Override
	public String getChessName() {
		// TODO Auto-generated method stub
		return "R";
	}

	/**
	 * Validates XY movement and castling
	 */
	@Override
	public boolean isValid(Cell target) {
		// TODO Auto-generated method stub
		
		if(target.getY() == getPosition().getY()||target.getX() == getPosition().getX()){
			if(!checkMoveXY(target)){
				//System.out.println("Invalid Move: Rook XY");

				return false;
			
			}

			return true;
		}
		return false;
	}

}
