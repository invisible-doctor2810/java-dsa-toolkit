package dsa.datastructures.heap;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class MyBinaryHeapTests {

    @Test
    void offerPeekPollProducesSortedOrder() {
        BinaryHeap<Integer> pq = new BinaryHeap<>();

        int[] nums = {7, 1, 9, 3, 2, 8, 4, 6, 5};
        for (int x : nums) pq.offer(x);

        assertEquals(9, pq.size());
        assertEquals(1, pq.peek());

        int prev = pq.poll();
        while (!pq.isEmpty()) {
            int cur = pq.poll();
            assertTrue(prev <= cur, "PriorityQueue returned out-of-order elements");
            prev = cur;
        }

        assertEquals(0, pq.size());
        assertTrue(pq.isEmpty());
    }

    @Test
    void handlesDuplicatesAndNegatives() {
        BinaryHeap<Integer> pq = new BinaryHeap<>();

        int[] nums = {5, 5, -1, 0, -1, 2, 2};
        for (int x : nums) pq.offer(x);

        int[] expected = {-1, -1, 0, 2, 2, 5, 5};
        for (int x : expected) {
            assertEquals(x, pq.peek());
            assertEquals(x, pq.poll());
        }

        assertTrue(pq.isEmpty());
        assertEquals(0, pq.size());
    }

    @Test
    void randomizedAgainstSortedList() {
        BinaryHeap<Integer> pq = new BinaryHeap<>();
        List<Integer> values = new ArrayList<>();
        Random r = new Random(42);

        for (int i = 0; i < 2000; i++) {
            int v = r.nextInt(10000) - 5000; // include negatives
            pq.offer(v);
            values.add(v);
        }

        Collections.sort(values);

        for (int i = 0; i < values.size(); i++) {
            assertEquals(values.get(i), pq.poll(), "Mismatch at poll index " + i);
        }

        assertTrue(pq.isEmpty());
    }
}
