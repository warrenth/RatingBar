package pe.warrneth.ratingbar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by 152317 on 2017-03-28.
 */

public class RatingBar extends RelativeLayout {

    private int mRatingColor;
    private int mRatingMaxCount;
    private float mRatingWidth;
    private float mRatingHeight;
    private float mRatingGap;
    private float mRatingCount;
    private Drawable mRatingBackground;

    public static final int DEFAULT_IMAGE_HIEHGT =  60;
    public static final int DEFAULT_IMAGE_WIDTH  =  60;

    private RelativeLayout mRateView;

    public RatingBar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public RatingBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setXmlAttribute(context, attrs);
        initView(context);
    }

    public void setXmlAttribute(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.RatingBar);
        mRatingCount = typedArray.getFloat(R.styleable.RatingBar_rating_rateCount, 2.5f);
        mRatingMaxCount = typedArray.getInteger(R.styleable.RatingBar_rating_maxCount, 5);
        mRatingWidth = typedArray.getDimension(R.styleable.RatingBar_rating_imageWidth, DEFAULT_IMAGE_WIDTH);
        mRatingHeight = typedArray.getDimension(R.styleable.RatingBar_rating_imageHeight, DEFAULT_IMAGE_HIEHGT);
        mRatingGap = typedArray.getDimension(R.styleable.RatingBar_rating_imageGap, 0);
        mRatingColor = typedArray.getInteger(R.styleable.RatingBar_rating_color, Color.parseColor("#ff0000"));
        mRatingBackground = typedArray.getDrawable(R.styleable.RatingBar_rating_background);
        typedArray.recycle();
    }

    public void initView(final Context context) {
        removeAllViews();
        setGravity(CENTER_VERTICAL);

        LinearLayout rootLayout = new LinearLayout(context);
        rootLayout.setOrientation(LinearLayout.HORIZONTAL);
        rootLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        addView(rootLayout);

        for(int i = 0; i < mRatingMaxCount; i++) {
            ImageView imageView = getRatingImageView(context);
            rootLayout.addView(imageView);
        }
        setRateView(context);
        setPaintBackground();
        rootLayout.bringToFront();
    }

    private void setRateView(Context context) {
        mRateView = new RelativeLayout(context);
        mRateView.setBackgroundColor(mRatingColor);
        mRateView.setGravity(Gravity.CENTER);
        addView(mRateView);
    }

    public ImageView getRatingImageView(Context context) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams((int)mRatingWidth, (int)mRatingHeight);
        ImageView ImageView = new ImageView(context);
        ImageView.setLayoutParams(params);
        ImageView.setImageDrawable(mRatingBackground);
        ImageView.setPadding(0,0,(int)mRatingGap,0);
        return ImageView;
    }

    public void setPaintBackground() {
        this.post(new Runnable() {
            @Override
            public void run() {
                int width = (int)  (mRatingCount * getWidth()) / mRatingMaxCount;
                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(width, getHeight());
                mRateView.setLayoutParams(params);
            }
        });
    }

    public int getRatingColor() {
        return mRatingColor;
    }

    public void setRatingColor(int mRatingColor) {
        this.mRatingColor = mRatingColor;
    }

    public int getRatingMaxCount() {
        return mRatingMaxCount;
    }

    public void setRatingMaxCount(int mRatingMaxCount) {
        this.mRatingMaxCount = mRatingMaxCount;
    }

    public float getRatingWidth() {
        return mRatingWidth;
    }

    public void setRatingWidth(float mRatingWidth) {
        this.mRatingWidth = mRatingWidth;
    }

    public float getRatingHeight() {
        return mRatingHeight;
    }

    public void setRatingHeight(float mRatingHeight) {
        this.mRatingHeight = mRatingHeight;
    }

    public float getRatingGap() {
        return mRatingGap;
    }

    public void setRatingGap(float mRatingGap) {
        this.mRatingGap = mRatingGap;
    }

    public float getRatingCount() {
        return mRatingCount;
    }

    public void setRatingCount(float mRatingCount) {
        this.mRatingCount = mRatingCount;
    }

    public Drawable getRatingBackground() {
        return mRatingBackground;
    }

    public void setRatingBackground(Drawable mRatingBackground) {
        this.mRatingBackground = mRatingBackground;
    }

    public void invalidateView() {
        initView(getContext());
    }
}
