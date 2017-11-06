import org.junit.Before;
import org.junit.Test;
import java.util.Iterator;
import static org.junit.Assert.assertEquals;


public class BinaryTreeIteratorTest {
    private BinaryTree<Integer> binaryTree = new BinaryTree<>();

    @Before
    public void terms() {
        binaryTree.add(9);
        binaryTree.add(5);
        binaryTree.add(20);
        binaryTree.add(3);
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
    }

    @Test()
    public void findNext() {
        Iterator<Integer> iterator = binaryTree.iterator();
        int treeSize=0;
        Integer pastNode;
        Integer currentNode;


        treeSize++;
        currentNode=iterator.next();
        System.out.println(currentNode);
        while (iterator.hasNext()) {
            treeSize++;
            pastNode=currentNode;
            currentNode=iterator.next();
            assertEquals(true,currentNode.compareTo(pastNode)>0);
            System.out.println(currentNode);
        }
        assertEquals(true,treeSize==binaryTree.size());

    }

}

