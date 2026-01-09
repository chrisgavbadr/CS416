
/**
 * @author Christian Baduria
 * @version 1.0
 * @param <T> Generic type
 */
public class BST<T extends Comparable> implements Tree<T> {

    private Node root;
    private int size;

    /**
     * Constructor for creating BST object.
     */
    public BST() {
        root = null;
        size = 0;
    }

    /**
     * Returns root of BST.
     *
     * @return [Node] Root of BST
     */
    public Node getRoot() {
        return root;
    }

    /**
     * Appends the specified value to this tree. Does not allow duplicates.
     *
     * @param value T The value to add
     * @return boolean True if the value is inserted, false otherwise
     */
    @Override
    @SuppressWarnings("unchecked")
    public boolean add(T value) {
        if (root == null) {
            root = new Node(value);
            size++;
            return true;
        }
        Node current = root;
        while (current != null) {
            if (current.value.compareTo(value) > 0) {
                if (current.left == null) {
                    Node newNode = new Node(value);
                    current.left = newNode;
                    newNode.parent = current;
                    size++;
                    return true;
                }
                current = current.left;
            } else if (current.value.compareTo(value) < 0) {
                if (current.right == null) {
                    Node newNode = new Node(value);
                    current.right = newNode;
                    newNode.parent = current;
                    size++;
                    return true;
                }
                current = current.right;
            } else {
                return false;
            }
        }
        current = new Node(value);
        size++;
        return true;
    }

    /**
     * Removes all of the elements from this tree.
     */
    @Override
    public void clear() {
        root = null;
    }

    /**
     * Returns true if this tree contains the specified element.
     *
     * @param o Object The element to check if present in the tree
     * @return boolean
     */
    @Override
    @SuppressWarnings("unchecked")
    public boolean contains(Object o) {
        Node current = root;
        while (current != null) {
            if (current.value.compareTo(o) > 0) {
                current = current.left;
            } else if (current.value.compareTo(o) < 0) {
                current = current.right;
            } else {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the element that matches the object parameter in this tree.
     *
     * @param o Object to search the tree for matching elements.
     * @return T
     */
    @Override
    @SuppressWarnings("unchecked")
    public T get(Object o) {
        Node current = root;
        while (current != null) {
            if (current.value.compareTo(o) > 0) {
                current = current.left;
            } else if (current.value.compareTo(o) < 0) {
                current = current.right;
            } else {
                return current.value;
            }
        }
        return null;
    }

    /**
     * Remove a specific node from the tree. Decrease the size.
     *
     * @param n Node        node to be deleted
     */
    @SuppressWarnings("unchecked")
    public void removeNode(Node n) {
        if (n == root) {
            removeRoot();
        } else if (n.value.compareTo(n.parent.value) < 0) {
            removeLeft(n.parent, n);
        } else {
            removeRight(n.parent, n);
        }
        size--;
    }

    /**
     * Removes the first occurrence of the specified element from this
     * tree, if it is present.
     * If tree does not contain the element, it is unchanged.
     * Returns true if this tree contained the specified element
     * (or equivalently, if this tree changed as a result of the call).
     *
     * @param o element to be removed from this tree, if present
     * @return true if this tree contained the specified element
     */
    @Override
    @SuppressWarnings("unchecked")
    public boolean remove(Object o) {
        Node current = root;
        while (current != null) {
            if (current.value.compareTo(o) > 0) {
                current = current.left;
            } else if (current.value.compareTo(o) < 0) {
                current = current.right;
            } else {
                removeNode(current);
                return true;
            }
        }
        return false;
    }

    /**
     * Returns true if this tree contains no elements.
     *
     * @return true if this tree contains no elements
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the number of elements in this tree.
     *
     * @return int
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Add subtree Node as the right most descendant of the 1st argument.
     *
     * @param n       Node        root of tree to which subtree must be added
     * @param subtree Node  subtree to be added to far right of subtree
     */
    public void addToFarRight(Node n, Node subtree) {
        while (n.right != null) {
            n = n.right;
        }
        n.right = subtree;
        subtree.parent = n;
    }

    /**
     * Add subtree Node as the left most descendant of the 1st argument.
     *
     * @param n       Node        root of tree to which subtree must be added
     * @param subtree Node  subtree to be added to far left of subtree
     */
    public void addToFarLeft(Node n, Node subtree) {
        while (n.left != null) {
            n = n.left;
        }
        n.left = subtree;
        subtree.parent = n;
    }

    /**
     * Remove a node that is the right child of its parent.
     *
     * @param parent Node
     * @param n      Node
     */
    private void removeRight(Node parent, Node n) {
        if (n.left != null && n.right != null) {
            Node leftChild = n.left;
            Node rightChild = n.right;
            addToFarLeft(rightChild, leftChild);
            parent.right = rightChild;
            rightChild.parent = parent;
        } else if (n.left != null) {
            parent.right = n.left;
            n.left.parent = parent;
        } else if (n.right != null) {
            parent.right = n.right;
            n.right.parent = parent;
        } else {
            parent.right = null;
        }
    }

    /**
     * Remove a node that is the left child of its parent.
     *
     * @param parent Node
     * @param n      Node
     */
    private void removeLeft(Node parent, Node n) {
        if (n.left != null && n.right != null) {
            Node leftChild = n.left;
            Node rightChild = n.right;
            addToFarRight(leftChild, rightChild);
            parent.left = leftChild;
            leftChild.parent = parent;
        } else if (n.left != null) {
            parent.left = n.left;
            n.left.parent = parent;
        } else if (n.right != null) {
            parent.left = n.right;
            n.right.parent = parent;
        } else {
            parent.left = null;
        }
    }

    /**
     * Remove the root node.
     */
    private void removeRoot() {
        if (root.left != null && root.right != null) {
            Node leftChild = root.left;
            Node rightChild = root.right;
            addToFarRight(leftChild, rightChild);
            root = leftChild;
            root.parent = null;
        } else if (root.left != null) {
            root = root.left;
            root.parent = null;
        } else if (root.right != null) {
            root = root.right;
            root.parent = null;
        } else {
            root = null;
        }
    }

    /**
     * Generate a string representation of the tree.
     *
     * @return String       a string representation of the tree.
     */
    public String toString() {
        return toString(root, "  \t", "");
    }

    /**
     * Recursively generate a string representation for a Node of a tree;
     * indent is increased for increasing depth.
     * Branch is a short string that prefixes each node indicating
     * whether the node is a left (L) or right (R) child.
     *
     * @param n      Node         subtree to convert to string
     * @param indent String  prefix string for indentation level
     * @param branch String  (L) or (R)
     * @return String        string rep of subtree
     */
    private String toString(Node n, String indent, String branch) {
        String s = "";
        if (n != null) {
            s += branch + n.value.toString() + "\n";
            if (n.left != null) {
                s += toString(n.left, indent, "L ");
            }
            if (n.right != null) {
                s += toString(n.right, indent, "R ");
            }
        }
        return s;
    }

    /**
     * Inner class to represent a List node.
     */
    public class Node implements Tree.Node<T> {

        T value;
        Node left;
        Node right;
        Node parent;

        /**
         * Constructor for Node object.
         *
         * @param value [T] Value to be set.
         */
        public Node(T value) {
            this.value = value;
            this.left = null;
            this.right = null;
        }

        /**
         * Set the value of this node.
         *
         * @param value Value
         */
        @Override
        public void setValue(T value) {
            this.value = value;
        }

        /**
         * Get value of this node.
         *
         * @return [T] Value
         */
        @Override
        public T getValue() {
            return value;
        }

        /**
         * Return left child node.
         *
         * @return [Node] Left child node
         */
        public Node getLeft() {
            return left;
        }

        /**
         * Return right child node.
         *
         * @return [Node] Right child node
         */
        public Node getRight() {
            return right;
        }

        /**
         * Return parent node.
         *
         * @return [Node] Parent node
         */
        public Node getParent() {
            return parent;
        }
    }
}
