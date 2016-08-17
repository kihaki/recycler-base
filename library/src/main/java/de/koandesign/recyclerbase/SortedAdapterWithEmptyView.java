package de.koandesign.recyclerbase;

import android.view.View;
import android.view.ViewGroup;

public abstract class SortedAdapterWithEmptyView<T extends RecyclerComparable<T>> extends SortedRecyclerViewAdapter<T> {

    public static final class ViewType {
        public static final int EMPTY = Integer.MIN_VALUE + 1;
    }

    /**
     * Must call this constructor for the Recycler to handle items sorting
     **/
    public SortedAdapterWithEmptyView(Class<T> klass) {
        super(klass);
    }

    @Override
    public int getItemCount() {
        if (isItemsEmpty()) {
            return 1; // Empty view
        }
        return super.getItemCount();
    }

    @Override
    public T getItem(int position) {
        if (isItemsEmpty()) {
            return null;
        }
        return super.getItem(position);
    }

    @Override
    public int getItemViewType(int position) {
        if(isItemsEmpty()) {
            return ViewType.EMPTY;
        }
        return super.getItemViewType(position);
    }

    @Override
    protected View onCreateItemView(ViewGroup parent, int viewType) {
        switch(viewType) {
            default:
                return onCreateRegularView(parent, viewType);
            case ViewType.EMPTY:
                return onCreateEmptyView(parent);
        }
    }

    protected abstract View onCreateEmptyView(ViewGroup parent);
    protected abstract View onCreateRegularView(ViewGroup parent, int viewType);
}
