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
public class OpcionJugador {
    //enum es un tipo de dato en el que nosotros le ponemos la variable
    //en este caso le hemos asignado las opciones del jugador en un turno
    enum OpcionTurno { //enumerado: tipo de opcion que elige el jugador
        Nada, IndiceFicha, Robar, Pasar
    };
    
    private int indiceFicha;
    private OpcionTurno opcionTurno;
    
    //Al principio del turno el jugador todavía no ha hecho nada
    public OpcionJugador() {
        this.opcionTurno = OpcionTurno.Nada;
    }

    //Escogemos el índice de la ficha que queremos poner en la mesa
    public int getIndice() {
        return this.indiceFicha;
    }
    
    //Indicamos que en este turno ponemos un indice
    public void setIndice(int indiceFicha) {
        this.indiceFicha = indiceFicha;
        this.opcionTurno = OpcionTurno.IndiceFicha;
    }
    
    //Devolvemos un boolean para indicar que ya hemos puesto un indice
        public boolean estaIndiceFicha() {
        return this.opcionTurno.equals(OpcionTurno.IndiceFicha);
    }
        
    //En este turno robamos una ficha del monton (Set)
    public void setRobar() { 
        this.opcionTurno = OpcionTurno.Robar;
    }
    
    //Si hemos robado devolvemos un boolean indicando que se ha robado
    public boolean estaRobado() {
        return this.opcionTurno.equals(OpcionTurno.Robar);
    }
    
    //En este turno el jugador decide pasar del turno sin hacer nada (Set)
    public void setPasar() {
        this.opcionTurno = OpcionTurno.Pasar;
    }
    
    //Devolvemos un boolean para indicar que hemos pasado de este turno
    public boolean estaPasado() {
        return this.opcionTurno.equals(OpcionTurno.Pasar);
    }
}
