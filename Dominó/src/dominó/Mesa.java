/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominó;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 *
 * @autores Zhong Hao Lin Chen y Adrián David Morillas Marco
 */
public class Mesa {
    private static final String Vacio = "Mesa esta vacia"; //Mensaje de error al estar vacia la mesa
    public static final char ladoInicial = 'I'; 
    public static final char ladoFinal = 'F';

    private final LinkedList<Ficha> fichas;
    
    //El TAD mesa es una lista enlazada simple ya que nos permite insertar fichas
    //tanto por el principio como por el final
    public Mesa() {
        this.fichas = new LinkedList<>(); //Creamos la lista enlazada
    }
    
    //Se selecciona una ficha del principio de la mesa
    public Ficha cogerFichaPrincipio() throws NoSuchElementException {
        if (this.fichas.isEmpty()) {
            throw new NoSuchElementException(Vacio); //Lanza una excepcion en caso de que este vacia la mesa
        }
        
        return this.fichas.getFirst();//Devuelve la primera ficha
    }
    
    //Se selecciona una ficha del final de la mesa
    public Ficha cogerFichaFinal() throws NoSuchElementException {
        if (this.fichas.isEmpty()) {
            throw new NoSuchElementException(Vacio); //Lanza una excepción en caso de que este vacía la mesa
        }
        
        return this.fichas.getLast();//Devuelve la última ficha
    }
    
    //Añadimos una ficha al principio de la mesa
    public void añadirFichaPrincipio(Ficha ficha) {
        this.fichas.addFirst(ficha);//AddFirst incluida en la propia API de Java de ArrayList
    }
    
    //Añadimos una ficha al final de la mesa
    public void añadirFichaFinal(Ficha ficha) {
        this.fichas.addLast(ficha);//AddLast incluida en la propia API de Java de ArrayList
    }
    
    //Recorremos las fichas de la mesa con el iterador
    public Iterator<Ficha> iterator() {
        return this.fichas.iterator();
    }
    
    //Mostramos las fichas de la mesa por pantalla
    @Override
    public String toString() {
        String resultado = "";
        
        int numeroFicha = 1;
        for (Ficha ficha: this.fichas) {
            resultado += ficha;
            
            if (numeroFicha != this.fichas.size()) {
                resultado += " ";
            }
            
            numeroFicha++;
        }
        
        return resultado;
    }
}
