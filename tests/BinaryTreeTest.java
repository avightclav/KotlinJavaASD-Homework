import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Iterator;

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
    void remove100() {
        BinaryTree<Integer> biTree = new BinaryTree<>();
        int[] toAdd = new int[]{44, 66, 78, 71, 38, 87, 45, 46, 62, 13, 79, 95, 70, 69, 2, 87, 20, 43, 56, 66, 94, 6, 7};
        for (int i : toAdd) {
            biTree.add(i);
        }
        biTree.remove(45);

        Iterator<Integer> it = biTree.iterator();

        int[] expected = new int[]{2, 6, 7, 13, 20, 38, 43, 44, 46, 56, 62, 66, 69, 70, 71, 78, 79, 87, 94, 95};
        assertEquals(Arrays.toString(expected), biTree.toString());
    }
}