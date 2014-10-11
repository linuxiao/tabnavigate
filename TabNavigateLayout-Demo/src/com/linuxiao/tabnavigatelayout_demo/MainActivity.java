package com.linuxiao.tabnavigatelayout_demo;

import com.linuxiao.lib.tabnavigate.TabNavigateLayout;
import com.linuxiao.lib.tabnavigate.TabNavigateLayout.OnItemSelectedListener;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends FragmentActivity implements OnItemSelectedListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		TabNavigateLayout navigateLayout = (TabNavigateLayout)findViewById(R.id.tabNavigateLayout1);
		navigateLayout.setOnItemSelectedListener(this);
		getSupportFragmentManager().beginTransaction().add(R.id.container, new ItemFragment(0)).commit();
	}

	@Override
	public void OnItemSelected(View v, int position) {
		getSupportFragmentManager().beginTransaction().replace(R.id.container, new ItemFragment(position)).commit();
	}


}
