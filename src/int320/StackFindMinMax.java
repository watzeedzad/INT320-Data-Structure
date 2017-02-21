package int320;

import java.util.LinkedList;

/**
 *
 * @author int320
 */
public class StackFindMinMax<E extends Comparable<E>> {

    private LinkedList<E> stack;
    private LinkedList<E> minStack;
    private LinkedList<E> maxStack;

    public StackFindMinMax() {
        stack = new LinkedList();
        minStack = new LinkedList();
        maxStack = new LinkedList();
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }

    public void push(E item) {
        stack.push(item);

        if (minStack.isEmpty() || item.compareTo(minStack.peek()) < 0) {
            minStack.push(item);
        } else {
            minStack.push(minStack.peek());
        }
        if (maxStack.isEmpty() || item.compareTo(maxStack.peek()) > 0) {
            maxStack.push(item);
        } else {
            maxStack.push(maxStack.peek());
        }
    }

    public E pop() {
        minStack.pop();
        maxStack.pop();
        return stack.pop();
    }

    public E peek() {
        return stack.peek();
    }

    public E findMin() {
        return minStack.peek();
    }

    public E findMax() {
        return maxStack.peek();
    }

    public static void main(String[] args) {
        StackFindMinMax<Double> st = new StackFindMinMax<>();
        st.push(50.2);
        st.push(7.5);
        st.push(518646.48);
        st.push(9.3);
        st.push(985745d);
        st.push(11.2);
        st.push(3.2);
        st.push(77.0);
        System.out.println("Min : " + st.findMin());
        System.out.println("Max : " + st.findMax());
        st.pop();
        st.pop();
        st.pop();
        st.pop();
        st.pop();
        System.out.println("Min : " + st.findMin());
        System.out.println("Max : " + st.findMax());
    }
}
