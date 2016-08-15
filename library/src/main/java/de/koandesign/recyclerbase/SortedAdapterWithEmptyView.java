package de.koandesign.recyclerbase;

import android.view.View;
import android.view.ViewGroup;

public abstract class SortedAdapterWithEmptyView<T extends RecyclerComparable<T>, V extends View & BindableView<T>> extends SortedRecyclerViewAdapter<T, V> {

    public static final class ViewType {
        public static final int EMPTY = 101;
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
    public int getItemViewType(int position) {
        if(isItemsEmpty()) {
            return ViewType.EMPTY;
        }
        return super.getItemViewType(position);
    }

    @Override
    protected V onCreateItemView(ViewGroup parent, int viewType) {
        switch(viewType) {
            default:
                return onCreateRegularView(parent, viewType);
            case ViewType.EMPTY:
                return onCreateEmptyView(parent, viewType);
        }
    }

    protected abstract V onCreateEmptyView(ViewGroup parent, int viewType);
    protected abstract V onCreateRegularView(ViewGroup parent, int viewType);
}
