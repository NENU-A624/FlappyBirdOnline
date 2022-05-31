package A624.com.FlappyBirdOnline;

import android.app.Application;
import android.content.Context;
import android.view.WindowManager;

public class MyApplication extends Application {
    public static Context context;

    public static int width;
    public static int height;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        WindowManager w = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        width = w.getDefaultDisplay().getWidth();
        height = w.getDefaultDisplay().getHeight();


    }
}
