/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package int320;

import java.util.Comparator;

/**
 *
 * @author jiraw
 */
public class PriorityQueue<E> {

    public final static int DEFAULT_CAPACITY = 11;
    private final static int MAX_HEAP = -1;
    private final static int MIN_HEAP = 1;

    public static enum HeapType {
        MAX_HEAP, MIN_HEAP
    };

    private E[] queue;
    private HeapType heapType;
    private int size;
    private Comparator<? super E> comparator;

    public PriorityQueue(int capacity, Comparator<? super E> comparator, HeapType heapType) {
        queue = (E[]) new Object[capacity];
        this.comparator = comparator;
        this.heapType = heapType;
    }

    public PriorityQueue() {
        this(DEFAULT_CAPACITY, null, HeapType.MIN_HEAP);
    }

    public PriorityQueue(int capacity) {
        this(capacity, null, HeapType.MIN_HEAP);
    }

    public PriorityQueue(Comparator<? super E> comparator) {
        this(DEFAULT_CAPACITY, comparator, HeapType.MIN_HEAP);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (E element : queue) {
            if (element == null) {
                break;
            }
            sb.append(element + ",");
        }
        return sb.toString();
    }

    public boolean offer(E element) {
        if (element == null) {
            throw new NullPointerException();
        }
        int i = size;
        if (i >= queue.length) {
            //grow(i+10);
            throw new RuntimeException("Queue is full!");
        }

        size = i + 1;
        if (i == 0) {
            queue[0] = element;
        } else {
            siftUp(i, element);
        }
        return true;
    }

    private void siftUp(int k, E element) {
        while (k > 0) {
            int parentIndex = (k - 1) >>> 1;
            E parentValue = queue[parentIndex];
            int compareValue;
            if (comparator != null) {
                compareValue = comparator.compare(element, parentValue) * getHeapTypeValue();
            } else {
                Comparable<? super E> key = (Comparable<? super E>) element;
                compareValue = key.compareTo(parentValue) * getHeapTypeValue();
            }
            if (compareValue >= 0) {
                break;
            }
            queue[k] = parentValue;
            k = parentIndex;
        }
        queue[k] = element;
    }

    private int getHeapTypeValue() {
        return heapType == HeapType.MAX_HEAP ? -1 : 1;
    }

    public E poll() {
        if (size == 0) {
            return null;
        }
        int s = --size;
        E result = queue[0];
        E lastElementInArray = queue[s];
        queue[s] = null;
        if (s != 0) {
            siftDown(0, lastElementInArray);
        }
        return result;
    }

    private void siftDown(int k, E element) {
        int half = size >>> 1;
        while (k < half) {

            int childIndex = (k << 1) + 1;

            E childValue = queue[childIndex];

            int rightChildIndex = childIndex + 1;

            int compareValue;
            if (rightChildIndex < size) {
                if (comparator != null) {
                    compareValue = comparator.compare(childValue, queue[rightChildIndex]) * getHeapTypeValue();
                } else {
                    compareValue = ((Comparable) childValue).compareTo(queue[rightChildIndex]) * getHeapTypeValue();
                }
                if (compareValue > 0) {
                    childIndex = rightChildIndex;
                    childValue = queue[rightChildIndex];
                }
            }
            if (comparator != null) {
                compareValue = comparator.compare(element, childValue) * getHeapTypeValue();
            } else {
                compareValue = ((Comparable) element).compareTo(childValue) * getHeapTypeValue();
            }

            if (compareValue <= 0) {
                break;
            }
            queue[k] = childValue;
            k = childIndex;
        }
        queue[k] = element;
    }
}
