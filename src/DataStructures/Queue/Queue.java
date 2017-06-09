package DataStructures.Queue;

import DataStructures.Collection;

public interface Queue<E> extends Collection {

  boolean add(E element);

  E poll();

  E peek();
}
