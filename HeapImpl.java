package edu.psu.ist311.sortinator;

import java.util.*;

import static java.util.Collections.swap;

public class HeapImpl<E> implements ISortinator<E> {

    private final List<E> heap = new ArrayList<>();
    private final Comparator<E> order;
    private boolean isAccepting = true;

    public HeapImpl(Comparator<E> order) {
        this.order = order;

    }

    private void makeProperHeapUnderIndex(int i) {
        int left = left(i);
        int right = right(i);
        int idxOfSmallest = i;

        if(left < heap.size() && order.compare(heap.get(left), heap.get(i)) < 0){
            idxOfSmallest = left;
        }
        if(right < heap.size() && order.compare(heap.get(right), heap.get(idxOfSmallest)) < 0){
            idxOfSmallest = right;
        }
        if(idxOfSmallest != i){
            Collections.swap(heap, i, idxOfSmallest);
            makeProperHeapUnderIndex(idxOfSmallest);
        }
    }

    private int left(int i) {
        return (2 * i) + 1;
    }

    private int right(int i) {
        return (2 * i) + 2;
    }

    private int parent(int i) {
        return (i - 1) / 2;
    }

    @Override
    public void add(E e) {
        if(!isAccepting){
            throw new IllegalArgumentException("machine is not accepting entries!");
        }
        heap.add(heap.size() , e);
        int pos = heap.size() - 1;
        do{
            Collections.swap(heap, pos, parent(pos));
            pos = parent(pos);
        }while(pos > 0 && order.compare(heap.get(pos), heap.get(parent(pos))) < 0);

    }

    @Override
    public void switchState() {
        isAccepting = !isAccepting;
    }

    @Override
    public E removeSmallest() {
        if(heap.isEmpty()){
            throw new NoSuchElementException("the Sortinator's multiset if empty!");
        }
        if(isAccepting){
            throw new IllegalStateException("Sortinator is not in extraction state!");
        }

        E smallestSoFar = heap.get(0);             //O(1)
        int indexOfSmallest = 0;
        int i = 0;
        for(E x : heap){
            if(order.compare(x, smallestSoFar) < 0){
                smallestSoFar = x;
                indexOfSmallest = i;
            }
            i++;
        }
        return heap.remove(indexOfSmallest);
    }


    @Override
    public int elementCount() {
        return heap.size();
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
        heap.clear();
        isAccepting = true;
    }

    @Override
    public Iterator<E> iterator() {
        return heap.iterator();
    }

    @Override
    public String toString() {
        return heap.toString();

    }
}
