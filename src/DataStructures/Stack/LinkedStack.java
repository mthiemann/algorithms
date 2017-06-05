package DataStructures.Stack;

public class LinkedStack implements Stack {

  private Node top;
  private int size;

  private class Node {
    private Object element;
    private Node child;

    Node() { this(null, null); }

    Node(Object element, Node child) {
      this.element = element;
      this.child = child;
    }
  }

  public LinkedStack() {
    this.top = new Node();
    this.size = 0;
  }

  @Override
  public Object push(Object element) {

    if (this.top.element == null) {
      this.top.element = element;
    } else {
      this.top = new Node(element, this.top);
    }

    this.size++;

    return element;
  }

  @Override
  public Object peek() {
    return this.top.element;
  }

  @Override
  public Object pop() {
    Object element = this.top.element;

    if (this.top.child != null) {
      this.top = this.top.child;
    } else {
      this.top.element = null;
    }

    if (element != null) this.size--;

    return element;
  }

  @Override
  public int search(Object element) {

    Node n = this.top;
    boolean notFound = true;
    int idx = 1;

    while (n != null && n.element != null) {
      if (n.element.equals(element)) {
        notFound = false;
        break;
      }
      n = n.child;
      idx++;
    }

    if (notFound) idx = -1;

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
