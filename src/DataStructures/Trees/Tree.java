package DataStructures.Trees;

import DataStructures.Collection;

public interface Tree<E extends Comparable<E>> extends Collection {

  class Node<E> {

    E value;
    Node<E> left;
    Node<E> right;
    int height;

    Node(E value) {
      this.value = value;
      this.left = null;
      this.right = null;
      this.height = 0;
    }

    boolean isLeaf() {
      return this.left == null && this.right == null;
    }
    int getLeftHeight() { return (this.left != null) ? this.left.height : -1; }
    int getRightHeight() { return (this.right != null) ? this.right.height : -1; }
    int getBalance() { return getLeftHeight() - getRightHeight(); }
    void updateHeight() { this.height = Math.max(getLeftHeight(), getRightHeight()) + 1; }
  }

  boolean add(E element);

  boolean remove(E element);

  boolean contains(E element);
}
