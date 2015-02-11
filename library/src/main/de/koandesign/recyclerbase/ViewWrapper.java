package de.koandesign.recyclerbase;

import android.support.v7.widget.RecyclerView;
import android.view.View;

public class ViewWrapper<T, V extends View & BindableView<T>> extends RecyclerView.ViewHolder {

    private V view;

    public ViewWrapper(V itemView) {
        super(itemView);
        view = itemView;
    }

    public V getView() {
        return view;
    }
}