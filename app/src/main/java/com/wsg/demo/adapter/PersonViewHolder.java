package com.wsg.demo.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.wsg.demo.R;
import com.wsg.demo.SwipeItemOnItemChildClickListener;
import com.wsg.demo.data.Person;

import java.util.List;

import cn.bingoogolapple.swipeitemlayout.BGASwipeItemLayout;


/**
 * Created by Mr.Jude on 2015/2/22.
 */
public class PersonViewHolder extends BaseViewHolder<Person> implements View.OnClickListener, View.OnLongClickListener {
    private TextView mTv_name;
    private ImageView mImg_face;
    private TextView mTv_sign;
    private TextView tv_item_swipe_delete;
    private TextView tv_item_swipe_detial;
    private BGASwipeItemLayout itemLayout;
    private SwipeItemOnItemChildClickListener swipeItemOnItemChildClickListener = null;
    private List<BGASwipeItemLayout> mOpenedSil;

    public PersonViewHolder(ViewGroup parent, SwipeItemOnItemChildClickListener swipeItemOnItemChildClickListener, List<BGASwipeItemLayout> mOpenedSil) {
        super(parent, R.layout.item_person);
        this.swipeItemOnItemChildClickListener = swipeItemOnItemChildClickListener;
        this.mOpenedSil = mOpenedSil;
        mTv_name = $(R.id.person_name);
        mTv_sign = $(R.id.person_sign);
        mImg_face = $(R.id.person_face);
        tv_item_swipe_delete = $(R.id.tv_item_swipe_delete);
        tv_item_swipe_detial = $(R.id.tv_item_swipe_detial);
        itemLayout = $(R.id.sil_item_swipe_root);

    }

    @Override
    public void setData(final Person person) {
        tv_item_swipe_delete.setOnClickListener(this);
        tv_item_swipe_delete.setOnLongClickListener(this);
        tv_item_swipe_detial.setOnClickListener(this);
        tv_item_swipe_detial.setOnLongClickListener(this);
        itemLayout.setDelegate(new BGASwipeItemLayout.BGASwipeItemLayoutDelegate() {
            @Override
            public void onBGASwipeItemLayoutOpened(BGASwipeItemLayout swipeItemLayout) {
                closeOpenedSwipeItemLayoutWithAnim();
                mOpenedSil.add(swipeItemLayout);
            }

            @Override
            public void onBGASwipeItemLayoutClosed(BGASwipeItemLayout swipeItemLayout) {
                mOpenedSil.remove(swipeItemLayout);
            }

            @Override
            public void onBGASwipeItemLayoutStartOpen(BGASwipeItemLayout swipeItemLayout) {
                closeOpenedSwipeItemLayoutWithAnim();
            }
        });
        mTv_name.setText(person.getName());
        mTv_sign.setText(person.getSign());
        Glide.with(getContext())
                .load(person.getFace())
                .placeholder(R.mipmap.ic_launcher)
                .into(mImg_face);
    }


    @Override
    public void onClick(View view) {
        if (null != this.swipeItemOnItemChildClickListener) {
            this.swipeItemOnItemChildClickListener.onItemChildClick(null, view, this.getAdapterPosition());
        }
    }

    @Override
    public boolean onLongClick(View view) {
        return null != this.swipeItemOnItemChildClickListener ? this.swipeItemOnItemChildClickListener.onItemChildLongClick(null, view, this.getAdapterPosition()) : false;
    }

    public void closeOpenedSwipeItemLayoutWithAnim() {
        for (BGASwipeItemLayout sil : mOpenedSil) {
            sil.closeWithAnim();
        }
        mOpenedSil.clear();
    }

}
