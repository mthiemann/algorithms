package DataStructures.Stack;

import java.util.Arrays;

public class ArrayStack implements Stack {

  private Object[] arr;
  private int size;

  public ArrayStack() {
    this.arr = new Object[10];
    this.size = 0;
  }

  @Override
  public Object push(Object element) {
    if (this.size == this.arr.length) this.resizeArr();

    this.arr[this.size++] = element;
    return element;
  }

  private void resizeArr() {
    this.arr = Arrays.copyOf(this.arr, this.arr.length << 1);
  }

  @Override
  public Object peek() {
    return this.arr[this.size - 1];
  }

  @Override
  public Object pop() {

    if (this.isEmpty()) return null;

    Object o = this.arr[this.size - 1];
    this.arr[this.size - 1] = null;
    this.size--;
    return o;
  }

  @Override
  public int search(Object element) {

    int idx = -1;

    for (int i = 0; i < this.size; i++) {
      if (this.arr[this.size - 1 - i].equals(element)) {
        idx = i + 1;
        break;
      }
    }

    return idx;
  }

  @Override
  public int size() {
    return this.size;
  }

  boolean isEmpty() {
    return this.size() == 0;
  }
}

