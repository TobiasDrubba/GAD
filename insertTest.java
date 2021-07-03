package gad.avl;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class insertTest {
    private AVLTreeNode t0;
    private AVLTreeNode t1;
    private AVLTreeNode t2;
    private AVLTreeNode t3;
    private AVLTreeNode t4;
    private AVLTreeNode t5;
    private AVLTreeNode t6;
    private AVLTreeNode t7;
    private AVLTreeNode t8;
    private AVLTreeNode t9;


    private AVLTree expected;
    private AVLTreeNode root;
    private AVLTreeNode left;
    private AVLTreeNode left2;
    private AVLTreeNode right;
    private AVLTreeNode right2;
    private AVLTreeNode sameAsRoot;

    private final static int rootKey = 3;
    private final static int leftKey = 1;
    private final static int leftKey2 = 0;
    private final static int rightKey = 6;
    private final static int rightKey2 = 8;

    private AVLTree got;

    @BeforeEach
    void setup() {
        expected = new AVLTree();
        got = new AVLTree();
        root = new AVLTreeNode(rootKey);
        left = new AVLTreeNode(leftKey);
        left2 = new AVLTreeNode(leftKey2);
        right = new AVLTreeNode(rightKey);
        right2 = new AVLTreeNode(rightKey2);
        sameAsRoot = new AVLTreeNode(rootKey);

        t0 = new AVLTreeNode(0);
        t1 = new AVLTreeNode(1);
        t2 = new AVLTreeNode(2);
        t3 = new AVLTreeNode(3);
        t4 = new AVLTreeNode(4);
        t5 = new AVLTreeNode(5);
        t6 = new AVLTreeNode(6);
        t7 = new AVLTreeNode(7);
        t8 = new AVLTreeNode(8);
        t9 = new AVLTreeNode(9);

        expected.setRoot(root);
    }

    @AfterEach
    void checkIfExpectedIsValidTree() {
        assertTrue(expected.validAVL());
    }

    @AfterEach
    void checkResult() {
        assertEquals(expected, got);
    }

    @Test
    void simpleInsert() {
        got.insert(rootKey);
    }

    @Test
    void insertLeft() {
        //set expected
        root.setLeft(left);
        root.setBalance(-1);
        //set result
        got.insert(rootKey);
        got.insert(leftKey);
    }

    @Test
    void insertRight() {
        //set expected
        root.setRight(right);
        root.setBalance(1);
        //set result
        got.insert(rootKey);
        got.insert(rightKey);
    }

    @Test
    void insertTwiceSame() {
        //set expected
        root.setRight(sameAsRoot);
        root.setBalance(1);
        //set result
        got.insert(rootKey);
        got.insert(rootKey);
    }

    @Test
    void biggerSymmetricalInsert() {
        root.setRight(root);
        root.setRight(right);
        root.setLeft(left);
        right.setRight(right2);
        left.setLeft(left2);
        right.setBalance(1);
        left.setBalance(-1);

        got.insert(rootKey);
        got.insert(rightKey);
        got.insert(leftKey);
        got.insert(rightKey2);
        got.insert(leftKey2);
    }

    @Test
    void simpleLeftRotation() {
        //set expected
        expected.setRoot(right);
        right.setRight(right2);
        right.setLeft(root);
        //set result
        got.insert(rootKey);
        got.insert(rightKey);
        got.insert(rightKey2);
    }

    @Test
    void leftRotation() {
        //set expected
        expected.setRoot(t4);
        t4.setLeft(t2);
        t2.setLeft(t1);
        t2.setRight(t3);
        t4.setRight(t5);
        t5.setRight(t6);
        t5.setBalance(1);
        //set result
        insert(new int[] {2, 1, 4, 3, 5, 6});
    }

    void insert(int[] arr) {
        for (int i : arr) {
            got.insert(i);
        }
    }

    @Test
    void rightLeftRotation() {
        expected.setRoot(t4);
        //set left of root
        t4.setLeft(t2);
        t2.setLeft(t1);
        t1.setLeft(t0);
        t2.setRight(t3);
        // setBalance
        t1.setBalance(-1);
        t2.setBalance(-1);
        //set right of root
        t4.setRight(t7);
        t7.setLeft(t5);
        t5.setRight(t6);
        t7.setRight(t8);
        t8.setRight(t9);
        // set Balance
        t5.setBalance(1);
        t8.setBalance(1);

        insert(new int[] {2, 1, 7}); // root and first layer
        insert(new int[] {0, 4, 8}); // third layer
        insert(new int[] {3, 5, 9}); // fourth layer
        got.insert(6);
    }
    
    @Test
    void simpleRightRotation() {
        expected.setRoot(t1);
        t1.setLeft(t0);
        t1.setRight(t2);

        got.insert(2);
        got.insert(1);
        got.insert(0);
    }
}
