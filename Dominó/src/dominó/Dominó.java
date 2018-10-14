/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominó;

/**
 *
 * @autores Zhong Hao Lin Chen y Adrián David Morillas Marco
 */
public class Dominó {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //En el metodo main simplemente creamos una nueva parida e invocamos a 
        //la TAD Partida para que nos simule una partida de domino
        Partida partida = new Partida();
        partida.start();
        
    }
    
}
