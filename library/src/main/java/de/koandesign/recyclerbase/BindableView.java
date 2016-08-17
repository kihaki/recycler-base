package de.koandesign.recyclerbase;

import android.support.annotation.Nullable;

public interface BindableView<T> {
    Class<T> getType();
    void bind(@Nullable T item);
}