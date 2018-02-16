import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Your implementation of a binary search tree.
 *
 * @author Kedar Rao
 * @userid krao9
 * @GTID 902842074
 * @version 1.0
 */
public class BST<T extends Comparable<? super T>> implements BSTInterface<T> {
    // DO NOT ADD OR MODIFY INSTANCE VARIABLES.
    private BSTNode<T> root;
    private int size;

    /**
     * A no-argument constructor that should initialize an empty BST.
     * DO NOT IMPLEMENT THIS CONSTRUCTOR!
     */
    public BST() {
    }

    /**
     * Initializes the BST with the data in the Collection. The data
     * should be added in the same order it is in the Collection.
     *
     * @param data the data to add to the tree
     * @throws IllegalArgumentException if data or any element in data is null
     */
    public BST(Collection<T> data) {
        if (data == null) {
            throw new IllegalArgumentException("The data cannot be empty");
        }
        for (T d: data) {
            add(d);
        }
    }

    @Override
    public void add(T data) {
        if (data == null) {
            throw new IllegalArgumentException("The data cannot be empty");
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
    private BSTNode<T> add(T data, BSTNode<T> node) {
        if (node == null) {
            size++;
            return new BSTNode<>(data);
        } else if (data.compareTo(node.getData()) < 0) {
            node.setLeft(add(data, node.getLeft()));
        } else if (data.compareTo(node.getData()) > 0) {
            node.setRight(add(data, node.getRight()));
        }
        return node;
    }

    @Override
    public T remove(T data) {
        if (data == null) {
            throw new IllegalArgumentException("The data cannot be empty");
        }
        BSTNode<T> removedNode = new BSTNode<>(null);
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
    private BSTNode<T> remove(T data, BSTNode<T> node, BSTNode<T> removedNode) {
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
                node = null;
            } else if (node.getLeft() == null) {
                node =  node.getRight();
            } else if (node.getRight() == null) {
                node = node.getLeft();
            } else {
                BSTNode<T> predecessor = new BSTNode<>(null);
                node.setLeft(findPredecessor(node.getLeft(), predecessor));
                node.setData(predecessor.getData());
            }
        }
        return node;
    }

    /**
     * Find the predecessor - right most child of left child of node and
     * store that node in predecessor node.
     *
     * @param node left child of removed node to find predecessor
     * @param predecessor store predecessor node of the given node
     * @return predecessor child
     */
    private BSTNode<T> findPredecessor(BSTNode<T> node,
                                       BSTNode<T> predecessor) {
        if (node.getRight() == null) {
            predecessor.setData(node.getData());
            node = node.getLeft();
        } else {
            node.setRight(findPredecessor(node.getRight(), predecessor));
        }
        return node;
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
    private T get(T data, BSTNode<T> node) {
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
            throw new IllegalArgumentException("The data cannot be empty");
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
    private boolean contains(T data, BSTNode<T> node) {
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
    public List<T> preorder() {
        List<T> preOrderedList = new ArrayList<>(size);
        preorder(preOrderedList, root);
        return preOrderedList;
    }

    /**
     * Helper method for inorder method that recursively goes through the tree
     * adds preorder elements to the list
     *
     * @param data the list of data of the tree
     * @param node current node in recursive call
     *
     */
    private void preorder(List<T> data, BSTNode<T> node) {
        if (node != null) {
            data.add(node.getData());
            preorder(data, node.getLeft());
            preorder(data, node.getRight());
        }
    }

    @Override
    public List<T> postorder() {
        List<T> postOrderedList = new ArrayList<>(size);
        postorder(postOrderedList, root);
        return postOrderedList;
    }

    /**
     * Helper method for inorder method that recursively goes through the tree
     * adds postorder elements to the list
     *
     * @param data the list of data of the tree
     * @param node current node in recursive call
     *
     */
    private void postorder(List<T> data, BSTNode<T> node) {
        if (node != null) {
            postorder(data, node.getLeft());
            postorder(data, node.getRight());
            data.add(node.getData());
        }
    }

    @Override
    public List<T> inorder() {
        List<T> inOrderedList = new ArrayList<>(size);
        inorder(inOrderedList, root);
        return inOrderedList;
    }

    /**
     * Helper method for inorder method that recursively goes through the tree
     * adds inorder elements to the list
     *
     * @param data the list of data of the tree
     * @param node current node in recursive call
     */
    private void inorder(List<T> data, BSTNode<T> node) {
        if (node != null) {
            inorder(data, node.getLeft());
            data.add(node.getData());
            inorder(data, node.getRight());
        }
    }

    @Override
    public List<T> levelorder() {
        List<T> levelOrderedList = new ArrayList<>(size);
        Queue<BSTNode<T>> queue = new LinkedList<>();
        queue.add(root);
        BSTNode<T> node;
        while (queue.size() > 0) {
            node = queue.poll();
            if (node != null) {
                levelOrderedList.add(node.getData());
                System.out.println(node.getData());
                queue.add(node.getLeft());
                queue.add(node.getRight());
            }
        }
        return levelOrderedList;
    }

    @Override
    public int distanceBetween(T data1, T data2) {
        if (data1 == null || data2 == null) {
            throw new IllegalArgumentException("The data cannot be empty");
        }

        BSTNode<T> commonAncestor = findCommonAncestor(data1, data2, root);
        return distanceFrom(data1, commonAncestor)
                + distanceFrom(data2, commonAncestor);
    }

    /**
     * Helper method for distanceBetween method that recursively goes through
     * the tree to find the common ancestor between two data elements
     * One must be less than and one must be greater than the node data
     *
     * @param data1 data1 to look for
     * @param data2 data2 to look for
     * @param node current node in recursive call
     * @throws java.util.NoSuchElementException if the data is not in the tree
     * @return the common ancestor of the two data points
     */
    private BSTNode<T> findCommonAncestor(T data1, T data2, BSTNode<T> node) {
        if (node == null) {
            throw new java.util.NoSuchElementException("Both data elements "
                    + "are not in the tree");
        }
        if (data1.compareTo(node.getData()) < 0
                && data2.compareTo(node.getData()) < 0) {
            return findCommonAncestor(data1, data2, node.getLeft());
        } else if (data1.compareTo(node.getData()) > 0
                && data2.compareTo(node.getData()) > 0) {
            return findCommonAncestor(data1, data2, node.getRight());
        } else {
            return node;
        }
    }

    /**
     * Helper method for contains method that recursively goes through the tree
     * to find the distance between a data element and a node
     *
     * @param data data1 to look for
     * @param node current node in recursive call
     * @throws java.util.NoSuchElementException if the data is not in the tree
     * @return the distance from data to node with data
     */
    private int distanceFrom(T data, BSTNode<T> node) {
        if (node == null) {
            throw new java.util.NoSuchElementException("The data: " + data
                + " is not in the tree.");
        }
        if (data.compareTo(node.getData()) < 0) {
            return distanceFrom(data, node.getLeft()) + 1;
        } else if (data.compareTo(node.getData()) > 0) {
            return distanceFrom(data, node.getRight()) + 1;
        } else {
            return 0;
        }
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    @Override
    public int height() {
        return height(root);
    }

    /**
     * Helper method for inorder method that recursively goes through the tree
     * to find the height of the node
     *
     * @param node the list of data of the tree
     * @return an int that gives the height of the node
     */
    private int height(BSTNode<T> node) {
        if (node == null) {
            return -1;
        }
        return Math.max(height(node.getLeft()), height(node.getRight())) + 1;
    }

    @Override
    public int size() {
        // DO NOT MODIFY THIS METHOD
        return size;
    }

    @Override
    public BSTNode<T> getRoot() {
        // DO NOT MODIFY THIS METHOD!
        return root;
    }
}
