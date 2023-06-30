package application;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;
import chess.Color;

public class UI {
	
	public static void printBoard(ChessPiece[][] pieces) {
		for(int i=0; i<pieces.length; i++){
			System.out.print((8-i)+" ");
			for(int j =0; j<pieces.length;j++){
				printPiece(pieces[i][j],false);
			}
			System.out.println();
		}
		System.out.println("  A B C D E F G H");
	}
	
	public static void printBoard(ChessPiece[][] pieces, boolean[][] possiblesMoves) {
		for(int i=0; i<pieces.length; i++){
			System.out.print((8-i)+" ");
			for(int j =0; j<pieces.length;j++){
				printPiece(pieces[i][j], possiblesMoves[i][j]);
			}
			System.out.println();
		}
		System.out.println("  A B C D E F G H ");
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
			throw new InputMismatchException("Error reading ChessPosition. Valid values are form A1 to H8.");
		}
	}
	
	public static void printMatch(ChessMatch chessMatch,List<ChessPiece> captured) {
		printBoard(chessMatch.getPieces());
		System.out.println();
		printCapturedPiece(captured);
		System.out.println();
		System.out.println("Turn : " + chessMatch.getTurn());
		System.out.println("Witting palyer: " + chessMatch.getCurrentPlater());
		if(chessMatch.getCheck()) {
			System.out.println("Check");
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
	
	private static void printCapturedPiece(List<ChessPiece> captured) {
		List<ChessPiece> white = captured.stream().filter(x -> x.getColor() == Color.WHITE).collect(Collectors.toList());
		List<ChessPiece> black = captured.stream().filter(x -> x.getColor() == Color.BLACK).collect(Collectors.toList());
		System.out.println("Captured pieces:");
		System.out.print("White: ");
		System.out.println(Arrays.toString(white.toArray()));
		System.out.print("Black: ");
		System.out.println(Arrays.toString(black.toArray()));
	}
	
}
