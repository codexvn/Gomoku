package Project;

import java.awt.*;

public class MyCanvas extends Canvas {
    private int rows_, cols_, grid_width_;//行 列 每一格的宽度
    public MyCanvas(int rows, int cols, int grid_width) {
        this.rows_ = rows;
        this.cols_ = cols;
        this.grid_width_ = grid_width;
    }

    @Override
    public void paint(Graphics g) {
        //super.paint(g);
        //System.out.printf("paint");
        //绘制棋盘线
        for (int i = 1; i <= this.rows_; i++){
            g.drawLine(grid_width_, i * grid_width_, cols_ * grid_width_, i * grid_width_);
        }
        for (int i = 1; i <= this.cols_; i++) {
            g.drawLine(i * grid_width_, grid_width_, i * grid_width_, rows_ * grid_width_);
        }
        g.drawLine(grid_width_, grid_width_, cols_ * grid_width_, cols_ * grid_width_);
        g.drawLine(rows_ * grid_width_, grid_width_, grid_width_, cols_ * grid_width_);
    }
    /**
     * 绘制棋子
     *
     * @param rows 行
     * @param cols 列
     */
    public void DrawChess(int rows, int cols, Board.kPieceType piece_type) {
        System.out.printf("在%d,%d绘制棋子%s\n", rows, cols,piece_type.toString());
        Graphics g =this.getGraphics();
        int r =(int)(this.grid_width_*0.5);
        g.setColor(Color.BLACK);
        g.drawArc(rows*this.grid_width_-r,cols*this.grid_width_-r,2*r,2*r,0,360);
        switch (piece_type){
            case kBlack ->         g.setColor(Color.BLACK);
            case kWhite -> g.setColor(Color.white);
        }
        g.fillArc(rows*this.grid_width_-r,cols*this.grid_width_-r,2*r,2*r,0,360);
    }
}
