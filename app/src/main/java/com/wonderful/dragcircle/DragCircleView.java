package com.wonderful.dragcircle;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import java.util.Random;

/**
 * 自定义圆形
 * Created by KXwon on 2016/11/24.
 */

public class DragCircleView extends View {

    // 圆的画笔
    private Paint mPaint = new Paint();

    // 圆的半径
    private final int r = 60;

    // 圆的颜色
    private int color = 0;

    // 是否初始
    private boolean isFirst = true;

    public DragCircleView(Context context) {
        super(context);
    }

    public DragCircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int width;
        int height ;
        if (widthMode == MeasureSpec.EXACTLY) {
            width = widthSize;
        } else {
            width = 120;
        }

        if (heightMode == MeasureSpec.EXACTLY) {
            height = heightSize;
        } else {
            height = 120;
        }

        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 设置颜色
        if (isFirst){
            mPaint.setColor(Color.GREEN);
        }else {
            mPaint.setColor(color);
        }

        // 设置画笔的锯齿效果
        mPaint.setAntiAlias(true);
        // 绘制圆形
        canvas.drawCircle(r, r, r, mPaint);
    }

    /**
     * 修改颜色，外部调用
     */
    public void changeColor() {
        // 随机颜色
        Random random = new Random();
        color = 0xff000000 | random.nextInt(0x00ffffff);
        isFirst = false;
    }
}
