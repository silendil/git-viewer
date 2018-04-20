package com.example.pavelhryts.git_viewer.presenter;

import com.example.pavelhryts.git_viewer.view.InfoView;
import com.hannesdorfmann.mosby.mvp.MvpPresenter;

import java.util.List;

public interface InfoPresenter extends MvpPresenter<InfoView> {
    void loadInformation();
    List<String> getListData();
}
