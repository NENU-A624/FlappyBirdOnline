package A624.com.FlappyBirdOnline;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

/**
 * 视图实体类
 * @author NENU-A624
 */
public class MyView extends View implements Runnable {
    /**
     * 外部对象
     * 1.上下文
     * 2.地面
     * 3.第一管道
     * 4.下一管道
     * 5.像素鸟
     * 6.画笔
     * 7.客户端
     */
    Context context;
    Ground ground;
    Column column1;
    Column column2;
    static Bird bird ;
    Paint paint = new Paint();
    Client client = new Client();

    /**
     * 静态基本参数
     * 1.开始横坐标
     * 2.开始纵坐标
     * 3.重开横坐标
     * 4.重开纵坐标
     * 5.开始标志
     * 6.结束标志
     * 7.分数
     */
    int startButtonX;
    int startButtonY;
    int stopButtonX;
    int stopButtonY;
    boolean isStart = false;
    boolean isFail = false;
    int score = 0;

    /**
     * 显式创建线程,无需使用线程池控制线程数量
     */
    Thread thread = new Thread(this);


    /**
     * 构造函数
     * 初始化实体对象，计算静态对象坐标，启动线程
     * @param context 应用上下文
     */
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

    /**
     * 初始化函数
     * 初始化地面管道及小鸟的对象
     * 由构造函数调用
     */
    public void init() {
        ground = new Ground();
        column1 = new Column(MyApplication.width + 200);
        column2 = new Column(MyApplication.width + 200 + MyApplication.width / 2 + 50);
        bird = new Bird(140, MyApplication.height / 2 - 30);
    }

    /**
     * 绘图函数
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //设定背景图
        setBackgroundResource(R.mipmap.background);
        //绘制地面
        ground.paint(canvas, paint);
        //未开始时绘制开始按钮
        if (!isStart) {
            canvas.drawBitmap(DateImage.start, startButtonX, startButtonY, paint);
        }
        //开始后绘制管道及鸟
        if (isStart) {
            column1.paint(canvas, paint);
            column2.paint(canvas, paint);
            bird.paint(canvas, paint);
        }
        //结束后绘制计分板与重新开始按钮
        if (isFail) {
            paint.setColor(Color.RED);
            paint.setTextSize(60);
            canvas.drawText("失败！最终得分：" + score, stopButtonX - 70, stopButtonY - 100, paint);
            canvas.drawBitmap(DateImage.restart, stopButtonX, stopButtonY, paint);
        }
        //绘制计分板
        paint.setColor(Color.RED);
        paint.setTextSize(40);
        canvas.drawText("分数 ：" + score, 200, 200, paint);
    }

    public static int returnbirdx(){
        return bird.x;
    }
    public static int returnbirdy(){
        return bird.y;
    }

    /**
     * 点击事件处理函数
     * 处理开始、重新开始以及小鸟跳跃
     * @param event 点击事件
     * @return 成功处理事件
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //获取点击坐标
        float x = event.getX();
        float y = event.getY();
        //开始按钮的点击事件
        if (!isStart && x > startButtonX && x < startButtonX + DateImage.start.getWidth() && y > startButtonY && y < startButtonY + DateImage.start.getHeight()) {
            isStart = true;
        }
        //重新开始按钮的点击事件
        if (isFail && x > startButtonX && x < startButtonX + DateImage.start.getWidth() && y > startButtonY && y < startButtonY + DateImage.start.getHeight()) {
            init();
            isStart = false;
            isFail = false;
            score = 0;
        }
        //小鸟跳跃处理
        bird.flappy();
        return true;
    }

    /**
     * 线程主体运行函数
     * 位于此处的代码是应用程序运行的主体部分
     */
    @Override
    public void run() {
        //client.main(null);    //连接服务器
        while (true) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //开始且未结束，调用所有对象的移动方法
            if (isStart && !isFail) {
                ground.move();
                column1.move();
                column2.move();
                bird.move();
                //碰撞检测
                if (bird.hit(column1, column2)) {
                    isFail = true;
                }
                //通过检测，增加分数
                if (bird.pass(column1, column2)) {
                    score++;
                }
                //重绘
                postInvalidate();
            }
        }
    }

}
