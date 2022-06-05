package A624.com.FlappyBirdOnline;


import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.Random;

/**
 * 管道实体类
 *
 * @author NENU-A624
 */
public class Column {
    /**
     * 基本参数
     * 1.管道横坐标
     * 2.下端开口纵坐标
     * 3.上端开口纵坐标
     * 4.像素宽
     * 5.像素高
     * 6.随机数
     */
    int columnX;
    int columnYs;
    int columnYx;
    int width;
    int height;
    Random random = new Random();

    /**
     * 构造函数
     * 初始化管道的基本状态
     *
     * @param x1 横坐标
     */
    public Column(int x1) {
        width = DateImage.sg1.getWidth();
        height = DateImage.sg1.getHeight();
        columnX = x1;
        columnYs = -(height / 2) + (getRandom(-35, 30)) * 10;
        columnYx = height + columnYs + 250;
        move();
    }

    /**
     * 管道绘制函数
     */
    public void paint(Canvas canvas, Paint paint) {
        canvas.drawBitmap(DateImage.sg1, columnX, columnYs, paint);
        canvas.drawBitmap(DateImage.sg2, columnX, columnYx, paint);
    }

    /**
     * 管道移动生成处理函数
     */
    public void move() {
        columnX -= 2;
        if (columnX < -width) {
            columnX = MyApplication.width + 20;
            columnYs = -(height / 2) + (getRandom(-30, 30)) * 10;
            columnYx = height + columnYs + 250;
        }
    }

    /**
     * 随机数生成函数
     *
     * @param min 最小值
     * @param max 最大值
     * @return 随机数
     */
    public int getRandom(int min, int max) {
        return random.nextInt(max) % (max - min + 1) + min;
    }

}
