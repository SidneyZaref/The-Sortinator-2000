package edu.psu.ist311.sortinator;

import java.util.*;

public class LazyImpl<E> implements ISortinator<E> {
    private final List<E> contents = new LinkedList<>();
    private final Comparator<E> order;
    private boolean isAccepting;

    public LazyImpl(Comparator<E> order) {
        this.order = order;
        this.isAccepting = true;
    }


    @Override
    public void add(E e) {
        if(!isAccepting){
            throw new IllegalArgumentException("machine is not accepting entries!");
        }
        contents.add(e);                              //O(1)

    }

    @Override
    public void switchState() {
        isAccepting = !isAccepting;
    }

    @Override
    public E removeSmallest() {
        if(contents.isEmpty()){
            throw new NoSuchElementException("the Sortinator's multiset if empty!");
        }
        if(isAccepting){
            throw new IllegalStateException("Sortinator is not in extraction state!");
        }
        E smallestSoFar = contents.get(0);             
        int indexOfSmallest = 0;
        int i = 0;
        for(E x : contents){
            if(order.compare(x, smallestSoFar) < 0){
                smallestSoFar = x;
                indexOfSmallest = i;
            }
            i++;
        }
        return contents.remove(indexOfSmallest);
    }


    @Override
    public int elementCount() {
        return contents.size();
    }


    @Override
    public boolean acceptingElements() {
        return isAccepting;
    }


    @Override
    public Comparator<E> orderCmp() {
        return order;
    }


    @Override
    public void clear() {
        contents.clear();
        isAccepting = true;
    }


    @Override
    public String toString() {
        return contents.toString();

    }


    @Override
    public Iterator<E> iterator() {
        return contents.iterator();

    }
}
