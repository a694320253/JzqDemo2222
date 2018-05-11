package com.huitu.jzqdemo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.huitu.jzqdemo.R;

import me.yokeyword.fragmentation.SupportFragment;
import q.rorbin.badgeview.QBadgeView;

/**
 * Created by Administrator on 2018/2/9.
 */

public class MyFragment2 extends SupportFragment {
private TextView tv;

    public static MyFragment2 newInstance() {
        Bundle args = new Bundle();
        MyFragment2 fragment = new MyFragment2();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.myfragment2, container, false);
        Log.i("TAGTAG", "MyFragment2--onCreateView");
        initView(view);
        return view;
    }

    private void initView(View view) {
        tv= (TextView) view.findViewById(R.id.tv);
//        new QBadgeView(getActivity()).bindTarget(tv).setBadgeNumber(5);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.i("TAGTAG", "MyFragment2--onViewCreated");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i("TAGTAG", "MyFragment2--onActivityCreated");
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        Log.i("TAGTAG", "MyFragment2--onLazyInitView");
    }
}
