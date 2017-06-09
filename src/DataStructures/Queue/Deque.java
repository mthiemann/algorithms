package DataStructures.Queue;

public interface Deque<E> extends Queue<E> {

  boolean addFirst(E element);

  boolean addLast(E element);

  E pollFirst();

  E pollLast();

  E peekFirst();

  E peekLast();
}
