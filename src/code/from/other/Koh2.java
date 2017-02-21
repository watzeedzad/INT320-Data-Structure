package code.from.other;


import java.util.LinkedList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 000
 */
public class Koh2 <K extends Comparable<K>,V>{
    Entry<K,V> root;
    
    
    
    public void put(K key, V value) {
        if (key == null) {
            throw new NullPointerException("Key is null");
        }
        root = add(root, key, value, null);
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
    public void rBreadFirst(){
        root.rBreadFirst(root);
        
    }
    
    public class Entry<K,V>{
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
        
              
        public void rBreadFirst(Entry<K,V> x){
            LinkedList<Entry<K, V>> q = new LinkedList();
            q.add(x);
            while (!q.isEmpty()) {
                Entry<K, V> tmp = q.remove();
                System.out.print(tmp + ", ");
                if (tmp.rightChild != null) {
                    q.add(tmp.rightChild);
                }
                if (tmp.leftChild != null) {
                    q.add(tmp.leftChild);
                }
                
            }
            System.out.println("\b\b\b\n");
        }
        
        @Override
        public String toString() {
            return "(" + key + ":" + value + '}';
        }
    }
}
