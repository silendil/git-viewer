package com.example.pavelhryts.git_viewer.view;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.pavelhryts.git_viewer.R;
import com.example.pavelhryts.git_viewer.model.InfoModelImpl;
import com.example.pavelhryts.git_viewer.presenter.InfoPresenter;
import com.example.pavelhryts.git_viewer.presenter.InfoPresenterImpl;
import com.hannesdorfmann.mosby.mvp.viewstate.lce.LceViewState;
import com.hannesdorfmann.mosby.mvp.viewstate.lce.MvpLceViewStateActivity;
import com.hannesdorfmann.mosby.mvp.viewstate.lce.data.RetainingLceViewState;

public class InfoActivity extends MvpLceViewStateActivity<TextView, String, InfoView, InfoPresenter>
        implements InfoView{

    private static final String UNKNOWN_ERROR_MESSAGE = "Unknown error";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        setContentView(R.layout.screen_info);
    }

    @NonNull
    @Override
    public InfoPresenter createPresenter() {
        return new InfoPresenterImpl(new InfoModelImpl());
    }

    @Override
    public LceViewState<String, InfoView> createViewState() {
        return new RetainingLceViewState<>();
    }

    @Override
    public String getData() {
        return contentView.getText().toString();
    }

    @Override
    protected String getErrorMessage(Throwable e, boolean pullToRefresh) {
        String errorMessage = e.getMessage();
        return errorMessage == null ? UNKNOWN_ERROR_MESSAGE : errorMessage;
    }

    @Override
    public void setData(String data) {
        contentView.setText(data);
    }

    @Override
    public void loadData(boolean pullToRefresh) {
        getPresenter().loadInformation();
    }
}
