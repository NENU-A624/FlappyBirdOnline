package A624.com.FlappyBirdOnline;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;


public class MyView extends View implements Runnable {
    Context context;
    //地板类
    Ground ground;

    //管子类
    Column column1;
    Column column2;

    //鸟类
    static Bird bird ;
    //画笔
    Paint paint = new Paint();
    //客户端
    Client client = new Client();

    int startButtonX;
    int startButtonY;
    int stopButtonX;
    int stopButtonY;
    Thread thread = new Thread(this);

    //判断游戏是否开始
    boolean isstart = false;
    //判断是否死亡
    boolean isFail = false;

    //分数
    int fs = 0;

    public MyView(Context context) {
        super(context);
        this.context = context;
        init();
        startButtonX = MyApplication.width / 2 - DateImage.start.getWidth() / 2;
        startButtonY = MyApplication.height / 2 - DateImage.start.getHeight() / 2;
        stopButtonX = MyApplication.width / 2 - DateImage.start.getWidth() / 2;
        stopButtonY = MyApplication.height / 2 - DateImage.start.getHeight() / 2;
        thread.start();
    }

    //初始化方法
    public void init() {
        ground = new Ground();
        column1 = new Column(MyApplication.width + 200);
        column2 = new Column(MyApplication.width + 200 + MyApplication.width / 2 + 50);
        bird = new Bird(140, MyApplication.height / 2 - 30);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        setBackgroundResource(R.mipmap.background);


        //绘制开始按钮
        if (isstart == false) {
            canvas.drawBitmap(DateImage.start, startButtonX, startButtonY, paint);
        }


        //绘制水管，和鸟
        if (isstart) {
            column1.paint(canvas, paint);
            column2.paint(canvas, paint);
            bird.paint(canvas, paint);

        }
        //绘制地面
        ground.paint(canvas, paint);

        //结束画面
        if (isFail) {
            paint.setColor(Color.RED);
            paint.setTextSize(60);
            canvas.drawText("失败！最终得分：" + fs, stopButtonX - 70, stopButtonY - 100, paint);
            canvas.drawBitmap(DateImage.restart, stopButtonX, stopButtonY, paint);

        }

        paint.setColor(Color.RED);
        paint.setTextSize(40);
        canvas.drawText("分数 ：" + fs, 200, 200, paint);

    }

    public static int returnbirdx(){
        return bird.x;
    }
    public static int returnbirdy(){
        return bird.y;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        float x = event.getX();
        float y = event.getY();
        //开始按钮的点击事件
        if (isstart == false && x > startButtonX && x < startButtonX + DateImage.start.getWidth() && y > startButtonY && y < startButtonY + DateImage.start.getHeight()) {
            isstart = true;
        }
        //结束按钮的点击事件
        if (isFail && x > startButtonX && x < startButtonX + DateImage.start.getWidth() && y > startButtonY && y < startButtonY + DateImage.start.getHeight()) {
            init();
            isstart = false;
            isFail = false;
            fs = 0;
        }
        //小鸟向上飞
        bird.flappy();
        return true;
    }

    @Override
    public void run() {
        client.main(null);    //连接服务器
        while (true) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //移动方法
            if (isstart && isFail == false) {
                ground.move();
                column1.move();
                column2.move();
                bird.move();
                //碰撞检测
                if (bird.hit(column1, column2, ground)) {
                    isFail = true;
                }

                if (bird.pass(column1, column2)) {
                    fs++;
                }
                postInvalidate();
            }

        }


    }

}
