package pieces;

import chess.Cell;
import chess.PlayerColor;
/**
 * King class
 * @author Joshua Yu
 * @author Jie Ouyang
 *
 */
public class King extends ChessPiece{
	
	/**
	 * Return name K
	 */
	@Override
	public String getChessName() {
		// TODO Auto-generated method stub
		return "K";
	}

	/**
	 * See if king piece has legal moves one move in each direction
	 */
	@Override
	public boolean isValid(Cell target) {
		int x = Math.abs(target.getX() - getPosition().getX());
		int y = Math.abs(target.getY() - getPosition().getY());

		if(x <= 1 && y <= 1){
			return true;
		}
		
		if(getInitialPosition()){
			int tempX = -1;
			if(target.getX() == 6){
				tempX = 7;
			} else if(target.getX() == 2){
				tempX = 0;
			} else {
				return false;
			}
			Cell[][] board = getBoard();
			if(getPlayer().getColor() == PlayerColor.WHITE){
				if(board[7][tempX].getPiece().getChessName() == "R"
						&& board[7][tempX].getPiece().getInitialPosition()){
					if(!checkMoveXY(board[7][tempX])){
						return false;
					}
					setCastling(true);
					return true;
				}
			} else {
				if(board[7][tempX].getPiece().getChessName() == "R" 
						&& board[0][tempX].getPiece().getInitialPosition()){
					if(!checkMoveXY(board[0][tempX])){
						return false;
					}
					setCastling(true);
					return true;
				}
			}
		}
		
		return false;
	
	}

}
