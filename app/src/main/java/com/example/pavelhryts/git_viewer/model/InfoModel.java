package com.example.pavelhryts.git_viewer.model;

import rx.Observable;

public interface InfoModel {
    Observable<String> retrieveInfo();
}
