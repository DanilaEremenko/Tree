import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;

public class BinaryTreeTest {
    BinaryTree<Integer> binaryTree = new BinaryTree<>();

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

    @Test
    public void remove() {


        //Удаление узла с двумя детьми
        binaryTree.remove(5);
        System.out.println(binaryTree.contains(5));
        System.out.println(binaryTree.checkInvariant());

        //Удаление узла без детей
        binaryTree.remove(30);
        System.out.println(binaryTree.contains(30));
        System.out.println(binaryTree.checkInvariant());

        //Удаление узла с одним ребенком
        binaryTree.remove(27);
        System.out.println(binaryTree.contains(27));
        System.out.println(binaryTree.checkInvariant());

        //Удаление корня
        binaryTree.remove(9);
        System.out.println(binaryTree.contains(9));
        System.out.println(binaryTree.checkInvariant());


    }

}