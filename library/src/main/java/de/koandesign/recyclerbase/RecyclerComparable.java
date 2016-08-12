package de.koandesign.recyclerbase;

public interface RecyclerComparable<T> {

    int compareTo(T other);
    boolean hasSameContentAs(T other);
    boolean isSameItemAs(T other);
}
