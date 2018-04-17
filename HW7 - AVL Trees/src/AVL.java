import java.util.Collection;

/**
 * Your implementation of an AVL Tree.
 *
 * @author Kedar Rao
 * @userid Kedar Rao
 * @GTID 902842074
 * @version 1.0
 */
public class AVL<T extends Comparable<? super T>> implements AVLInterface<T> {
    // DO NOT ADD OR MODIFY INSTANCE VARIABLES.
    private AVLNode<T> root;
    private int size;

    /**
     * A no argument constructor that should initialize an empty AVL tree.
     * DO NOT IMPLEMENT THIS CONSTRUCTOR!
     */
    public AVL() {
    }

    /**
     * Initializes the AVL tree with the data in the Collection. The data
     * should be added in the same order it is in the Collection.
     *
     * @param data the data to add to the tree
     * @throws IllegalArgumentException if data or any element in data is null
     */
    public AVL(Collection<T> data) {
        if (data == null) {
            throw new IllegalArgumentException("The data collection is null");
        }
        for (T entry : data) {
            add(entry);
        }
    }

    @Override
    public void add(T data) {
        if (data == null) {
            throw new IllegalArgumentException("The data cannot be null");
        }

        root = add(data, root);
    }


    /**
     * Helper method for add function that recursively goes through the tree
     * to find where to add the data
     *
     * @param data data to add to
     * @param node current node in recursive call
     * @return root node with the data added
     */
    private AVLNode<T> add(T data, AVLNode<T> node) {

        if (node == null) {
            size++;
            return new AVLNode<>(data);
        } else if (data.compareTo(node.getData()) < 0) {
            node.setLeft(add(data, node.getLeft()));
        } else if (data.compareTo(node.getData()) > 0) {
            node.setRight(add(data, node.getRight()));
        } else {
            return node;
        }

        updateHeightandBF(node);
        return rebalanceAVLTree(node);
    }

    /**
     * Helper method that goes through the tree and updates the height and the
     * balance factor of each node
     *
     * @param node current node in recursive call
     */
    private void updateHeightandBF(AVLNode<T> node) {
        int leftChildHeight = (node.getLeft() == null) ? -1
                : node.getLeft().getHeight();
        int rightChildHeight = (node.getRight() == null) ? -1
                : node.getRight().getHeight();
        node.setHeight(Math.max(leftChildHeight, rightChildHeight) + 1);
        node.setBalanceFactor(leftChildHeight - rightChildHeight);
    }

    /**
     * Helper method that finds out what kind of rotation must be implemented
     * to re-balance the tree
     *
     * @param node current node in recursive call
     * @return the rotated parent node of the subtree
     */
    private AVLNode<T> rebalanceAVLTree(AVLNode<T> node) {
        if (node.getBalanceFactor() < -1) {
            if (node.getRight().getBalanceFactor() <= 0) {
                node = leftRotation(node);
            } else {
                node = rightLeftRotation(node);
            }
        } else if (node.getBalanceFactor() > 1) {
            if (node.getLeft().getBalanceFactor() >= 0) {
                node = rightRotation(node);
            } else {
                node = leftRightRotation(node);
            }
        }
        return node;
    }


    @Override
    public T remove(T data) {
        if (data == null) {
            throw new IllegalArgumentException("The data cannot be empty");
        }

        AVLNode<T> removedNode = new AVLNode<>(null);
        root = remove(data, root, removedNode);
        return removedNode.getData();
    }

    /**
     * Helper method for remove method that recursively goes through the tree
     * to find out if the tree has the data
     *
     * @param data data to remove
     * @param node current node in recursive call
     * @param removedNode the removed node to return
     * @throws java.util.NoSuchElementException if the data is not in the tree
     * @return removed node of the new tree
     */
    private AVLNode<T> remove(T data, AVLNode<T> node, AVLNode<T> removedNode) {
        if (node == null) {
            throw new java.util.NoSuchElementException("The data is not in "
                    + "the tree.");
        }

        if (data.compareTo(node.getData()) < 0) {
            node.setLeft(remove(data, node.getLeft(), removedNode));
        } else if (data.compareTo(node.getData()) > 0) {
            node.setRight(remove(data, node.getRight(), removedNode));
        } else {
            removedNode.setData(node.getData());
            size--;
            if (node.getLeft() == null && node.getRight() == null) {
                return null;
            } else if (node.getLeft() == null) {
                return node.getRight();
            } else if (node.getRight() == null) {
                return node.getLeft();
            } else {
                AVLNode<T> successor = new AVLNode<>(null);
                node.setRight(findSuccessor(node.getRight(), successor));
                node.setData(successor.getData());
            }
        }
        updateHeightandBF(node);
        return rebalanceAVLTree(node);
    }

    /**
     * Find the successor - left most child of right child of node and
     * store that node in successor node.
     *
     * @param node left child of removed node to find successor
     * @param successor store successor node of the given node
     * @return successor child
     */
    private AVLNode<T> findSuccessor(AVLNode<T> node, AVLNode<T> successor) {

        if (node.getLeft() == null) {
            successor.setData(node.getData());
            return node.getRight();
        } else {
            node.setLeft(findSuccessor(node.getLeft(), successor));
        }
        updateHeightandBF(node);
        return rebalanceAVLTree(node);
    }

    /**
     * Helper method to do a left rotation of the sub tree
     * and rebalance the tree
     *
     * @param node current node in recursive call
     * @return the rotated sub tree
     */
    private AVLNode<T> leftRotation(AVLNode<T> node) {

        AVLNode<T> newParent = node.getRight();
        node.setRight(newParent.getLeft());
        newParent.setLeft(node);
        updateHeightandBF(node);
        updateHeightandBF(newParent);
        return newParent;
    }

    /**
     * Helper method to do a right rotation of the sub tree
     * and rebalance the tree
     *
     * @param node current node in recursive call
     * @return the rotated sub tree
     */
    private AVLNode<T> rightRotation(AVLNode<T> node) {
        AVLNode<T> newParent = node.getLeft();
        node.setLeft(newParent.getRight());
        newParent.setRight(node);
        updateHeightandBF(node);
        updateHeightandBF(newParent);
        return newParent;
    }

    /**
     * Helper method to do a left-right rotation of the sub tree
     * and rebalance the tree
     *
     * @param node current node in recursive call
     * @return the rotated sub tree
     */
    private AVLNode<T> leftRightRotation(AVLNode<T> node) {
        node.setLeft(leftRotation(node.getLeft()));
        return rightRotation(node);
    }

    /**
     * Helper method to do a right-left rotation of the sub tree
     * and rebalance the tree
     *
     * @param node current node in recursive call
     * @return the rotated sub tree
     */
    private AVLNode<T> rightLeftRotation(AVLNode<T> node) {
        node.setRight(rightRotation(node.getRight()));
        return leftRotation(node);
    }


    @Override
    public T get(T data) {
        if (data == null) {
            throw new IllegalArgumentException("The data cannot be empty");
        }

        return get(data, root);
    }

    /**
     * Helper method for get method that recursively goes through the tree
     * to find where to get the data
     *
     * @param data data to get
     * @param node current node in recursive call
     * @throws java.util.NoSuchElementException if the data is not in the tree
     * @return the data of the root node
     */
    private T get(T data, AVLNode<T> node) {
        if (node == null) {
            throw new java.util.NoSuchElementException("The data is not "
                    + "in the tree.");
        }

        if (data.equals(node.getData())) {
            return node.getData();
        } else if (data.compareTo(node.getData()) < 0) {
            return get(data, node.getLeft());
        } else {
            return get(data, node.getRight());
        }
    }

    @Override
    public boolean contains(T data) {
        if (data == null) {
            throw new IllegalArgumentException("The data cannot be null");
        }

        return contains(data, root);

    }

    /**
     * Helper method for contains method that recursively goes through the tree
     * to find out if the tree has the data
     *
     * @param data data to look for
     * @param node current node in recursive call
     * @return true if the tree has the data
     */
    private boolean contains(T data, AVLNode<T> node) {
        if (node == null) {
            return false;
        } else if (data.equals(node.getData())) {
            return true;
        } else if (data.compareTo(node.getData()) < 0) {
            return contains(data, node.getLeft());
        } else {
            return contains(data, node.getRight());
        }
    }

    @Override
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }

    @Override
    public T getSecondLargest() {
        if (size < 2) {
            throw new java.util.NoSuchElementException("There isn't enough "
                    + "data in the tree to find second largest!");
        }

        if (root.getRight() == null) {
            return root.getLeft().getData();
        }
        return getSecondLargest(root, root.getRight());
    }

    /**
     * Helper method for add function that recursively goes through the tree
     * to find the second largest data.
     *
     * @param previousNode the parent node of the current node
     * @param currentNode the current largest node of the tree
     * @return the second largest node data
     */
    private T getSecondLargest(AVLNode<T> previousNode,
                               AVLNode<T> currentNode) {
        if (currentNode.getRight() == null) {
            if (currentNode.getLeft() != null
                    && currentNode.getLeft().getData()
                        .compareTo(previousNode.getData()) > 0) {
                return currentNode.getLeft().getData();
            }
            return previousNode.getData();
        } else {
            previousNode = currentNode;
            currentNode = currentNode.getRight();
            return getSecondLargest(previousNode, currentNode);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof AVL) {
            AVL otherAVL = (AVL) obj;
            return equals(root, otherAVL.root);
        } else {
            return false;
        }
    }

    /**
     * Helper method for contains method that recursively goes through the tree
     * to find out if the tree has the data
     *
     * @param origNode node of original avl tree
     * @param otherNode node of the other avl tree
     * @return true if both trees have the same data
     */
    public boolean equals(AVLNode<T> origNode, AVLNode<T> otherNode) {
        if (origNode == null) {
            return otherNode == null;
        } else {
            return origNode.getData().equals(otherNode.getData())
                    && equals(origNode.getLeft(), otherNode.getLeft())
                    && equals(origNode.getRight(), otherNode.getRight());
        }
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    @Override
    public int height() {
        if (root != null) {
            return root.getHeight();
        } else {
            return -1;
        }
    }

    @Override
    public AVLNode<T> getRoot() {
        // DO NOT MODIFY THIS METHOD!
        return root;
    }
}
