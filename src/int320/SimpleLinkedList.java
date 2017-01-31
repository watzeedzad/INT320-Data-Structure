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

        public Element() {

        }

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

    @Override
    public int indexOf(Object o) {
        int index = 0;
        if (o == null) {
            for (Element x = head; x != null; x = x.next) {
                if (x.dataElement == null) {
                    return index;
                }
                index++;
            }
        } else {
            for (Element x = head; x != null; x = x.next) {
                if (x.dataElement.equals(o)) {
                    return index;
                }
                index++;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        int index = size;
        if (o == null) {
            for (Element x = tail; x != null; x = x.previous) {
                index--;
                if (x.dataElement == null) {
                    return index;
                }
            }
        } else {
            for (Element x = tail; x != null; x = x.previous) {
                index--;
                if (o.equals(x.dataElement)) {
                    return index;
                }
            }
        }
        return -1;
    }

    @Override
    public void add(int index, E element) {
        if (index == size) {
            add(element);
        } else if (index == 0) {
            Element temp = new Element();
            temp.dataElement = element;
            temp.next = head;
            temp.previous = temp;
            head = temp;
            size++;
        } else {
            Element temp = new Element();
            temp = head;
            for (int i = 0; i < index; i++) {
                temp = temp.next;
            }
            Element myElement = new Element();
            myElement.dataElement = element;
            myElement.next = temp.next;
            myElement.next.previous = myElement;
            temp.next = myElement;
            size++;
        }
    }

    @Override
    public E remove(int index) {
        if (0 < index || index >= size) {
            throw new IndexOutOfBoundsException();
        } else if (index == 0) {
            E element = head.dataElement;
            head = head.next;
            size--;
            return element;
        } else if (index == size - 1) {
            E element = tail.dataElement;
            tail = tail.previous;
            size--;
            return element;
        } else {
            Element temp = head;
            for (int i = 0; i < index; i++) {
                temp = temp.next;
            }
            temp.previous.next = temp.next;
            E element = temp.dataElement;
            return element;
        }
    }

}
