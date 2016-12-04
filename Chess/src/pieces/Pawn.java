package pieces;

import chess.Cell;
import chess.PlayerColor;
/**
 * Pawn Class
 * @author Joshua Yu
 * @author Jie Ouyang
 *
 */
public class Pawn extends ChessPiece {

	/**
	 * Return chess name p
	 */
	@Override
	public String getChessName() {
		// TODO Auto-generated method stub
		return "p";
	}

	/**
	 * Checks movement in x direction. Checks for promotion. Checks for enpassant
	 */
	@Override
	public boolean isValid(Cell target) {
		int x = target.getX() - getPosition().getX();
		int y = target.getY() - getPosition().getY();
		if (getPlayer().getColor() == PlayerColor.WHITE) {
			if (y == -1 && x == 0) {
				if(getPawnTwo() == true){
					setPawnTwo(false);
				}
				if (target.getPiece() != null) {
					//System.out.println("Blocking Pawn");
					return false;
				}
				if (target.getY() == 0) {

					setPromo(true);
				}
				return true;
			}

			if (y == -2 && x == 0) {
				if (!getInitialPosition()) {
					return false;
				}
				if (!checkMoveXY(target)) {

					return false;
				}

				if (target.getPiece() != null) {
					//System.out.println("Blocking Pawn");
					return false;
				}
				setInitialPosition(false);
				setPawnTwo(true);
				return true;
			}

			if (y == -1 && x == 1 || y == -1 && x == -1) {
				if(getPawnTwo() == true){
					setPawnTwo(false);
				}
				if (target.getPiece() != null && target.getPiece().getPlayer().getColor() != getPlayer().getColor()) {
					if (target.getY() == 0) {
						setPromo(true);
					}
					return true;
				} else if(target.getPiece() == null){
					Cell[][] board = getBoard();
					if(board[target.getY()+1][target.getX()].getPiece() != null && board[target.getY()+1][target.getX()].getPiece().getChessName() == "p" &&
							board[target.getY()+1][target.getX()].getPiece().getPlayer().getColor() != getPlayer().getColor()){
						if(board[target.getY()+1][target.getX()].getPiece().getPawnTwo()){
								return true;
						}
					}
				}

				return false;
			}

		}
		if (getPlayer().getColor() == PlayerColor.BLACK) {
			if (y == 1 && x == 0) {
				if(getPawnTwo() == true){
					setPawnTwo(false);
				}
				if (target.getPiece() != null) {
					//System.out.println("Blocking Pawn");
					return false;
				}
				if (target.getY() == 7) {
					setPromo(true);
				}
				return true;
			}
			
			if (y == 2 && x == 0) {
				if (!getInitialPosition()) {
					return false;
				}
				if (!checkMoveXY(target)) {

					return false;
				}

				if (target.getPiece() != null) {
					//System.out.println("Blocking Pawn");
					return false;
				}
				setInitialPosition(false);
				setPawnTwo(true);
				return true;
			}

			if (y == 1 && x == 1 || y == 1 && x == -1) {
				if(getPawnTwo() == true){
					setPawnTwo(false);
				}
				if (target.getPiece() != null && target.getPiece().getPlayer().getColor() != getPlayer().getColor()) {
					if (target.getY() == 7) {
						setPromo(true);
					}
					return true;
				} else if(target.getPiece() == null){
					Cell[][] board = getBoard();
					if(board[target.getY()-1][target.getX()].getPiece() != null && board[target.getY()-1][target.getX()].getPiece().getChessName() == "p" &&
							board[target.getY()-1][target.getX()].getPiece().getPlayer().getColor() != getPlayer().getColor()){
						if(board[target.getY()-1][target.getX()].getPiece().getPawnTwo()){
								return true;
						}
					}
				}

				return false;
			}

		}

		return false;
	}

}
