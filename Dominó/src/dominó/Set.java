/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominó;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *
 * @autores Zhong Hao Lin Chen y Adrián David Morillas Marco
 * @param <E>
 */
//Esta interfaz pertenece al de un conjunto genérico como el montón, aunque
//finalmente no se ha implementado en el montón de nuestro dominó debido
//a que ya viene en la propia Java
public interface Set<E> {
    public boolean add(E ficha);
    public boolean remove(E ficha);
    public boolean contains(E ficha);
    public Iterator<E> iterator();
    public int size();
    public boolean isEmpty();
    public void clear();
    public E choose() throws NoSuchElementException;
    
    //Todas estas cabeceras pertenecen al conjunto aunque en nuestro 
    //caso no hacemos uso de ellos
    public boolean addAll(Set<E> anotherSet);
    public boolean retainAll(Set<E> anotherSet);
    public boolean removeAll(Set<E> anotherSet);
    public boolean containsAll(Set<E> anotherSet);
    
    public boolean equals(Set<E> anotherSet);
    public Set<E> clone();
}
