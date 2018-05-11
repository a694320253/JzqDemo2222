package com.huitu.jzqdemo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.huitu.jzqdemo.R;
import com.huitu.jzqdemo.utils.SPUtils;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by Administrator on 2018/2/9.
 */

public class MyFragment4 extends SupportFragment implements View.OnClickListener {
    private Button btn1, btn2, btn3;
    SPUtils spUtils;

    public static MyFragment4 newInstance() {
        Bundle args = new Bundle();
        MyFragment4 fragment = new MyFragment4();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.myfragment4, container, false);
        if (spUtils == null) {
            spUtils = SPUtils.getInstance("test");
        }
        initView(view);
        return view;
    }

    private void initView(View view) {
        btn1 = (Button) view.findViewById(R.id.btn1);
        btn1.setOnClickListener(this);
        btn2 = (Button) view.findViewById(R.id.btn2);
        btn2.setOnClickListener(this);
        btn3 = (Button) view.findViewById(R.id.btn3);
        btn3.setOnClickListener(this);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.i("TAGTAG", "MyFragment4--onViewCreated");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i("TAGTAG", "MyFragment4--onActivityCreated");
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        Log.i("TAGTAG", "MyFragment4--onLazyInitView");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn1:
                spUtils.put("btn", 1);
                break;
            case R.id.btn2:
                spUtils.put("btn", 2);
                break;
            case R.id.btn3:
                int test = spUtils.getInt("btn");
                Toast.makeText(getActivity(), test+"", Toast.LENGTH_SHORT).show();
                break;
        }

    }
}
