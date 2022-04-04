package edu.psu.ist311.sortinator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Comparator;

public abstract class AbstractSortinatorTests {

    abstract <U> ISortinator<U> createInstance(Comparator<U> order);

    /**
     * If jUnit tells you "No tests were found" it just means you haven't
     * provided an implementation of this abstract sortinator factory method:
     * {@link #createInstance(Comparator)}
     * <p>
     * So create a class that extends {@code AbstractSortinatorTests} and
     * implements this method. Then you should be able to execute the test below.
     * <p>
     * (see README.md for specifics for an example)
     */
    @Test
    public void testRoutine1() {
        ISortinator<Integer> s = createInstance(Integer::compareTo);
        s.add(33);
        s.add(9);
        s.add(43);
        s.add(0);
        s.add(-4);
        s.switchState();

        int[] expected = {-4, 0, 9, 33, 43};
        int currExpectedArrayIdx = 0;

        while (s.elementCount() > 0) {
            Assertions.assertEquals(expected[currExpectedArrayIdx],
                    (int) s.removeSmallest());
            currExpectedArrayIdx++;
        }
    }

    @Test
    public void testadd1() {
        ISortinator<Integer> s = createInstance(Integer::compareTo);
        //adding 5 entries
        s.add(33);
        s.add(9);
        s.add(43);
        s.add(0);
        s.add(-4);
        s.switchState(); //switch state to machine not accepting
        Assertions.assertEquals(5, s.elementCount()); //counting 5 elements in list
        Assertions.assertEquals(-4, s.removeSmallest()); //removing smallest
        Assertions.assertEquals(0, s.removeSmallest()); //removing new smallest
        Assertions.assertEquals(3, s.elementCount()); //new element count is 3 after the removals

        s.switchState(); //switching state to accepting
        s.add(100);           //add
        s.add(-100);          //add
        s.add(50);           //add
        s.switchState();     //switch state to machine not accepting
        Assertions.assertEquals(6, s.elementCount());    //counting 6 elements
        Assertions.assertEquals(-100, s.removeSmallest());    //removing new smallest
        Assertions.assertEquals(9, s.removeSmallest());      //removing new smallest
        Assertions.assertEquals(4, s.elementCount());        //new element count after 2 removals

        s.clear();         //clearing all elements

    }

    @Test
    public void testRemoveSmallest0() {
        ISortinator<Integer> s = createInstance(Integer::compareTo);
        s.add(33);   //add
        s.add(9);    //add
        s.add(43);    //add
        s.add(0);     //add
        s.add(-4);     //add
        s.switchState();  //switch state to machine not accepting
        Assertions.assertEquals(-4, s.removeSmallest());   //removing smallest
        Assertions.assertEquals(0, s.removeSmallest());   //removing new smallest
        Assertions.assertEquals(9, s.removeSmallest());    //removing new smallest
        Assertions.assertEquals(33, s.removeSmallest());    //removing new smallest
        Assertions.assertEquals(43, s.removeSmallest());    //removing new smallest
    }

    @Test
    public void testRemoveSmallest1() {
        ISortinator<Integer> s = createInstance(Integer::compareTo);
        s.add(-33);    //add
        s.add(10009);    //add
        s.add(430);    //add
        s.add(0);      //add
        s.add(-40000);     //add
        s.switchState();    //switch state to machine not accepting
        Assertions.assertEquals(-40000, s.removeSmallest());   //removing smallest
        Assertions.assertEquals(-33, s.removeSmallest());     //removing new smallest
        Assertions.assertEquals(3, s.elementCount());        //counting elements after removing
        Assertions.assertEquals(0, s.removeSmallest());      //removing new smallest
        Assertions.assertEquals(430, s.removeSmallest());      //removing new smallest
        Assertions.assertEquals(10009, s.removeSmallest());    //removing new smallest
    }

    @Test
    public void testElementCount() {
        ISortinator<Integer> s = createInstance(Integer::compareTo);
        s.add(33);   //add
        s.add(9);    //add
        s.add(43);   //add
        s.add(0);     //add
        s.add(-4);    //add
        s.switchState();    //switch state to machine not accepting
        Assertions.assertEquals(5, s.elementCount());  //counting elements
    }

    @Test
    public void testclear1(){
        ISortinator<Integer> s = createInstance(Integer::compareTo);
        s.add(33);  //add
        s.add(9);    //add
        s.add(43);   //add
        s.switchState();     //switch state to machine not acceptin
        s.clear();       //clear all and revert back to switch state at accepting


    }

    @Test
    public void testclear2(){
        ISortinator<Integer> s = createInstance(Integer::compareTo);
        s.add(33);    //add
        s.add(9);     //add
        s.add(43);    //add
        s.switchState();   //switch state to machine not accepting
        s.clear();  //clear all and switch state is now accepting
        //Assertions.assertEquals("[]", s.toString());
        s.add(0);  //add
        s.add(-4);   //add
        s.switchState();  //switch state to machine is not accepting
        //Assertions.assertEquals("[0, -4]", s.toString());    //this assertion only works works for one implementation
                                                            // and when i flip the numbers in the output it tells me
                                                                //the pother implementation does not work

    }

    @Test
    public void testFailure1() {
        ISortinator<Integer> s = createInstance(Integer::compareTo);
        s.switchState();
        Assertions.assertThrows(Exception.class, () -> s.removeSmallest());
    }
//FROM OFFICE HOURS:
    //Function<Integer, Integer> F = (Integer x) -> x + 5;

    //List<Integer> result = add5(List.of(2, 2, 2));
    //List<Boolean> result2 = convertToBools(List.of(2, -5, 2));
    //Assertions.assertEquals(List.of(false, true, false), result2);
//        Assertions.assertEquals(List.of(false, true, false), apply(List.of(2, -5, 2), (Integer i) -> i <= 0));
//        Assertions.assertEquals(List.of(7, 7, 7), apply(List.of(2, 2, 2), (Integer i) -> i + 5));
//        LazyTests.<Integer, Integer>apply(List.of(2, 2, 2), (Integer i) -> i + 5);
//        Function<Integer, Boolean> f = (Integer i) -> i < 5;

//    public static <T, R> List<R> apply(List<T> lst, Function<T, R> f){
//        List<R> result = new ArrayList<>();
//        for(T x : lst){
//            result.add(f.apply(x));
//            // f(x)
//        }
//        return result;
//    }

    @Test
    public void testFailure2() {
        ISortinator<Integer> s = createInstance(Integer::compareTo);
        s.add(33);
        s.add(-4);
        s.add(0);
        s.switchState();
        Assertions.assertThrows(Exception.class, () -> s.add(5));
    }


}
