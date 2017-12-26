package com.monkey_monkey.monkeyvideoviewerandroid.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.monkey_monkey.monkeyvideoviewerandroid.R;

/**
 * Created by admin on 26/12/2017 AD.
 */

public class PdfViewerFragment extends Fragment {
    private static final String TAG = "PdfViewerFragment";
    private static PdfViewerFragment instance;

    public static PdfViewerFragment getInstance() {
        if (instance == null) {
            instance = new PdfViewerFragment();
        }
        return instance;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_pdf_viewer, container,false);
        initInstance(rootView,savedInstanceState);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    private void initInstance(View rootView, Bundle savedInstanceState) {
    }
}
