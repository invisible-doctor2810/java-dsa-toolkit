package dsa.datastructures.heap;

public interface PriorityQueue_ADT<T extends Comparable<T>>{
    int size();
    boolean isEmpty();
    void clear();
    T peek();
    void offer( T element);
    T poll();
}
