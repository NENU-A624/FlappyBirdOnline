package A624.com.FlappyBirdOnline;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;


public class DateImage {

    //背景草地
    public static Bitmap background = BitmapFactory.decodeResource(MyApplication.context.getResources(), R.mipmap.background);
    public static Bitmap caodi = BitmapFactory.decodeResource(MyApplication.context.getResources(), R.mipmap.caodi);
    //开始结束图片
    public static Bitmap start = BitmapFactory.decodeResource(MyApplication.context.getResources(), R.mipmap.start);
    public static Bitmap restart = BitmapFactory.decodeResource(MyApplication.context.getResources(), R.mipmap.restart);
    //水管
    public static Bitmap sg1 = BitmapFactory.decodeResource(MyApplication.context.getResources(), R.mipmap.sg1);
    public static Bitmap sg2 = BitmapFactory.decodeResource(MyApplication.context.getResources(), R.mipmap.sg2);
    //像素鸟
    public static Bitmap xsn1 = BitmapFactory.decodeResource(MyApplication.context.getResources(), R.mipmap.xsn1);
    public static Bitmap xsn2 = BitmapFactory.decodeResource(MyApplication.context.getResources(), R.mipmap.xsn2);
    public static Bitmap xsn3 = BitmapFactory.decodeResource(MyApplication.context.getResources(), R.mipmap.xsn3);

    public static Bitmap[] xsn() {
        Bitmap[] bitmap = new Bitmap[3];
        bitmap[0] = xsn1;
        bitmap[1] = xsn2;
        bitmap[2] = xsn3;

        return bitmap;
    }
}
