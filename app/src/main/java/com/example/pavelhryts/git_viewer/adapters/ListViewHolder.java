package com.example.pavelhryts.git_viewer.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.pavelhryts.git_viewer.R;
import com.example.pavelhryts.git_viewer.presenter.ListData;

import java.util.List;


public class ListViewHolder extends RecyclerView.ViewHolder {
    private ListData data;
    private TextView text;

    ListViewHolder(LayoutInflater inflater, ViewGroup parent, ListData data) {
        super(inflater.inflate(R.layout.list_item, parent, false));
        this.data = data;
        text = itemView.findViewById(R.id.text);
    }

    public void bind(int position){
        text.setText(data.getItem(position));
    }
}
