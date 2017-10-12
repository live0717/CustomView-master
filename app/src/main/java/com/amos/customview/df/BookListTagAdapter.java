package com.amos.customview.df;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.amos.customview.R;

import java.util.List;

/**
 * Created by Amos on 2017/8/23.
 */

public class BookListTagAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<BookListTags.DataBean> mDataBeans;
    private LayoutInflater mInflater;

    public BookListTagAdapter(Context context, List<BookListTags.DataBean> dataBeans) {
        mContext = context;
        mDataBeans = dataBeans;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_book_list_tags, parent, false);
        return new TagsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        TagsViewHolder viewHolder = (TagsViewHolder) holder;
        RecyclerView rvListTag = viewHolder.rvListTag;
        rvListTag.setLayoutManager(new GridLayoutManager(mContext, 4));
        rvListTag.setAdapter(new TagAdapter(mDataBeans.get(position).tags));
        viewHolder.tvListTagsName.setText(mDataBeans.get(position).name);
    }

    @Override
    public int getItemCount() {
        return mDataBeans.size();
    }

    public void addAll(List<BookListTags.DataBean> data) {
        mDataBeans.addAll(data);
        notifyDataSetChanged();
    }

    class TagsViewHolder extends RecyclerView.ViewHolder {
        TextView tvListTagsName;
        RecyclerView rvListTag;

        public TagsViewHolder(View itemView) {
            super(itemView);
            tvListTagsName = itemView.findViewById(R.id.tvListTagsName);
            rvListTag = itemView.findViewById(R.id.rvListTag);
        }
    }

    class TagAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        private List<String> mTags;

        public TagAdapter(List<String> tags) {
            mTags = tags;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = mInflater.inflate(R.layout.item_list_tag, parent, false);
            return new TagViewHolder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            TagViewHolder viewHolder = (TagViewHolder) holder;
            viewHolder.tvTagName.setText(mTags.get(position));
        }

        @Override
        public int getItemCount() {
            return mTags.size();
        }
    }

    class TagViewHolder extends RecyclerView.ViewHolder {
        TextView tvTagName;

        public TagViewHolder(View itemView) {
            super(itemView);
            tvTagName = itemView.findViewById(R.id.tvTagName);
        }
    }
}
