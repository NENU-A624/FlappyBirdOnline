package A624.com.FlappyBirdOnline;


import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * 音频资源类
 *
 * @author NENU-A624
 */
public class MusicUtil extends Service {

    private MediaPlayer mediaPlayer;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    /**
     * Service创建时回调方法
     */
    @Override
    public void onCreate() {
        super.onCreate();
    }

    /**
     * Service启动时回调方法
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Bundle b = intent.getExtras();
        int i = b.getInt("key");
        mediaPlayer = MediaPlayer.create(MusicUtil.this, i);
        mediaPlayer.setLooping(false);
        mediaPlayer.start();
        return super.onStartCommand(intent, flags, startId);
    }

    /**
     * Service停止时回调方法
     */
    @Override
    public void onDestroy() {
        mediaPlayer.stop();//停止播放
    }

}