package views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;

/**
 * Created by ASUS on 2017/6/13.
 */

public class LineEditText extends AppCompatEditText {
    public LineEditText(Context context) {
        super(context);
    }

    public LineEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 提前需要画的线的条数
     */
    private int drawLine = 4;
    /**
     * 为了不让指针压线太明显，将线下移的像素，可根据字体大小和行间距自己调整
     */
    public int lineDis = 18;
    private Rect mRect;
    private Paint mPaint;

    public LineEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
        mRect = new Rect();
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(0x800000FF);
    }

    public void setNotesMinLines(int lines) {
        this.drawLine = lines;
        setMinLines(lines);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.WHITE);
        super.onDraw(canvas);
        int count = getLineCount();
        Rect r = mRect;
        Paint paint = mPaint;
        int basicline = 0;
        //第一次画第一条线。以后每次输入，换行时仍然检测，继续画线
        int baseline = getLineBounds(0, r);
        int h = getLineHeight();
        canvas.drawLine(r.left, baseline + lineDis, r.right, baseline + lineDis, paint);
        canvas.drawLine(r.left, baseline + h + 1 + lineDis, r.right, baseline + h + 1 + lineDis, paint);
        canvas.drawLine(r.left, baseline + 2 * h + lineDis, r.right, baseline + 2 * h + lineDis, paint);
        canvas.drawLine(r.left, baseline + 3 * h + lineDis, r.right, baseline + 3 * h + lineDis, paint);
//        int h =getLineHeight();
//        canvas.drawLine(r.left, baseline +  h + lineDis, r.right, baseline +  h + lineDis, paint);
//        canvas.drawLine(r.left, baseline + 2 * h + lineDis, r.right, baseline + 2 * h + lineDis, paint);
//        canvas.drawLine(r.left, baseline + 3 * h + lineDis, r.right, baseline + 3 * h + lineDis, paint);
//        for (int i = 0; i < count; i++) {
//            int baseline = getLineBounds(i, r);
////            basicline = baseline;
//            canvas.drawLine(r.left, baseline + lineDis, r.right, baseline + lineDis, paint);
//        }
        //根据判定条件，画出固定条数的线,从第二套开始画
//        if(count < drawLine){
//            for (int j = 0; j < drawLine; j++) {
//                int baseline = basicline+j*getLineHeight();
//                canvas.drawLine(r.left, baseline + lineDis, r.right, baseline + lineDis, paint);
//            }
//        }


    }
}
