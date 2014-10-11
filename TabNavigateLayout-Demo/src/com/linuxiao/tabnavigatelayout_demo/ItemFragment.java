/**
 * @Title: ItemFragment.java
 * @Description: TODO
 * @author: linuxiao
 * Email: linuxiao@gmail.com
 * @date 2014年10月9日 下午5:39:18
 */
package com.linuxiao.tabnavigatelayout_demo;

import android.R.integer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * @author linuxiao
 * Email: linuxiao@gmail.com
 * @date 2014年10月9日 下午5:39:18
 *
 */
public class ItemFragment extends Fragment {
	
	private int position;

	public ItemFragment(int position) {
		this.position = position;
	}
	/* (non-Javadoc)
	 * @see android.support.v4.app.Fragment#onCreateView(android.view.LayoutInflater, android.view.ViewGroup, android.os.Bundle)
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		TextView tv = new TextView(getActivity());
		tv.setText("这是第" + position + "个Fragment");
		return tv;
	}
}
