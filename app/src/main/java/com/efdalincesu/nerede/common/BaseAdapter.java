package com.efdalincesu.nerede.common;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseAdapter<DB extends ViewDataBinding, T> extends RecyclerView.Adapter<BaseAdapter.ViewHolder> {

    public DB binding;
    public List<T> list;
    int layoutRes;

    public abstract void onBind(int pos);

    public BaseAdapter(int layoutRes) {
        this.layoutRes = layoutRes;
    }

    public void submitList(List<T> listT) {
        this.list = listT;
    }

    @NonNull
    @Override
    public BaseAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        binding = DataBindingUtil.inflate(inflater, layoutRes, viewGroup, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseAdapter.ViewHolder viewHolder, int i) {
        onBind(i);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull DB itemView) {
            super(itemView.getRoot());
        }
    }

}
