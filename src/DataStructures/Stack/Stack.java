package DataStructures.Stack;

import DataStructures.Collection;

public interface Stack<E> extends Collection<E> {

  E push(E element);

  E pop();

  E peek();

  int search(E element);
}
