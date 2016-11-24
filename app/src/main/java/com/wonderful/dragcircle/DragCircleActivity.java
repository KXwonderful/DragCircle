package com.wonderful.dragcircle;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

public class DragCircleActivity extends AppCompatActivity {

    private RelativeLayout rl;
    private DragCircleView dcv;
    //容器的宽高
    private int containerWidth;
    private int containerHeight;
    // 记录位置
    private float lastX, lastY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drag_circle);

        rl = (RelativeLayout) findViewById(R.id.rl_drag_circle);

        dcv = (DragCircleView) findViewById(R.id.drag_circle_view);

        dcv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getActionMasked()) {
                    case MotionEvent.ACTION_DOWN:
                        lastX = event.getRawX();
                        lastY = event.getRawY();
                        return true;

                    case MotionEvent.ACTION_MOVE:
                        float distanceX = lastX - event.getRawX();
                        float distanceY = lastY - event.getRawY();

                        float nextY = dcv.getY() - distanceY;
                        float nextX = dcv.getX() - distanceX;

                        // 不能移出屏幕
                        if (nextY <= 0) {
                            dcv.changeColor(); //改变颜色
                            v.invalidate();    //刷新
                            nextY = 0;
                        } else if (nextY >= containerHeight - dcv.getHeight()) {
                            dcv.changeColor();
                            v.invalidate();
                            nextY = containerHeight - dcv.getHeight();
                        }
                        if (nextX <= 0){
                            dcv.changeColor();
                            v.invalidate();
                            nextX = 0;
                        }
                        else if (nextX >= containerWidth - dcv.getWidth()){
                            dcv.changeColor();
                            v.invalidate();
                            nextX = containerWidth - dcv.getWidth();
                        }


                        // 属性动画移动
                        ObjectAnimator y = ObjectAnimator.ofFloat(dcv, "y", dcv.getY(), nextY);
                        ObjectAnimator x = ObjectAnimator.ofFloat(dcv, "x", dcv.getX(), nextX);

                        AnimatorSet animatorSet = new AnimatorSet();
                        animatorSet.playTogether(x, y);
                        animatorSet.setDuration(0);
                        animatorSet.start();

                        lastX = event.getRawX();
                        lastY = event.getRawY();
                }
                return false;
            }
        });
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        // 获取容器的宽和高
        if (hasFocus) {
            containerHeight = rl.getHeight();
            containerWidth = rl.getWidth();
        }
    }
}
