/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package int320.test;

import int320.SimpleArrayList;
import int320.SimpleLinkedList;
import java.util.List;

/**
 *
 * @author int320
 */
public class TestList {

    public static void main(String[] args) {
//                SimpleArrayList<Integer> sa = new SimpleArrayList<>();
        List<Integer> sa = new SimpleLinkedList();
        sa.add(900);
        sa.add(101);
        for (int i = 0; i < 10; i++) {
            sa.add((int) (Math.random() * 100) + 10);
        }
        sa.add(999);
        sa.add(909);
        for (int i = 0; i < sa.size(); i++) {
            System.out.print(sa.get(i) + ", ");
        }

        System.out.println(sa.get(0));
//                System.out.println();
//                sa.remove(8);
//                sa.remove(0);
//                sa.remove(0);
//                sa.remove(sa.size()-1);
//                sa.remove(sa.size()-1);
        sa.add(1, 200);
        System.out.println("\nAfter remover : ");
        for (int i = 0; i < sa.size(); i++) {
            System.out.print(sa.get(i) + ", ");
        }
        //sa.add(1, 111);
        System.out.println("\b\b");
//        SimpleLinkedList test = new SimpleLinkedList();
//        System.out.println("Size of init array : " + test.size());
//        test.add("#1");
//        System.out.println("Size of array after add element : " + test.size());
    }
}
