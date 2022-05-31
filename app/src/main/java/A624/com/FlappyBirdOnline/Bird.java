package A624.com.FlappyBirdOnline;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

public class Bird {
    /**
     * 绘制鸟
     */

    int x;
    int y;
    int width;
    int height;
    Bitmap brid;

    /**
     * 属性列表
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

    public Bird(int x, int y) {
        this.x = x;
        this.y = y;

        width = DateImage.xsn1.getWidth();
        height = DateImage.xsn1.getHeight();
        brid = DateImage.xsn()[0];
        index = 0;

        g = 4;
        t = 0.25;
        v0 = 20;
    }


    public void paint(Canvas canvas, Paint paint) {
        canvas.drawBitmap(brid, x, y, paint);
    }


    public void move() {
        /**
         *  小鸟下落的抛物运动
         */

        //当前速度
        double vt1 = vt;
        //计算垂直上抛运动，经过时间t以后的速度
        double v = vt1 - g * t;
        //v作为下一次计算位移的初始速度
        vt = v;
        //运行距离
        s = vt1 * t - 0.5 * g * t * t;

        y = y - (int) s;

        //扇翅膀
        index++;
        brid = DateImage.xsn()[index / 8 % 3];
    }


    //每次点击屏幕，小鸟重新获得初速度，向上飞，直到下一次点击之前，都作自由落体运动
    public void flappy() {
        vt = v0;
    }

    //碰撞检测
    public boolean hit(Column column1, Column column2, Ground ground) {
        //检测是否碰撞顶部
        if (y + 50 < 0) {
            return true;
        }
        //是否碰撞底部
        if (y + 50 > MyApplication.height - DateImage.caodi.getHeight()) {
            return true;
        }
        //碰撞水管
        if (x + 70 > column1.columX && x + 70 < column1.columX + column1.width && y + 50 > column1.columYs && y + 50 < column1.columYs + column1.height) {
            return true;
        } else if (x + 70 > column1.columX && x + 70 < column1.columX + column1.width && y + 50 > column1.columYx && y + 50 < column1.columYx + column1.height) {
            return true;
        } else if (x + 70 > column2.columX && x + 70 < column2.columX + column2.width && y + 50 > column2.columYs && y + 50 < column2.columYs + column2.height) {
            return true;
        } else if (x + 70 > column2.columX && x + 70 < column2.columX + column2.width && y + 50 > column2.columYx && y + 50 < column2.columYx + column2.height) {
            return true;
        }
        return false;
    }

    //判断是否通过管子
    public boolean pass(Column column1, Column column2) {
        return column1.columX == x || column2.columX == x;
    }


}
