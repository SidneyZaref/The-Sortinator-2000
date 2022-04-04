package edu.psu.ist311.sortinator;

import java.util.Comparator;

public class HeapTests extends AbstractSortinatorTests{
    @Override
    <U> ISortinator<U> createInstance(Comparator<U> order) {
        return new HeapImpl<>(order);
    }
}
