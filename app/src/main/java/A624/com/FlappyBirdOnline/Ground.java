package A624.com.FlappyBirdOnline;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * 地面实体类
 *
 * @author NENU-A624
 */
public class Ground {
    /**
     * 基本参数
     * 1.横坐标
     * 2.纵坐标
     */
    int groundX;
    int groundY;

    /**
     * 构造函数
     * 初始化地面的基本状态
     */
    public Ground() {
        groundX = 0;
        groundY = MyApplication.height - DateImage.grassGround.getHeight();
        move();
    }

    /**
     * 地面绘制函数
     */
    public void paint(Canvas canvas, Paint paint) {
        canvas.drawBitmap(DateImage.grassGround, groundX, groundY, paint);
    }

    /**
     * 地面移动处理函数
     */
    public void move() {
        groundX -= 2;
        if (groundX == MyApplication.width - DateImage.grassGround.getWidth() + 50) {
            groundX = 0;
        }
    }

}
