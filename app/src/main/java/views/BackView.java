package views;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.justin.hzwl.myhzwl.R;

import util.DimensionUtils;


/**
 * created by apple on 16/8/24.
 */

public class BackView extends RelativeLayout {

    private TextView     mTitleTextView;
    private ImageView    mBackIconView;
    private LinearLayout mRightWrapper;
    private boolean      showPadding;

    public BackView(Context context) {
        super(context);
        initView(context);
    }

    public BackView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
        initStyle(context, attrs, 0);
    }

    public BackView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
        initStyle(context, attrs, defStyleAttr);
    }

    private void initView(Context context) {
        inflate(context, R.layout.view_back_view, this);
        mBackIconView = (ImageView) findViewById(R.id.title_back);
        mTitleTextView = (TextView) findViewById(R.id.title_text);
        mRightWrapper = (LinearLayout) findViewById(R.id.title_right_wrapper);

    }

    private void initStyle(Context context, AttributeSet attrs, int defStyleAttr) {
        DimensionUtils.initDimension(context);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.BackView, defStyleAttr, 0);
        boolean defaultRight = initRightWrapper(typedArray);
        if (typedArray != null) {
            String title = typedArray.getString(R.styleable.BackView_text);
            mTitleTextView.setText(title);
            if (defaultRight) {
                initRightTextView(typedArray);
            }
            setChildVisible(mBackIconView, typedArray.getInt(R.styleable.BackView_icon_visible, 2));
            typedArray.recycle();

        }
        setShowPadding(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(Math.min(DimensionUtils.HEIGHT_PIXELS, DimensionUtils.WIDTH_PIXELS), showPadding ?
                getResources().getDimensionPixelOffset(R.dimen.back_view_height) + DimensionUtils.STATUS_BAR_HEIGHT :
                getResources().getDimensionPixelOffset(R.dimen.back_view_height));
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if (showPadding) {
            getChildAt(0).layout(l, t + DimensionUtils.STATUS_BAR_HEIGHT, r, b);
        } else {
            super.onLayout(changed, l, t, r, b);
        }
    }

    private void initRightTextView(@NonNull TypedArray typedArray) {
        TextView mTitleRightTextView = (TextView) findViewById(R.id.back_view_default_right_text_view);
        if (mTitleRightTextView != null) {
            String rightTitle = typedArray.getString(R.styleable.BackView_right_text);
            mTitleRightTextView.setText(rightTitle);
            float rightTitleWidth = typedArray.getDimension(R.styleable.BackView_right_text_width, 0);
            if (rightTitleWidth != 0) {
                mTitleRightTextView.setWidth((int) rightTitleWidth);
            }
            setChildVisible(mTitleRightTextView, typedArray.getInt(R.styleable.BackView_right_text_visible, 0));
            float dimension = typedArray.getDimension(R.styleable.BackView_right_text_size, DimensionUtils.dpToPx(14));
            mTitleRightTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, dimension);
        }
    }

    private boolean initRightWrapper(TypedArray typedArray) {
        int sourceId = R.layout.back_view_default_right_text_wrapper;
        if (typedArray != null) {
            sourceId = typedArray.getResourceId(R.styleable.BackView_right_wrapper_layout, R.layout.back_view_default_right_text_wrapper);
        }
        View.inflate(getContext(), sourceId, mRightWrapper);
        return sourceId == R.layout.back_view_default_right_text_wrapper;
    }

    private void setChildVisible(View view, int visible) {
        int visibility;
        switch (visible) {
            case 0:
            default:
                visibility = VISIBLE;
                break;
            case 1:
                visibility = INVISIBLE;
                break;
            case 2:
                visibility = GONE;
                break;
        }
        view.setVisibility(visibility);
    }

    public void setTitleBackClickedListener(OnClickListener listener) {
        mBackIconView.setOnClickListener(listener);
    }

    public ImageView getBackIconView(){
        return mBackIconView;
    }

    public LinearLayout getTitleRightWrapper(){
        return mRightWrapper;
    }

    public TextView getTitleTextView() {
        return mTitleTextView;
    }

    public void setBackIconVisible(int visible) {
        mBackIconView.setVisibility(visible);
    }

    public boolean isShowPadding() {
        return showPadding;
    }

    public void setShowPadding(boolean showPadding) {
        if (showPadding ^ this.showPadding) {
            this.showPadding = showPadding;
            invalidate();
        }

    }
}
