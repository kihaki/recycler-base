package de.koandesign.recyclerbase;

import android.support.v7.util.SortedList;
import android.view.View;
import android.view.ViewGroup;

import java.util.Collection;

public abstract class SortedRecyclerViewAdapter<T extends RecyclerComparable<T>> extends RecyclerViewAdapterBase<T> {

    boolean isEmpty = true;
    OnEmptyStateChangeListener listener;
    private SortedList<T> items;

    /**
     * Must call this constructor for the Recycler to handle items sorting
     */
    public SortedRecyclerViewAdapter(Class<T> type) {
        super(type);
        items = new SortedList<>(type, new SortedListCallbackBase());
    }

    public void setOnEmptyStateChangeListener(OnEmptyStateChangeListener listener) {
        this.listener = listener;
    }

    protected void notifyEmptyStateListener(boolean empty) {
        if(isEmpty != empty) {
            isEmpty = empty;
            if (listener == null) {
                return;
            }
            listener.onEmptyStateChanged(empty);
        }
    }

    protected abstract View onCreateItemView(ViewGroup parent, int viewType);

    @Override
    public int getItemCount() {
        return items.size();
    }

    private boolean notifyIfEmpty() {
        if(getItemCount() == 0) {
            notifyEmptyStateListener(true);
            return true;
        } else {
            notifyEmptyStateListener(false);
            return false;
        }
    }

    public T getItem(int position){
        return this.items.get(position);
    }

    public void add(T item) {
        this.items.add(item);
    }

    @SafeVarargs
    public final void add(T... items) {
        this.items.addAll(items);
    }

    public void addAll(Collection<T> items) {
        this.items.addAll(items);
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

    public void clear(){
        this.items.clear();
    }

    public boolean isItemsEmpty() {
        return items.size() == 0;
    }

    public class SortedListCallbackBase extends SortedList.Callback<T> {

        @Override
        public int compare(T a, T b) {
            return a.compareTo(b);
        }

        @Override
        public void onInserted(int position, int count) {
            notifyItemRangeInserted(position, count);
            notifyIfEmpty();
        }

        @Override
        public void onRemoved(int position, int count) {
            notifyItemRangeRemoved(position, count);
            notifyIfEmpty();
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

    public interface OnEmptyStateChangeListener {
        void onEmptyStateChanged(boolean empty);
    }
}