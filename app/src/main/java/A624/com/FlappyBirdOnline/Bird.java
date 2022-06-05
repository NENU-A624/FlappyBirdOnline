package A624.com.FlappyBirdOnline;


import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * 鸟实体类
 *
 * @author NENU-A624
 */
public class Bird {
    /**
     * 静态基本参数
     * 1.横坐标
     * 2.纵坐标
     * 3.像素宽
     * 4.像素高
     * 5.贴图
     */
    int x;
    int y;
    int width;
    int height;
    Bitmap birdBitmap;

    /**
     * 运动属性列表
     * 1.初始索引
     * 2.重力加速度
     * 3.间隔时间
     * 4.初始速度 （pix/s）
     * 5.当前时刻速度
     * 6.运动距离
     */
    int index;
    int g;
    double t;
    double v0;
    double vt;
    double s;

    /**
     * 构造函数
     * 初始化小鸟的基本状态
     * 传入参数为初始的横纵坐标
     * 设定游戏的动作特征
     *
     * @param x 横坐标
     * @param y 纵坐标
     */
    public Bird(int x, int y) {
        //初始化鸟的状态
        this.x = x;
        this.y = y;
        width = DateImage.xsn1.getWidth();
        height = DateImage.xsn1.getHeight();
        birdBitmap = DateImage.xsn()[0];
        index = 0;

        //对于运动状态的初始设定，决定游戏难度的参数
        g = 4;
        t = 0.25;
        v0 = 20;
    }

    /**
     * 鸟绘制函数
     */
    public void paint(Canvas canvas, Paint paint) {
        canvas.drawBitmap(birdBitmap, x, y, paint);
    }

    /**
     * 鸟移动处理函数
     * 计算小鸟下落的抛物运动
     */
    public void move() {

        //当前速度
        double vt1 = vt;
        //计算垂直上抛运动，经过时间t以后的速度
        vt = vt1 - g * t;
        //运行距离
        s = vt1 * t - 0.5 * g * t * t;
        //计算新的纵向坐标
        y = y - (int) s;
        //扇翅膀，增加对应图片状态索引，对三取模得到翅膀位置上中下的不同图片
        index++;
        birdBitmap = DateImage.xsn()[index / 8 % 3];
    }


    /**
     * 跳跃处理函数
     * 每次点击屏幕，小鸟重新获得初速度，向上飞，直到下一次点击之前，都作自由落体运动
     */
    public void flappy() {
        vt = v0;
    }


    /**
     * 碰撞检测函数
     *
     * @param column1 实体第一管道
     * @param column2 实体下一管道
     * @return true表示发生碰撞，false表示未发生碰撞
     */
    @SuppressWarnings("all")
    public boolean hit(Column column1, Column column2) {
        //检测是否碰撞顶部
        if (y + 50 < 0) {
            return true;
        }
        //是否碰撞底部
        if (y + 50 > MyApplication.height - DateImage.grassGround.getHeight()) {
            return true;
        }
        //碰撞水管
        if (x + 70 > column1.columnX && x + 70 < column1.columnX + column1.width && y + 50 > column1.columnYs && y + 50 < column1.columnYs + column1.height) {
            return true;
        } else if (x + 70 > column1.columnX && x + 70 < column1.columnX + column1.width && y + 50 > column1.columnYx && y + 50 < column1.columnYx + column1.height) {
            return true;
        } else if (x + 70 > column2.columnX && x + 70 < column2.columnX + column2.width && y + 50 > column2.columnYs && y + 50 < column2.columnYs + column2.height) {
            return true;
        } else if (x + 70 > column2.columnX && x + 70 < column2.columnX + column2.width && y + 50 > column2.columnYx && y + 50 < column2.columnYx + column2.height) {
            return true;
        }
        return false;
    }

    /**
     * 通过判定函数
     *
     * @param column1 实体第一管道
     * @param column2 实体下一管道
     * @return true通过，false未通过
     */
    public boolean pass(Column column1, Column column2) {
        return column1.columnX == x || column2.columnX == x;
    }

}
