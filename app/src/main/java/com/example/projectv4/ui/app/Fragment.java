package com.example.projectv4.ui.app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class Fragment extends androidx.fragment.app.Fragment {
    int d ;
    private int progress = 0;
    ProgressBar progressBar;
    Button btn=null;
    Button btn1=null;

    public Fragment(int contentLayoutId) {
        super(contentLayoutId);
        this.d=contentLayoutId;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(d, container, false);

        return v;
    }
    private void updateProgressBar() {
        progressBar.setProgress(progress);
        System.out.println("****************************"+progressBar.getProgress());
    }
}