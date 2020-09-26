package Project;
public class Gomoku {
    private static Board  board;
    private static Chess  chess;
    public static Board.kPieceType kNextPiece;
    public static void main(String[] args) {
        board = new Board(15,15,30);
        chess =  new Chess(15,15);
        board.DrawBoard();//绘制棋盘
        chess.GameStart(board);
    }
}




