package de.koandesign.recyclerbase;

import android.support.annotation.Nullable;

public interface BindableView<T> {
    void bind(@Nullable T item);
}