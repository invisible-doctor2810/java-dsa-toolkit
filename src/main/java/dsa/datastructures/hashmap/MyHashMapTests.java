package dsa.datastructures.hashmap;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MyHashMapTests {

    @Test
    void putGetOverwriteAndSize() {
        HashMap<String, Integer> map = new HashMap<>();

        assertEquals(0, map.size());

        // insert
        assertNull(map.put("a", 10));
        assertEquals(1, map.size());
        assertTrue(map.containsKey("a"));
        assertEquals(10, map.get("a"));

        // overwrite same key
        assertEquals(10, map.put("a", 99));
        assertEquals(1, map.size());        
        assertEquals(99, map.get("a"));
    }

    @Test
    void collisionHandlingSameBucketDifferentKeys() {
        class BadHashKey {
            private final int id;
            BadHashKey(int id) { this.id = id; }
            @Override public int hashCode() { return 1; }
            @Override public boolean equals(Object o) {
                return (o instanceof BadHashKey) && ((BadHashKey) o).id == this.id;
            }
        }

        HashMap<BadHashKey, String> map = new HashMap<>();

        BadHashKey k1 = new BadHashKey(1);
        BadHashKey k2 = new BadHashKey(2);
        BadHashKey k3 = new BadHashKey(3);

        map.put(k1, "one");
        map.put(k2, "two");
        map.put(k3, "three");

        // If chaining traversal is correct, all should be retrievable.
        assertEquals("one", map.get(k1));
        assertEquals("two", map.get(k2));
        assertEquals("three", map.get(k3));

        // Remove a middle key (depending on insertion order, this stresses unlinking)
        assertEquals("two", map.remove(k2));
        assertFalse(map.containsKey(k2));

        // Others should remain intact
        assertEquals("one", map.get(k1));
        assertEquals("three", map.get(k3));
        assertEquals(2, map.size());
    }

    @Test
    void manyInsertsStillRetrievable() {
        HashMap<Integer, Integer> map = new HashMap<>();
        int n = 2000;

        for (int i = 0; i < n; i++) {
            map.put(i, i * i);
        }
        assertEquals(n, map.size());

        for (int i = 0; i < n; i += 137) {
            assertTrue(map.containsKey(i));
            assertEquals(i * i, map.get(i));
        }

        assertEquals(0, map.get(0));
        assertEquals((n - 1) * (n - 1), map.get(n - 1));
    }
}
