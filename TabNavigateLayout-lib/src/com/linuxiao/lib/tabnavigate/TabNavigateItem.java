package com.linuxiao.lib.tabnavigate;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * @author linuxiao Email: linuxiao@gmail.com
 * @date 2014年10月8日 上午10:06:27
 *
 */
public class TabNavigateItem extends LinearLayout {

	private Context mContext;
	private ImageView mImageView;

	private TextView mTextView;

	private LinearLayout rootLayout;

	public TabNavigateItem(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		init(context);
	}

	public TabNavigateItem(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		init(context);
		initAttrs(attrs);
	}

	/**
	 * @param context
	 * @param attrs
	 * @param defStyle
	 */
	public TabNavigateItem(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
		initAttrs(attrs);
	}

	/**
	 * 获取mImageView
	 * 
	 * @return mImageView mImageView
	 */
	public ImageView getImageView() {
		return mImageView;
	}

	/**
	 * 获取mTextView
	 * 
	 * @return mTextView mTextView
	 */
	public TextView getTextView() {
		return mTextView;
	}

	/**
	 * @Title: initAttrs
	 * @Description: TODO 初始化xml中的属性
	 * @param attrs
	 */
	private void initAttrs(AttributeSet attrs) {
		// TODO Auto-generated method stub
		TypedArray typedArray = mContext.obtainStyledAttributes(attrs,
				R.styleable.TabNavigateItem);
		initWidget(typedArray);
	}

	/**
	 * @Title: init
	 * @Description: TODO 初始化
	 */
	@SuppressLint("NewApi")
	private void init(Context context) {
		this.mContext = context;
		LayoutParams wrapParams = new LayoutParams(
				android.view.ViewGroup.LayoutParams.WRAP_CONTENT,
				android.view.ViewGroup.LayoutParams.WRAP_CONTENT);

		rootLayout = this;
		rootLayout.setLayoutParams(wrapParams);
		rootLayout.setOrientation(LinearLayout.VERTICAL);
		rootLayout.setGravity(Gravity.CENTER_HORIZONTAL);
		rootLayout.setClickable(true);
		mImageView = new ImageView(mContext);
		mImageView.setLayoutParams(wrapParams);
		mImageView.setScaleType(ScaleType.MATRIX);
		mImageView.setClickable(false);
		mImageView.setFocusable(true);
		mImageView.setFocusableInTouchMode(true);
		mImageView.setDuplicateParentStateEnabled(true);
		mTextView = new TextView(mContext);
		mTextView.setLayoutParams(wrapParams);
		mTextView.setTextSize(12.0f);
		mTextView.setClickable(false);
		mTextView.setFocusable(true);
		mTextView.setFocusableInTouchMode(true);
		mTextView.setDuplicateParentStateEnabled(true);
		rootLayout.addView(mImageView, wrapParams);
		rootLayout.addView(mTextView, wrapParams);
	}

	/***
	 * 初始化Widget
	 * 
	 * @Title: initWidget
	 * @Description: TODO
	 * @param typedArray
	 */
	private void initWidget(TypedArray typedArray) {

		String textString = typedArray
				.getString(R.styleable.TabNavigateItem_text);
		int textColor = typedArray.getColor(
				R.styleable.TabNavigateItem_text_color, 0xffffffff);
		float textSize = typedArray.getDimension(
				R.styleable.TabNavigateItem_text_size, 12);
		mTextView.setText(textString);
		mTextView.setTextColor(textColor);
		mTextView.setTextSize(textSize);

		int imageHeight = (int) typedArray.getDimension(
				R.styleable.TabNavigateItem_image_height,
				android.view.ViewGroup.LayoutParams.WRAP_CONTENT);
		int imageWidth = (int) typedArray.getDimension(
				R.styleable.TabNavigateItem_image_width,
				android.view.ViewGroup.LayoutParams.WRAP_CONTENT);
		int imageSrc = typedArray.getResourceId(
				R.styleable.TabNavigateItem_src, 0);

		int imageAlpha = typedArray.getInt(
				R.styleable.TabNavigateItem_image_alpha, 255);
		mImageView.setAlpha(imageAlpha);
		mImageView.setBackgroundResource(imageSrc);
		LayoutParams layoutParams = new LayoutParams(imageWidth, imageHeight);
		mImageView.setLayoutParams(layoutParams);

		typedArray.recycle();
	}

	/**
	 * 设置mImageView
	 * 
	 * @param mImageView
	 *            mImageView
	 */
	public void setImageView(ImageView mImageView) {
		this.mImageView = mImageView;
	}

	/**
	 * 设置mTextView
	 * 
	 * @param mTextView
	 *            mTextView
	 */
	public void setTextView(TextView mTextView) {
		this.mTextView = mTextView;
	}
}
