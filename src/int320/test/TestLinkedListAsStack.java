/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package int320.test;

import int320.SimpleStack;
import java.util.LinkedList;

/**
 *
 * @author User
 */
public class TestLinkedListAsStack {

    public static void main(String[] args) {
        //LinkedList<String> testJa = new LinkedList();
        SimpleStack<Integer> testJa = new SimpleStack();
        testJa.push(1);
        testJa.push(2);
        testJa.push(3);
        testJa.push(4);
        for (int i = 0; i < 10; i++) {
            testJa.push(i * 10);
        }
        System.out.println("This is top of stack : " + testJa.peek());
        //System.out.println("This is indexOf : " + testJa.indexOf("wtf dude"));
        //System.out.println("This is lastIndexOf : " + testJa.lastIndexOf("wtf dude"));
        while (!testJa.isEmpty()) {
            System.out.println(testJa.pop());
        }
    }
}
