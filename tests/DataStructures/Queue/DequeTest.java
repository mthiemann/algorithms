package DataStructures.Queue;

import org.junit.After;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

/**
 * Tests the linked and array deque implementation
 * TODO: Add tests for all permutations of the operations
 */
@RunWith(Parameterized.class)
public class DequeTest {

  enum Positioning {
    DEFAULT,
    FIRST,
    LAST
  }

  // Test data
  private final static Integer[] singleValArr = new Integer[]{10};
  private final static Integer[] twoValueArr = new Integer[]{10, 17};
  private final static Integer[] multipleValueArr =
    new Integer[]{2, 5, 1000, 300, 12, 4, 78, 304, 50, 402, 24, 1, 17, 11};

  private Deque<Integer> deque;

  @Parameterized.Parameters
  public static Collection stackImplementations() {
    return Arrays.asList(new Object[][] {
      { new ArrayDeque<Integer>() },
      { new LinkedDeque<Integer>() }
    });
  }

  public DequeTest(Deque<Integer> deque) {
    this.deque = deque;
  }

  @After
  public void tearDown() {
    this.deque.clear();
  }

  /**
   * Pushes the elements of the array according to the passed positioning and checks it using
   * all available peek methods
   * Tests size, search and peek methods
   * @param integers
   */
  private void fillAndCheckQueue(Integer[] integers, Positioning pos) {

    for (int i = 0; i < integers.length; i++) {
      switch(pos) {
        case DEFAULT: this.deque.add(integers[i]); break;
        case FIRST: this.deque.addFirst(integers[i]); break;
        case LAST: this.deque.addLast(integers[i]); break;
      }

      assertEquals(this.deque.size(), i + 1); // check new size

      int firstIdx;
      int lastIdx;

      if (pos == Positioning.LAST || pos == Positioning.DEFAULT) {
        firstIdx = 0;
        lastIdx = i;
      } else {
        firstIdx = i;
        lastIdx = 0;
      }

      // check all peek methods
      assertEquals(this.deque.peek(), integers[firstIdx]);
      assertEquals(this.deque.peekFirst(), integers[firstIdx]);
      assertEquals(this.deque.peekLast(), integers[lastIdx]);
    }
  }

  /**
   * Polls as many elements from the queue as the array's length
   * The array contains the elements in the order in which they are expected to be polled by the
   * corresponding polling method which is determined by pos.
   * @param integers
   * @param pos
   */
  private void pollAllElementsFromDeque(Integer[] integers, Positioning pos) {

    int initSize = this.deque.size();
    Integer polledElement = null;

    for (int i = 0; i < integers.length; i++) {
      switch(pos) {
        case DEFAULT: polledElement = this.deque.poll(); break;
        case FIRST: polledElement = this.deque.pollFirst(); break;
        case LAST: polledElement = this.deque.pollLast(); break;
      }

      assertEquals(this.deque.size(), initSize - 1 - i); // check new size
      assertEquals(polledElement, integers[i]);
    }
  }

  private Integer[] getReverseArray(Integer[] arr) {

    Integer[] reverseArr = new Integer[arr.length];

    for(int i = 0; i < arr.length; i++) {
      reverseArr[i] = arr[arr.length - 1 - i];
    }

    return reverseArr;
  }

  @Test
  public void addToEmptyDeque() {
    fillAndCheckQueue(DequeTest.singleValArr, Positioning.DEFAULT);
  }

  @Test
  public void addFirstToEmptyDeque() {
    fillAndCheckQueue(DequeTest.singleValArr, Positioning.FIRST);
  }

  @Test
  public void addLastToEmptyDeque() {
    fillAndCheckQueue(DequeTest.singleValArr, Positioning.LAST);
  }

  @Test
  public void addToSingleElementDeque() {
    fillAndCheckQueue(DequeTest.twoValueArr, Positioning.DEFAULT);
  }

  @Test
  public void addFirstToSingleElementDeque() {
    fillAndCheckQueue(DequeTest.twoValueArr, Positioning.FIRST);
  }

  @Test
  public void addLastToSingleElementDeque() {
    fillAndCheckQueue(DequeTest.twoValueArr, Positioning.LAST);
  }

  @Test
  public void addMultipleElementsToDeque() {
    fillAndCheckQueue(DequeTest.multipleValueArr, Positioning.DEFAULT);
  }

  @Test
  public void addMultipleFirstElementsToDeque() {
    fillAndCheckQueue(DequeTest.multipleValueArr, Positioning.FIRST);
  }

  @Test
  public void addMultipleLastElementsToDeque() {
    fillAndCheckQueue(DequeTest.multipleValueArr, Positioning.LAST);
  }

  @Test
  public void peekEmptyDeque() {
    assertEquals(this.deque.peek(), null);
  }

  @Test
  public void peekFirstEmptyDeque() {
    assertEquals(this.deque.peekFirst(), null);
  }

  @Test
  public void peekLastEmptyDeque() {
    assertEquals(this.deque.peekLast(), null);
  }

  @Test
  public void pollEmptyDeque() {
    assertEquals(this.deque.poll(), null);
  }

  @Test
  public void pollFirstEmptyDeque() {
    assertEquals(this.deque.pollFirst(), null);
  }

  @Test
  public void pollLastEmptyDeque() {
    assertEquals(this.deque.pollLast(), null);
  }

  @Test
  public void pollSingleElementDeque() {
    fillAndCheckQueue(DequeTest.singleValArr, Positioning.DEFAULT);
    pollAllElementsFromDeque(DequeTest.singleValArr, Positioning.DEFAULT);
  }

  @Test
  public void pollFirstSingleElementDeque() {
    fillAndCheckQueue(DequeTest.singleValArr, Positioning.DEFAULT);
    pollAllElementsFromDeque(DequeTest.singleValArr, Positioning.FIRST);
  }

  @Test
  public void pollLastSingleElementDeque() {
    fillAndCheckQueue(DequeTest.singleValArr, Positioning.DEFAULT);
    pollAllElementsFromDeque(getReverseArray(DequeTest.singleValArr), Positioning.LAST);
  }

  @Test
  public void pollMultipleElementsFromDeque() {
    fillAndCheckQueue(DequeTest.singleValArr, Positioning.DEFAULT);
    pollAllElementsFromDeque(DequeTest.singleValArr, Positioning.DEFAULT);
  }

  @Test
  public void pollFirstMultipleElementsFromDeque() {
    fillAndCheckQueue(DequeTest.singleValArr, Positioning.DEFAULT);
    pollAllElementsFromDeque(DequeTest.singleValArr, Positioning.FIRST);
  }

  @Test
  public void pollLastMultipleElementsFromDeque() {
    fillAndCheckQueue(DequeTest.singleValArr, Positioning.DEFAULT);
    pollAllElementsFromDeque(getReverseArray(DequeTest.singleValArr), Positioning.LAST);
  }
}