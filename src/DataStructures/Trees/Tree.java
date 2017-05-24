package DataStructures.Trees;

public interface Tree<E extends Comparable<E>> {

  void add(E element);

  boolean remove(E element);

  boolean contains(E element);

  int size();

  // possible extensions: toArray
}
