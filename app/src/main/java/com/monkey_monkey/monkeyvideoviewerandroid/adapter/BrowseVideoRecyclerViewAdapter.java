package com.monkey_monkey.monkeyvideoviewerandroid.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.monkey_monkey.monkeyvideoviewerandroid.R;
import com.monkey_monkey.monkeyvideoviewerandroid.manager.ListStudentVideoManager;

/**
 * Created by admin on 24/12/2017 AD.
 */

public class BrowseVideoRecyclerViewAdapter extends RecyclerView.Adapter<BrowseVideoRecyclerViewAdapter.ViewHolder> implements ListStudentVideoManager.onLoad {
    private static final String TAG = "BrowseVideoAdapter";
    private static BrowseVideoRecyclerViewAdapter instance;
    private LayoutInflater mInflater;
    private String studentCode;
    private BrowseVideoRecyclerViewAdapter.onClickListener callback;

    public interface onClickListener {
        void onClick(String videoName);
    }

    private BrowseVideoRecyclerViewAdapter() {
    }

    public void init(Context context, String studentCode, BrowseVideoRecyclerViewAdapter.onClickListener callback) {
        mInflater = LayoutInflater.from(context);
        this.studentCode = studentCode;
        this.callback = callback;
    }

    public void load() {
        ListStudentVideoManager.getInstance().getVideoList(studentCode, this);
    }

    public static BrowseVideoRecyclerViewAdapter getInstance() {
        if (instance == null) {
            instance = new BrowseVideoRecyclerViewAdapter();
        }
        return instance;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.view_browse_video_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return ListStudentVideoManager.getInstance().getSize();
    }

    @Override
    public void onLoadComplete() {
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        int position;

        ViewHolder(View itemView) {
            super(itemView);
            initView(itemView);
        }

        private void initView(View itemView) {
            itemView.setOnClickListener(this);
        }

        void bind(int position) {
            this.position = position;
            TextView textViewVideoName = itemView.findViewById(R.id.text_view_video_name);
            textViewVideoName.setText(ListStudentVideoManager.getInstance().getVideoNameAtIndex(position));
        }

        @Override
        public void onClick(View view) {
            callback.onClick(ListStudentVideoManager.getInstance().getVideoNameAtIndex(position));
        }
    }
}
