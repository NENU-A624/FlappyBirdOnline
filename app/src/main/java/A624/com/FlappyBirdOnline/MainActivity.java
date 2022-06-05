package A624.com.FlappyBirdOnline;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 * 窗体实体类
 *
 * @author NENU-A624
 */
public class MainActivity extends AppCompatActivity {

    /**
     * 窗体创建时回调函数
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MyView(this));
    }

}
