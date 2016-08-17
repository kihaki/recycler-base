package de.koandesign.recyclerbase;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

public abstract class RecyclerViewAdapterBase<T> extends RecyclerView.Adapter<ViewWrapper<T, View>> {

    protected final Class<T> type;

    public RecyclerViewAdapterBase(Class<T> type) {
        this.type = type;
    }

    @Override
    public final ViewWrapper<T, View> onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewWrapper<>(onCreateItemView(parent, viewType));
    }

    @Override
    public void onBindViewHolder(ViewWrapper<T, View> viewHolder, int position) {
        View view = viewHolder.getView();
        T item = getItem(position);
        if(view instanceof BindableView) {
            BindableView bindableView = (BindableView) view;
            if(bindableView.getType().equals(type)) {
                ((BindableView)view).bind(item);
            }
        }
    }

    @Override
    public abstract int getItemCount();

    public abstract T getItem(int position);

    protected abstract View onCreateItemView(ViewGroup parent, int viewType);
}