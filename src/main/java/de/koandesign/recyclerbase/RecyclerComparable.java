package de.koandesign.recyclerbase;

public interface RecyclerComparable {

    int compareTo(RecyclerComparable other);
    boolean hasSameContentAs(RecyclerComparable other);
    boolean isSameItemAs(RecyclerComparable other);
}
