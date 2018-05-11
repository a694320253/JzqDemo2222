package com.huitu.jzqdemo.activity;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.huitu.jzqdemo.R;
import com.huitu.jzqdemo.fragment.MyFragment1;
import com.huitu.jzqdemo.fragment.MyFragment2;
import com.huitu.jzqdemo.fragment.MyFragment3;
import com.huitu.jzqdemo.fragment.MyFragment4;
import com.huitu.jzqdemo.utils.BottomNavigationViewHelper;

import me.yokeyword.fragmentation.SupportFragment;
import q.rorbin.badgeview.Badge;
import q.rorbin.badgeview.QBadgeView;


public class MainActivity extends BaseActivity {
    public static final int FIRST = 0;
    public static final int SECOND = 1;
    public static final int THIRD = 2;
    public static final int FOURTH = 3;
    private BottomNavigationView bottomNavigationView;
    private int prePosition = 0;
    private SupportFragment[] mFragments = new SupportFragment[4];
    private QBadgeView badgeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SupportFragment firstFragment = findFragment(MyFragment1.class);
        if (firstFragment == null) {
            mFragments[FIRST] = MyFragment1.newInstance();
            mFragments[SECOND] = MyFragment2.newInstance();
            mFragments[THIRD] = MyFragment3.newInstance();
            mFragments[FOURTH] = MyFragment4.newInstance();
            loadMultipleRootFragment(R.id.count, FIRST,
                    mFragments[FIRST],
                    mFragments[SECOND],
                    mFragments[THIRD],
                    mFragments[FOURTH]);
        } else {
            // 这里库已经做了Fragment恢复,所有不需要额外的处理了, 不会出现重叠问题
            // 这里我们需要拿到mFragments的引用
            mFragments[FIRST] = firstFragment;
            mFragments[SECOND] = findFragment(MyFragment2.class);
            mFragments[THIRD] = findFragment(MyFragment3.class);
            mFragments[FOURTH] = findFragment(MyFragment4.class);
        }
        initView();//
    }

    private void initView() {
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigationView);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.item_home:
                        if (prePosition != FIRST) {
                            showHideFragment(mFragments[FIRST], mFragments[prePosition]);
                            prePosition = FIRST;
                            Log.i("TAGTAG", "0");
                            if (badgeView!=null){
                                badgeView.hide(false);
                            }
                        }
                        return true;
                    case R.id.item_category:
                        if (prePosition != SECOND) {
                            showHideFragment(mFragments[SECOND], mFragments[prePosition]);
                            prePosition = SECOND;
                            Log.i("TAGTAG", "1");
                            if (badgeView!=null){
                                badgeView.hide(true);
                            }
                        }
                        return true;
                    case R.id.item_mine:
                        if (prePosition != THIRD) {
                            showHideFragment(mFragments[THIRD], mFragments[prePosition]);
                            prePosition = THIRD;
                            Log.i("TAGTAG", "2");
                            if (badgeView!=null){
                                badgeView.setBadgeNumber(6);
                            }
                        }
                        return true;
                    case R.id.item_news:
                        if (prePosition != FOURTH) {
                            showHideFragment(mFragments[FOURTH], mFragments[prePosition]);
                            prePosition = FOURTH;
                            //如果没有消息，不需要显示的时候那只需要将它隐藏即可
//                            count.setVisibility(View.GONE);
                            Log.i("TAGTAG", "3");
                        }
                        return true;
                }
                Log.i("TAGTAG", "return");
                return false;
            }
        });

        //获取整个的NavigationView
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) bottomNavigationView.getChildAt(0);
//        Log.i("TAGTAG",menuView.getChildCount()+"");
//这里就是获取所添加的每一个Tab(或者叫menu)，
        View tab = menuView.getChildAt(2);
        BottomNavigationItemView itemView = (BottomNavigationItemView) tab;
        badgeView = (QBadgeView) new QBadgeView(MainActivity.this).bindTarget(itemView).setBadgeNumber(5).setBadgeGravity(Gravity.TOP | Gravity.END).setGravityOffset(20, 0, true);
////加载我们的角标View，新创建的一个布局
//        View badge = LayoutInflater.from(this).inflate(R.layout.menu_badge, menuView, false);
////添加到Tab上
//        itemView.addView(badge);
//        final TextView count = (TextView) badge.findViewById(R.id.tv_msg_count);
//        count.setText(String.valueOf(99));

//如果没有消息，不需要显示的时候那只需要将它隐藏即可
//        count.setVisibility(View.GONE);
    }


//    @Override
//    public void onBackPressedSupport() {
//        super.onBackPressedSupport();
//        Log.i("TAGTAG","onBackPressedSupport");
//    }
//
//    //记录用户首次点击返回键的时间
//    private long firstTime=0;
//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        Log.i("TAGTAG","onKeyDown");
//        if(keyCode==KeyEvent.KEYCODE_BACK && event.getAction()==KeyEvent.ACTION_DOWN){
//            if (System.currentTimeMillis()-firstTime>2000){
//                Toast.makeText(MainActivity.this,"再按一次退出程序--->onKeyDown",Toast.LENGTH_SHORT).show();
//                firstTime=System.currentTimeMillis();
//            }else{
//                return super.onKeyDown(keyCode, event);
////                System.exit(0);
//            }
//            return true;
//        }
//        return super.onKeyDown(keyCode, event);
//    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("TAGTAG", "onDestroy");
    }
}
