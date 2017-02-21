/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package int320.test;

import int320.BinarySearchTree;

/**
 *
 * @author User
 */
public class TestBST {

    public static void main(String[] args) {
        BinarySearchTree<Integer, String> bst = new BinarySearchTree();
        bst.put(102, "Java I");
        bst.put(105, "Java II");
        bst.put(303, "JSP");
        bst.put(320, "Data Structures");
        bst.put(106, "Web Tech");
        bst.put(201, "NW I");
        bst.put(202, "Software Process");
        bst.put(101, "IT FUN");
        System.out.println("Breath First : ");
        bst.breathFirst();
//        System.out.println("Pre Order : ");
//        bst.preOrder();
//        System.out.println("In Order : ");
//        bst.inOrder();
//        System.out.println("Post Order : ");
//        bst.postOrder();
        System.out.println("This is min value : " + bst.findMin());
        System.out.println("This is max value : " + bst.findMax());
        System.out.println("This is 2ndmin value : " + bst.find2ndMin());
        System.out.println("This is 2ndmax value : " + bst.find2ndMax());
//        System.out.println(bst.get(106));
//        System.out.println(bst.get(9999));
//        System.out.println(bst.remove(102));
//        System.out.println("Breath First after remove 102 (root node) : ");
//        bst.breathFirst();
        System.out.println("Reverse : ");
        bst.reverseBreathFirst();
    }
}
