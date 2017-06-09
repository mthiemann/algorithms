package DataStructures.Heap;

import DataStructures.Collection;

public interface Heap<E extends Comparable<E>> extends Collection<E> {

  void push(E element);

  E poll();

  E peek();

  boolean remove(E element);
}
