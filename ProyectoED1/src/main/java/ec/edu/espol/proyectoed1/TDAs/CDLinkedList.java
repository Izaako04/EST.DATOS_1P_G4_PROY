package ec.edu.espol.proyectoed1.TDAs;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 *
 * @author Isaías
 */

public class CDLinkedList<E> implements List<E> {
    protected class Node <E> {
        private E data;
        private Node<E> next;
        private Node<E> prev;
        
        Node (E data) {
            this.data = data;
            next = null;
            prev = null;
        }
        
        Node () {
            next = null;
            prev = null;
        }
    }
    
    private Node <E> head;
    private Node <E> tail;
    private int size;
    
    public CDLinkedList () {
        head = new Node ();
        tail = new Node ();
    }
    
    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return head.data == null && tail.data == null;
    }

    @Override
    public boolean contains(Object o) {
        if (isEmpty()) return false;
        
        int size = size();
        Node <E> tempNode = new Node <E> ();
        
        for (int i = 0; i < size; i++) {
            if (o.equals(tempNode.data)) return true;
        }
        
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        Iterator<E> it = new Iterator<E> () {
            private Node<E> current = head.next;

            @Override
            public boolean hasNext() {
                return current != tail;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException("No hay más elementos en la lista");
                }
                
                E data = current.data;
                current = current.next;
                return data;
            }
        };
        return it;
    }

    @Override
    public Object[] toArray() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public <T> T[] toArray(T[] a) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean add(E e) {
        Node newNode = new Node (e);
        
        if (isEmpty()) {
            head = tail = newNode;
            newNode.prev = newNode.next = newNode;
        } else {
            newNode.next = head;
            newNode.prev = tail;
            head.prev = newNode;
            tail.next = newNode;
            tail = newNode;
        }
        
        size++;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        if (!contains(o) || isEmpty()) return false;
        
        Node <E> tempNode = head;
        for (int i = 0; i < size(); i++) {
            if (tempNode.data.equals(o)) {
                Node <E> prevNode = tempNode.prev;
                Node <E> sgtNode = tempNode.next;
                prevNode.next = tempNode.next;
                sgtNode.prev = prevNode;
            }
                
            tempNode = tempNode.next;
        }
        
        size--;
        return true;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void clear() {
        if (isEmpty()) return;
        
        head.next = head.prev = tail.next = tail.prev = null;
        head = tail = null;
        size = 0;
    }

    @Override
    public E get(int index) {
        int size = size();
        if (isEmpty() || index >= size) return null;
        
        if (index == 0) return head.data;
        else if (index == size - 1) return tail.data;
        else {
            Node <E> tempNode = head;
            for (int i = 0; i < index; i++) {
                tempNode = tempNode.next;
            }
            return tempNode.data;
        }
    }

    @Override
    public E set(int index, E element) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void add(int index, E element) {
        int size = size();
        if (isEmpty() || index >= size) return;
        
        Node <E> newNode = new Node (element);
        Node <E> tempNode = head;
        for (int i = 0; i < index - 1; i++) {
            tempNode = tempNode.next;
        }
        
        newNode.prev = tempNode;
        newNode.next = tempNode.next;
        tempNode.next.prev = newNode;
        tempNode.next = newNode;
        size++;
    }

    @Override
    public E remove(int index) {
        if (isEmpty() || index >= size) return null;
        
        Node <E> tempNode = head;
        Node <E> removedNode;
        
        if (index == 0) {
            removedNode = head;
            tempNode = tempNode.next;
            tempNode.prev = tail;
            head = tempNode;
            tail.next = head;
            
        } else if (index == size - 1) {
            removedNode = tail;
            tempNode = tempNode.prev.prev;
            tempNode.next = head;
            tail = tempNode;
            head.prev = tail;
            
        } else {
            for (int i = 0; i < index - 1; i++) {
                tempNode = tempNode.next;
            }

            removedNode = tempNode.next;
            tempNode.next = tempNode.next.next;
            tempNode.next.prev = tempNode;

        }
        
        size--;
        return removedNode.data;
    }

    @Override
    public int indexOf(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int lastIndexOf(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
 
    @Override
    public ListIterator<E> listIterator() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    @Override
    public String toString () {
        String strReturn = "";
        Node tempNode = head;
        
        for (int i = 0; i < size; i++) {
            strReturn = strReturn + tempNode.data.toString() + " -> ";
            tempNode = tempNode.next;
        }

        return strReturn.substring(0, strReturn.length() - 3);
    }
}