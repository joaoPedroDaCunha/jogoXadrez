package chess;

import java.util.ArrayList;
import java.util.List;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.pieces.Rook;

public class ChessMatch {
	
	private int turn;
	private Color currentPlayer;
	private Board board;
	
	private List<Piece> piecesOnTheBoard = new ArrayList<>();
	private List<Piece> capturedPieces = new ArrayList<>();
	
	public ChessMatch() {
		board = new Board(8, 8);
		turn = 1;
	    currentPlayer = Color.WHITE; 
		initialSetup();
	}
	
	public int getTurn() {
		return turn;
	}
	
	public Color getCurrentPlater() {
		return  currentPlayer;
	}
	
	public ChessPiece[][] getPieces(){
		ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getColumns()];
		for (int i= 0;i<board.getRows();i++) {
			for(int j=0;j<board.getColumns();j++) {
				mat[i][j] = (ChessPiece) board.piece(i, j);
			}		
		}
		return mat;
	}
	
	public ChessPiece performChessMove(ChessPosition sourcePosition,ChessPosition targetPosition) {
		Position source = sourcePosition.toposition();
		Position target = targetPosition.toposition();
		validateSourcePosition(source);
		validateTargetPosition(source,target);
		Piece capturePiece = makeMove(source, target);
		nestTurn();
		return (ChessPiece)capturePiece;
	}
	
	public boolean[][] possibleMoves(ChessPosition sourcePosition){
		Position position = sourcePosition.toposition();
		validateSourcePosition(position);
		return board.piece(position).possibleMoves();
	}
	
	private void validateSourcePosition(Position position) {
		if(!board.thereIsAPiece(position)) {
			throw new ChessException("There is no piece no source position");
		}
		if(currentPlayer !=((ChessPiece) board.piece(position)).getColor()){
			throw new ChessException("the chose piece is bot yours");
		}
		if(!board.piece(position).isThereAnyPossibleMove()) {
			throw new ChessException("There is no possible moves for the chose piece");
		}
	}
	
	private void validateTargetPosition(Position source,Position target) {
		if(!board.piece(source).isThereAnyPossibleMove()) {
			throw new ChessException("The chose piece can't move to target position");
		}
	}
	
	private void nestTurn() {
		turn++;
		currentPlayer = (currentPlayer == Color.WHITE) ? Color.BLACK : Color.WHITE;
	}
	
	private Piece makeMove(Position source,Position target) {
		Piece p = board.removePiece(source);
		Piece capturePiece = board.removePiece(target);
		board.placePiece(p, target);
		
		if(capturePiece != null) {
			piecesOnTheBoard.remove(capturePiece);
			capturedPieces.add(capturePiece);
		}
		
		return capturePiece;
	}
	
	private void placeNewPiece(char column,int row, ChessPiece piece) {
		board.placePiece(piece, new ChessPosition(column,row).toposition());
	    piecesOnTheBoard.add(piece);
	}
	
	private void initialSetup() {
		placeNewPiece('B', 6,new Rook(board,Color.WHITE));
		placeNewPiece('H', 7,new Rook(board,Color.BLACK));
	}
}
