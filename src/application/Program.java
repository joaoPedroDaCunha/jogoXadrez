package application;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import chess.ChessException;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;

public class Program {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner (System.in);
		ChessMatch chessMatch = new ChessMatch();
		List<ChessPiece> captured = new ArrayList<>();
		
		while (!chessMatch.getCheckMate()) {
			try {
			 UI.clearScreen();
		     UI.printMatch(chessMatch,captured);
		     System.out.println();
		     System.out.print("Source: ");
		     ChessPosition source = UI.readChessPosition(sc);
		     
		     boolean[][] possiblesMoves = chessMatch.possibleMoves(source);
		     UI.clearScreen();
		     UI.printBoard(chessMatch.getPieces(), possiblesMoves);
		     
		     System.out.println();
		     System.out.print("Target: ");
		     ChessPosition target = UI.readChessPosition(sc);
		     
		     ChessPiece capturePiece = chessMatch.performChessMove(source, target);
		     
		    if(capturePiece != null) {
		    	captured.add(capturePiece);
		    }
		    if(chessMatch.getPromoted() !=null) {
		    	System.out.print("Enter piece for promotion (B/N/R/Q): ");
		    	String type = sc.nextLine().toUpperCase();
		    	while(!type.equals("B")&&!type.equals("N")&&!!type.equals("R")&&!type.equals("Q")) {
		    		System.out.print("Invalid values !Enter piece for promotion (B/N/R/Q): ");
		    	}
		    	chessMatch.raplacePromotedPiece(type);
		    }
		    
			}
			catch(ChessException e) {
				System.out.print(e.getMessage());
				sc.nextLine();
			}
			catch(InputMismatchException e) {
				System.out.print(e.getMessage());
				sc.nextLine();
			}
		}
		UI.clearScreen();
		UI.printMatch(chessMatch, captured);
	}

}
