package Project;


import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Board {
    private int rows_, cols_, grid_width_;//行 列 每一格的宽度

    public Frame frame;//窗体
    public MyCanvas canvas;//画布

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
        this.grid_width_ = 25;
    }

    /**
     * 构造函数
     *
     * @param rows       棋盘行数
     * @param cols       棋盘列数
     * @param grid_width 单格宽度(像素)
     */
    public Board(int rows, int cols, int grid_width) {
        this(rows, cols);
        this.grid_width_ = grid_width;
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
     * @param grid_width 单格宽度
     */
    public void SetGridWidth(int grid_width) {
        this.grid_width_ = grid_width;
    }

    /**
     * 获取当前棋盘单格宽度
     *
     * @return 单格宽度
     */
    public int GetGridWidth() {
        return this.grid_width_;
    }

    /**
     * 绘制棋盘
     */
    public void DrawBoard() {
        int width = grid_width_ * (rows_ + 1); //窗口宽
        int height = grid_width_ * (cols_ + 1); //窗口高
        frame = new Frame("我的五子棋");
        //frame.setSize(width, height);
        frame.setLayout(new FlowLayout());

        //绘制背景颜色
        frame.setBackground(new Color(213, 176, 146));

        canvas = new MyCanvas(rows_, cols_, grid_width_);
        canvas.setSize(width, height);
        frame.add(canvas);
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);//显示窗体
    }


    /**
     * 将物理坐标（像素）转换为逻辑坐标（行号，列号）
     */
    public int[] Convert(int x,int y){
        if(In(x,y)==false)
            return null;
        else {
            int tmp = x%this.grid_width_;
            x/=this.grid_width_;
            if(tmp>this.grid_width_*0.5)
                x++;
            tmp = y%this.grid_width_;
            y/=this.grid_width_;
            if(tmp>this.grid_width_*0.5)
               y++;
            return new int[]{x,y};
        }
    }
    /**
     * 当前棋子是否在棋盘范围内
     * @param x 行
     * @param y 列
     */
    public boolean In( int x, int y)
    {
        if(x<this.grid_width_*0.5 || x > (this.cols_+0.5)*this.grid_width_)
            return false;
        else if(y<this.grid_width_*0.5 || y > (this.rows_+0.5)*this.grid_width_)
            return false;
        else
            return true;
    }

}