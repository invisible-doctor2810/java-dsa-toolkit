package dsa.datastructures.tree.bst;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MyBSTTests {

    @Test
    void insertContainsAndSize_noDuplicates() {
        BST<Integer> bst = new BST<>();

        assertTrue(bst.isEmpty());
        assertEquals(0, bst.size());

        assertTrue(bst.insert(5));
        assertTrue(bst.insert(3));
        assertTrue(bst.insert(7));
        assertTrue(bst.insert(2));
        assertTrue(bst.insert(4));
        assertTrue(bst.insert(6));
        assertTrue(bst.insert(8));

        assertEquals(7, bst.size());
        assertFalse(bst.isEmpty());

        assertTrue(bst.contains(5));
        assertTrue(bst.contains(2));
        assertTrue(bst.contains(8));
        assertFalse(bst.contains(999));

        // Duplicate insert should not change size (assuming you disallow duplicates)
        assertFalse(bst.insert(5));
        assertEquals(7, bst.size());
    }

    @Test
    void remove_leaf_oneChild_twoChildren() {
        BST<Integer> bst = new BST<>();
        // Build a tree where we can remove different-case nodes
        //            5
        //          /   \
        //         3     8
        //        / \   /
        //       2   4 7
        //            /
        //           6
        int[] vals = {5, 3, 8, 2, 4, 7, 6};
        for (int v : vals) bst.insert(v);

        assertEquals(7, bst.size());

        // Remove leaf (2)
        assertTrue(bst.remove(2));
        assertFalse(bst.contains(2));
        assertEquals(6, bst.size());

        // Remove node with one child (7 has only left child 6 after removing nothing else)
        assertTrue(bst.remove(7));
        assertFalse(bst.contains(7));
        assertTrue(bst.contains(6)); // child should remain in tree
        assertEquals(5, bst.size());

        // Remove node with two children (3 has children 4 and (formerly 2 removed))
        // After removal of 2, node 3 has one child (4) actually; so remove 5 for two-child case instead.
        // 5 has two children (3 and 8) throughout this sequence.
        assertTrue(bst.remove(5));
        assertFalse(bst.contains(5));
        assertEquals(4, bst.size());

        // Remaining values should still be searchable
        assertTrue(bst.contains(3));
        assertTrue(bst.contains(4));
        assertTrue(bst.contains(6));
        assertTrue(bst.contains(8));

        // Removing missing value should return false
        assertFalse(bst.remove(12345));
        assertEquals(4, bst.size());
    }

    @Test
    void iterator_inorderProducesSortedOrder() {
        BST<Integer> bst = new BST<>();
        int[] vals = {5, 3, 8, 2, 4, 7, 6, 1, 9};
        for (int v : vals) bst.insert(v);

        Iterator<Integer> it = bst.iterator();
        List<Integer> out = new ArrayList<>();
        while (it.hasNext()) out.add(it.next());

        // inorder should be sorted ascending for BST
        assertEquals(List.of(1,2,3,4,5,6,7,8,9), out);
    }
}
