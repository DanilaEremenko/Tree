import java.util.Map;
import java.util.TreeMap;

public class SimpleGraph<T extends Comparable<T>> {

    private static class Node<T> {
        final T value;
        Map<Node<T>, Integer> nearNodes;//1-Узел,2-вес ребра
        ColorOfSearch colorOfSearch = ColorOfSearch.WHITE;

        Node(T value) {
            this.value = value;
            nearNodes = new TreeMap<>();
        }

        boolean addNeighbor(Node<T> neighbor, Integer weight) {
            nearNodes.put(neighbor, weight);
            return true;
        }
    }

    private Node<T> startedNode = null;

    private enum ColorOfSearch {
        WHITE, GREY, BLACK;

    }

    private int size = 0;



    public boolean add(T nodeNeighbor, Integer weight) {
        if (size == 0) {
            startedNode = new Node<>(nodeNeighbor);
            return true;
        }
        startedNode.addNeighbor(new Node<>(nodeNeighbor),weight);
        size++;
        return true;
    }


}
