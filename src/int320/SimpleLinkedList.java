/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package int320;

import java.util.AbstractList;
import java.util.List;

/**
 *
 * @author User
 */
public class SimpleLinkedList<E> extends AbstractList<E> implements List<E> {

    private Element head;
    private Element tail;
    private int size;

    private class Element {

        E dataElement;
        Element next;
        Element previous;

        public Element(Element previous, E dataElement, Element next) {
            this.dataElement = dataElement;
            this.next = next;
            this.previous = previous;
        }

        public void insertAfter(E item) {
            Element temp = new Element(this, item, this.next);
            this.next = temp;
            if (temp.next != null) {
                temp.next.previous = temp;
            }
        }

        public Element getElement(int index) {
            Element ptr;
            int pos;
            if (index <= size >> 1) {
                ptr = head;
                pos = 0;
                while (pos < index) {
                    ptr = ptr.next;
                    pos++;
                }
            } else {
                ptr = tail;
                pos = size - 1;
                while (pos > index) {
                    ptr = ptr.previous;
                    pos--;
                }
            }
            return ptr;
        }
    }

    @Override
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        return head.getElement(index).dataElement;
    }

    @Override
    public boolean add(E e) {
        if (head == null) {
            head = new Element(null, e, null);
            tail = head;
        } else {
            tail.insertAfter(e);
            tail = tail.next;
        }
        size++;
        return true;
    }

    @Override
    public int size() {
        return size;
    }

}
