import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.*;

class BinaryTreeTest {

    @Test
    void removeLeaf() {
        BinaryTree<Integer> biTree = new BinaryTree<>();
        biTree.add(200);
        biTree.add(100);
        biTree.add(300);
        biTree.add(50);
        biTree.add(25);
        biTree.add(1);
        biTree.remove(1);
        assertTrue(biTree.checkInvariant());
        assertTrue(biTree.contains(200));
        assertTrue(biTree.contains(100));
        assertTrue(biTree.contains(300));
        assertTrue(biTree.contains(50));
        assertTrue(biTree.contains(25));
        assertFalse(biTree.contains(1));
    }

    @Test
    void removeNode() {
        BinaryTree<Integer> biTree = new BinaryTree<>();
        biTree.add(200);
        biTree.add(100);
        biTree.add(300);
        biTree.add(50);
        biTree.add(25);
        biTree.add(60);
        biTree.add(70);
        biTree.remove(60);
        assertTrue(biTree.checkInvariant());
        assertTrue(biTree.contains(200));
        assertTrue(biTree.contains(100));
        assertTrue(biTree.contains(300));
        assertTrue(biTree.contains(50));
        assertTrue(biTree.contains(25));
        assertTrue(biTree.contains(70));
        assertFalse(biTree.contains(60));
    }

    @Test
    void removeNode2() {
        BinaryTree<Integer> biTree = new BinaryTree<>();
        biTree.add(200);
        biTree.add(100);
        biTree.add(300);
        biTree.add(50);
        biTree.add(25);
        biTree.add(60);
        biTree.add(70);
        biTree.add(55);
        biTree.add(52);
        biTree.add(57);
        biTree.remove(60);
        assertTrue(biTree.checkInvariant());
        assertTrue(biTree.contains(200));
        assertTrue(biTree.contains(100));
        assertTrue(biTree.contains(300));
        assertTrue(biTree.contains(50));
        assertTrue(biTree.contains(25));
        assertTrue(biTree.contains(70));
        assertTrue(biTree.contains(55));
        assertTrue(biTree.contains(52));
        assertTrue(biTree.contains(57));
        assertFalse(biTree.contains(60));
    }

    @Test
    void removeNode3() {
        BinaryTree<Integer> biTree = new BinaryTree<>();
        biTree.add(200);
        biTree.add(100);
        biTree.add(300);
        biTree.add(50);
        biTree.add(25);
        biTree.add(60);
        biTree.add(70);
        biTree.add(55);
        biTree.add(52);
        biTree.add(57);
        biTree.add(65);
        biTree.add(64);
        biTree.add(66);
        biTree.add(63);
        biTree.remove(60);
        assertTrue(biTree.checkInvariant());
        assertTrue(biTree.contains(200));
        assertTrue(biTree.contains(100));
        assertTrue(biTree.contains(300));
        assertTrue(biTree.contains(50));
        assertTrue(biTree.contains(25));
        assertTrue(biTree.contains(70));
        assertTrue(biTree.contains(55));
        assertTrue(biTree.contains(52));
        assertTrue(biTree.contains(57));
        assertTrue(biTree.contains(65));
        assertTrue(biTree.contains(64));
        assertTrue(biTree.contains(66));
        assertTrue(biTree.contains(63));
        assertFalse(biTree.contains(60));
    }

    @Test
    void iterator() {
        BinaryTree<Integer> biTree = new BinaryTree<>();
        biTree.add(200);
        biTree.add(100);
        biTree.add(300);
        biTree.add(400);
        biTree.add(350);
        biTree.add(340);
        biTree.add(420);
        biTree.add(410);
        biTree.add(430);
        biTree.add(50);
        biTree.add(25);
        biTree.add(60);
        biTree.add(70);
        biTree.add(55);
        biTree.add(52);
        biTree.add(57);
        biTree.add(65);
        biTree.add(64);
        biTree.add(66);
        biTree.add(63);
        Iterator<Integer> iterator = biTree.iterator();
        assertTrue(25 == iterator.next());
        assertTrue(50 == iterator.next());
        assertTrue(52 == iterator.next());
        assertTrue(55 == iterator.next());
        assertTrue(57 == iterator.next());
        assertTrue(60 == iterator.next());
        assertTrue(63 == iterator.next());
        assertTrue(64 == iterator.next());
        assertTrue(65 == iterator.next());
        assertTrue(66 == iterator.next());
        assertTrue(70 == iterator.next());
        assertTrue(100 == iterator.next());
        assertTrue(200 == iterator.next());
        assertTrue(300 == iterator.next());
        assertTrue(340 == iterator.next());
        assertTrue(350 == iterator.next());
        assertTrue(400 == iterator.next());
        assertTrue(410 == iterator.next());
        assertTrue(420 == iterator.next());
        assertTrue(430 == iterator.next());
    }

    @Test
    void subTree() {
        BinaryTree<Integer> biTree = new BinaryTree<>();
        biTree.add(200);
        biTree.add(100);
        biTree.add(300);
        biTree.add(400);
        biTree.add(350);
        biTree.add(340);
        biTree.add(420);
        biTree.add(410);
        biTree.add(430);
        biTree.add(50);
        biTree.add(25);
        biTree.add(60);
        biTree.add(70);
        biTree.add(55);
        biTree.add(52);
        biTree.add(57);
        biTree.add(65);
        biTree.add(64);
        biTree.add(66);
        biTree.add(63);

        SortedSet<Integer> subSet = biTree.subSet(69, 400);
        Iterator<Integer> subSetIterator = subSet.iterator();
    }

}