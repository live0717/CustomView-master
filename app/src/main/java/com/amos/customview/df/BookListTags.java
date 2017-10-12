package com.amos.customview.df;

import java.util.List;

/**
 * Created by Amos on 2017/8/23.
 */

public class BookListTags {
    public List<DataBean> data;

    public static class DataBean {
        public String name;
        public List<String> tags;
    }
}
