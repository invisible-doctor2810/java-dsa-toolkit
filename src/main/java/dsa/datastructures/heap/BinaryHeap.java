package dsa.datastructures.heap;

import java.util.ArrayList;
import java.util.Collection;
import java.util.NoSuchElementException;

public class BinaryHeap<T extends Comparable<T>> implements PriorityQueue_ADT<T> {
    ArrayList<T> data;

    public BinaryHeap() {
        data = new ArrayList<>();
    }

    public BinaryHeap(Collection<T> items) {
        for (T item :items) {
            data.add(item);
        }
        heapify();
    }

    @Override
    public int size() {
        return data.size();
    }

    @Override
    public boolean isEmpty() {
        return data.isEmpty();
    }

    @Override
    public void clear() {
        data.clear();
    }

    @Override
    public T peek() {
        if (isEmpty()) {throw new NoSuchElementException("Heap is empty.");}
        return data.get(0);
    }

    @Override
    public void offer (T value) {
        if (value == null) {
            throw new IllegalArgumentException("Null values not allowed in heap.");
        }
        data.add(value);
        siftUp(data.size() - 1);
    }

    @Override
    public T poll() {
        if (isEmpty()) {
            throw new NoSuchElementException("Heap is empty.");
        }
        T minValue = data.get(0);
        T lastValue = data.remove(data.size() - 1);
        if (!isEmpty()) {
            data.set(0, lastValue);
            siftDown(0);
        }
        return minValue;
    }

    private void swap(int i , int j){
        T temp = data.get(i);
        data.set(i, data.get(j));
        data.set(j, temp);
    }

    private void siftUp(int index) {
        if (index == 0) return; // at root

        int parentIndex = (index - 1) / 2;
        T item = data.get(index);
        T parent = data.get(parentIndex);

        if (item.compareTo(parent) < 0) {
            swap(index, parentIndex);
            siftUp(parentIndex);
        }
    }

    private void siftDown(int index) {
        int leftChildIndex = 2 * index + 1;
        int rightChildIndex = 2 * index + 2;
        int smallestIndex = index;

        if (leftChildIndex < data.size() && data.get(leftChildIndex).compareTo(data.get(smallestIndex)) < 0) {
            smallestIndex = leftChildIndex;
        }

        if (rightChildIndex < data.size() && data.get(rightChildIndex).compareTo(data.get(smallestIndex)) < 0) {
            smallestIndex = rightChildIndex;
        }

        if (smallestIndex != index) {
            swap(index, smallestIndex);
            siftDown(smallestIndex);
        }
    }

    private void heapify() {
        for (int i = (data.size() / 2) - 1; i >= 0; i--) {
            siftDown(i);
        }
    }
}
