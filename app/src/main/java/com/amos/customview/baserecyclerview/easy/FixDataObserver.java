package com.amos.customview.baserecyclerview.easy;

import android.support.v7.widget.RecyclerView;

/**
 * Project CustomView-master
 * Created by Amos
 * Created on 2017-10-09
 * Desc
 */

public class FixDataObserver extends RecyclerView.AdapterDataObserver {

    private RecyclerView recyclerView;

    public FixDataObserver(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
    }



    @Override
    public void onItemRangeInserted(int positionStart, int itemCount) {
        if (recyclerView.getAdapter() instanceof BaseRvAdapter) {
            BaseRvAdapter adapter = (BaseRvAdapter) recyclerView.getAdapter();
            if (adapter.getFooterCount() > 0 && adapter.getCount() == itemCount) {
                recyclerView.scrollToPosition(0);
            }
        }
    }

}
