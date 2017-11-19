import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class BinaryTreeTest {
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
    void removeLeaf() {
        BinaryTree<Integer> biTree = new BinaryTree<>();
        int[] toAdd = new int[]{200, 100, 300, 50, 25, 1};
        for (int i: toAdd) {
            biTree.add(i);
        }
        biTree.remove(1);
        assertTrue(biTree.checkInvariant());

        int[] expected = new int[]{25, 50, 100, 200, 300};
        assertEquals(Arrays.toString(expected), biTree.toString());
    }

    @Test
    void removeNodeWithOneSuccessor() {
        BinaryTree<Integer> biTree1 = new BinaryTree<>();
        int[] toAdd = new int[]{44, 66, 78, 71, 38, 87, 45, 46, 62, 13, 79, 95, 70, 69, 2, 87, 20, 43, 56, 66, 94, 6, 7};
        for (int i : toAdd) {
            biTree1.add(i);
        }
        biTree1.remove(45);
        assertTrue(biTree1.checkInvariant());

        int[] expected = new int[]{2, 6, 7, 13, 20, 38, 43, 44, 46, 56, 62, 66, 69, 70, 71, 78, 79, 87, 94, 95};
        assertEquals(Arrays.toString(expected), biTree1.toString());

        BinaryTree<Integer> biTree2 = new BinaryTree<>();
        toAdd = new int[]{200, 100, 300, 50, 25, 60, 70};
        for (int i : toAdd)
            biTree2.add(i);
        biTree2.remove(60);

        expected = new int[]{25, 50, 70, 100, 200, 300};
        assertEquals(Arrays.toString(expected), biTree2.toString());
    }

    @Test
    void removeNodeWithTwoSuccessors(){
        BinaryTree<Integer> biTree1 = new BinaryTree<>();
        int[] toAdd = new int[]{200, 100, 300, 50, 25, 60, 70, 55, 52, 57};
        for(int i: toAdd)
            biTree1.add(i);
        biTree1.remove(60);
        assertTrue(biTree1.checkInvariant());

        int[] expected = new int[]{25, 50, 52, 55, 57, 70, 100, 200, 300};
        assertEquals(Arrays.toString(expected), biTree1.toString());


        BinaryTree<Integer> biTree2 = new BinaryTree<>();
        toAdd = new int[]{200, 100, 300, 50, 25, 60, 70, 55, 52, 57, 65, 64, 66, 63};
        for (int i : toAdd)
            biTree2.add(i);
        biTree2.remove(60);
        assertTrue(biTree2.checkInvariant());
        expected = new int[]{25, 50, 52, 55, 57, 63, 64, 65, 66, 70, 100, 200, 300};
        assertEquals(Arrays.toString(expected), biTree2.toString());
    }
}