package chess;

import java.util.Scanner;

import pieces.Bishop;
import pieces.ChessPiece;
import pieces.King;
import pieces.Knight;
import pieces.Pawn;
import pieces.Queen;
import pieces.Rook;
/**
 * All command's, inputs and piece movement
 * @author Joshua Yu
 * @author Jie Ouyang
 *
 */
public class Game {

	/**
	 * Initial board with 8 x 8 cells
	 */
	private Cell[][] board = new Cell[8][8];
	
	/**
	 * White player
	 */
	private Player playerWhite;
	
	/**
	 * Black player 
	 */
	private Player playerBlack;
	
	/**
	 * current player
	 */
	private Player currentPlayer;
	
	/**
	 * Runs the entire game called from Chess class
	 */
	public Game() {

		playerWhite = new Player(PlayerColor.WHITE);
		playerBlack = new Player(PlayerColor.BLACK);

		playerWhite.setOpponent(playerBlack);
		playerBlack.setOpponent(playerWhite);

		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 8; y++) {
				board[x][y] = new Cell(x, y);
			}
		}
		putChessPieces(playerWhite);
		putChessPieces(playerBlack);

		Board.displayBoard(board);
		Scanner sc = new Scanner(System.in);

		currentPlayer = playerWhite;
		while (true) {
			String player = currentPlayer.getColor() == PlayerColor.WHITE ? "White" : "Black";
			System.out.println();
			System.out.print(player + "'s move: ");
			String input = sc.nextLine().trim();
			System.out.println();
			inputMove(input);

		}

	}

	/**
	 * Reads all the input of the user
	 * @param input input from user
	 */
	private void inputMove(String input) {

		if(input.length() == 0){
			System.out.println("Illegal move, try again");
			return;
		}
		
		if(input.contains("resign") && input.length() == 6){
			String winner = " ";
			if(currentPlayer.getColor() == PlayerColor.WHITE){
				winner = "Black";
			} else {
				winner = "White";
			}
			System.out.println(winner + " wins");
			System.exit(0);
		}
		Scanner sc = new Scanner(System.in);
		if(input.contains("draw") && input.length() == 4){
			
				System.out.println("draw?");
				String drawInput = sc.nextLine().trim();
				if(drawInput.contains("draw")&& input.length() == 4){
					System.out.println("Draw");
					System.exit(0);
				} else {
					return;
				}
		}
		
		String[] move = input.split("\\s+");
		
		
		if(move.length > 3 || move.length < 2){
			System.out.println("Invalid Input");
			return;
		}
		
		if(move[0].length() != 2 || move[1].length() != 2){
			System.out.println("Invalid Input");
			return;
		}
		
		int rankFrom = (int) move[0].charAt(0) - 97;
		int fileFrom = Math.abs(8 - (Character.getNumericValue(move[0].charAt(1))));
		int rankTo = (int) move[1].charAt(0) - 97;
		int fileTo = Math.abs(8 - (Character.getNumericValue(move[1].charAt(1))));

		String promo = null;
		Cell temp = board[fileFrom][rankFrom];
		if(temp.getPiece() != null){
		if (move.length == 3) {
			
			if (board[fileFrom][rankFrom].getPiece().getChessName() != "p") {
				System.out.println("Invalid Input");
				return;
			}
			if(fileTo == 0 || fileTo == 7){
			promo = move[2];
			if(promo.length() > 1){
				System.out.println("Invalid Input");
				return;
			}
			if(!validPromo(promo)){
				System.out.println("Invalid Input");
				return;
			}
			} else {
				System.out.println("Invalid Input");
				return;
			}
		}
		}

		if (rankFrom < 0 || fileFrom < 0 || rankTo < 0 || fileTo < 0 || rankFrom > 7 || fileFrom > 8 || rankTo > 7
				|| fileTo > 8) {

			System.out.println("Invalid Input");
			return;

		}

		if (rankFrom == rankTo && fileFrom == fileTo) {
			System.out.println("Invalid Input");
			return;
		}

		Cell source = board[fileFrom][rankFrom];
		Cell target = board[fileTo][rankTo];

		Boolean check = checkMove(source, target);
		if (check == false) {
			return;
		}
		movePiece(source, target, promo);
		
		if (currentPlayer == playerWhite) {
			currentPlayer = playerBlack;
		} else {
			currentPlayer = playerWhite;
		}

	}
	
	/**
	 * Checks if promo equals R,B,B or Q
	 * @param promo third arg for promotion
	 * @return true,false 
	 */
	private boolean validPromo(String promo){
		if(promo.equals("R")){
			return true;
		}
		if(promo.equals("B")){
			return true;
		}
		if(promo.equals("N")){
			return true;
		}
		if(promo.equals("Q")){
			return true;
		}
		return false;
	}

	/**
	 * After validation, this method moves all the pieces to it's target spot
	 * @param source Cell of the original chess piece
	 * @param target Cell of target input for the chess piece
	 * @param promo Promotion for pawn if avaliable
	 */
	private void movePiece(Cell source, Cell target, String promo) {

		Player oppPlayer;
		oppPlayer = currentPlayer.getOpponent();
		
		if (source.getPiece().getChessName() == "K") {
			if (source.getPiece().getCastling() == true) {

				int tempX = -1;
				if (target.getX() == 6) {
					tempX = 7;
				} else if (target.getX() == 2) {
					tempX = 0;
				}

				if (source.getPiece().getPlayer().getColor() == PlayerColor.WHITE) {
					if (tempX == 0) {
						board[7][tempX].getPiece().setPosition(board[7][3]);
						board[7][3].setPiece(board[7][tempX].getPiece());
						board[7][tempX].setPiece(null);
					} else {
						board[7][tempX].getPiece().setPosition(board[7][5]);
						board[7][5].setPiece(board[7][tempX].getPiece());
						board[7][tempX].setPiece(null);
					}
				} else {
					if (tempX == 0) {
						board[0][tempX].getPiece().setPosition(board[0][3]);
						board[0][3].setPiece(board[0][tempX].getPiece());
						board[0][tempX].setPiece(null);
					} else {
						board[0][tempX].getPiece().setPosition(board[0][5]);
						board[0][5].setPiece(board[0][tempX].getPiece());
						board[0][tempX].setPiece(null);
					}
				}

			}

		}

		if(board[target.getY()][target.getX()].getPiece() != null){
			if(board[target.getY()][target.getX()].getPiece().getChessName() == "K"){
				System.out.println(currentPlayer.getColor() + " wins");
				System.exit(0);
			}
			oppPlayer.getPieces().remove(board[target.getY()][target.getX()].getPiece());
		}
		source.getPiece().setPosition(target);
		board[target.getY()][target.getX()].setPiece(source.getPiece());
		board[source.getY()][source.getX()].setPiece(null);

		if (target.getPiece().getChessName() == "p") {
			enPassant(target);

			if (target.getPiece().getPromo() == true) {
				
				ChessPiece temp = new Queen();
				if (promo != null) {
					switch (promo) {
					case "R":
						temp = new Rook();
						break;
					case "N":
						temp = new Knight();
						break;
					case "B":
						temp = new Bishop();
						break;
					case "Q":
						temp = new Queen();
						break;
					}
				}
				temp.setPlayer(currentPlayer);
				temp.setPosition(target);
				currentPlayer.getPieces().add(temp);
				currentPlayer.getPieces().remove(board[target.getY()][target.getX()].getPiece());
				board[target.getY()][target.getX()].setPiece(temp);

			}

		}

		Board.displayBoard(board);
		System.out.println();
		
		if(isCheckMate(target, oppPlayer.getKing().getPosition())){
			System.out.println("CheckMate");
			System.out.println(currentPlayer.getColor() + " wins");
			System.exit(0);

		} else if(isCheck(target, oppPlayer.getKing().getPosition())){
			System.out.println("Check");
		}
		

	}
	
	/**
	 * Checks if opponent is in checkmate
	 * @param target Cell of input
	 * @param kingPos Position of king
	 * @return true,false
	 */
	private boolean isCheckMate(Cell target, Cell kingPos){
		
		if(!checkHelper(kingPos)){
			return false;
		}
		
		Cell up = new Cell(kingPos.getY() - 1, kingPos.getX());
		Cell down = new Cell(kingPos.getY() + 1, kingPos.getX());
		Cell left = new Cell(kingPos.getY(), kingPos.getX() - 1);
		Cell right = new Cell(kingPos.getY(), kingPos.getX() + 1);
		Cell leftup = new Cell(kingPos.getY() - 1, kingPos.getX() - 1);
		Cell leftdown = new Cell(kingPos.getY() - 1, kingPos.getX() + 1);
		Cell rightup = new Cell(kingPos.getY() + 1, kingPos.getX() - 1);	
		Cell rightdown = new Cell(kingPos.getY() + 1, kingPos.getX() + 1);
		
		if(borderCheck(up)){
			if(!checkHelper(up)){
				return false;
			}
			//System.out.println("UP");
		}
		
		if(borderCheck(down)){
			if(!checkHelper(down)){
				return false;
			}
			//System.out.println("DOWN");
		}
		
		if(borderCheck(left)){
			if(!checkHelper(left)){
				return false;
			}
			//System.out.println("LEFT");
		}
		
		if(borderCheck(leftup)){
			if(!checkHelper(leftup)){
				return false;
			}
			//System.out.println("LEFTUP");
		}
		
		if(borderCheck(leftdown)){
			if(!checkHelper(leftdown)){
				return false;
			}
			//System.out.println("LEFTDOWN");
		}
		
		if(borderCheck(right)){
			if(!checkHelper(right)){
				return false;
			}
			//System.out.println("RIGHT");
		}
		
		if(borderCheck(rightup)){
			if(!checkHelper(rightup)){
				return false;
			}
			//System.out.println("RIGHTUP");
		}
		
		if(borderCheck(rightdown)){
			if(!checkHelper(rightdown)){
				return false;
			}
			//System.out.println("RIGHTDOWN");
		}
		
		return true;
	}
	
	/**
	 * Check whether king is under check from various pieces of the player
	 * @param kingPos position of king
	 * @return true,false
	 */
	private boolean checkHelper(Cell kingPos){
		for(int i = 0; i < currentPlayer.getPieces().size(); i++){
			ChessPiece piece = currentPlayer.getPieces().get(i);
			//System.out.println(piece.getPosition());
			piece.setBoard(board);
			if(isCheck(piece.getPosition(), kingPos)){
			return true;
			}
		}
		
		
		return false;
	}
	
	/**
	 * Checks if cell is within chess boarder and does not intersect with pieces same color of the king
	 * @param temp Cell to check
	 * @return true,false
	 */
	private boolean borderCheck(Cell temp){
		
		if(temp.getX() < 0 || temp.getY() < 0 || temp.getX() > 7 || temp.getY() > 7){
			return false;
		}
		if(board[temp.getY()][temp.getX()].getPiece() != null
				&& board[temp.getY()][temp.getX()].getPiece().getPlayer().getColor() != currentPlayer.getColor() ){
			return false;
		}
		
		return true;
	}

	/**
	 * Checks if king is under check
	 * @param target end location for the chess piece
	 * @param kingPos position of king
	 * @return true,false
	 */
	private boolean isCheck(Cell target, Cell kingPos) {
		
		if(target.getPiece().isValid(kingPos)){
		
		return true;
		}
		return false;

	}

	/**
	 * Performs enpassant when validated
	 * @param target end location for the chess piece
	 */
	private void enPassant(Cell target) {
		if (target.getPiece().getPlayer().getColor() == PlayerColor.WHITE) {
			if (board[target.getY() + 1][target.getX()].getPiece() != null
					&& board[target.getY() + 1][target.getX()].getPiece().getChessName() == "p"
					&& board[target.getY() + 1][target.getX()].getPiece().getPlayer().getColor() != currentPlayer
							.getColor()) {
				if (board[target.getY() + 1][target.getX()].getPiece().getPawnTwo()) {
					board[target.getY() + 1][target.getX()].setPiece(null);
				}
			}
		}
		if (target.getPiece().getPlayer().getColor() == PlayerColor.BLACK) {

			if (board[target.getY() - 1][target.getX()].getPiece() != null
					&& board[target.getY() - 1][target.getX()].getPiece().getChessName() == "p"
					&& board[target.getY() - 1][target.getX()].getPiece().getPlayer().getColor() != currentPlayer
							.getColor()) {
				if (board[target.getY() - 1][target.getX()].getPiece().getPawnTwo()) {
					board[target.getY() - 1][target.getX()].setPiece(null);
				}
			}
		}
	}

	/**
	 * Validates the chess move
	 * @param source original position
	 * @param target destination
	 * @return true,false
	 */
	private boolean checkMove(Cell source, Cell target) {
		ChessPiece fromPiece = source.getPiece();
		ChessPiece toPiece = target.getPiece();

		if (fromPiece == null) {
			System.out.println("Invalid Input");
			return false;
		}

		if (fromPiece.getPlayer().getColor() != currentPlayer.getColor()) {
			System.out.println("Illegal move, try again");
			return false;
		}

		if (toPiece != null) {
			if (toPiece.getPlayer().getColor() == currentPlayer.getColor()) {
				System.out.println("Illegal move, try again");
				return false;
			}
		}

		fromPiece.setBoard(board);
		if (!fromPiece.isValid(target)) {
			System.out.println("Illegal move, try again");
			return false;
		}

		return true;
	}

	/**
	 * Creates the initial setup of chess pieces and board
	 * @param player playerWhite or playerBlack
	 */
	private void putChessPieces(Player player) {
		ChessPiece[] pieces = new ChessPiece[] { new Rook(), new Knight(), new Bishop(), new Queen(), new King(),
				new Bishop(), new Knight(), new Rook() };
		int row, row2;
		if (player.getColor() == PlayerColor.WHITE) {
			row = 7;
			row2 = 6;
		} else {
			row = 0;
			row2 = 1;
		}

		for (int x = 0; x < 8; x++) {
			pieces[x].setPosition(board[row][x]);
			board[row][x].setPiece(pieces[x]);
			board[row][x].getPiece().setPlayer(player);
			player.setPieces(pieces[x]);
		}

		for (int y = 0; y < 8; y++) {
			ChessPiece pawn = new Pawn();
			pawn.setPosition(board[row2][y]);
			board[row2][y].setPiece(pawn);
			board[row2][y].getPiece().setPlayer(player);
			player.setPieces(pawn);
		}

		player.setKing(pieces[4]);

	}

}
