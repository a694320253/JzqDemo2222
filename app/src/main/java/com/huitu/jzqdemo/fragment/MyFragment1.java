package com.huitu.jzqdemo.fragment;

import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.huitu.jzqdemo.R;
import com.huitu.jzqdemo.adapter.PullToRefreshAdapter;
import com.huitu.jzqdemo.bean.Bean;
import com.huitu.jzqdemo.utils.AppConfig;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import me.yokeyword.fragmentation.SupportFragment;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2018/2/9.
 */

public class MyFragment1 extends SupportFragment implements SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {
    private static final String TAG = "MyFragment1";
    private SwipeRefreshLayout refresh_layout;
    private RecyclerView recyclerView;
    private PullToRefreshAdapter mAdapter;
    private int page = 1;

    public static MyFragment1 newInstance() {
        Bundle args = new Bundle();
        MyFragment1 fragment = new MyFragment1();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.myfragment1, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        refresh_layout = (SwipeRefreshLayout) view.findViewById(R.id.refresh_layout);
        refresh_layout.setColorSchemeColors(getResources().getColor(R.color.colorAccent), getResources().getColor(R.color.colorPrimary), getResources().getColor(R.color.colorPrimaryDark));
        refresh_layout.setOnRefreshListener(this);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycleView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new PullToRefreshAdapter(R.layout.adapter_item, null, getActivity());
        mAdapter.setOnLoadMoreListener(this);
        mAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
        recyclerView.setAdapter(mAdapter);

    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.i("TAGTAG", "MyFragment1--onViewCreated");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i("TAGTAG", "MyFragment1--onActivityCreated");
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        refresh();
    }

    private void refresh() {
        page = 1;
        mAdapter.setEnableLoadMore(false);
        refresh_layout.setRefreshing(true);
        OkHttpClient okHttpClient = new OkHttpClient();
        final Request request = new Request.Builder().url(AppConfig.path + page).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                mAdapter.setEnableLoadMore(true);
                refresh_layout.setRefreshing(false);
                Log.i(TAG, "请求失败");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
//                mAdapter.setEnableLoadMore(true);
//                refresh_layout.setRefreshing(false);
                if (response.isSuccessful()) {
                    final List<Bean> list = new ArrayList<>();
                    try {
                        JSONObject object = new JSONObject(response.body().string());
                        String code = object.getString("code");
                        if (code.equals("10000")) {
                            JSONObject result = object.getJSONObject("result");
                            JSONArray maplist = result.getJSONArray("maplist");
                            for (int i = 0; i < maplist.length(); i++) {
                                String userImg = maplist.getJSONObject(i).getString("userImg");
                                String userRealname = maplist.getJSONObject(i).getString("userRealname");
                                Bean bean = new Bean(userRealname, userImg);
                                list.add(bean);
                            }
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    mAdapter.setEnableLoadMore(true);
                                    refresh_layout.setRefreshing(false);
                                    mAdapter.setNewData(list);
                                }
                            });

                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }
        });

    }

    //下拉刷新回调的方法
    @Override
    public void onRefresh() {
        refresh();
    }

    //上拉加载回调的方法
    @Override
    public void onLoadMoreRequested() {
        Log.i("TAGTAG", "onLoadMoreRequested");
    }
}
