package chess;

import pieces.ChessPiece;
/**
 * 
 * @author Joshua Yu
 * @author Jie Ouyang
 *
 */
public class Board {
/**
 * Create and show the chess board
 * @param board chess board with chess pieces from game class
 */
	public static void displayBoard(Cell[][] board){
		
		for(int col = 0; col < 8; col++){
			for(int row = 0; row< 8; row++){
				
				ChessPiece piece = board[col][row].getPiece();
				
				
				if(piece == null){
				if(row%2 == 0 && col%2 != 0){
					System.out.print("## ");
				} else if(row%2 != 0 && col%2 == 0){
					System.out.print("## ");
				}
				else{
					System.out.print("   ");
					
				}
			} else {
				System.out.print(piecePlayer(piece) + piece.getChessName() + " ");
			}
			
			}

			System.out.print(8-col);
			System.out.println();
			
		}
		System.out.println(" a  b  c  d  e  f  g  h");
	}
	
/**
 * 
 * @param piece individual chess piece
 * @return the letter of the color of the player
 */
	public static char piecePlayer(ChessPiece piece){
		char color;
		if(piece.getPlayer().getColor() == PlayerColor.WHITE){
			color = 'w';
		} else {
			color = 'b';
		}
		return color;
	}
	
}
