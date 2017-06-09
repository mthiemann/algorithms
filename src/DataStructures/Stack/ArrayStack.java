package DataStructures.Stack;

import java.util.Arrays;

@SuppressWarnings("unchecked")
public class ArrayStack<E> implements Stack<E> {

  private Object[] arr;
  private int size;

  ArrayStack() {
    this.arr = new Object[10];
    this.size = 0;
  }

  @Override
  public E push(E element) {
    if (this.size == this.arr.length) this.resizeArr();

    this.arr[this.size++] = element;
    return element;
  }

  private void resizeArr() {
    this.arr = Arrays.copyOf(this.arr, this.arr.length << 1);
  }

  @Override
  public E peek() {
    return (E)this.arr[this.size - 1];
  }

  @Override
  public E pop() {

    if (this.isEmpty()) return null;

    Object o = this.arr[this.size - 1];
    this.arr[this.size - 1] = null;
    this.size--;
    return (E)o;
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

  @Override
  public boolean isEmpty() {
    return this.size() == 0;
  }

  @Override
  public void clear() {
    for (int i = 0; i < this.size; i++) {
      this.arr[i] = null;
    }

    this.size = 0;
  }
}

