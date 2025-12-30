package dsa.datastructures.tree.bst;

import java.util.Iterator;
import java.util.Stack;

public class BSTIterator<T extends Comparable<T>> implements Iterator<T> {
    Stack<Node<T>> stack;

    public BSTIterator(Node<T> root) {
        stack = new Stack<>();
        pushLeft(root);
        }

    public boolean hasNext() {
        return !stack.isEmpty();
    }

    @Override
    public T next() {
        Node<T> top = stack.pop();
        T result = top.value;
        if (top.right != null) {
            pushLeft(top.right);
        }
        return result;
    }

    private void pushLeft(Node<T> node) {
        while(node != null) {
            stack.push(node);
            node = node.left;
        }
    }
}
