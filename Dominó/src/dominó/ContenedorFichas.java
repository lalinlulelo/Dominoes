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
//Clase que crea objetos que almacena fichas en el conjunto
public class ContenedorFichas {
    //Creamos una ficha del tipo TADFicha que hemos creado en la clase Ficha
    private Ficha ficha;  
    private boolean tieneFicha;
    
    //Inicializamos el contenedor de fichas a vacío
    public ContenedorFichas() {
        this.ficha = null;
        this.tieneFicha = false;
    }
    
    //Si cogemos una ficha devolvemos el valor de la ficha cogida
    public Ficha cogerFicha() {
        return this.ficha;
    }
    
    //Al ser un void no devuelve un valor, asigna a ficha la ficha 
    //que queremos añadir y ponemos un true porque tiene un valor
    public void añadirFicha(Ficha ficha) {
        this.ficha = ficha;
        this.tieneFicha = true;
    }
    
    //Si queremos eliminar una ficha, ponemos que la ficha esta vacia
    public void eliminarFicha() {
        this.ficha = null;
        this.tieneFicha = false;
    }
    
    //En caso de tener ficha devolvemos un true que tiene ficha
    public boolean tieneFicha() {
        return this.tieneFicha;
    }

}
