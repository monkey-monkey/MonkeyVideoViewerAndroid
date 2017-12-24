package com.monkey_monkey.monkeyvideoviewerandroid.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.monkey_monkey.monkeyvideoviewerandroid.R;
import com.monkey_monkey.monkeyvideoviewerandroid.adapter.BrowseVideoRecyclerViewAdapter;

/**
 * Created by admin on 24/12/2017 AD.
 */

public class BrowseVideoFragment extends Fragment {
    private static final String TAG = "BrowseVideoFragment";
    private static BrowseVideoFragment instance;

    public static BrowseVideoFragment getInstance() {
        if (instance == null) {
            instance = new BrowseVideoFragment();
        }
        return instance;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_browse_video, container, false);
        initInstance(rootView, savedInstanceState);
        return rootView;
    }

    private void initInstance(View rootView, Bundle savedInstanceState) {
        RecyclerView recyclerView = rootView.findViewById(R.id.recycler_view);
        BrowseVideoRecyclerViewAdapter.getInstance().init(getActivity(), getActivity().getIntent().getStringExtra("studentCode"));
        BrowseVideoRecyclerViewAdapter.getInstance().load();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(BrowseVideoRecyclerViewAdapter.getInstance());
    }
}
