package DataStructures.Heap;

import DataStructures.Collection;

import java.util.Arrays;
import java.util.Comparator;

@SuppressWarnings("unchecked")
public class ArrayHeap<E extends Comparable<E>> implements Heap<E> {

  private static int startLength = 10;

  private Object[] arr;
  private int size;
  private Comparator<E> comparator;

  public ArrayHeap() {
    initHeap();
  }

  public ArrayHeap(Comparator<E> comparator) {
    this();
    this.comparator = comparator;
  }

  private void initHeap() {
    this.arr = new Object[startLength];
    this.size = 0;
  }

  /**
   * Add element to the heap
   * @param el
   */
  @Override
  public void push(E el) {
    // if array too small -> resize
    if (this.size >= this.arr.length) this.resize();

    // insert at index = current size
    this.arr[this.size] = el;
    this.size++;

    this.bubbleUp(this.size - 1);
  }

  /**
   * Return max element without removing it
   * @return
   */
  @Override
  public E peek() {
    return (this.size > 0) ? (E)this.arr[0] : null;
  }

  /**
   * Return and remove max element
   * @return
   */
  @Override
  public E poll() {

    if (this.size == 0) {
      return null;
    }

    // extract first element
    E first = (E) this.arr[0];
    this.size--;
    if (this.size == 0) return first;

    // replace with last element
    this.arr[0] = this.arr[size];
    this.arr[size] = 0;

    this.bubbleDown(0);

    return first;
  }

  @Override
  public boolean remove(E element) {

    if (this.size == 0) return false;

    int idx = this.findIdx(element, 0);

    if (idx >= 0) {
      // Replace element with last element
      this.arr[idx] = this.arr[size - 1];
      this.size--;

      int parentIdx = getParentIdx(idx);
      E topChild = getTopChild(idx);

      // bubble up or down if element is at the wrong position
      if (parentIdx >= 0 && compare(parentIdx, idx) > 0) {
        this.bubbleUp(idx);
      } else if (topChild != null && this.compare(topChild, (E)this.arr[idx]) < 0) {
        this.bubbleDown(idx);
      }
      return true;
    } else {
      return false;
    }
  }

  /**
   * Returns the index of the element
   * @param element
   * @param idx -1 if the element is not in the tree
   * @return
   */
  private int findIdx(E element, int idx) {

    // return index if element was found
    if (this.compare(element, (E)this.arr[idx]) == 0) return idx;

    int left = -1;
    int right = -1;

    // call left side
    if (this.hasLeftChild(idx) && this.compare(element, this.getLeftChild(idx)) >= 0) {
      left = this.findIdx(element, this.getLeftChildIdx(idx));
    }

    // call right side
    if (left <= 0 && this.hasRightChild(idx) && this.compare(element, this.getRightChild(idx)) >= 0) {
      right = this.findIdx(element, this.getRightChildIdx(idx));
    }

    // return piped up values
    return (left >= 0) ? left : right;
  }

  private void bubbleDown(int idx) {
    int leftIdx = this.getLeftChildIdx(idx);
    int rightIdx = this.getRightChildIdx(idx);
    int highIdx;

    // stop once element has no children
    while(leftIdx < this.size) {

      highIdx = (rightIdx >= this.size ||
        this.compare(leftIdx, rightIdx) <= 0) ? leftIdx : rightIdx;

      if (this.compare(highIdx, idx) < 0) {
        this.swap(idx, highIdx);

        idx = highIdx;
        leftIdx = this.getLeftChildIdx(idx);
        rightIdx = this.getRightChildIdx(idx);
      } else {
        break;
      }
    }
  }

  private void bubbleUp(int idx) {
    if (idx < 0 || idx >= this.size) {
      throw new IndexOutOfBoundsException("Currently allowed indices: 0-" + (this.size - 1));
    }

    int nxtIdx;

    // stop once heap property is restored
    while (idx > 0) {
      nxtIdx = getParentIdx(idx);
      if (this.compare(nxtIdx, idx) > 0) {
        this.swap(idx, nxtIdx);
      } else {
        break;
      }

      idx = nxtIdx;
    }
  }

  @Override
  public int size() {
    return this.size;
  }

  @Override
  public void clear() { this.initHeap(); }

  @Override
  public boolean isEmpty() { return this.size == 0; }

  private int getParentIdx(int idx) {
    return (int) Math.floor((idx - 1) / 2);
  }

  private int getLeftChildIdx(int idx) {
    return 2 * idx + 1;
  }

  private int getRightChildIdx(int idx) {
    return getLeftChildIdx(idx) + 1;
  }

  private boolean hasLeftChild(int idx) {
    return this.getLeftChildIdx(idx) < this.size;
  }

  private boolean hasRightChild(int idx) {
    return this.getRightChildIdx(idx) < this.size;
  }

  private E getLeftChild(int idx) {
    return (this.hasLeftChild(idx)) ? (E) this.arr[this.getLeftChildIdx(idx)]: null;
  }

  private E getRightChild(int idx) {
    return (this.hasRightChild(idx)) ? (E) this.arr[this.getRightChildIdx(idx)] : null;
  }

  private E getTopChild(int idx) {
    E left = getLeftChild(idx);
    E right = getRightChild(idx);

    if (left != null) {
      return (right == null || this.compare(left, right) <= 0) ? left : right;
    } else {
      return null;
    }
  }

  /**
   * Swap two elements in the array
   * @param i
   * @param j
   */
  private void swap(int i, int j) {
    Object temp = this.arr[j];
    this.arr[j] = this.arr[i];
    this.arr[i] = temp;
  }

  /**
   * Resize the array by a factor of ~1.5
   */
  private void resize() {
    this.arr = Arrays.copyOf(this.arr, this.arr.length + (this.arr.length >> 1));
  }

  /**
   * Compare two elements of the array
   * @param i index of first element
   * @param j index of second element
   * @return
   */
  private int compare(int i, int j) {
    return this.compare((E)this.arr[i], (E)this.arr[j]);
  }

  /**
   * Returns the result of the comparator or comparable compare operation
   * depending on what the Heap was set up with
   * @param e1
   * @param e2
   * @return
   */
  private int compare(E e1, E e2) {
    return (this.comparator != null) ? this.comparator.compare(e1, e2) : e1.compareTo(e2);
  }
}
