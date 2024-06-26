package ec.edu.espol.proyectoed1.TDAs;


import java.io.Serializable;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 *
 * @author Isaías
 */

public class CDLinkedList<E> implements List<E>, Serializable, Iterable<E> {

    protected class Node <E> implements Serializable{
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
        head = null;
        tail = null;
        size = 0;
    }
    
    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        if (isEmpty()) return false;

        Node <E> tempNode = head;
        
        for (int i = 0; i < size; i++) {
            if (tempNode.data.equals(o)) return true;
            tempNode = tempNode.next;
        }
        
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private Node<E> current = head;
            private int count = 0;
            private final int expectedModCount = size;

            @Override
            public boolean hasNext() {
                if (expectedModCount != size) {
                    throw new ConcurrentModificationException();
                }
                return count < size;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException("No hay más elementos en la lista");
                }

                E data = current.data;
                current = current.next;
                count++;
                return data;
            }
        };
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
        System.out.println(this);
        return true;
    }

    @Override
    public boolean remove(Object o) {
        if (isEmpty()) return false;
    
        Node<E> tempNode = head;

        for (int i = 0; i < size; i++) {
            if (tempNode.data.equals(o)) {
                Node<E> prevNode = tempNode.prev;
                Node<E> sgtNode = tempNode.next;

                prevNode.next = sgtNode;
                sgtNode.prev = prevNode;

                if (tempNode == head) {
                    head = sgtNode;
                }

                if (tempNode == tail) {
                    tail = prevNode;
                }

                size--;
                return true;
            }

            tempNode = tempNode.next;
        }
    
        return false;
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
        if (isEmpty()) {
            return null;
        } else if (!isEmpty() && index >= size) {
            return null;
        }
        
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
        if (element == null) throw new IllegalArgumentException("Element cannot be null");
        if (index < 0 || index > size) throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        
        Node <E> newNode = new Node (element);
        
        if (head == null) {
            head = tail = newNode;
            newNode.prev = newNode.next = newNode;
            
        } else if (index == 0) {
            newNode.next = head;
            newNode.prev = tail;
            head.prev = newNode;
            tail.next = newNode;
            head = newNode;
            
        } else if (index >= size - 1) {
            newNode.next = head;
            newNode.prev = tail;
            head.prev = newNode;
            tail.next = newNode;
            tail = newNode;
            
        } else {
            Node <E> tempNode = head;
            int i = 0;
            
            while (i < index - 1) {
                tempNode = tempNode.next;
                i++;
            }
            
            newNode.prev = tempNode;
            newNode.next = tempNode.next;
            tempNode.next = newNode;
            newNode.next.prev = newNode;
        }
        
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
        Node <E> tempNode = head;
        
        for (int i = 0; i < size; i++) {
            if (o.equals(tempNode.data)) {
                return i;
            }
            tempNode = tempNode.next;
        }
        
        return -1;
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
        if (isEmpty()) return "Empty list";
        
        String strReturn = "";
        Node tempNode = head;
        
        for (int i = 0; i < size; i++) {
            
            if (tempNode.data != null) {
                strReturn = strReturn + tempNode.data.toString() + " -> ";

            }

            
            tempNode = tempNode.next;
        }

        return strReturn.substring(0, strReturn.length() - 3);
    }
    
    public E getNext (E e) {
        Node <E> tempNode = head;
        
        for (int i = 0; i < size; i++) {
            if (tempNode.data.equals(e)) {
                return tempNode.next.data;
            }
            tempNode = tempNode.next;
        }
        
        return null;
    }
    
    public E getPrev (E e) {
        Node <E> tempNode = head;
        
        for (int i = 0; i < size; i++) {
            if (tempNode.data.equals(e)) {
                return tempNode.prev.data;
            }
            tempNode = tempNode.prev;
        }
        
        return null;
    }
    
    
}