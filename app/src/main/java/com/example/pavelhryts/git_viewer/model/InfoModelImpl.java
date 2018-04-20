package com.example.pavelhryts.git_viewer.model;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.functions.Func1;

public class InfoModelImpl implements InfoModel {

    private static final String FUBAR = "FUBAR";
    private static final String SUSFU = "SUSFU";
    private static final String BOHICA = "BOHICA";

    @Override
    public Observable<String> retrieveInfo() {
        return Observable.timer(1L, TimeUnit.SECONDS).flatMap(new Func1<Long, Observable<String>>() {
            @Override
            public Observable<String> call(Long aLong) {
                return Observable.from(generateString(20));
            }
        });
    }

    private String[] generateString(int length){
        String[] array = new String[length];
        for(int i = 0; i < length; i++){
            array[i] = String.format(Locale.US, "line %d",i+1);
        }
        return array;
    }
}
