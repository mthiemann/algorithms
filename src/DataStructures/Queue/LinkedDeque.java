package DataStructures.Queue;

public class LinkedDeque<E> implements Deque<E> {

  private Node head;
  private Node tail;
  private int size;

  private class Node {
    E element;
    Node next;
    Node previous;

    Node() { this(null, null, null); }

    Node(E element, Node previous, Node next) {
      this.element = element;
      this.next = next;
      this.previous = previous;
    }
  }

  LinkedDeque(){
    Node n = new Node();
    this.head = n;
    this.tail = n;
    this.size = 0;
  }

  @Override
  public boolean add(E element) {

    // no elements in deque
    if (this.tail.element == null) {
      this.tail.element = element;
    } else {
      Node n = new Node(element, this.tail, null);
      this.tail.next = n;
      this.tail = this.tail.next;
    }

    this.size++;

    return true;
  }

  @Override
  public E poll() {

    if (this.head.element == null) return null;

    E element = this.head.element;
    Node nextHead = this.head.next;

    if (nextHead == null) {
      this.head.element = null;
    } else {
      this.head = nextHead;
      this.head.previous = null;
    }

    this.size--;

    return element;
  }

  @Override
  public E peek() {
    return this.head.element;
  }

  @Override
  public boolean addFirst(E element) {

    if (this.head.element == null) {
      this.head.element = element;
    } else {
      Node n = new Node(element, null, this.head);
      this.head.previous = n;
      this.head = n;
    }

    this.size++;

    return true;
  }

  @Override
  public boolean addLast(E element) {
    return this.add(element);
  }

  @Override
  public E pollFirst() {
    return this.poll();
  }

  @Override
  public E pollLast() {

    if (this.tail.element == null) return null;

    E element = this.tail.element;

    if (this.tail == this.head) {
      this.tail.element = null;
    } else {
      Node predecessor = this.tail.previous;
      predecessor.next = null;
      this.tail = predecessor;
    }

    this.size--;

    return element;
  }

  @Override
  public E peekFirst() {
    return this.peek();
  }

  @Override
  public E peekLast() {
    return this.tail.element;
  }

  @Override
  public int size() { return this.size; }

  @Override
  public boolean isEmpty() { return this.size == 0; }

  @Override
  public void clear() {
    while (this.size() > 0) {
      this.poll();
    }
  }
}
