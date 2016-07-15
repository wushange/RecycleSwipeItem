package com.wsg.demo.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.wsg.demo.SwipeItemOnItemChildClickListener;
import com.wsg.demo.data.Person;

import java.util.ArrayList;
import java.util.List;

import cn.bingoogolapple.swipeitemlayout.BGASwipeItemLayout;

/**
 * Created by Mr.Jude on 2015/7/18.
 */
public class PersonAdapter extends RecyclerArrayAdapter<Person> {
    public PersonAdapter(Context context) {
        super(context);
    }

    SwipeItemOnItemChildClickListener swipeItemOnItemChildClickListener;

    public void setSwipeItemOnItemChildClickListener(SwipeItemOnItemChildClickListener swipeItemOnItemChildClickListener) {
        this.swipeItemOnItemChildClickListener = swipeItemOnItemChildClickListener;
    }

    /**
     * 当前处于打开状态的item
     */
    private List<BGASwipeItemLayout> mOpenedSil = new ArrayList<>();

    public void closeOpenedSwipeItemLayoutWithAnim() {
        for (BGASwipeItemLayout sil : mOpenedSil) {
            sil.closeWithAnim();
        }
        mOpenedSil.clear();
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new PersonViewHolder(parent, swipeItemOnItemChildClickListener, mOpenedSil);
    }
}
