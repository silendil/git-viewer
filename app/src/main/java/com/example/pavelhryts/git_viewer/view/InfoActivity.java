package com.example.pavelhryts.git_viewer.view;

import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.pavelhryts.git_viewer.R;
import com.example.pavelhryts.git_viewer.adapters.ListAdapter;
import com.example.pavelhryts.git_viewer.model.InfoModelImpl;
import com.example.pavelhryts.git_viewer.presenter.InfoPresenter;
import com.example.pavelhryts.git_viewer.presenter.InfoPresenterImpl;
import com.example.pavelhryts.git_viewer.presenter.ListData;
import com.hannesdorfmann.mosby.mvp.viewstate.lce.LceViewState;
import com.hannesdorfmann.mosby.mvp.viewstate.lce.MvpLceViewStateActivity;
import com.hannesdorfmann.mosby.mvp.viewstate.lce.data.RetainingLceViewState;

import java.util.List;

import static android.widget.LinearLayout.VERTICAL;

public class InfoActivity extends MvpLceViewStateActivity<RecyclerView, List<String>, InfoView, InfoPresenter>
        implements InfoView, SwipeRefreshLayout.OnRefreshListener{

    private static final String UNKNOWN_ERROR_MESSAGE = "Unknown error";
    private SwipeRefreshLayout swipe;
    private RecyclerView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        setContentView(R.layout.screen_info);
        swipe = findViewById(R.id.swipe);
        list = findViewById(R.id.contentView);
        list.setAdapter(new ListAdapter(this,(ListData) getPresenter()));
        list.setLayoutManager(new LinearLayoutManager(this, VERTICAL, false));
        swipe.setOnRefreshListener(this);
    }

    @NonNull
    @Override
    public InfoPresenter createPresenter() {
        return new InfoPresenterImpl(new InfoModelImpl());
    }

    @Override
    public LceViewState<List<String>, InfoView> createViewState() {
        return new RetainingLceViewState<>();
    }

    @Override
    public List<String> getData() {
        return getPresenter().getListData();
    }

    @Override
    protected String getErrorMessage(Throwable e, boolean pullToRefresh) {
        String errorMessage = e.getMessage();
        return errorMessage == null ? UNKNOWN_ERROR_MESSAGE : errorMessage;
    }

    @Override
    public void setData(List<String> data) {
        list.getAdapter().notifyDataSetChanged();
    }

    @Override
    public void loadData(boolean pullToRefresh) {
        getPresenter().loadInformation();
    }

    @Override
    public void loadingFinished() {
        swipe.setRefreshing(false);
        list.getAdapter().notifyDataSetChanged();
        list.setVisibility(View.VISIBLE);
        showContent();
    }

    @Override
    public void onRefresh() {
        list.setVisibility(View.GONE);
        getPresenter().loadInformation();
    }
}
