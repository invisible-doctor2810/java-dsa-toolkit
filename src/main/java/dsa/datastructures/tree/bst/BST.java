package dsa.datastructures.tree.bst;

import java.util.Iterator;

public class BST<T extends Comparable<T>> {
    Node<T> root;
    int size;

    public BST() {
        this.root = null;
        this.size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public void clear() {
        this.root = null;
        this.size = 0;
    }

    public boolean contains(T value) {
        Node<T> current = this.root;
        while (current != null) {
            if (current.value.equals(value)) {
                return true;
            } else if (value.compareTo(current.value) < 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }
        return false;
    }

    public boolean insert(T value) {
        if (this.root == null) {
            this.root = new Node<>();
            this.root.value = value;
            this.size++;
            return true;
        }

        Node<T> current = this.root;
        while (true) { 
            if (value.compareTo(value) > 0) {
                if (current.right == null) {
                    current.right = new Node<>();
                    current.right.value = value;
                    this.size++;
                    return true;
                }
                current = current.right;
            } else if (value.compareTo(value) < 0) {
                if (current.left == null) {
                    current.left = new Node<>();
                    current.left.value = value;
                    this.size++;
                    return true;
                }
                current = current.left;
            } else {
                return false;
            }
        }
    }


    public T remove(T value) {
        Node<T> find = this.root;
        while (find != null) {
            if (find.value.equals(value)) {
                break;
            } else if (value.compareTo(find.value) < 0) {
                find = find.left;
            } else {
                find = find.right;
            }
        }

        if (find.left == null && find.right == null) {
            T val = find.value;
            find = null;
            this.size--;
            return val;
        }

        if (find.left != null && find.right == null) {
            T val = find.value;
            find = find.left;
            this.size--;
            return val;
        }

        if (find.left == null && find.right != null) {
            T val = find.value;
            find = find.right;
            this.size--;
            return val;
        }

        if (find.left != null && find.right != null) {
            Node<T> minRight = minNode(find.right);
            T val = find.value;
            find = minRight;
            minRight = null;
            this.size--;
            return val;
        }
        return null;
    }

    public Iterator<T> iterator() {
        return new BSTIterator<>(this.root);
    }


    private Node<T> minNode (Node node) {
        if (node == null) return null;
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }
}
