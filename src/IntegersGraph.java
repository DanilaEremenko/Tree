import java.lang.reflect.Array;

public class IntegersGraph {
    private int size = 0;
    private Vertex first;
    int matrix[][];


    boolean add(int value) {
        if (size == 0) {
            first = new Vertex(value);
            size++;
            return true;
        }
        return false;
    }

    boolean add(int addedValue, int adress) {
        if (size == 0)
            return false;
        if (first.value == adress) {
            return first.addNeighbor(new Vertex(addedValue));
        }

        Vertex searchedVertex = find(first, adress);
        searchedVertex.addNeighbor(new Vertex(addedValue));
        size++;
        return true;
    }

    Vertex get(int value) {
        if (value == first.value)
            return first;
        return find(first, value);
    }

    private Vertex find(Vertex currentVertex, int value) {
        for (Vertex neighbor : currentVertex.neighbors)
            if (neighbor != null)
                if (neighbor.value == value)
                    return neighbor;

        currentVertex.color = 2;//Красим в черный цвет
        Vertex result;
        for (Vertex neighbor : currentVertex.neighbors) {
            if (neighbor != null && neighbor.color != 2) {
                result = find(neighbor, value);
                if (result != null)
                    return result;
            }
        }

        return null;

    }


    private class Vertex {
        int value;
        Vertex neighbors[];
        int indexOfNeigh;
        int color = 0;

        Vertex(int value) {
            this.value = value;
            neighbors = new Vertex[5];
        }

        private boolean addNeighbor(Vertex neighbor) {
            if (neighbors.length - indexOfNeigh == 0)
                return false;

            neighbors[indexOfNeigh] = neighbor;
            indexOfNeigh++;
            return true;
        }

        @Override
        public String toString() {
            return "" + this.value;
        }

        public String neigborsToString() {
            String string = "Смежные\n";
            for (Vertex neigbor : neighbors)
                if (neigbor != null)
                    string += neigbor + "\n";
            return string;

        }
    }

    public static void main(String[] args) {
        IntegersGraph integersGraph = new IntegersGraph();
        integersGraph.add(5);
        integersGraph.add(6, 5);
        integersGraph.add(7, 5);
        integersGraph.add(8,5);
        integersGraph.add(9,5);
        integersGraph.add(10,5);
        System.out.println(!integersGraph.add(11,5));
        System.out.println(integersGraph.get(5).neigborsToString());


    }

}
