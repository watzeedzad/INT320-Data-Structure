/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package int320;

import java.util.List;

/**
 *
 * @author int320
 */
public class TestList {

    public static void main(String[] args) {
        //SimpleArrayList<Integer> sa = new SimpleArrayList<>();
        List<Integer> sa = new SimpleLinkedList();
        sa.add(900);
        sa.add(101);
        for (int i = 0; i < 100; i++) {
            sa.add((int) (Math.random() * 100) + 10);
        }
        sa.add(999);
        sa.add(909);
        for (int i = 0; i < sa.size(); i++) {
            System.out.println(sa.get(i) + ",");
        }
        //sa.add(1, 111);
        System.out.println("\b\b");
    }
}