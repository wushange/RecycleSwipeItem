package com.wsg.demo;

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
public class NormActivity extends BaseActivity implements SwipeItemOnItemChildClickListener {
    private PersonAdapter adapter;
    @ViewInject(R.id.recyclerview)
    EasyRecyclerView recyclerView;
    @ViewInject(R.id.toolbar)
    Toolbar toolbar;

    @Override
    public int initContentView() {
        return R.layout.activity_norm_view;
    }

    @Override
    public void initUiAndListener() {
        initToolBar(toolbar);
        setTitle("NormActivity");
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new ScaleInBottomAnimator(new OvershootInterpolator(1f)));
        recyclerView.setAdapter(adapter = new PersonAdapter(this));
        adapter.setSwipeItemOnItemChildClickListener(this);//设置侧滑菜单控件点击监听
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
        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if (RecyclerView.SCROLL_STATE_DRAGGING == newState) {
                    adapter.closeOpenedSwipeItemLayoutWithAnim();
                }
            }
        });
        adapter.addAll(DataProvider.getPersonList(1));

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
