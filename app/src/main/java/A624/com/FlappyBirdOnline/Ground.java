package A624.com.FlappyBirdOnline;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * 地面类
 */
public class Ground {
    //地面坐标
    int groundX;
    int groundY;

    public Ground() {
        groundX = 0;
        groundY = MyApplication.height - DateImage.caodi.getHeight();
        move();
    }

    public void paint(Canvas canvas, Paint paint) {
        canvas.drawBitmap(DateImage.caodi, groundX, groundY, paint);
    }


    public void move() {
        groundX -= 2;
        if (groundX == MyApplication.width - DateImage.caodi.getWidth() + 50) {
            groundX = 0;

        }
    }


}
