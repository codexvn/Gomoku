package Project;
public class Gobang {
    private static Board  board;
    private static Chess  chess;
    public static void main(String[] args) {
        board = new Board(15,15,25);
        chess =  new Chess(15,15);
        board.DrawBoard();//绘制棋盘

    }
}




