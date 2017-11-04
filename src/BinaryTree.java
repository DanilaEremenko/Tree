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


    //ОКЕЙ ЛЕГКО
    public Node<T> searchParent(Node<T> child) {
        if (child == root) return null;
        Node<T> currentNode = root;
        Node<T> parent = new Node<>(null);
        while (currentNode != null) {
            if (currentNode.left == child) {
                parent = currentNode;
                break;
            }
            if (currentNode.right == child) {
                parent = currentNode;
                break;
            }
            if (child.value.compareTo(currentNode.value) > 0) {
                currentNode = currentNode.right;
            } else {
                if (child.value.compareTo(currentNode.value) < 0) {
                    currentNode = currentNode.left;
                }
            }
        }
        return parent;
    }


    @Override
    public boolean remove(Object o) {
        @SuppressWarnings("unchecked")
        Node<T> removedNode = find(root, (T) o);
        if (removedNode == null) return false;
        Node<T> parentNode = searchParent(removedNode);


        if (removedNode.value.compareTo(root.value) == 0) {
            if (removedNode.left == null && removedNode.right == null)
                removedNode=null;
            else if (removedNode.left == null) {


            } else if (removedNode.right == null) {


            }

        } else if (removedNode.value.compareTo(parentNode.value) < 0) {//Если удаляемое левое удаляемое относительно родителя
            parentNode.left = removedNode.right;
            balancing(parentNode.left, removedNode.left);

        } else if (removedNode.value.compareTo(parentNode.value) > 0) {//Если удаляемое правое относительно корня
            parentNode.right = removedNode.right;
            balancing(parentNode.right, removedNode.left);


        }
        return true;

    }

    private void balancing(Node<T> currentToRoot, Node<T> left) {
        if (currentToRoot.left != null)
            balancing(currentToRoot.left, left);
        else
            currentToRoot.left = left;
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

    @Override
    public boolean contains(Object o) {
        @SuppressWarnings("unchecked")
        T t = (T) o;
        Node<T> closest = find(t);
        return closest != null && t.compareTo(closest.value) == 0;
    }

    public class BinaryTreeIterator implements Iterator<T> {

        private Node<T> current = null;

        private BinaryTreeIterator() {
        }

        private Node<T> findNext() {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean hasNext() {
            return findNext() != null;
        }

        @Override
        public T next() {
            current = findNext();
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
        throw new UnsupportedOperationException();
    }

    @NotNull
    @Override
    public SortedSet<T> headSet(T toElement) {
        throw new UnsupportedOperationException();
    }

    @NotNull
    @Override
    public SortedSet<T> tailSet(T fromElement) {
        throw new UnsupportedOperationException();
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

    public static void main(String[] args) {
        BinaryTree<Integer> binaryTree = new BinaryTree<Integer>();
        //binaryTree.add(8);
        //binaryTree.add(2);
        //binaryTree.add(3);
        //binaryTree.add(7);
        //binaryTree.add(10);
        //binaryTree.add(9);
        //binaryTree.add(5);
        //binaryTree.add(6);
        binaryTree.add(9);
        binaryTree.add(5);
        binaryTree.add(20);
        binaryTree.add(3);
        binaryTree.add(7);
        binaryTree.add(2);
        binaryTree.add(4);
        binaryTree.add(7);
        binaryTree.add(6);
        binaryTree.add(8);
        binaryTree.add(15);
        binaryTree.add(25);
        binaryTree.add(13);
        binaryTree.add(17);
        binaryTree.add(23);
        binaryTree.add(21);
        binaryTree.add(24);
        binaryTree.add(27);
        binaryTree.add(26);
        binaryTree.add(30);
        binaryTree.remove(20);
        System.out.println("еболо");

        //binaryTree.remove(1);

        /*

                          8
                      /       \
                  2               10
                   \             /
                    3           9
                     \
                      7
                     /
                    5
                     \
                      6
         */

        /*

                          8
                      /       \
                  2               10
                   \             /
                    3           9
                     \
                      7
                     /
                    6

         */

    }


}