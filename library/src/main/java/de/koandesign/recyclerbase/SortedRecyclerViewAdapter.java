package de.koandesign.recyclerbase;

import android.support.v7.util.SortedList;
import android.view.View;
import android.view.ViewGroup;

public abstract class SortedRecyclerViewAdapter<T extends RecyclerComparable<T>, V extends View & BindableView<T>> extends RecyclerViewAdapterBase<T, V> {

    private SortedList<T> items;

    /**
     * Must call this constructor for the Recycler to handle items sorting
     */
    public SortedRecyclerViewAdapter(Class<T> klass) {
        items = new SortedList<>(klass, new SortedListCallbackBase());
    }

    protected abstract V onCreateItemView(ViewGroup parent, int viewType);

    @Override
    public int getItemCount() {
        return items.size();
    }

    protected T getItem(int position){
        return this.items.get(position);
    }

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

    public class SortedListCallbackBase extends SortedList.Callback<T> {

        @Override
        public int compare(T a, T b) {
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
        public boolean areContentsTheSame(T a, T b) {
            return a.hasSameContentAs(b);
        }

        @Override
        public boolean areItemsTheSame(T a, T b) {
            return a.isSameItemAs(b);
        }
    }
}