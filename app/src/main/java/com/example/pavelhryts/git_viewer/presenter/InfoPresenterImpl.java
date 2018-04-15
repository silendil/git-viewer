package com.example.pavelhryts.git_viewer.presenter;

import com.example.pavelhryts.git_viewer.model.InfoModel;
import com.example.pavelhryts.git_viewer.view.InfoView;
import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

public class InfoPresenterImpl extends MvpBasePresenter<InfoView> implements InfoPresenter {
    private final InfoModel model;
    private Subscription subscription;

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
        subscription = model.retrieveInfo().observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        InfoView view = getView();
                        if (isViewAttached()) {
                            view.setData(s);
                            view.showContent();
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        if (isViewAttached())
                            getView().showError(throwable, false);
                    }
                });
    }
}
