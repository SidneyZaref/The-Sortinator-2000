package edu.psu.ist311.sortinator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

public class LazyTests extends AbstractSortinatorTests {

    @Override
    <U> ISortinator<U> createInstance(Comparator<U> order) {
        return new LazyImpl<>(order);
    }

    // any specific tests would go here using @Test

}
