package code.from.other;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.AbstractList;
import java.util.List;

/**
 *
 * @author computer
 */
public class Koh6<E extends Comparable> extends AbstractList<E> implements List<E> {

    private Element head;
    private Element tail;
    private int size;

    @Override
    public int lastIndexOf(Object o) {
        int currentPos = size-1;
        if(o!=null){
            Element ptr = tail;          
            while(ptr != null &&! ptr.dataElement.equals(o)){
                ptr = ptr.previous;
                currentPos--;                
            }
        }
        return currentPos;
    }

    @Override
    public int indexOf(Object o) {
        int i = 0;
        if (o == null) {
            for (Element e = head; e != null; e = e.next) {
                if (e.dataElement == null) {
                    return i;
                }
                i++;
            }
        } else {
            for (Element e = head; e != null; e = e.next) {
                if (o.equals(e.dataElement)) {
                    return i;
                }
                i++;
            }
        }
        return -1;
    }

    public class Element {

        Element previous;
        E dataElement;
        Element next;

        public Element(Element previous, E dataElement, Element next) {
            this.previous = previous;
            this.dataElement = dataElement;
            this.next = next;
        }

        public void insertAfter(E item) {
            Element tmp = new Element(this, item, this.next);
            this.next = tmp;
            if (tmp.next != null) {
                tmp.next.previous = tmp;
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
    public boolean add(E item) {
        if(head == null){
            head = new Element(null, item, null);
            tail = head;
        }else{
            for (Element e = head; e != null; e = e.next) {
                if (item.compareTo(e.dataElement)>0) {
                    Element tmp = new Element(e.previous, item, e);
                    e.previous = tmp;
                    if (tmp.previous != null) {
                        tmp.previous.next = tmp;
                    }else{
                        head = tmp;
                    }
                    break;
                }else if(e.next == null){
                    Element tmp = new Element(e, item, e.next);
                    e.next = tmp;
                    tail = tmp;
                    break;
                }               
            }           
        }
        size++;
        return true;
    }
    
    public E findByMin(){
        Element min = head;
        for (Element e = head; e != null; e = e.next) {
                if (min.dataElement.compareTo(e.dataElement)>0) {
                    min = e;
                }               
            }
        return min.dataElement;
    }

    @Override
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        if (head != null) {
            Element e = head.getElement(index);
            if (e.previous != null) {
                e.previous.next = e.next;
            }

            if (e.next != null) {
                e.next.previous = e.previous;
            }
            if(e==tail){
                tail = e.previous;
            }
            if(e==head){
                head = e.next;
            }
            e.next = null;
            e.previous = null;
            size--;
            return e.dataElement;
        }else{
            return null;
        }
    }
       
    @Override
    public int size() {
        return size;
    }

}