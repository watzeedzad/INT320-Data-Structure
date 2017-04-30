/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avl;

import avl.AVLTreeCopyCode;

/**
 *
 * @author jiraw
 */
public class TestAVLTree2 {
    public static void main(String[] args) {
        AVLTreeCopyCode<Integer, String> avl2 = new AVLTreeCopyCode<>();
        avl2.put(100, "100");  
        avl2.put(50, "50");     
        avl2.put(30, "30");          
        avl2.breathFirst();
        avl2.put(150, "150");  
        avl2.put(200, "200"); 
        avl2.put(90, "90");         
        avl2.breathFirst();
        
        
        // check delete
        avl2.put(100, "100");
        avl2.put(50, "50");
        avl2.put(200, "200");
        avl2.put(300, "300");
        avl2.breathFirst();
        avl2.removeRecursion(50);
        avl2.breathFirst();
        
        avl2.put(100, "100");
        avl2.put(50, "50");
        avl2.put(200, "200");
        avl2.put(150, "150");
        avl2.breathFirst(); 
        avl2.removeRecursion(50);
        avl2.breathFirst();
    }
}
