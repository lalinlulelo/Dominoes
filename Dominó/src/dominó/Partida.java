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
 */
public class Partida {
    private static final int numeroJugadores = 2;
    private static final int jugador1 = 0;
    private static final int jugador2 = 1;    
    private static final int fichasIniciales = 7;
    private static final String movimientoInvalido = "Error de Partida: Ficha incompatible, seleccione otra opcion.";
    
    //Enumerado para el turno
    enum Turn {
        Jugador1, Jugador2
    };

    private Mesa mesa;
    private Monton monton; 
    private Jugador[] jugadores;//Array de jugadores, posicion 0 jugador1 y poscion 1 jugador 2
    private Turn turno;
    private int contadorTurno;
    
    public Partida() {
        this.mesa = new Mesa();       
        this.monton = new Monton();
        
        this.jugadores = new Jugador[numeroJugadores];
        //Inicializamos 2 jugadores con el bucle for
        for (int index = 0; index < numeroJugadores; index++) {
            this.jugadores[index] = new Jugador();
        }
        
        this.turno = Turn.Jugador1;//El turno inicial es del jugador 1
        this.contadorTurno = 1;

        //En este for repartimos las fichas a los jugadores
        for (int index = 1; index <= fichasIniciales; index++) {
            //Los 2 jugadores roban fichas aleatorias del monton
            this.jugadores[jugador1].robarFicha(this.monton.choose());            
            this.jugadores[jugador2].robarFicha(this.monton.choose());
        }
    }
    
    //Alterna entre el turno de los jugadores
    private void nextTurn() {
        if (this.turno == Turn.Jugador2) {
            this.turno = Turn.Jugador1;
        } else {
            this.turno = Turn.Jugador2;
        }
    }
    
    @SuppressWarnings("null")
    public void start() {        
        Jugador player = null;
        
        boolean partidaTerminada = false;
        boolean robado;
        //Mientras la partida no este terminada se sigue jugando
        while (!partidaTerminada) {
            robado = false;
            
            //Imprime por pantalla el turno del jugador correspondiente
            System.out.print("Turno " + this.contadorTurno + " (Jugador ");
            switch(this.turno) {
                case Jugador1:
                    System.out.print("1");
                    break;
                case Jugador2:
                    System.out.print("2");
            }
            System.out.println("):");

            System.out.print("Fichas Jugador ");
            switch(this.turno) {
                case Jugador1:
                    System.out.print((jugador1 + 1) + ": ");
                    player = this.jugadores[jugador1];//El puntero apunta al array del indice 0 de jugadores
                    break;
                case Jugador2:
                    System.out.print((jugador2 + 1) + ": ");
                    player = this.jugadores[jugador2];//El puntero apunta al array del indice 1 de jugadores
            }
            System.out.println(player);//Invocamos al toString de la clase Jugador
            
            //Al principio iniciamos al jugador sin completar ninguna opcion
            boolean opcionCompletada = false;
            while (!opcionCompletada) {
                OpcionJugador opcionJugador = player.escogerOpcion();
                //Si se ha puesto un indice se inicia lo del If
                if (opcionJugador.estaIndiceFicha()) {
                    //Me devuelve la ficha que se ha cogido
                    Ficha fichaJugador = player.elegirFicha(opcionJugador.getIndice());

                    char ladoMesa = player.ponerLadoMesa();
                    switch(ladoMesa) {
                        //Caso en el que decidamos poner la ficha por el inicio de la mesa
                        case Mesa.ladoInicial:
                            //Intenta coger una excepcion en caso de que la mesa este vacia
                            //si esta vacia directamente añade la ficha del jugador por el principio
                            try {
                                //Si este metodo lanza una excepcion directamente va a catch
                                Ficha tableBeginSideChip = mesa.cogerFichaPrincipio();
                                if (fichaJugador.isCompatibleWithLeftNumber(tableBeginSideChip)) {
                                    mesa.añadirFichaPrincipio(fichaJugador);
                                    
                                    player.borrarFicha(fichaJugador);
                                    
                                    opcionCompletada = true;
                                } else {
                                    System.out.println(movimientoInvalido);
                                }
                            } catch (NoSuchElementException exception) {
                                mesa.añadirFichaPrincipio(fichaJugador);
                                
                                player.borrarFicha(fichaJugador);
                                
                                opcionCompletada = true;
                            }                           
                            break;
                        //Caso en el que decidamos poner la ficha al final de la mesa   
                        case Mesa.ladoFinal:
                            try {
                                Ficha tableEndSideChip = mesa.cogerFichaFinal();
                                if (fichaJugador.isCompatibleWithRightNumber(tableEndSideChip)) {
                                    mesa.añadirFichaFinal(fichaJugador);
                                    
                                    player.borrarFicha(fichaJugador);
                                    
                                    opcionCompletada = true;
                                } else {
                                    System.out.println(movimientoInvalido);
                                }
                            } catch (NoSuchElementException exception) {
                                mesa.añadirFichaFinal(fichaJugador);
                                
                                player.borrarFicha(fichaJugador);
                                
                                opcionCompletada = true;
                            }
                    }
                //En caso de que no pongamos un indice y robamos del monton
                } else if (opcionJugador.estaRobado()) {
                    try {
                        player.robarFicha(this.monton.choose());
                        
                        robado = true;
                        opcionCompletada = true;
                    } catch (NoSuchElementException exception) {
                        //Si esta vacio se lanza una excepcion con el mensaje
                        System.out.println(exception.getMessage());
                    } 
                } else {
                    opcionCompletada = true;
                }
            }//Se cierra el bucle while despues de completar una jugada

            System.out.println("El monton tiene: " + this.monton.size() + " fichas");
            System.out.println("Mesa: " + this.mesa);
            
            //La partida termina cuando se cumple las siguientes condiciones
            partidaTerminada = (!player.tieneFichas()) || (juegoTerminado());
            //Y si no esta terminada pasa al siguiente turno
            if ((!partidaTerminada) && (!robado)) {
                this.nextTurn();                
            }
            contadorTurno++;
            //El espacio que hay entre 2 turnos
            System.out.println();
            System.out.println();
        }
        
        int puntosTotales = 0;
        if (juegoTerminado()) {
            int puntosJugador1 = this.jugadores[jugador1].getPuntos();
            int puntosJugador2 = this.jugadores[jugador2].getPuntos();
            if (puntosJugador1 < puntosJugador2) {
                System.out.println("Ganador: Jugador 1");
            } else if (puntosJugador2 < puntosJugador1) {
                System.out.println("Ganador: Jugador 2");
            } else {
                System.out.println("Empate");
            }
            puntosTotales = Math.abs(puntosJugador1 - puntosJugador2);
        } else {
            if (player != null) {
                //Si el ultimo turno es del jugador 1 devolvemos los puntos del 2
                if (turno == Turn.Jugador1) {
                    System.out.println("Jugador 1");
                    puntosTotales = this.jugadores[jugador2].getPuntos();
                } else {
                //Si el ultimo turno es del jugador 2 devolvemos los puntos del 1    
                    System.out.println("Jugador 2");
                    puntosTotales = this.jugadores[jugador1].getPuntos();
                }
            }
        }
        System.out.println("Puntos totales: " + puntosTotales);
    }
    
    private boolean juegoTerminado() {
        boolean terminado = false;
        
        try {
            Ficha fichaPrincipio = this.mesa.cogerFichaPrincipio();
            Ficha fichaFinal = this.mesa.cogerFichaFinal();
            //Si el principo y final tienen el mismo número
            if (fichaPrincipio.getLeftNumber() == fichaFinal.getRightNumber()) {
                int numeroABuscar = fichaPrincipio.getLeftNumber(); //Tambien vale getRightNumber
                int contadorNumero = 0;
                //Se inicia un iterador para comprobar si en la mesa están 
                //los 7 fichas como máximo que contienen el mismo número
                Iterator<Ficha> tableIterator = this.mesa.iterator();
                while (tableIterator.hasNext()) {
                    Ficha tableChip = tableIterator.next();
                    if (tableChip.containsNumber(numeroABuscar)) {
                        contadorNumero++;
                    }
                }
                //En caso de llegar a 7 como el número como máximo
                //que se puede repetir un número, entonces se sale del 
                //if y devolvemos que el juego ha terminado
                terminado = (contadorNumero == 7);
            }
        } catch (NoSuchElementException exception) {
        //Es una excepcion que se podria contemplar   
        }
        
        return terminado;
    }
}
