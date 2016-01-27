package newair.com.marriagenetwork.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import newair.com.marriagenetwork.R;
import newair.com.marriagenetwork.application.AppManager;
import newair.com.marriagenetwork.utils.HideActionBar;
import newair.com.marriagenetwork.utils.L;


/**
 * Created by mateng on 15/9/17.
 * 应用程序Activity的基类
 *
 * @version 1.0
 */
public abstract class BaseActivity extends ActionBarActivity implements
        View.OnClickListener {
    /*
      activity的四种状态
     */
    private static final int ACTIVITY_RESUME = 0;
    private static final int ACTIVITY_STOP = 1;
    private static final int ACTIVITY_PAUSE = 2;
    private static final int ACTIVITY_DESTROY = 3;

    //activity的当前状态
    public int activityState;

    // 是否允许全屏
    private boolean mAllowFullScreen = true;

    /**
     * 初始化方法，在oncreate前执行
     */
    public abstract void initWidget();

    /**
     * activity组件点击回调方法
     *
     * @param v
     */
    public abstract void widgetClick(View v);


    public void setAllowFullScreen(boolean allowFullScreen) {
        this.mAllowFullScreen = allowFullScreen;
    }


    @Override
    public void onClick(View v) {
        widgetClick(v);
    }


    /**
     * ************************************************************************
     * <p/>
     * 打印Activity生命周期
     * <p/>
     * *************************************************************************
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        L.d(this.getClass().toString(), "---------onCreat ");
        // 竖屏锁定
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        if (mAllowFullScreen) {
//            supportRequestWindowFeature(Window.FEATURE_NO_TITLE); // 取消标题
            HideActionBar.hideActionBar(getSupportActionBar());
        }

        //将当前activity添加进activity的map集合
        AppManager.getInstance().addActivity(this.getClass(), this);

        initWidget();

    }

    /**
     * 设置app刷新
     */
    protected void refreshApp() {

        List<Intent> list = new ArrayList<>();
        for (Activity ac : AppManager.getInstance().getActivities().values()
                ) {
            ac.finish();
            Intent intent = new Intent(this, ac.getClass());
            if (this.getClass() != ac.getClass()) {
                list.add(intent);
            }
        }
        Collections.reverse(list);
        for (Intent intent : list
                ) {
            startActivity(intent);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        L.d(this.getClass().toString(), "---------onStart ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        activityState = ACTIVITY_RESUME;
        L.d(this.getClass().toString(), "---------onResume ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        activityState = ACTIVITY_STOP;
        L.d(this.getClass().toString(), "---------onStop ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        activityState = ACTIVITY_PAUSE;
        L.d(this.getClass().toString(), "---------onPause ");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        L.d(this.getClass().toString(), "---------onRestart ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        activityState = ACTIVITY_DESTROY;
        L.d("---------onDestroy ");
        AppManager.getInstance().removeActivity(this.getClass());
    }

    /**
     * 关闭activity
     *
     * @param view 返回按钮
     */
    public void backBefore(View view) {
        finish();
    }


    /**
     * 需要侧滑关闭的界面继承本类
     */
    public abstract class SlipOffActivity extends BaseActivity {
        //手指上下滑动时的最小速度
        private static final int YSPEED_MIN = 1000;

        //手指向右滑动时的最小距离
        private static final int XDISTANCE_MIN = 350;

        //手指向上滑或下滑时的最小距离
        private static final int YDISTANCE_MIN = 200;

        //记录手指按下时的横坐标。
        private float xDown;

        //记录手指按下时的纵坐标。
        private float yDown;

        //记录手指移动时的横坐标。
        private float xMove;

        //记录手指移动时的纵坐标。
        private float yMove;

        //用于计算手指滑动的速度。
        private VelocityTracker mVelocityTracker;

        protected boolean isOpenDistance = true;

        @Override
        public boolean dispatchTouchEvent(MotionEvent event) {
            createVelocityTracker(event);
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    xDown = event.getRawX();
                    yDown = event.getRawY();
                    break;
                case MotionEvent.ACTION_MOVE:
                    xMove = event.getRawX();
                    yMove = event.getRawY();
                    //滑动的距离
                    int distanceX = (int) (xMove - xDown);
                    int distanceY = (int) (yMove - yDown);
                    //获取顺时速度
                    int ySpeed = getScrollVelocity();
                    //关闭Activity需满足以下条件：
                    //1.x轴滑动的距离>XDISTANCE_MIN
                    //2.y轴滑动的距离在YDISTANCE_MIN范围内
                    //3.y轴上（即上下滑动的速度）<XSPEED_MIN，如果大于，则认为用户意图是在上下滑动而非左滑结束Activity
                    if (distanceX > XDISTANCE_MIN && (distanceY < YDISTANCE_MIN && distanceY > -YDISTANCE_MIN) && ySpeed < YSPEED_MIN) {
                        if (!isOpenDistance)
                            return false;
                        finish();
                        overridePendingTransition(R.anim.zoomin, R.anim.zoomout);
                    }
                    break;
                case MotionEvent.ACTION_UP:
                    recycleVelocityTracker();
                    break;
                default:
                    break;
            }
            return super.dispatchTouchEvent(event);
        }

        /**
         * 创建VelocityTracker对象，并将触摸界面的滑动事件加入到VelocityTracker当中。
         *
         * @param event
         */
        private void createVelocityTracker(MotionEvent event) {
            if (mVelocityTracker == null) {
                mVelocityTracker = VelocityTracker.obtain();
            }
            mVelocityTracker.addMovement(event);
        }

        /**
         * 回收VelocityTracker对象。
         */
        private void recycleVelocityTracker() {
            mVelocityTracker.recycle();
            mVelocityTracker = null;
        }

        /**
         * @return 滑动速度，以每秒钟移动了多少像素值为单位。
         */
        private int getScrollVelocity() {
            mVelocityTracker.computeCurrentVelocity(1000);
            int velocity = (int) mVelocityTracker.getYVelocity();
            return Math.abs(velocity);
        }
    }


}
