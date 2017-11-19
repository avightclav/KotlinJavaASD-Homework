import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

// Attention: comparable supported but comparator is not
@SuppressWarnings("WeakerAccess")
public class BinaryTree<T extends Comparable<T>> extends AbstractSet<T> implements SortedSet<T> {

    private static class Node<T> {
        final T value;

        Node<T> left = null;

        Node<T> right = null;

        Node(T value) {
            this.value = value;
        }
    }

    private Node<T> root = null;

    private int size = 0;

    @SuppressWarnings("unchecked")
    class subBinaryTree<T extends Comparable<T>> extends BinaryTree<T> {
        final BinaryTree<T> backedTree;

        final T fromElement;
        final T toElement;

        subBinaryTree(T fromElement, T toElement, BinaryTree<T> backedTree) {
            this.backedTree = backedTree;
            this.fromElement = fromElement;
            this.toElement = toElement;
        }

        boolean inRange(T t) {
            if ((fromElement != null) && (toElement != null)) {
                return ((t.compareTo(fromElement) <= 0) && (t.compareTo(toElement) >= 0));
            }

            if (fromElement != null) {
                return (t.compareTo(fromElement) <= 0);
            } else {
                return (t.compareTo(toElement) >= 0);
            }
        }

        @Override
        public boolean add(T t) {
            if (inRange(t)) {
                return backedTree.add(t);
            } else {
                throw new IllegalArgumentException();
            }
        }

        @Override
        public boolean remove(Object o) {
            @SuppressWarnings("unchecked")
            T t = (T) o;
            if (inRange(t)) {
                return backedTree.remove(t);
            } else {
                throw new IllegalArgumentException();
            }
        }

        @NotNull
        @Override
        public Iterator<T> iterator() {
            return new subBinaryTreeIterator();
        }

        @Override
        public int size() {
            throw new UnsupportedOperationException();
        }

        @SuppressWarnings("unchecked")
        @Override
        public boolean contains(Object o) {
            T t = (T) o;
            if (inRange(t)) {
                return backedTree.contains(t);
            } else {
                throw new IllegalArgumentException();
            }
        }

        class subBinaryTreeIterator implements Iterator<T> {
            private T current = null;
            final Iterator binaryTreeIterator = backedTree.iterator();

            subBinaryTreeIterator() {
            }

            @Override
            public boolean hasNext() {
                return (findNext() != null);
            }

            @SuppressWarnings("unchecked")
            private T findNext() {
                current = (T) binaryTreeIterator.next();
                while ((fromElement != null) && (current.compareTo(fromElement) < 0)) {
                    if (current == null)
                        return null;
                    current = (T) binaryTreeIterator.next();
                }
                if ((toElement != null) && (current.compareTo(toElement) >= 0))
                    return null;
                return current;
            }

            @Override
            public T next() {
                if (current == null) {
                    current = findNext();
                }
                T tmpResult = current;
                current = null;
                return tmpResult;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        }

        @Override
        public T first() {
            if (fromElement != null) {
                return fromElement;
            } else {
                return backedTree.first();
            }
        }

        @Override
        public T last() {
            if (toElement != null) {
                return fromElement;
            } else {
                return backedTree.last();
            }
        }
    }

    @Override
    public boolean add(T t) {
        Node<T> closest = find(t);
        int comparison = closest == null ? -1 : t.compareTo(closest.value);
        if (comparison == 0) {
            return false;
        }
        Node<T> newNode = new Node<>(t);
        if (closest == null) {
            root = newNode;
        } else if (comparison < 0) {
            assert closest.left == null;
            closest.left = newNode;
        } else {
            assert closest.right == null;
            closest.right = newNode;
        }
        size++;
        return true;
    }

    boolean checkInvariant() {
        return root == null || checkInvariant(root);
    }

    private boolean checkInvariant(Node<T> node) {
        Node<T> left = node.left;
        if (left != null && (left.value.compareTo(node.value) >= 0 || !checkInvariant(left))) return false;
        Node<T> right = node.right;
        return right == null || right.value.compareTo(node.value) > 0 && checkInvariant(right);
    }

    @Override
    public boolean remove(Object o) {
        @SuppressWarnings("unchecked")
        T t = (T) o;
        if (!this.contains(o)) {
            return false;
        }
        root = remove(root, t);
        return true;
    }

    Node<T> remove(Node<T> node, T t) {
        if (node == null) {
            return null;
        }
        int comparison = t.compareTo(node.value);
        if (comparison < 0) {
            node.left = remove(node.left, t);
        } else if (comparison > 0) {
            node.right = remove(node.right, t);
        } else {
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            } else {
                Node<T> tmpLeft = node.left;
                Node<T> tmpRight = node.right;
                node = minValue(node.right);
                node.right = remove(tmpRight, node.value);
                node.left = tmpLeft;
            }
        }
        return node;
    }

    Node<T> minValue(Node<T> node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }


    @Override
    public boolean contains(Object o) {
        @SuppressWarnings("unchecked")
        T t = (T) o;
        Node<T> closest = find(t);
        return closest != null && t.compareTo(closest.value) == 0;
    }

    private Node<T> find(T value) {
        if (root == null) return null;
        return find(root, value);
    }

    private Node<T> find(Node<T> start, T value) {
        int comparison = value.compareTo(start.value);
        if (comparison == 0) {
            return start;
        } else if (comparison < 0) {
            if (start.left == null) return start;
            return find(start.left, value);
        } else {
            if (start.right == null) return start;
            return find(start.right, value);
        }
    }

    public class BinaryTreeIterator implements Iterator<T> {

        private Node<T> current = null;

        private final Stack<Node<T>> pastNodes;
        private boolean reverseMove;
        private Node<T> pastNode;

        private BinaryTreeIterator() {
            pastNodes = new Stack<>();
            current = root;
            pastNode = root;
        }

        private Node<T> findNext() {
            if (!reverseMove && (current.left != null)) {
                if (current.left.left != null) {
                    if (!pastNodes.contains(current)) {
                        pastNodes.push(current);
                    }
                    current = current.left;
                    return findNext();
                } else {
                    if (!pastNodes.contains(current)) {
                        pastNodes.push(current);
                    }
                    pastNode = current.left;
                    return current.left;
                }
            } else if (current.right != null) {
                if (current.right.left != null) {
                    current = current.right;
                    pastNodes.push(current);
                    reverseMove = false;
                    return findNext();
                }
                return current.right;
            } else {
                reverseMove = true;
                if (!pastNodes.isEmpty())
                    return pastNodes.lastElement();
                else
                    return null;
            }
        }

        @Override
        public boolean hasNext() {
            return findNext() != null;
        }

        @Override
        public T next() {
            current = findNext();

            if (pastNode.right == current) {
                reverseMove = false;
            }
            if (reverseMove && !pastNodes.isEmpty())
                pastNode = pastNodes.pop();


            if (current == null) throw new NoSuchElementException();

            return current.value;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

    }

    @NotNull
    @Override
    public Iterator<T> iterator() {
        return new BinaryTreeIterator();
    }

    @Override
    public int size() {
        return size;
    }


    @Nullable
    @Override
    public Comparator<? super T> comparator() {
        return null;
    }

    @NotNull
    @Override
    public SortedSet<T> subSet(T fromElement, T toElement) {
        return new subBinaryTree<>(fromElement, toElement, this);
    }

    @NotNull
    @Override
    public SortedSet<T> headSet(T toElement) {
        return new subBinaryTree<>(null, toElement, this);
    }

    @NotNull
    @Override
    public SortedSet<T> tailSet(T fromElement) {
        return new subBinaryTree<>(fromElement, null, this);
    }

    @Override
    public T first() {
        if (root == null) throw new NoSuchElementException();
        Node<T> current = root;
        while (current.left != null) {
            current = current.left;
        }
        return current.value;
    }

    @Override
    public T last() {
        if (root == null) throw new NoSuchElementException();
        Node<T> current = root;
        while (current.right != null) {
            current = current.right;
        }
        return current.value;
    }
}