/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominó;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

/**
 *
 * @autores Zhong Hao Lin Chen y Adrián David Morillas Marco
 */
public class Monton {
    //Vamos a implementar los métodos de un conjunto para el montón
    private static final int NFichas = 28;
    //Mensaje que muestra el error de que el montón está vacío
    private static final String MensajeVacio = "Error en el Monton: Monton vacio";
    
    private ContenedorFichas[] fichas;//Se crean fichas de la clase ContenedorFichas
    private int cardinal; //Elementos que tiene el conjunto Monton
    
    //Primero nos creamos un constructor del TAD Monton
    public Monton() {
        this.fichas = new ContenedorFichas[NFichas];
        for (int index = 0; index < NFichas; index++) {
            this.fichas[index] = new ContenedorFichas();
        }
        
        //Inicializa el montón con las 28 fichas
        int limitenumero = Ficha.MinNumber;
        for (int primerNumero = Ficha.MinNumber; primerNumero <= Ficha.MaxNumber; primerNumero++) {
            for (int segundoNumero = Ficha.MinNumber; segundoNumero <= limitenumero; segundoNumero++) {
                Ficha ficha = new Ficha(primerNumero, segundoNumero);
                
                this.add(ficha); 
            }
            
            limitenumero++;
        }
    }
    
    //Añade una ficha al conjunto
    public boolean add(Ficha ficha) {
        boolean added = false;
        
        if ((this.cardinal < NFichas) && (!this.contains(ficha))) {
            boolean holeFound = false;
            int index = 0;
            while ((!holeFound) && (index < NFichas)) {
                holeFound = !this.fichas[index].tieneFicha();
                if (holeFound) {
                    this.fichas[index].añadirFicha(ficha);
                    this.cardinal++;
                }
                
                index++;
            }
            
            added = true;
        }
        return added;
    }
    
    //Borra una Ficha del conjunto
    public boolean remove(Ficha ficha) {
        boolean removed = false;  
        
        boolean found = false;
        int index = 0;
        while ((!found) && (index < NFichas)) {
            if (this.fichas[index].tieneFicha()) {
               found = ficha.equals(this.fichas[index].cogerFicha());
               if (found) {
                   this.fichas[index].eliminarFicha();
                   this.cardinal--;
                   
                   removed = true;
               }
            }
            
            index++;
        }       
        return removed;      
    }
    
    //Comprueba si el conjunto Monton tiene la ficha solicitada
    public boolean contains(Ficha ficha) {
        boolean contained = false;
        int index = 0;
        while ((!contained) && (index < NFichas)) {
            contained = ficha.equals(this.fichas[index].cogerFicha());
            index++;
        }
        
        return contained;
    }
    
    //El iterador recorre la estructura de datos para saber si tiene una ficha
    //y en caso afirmativo devolver el valor de esa ficha
    public Iterator<Ficha> iterator() {
        return new Iterator<Ficha>() {
            private int index = 0;
            
            @Override
            public boolean hasNext() {
                boolean fichaEncontrada = false;
                while ((!fichaEncontrada) && (index < NFichas)) {
                    fichaEncontrada = Monton.this.fichas[index].tieneFicha();
                    if (!fichaEncontrada) {
                        index++;
                    }
                }
                
                return fichaEncontrada;
            }

            @Override
            public Ficha next() {
                return Monton.this.fichas[index++].cogerFicha();
            }
        };
    }
    
    //Se devuelve el cardinal que es el número de elementos que tiene el conjunto Monton
    public int size() {
        return this.cardinal;
    }
    
    //Si cardinal es 0 se devuelve el boolean de que está vacó el Monton
    public boolean isEmpty() {
        return this.cardinal == 0;
    }
    
    //Poniendo que el cardinal es 0 significa borrar todos los elementos del conjunto
    public void clear() {
        for (int index = 0; index < NFichas; index++) {
            if (this.fichas[index].tieneFicha()) {
                this.fichas[index].eliminarFicha();
            }
        }
        this.cardinal = 0;
    }
    
    //Permite coger una ficha aleatoria del Monton al jugador
    public Ficha choose() throws NoSuchElementException {
        Ficha fichaElegida = null;
        
        if (this.isEmpty()) {
            throw new NoSuchElementException(MensajeVacio);
        }
        
        Random numeroAleatorio = new Random();
        
        boolean fichaEncontrada = false;
        while (!fichaEncontrada) {
            int indiceAleatorio = numeroAleatorio.nextInt(NFichas);
            fichaEncontrada = this.fichas[indiceAleatorio].tieneFicha();
            //Si encuentra la ficha, coge la ficha para el jugador
            //y elimina la ficha elegida del monton
            if (fichaEncontrada) {
                fichaElegida = this.fichas[indiceAleatorio].cogerFicha();
                this.remove(fichaElegida);
            }
        }
        
        return fichaElegida;
    }
}
