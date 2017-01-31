/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package int320;

/**
 *
 * @author User
 */
public class SimpleStack<E> {

    private SimpleLinkedList<E> list;

    public SimpleStack(SimpleLinkedList<E> list) {
        this.list = list;
        list = new SimpleLinkedList<>();
    }

    public SimpleStack() {
        list = new SimpleLinkedList<>();
    }

    public boolean isEmpty() {
        return list.size() == 0;
    }

    public void push(E item) {
        list.add(item);
    }

    public E pop() {
        return list.remove(list.size() - 1);
    }

    public E peek() {
        return list.get(list.size() - 1);
    }
}
