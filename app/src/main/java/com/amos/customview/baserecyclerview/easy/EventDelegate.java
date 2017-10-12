package com.amos.customview.baserecyclerview.easy;

import android.view.View;

/**
 * Project CustomView-master
 * Created by Amos
 * Created on 2017-10-09
 * Desc
 */

public interface EventDelegate {
    void addData(int length);
    void clear();

    void stopLoadMore();
    void pauseLoadMore();
    void resumeLoadMore();

    void setMore(View view, BaseRvAdapter.OnMoreListener listener);
    void setNoMore(View view, BaseRvAdapter.OnNoMoreListener listener);
    void setErrorMore(View view, BaseRvAdapter.OnErrorListener listener);
    void setMore(int res, BaseRvAdapter.OnMoreListener listener);
    void setNoMore(int res, BaseRvAdapter.OnNoMoreListener listener);
    void setErrorMore(int res, BaseRvAdapter.OnErrorListener listener);
}