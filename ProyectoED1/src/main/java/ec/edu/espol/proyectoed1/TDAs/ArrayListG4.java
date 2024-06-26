/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.proyectoed1.TDAs;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 *
 * @author USER
 */
public class ArrayListG4<E> implements List<E>, Serializable{
    int dimensionMax = 10;
    int dimensionReal; 
    E arreglo[];
    
    public ArrayListG4(){
        arreglo = (E[]) new Object[dimensionMax ];
        dimensionReal = 0;
    }

    @Override
    public int size() {
        return dimensionReal;
    }

    @Override
    public boolean isEmpty() {
        return dimensionReal == 0;
    }

    @Override
    public boolean contains(Object o) {
        for (int i = 0; i < dimensionReal; i++) {
            if (arreglo [i].equals(o)) return true;
        }
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        Iterator <E> it = new Iterator <E> () {
            int cursor = 0;
            
            @Override
            public boolean hasNext () {
                return cursor < dimensionReal;
            }
            
            @Override
            public E next () {
                E e = arreglo [cursor];
                cursor++;
                return e;
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
        if(this.isFull()){
            this.aumentarDim();
        }else if(e==null){
            return false;
        }
        arreglo[dimensionReal++]= e;
        return true;
    }
    
    private boolean isFull(){
        return dimensionReal == dimensionMax;
    }
    
    private void aumentarDim(){
        E[] arrTemp = (E[]) new Object[dimensionMax*2];
        for (int i=0;i<dimensionMax;i++){
            arrTemp[i] = arreglo[i];
        }
        arreglo = arrTemp;
        dimensionMax = dimensionMax * 2;
    }

    @Override
    public boolean remove(Object o) {
        if (!contains(o)) return false;
        
        int pos = 0;
        for (int i = 0; i < dimensionReal; i++) {
            if  (o.equals(arreglo[i])) {
                pos = i;
                arreglo[i] = null;
            }
        }
        
        for (int i = pos; i < dimensionReal; i++) {
            arreglo[i] = arreglo[i+1];
        }
        
        dimensionReal--;
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
        int espacio = dimensionMax - dimensionMax;
        if(c.isEmpty()){
            return true;
        }else if(c.size()>dimensionMax || c.size()>espacio){
            this.aumentarDim();
        }
        for(E elemento: c){
            this.add(elemento);
        }
        return true;
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
        for (int i = 0; i < dimensionReal; i++) {
            arreglo [i] = null;
        }
        dimensionReal = 0;
    }

    @Override
    public E get(int index) {
        if(!this.isEmpty()&&index<dimensionReal){
            return arreglo[index];
        }
        return null;
    }

    @Override
    public E set(int index, E element) {
        if(index<0||index>=dimensionReal){
            throw new IndexOutOfBoundsException();
        }else{
            E eviejo = arreglo[index];
            arreglo[index] = element;
            return eviejo;
        }
    }

    @Override
    public void add(int index, E element) {
        if(element == null){
            throw new NullPointerException();
        }else if(index <0 || index>this.dimensionReal){
            throw new IndexOutOfBoundsException();
        }else if(this.isEmpty()){
            arreglo[dimensionReal++] = element;
        }else if(this.isFull()){
            this.aumentarDim();
        }
        for(int i = dimensionReal;i>=index;i--){
           arreglo[i+1] = arreglo[i];
        }
        arreglo[index]=element;
        dimensionReal++;
    };

    @Override
    public E remove(int index) {
        E eremove = null;
        if(this.isEmpty()||index>this.dimensionReal||index<0){
            throw new IndexOutOfBoundsException();
        }else{
            eremove = arreglo[index];
            for(int i =0;i<this.dimensionReal-1;i++){
                arreglo[i]=arreglo[i+1];
            }
            dimensionReal--;
        }
        return eremove;
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
    
    public boolean addFrist(E e){
        if (e == null){
            return false;
        } else if (isEmpty()){
            arreglo[0] = e;
            dimensionReal++;
            return true;
        } else if(isFull()){
            aumentarDim();
        }
        for (int i = dimensionReal-1; i>=0;i--){
            arreglo[i+1] = arreglo[i];
        }
        arreglo[0] = e;
        dimensionReal++;
        return true;
    }
}