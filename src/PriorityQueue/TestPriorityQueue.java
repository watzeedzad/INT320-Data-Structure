/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PriorityQueue;

import PriorityQueue.PriorityQueue;

/**
 *
 * @author jiraw
 */
public class TestPriorityQueue {

    public static void main(String[] args) {
        PriorityQueue<Integer> pq = new PriorityQueue(15, null, PriorityQueue.HeapType.MIN_HEAP);
        pq.offer(35);
        pq.offer(70);
        pq.offer(25);
        pq.offer(39);
        pq.offer(72);
        pq.offer(11);
        pq.offer(90);
        pq.offer(8);
        pq.offer(11);
        System.out.println(pq);
    }
}
