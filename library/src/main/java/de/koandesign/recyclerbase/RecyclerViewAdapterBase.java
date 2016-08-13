package de.koandesign.recyclerbase;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

public abstract class RecyclerViewAdapterBase<T, V extends View & BindableView<T>> extends RecyclerView.Adapter<ViewWrapper<T, V>> {

    @Override
    public final ViewWrapper<T, V> onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewWrapper<>(onCreateItemView(parent, viewType));
    }

    @Override
    public void onBindViewHolder(ViewWrapper<T, V> viewHolder, int position) {
        BindableView<T> view = viewHolder.getView();
        T item = getItem(position);
        view.bind(item);
    }

    @Override
    public abstract int getItemCount();

    protected abstract V onCreateItemView(ViewGroup parent, int viewType);

    protected abstract T getItem(int position);
}