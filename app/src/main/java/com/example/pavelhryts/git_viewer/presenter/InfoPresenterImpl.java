package com.example.pavelhryts.git_viewer.presenter;

import com.example.pavelhryts.git_viewer.model.InfoModel;
import com.example.pavelhryts.git_viewer.view.InfoView;
import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

import java.util.ArrayList;
import java.util.List;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

public class InfoPresenterImpl extends MvpBasePresenter<InfoView> implements InfoPresenter, ListData, Observer<String> {
    private final InfoModel model;
    private Subscription subscription;
    private List<String> data = new ArrayList<>();

    public InfoPresenterImpl(InfoModel model) {
        this.model = model;
    }

    @Override
    public void detachView(boolean retainInstance) {
        if (!retainInstance && subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
            subscription = null;
        }
        super.detachView(retainInstance);
    }

    @Override
    public void loadInformation() {
        if (isViewAttached())
            getView().showLoading(false);
        data.clear();
        subscription = model.retrieveInfo().observeOn(AndroidSchedulers.mainThread())
                .subscribe(this);
    }

    @Override
    public List<String> getListData() {
        return data;
    }

    @Override
    public int getListSize() {
        return data.size();
    }

    @Override
    public void onCompleted() {
        if(isViewAttached()){
            InfoView view = getView();
            view.setData(data);
            view.loadingFinished();
        }
    }

    @Override
    public void onError(Throwable e) {
        data.add("");
    }

    @Override
    public void onNext(String s) {
        data.add(s);
    }

    @Override
    public String getItem(int position) {
        return position>= 0 && position < data.size() ? data.get(position) : "";
    }
}
