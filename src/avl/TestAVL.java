/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avl;

/**
 *
 * @author int320
 */
public class TestAVL {
    public static void main(String[] args) {
        AVLTree<Integer,String> avl = new AVLTree();
//        avl.put(100, "100");
//        System.out.println("Height = "+avl.getHeight()); //0
//        avl.put(200, "200");
//        avl.put(300, "300");
//        avl.put(250, "250");
//        System.out.println("Height = "+avl.getHeight()); //3
//        avl.put(150, "150");
//        avl.put(50, "50");
//        avl.put(20, "20");
//        avl.put(10, "10");
//        avl.put(15, "15");
//        System.out.println("Height = "+avl.getHeight()); //4
//        avl.breathFirst(); // 100 200 300 
        // alpha right right

        // test whether it balance or not
      
//        avl.put(100, "100");
//        avl.put(50, "50");
//        avl.put(30, "30");
        // alpha LL
       
//        avl.put(100, "100");
//        avl.put(50, "50");
//        avl.put(70, "70");        
        // alpha RL right subtree of leftChild

//----------------------------------------------------        
//        avl.put(100, "100");   //              100 >> 1.LL >> 3.LL
//        avl.put(50, "50");     //          50 >> 2.หลังใส่ 40 RL 5.RL
//        avl.put(30, "30");     //    30 >>4.หลัง +45 RR  
//        avl.put(40, "40");     //       40 >> RL
//        avl.put(45, "45");     //          45 >> LL
//                            You have to rotate at (100:100)
//                            CASE: alpha LL 
//                            You have to rotate at (50:50)
//                            CASE: alpha RL 
//                            You have to rotate at (100:100)
//                            CASE: alpha LL 
//                            You have to rotate at (30:30)
//                            CASE: alpha RR
//                            You have to rotate at (50:50)
//                            CASE: alpha RL 
//                            You have to rotate at (100:100)
//                            CASE: alpha LL                                 
          
//-------------- test หมุน          
//        avl.put(100, "100");  
//        avl.put(50, "50");     
//        avl.put(30, "30");          
//        avl.breathFirst();
        
        avl.put(100, "100");  
        avl.put(50, "50");     
        avl.put(30, "30");          
        avl.breathFirst();
        avl.put(150, "150");  
        avl.put(200, "200"); 
        avl.put(90, "90");         
        avl.breathFirst();
        
        
        // check delete
        avl.put(100, "100");
        avl.put(50, "50");
        avl.put(200, "200");
        avl.put(300, "300");
        avl.breathFirst();
        avl.remove(50);
        avl.breathFirst(); // 200 100 300
        
        avl.put(100, "100");
        avl.put(50, "50");
        avl.put(200, "200");
        avl.put(150, "150");
        avl.breathFirst(); 
        avl.remove(50); // หมุนขวา หมุนซ้าย
        avl.breathFirst(); // 150 100 200
        
//1                      200
//1        100                  300     
//1    10                  250       400                           
//1                                      500                  
// ถ้าลบ 300 มันจะ เอา 400 มาแทน 300 ก็ยัง balance                                                              
    }
}
