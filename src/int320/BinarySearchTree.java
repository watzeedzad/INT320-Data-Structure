/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package int320;

import java.util.Collections;
import java.util.LinkedList;

/**
 *
 * @author User
 */
public class BinarySearchTree<K extends Comparable<K>, V> {

    private Entry<K, V> root;

    public int Size() {
        return getSize(root);
    }

    private int getSize(Entry<K, V> x) {
        if (x == null) {
            return 0;
        } else {
            return 1 + getSize(x.leftChild) + getSize(x.rightChild);
        }
    }

    public V remove(K key) {
        if (key == null) {
            throw new NullPointerException("Key to delete is null");
        }
        Entry<K, V> tmp = get(root, key);
        return remove(tmp);
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
        //return get(root, key).value;
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

    public V findMax() {
        if (root == null) {
            return null;
        } else {
            return root.findMax(root).value;
        }
    }

    public V find2ndMin() {
        if (root == null) {
            return null;
        } else {
            return root.find2ndMin(root).value;
        }
    }

    public V find2ndMax() {
        if (root == null) {
            return null;
        } else {
            return root.find2ndMax(root).value;
        }
    }
    
    public void put(K key, V value) {
        if (key == null) {
            throw new NullPointerException("Key is null");
        }
        root = add(root, key, value, null);
    }

    public void reverseBreathFirst() {
        root.reverseBreathFirst(root);
    }

    public void breathFirst() {
        root.breathFirst(root);
        //System.out.println("\b\b\b\n");
    }

    public void preOrder() {
        root.preOrder(root);
        System.out.println("\b\b\b\n");
    }

    public void inOrder() {
        root.inOrder(root);
        System.out.println("\b\b\b\n");
    }

    public void postOrder() {
        root.postOrder(root);
        System.out.println("\b\b\b\n");
    }

    private Entry<K, V> add(Entry<K, V> x, K key, V value, Entry<K, V> parent) {
        if (x == null) {
            return new Entry(key, value, parent);
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            x.leftChild = add(x.leftChild, key, value, x);
        } else if (cmp > 0) {
            x.rightChild = add(x.rightChild, key, value, x);
        } else {
            x.value = value;
        }
        return x;
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

        @Override
        public String toString() {
            return "(" + key + " : " + value + ")";
        }

        //ส่ง node ไหนมา breathFirst ที่ Node นั้น
        public void breathFirst(Entry<K, V> x) {
            LinkedList<Entry<K, V>> q = new LinkedList();
            q.add(x);
            while (!q.isEmpty()) {
                Entry<K, V> temp = q.remove();
                System.out.print(temp + ", ");
                if (temp.leftChild != null) {
                    q.add(temp.leftChild);
                }
                if (temp.rightChild != null) {
                    q.add(temp.rightChild);
                }
            }
            System.out.println("\b\b\b\n");
        }

        public void reverseBreathFirst(Entry<K, V> x) {
            LinkedList<Entry<K, V>> q = new LinkedList();
            LinkedList stack = new LinkedList();
            q.add(x);
            while (!q.isEmpty()) {
                Entry<K, V> temp = q.remove();
                stack.add(temp);
                if (temp.leftChild != null) {
                    q.add(temp.leftChild);
                }
                if (temp.rightChild != null) {
                    q.add(temp.rightChild);
                }
            }
            Collections.reverse(stack);
            while (!stack.isEmpty()) {
                System.out.print(stack.pop() + ", ");
            }
            System.out.println("\b\b\b\n");
        }

        public void preOrder(Entry<K, V> x) {
            if (x != null) {
                System.out.print(x + ", ");
                preOrder(x.leftChild);
                preOrder(x.rightChild);
            }
        }

        public void inOrder(Entry<K, V> x) {
            if (x != null) {
                preOrder(x.leftChild);
                System.out.print(x + ", ");
                preOrder(x.rightChild);
            }
        }

        public void postOrder(Entry<K, V> x) {
            if (x != null) {
                preOrder(x.leftChild);
                preOrder(x.rightChild);
                System.out.print(x + ", ");
            }
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
        
        public Entry<K, V> find2ndMin(Entry<K, V> x) {
            if (x.leftChild == null) {
                return x.parent;
            } else {
                return find2ndMin(x.leftChild);
            }
        }

        public Entry<K, V> find2ndMax(Entry<K, V> x) {
            if (x.rightChild == null) {
                return x.parent;
            } else {
                return find2ndMax(x.rightChild);
            }
        }
    }
}
