package com.wsg.demo;

import android.view.View;
import android.view.ViewGroup;

public interface SwipeItemOnItemChildClickListener {
    void onItemChildClick(ViewGroup var1, View var2, int var3);

    boolean onItemChildLongClick(ViewGroup var1, View var2, int var3);
}
