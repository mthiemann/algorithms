package DataStructures.Trees;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BSTTest<E extends Comparable<E>> {

  private BST<Integer> bst;

  @Before
  public void setUp() {
    bst = new BST<Integer>();
  }

  private void addElements(Integer[] elements) {
    for (int i = 0; i < elements.length; i++) {
      bst.add(elements[i]);
    }
  }

  @Test
  public void testAddOnEmptyBst() {
    bst.add(1);
    assertTrue(bst.contains(1));
    assertEquals(bst.size(), 1);
  }

  @Test
  public void testMultipleAddOnBst() {
    bst.add(1);
    bst.add(90);
    bst.add(100);

    assertTrue(bst.contains(1));
    assertTrue(bst.contains(90));
    assertTrue(bst.contains(100));
    assertEquals(bst.size(), 3);
  }

  @Test
  public void testAddWithDuplicateElements() {
    Integer[] elements = new Integer[]{ 50, 30, 50, 25, 30}; // 30 has children: 25 and 35
    this.addElements(elements);

    assertTrue(bst.contains(50));
    assertTrue(bst.contains(30));
    assertTrue(bst.contains(25));
    assertEquals(bst.size(), 3);
  }

  @Test
  public void testContainsOnEmptyBst() {
    assertFalse(bst.contains(33));
  }

  @Test
  public void testRemoveOnEmptyBst() {
    assertFalse(bst.remove(10));
  }

  @Test
  public void testRemoveOnBranchWithTwoChildren() {
    Integer[] elements = new Integer[]{ 50, 30, 70, 25, 35}; // 30 has children: 25 and 35
    this.addElements(elements);

    Integer valueToRemove = 30;
    bst.remove(valueToRemove);

    for (int i = 0; i < elements.length; i++) {
      boolean contains = bst.contains(elements[i]);
      assertTrue((elements[i] == valueToRemove) != contains);
    }

    assertEquals(bst.size(), elements.length - 1);
  }

  @Test
  public void testRemoveOnBranchWithJustOneRightChild() {
    Integer[] elements = new Integer[]{40, 50, 65}; // 50 has only one right child: 65
    this.addElements(elements);

    assertTrue(bst.remove(50));
    assertFalse(bst.contains(50));
    assertTrue(bst.contains(65));
    assertTrue(bst.contains(40));
    assertEquals(bst.size(), 2);
  }

  @Test
  public void testRemoveOnBranchWithJustOneLeftChild() {
    Integer[] elements = new Integer[]{40, 50, 45}; // 50 has only one left child: 45
    this.addElements(elements);

    assertTrue(bst.remove(50));
    assertFalse(bst.contains(50));
    assertTrue(bst.contains(45));
    assertTrue(bst.contains(40));
    assertEquals(bst.size(), 2);
  }
}