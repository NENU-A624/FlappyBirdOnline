package A624.com.FlappyBirdOnline;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * 图片资源类
 *
 * @author NENU-A624
 */
public class DateImage {
    /**
     * 资源信息
     * 1.background 背景
     * 2.grassGround 草地
     * 3.start 开始
     * 4.restart 重新开始
     * 5.sg1 上管道
     * 6.sg2 下管道
     * 7.xsn 像素鸟
     */
    public static Bitmap background = BitmapFactory.decodeResource(MyApplication.context.getResources(), R.mipmap.background);
    public static Bitmap grassGround = BitmapFactory.decodeResource(MyApplication.context.getResources(), R.mipmap.caodi);
    public static Bitmap start = BitmapFactory.decodeResource(MyApplication.context.getResources(), R.mipmap.start);
    public static Bitmap restart = BitmapFactory.decodeResource(MyApplication.context.getResources(), R.mipmap.restart);
    public static Bitmap sg1 = BitmapFactory.decodeResource(MyApplication.context.getResources(), R.mipmap.sg1);
    public static Bitmap sg2 = BitmapFactory.decodeResource(MyApplication.context.getResources(), R.mipmap.sg2);
    public static Bitmap xsn1 = BitmapFactory.decodeResource(MyApplication.context.getResources(), R.mipmap.xsn1);
    public static Bitmap xsn2 = BitmapFactory.decodeResource(MyApplication.context.getResources(), R.mipmap.xsn2);
    public static Bitmap xsn3 = BitmapFactory.decodeResource(MyApplication.context.getResources(), R.mipmap.xsn3);

    /**
     * 动画创建函数
     *
     * @return 小鸟飞行动作组合
     */
    public static Bitmap[] xsn() {
        Bitmap[] bitmap = new Bitmap[3];
        bitmap[0] = xsn1;
        bitmap[1] = xsn2;
        bitmap[2] = xsn3;

        return bitmap;
    }
}
