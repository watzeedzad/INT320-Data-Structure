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
public class BinarySearchTreeAVL<K extends Comparable<K>, V> {

    protected Entry<K, V> root;   // เปลี่ยนเป็น protected เพื่อที่จะให้ access ได้
    // ต้องมี method check ความสูง เพื่อดูว่า balance ไหม

    public V get(K key) {
        Entry<K, V> temp = get(root, key);
        return temp == null ? null : temp.value;
    }

    public Entry<K, V> get(Entry<K, V> x, K key) {
        if (x == null) {
            return null;
        }
        int compare = key.compareTo(x.key);
        if (compare < 0) {
            return get(x.leftChild, key); // เลื่อน node ไปทางซ้าย
        } else if (compare > 0) {
            return get(x.rightChild, key);
        } else {
            return x;
        }
    }

    public int size() {
        return getSize(root);
    }

    private int getSize(Entry<K, V> x) {
        if (x == null) {
            return 0;
        } else {
            return 1 + getSize(x.leftChild) + getSize(x.rightChild);
            // มันจะ return 1 แล้วก็ + เรื่อยๆ
        }
    }

    public void breathFirst() { // คือการวิ่งแนวระดับจาก ระดับ 1 ซ้ายไปขวา 2 ซ้ายไปขวา
        root.breathFirst(root); // เรียกจาก entry ทำให้เวลาจะเรียก จะเรียกที่ node ไหนก็ได้
    }

    public void inOrder() {
        root.inOrder(root);
        System.out.print("\b\b\b\n");
    }

    public void preOrder() {
        root.preOrder(root);
        System.out.print("\b\b\b\n");
    }

    public void postOrder() {
        root.postOrder(root);
        System.out.print("\b\b\b\n");
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

    protected Entry<K, V> add(Entry<K, V> x, K key, V value, Entry<K, V> parent) {
        if (x == null) {
            return new Entry(key, value, parent);
        }
        int compare = key.compareTo(x.key); // เอา key ที่จะ add มาเทียบกับ root
        if (compare < 0) {
            x.leftChild = add(x.leftChild, key, value, x);
        } else if (compare > 0) {
            x.rightChild = add(x.rightChild, key, value, x);
        } else {
            x.value = value;
        }
        return x;
    }

    public V remove(K key) {
        if (key != null) {
            throw new NullPointerException("Key to delete is null");
        }
        Entry<K, V> temp = get(root, key);
        return root.remove(temp);
    }

    // service method of getHeight
    public int getHeight() {
        return root.getHeight(root);
    }

//----------------------------------------------------------------------------------     
//----------------------------------------------------------------------------------
    protected class Entry<K, V> { // inner class 

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

        private Entry<K, V> findMin(Entry<K, V> x) {
            if (x.leftChild == null) {
                return x;
            } else {
                return findMin(x.leftChild);
            }
        }

        private V remove(Entry<K, V> x) {
            if (x == null) {
                return null;
            }
            V value = x.value;
            if (x.leftChild != null && x.rightChild != null) { // delete node which has 2 child ;
                Entry<K, V> minNode = findMin(x.rightChild);
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
            {
                if (x.parent != null) {  // node to be delete is not root 
                    if (x.parent.leftChild == x) {
                        x.parent.leftChild = null;
                    } else {
                        x.parent.rightChild = null;
                    }
                } else {
                    root = null;
                }
            }
            return value;
        }

        private void breathFirst(Entry<K, V> x) { // บอกว่าจะทำ breathFirst ที่ node ไหน
            LinkedList<Entry<K, V>> q = new LinkedList(); // สร้าง q
            q.add(x); // เริ่มจาก root เสมอ
            int count = 0;
            while (!q.isEmpty()) {
                count++;
                Entry<K, V> temp = q.remove();
                System.out.print(temp + " ,");
                if (temp.leftChild != null) {
                    q.add(temp.leftChild);
                }
                if (temp.rightChild != null) {
                    q.add(temp.rightChild);
                }
            }
            System.out.print("\b\b\b\n");
        }

        private void preOrder(Entry<K, V> x) { // rootก่อน
            if (x != null) {
                System.out.print(x + ","); // root
                preOrder(x.leftChild);
                preOrder(x.rightChild);
            }
        }

        private void inOrder(Entry<K, V> x) { // ซ้าย root ขวา
            if (x != null) {
                inOrder(x.leftChild);
                System.out.print(x + ","); // root
                inOrder(x.rightChild);
            }
        }

        private void postOrder(Entry<K, V> x) { // ซ้าย ขวา root
            if (x != null) {
                postOrder(x.leftChild);
                postOrder(x.rightChild);
                System.out.print(x + ","); // root
            }
        }

        // เพิ่ม method get height
        // support method
        private int getHeight(Entry<K, V> node) {
            if (node == null) {
                return -1; // return -1 เพราะว่า เราเริ่มนับจาก 0
            }
            return 1 + Math.max(getHeight(node.leftChild), getHeight(node.rightChild));
        }

        @Override
        public String toString() {
            return "(" + key + ':' + value + ')'; // จาวาใช้ single code เพราะลดขยะ ถ้าเราใช้ "1ตัว" จะ new obj
        }

        //หาความสูง
    }
}
