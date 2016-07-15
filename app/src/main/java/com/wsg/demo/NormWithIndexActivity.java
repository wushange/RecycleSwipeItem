package com.wsg.demo;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.TextView;

import com.wsg.demo.adapter.IndexAdapter;
import com.wsg.demo.data.DataProvider;
import com.wsg.demo.data.Person;
import com.wsg.demo.util.PinyinComparator;
import com.wsg.demo.widget.MyLetterView;

import org.xutils.common.util.LogUtil;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by wushange on 2016/07/14.
 */
public class NormWithIndexActivity extends BaseActivity implements SwipeItemOnItemChildClickListener, AbsListView.OnScrollListener {

    @ViewInject(R.id.listview)
    ListView mListView;
    @ViewInject(R.id.mlv_right_letter)
    private MyLetterView letterSideBar; // 字母导航栏
    @ViewInject(R.id.tv_dialog)
    private TextView dialog;

    @ViewInject(R.id.toolbar)
    Toolbar toolbar;
    IndexAdapter adapter;
    List<Person> persons = new ArrayList<Person>();
    int itemPosition, top;

    @Override
    public int initContentView() {
        return R.layout.activity_index_view;
    }

    @Override
    public void initUiAndListener() {
        initToolBar(toolbar);
        setTitle("NormWithIndexActivity");
        adapter = new IndexAdapter(this, persons);
        adapter.setSwipeItemOnItemChildClickListener(this);
        adapter.setOnItemClickListener(new IndexAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                showToast("点击了第" + position + "个");
            }
        });
        adapter.setOnItemLongClickListener(new IndexAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemClick(int position) {
                showToast("长按了" + position + "个");
                return true;
            }
        });
        mListView.setAdapter(adapter);
        mListView.setOnScrollListener(this);
        initFirstLetter();
        persons = DataProvider.getIndexList();
        Collections.sort(persons,
                new PinyinComparator());
        adapter.updateDatas(persons);
    }

    /**
     * 初始化右侧字母
     */
    private void initFirstLetter() {
        letterSideBar.setTextView(dialog);
        letterSideBar
                .setOnTouchingLetterChangedListener(new LetterListViewListener());
        mListView.setSelectionFromTop(itemPosition, top);
    }

    @Override
    public void onItemChildClick(ViewGroup viewGroup, View childView, int position) {
        LogUtil.e("--onItemChildClick call back--" + childView.getId());
        if (childView.getId() == R.id.tv_item_swipe_delete) {
            persons.remove(position);
            adapter.updateDatas(persons);
            adapter.closeOpenedSwipeItemLayoutWithAnim();
            showToast(" 删除 ");
        }
        if (childView.getId() == R.id.tv_item_swipe_detial) {
            showToast(" 详情 ");
        }
    }

    public void showItemAnim(final View view) {
        Animation animation = AnimationUtils.loadAnimation(view.getContext(), R.anim.item_bottom_in);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                view.setAlpha(1);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        view.startAnimation(animation);
    }

    @Override
    public boolean onItemChildLongClick(ViewGroup viewGroup, View childView, int position) {
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

    @Override
    public void onScrollStateChanged(AbsListView absListView, int i) {
        adapter.closeOpenedSwipeItemLayoutWithAnim();
    }

    @Override
    public void onScroll(AbsListView absListView, int i, int i1, int i2) {

    }


    private class LetterListViewListener implements
            MyLetterView.OnTouchingLetterChangedListener {

        @Override
        public void onTouchingLetterChanged(String s) {
            if (adapter != null && mListView != null) {
                // 该字母首次出现的位置
                int position = adapter.getPositionForSection(s.charAt(0));
                if (position != -1) {
                    mListView.setSelection(position);
                } else {
//                    mListView.setSelection(0);
                }
            }
        }
    }
}
