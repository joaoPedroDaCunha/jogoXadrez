package application;

import java.util.InputMismatchException;
import java.util.Scanner;

import chess.ChessPiece;
import chess.ChessPosition;

public class UI {
	
	public static void printBoard(ChessPiece[][] pieces) {
		for(int i=0; i<pieces.length; i++){
			System.out.print((8-i)+" ");
			for(int j =0; j<pieces.length;j++){
				printPiece(pieces[i][j],false);
			}
			System.out.println();
		}
		System.out.println("  A B C D F G H I");
	}
	
	public static void printBoard(ChessPiece[][] pieces, boolean[][] possiblesMoves) {
		for(int i=0; i<pieces.length; i++){
			System.out.print((8-i)+" ");
			for(int j =0; j<pieces.length;j++){
				printPiece(pieces[i][j], possiblesMoves[i][j]);
			}
			System.out.println();
		}
		System.out.println("  A B C D F G H I");
	}
	
	public static void clearScreen() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}
	
	public static ChessPosition readChessPosition(Scanner sc) {
		try {
		String s = sc.nextLine();
		char column = s.charAt(0);
		int row = Integer.parseInt(s.substring(1));
		return new ChessPosition(column, row);
		}
		catch(RuntimeException e){
			throw new InputMismatchException("Error reading ChessPosition. Valid values are form a1 to h8.");
		}
	}
	
	private static void printPiece(ChessPiece piece, boolean background) {
		if(background){
			System.out.print("x");
		}
		if(piece == null) {
			System.out.print("-");
		}
		else {
			System.out.print(piece);
		}
		System.out.print(" ");
	}
	
}
