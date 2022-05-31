package A624.com.FlappyBirdOnline;


import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.Random;

/**
 * 管子类
 */

public class Column {

    //管子的坐标
    int columX;
    int columYs;
    int columYx;
    //管子的宽高
    int width;
    int height;

    //创建随机数
    Random random = new Random();

    public Column(int x1) {
        width = DateImage.sg1.getWidth();
        height = DateImage.sg1.getHeight();
        columX = x1;
        columYs = -(height / 2) + (getRandom(-35, 30)) * 10;
        columYx = height + columYs + 250;
        move();
    }

    public void paint(Canvas canvas, Paint paint) {

        canvas.drawBitmap(DateImage.sg1, columX, columYs, paint);
        canvas.drawBitmap(DateImage.sg2, columX, columYx, paint);


    }

    public void move() {

        columX -= 2;
        if (columX < -width) {
            columX = MyApplication.width + 20;
            columYs = -(height / 2) + (getRandom(-30, 30)) * 10;
            columYx = height + columYs + 250;
        }

    }

    public int getRandom(int min, int max) {
        int s = random.nextInt(max) % (max - min + 1) + min;
        return s;

    }

}
