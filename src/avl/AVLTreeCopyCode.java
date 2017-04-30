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
public class AVLTreeCopyCode <K extends Comparable<K>, V> {
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
    
    //แก้ตรงนี้
    // เชคว่า return มาปุ๊บ ความสูง balance ไหม
    protected Entry<K, V> add(Entry<K, V> x, K key, V value, Entry<K, V> parent) {
        if (x == null) {
            return new Entry(key, value, parent);
        }
        int compare = key.compareTo(x.key); // เอา key ที่จะ add มาเทียบกับ root
        if (compare > 0) { //แอดทางซ้าย ไม่มีทางที่ขวาขวาจะสูงกว่า
            x.leftChild = add(x.leftChild, key, value, x);
            //เชคว่าด้านซ้ายของ x / ขวา ของ x balance ไหม
            // ลูกซ้ายต้องมีแน่ๆ            
            int balanceFactor = x.getHeight(x.leftChild) - x.getHeight(x.rightChild); 
            // ค่าจะไม่ติดลบ 
            if(balanceFactor > 1){
                System.out.println("You have to rotate at "+x);
                // มีปหที่ลูกซ้าย แต่ยังไม่รู้ว่าเป็น left or right subtree
                //เชคว่าไม่ balance ที่ left subtree หรือ right subtree
                int unBalanceCase = x.getHeight(x.leftChild.leftChild) - x.getHeight(x.leftChild.rightChild);
                if(unBalanceCase >= 0){ //ซ้ายสูงกว่าขวา = alpha LL
//                    System.out.println("CASE: alpha LL ");
                    x = singleRightRotate(x); // promote ลูกเป็น พ่อ เลยส่ง x กลับมา
                }else{
                    System.out.println("CASE: alpha RL ");
                    x = doubleLeftRightRotate(x); // หมุนซ้ายที่ left subtree แล้วหมุนขวาที่ left child
                    
                }
            }                        
            
        } else if (compare < 0) {
            x.rightChild = add(x.rightChild, key, value, x);
            int balanceFactor = x.getHeight(x.leftChild) - x.getHeight(x.rightChild);
            // ขวามากกว่า จะติดลบ
            if(balanceFactor < -1){
                System.out.println("You have to rotate at "+x);
                int unBalanceCase = x.getHeight(x.rightChild.leftChild) - x.getHeight(x.rightChild.rightChild);
                if(unBalanceCase >= 0){
                    System.out.println("CASE: alpha LR");
                    x = doubleRightleftRotate(x);
                }else{
                    System.out.println("CASE: alpha RR");
                    x = singleLeftRotate(x);
                }
                
            }
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

    private Entry<K,V> singleRightRotate(Entry<K,V> alpha) { // alpha = nodeที่จะต้องหมุน
        Entry<K,V> oldLeft = alpha.leftChild;
        alpha.leftChild = oldLeft.rightChild; 
        
        if(oldLeft.rightChild != null){
            oldLeft.rightChild.parent = alpha;
        }         
        
        oldLeft.rightChild = alpha;      
        
        oldLeft.parent = alpha.parent;
        alpha.parent = oldLeft;      
                
        return oldLeft;
    }

    private Entry<K,V> singleLeftRotate(Entry<K,V> alpha) {
        Entry<K,V> oldRight = alpha.rightChild;
        alpha.rightChild = oldRight.leftChild;
        
        if(oldRight.leftChild != null){
            oldRight.leftChild.parent = alpha;
        }  
        
        oldRight.leftChild = alpha;
        
        oldRight.parent = alpha.parent;
        alpha.parent = oldRight;              
                
        return oldRight;
    }

    private Entry<K, V> doubleLeftRightRotate(Entry<K, V> alpha) { //เกิดที่ลูกซ้าย  
        // หมุนขวาที่ alpha.right ก่อน   
        // แล้วค่อยหมุนซ้ายที่ alpha
        alpha.leftChild = singleLeftRotate(alpha.leftChild);        
        return singleRightRotate(alpha);
    }

    private Entry<K, V> doubleRightleftRotate(Entry<K, V> alpha) { // เกิดที่ลูกขวา        
        alpha.rightChild = singleRightRotate(alpha.rightChild);
        return singleLeftRotate(alpha);
    }
//---------- REMOVEEE
    public void removeRecursion(K key) {
        if (key == null) {
            throw new NullPointerException("Key to delete is null");
        }
        root = removeRe(root, key, null);
    }    
    
    private Entry<K,V> removeRe (Entry<K,V> node, K key, Entry<K,V> parent){
        if(node==null){
            return null;
        }
        
        int comp = key.compareTo(node.key);
        
        if(comp < 0){
            node.leftChild = removeRe(node.leftChild, key , node);
            int balanceFactor = node.getHeight(node.leftChild) - node.getHeight(node.rightChild); 
            // รู้ว่าเกิดที่ลูกขวา แต่ไม่รู้ว่าเกิดที่ subtree ไหน
            if(balanceFactor < -1){
                int unbalanceCase = node.getHeight(node.rightChild.leftChild) - node.getHeight(node.rightChild.rightChild);
                if(unbalanceCase <= 0){ // alpha R R
                    node = singleLeftRotate(node);
                }else{ // alpha L R
                    node = doubleRightleftRotate(node);                          
                }
            }
        }else if (comp > 0){
            node.rightChild = removeRe(node.rightChild,key,node);
            int balanceFactor = node.getHeight(node.leftChild) - node.getHeight(node.rightChild);
            if(balanceFactor > 1){
                int unbalanceCase = node.getHeight(node.leftChild.leftChild) - node.getHeight(node.leftChild.rightChild);
                if(unbalanceCase >=0 ){ // alpha L L
                    node = singleRightRotate(node);                    
                }else{ // alpha R L
                    node = doubleLeftRightRotate(node);
                }
            }
        }else if (node.leftChild != null && node.rightChild != null){ // 2 children
            // ถ้ามีลูกสองฝั่ง หาตัวที่จะมาแทน
            if( node.getHeight(node.leftChild) > node.getHeight(node.rightChild) ){
                Entry<K,V> tmp = node.findMax(node.leftChild);
                node.key = tmp.key; // เอาค่ามา replace แล้วลบ tmp
                node.value = tmp.value;
                node.leftChild = removeRe(node.leftChild,tmp.key,node);
            }else{
                Entry<K,V> tmp = node.findMin(node.rightChild);
                node.key = tmp.key;
                node.value = tmp.value;
                node.rightChild = removeRe(node.rightChild,tmp.key,node);
            }
        }else{
            Entry<K,V> tmp = node;
            if(tmp.leftChild != null){
                node = node.leftChild;
                tmp.leftChild.parent = parent;
            }else{
                node = node.rightChild;
                if(tmp.rightChild != null){
                    tmp.rightChild.parent = parent;
                }
            }
        }
        return node;
                
                
                
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
        
        private Entry<K, V> findMax(Entry<K, V> x) {
            if (x.rightChild == null) {
                return x;
            } else {
                return findMax(x.rightChild);
            }
        }
// ลบซ้าย ขวาจะสูงขึ้น
// ลบขวา ซ้ายจะสูงขึ้น
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
                System.out.print(temp + " --> "+temp.parent+" , ");
                if (temp.leftChild != null) {
                    q.add(temp.leftChild);
                }
                if (temp.rightChild != null) {
                    q.add(temp.rightChild);
                }
            }
            System.out.print("\b\b\n");
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
    }    
}
