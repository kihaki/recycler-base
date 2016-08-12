package de.koandesign.recyclerbase;

import android.support.v7.util.SortedList;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

public abstract class RecyclerViewAdapterBase<T extends RecyclerComparable<T>, V extends View & BindableView<T>> extends RecyclerView.Adapter<ViewWrapper<T, V>> {

    // Casting should be safe, since T must extend RecyclerComparable
    private SortedList<T> items = (SortedList<T>) new SortedList<>(RecyclerComparable.class, new SortedListCallbackBase());

    @Override
    public abstract int getItemCount();

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

    protected abstract V onCreateItemView(ViewGroup parent, int viewType);

    protected abstract T getItem(int position);

    @SafeVarargs
    public final void add(T... items) {
        this.items.addAll(items);
    }

    public void add(T item) {
        this.items.add(item);
    }

    public void remove(T item) {
        this.items.remove(item);
    }

    public void removeItemAt(int pos) {
        this.items.removeItemAt(pos);
    }

    public SortedList<T> getItems() {
        return items;
    }

    public class SortedListCallbackBase extends SortedList.Callback<RecyclerComparable> {

        @Override
        public int compare(RecyclerComparable a, RecyclerComparable b) {
            return a.compareTo(b);
        }

        @Override
        public void onInserted(int position, int count) {
            notifyItemRangeInserted(position, count);
        }

        @Override
        public void onRemoved(int position, int count) {
            notifyItemRangeRemoved(position, count);
        }

        @Override
        public void onMoved(int fromPosition, int toPosition) {
            notifyItemMoved(fromPosition, toPosition);
        }

        @Override
        public void onChanged(int position, int count) {
            notifyItemRangeChanged(position, count);
        }

        @Override
        public boolean areContentsTheSame(RecyclerComparable a, RecyclerComparable b) {
            return a.hasSameContentAs(b);
        }

        @Override
        public boolean areItemsTheSame(RecyclerComparable a, RecyclerComparable b) {
            return a.isSameItemAs(b);
        }
    }
}