/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avl;

import java.util.LinkedList;

/**
 *
 * @author int320
 */
public class AVLTree<K extends Comparable<K>, V> {

    protected Entry<K, V> root;

    public int getHeight() {
        return root.getHeight(root);
    }

    public int size() {
        return getSize(root);
    }

    private int getSize(Entry<K, V> x) {
        if (x == null) {
            return 0;
        } else {
            return 1 + getSize(x.leftChild) + getSize(x.rightChild);
        }
    }

//    public V remove(K key) {
//        if (key == null) {
//            throw new NullPointerException("Key to delete is null");
//        }
//        Entry<K, V> tmp = get(root, key);
//        return remove(tmp);
//    }

    public void remove(K key) {
        if (key == null) {
            throw new NullPointerException("Key to delete is null");
        }
        root = remove(root, key, null);
    }

    private Entry<K, V> remove(Entry<K, V> node, K key, Entry<K, V> parent) {
        if (node == null) {
            return null;
        }
        int comp = key.compareTo(node.key);
        if (comp < 0) {
            node.leftChild = remove(node.leftChild, key, node);
            int balanceFactor = node.getHeight(node.leftChild) - node.getHeight(node.rightChild);
            if (balanceFactor < -1) {
                int unbalanceCase = node.getHeight(node.rightChild.leftChild) - node.getHeight(node.rightChild.rightChild)  ;
                if (unbalanceCase <= 0 ) { // alpha R R
                    node = singleLeftRotate(node);
                } else {
                    node = doubleRightLeftRotate(node);   // alpha  L R
                }
            }
        } else if (comp > 0) {
            node.rightChild = remove(node.rightChild, key, node);
            int balanceFactor = node.getHeight(node.leftChild) - node.getHeight(node.rightChild);
            if (balanceFactor > 1) {
                int unbalanceCase = node.getHeight(node.leftChild.leftChild) - node.getHeight(node.leftChild.rightChild)  ;
                if (unbalanceCase >=0) { // alpha L L
                    node = singleRightRotate(node);
                } else {
                    node = doubleLeftRightRotate(node);   //alpha R L
                }
            }
        } else if (node.leftChild != null && node.rightChild != null) {  // 2 childrens
            if (node.getHeight(node.leftChild) > node.getHeight(node.rightChild)) {
                Entry<K, V> tmp = node.findMax(node.leftChild);
                node.key = tmp.key;
                node.value = tmp.value;
                node.leftChild = remove(node.leftChild, tmp.key, node);
            } else {
                Entry<K, V> tmp = node.findMin(node.rightChild);
                node.key = tmp.key;
                node.value = tmp.value;
                node.rightChild = remove(node.rightChild, tmp.key, node);
            }
        } else {
            Entry<K, V> tmp = node;
            if (tmp.leftChild != null) {
                node = node.leftChild;
                tmp.leftChild.parent = parent;
            } else {
                node = node.rightChild;
                if (tmp.rightChild != null) {
                    tmp.rightChild.parent = parent;
                }
            }
        }
        return node;
    }

    private V remove(Entry<K, V> x) {
        if (x == null) {
            return null;
        }
        V value = x.value;
        if (x.leftChild != null && x.rightChild != null) { // delete node which has 2 child ;
            Entry<K, V> minNode = x.findMin(x.rightChild);
            x.value = minNode.value;
            x.key = minNode.key;
            remove(minNode);
        } else if (x.leftChild != null) {  // delete node which has left child only
            if (x.parent != null) {  // node to be delete is not root 
                if (x.parent.leftChild == x) {
                    x.parent.leftChild = x.leftChild;
                } else {
                    x.parent.rightChild = x.leftChild;
                }
            }
            x.leftChild.parent = x.parent;
        } else if (x.rightChild != null) {  // delete node which has right child only
            if (x.parent != null) {  // node to be delete is not root 
                if (x.parent.leftChild == x) {
                    x.parent.leftChild = x.rightChild;
                } else {
                    x.parent.rightChild = x.rightChild;
                }
            }
            x.rightChild.parent = x.parent;

        } else // delete leaf node
         if (x.parent != null) {  // node to be delete is not root 
                if (x.parent.leftChild == x) {
                    x.parent.leftChild = null;
                } else {
                    x.parent.rightChild = null;
                }
            } else {
                root = null;
            }
        return value;
    }

    public V get(K key) {
        Entry<K, V> tmp = get(root, key);
        return tmp == null ? null : tmp.value;
    }

    private Entry<K, V> get(Entry<K, V> x, K key) {
        if (x == null) {
            return null;
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            return get(x.leftChild, key);
        } else if (cmp > 0) {
            return get(x.rightChild, key);
        } else {
            return x;
        }
    }

    public V findMin() {
        if (root == null) {
            return null;
        } else {
            return root.findMin(root).value;
        }
    }

    public void put(K key, V value) {
        if (key == null) {
            throw new NullPointerException("Key is null");
        }
        root = add(root, key, value, null);
    }

    public void breathFirst() {
        root.breathFirst(root);
    }

    public void inOrder() {
        root.inOrder(root);
        System.out.println("\b\b\b\n");
    }

    public void postOrder() {
        root.postOrder(root);
        System.out.println("\b\b\b\n");
    }

    public void preOrder() {
        root.preOrder(root);
        System.out.println("\b\b\b\n");
    }

    protected Entry<K, V> add(Entry<K, V> x, K key, V value, Entry<K, V> parent) {
        if (x == null) {
            return new Entry(key, value, parent);
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            x.leftChild = add(x.leftChild, key, value, x);
            int balanceFactor = x.getHeight(x.leftChild) - x.getHeight(x.rightChild);
            if (balanceFactor > 1) {
                System.out.println("Must be rotate at " + x);
                int unbalanceCase = x.getHeight(x.leftChild.leftChild) - x.getHeight(x.leftChild.rightChild);
                if (unbalanceCase >= 0) {
                    //System.out.println("Case: Alpha-L-L");
                    x = singleRightRotate(x);
                } else {
                    //System.out.println("Case: Alpha-R-L");
                    x = doubleLeftRightRotate(x);
                }
            }
        } else if (cmp > 0) {
            x.rightChild = add(x.rightChild, key, value, x);
            int balanceFactor = x.getHeight(x.leftChild) - x.getHeight(x.rightChild);
            if (balanceFactor < -1) {
                System.out.println("Must be rotate at " + x);
                int unbalanceCase = x.getHeight(x.rightChild.leftChild) - x.getHeight(x.rightChild.rightChild);
                if (unbalanceCase >= 0) {
                    //System.out.println("Case: Alpha-L-R");
                    x = doubleRightLeftRotate(x);
                } else {
                    //System.out.println("Case: Alpha-R-R");
                    x = singleLeftRotate(x);
                }
            }
        } else {
            x.value = value;
        }
        return x;
    }

    private Entry<K, V> singleRightRotate(Entry<K, V> alpha) {
        Entry<K, V> oldLeft = alpha.leftChild;
        alpha.leftChild = oldLeft.rightChild;
        if (oldLeft.rightChild != null) {
            oldLeft.rightChild.parent = alpha;
        }
        oldLeft.rightChild = alpha;

        oldLeft.parent = alpha.parent;
        alpha.parent = oldLeft;

        return oldLeft;
    }

    private Entry<K, V> singleLeftRotate(Entry<K, V> alpha) {
        Entry<K, V> oldRight = alpha.rightChild;
        alpha.rightChild = oldRight.leftChild;
        if (oldRight.leftChild != null) {
            oldRight.leftChild.parent = alpha;
        }
        oldRight.leftChild = alpha;

        oldRight.parent = alpha.parent;
        alpha.parent = oldRight;

        return oldRight;
    }

//    private Entry<K, V> doubleLeftRightRotate(Entry<K, V> x) {
//        x.rightChild = singleRightRotate(x.rightChild) ;
//        return singleLeftRotate(x);
//    }
//
//    private Entry<K, V> doubleRightLeftRotate(Entry<K, V> x) {
//        x.leftChild = singleLeftRotate(x.leftChild) ;
//        return singleRightRotate(x) ;
//    }
    private Entry<K, V> doubleLeftRightRotate(Entry<K, V> alpha) {
        alpha.leftChild = singleLeftRotate(alpha.leftChild);
        return singleRightRotate(alpha);
    }

    private Entry<K, V> doubleRightLeftRotate(Entry<K, V> alpha) {
        alpha.rightChild = singleRightRotate(alpha.rightChild);
        return singleLeftRotate(alpha);
    }

    public class Entry<K, V> {

        K key;
        V value;
        Entry<K, V> parent;
        Entry<K, V> leftChild;
        Entry<K, V> rightChild;

        public Entry(K key, V value, Entry<K, V> parent) {
            this.key = key;
            this.value = value;
            this.parent = parent;
        }

        public Entry<K, V> findMin(Entry<K, V> x) {
            if (x.leftChild == null) {
                return x;
            } else {
                return findMin(x.leftChild);
            }
        }
        
        public Entry<K, V> findMax(Entry<K, V> x) {
            if (x.rightChild == null) {
                return x;
            } else {
                return findMax(x.rightChild);
            }
        }

        public void breathFirst(Entry<K, V> x) {
            LinkedList<Entry<K, V>> q = new LinkedList();
            q.add(x);
            while (!q.isEmpty()) {
                Entry<K, V> tmp = q.remove();
                System.out.print(tmp + "--> " + tmp.parent + " ,  ");
                if (tmp.leftChild != null) {
                    q.add(tmp.leftChild);
                }
                if (tmp.rightChild != null) {
                    q.add(tmp.rightChild);
                }
            }
            System.out.println("\b\b\b\n");
        }

        public void preOrder(Entry<K, V> x) {
            if (x != null) {
                System.out.print(x + ",  ");
                preOrder(x.leftChild);
                preOrder(x.rightChild);
            }
        }

        public void inOrder(Entry<K, V> x) {
            if (x != null) {
                inOrder(x.leftChild);
                System.out.print(x + ",  ");
                inOrder(x.rightChild);
            }
        }

        public void postOrder(Entry<K, V> x) {
            if (x != null) {
                postOrder(x.leftChild);
                postOrder(x.rightChild);
                System.out.print(x + ",  ");
            }
        }

        public int getHeight(Entry<K, V> entry) {
            if (entry == null) {
                return -1;
            }
            return 1 + Math.max(getHeight(entry.leftChild), getHeight(entry.rightChild));
        }

        @Override
        public String toString() {
            return "(" + key + " : " + value + ')';
        }

    }

}
