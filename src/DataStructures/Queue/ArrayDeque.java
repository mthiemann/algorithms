package DataStructures.Queue;

@SuppressWarnings("unchecked")
public class ArrayDeque<E> implements Deque<E> {

  private Object[] arr;
  private int size;
  private int headIdx;

  ArrayDeque() {
    this.initArrayDeque();
  }

  private void initArrayDeque() {
    this.arr = new Object[10];
    this.size = 0;
    this.headIdx = 0;
  }

  @Override
  public boolean add(E element) {

    if (this.size == arr.length) this.resize();

    this.arr[(this.headIdx + this.size) % this.arr.length] = element;
    this.size++;

    return true;
  }

  @Override
  public E poll() {
    return this.poll(true);
  }

  @Override
  public E peek() { return (E)this.arr[this.headIdx]; }

  @Override
  public boolean addFirst(E element) {

    if (this.size == arr.length) this.resize();

    int insertionIdx = (((this.headIdx - 1) % this.arr.length) + this.arr.length) % this.arr.length;
    this.arr[insertionIdx] = element;
    this.headIdx = insertionIdx;
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
  public E pollLast() { return this.poll(false); }

  /**
   * Polls an element from the queue
   * @param head {boolean} True: head, False: tail
   * @return E
   */
  private E poll(boolean head) {

    if (this.isEmpty()) return null;

    int idx = (head) ? this.headIdx : this.tailIdx();
    E polledElement = (E)this.arr[idx];
    this.arr[idx] = null;

    if (head) this.headIdx = ++this.headIdx % this.arr.length;

    this.size--;

    return polledElement;
  }

  @Override
  public E peekFirst() {
    return this.peek();
  }

  @Override
  public E peekLast() {
    return (E)this.arr[this.tailIdx()];
  }

  @Override
  public int size() {
    return this.size;
  }

  @Override
  public boolean isEmpty() { return this.size == 0; }

  @Override
  public void clear() {
    this.initArrayDeque();
  }

  /**
   * Start at head idx and copy elements to new array
   */
  private void resize() {

    Object[] arr = new Object[this.arr.length << 1];

    for (int i = 0; i < this.size; i++) {
      arr[i] = this.arr[(this.headIdx + i) % this.arr.length];
    }

    this.arr = arr;
    this.headIdx = 0;
  }

  private int tailIdx() {
    return (this.isEmpty()) ? this.headIdx : (this.headIdx + this.size - 1) % this.arr.length;
  }
}
