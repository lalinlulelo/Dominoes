/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominó;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @autores Zhong Hao Lin Chen y Adrián David Morillas Marco
 */
public class Jugador {
    //A continuación se muestra los mensajes que requieren interacción del jugador
    private static final String opciones = "Escriba numero de ficha, o 'R' para 'robar' o 'P' para 'pasar':";
    private static final String opcionInvalida = "Error del jugador: Opcion invalida. Introduzca otra opcion";
    private static final String orden = "Escriba si la ficha se pone por el inicio 'I' o por el final 'F':";
    private static final String indiceInvalido = "Error del jugador: Indice de ficha invalida. Introduzca otra orden";
    private static final char robar = 'R';
    private static final char pasar = 'P';
    private static final char ponerInicio = 'I';
    private static final char ponerFinal = 'F';
    
    //La TAD jugador pertenece a una ArrayList que esta predefinida en Java
    private ArrayList<Ficha> fichas;//Almacena las fichas del jugador
    
    private Scanner input;

    public Jugador() {
        this.fichas = new ArrayList<>();
        this.input = new Scanner(System.in);
    }
    
    //Por medio de la clase que nos hemos creado llamado OpcionJuador
    //creamos un metodo que permite al jugador escoger distintas 
    //opciones dentro de su turno
    @SuppressWarnings("ConvertToTryWithResources")
    public OpcionJugador escogerOpcion() {
        //Creo un objeto de la clase OpcionJugador en el que de momento no hemos elegido nada
        OpcionJugador opcionJugador = new OpcionJugador();
        
        //Imprimimos por pantalla para saber si el jugador quiere poner ficha
        //robar o pasar de este turno
        System.out.println(opciones); 
        System.out.print("> ");
        
        boolean validOption = false;
        while ((!validOption) && (input.hasNext())) {
            if (input.hasNextInt()) { //Le pregunta al Scanner si lo introducido es de tipo entero
                int indiceFicha = input.nextInt();
                //Comprobar indice sea mayor o igual a 0 y indice menor 
                //que el tamaño del ArrayList
                if ((indiceFicha >= 0) && (indiceFicha < this.fichas.size())) {
                    opcionJugador.setIndice(indiceFicha);
                    
                    validOption = true;
                } else {
                    System.out.println(indiceInvalido);
                }
            } else { //Si no he introducido un entero si no un String, me meto por el else
                String cadena = input.next();
                if (cadena.length() == 1) {
                    char opcion = cadena.charAt(0);
                    switch(opcion) {
                        case robar:
                            opcionJugador.setRobar();//A la opcion de jugador le asignamos robar(set)
                            
                            validOption = true;
                            break;
                        case pasar:
                            opcionJugador.setPasar();//A la opcion de jugador le asignamos pasar(set)
                            
                            validOption = true;
                            break;
                        default:
                            System.out.println(opcionInvalida);
                    }
                }
            }
            
            if (!validOption) {
                System.out.println(orden);
                System.out.print("> ");
            }
        }       
        
        return opcionJugador;//Devolvemos la opcion del jugador
    }
    
    //Pregunta en que extremo de la mesa queremos introducir la ficha
    @SuppressWarnings("ConvertToTryWithResources")
    public char ponerLadoMesa() {
        char ladoMesa = 0;
        
        System.out.println(orden);
        System.out.print("> ");
        
        boolean validOption = false;
        while ((!validOption) && (input.hasNext())) {
            if (input.hasNext()) {
                String cadena = input.next();
                if (cadena.length() == 1) {
                    char opcion = cadena.charAt(0);
                    switch(opcion) {
                        case ponerInicio:
                            ladoMesa = ponerInicio;
                            
                            validOption = true;
                            break;
                        case ponerFinal:
                            ladoMesa = ponerFinal;
                            
                            validOption = true;
                            break;
                        default:
                            System.out.println(opcionInvalida);
                    }
                }
            }
            
            if (!validOption) {
                System.out.println(opciones);
                System.out.print("> ");
            }
        }
        
        return ladoMesa;
    }
    
    public boolean robarFicha(Ficha ficha) {
        return this.fichas.add(ficha);
    }
    
    public Ficha elegirFicha(int indice) throws IndexOutOfBoundsException {
        if ((indice < 0) || (indice >= this.fichas.size())) {
            throw new IndexOutOfBoundsException(indiceInvalido);
        }
        
        Ficha fichaElegida = this.fichas.get(indice);
        
        return fichaElegida;
    }

    public void borrarFicha(Ficha ficha){
        this.fichas.remove(ficha);
    }
    
    public boolean tieneFichas() {
        return !this.fichas.isEmpty();
    }
    
    //Se obtiene los puntos del jugador con un (get)
    public int getPuntos() {
        int puntos = 0;
        //Este for me permite recorrer una estructura de principio a fin
        for (Ficha ficha: this.fichas) {
            puntos += ficha.obtenerValor();
        }
        
        return puntos;
    }

    //Aqui nos encargaremos de imprimir por pantalla las fichas del jugador
    //y tambien su numero/indice correspondiente
    @Override
    public String toString() {
        String resultado = "";//La cadena resultado representa las fichas del jugador
        String resultado2= "";//La cadena resultado2 representa el numero de ficha
        
        int nFichas = 1;
        //Iniciamos un bucle for para que se quede guardado en resultado las fichas del jugador
        for (Ficha ficha: this.fichas) {
            //Al resultado le vamos añadiendo las fichas que tiene el jugador
            resultado += ficha;
            
            //Si el numero de fichas no es igual al tamaño de las fichas que tenemos
            //le ponemos un espacio y otra vez ejecutamos el bucle
            //para poner la siguiente ficha en el resultado
            if (nFichas!= this.fichas.size()) {
                resultado += " ";
            }
            //El contador de numero de fichas va aumentando hasta que
            //sea igual que el tamaño de las fichas que tenemos
            nFichas++;
        }
        
        int nFichas2 = 1;
        //Iniciamos un bucle for para que se quede guardaddo en resultado2
        //el numero/indice de las fichas del jugador
        for (Ficha ficha: this.fichas) {
            //Al resultado2 le vamos añadiendo los numeros de las fichas
            resultado2 += (nFichas2 - 1) + "     ";//Espacio entre numero de ficha

            //Si el numero de fichas no es igual al tamaño de las fichas que tenemos
            //le ponemos un espacio y otra vez ejecutamos el bucle
            //para poner la siguiente ficha en el resultado
            if (nFichas2!= this.fichas.size()) {
                resultado2 += "";
            }
            nFichas2++;
        }
        //Creamos una tercera cadena de caracteres String llamado resultado3
        //En resultado3 guardamos el resultado mas en la siguiente linea el 
        //resultado2, y por último devolvemos la cadena resultado3
        String resultado3 = resultado + "\n"
                + "Numero de ficha:    " + resultado2;
        return resultado3;
    }
}
