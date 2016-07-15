package com.wsg.demo;

import android.content.Intent;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import org.xutils.view.annotation.ViewInject;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    @ViewInject(R.id.norm_view)
    AppCompatTextView normView;
    @ViewInject(R.id.loadmoreview)
    AppCompatTextView loadMoreView;
    @ViewInject(R.id.indexview)
    AppCompatTextView indexView;

    @ViewInject(R.id.toolbar)
    Toolbar toolbar;


    @Override
    public int initContentView() {
        return R.layout.activity_main;
    }

    @Override
    public void initUiAndListener() {
        initToolBar(toolbar);
        setTitle("Demo");
        normView.setOnClickListener(this);
        loadMoreView.setOnClickListener(this);
        indexView.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.norm_view:
                startActivity(new Intent(this, NormActivity.class));
                break;
            case R.id.loadmoreview:
                startActivity(new Intent(this, NormWithLoadMoreActivity.class));
                break;
            case R.id.indexview:
                startActivity(new Intent(this, NormWithIndexActivity.class));
                break;
        }

    }


}
