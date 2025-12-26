package dsa.datastructures.hashmap;

import java.security.InvalidKeyException;
import java.util.NoSuchElementException;

public interface HashMapADT<K, V> {
    int size();
    boolean isEmpty();
    void clear();

    boolean containsKey(K key) throws InvalidKeyException;

    V get(K key) throws NoSuchElementException;

    /**
     * Adds or replaces a key-value mapping.
     * @return the previous value mapped to key, or null if none existed
     */
    V put(K key, V value);

    /**
     * Removes a key-value mapping.
     * @return the value that was removed
     * @throws NoSuchElementException if the key is not present
     */
    V remove(K key) throws NoSuchElementException;
}
