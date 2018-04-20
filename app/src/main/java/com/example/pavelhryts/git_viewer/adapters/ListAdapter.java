package com.example.pavelhryts.git_viewer.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.pavelhryts.git_viewer.presenter.ListData;

import java.util.ArrayList;
import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListViewHolder> {

    private ListData data;
    private Context context;

    public ListAdapter(Context context, ListData data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ListViewHolder(LayoutInflater.from(context), parent, data);
    }

    @Override
    public void onBindViewHolder(ListViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return data.getListSize();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}
