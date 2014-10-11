tabnavigate
===========

Android底部导航栏封装
一、概述
现在Android底部导航的设计风格已经十分普及，实现起来都比较繁琐。本着程序猿能偷懒就偷懒的原则，所以笔者封装了下View。

目前比较流行的解决方案大致有一下几种：

1、viewpager + tabhost + fragment

2、viewpager +  RadioButton  + fragment

这些实现起来都比较繁琐。

二、预期效果
用比较少的代码达到底部导航的效果，

三、实现过程
详细参考 http://blog.csdn.net/linuxiao/article/details/39934987
四、用法
4.1 定义item的选择器item0，item1，item2，item3
最主要要定义好state_selected

代码如下
[html] view plaincopy
<?xml version="1.0" encoding="utf-8"?>  
<selector xmlns:android="http://schemas.android.com/apk/res/android">  
  
    <item android:drawable="@drawable/tab_address_pressed" android:state_pressed="true"/>  
    <item android:drawable="@drawable/tab_address_pressed" android:state_selected="true"></item>  
    <item android:drawable="@drawable/tab_address_pressed" android:state_focused="true" android:state_pressed="false"></item>  
    <item android:drawable="@drawable/tab_address_normal" android:state_focused="false" android:state_pressed="false"/>  
    <item android:drawable="@drawable/tab_address_normal" android:state_focused="false"/>  
  
</selector>  

4.2 activity_main.xml
代码如下：
[html] view plaincopy
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"  
    xmlns:tools="http://schemas.android.com/tools"  
    xmlns:app="http://schemas.android.com/apk/res-auto"  
    android:layout_width="match_parent"  
    android:layout_height="match_parent"  
    tools:context="com.linuxiao.tabnavigatelayout_demo.MainActivity" >  
  
    <com.linuxiao.lib.tabnavigate.TabNavigateLayout  
        android:id="@+id/tabNavigateLayout1"  
        android:layout_width="match_parent"  
        android:layout_height="wrap_content"  
        android:layout_alignParentBottom="true"  
        android:background="@drawable/bottom_bar" >  
  
        <com.linuxiao.lib.tabnavigate.TabNavigateItem  
            android:layout_width="wrap_content"  
            android:layout_height="wrap_content"  
            app:src="@drawable/item_0"  
            app:text="首页" >  
        </com.linuxiao.lib.tabnavigate.TabNavigateItem>  
  
        <com.linuxiao.lib.tabnavigate.TabNavigateItem  
            android:layout_width="wrap_content"  
            android:layout_height="wrap_content"  
            app:src="@drawable/item_1"  
            app:text="消息" >  
        </com.linuxiao.lib.tabnavigate.TabNavigateItem>  
  
        <com.linuxiao.lib.tabnavigate.TabNavigateItem  
            android:layout_width="wrap_content"  
            android:layout_height="wrap_content"  
            app:src="@drawable/item_2"  
            app:text="更多" >  
        </com.linuxiao.lib.tabnavigate.TabNavigateItem>  
  
        <com.linuxiao.lib.tabnavigate.TabNavigateItem  
            android:layout_width="wrap_content"  
            android:layout_height="wrap_content"  
            app:src="@drawable/item_3"  
            app:text="设置" >  
        </com.linuxiao.lib.tabnavigate.TabNavigateItem>  
    </com.linuxiao.lib.tabnavigate.TabNavigateLayout>  
  
    <FrameLayout  
        android:id="@+id/container"  
        android:layout_width="wrap_content"  
        android:layout_height="wrap_content"  
        android:layout_above="@+id/tabNavigateLayout1"  
        android:layout_alignLeft="@+id/tabNavigateLayout1"  
        android:layout_alignParentTop="true"  
        android:layout_alignRight="@+id/tabNavigateLayout1"   
        android:background="#cdcdcd">  
  
    </FrameLayout>  
  
</RelativeLayout>  
4.3 MainActivity
比较简单代码如下：

  
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

4.4 ItemFragment
  
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



转载请注明出处http://blog.csdn.net/linuxiao/article/details/39934987