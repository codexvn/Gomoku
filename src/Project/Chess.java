package Project;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Chess {
    private Board.kPieceType kChess_[][];
    private Board.kPieceType kNextPiece;
    private Board board_;

    /**
     * 构造函数，空棋局
     *
     * @param rows 棋盘行数
     * @param cols 棋盘列数
     */
    public Chess(int rows, int cols) {
        this.kChess_ = new Board.kPieceType[rows][cols];
        /*  引用类型数组创建时元素默认为null
        for (int i = 0; i < this.kChess_.length ; i++) {
            for (int j = 0; j < this.kChess_[i].length; j++) {
                this.kChess_[i][j]=Board.kPieceType.kNull;
            }
        }
        */
    }

    /**
     * 构造函数，从已有棋局构造棋局
     *
     * @param Chess 二维 Board.kPieceType 数组，用于保存棋局数据
     */
    public Chess(Board.kPieceType Chess[][]) {
        this.kChess_ = Chess;
    }

    /**
     * 该位置上是否为空
     *
     * @param x 行
     * @param y 列
     * @return 空返回true，否则返回false
     */
    public boolean IsEmpty(int x, int y) {
        if (this.kChess_[x - 1][y - 1] == null)
            return true;
        else
            return false;
    }

    /**
     * 在棋局上添加棋子
     *
     * @param x          行
     * @param y          列
     * @param piece_type 棋子类型
     */
    public void Add(int x, int y, Board.kPieceType piece_type) {
        this.kChess_[x - 1][y - 1] = piece_type;
    }

    /**
     * 获取指定位置上棋子的类型
     *
     * @param x 行
     * @param y 列
     * @return 有则返回类型，否则返回 <em>null<em/>
     */
    public Board.kPieceType GetPieceType(int x, int y) {
        return this.kChess_[x][y];
    }

    /**
     * 在落下当前棋子之后是否胜利
     *
     * @param x 行
     * @param y 列
     * @return 是否胜利
     */
    public boolean IsWin(int x, int y, Board.kPieceType piece_type) {
        int[][][] foot = {
                {{0, -1}/*往左*/, {0, 1}/*往右*/}, //水平方向移动
                {{1, 0}/*往上*/, {-1, 0}/*往下*/}, //竖直方向移动
                {{1, -1}/*往左上*/, {-1, 1}/*往右下*/}, //左斜方向移动
                {{-1, -1}/*往左下*/, {1, 1}/*往右上*/} //右斜方向移动
        };
        int count, x_tmp, y_tmp;
        for (int[][] i : foot) {
            count = 1; //计算连子数量
            for (int[] j : i) { //直线方向共9格遍历
                x_tmp = x;
                y_tmp = y;
                for (int k = 0; k < 5; k++) { //单方向遍历
                    x_tmp += j[0];
                    y_tmp += j[1];
                    if (In(x_tmp, y_tmp) && this.kChess_[x_tmp - 1][y_tmp - 1] == piece_type)
                        count++;
                    else
                        continue;
                }
            }
            if (count >= 5)
                return true;
        }
        return false;
    }

    /**
     * 当前棋子是否在棋局范围内
     *
     * @param x 行
     * @param y 列
     */
    public boolean In(int x, int y) {
        if (x <= 0 || x >= this.kChess_.length)
            return false;
        else if (y <= 0 || y >= this.kChess_[0].length)
            return false;
        else
            return true;
    }

    public void GameStart(Board board) {
        this.kNextPiece = Board.kPieceType.kWhite;
        this.board_ = board;
        this.board_.canvas.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.printf("%d,%d\n", e.getX(), e.getY());
                int tmp[] = board.Convert(e.getX(), e.getY());
                if (tmp == null)
                    return;
                else if (IsEmpty(tmp[0], tmp[1]) == true) //是否为空
                {
                    board.canvas.DrawChess(tmp[0], tmp[1], kNextPiece);
                    Add(tmp[0], tmp[1], kNextPiece);//添加棋子
                    board.canvas.DrawChess(tmp[0], tmp[1], kNextPiece);
                    if (IsWin(tmp[0], tmp[1], kNextPiece)) {
                        switch (kNextPiece) {
                            case kWhite -> System.out.printf("白子赢");
                            case kBlack -> System.out.printf("黑子赢");
                        }
                        board.frame.setEnabled(false);
                    } else {
                        switch (kNextPiece) {
                            case kBlack -> kNextPiece = Board.kPieceType.kWhite;
                            case kWhite -> kNextPiece = Board.kPieceType.kBlack;
                        }
                    }
                    super.mouseClicked(e);
                }
            }
        });
    }

}