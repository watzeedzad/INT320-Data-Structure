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
 * @author int320
 */
public class SimpleArrayList<E> extends AbstractList<E> implements List<E> {

    private final static int DEFAULT_CAPACITY = 10;
    private final static Object[] DEFAULT_EMPTY_ELEMENT = {};
    private E[] element;
    private int size;

    public SimpleArrayList() {
        element = (E[]) DEFAULT_EMPTY_ELEMENT;
    }

    @Override
    public int indexOf(Object o) {
        if (o == null) {
            for (int i = 0; i < size; i++) {
                if (get(i) == null) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (o.equals(get(i))) {
                    return i;
                }
            }
        }
        return -1;
    }

    @Override
    public E remove(int index) {
        E e = element[index];
        if (index >= 0 && index < size - 1) {
            System.arraycopy(element, index + 1, element, index, size - index);
        }
        element[size] = null;
        size = size - 1;
        return e;
    }

    @Override
    public void add(int index, E element) {
        ensureCapacity(size + 1);
        System.arraycopy(this.element, index, this.element, index + 1, size - index);
        this.element[index] = element;
        size = size + 1;
    }

    @Override

    public E set(int index, E element) {
        return super.set(index, element); //To change body of generated methods, choose Tools | Templates.
    }

    private void ensureCapacity(int minSize) {
        if (minSize > element.length) {
            if (size == 0) {
                element = (E[]) new Object[DEFAULT_CAPACITY];
            } else {
                E[] oldElement = element;
                int newCapacity = size * 3 / 2 + 1;
                element = (E[]) new Object[newCapacity];
                System.arraycopy(oldElement, 0, element, 0, size);
            }
        }
    }

    @Override
    public boolean add(E e) {
        ensureCapacity(size + 1);
        element[size] = e;
        size = size + 1;
        return true;
    }

    @Override
    public E get(int index) throws IndexOutOfBoundsException {
        return element[index];
    }

    @Override
    public int size() {
        return size;
    }

}
