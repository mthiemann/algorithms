package DataStructures.Trees;

/**
 * A binary search tree implementation without duplicate elements.
 * @param <E>
 */
public class BST<E extends Comparable<E>> implements Tree<E> {

  private Node<E> root;
  private int size;

  private class Node<E> {

    private E value;
    private Node<E> left;
    private Node<E> right;

    public Node(E value) {
      this(value, null, null);
    }

    public Node(E value, Node<E> left, Node<E> right) {
      this.value = value;
      this.left = left;
      this.right = right;
    }

    public boolean isLeaf() {
      return this.left == null && this.right == null;
    }
  }

  public BST() {
    this.root = null;
    this.size = 0;
  }

  public void add(E value) {
    add(value, this.root, null, false);
  }

  private void add(E value, Node<E> node, Node<E> parent, boolean left) {

    if (node == null) {
      this.size++;
      Node<E> nodeToInsert = new Node<E>(value);
      if (parent == null) {
        this.root = nodeToInsert;
      } else {
        if (left) {
          parent.left = nodeToInsert;
        } else {
          parent.right = nodeToInsert;
        }
      }
      return;
    }

    int comparison = value.compareTo(node.value);
    if (comparison > 0) {
      add(value, node.right, node, false);
    } else if (comparison < 0) {
      add(value, node.left, node, true);
    }
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
    if (this.root == null) return false;

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

      if (nextNode == null) {
        return node.value;
      } else {
        node = nextNode;
      }
    }
  }
}
