package com.example.pavelhryts.git_viewer.view;

import com.hannesdorfmann.mosby.mvp.lce.MvpLceView;

import java.util.List;

public interface InfoView extends MvpLceView<List<String>> {
    void loadingFinished();
}
