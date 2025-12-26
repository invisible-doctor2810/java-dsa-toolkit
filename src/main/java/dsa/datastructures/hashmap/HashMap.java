package dsa.datastructures.hashmap;

import java.security.InvalidKeyException;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class HashMap<K,V> implements hashMapADT<K,V> {

    ArrayList<Node<K,V>> table;
    int size;
    double loadFactor;
    int capacity;
    final double DEFAULT_LOAD_FACTOR = 0.75;
    final int DEFAULT_CAPACITY = 16;

    public HashMap(){
        this.table = new ArrayList<>(DEFAULT_CAPACITY);
        this.capacity = DEFAULT_CAPACITY;
        this.size = 0;
        this.loadFactor = DEFAULT_LOAD_FACTOR;
    }

    public HashMap(int initCapacity) throws Exception{
        if (initCapacity <= 0) {
            throw new Exception("Unsupported Array Size of " + initCapacity + "!");
        }
        this.table = new ArrayList<>(initCapacity);
        this.capacity = initCapacity;
        this.size = 0;
        this.loadFactor = DEFAULT_LOAD_FACTOR;
    }

    public HashMap(int initCapacity , double initLoadFactor) throws Exception{
        if (initCapacity <= 0) {
            throw new Exception("Unsupported Array Size of " + initCapacity + "!"+
                "\nMust be a positive integer.");
        }
        if (initLoadFactor <= 0) {
            throw new Exception("Unsupported Load Factor number of " + initLoadFactor + "!"+
                "\nMust be a doouble betwween 0 and 1");
        }
        this.table = new ArrayList<>(initCapacity);
        this.capacity = initCapacity;
        this.size = 0;
        this.loadFactor = initLoadFactor;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public void clear() {
        table.clear();
    }

    @Override
    public boolean containsKey(K key) throws InvalidKeyException {
        if (key == null) {
            throw new InvalidKeyException("Invalid key to find.")
        }
        int currentIndex = 0;
        Node<K,V> current = table.get(0);
        while (current != null) {
            if (current.key.equals(key)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public V get(K key) throws NoSuchElementException {
        if (key == null) {
            throw new InvalidKeyException("Invalid key to find.")
        }
        int currentIndex = 0;
        Node<K,V> current = table.get(0);
        while (current != null) {
            if (current.key.equals(key)) {
                return current.value;
            }
        }
        return null;
    }

    @Override
    public V put(K key, V value) {
        if (key == null) {
            throw new InvalidKeyException("Invalid key to find.")
        }
        if ((size + 1) > this.capacity * this.loadFactor) {
            resize(table);
        }

        int index = indexFor(key, capacity);


        Node<K,V> find = findNode(key);
        if (find != null){
            V oldVal = find.value;
            find.value = value;
            return oldVal;
        }
        else {
            find.next = table.get(index);
            table.set(index , find);
            size ++;
            return null;
        }
    }

    @Override
    public V remove(K key) throws NoSuchElementException {
        if (key == null) {
            throw new NoSuchElementException("Null values are not accepted as arguments.");
        }

        int index = indexFor(key, capacity);
        Node<K,V> prev = table.get(index);
        Node<K,V> current = prev.next;

        if (prev.key.equals(key)) {
            table.set(index , current);
            return prev.value;
        }
        while(current != null) {
            if (current.key.equals(key)) {
                prev.next = current.next;
                return current.value;
            }
        }
        throw new NoSuchElementException("The key " + key + " does not exist in the HashMap.");

    }

    private int indexFor(K key , int capacity){
        int hash = key.hashCode();
        int index = 0;
        if (powOf2(capacity)){
            index = hash & (capacity - 1);
        }
        return index;
    }

    private Node<K,V> findNode(K key) {
        int index = indexFor(key , this.capacity);
        Node<K,V> currentNode = table.get(index);
        while (currentNode != null) {
            if (currentNode.key.equals(key)) {
                return currentNode;
            }
        }
        return null;
    }

    private void resize (ArrayList<Node<K,V>> tableArrayList) {
        ArrayList<Node<K,V>> newTable = new ArrayList<>(tableArrayList.size() * 2);
        for (int i = 0; i < newTable.size(); i++){
            newTable.set(i , null);
        }
        for (int i = 0; i < tableArrayList.size(); i++) {
            Node<K,V> head = table.get(i);
            while (head != null) {
                Node<K,V> next = head.next;
                int newIndex = Math.floorMod(head.key.hashCode() , newTable.size());
                head.next = newTable.get(newIndex);
                newTable.set(newIndex , head);
                head = next;
            }
        }
        tableArrayList = newTable;
    }

    private boolean powOf2 (int num) {
        if (num == 1){
            return true;
        }
        String bitNum = Integer.toBinaryString(num);
        String bitNumLess = Integer.toBinaryString(num - 1);
        int length = bitNum.length() - bitNumLess.length();
        return length == 1;
    }
    
}
