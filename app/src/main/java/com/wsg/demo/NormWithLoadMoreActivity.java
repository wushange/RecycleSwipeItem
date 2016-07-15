package com.wsg.demo;

import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.wsg.demo.data.DataProvider;
import com.wsg.demo.adapter.PersonAdapter;

import org.xutils.common.util.LogUtil;
import org.xutils.view.annotation.ViewInject;

import jp.wasabeef.recyclerview.animators.ScaleInBottomAnimator;

/**
 * Created by wushange on 2016/07/14.
 */
public class NormWithLoadMoreActivity extends BaseActivity implements SwipeItemOnItemChildClickListener, android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener, RecyclerArrayAdapter.OnLoadMoreListener {
    private PersonAdapter adapter;
    @ViewInject(R.id.recyclerview)
    EasyRecyclerView recyclerView;
    @ViewInject(R.id.toolbar)
    Toolbar toolbar;
    private int page = 0;

    @Override
    public int initContentView() {
        return R.layout.activity_loadmore_view;
    }

    @Override
    public void initUiAndListener() {
        initToolBar(toolbar);
        setTitle("NormWithLoadMoreActivity");
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new ScaleInBottomAnimator(new OvershootInterpolator(1f)));
        recyclerView.setAdapterWithProgress(adapter = new PersonAdapter(this));
        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if (RecyclerView.SCROLL_STATE_DRAGGING == newState) {
                    adapter.closeOpenedSwipeItemLayoutWithAnim();
                }
            }
        });
        recyclerView.setRefreshListener(this);
        adapter.setMore(R.layout.view_more, this);
        adapter.setNoMore(R.layout.view_nomore);
        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                showToast("点击了" + position);
            }
        });
        adapter.setOnItemLongClickListener(new RecyclerArrayAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemClick(int position) {
                showToast("长按了" + position);
                return true;
            }
        });
        adapter.setError(R.layout.view_error).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.resumeMore();
            }
        });

        adapter.setSwipeItemOnItemChildClickListener(this);//设置侧滑菜单控件点击监听

        onRefresh();
    }

    @Override
    public void onLoadMore() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                adapter.addAll(DataProvider.getPersonList(page));
                page++;
            }
        }, 1000);
    }

    @Override
    public void onRefresh() {
        page = 0;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                adapter.clear();
                adapter.addAll(DataProvider.getPersonList(page));
                page++;
            }
        }, 1000);
    }

    @Override
    public void onItemChildClick(ViewGroup var1, View childView, int position) {
        LogUtil.e("--onItemChildClick call back--" + childView.getId());
        if (childView.getId() == R.id.tv_item_swipe_delete) {
            adapter.remove(position);
        }
        if (childView.getId() == R.id.tv_item_swipe_detial) {
            showToast("详情 ");
        }
    }

    @Override
    public boolean onItemChildLongClick(ViewGroup var1, View childView, int position) {
        LogUtil.e("--onItemChildLongClick call back--" + childView.getId());
        if (childView.getId() == R.id.tv_item_swipe_delete) {
            showToast("长按了删除 ");
            return true;
        }
        if (childView.getId() == R.id.tv_item_swipe_detial) {
            showToast("长按了详情 ");
            return true;
        }
        return false;
    }


}
