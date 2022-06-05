package A624.com.FlappyBirdOnline;

import android.app.Application;
import android.content.Context;
import android.graphics.Point;
import android.view.Display;
import android.view.WindowManager;

/**
 * 应用实体类
 *
 * @author NENU-A624
 */
public class MyApplication extends Application {
    /**
     * 基本参数
     * 1.上下文 单例类中的Context对象必须为Application Context，保证在整个应用生命周期内引用,此处忽略可能的内存泄漏
     * 2.像素宽
     * 3.像素高
     */

    public static Context context;
    public static int width;
    public static int height;

    /**
     * 应用创建时回调函数
     */
    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        WindowManager w = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = w.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        width = size.x;
        height = size.y;
    }
}
