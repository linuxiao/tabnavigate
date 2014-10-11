package com.linuxiao.lib.tabnavigate;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * @author linuxiao Email: linuxiao@gmail.com
 * @date 2014年10月8日 上午10:05:42
 *
 */
public class TabNavigateLayout extends LinearLayout {
	class MyOnClickListener implements OnClickListener {
		private int position;

		public MyOnClickListener(int position) {
			this.position = position;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see android.view.View.OnClickListener#onClick(android.view.View)
		 */
		@Override
		public void onClick(View v) {
			try {
				oldIndex = curIndex;
				curIndex = position;
				setSelectedPosition(curIndex);
				mSelectedListener.OnItemSelected(v, curIndex);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	class MyOnFocusChangeListener implements OnFocusChangeListener {

		private int position;

		public MyOnFocusChangeListener(int position) {
			this.position = position;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * android.view.View.OnFocusChangeListener#onFocusChange(android.view
		 * .View, boolean)
		 */
		@Override
		public void onFocusChange(View v, boolean hasFocus) {
			if (hasFocus) {
				try {
					oldIndex = curIndex;
					curIndex = position;
					setSelectedPosition(curIndex);
					mSelectedListener.OnItemSelected(v, curIndex);
					
				} catch (Exception e) {

				}
			}
		}
	}

	public interface OnItemSelectedListener {
		public void OnItemSelected(View v, int position);
	}

	private TranslateAnimation animation;
	private int curIndex = 0;
	private int itemWidth;
	private LinearLayout linearLayout;
	private Context mContext;
	private List<LinearLayout> mItems;
	private OnItemSelectedListener mSelectedListener;

	private ImageView mSelectImage;

	protected int oldIndex = 0;

	private LinearLayout rootLayout;
	private int selectedImageSrc = 0;

	public TabNavigateLayout(Context context) {
		super(context);
		init(context);
	}

	public TabNavigateLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
		initAttrs(attrs);
	}

	public TabNavigateLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
		initAttrs(attrs);

	}

	public OnItemSelectedListener getOnItemSelectedListener() {
		return mSelectedListener;
	}

	/**
	 * @Title: init
	 * @Description: TODO 初始化
	 * @param attrs
	 */
	private void initAttrs(AttributeSet attrs) {
		// TODO Auto-generated method stub
		TypedArray typedArray = mContext.obtainStyledAttributes(attrs,
				R.styleable.TabNavigateItem);
		selectedImageSrc = typedArray.getResourceId(
				R.styleable.TabNavigateLayout_selected_bg, R.drawable.tab_bg);
		typedArray.recycle();

	}

	/**
	 * @Title: init
	 * @Description: TODO 初始化
	 * @param context
	 */
	private void init(Context context) {
		this.rootLayout = this;
		this.mContext = context;
		if (mItems == null) {
			mItems = new ArrayList<LinearLayout>();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.view.ViewGroup#onAttachedToWindow()
	 */
	@Override
	protected void onAttachedToWindow() {
		// TODO Auto-generated method stub
		super.onAttachedToWindow();
		// 获取布局中的item存放在数组中
		for (int i = 0; i < rootLayout.getChildCount(); i++) {
			final int tmp = i;
			TabNavigateItem tabItem = (TabNavigateItem) rootLayout
					.getChildAt(i);
			MyOnClickListener myOnClickListener = new MyOnClickListener(tmp);
			tabItem.setOnClickListener(myOnClickListener);
			MyOnFocusChangeListener mFocusChangeListener = new MyOnFocusChangeListener(
					tmp);
			tabItem.getImageView().setOnFocusChangeListener(
					mFocusChangeListener);
			mItems.add(tabItem);
		}
		// 先移除现有布局，重新代码布局
		rootLayout.removeAllViews();
		// 将item加入linearLayout中
		linearLayout = new LinearLayout(mContext);
		android.widget.LinearLayout.LayoutParams warpParams = new android.widget.LinearLayout.LayoutParams(
				android.view.ViewGroup.LayoutParams.WRAP_CONTENT,
				android.view.ViewGroup.LayoutParams.WRAP_CONTENT, 1.0f);
		for (LinearLayout item : mItems) {
			linearLayout.addView(item, warpParams);
		}
//		// 获取不到分辨率
//		int displayWidth = getMeasuredWidth();
//		itemWidth = displayWidth / mItems.size(); // 设置水平动画平移大小
		// 选择动画背景
		mSelectImage = new ImageView(mContext);
		LayoutParams lpSelectedImage = new LayoutParams(android.view.ViewGroup.LayoutParams.WRAP_CONTENT,
				android.view.ViewGroup.LayoutParams.WRAP_CONTENT);
		mSelectImage.setLayoutParams(lpSelectedImage);
		mSelectImage.setImageResource(selectedImageSrc);
		// FragmLayout
		FrameLayout frameLayout = new FrameLayout(mContext);
		android.widget.FrameLayout.LayoutParams fragmeParams = new android.widget.FrameLayout.LayoutParams(
				android.view.ViewGroup.LayoutParams.MATCH_PARENT,
				android.view.ViewGroup.LayoutParams.MATCH_PARENT);
		frameLayout.setLayoutParams(fragmeParams);
		android.widget.FrameLayout.LayoutParams selectedImageParams = new android.widget.FrameLayout.LayoutParams(
				android.view.ViewGroup.LayoutParams.MATCH_PARENT,
				android.view.ViewGroup.LayoutParams.WRAP_CONTENT);
		selectedImageParams.gravity = Gravity.BOTTOM;
		frameLayout.addView(mSelectImage, selectedImageParams);
		frameLayout.addView(linearLayout);
		rootLayout.addView(frameLayout);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.view.View#onDraw(android.graphics.Canvas)
	 */
	@SuppressLint("DrawAllocation")
	@Override
	protected void onDraw(Canvas canvas) {

		int displayWidth = getMeasuredWidth();
		itemWidth = displayWidth / mItems.size(); // 设置水平动画平移大小
		android.view.ViewGroup.LayoutParams layoutParams = mSelectImage
				.getLayoutParams();
		layoutParams.width = itemWidth;
		mSelectImage.setLayoutParams(layoutParams);

	}

	public void setOnItemSelectedListener(
			OnItemSelectedListener mSelectedListener) {
		this.mSelectedListener = mSelectedListener;
	}

	/**
	 * @Title: setSelectedPosition
	 * @Description: TODO
	 * @param i
	 */
	public void setSelectedPosition(int i) {
		if (mItems != null && mItems.size() > i) {
			for (int j = 0; j < mItems.size(); j++) {
				if (i == j) {
					mItems.get(curIndex).setSelected(true);
				} else {
					mItems.get(j).setSelected(false);
				}
			}
			animation = new TranslateAnimation(oldIndex * itemWidth, itemWidth
					* i, 0, 0);
			animation.setFillAfter(true);// True:图片停在动画结束位置
			animation.setDuration(150);
			mSelectImage.startAnimation(animation);
		}

	}

}
