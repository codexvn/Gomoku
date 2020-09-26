package Project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Board {
    private int rows_, cols_, grid_wight_;//行 列 每一格的宽度

    private Frame frame;//窗体
    private Canvas canvas;//画布
    private Graphics graphics;//绘图对象

    public static enum kPieceType { //棋子种类
        kBlack, //黑色棋子
        kWhite, //白色棋子
        //kNull   //没有棋子
    }

    /**
     * 构造函数，默认单格宽度为1
     *
     * @param rows 棋盘行数
     * @param cols 棋盘列数
     */
    public Board(int rows, int cols) {
        this.rows_ = rows;
        this.cols_ = cols;
        this.grid_wight_ = 25;
    }

    /**
     * 构造函数
     *
     * @param rows       棋盘行数
     * @param cols       棋盘列数
     * @param grid_wight 单格宽度(像素)
     */
    public Board(int rows, int cols, int grid_wight) {
        this(rows, cols);
        this.grid_wight_ = grid_wight;
    }

    /**
     * 返回棋盘大小
     *
     * @return 数组中第一个元素为行数，第二个元素位列数
     */
    public int[] GetBoardSize() {
        return new int[]{this.rows_, this.cols_};
    }

    /**
     * 设置棋局单格宽度
     *
     * @param grid_wight 单格宽度
     */
    public void SetGridWight(int grid_wight) {
        this.grid_wight_ = grid_wight;
    }

    /**
     * 获取当前棋盘单格宽度
     *
     * @return 单格宽度
     */
    public int GetGridWight() {
        return this.grid_wight_;
    }

    /**
     * 绘制棋盘
     */
    public void DrawBoard() {
        int width = grid_wight_ * (rows_ + 2); //窗口宽
        int height = grid_wight_ * (cols_ + 2); //窗口高
        frame = new Frame("我的五子棋");
        frame.setSize(width, height);
        frame.setLayout(new FlowLayout());

        //绘制背景颜色
        frame.setBackground(new Color(213, 176, 146));

        canvas = new Canvas();
        canvas.setSize(width, height);
        frame.add(canvas);
//        frame.setResizable(false);
        frame.setVisible(true);//显示窗体

        //绘制棋盘线
        graphics = canvas.getGraphics();
        for (int i = 1; i <= this.rows_; i++) {
            graphics.drawLine(grid_wight_, i * grid_wight_, cols_ * grid_wight_, i * grid_wight_);
        }
        for (int i = 1; i <= this.cols_; i++) {
            graphics.drawLine(i * grid_wight_, grid_wight_, i * grid_wight_, rows_ * grid_wight_);
        }
//        canvas.update(graphics);
        canvas.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                handleClick(e.getX(), e.getY());
                super.mouseClicked(e);
            }
        });
        frame.setVisible(true);
    }

    /**
     * 绘制棋子
     *
     * @param rows
     * @param cols
     */
    public void handleClick(int rows, int cols) {
        System.out.printf("在%d,%d绘制棋子\n", rows, cols);
    }
    /**
     * 将物理坐标（像素）转换为逻辑坐标（行号，列号）
     */
    /*public  int[] Convert(){

    }*/
}