package A624.com.FlappyBirdOnline;


import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;


public class MusicUtil extends Service {

    private MediaPlayer mediaPlayer;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    //Service被创建时的回调方法
    @Override
    public void onCreate() {
        super.onCreate();


    }

    //Service被启动时回调方法
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
     * Service被停止后调用
     */
    @Override
    public void onDestroy() {
        mediaPlayer.stop();//停止播放
    }

}