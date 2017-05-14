package DataStructures;

import static org.junit.Assert.*;
import org.junit.Test; // for @Test
import org.junit.Before; // for @Before

import java.util.ArrayList;
import java.util.Comparator;

public class HeapTest {

  public class MinHeapComparator implements Comparator<Integer> {

    @Override
    public int compare(Integer i, Integer j) {
      return i - j;
    }
  }

  public class MaxHeapComparator implements Comparator<Integer> {

    @Override
    public int compare(Integer i, Integer j) {
      return j - i;
    }
  }

  private Heap<Integer> naturalOrderingHeap;
  private Heap<Integer> minHeap;
  private Heap<Integer> maxHeap;
  private ArrayList<Heap<Integer>> heaps;

  @Before
  public void setUp() {
    naturalOrderingHeap = new Heap<Integer>();
    minHeap = new Heap<Integer>(new MinHeapComparator());
    maxHeap = new Heap<Integer>(new MaxHeapComparator());

    heaps = new ArrayList<>();
    heaps.add(naturalOrderingHeap);
    heaps.add(minHeap);
    heaps.add(maxHeap);
  }

  /**
   * Fill corresponding Heap with n elements from 1 to n
   * @param n
   * @param heapType Min Heap (<0), Max Heap (>0), Natural Ordering (0)
   */
  private void fillHeap(int n, int heapType) {

    Heap<Integer> heap;
    if (heapType < 0) {
      heap = minHeap;
    } else if (heapType > 0) {
      heap = maxHeap;
    } else {
      heap = naturalOrderingHeap;
    }

    for (int i = 1; i <= n; i++) {
      heap.push(i);
    }
  }

  /**
   * Fill all Heaps with n elements
   * @param n
   */
  private void fillAllHeaps(int n) {
    fillHeap(n, -1);
    fillHeap(n, 0);
    fillHeap(n, 1);
  }

  @Test
  public void testSize() {
    fillAllHeaps(9);
    for (Heap<Integer> heap: heaps) {
      assertEquals(heap.size(), 9);
    }
  }

  @Test
  public void testResize() {
    try {
      fillAllHeaps(11);
    } catch(IndexOutOfBoundsException e) {
      fail();
    }

    for (Heap<Integer> heap: heaps) {
      assertEquals(heap.size(), 11);
    }
  }

  private void testOnePeek(Heap<Integer> heap, int expected) {
    int initialSize = heap.size();
    int top = heap.peek();
    assertEquals(top, expected);
    assertEquals(heap.size(), initialSize);
  }

  @Test
  public void testPeek() {
    int n = 5;
    fillAllHeaps(n);
    for (int i = 0; i < 2; i++) {
      testOnePeek(naturalOrderingHeap, 1);
      testOnePeek(minHeap, 1);
      testOnePeek(maxHeap, n);
    }
  }

  @Test
  public void testPeekForEmptyHeap() {
    for (Heap<Integer> heap: heaps) {
      assertEquals(heap.peek(), null);
      assertEquals(heap.size(), 0);
    }
  }

  private void testOnePoll(Heap<Integer> heap, int expected) {
    int initialSize = heap.size();
    int top = heap.poll();
    assertEquals(top, expected);

    int finalSize = heap.size();
    assertEquals(finalSize, initialSize - 1);
    assertTrue(finalSize >= 0);
  }

  @Test
  public void testPoll() {
    int n = 5;
    fillAllHeaps(n);

    for (int i = 0; i < 2; i++) {
      testOnePoll(naturalOrderingHeap, 1 + i);
      testOnePoll(minHeap, 1 + i);
      testOnePoll(maxHeap, n - i);
    }
  }

  @Test
  public void testPollForAllElements() {
    int n = 10;
    fillAllHeaps(n);

    for (int i = 0; i < n; i++) {
      testOnePoll(naturalOrderingHeap, 1 + i);
      testOnePoll(minHeap, 1 + i);
      testOnePoll(maxHeap, n - i);
    }
  }

  @Test
  public void testPollForSingleElementHeap() {
    fillAllHeaps(1);
    for (Heap<Integer> heap: heaps) {
      testOnePoll(heap, 1);
    }
  }

  @Test
  public void testPollForEmptyHeap() {
    for (Heap<Integer> heap: heaps) {
      assertEquals(heap.poll(), null);
      assertEquals(heap.size(), 0);
    }
  }

  @Test
  public void testRemoveForBranch() {
    int n = 5;
    this.fillAllHeaps(n);

    assertTrue(naturalOrderingHeap.remove(2));
    assertTrue(minHeap.remove(2));
    assertTrue(maxHeap.remove(4));

    for (Heap<Integer> heap: heaps) {
      assertEquals(heap.size(), n - 1);
    }

    assertEquals((int)naturalOrderingHeap.peek(), 1);
    assertEquals((int)minHeap.peek(), 1);
    assertEquals((int)maxHeap.peek(), 5);
  }

  @Test
  public void testRemoveForLeaf() {
    int n = 5;
    this.fillAllHeaps(n);

    assertTrue(naturalOrderingHeap.remove(n));
    assertTrue(minHeap.remove(n));
    assertTrue(maxHeap.remove(1));

    for (Heap<Integer> heap: heaps) {
      assertEquals(heap.size(), n - 1);
    }

    assertEquals((int)naturalOrderingHeap.peek(), 1);
    assertEquals((int)minHeap.peek(), 1);
    assertEquals((int)maxHeap.peek(), 5);
  }

  @Test
  public void testRemoveForRoot() {
    int n = 5;
    this.fillAllHeaps(n);

    assertTrue(naturalOrderingHeap.remove(1));
    assertTrue(minHeap.remove(1));
    assertTrue(maxHeap.remove(5));

    for (Heap<Integer> heap: heaps) {
      assertEquals(heap.size(), n - 1);
    }

    assertEquals((int)naturalOrderingHeap.peek(), 2);
    assertEquals((int)minHeap.peek(), 2);
    assertEquals((int)maxHeap.peek(), 4);
  }

  @Test
  public void testRemoveForEmptyHeap() {
    for (Heap<Integer> heap: heaps) {
      assertEquals(heap.remove(5), false);
      assertEquals(heap.size(), 0);
    }
  }

  @Test
  public void testRemoveForSingleElementHeap() {
    this.fillAllHeaps(1);
    for (Heap<Integer> heap: heaps) {
      assertTrue(heap.remove(1));
      assertEquals(heap.size(), 0);
      assertEquals(heap.peek(), null);
    }
  }

  @Test
  public void testRemoveForAllElements() {

  }

  @Test
  public void testRemoveForNonExistingElement() {
    this.fillAllHeaps(5);
    for (Heap<Integer> heap: heaps) {
      int initialSize = heap.size();
      assertFalse(heap.remove(8));
      assertEquals(heap.size(), initialSize);
    }
  }
}