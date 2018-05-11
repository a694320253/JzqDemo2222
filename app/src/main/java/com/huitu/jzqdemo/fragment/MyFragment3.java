package com.huitu.jzqdemo.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.huitu.jzqdemo.R;
import com.huitu.jzqdemo.activity.ZxingActivity;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by Administrator on 2018/2/9.
 */

public class MyFragment3 extends SupportFragment implements View.OnClickListener {
    private Button btn;

    public static MyFragment3 newInstance() {
        Bundle args = new Bundle();
        MyFragment3 fragment = new MyFragment3();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.myfragment3, container, false);
        Log.i("TAGTAG", "MyFragment3--onCreateView");
        btn = (Button) view.findViewById(R.id.btn);
        btn.setOnClickListener(this);
        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.i("TAGTAG", "MyFragment3--onViewCreated");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i("TAGTAG", "MyFragment3--onActivityCreated");
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        Log.i("TAGTAG", "MyFragment3--onLazyInitView");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn:
                Intent intent=new Intent(getActivity(), ZxingActivity.class);
                startActivity(intent);
                break;
        }

    }
}
