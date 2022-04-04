package edu.psu.ist311.sortinator;

import java.util.*;

public class EagerImpl<E> implements ISortinator<E> {

    private final List<E> contents = new LinkedList<>();
    private final Comparator<E> order;
    private boolean isAccepting;

    public EagerImpl(Comparator<E> order){
        this.order = order;
        this.isAccepting = true;
    }


    @Override
    public void add(E e) {
        int insertPosition = 0;

        for (int i = 0; i < contents.size(); i++) {

            E curr = contents.get(i);

            if(order.compare(e, curr) >= 0){
                insertPosition++;
            }

        }
        contents.add(insertPosition, e);
    }

    @Override
    public void switchState() {
        this.isAccepting = isAccepting;
        if(isAccepting != true){
            isAccepting = false;
        }

    }

    @Override
    public E removeSmallest() {
        if(contents == null){
            throw new IllegalStateException("the Sortinator's multiset if empty!");
        }else if(isAccepting == false){
            throw new IllegalStateException("Sortinator is not in extraction state!");
        }
        for (int i = 0; i < contents.size(); i++){
            E dataToReturn = contents.remove(i);
        }
        return contents.remove(0);
    }

    @Override
    public int elementCount() { // do i have to do anything else here ?
        return contents.size();
    }

    @Override
    public boolean acceptingElements() {
        isAccepting = true;
        return isAccepting;
    }

    @Override
    public Comparator<E> orderCmp() {
        return order; // giving back comparator needed;
    }

    @Override
    public void clear() {
//        if(contents != null){
//            isAccepting = false;
//            contents.size() = 0;
//        }
    }

    @Override
    public Iterator<E> iterator() {
        return contents.iterator();
        // do not need any more here
    }
}
