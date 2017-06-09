package DataStructures.Trees;

/**
 * A binary search tree implementation without duplicate elements.
 * @param <E>
 */
public class BST<E extends Comparable<E>> implements Tree<E> {

  Node<E> root;
  int size;

  BST() {
    this.root = null;
    this.size = 0;
  }

  public boolean add(E value) {
    int previousSize = this.size();
    this.root = add(value, this.root);
    return this.size() > previousSize;
  }

  Node<E> add(E value, Node<E> node) {
    if (node == null) {
      node = new Node<E>(value);
      this.size++;
    } else {
      if (node.value.compareTo(value) > 0) {
        node.left = this.add(value, node.left);
      } else if (node.value.compareTo(value) < 0) {
        node.right = this.add(value, node.right);
      }
    }

    return node;
  }

  public boolean remove(E value) {
    boolean wasRemoved = remove(value, this.root, null, true);
    if (wasRemoved) this.size--;
    return wasRemoved;
  }

  private boolean remove(E value, Node<E> node, Node<E> parent, boolean left) {

    // base case: node is null
    if (node == null) return false;

    int comparison = value.compareTo(node.value);

    if (comparison > 0) {
      return remove(value, node.right, node, false);
    } else if (comparison < 0) {
      return remove(value, node.left, node, true);
    }

    // Node to remove was found
    Node<E> newReferenceForParent;

    // Case 1: Found node is a leaf
    if (node.isLeaf()) {
      newReferenceForParent = null;

    // Case 2: Found node has only 1 child
    } else if (node.left == null && node.right != null) {
      newReferenceForParent = node.right;
    } else if (node.left != null && node.right == null) {
      newReferenceForParent = node.left;
    } else {
      // Case 3: Found node has two subtrees

      // get max element of left subtree (min element of right subtree could also be used)
      newReferenceForParent = node.left;
      while (newReferenceForParent.right != null) {
        newReferenceForParent = newReferenceForParent.right;
      }

      newReferenceForParent.left = node.left;
      newReferenceForParent.right = node.right;
    }

    // assign new child reference to parent
    if (parent == null) {
      this.root = newReferenceForParent;
    } else if(left) {
      parent.left = newReferenceForParent;
    } else {
      parent.right = newReferenceForParent;
    }

    return true;
  }

  public boolean contains(E value) {
    Node<E> node = this.root;

    while (node != null) {
      int comparison = value.compareTo(node.value);

      if (comparison == 0) {
        return true;
      } else if (comparison < 0) {
        node = node.left;
      } else {
        node = node.right;
      }
    }

    return false;
  }

  public int size() {
    return this.size;
  }

  public E getMax() {
    if (this.root == null) return null;

    return this.getEdgeLeaf(true);
  }

  public E getMin() {
    if (this.root == null) return null;

    return this.getEdgeLeaf(false);
  }

  private E getEdgeLeaf(boolean right) {
    Node<E> node = this.root;
    Node<E> nextNode;

    while (true) {
      nextNode = (right) ? node.right : node.left;

      if (nextNode == null) return node.value;

      node = nextNode;
    }
  }
}
