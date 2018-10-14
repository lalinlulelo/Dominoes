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
public class Ficha {
    //Las partes de una ficha de domino lo vamos a diferenciar 
    //en el lado izquierdo y lado derecho
    private int leftNumber;  
    private int rightNumber;
    
    //Los numeros del domino son del 0 al 6
    public static final int MinNumber = 0;//Final identifica que es una constante
    public static final int MaxNumber = 6;
    
    public Ficha(int leftNumber, int rightNumber){
        //A los parametros le asignamos cada atributo de la ficha
        this.leftNumber = leftNumber;  
        this.rightNumber = rightNumber;
    }
    
    //Devuelve el valor del lado izquierdo y lado derecho
    public int getLeftNumber(){
        return this.leftNumber;
    }
    public int getRightNumber(){
        return this.rightNumber;
    }
    
    //Imprime por pantalla entre corchetes el numero del 
    //lado izquierdo y lado derecho de una ficha de domino
    @Override
    public String toString(){
        return "[" + this.leftNumber + "|" +this.rightNumber + "]";
    }
   
    //Esto sirve para invertir el lado de la ficha
    private void invert() {
        int auxiliar = this.leftNumber; //Guardamos el lado izquiedo en un auxiliar
        this.leftNumber = this.rightNumber; //Convertimos el lado izquierdo en el derecho
        this.rightNumber = auxiliar;//El lado derecho toma el valor guardado
    }
    
    //Obtener el valor del lado izquierdo y derecho del domino
    public int obtenerValor() {
        return this.leftNumber + this.rightNumber;
    }

    //Comprueba si es compatible con el número del lado izquierdo    
    public boolean isCompatibleWithLeftNumber(Ficha otraFicha) {
        boolean isCompatible = (this.leftNumber == otraFicha.leftNumber) || (this.rightNumber == otraFicha.leftNumber);
        if (isCompatible) {
            if (this.rightNumber != otraFicha.leftNumber) {
                this.invert();
            }
        }
        
        return isCompatible;
    }
    
    //Comprueba si es compatible con el número del lado derecho  
    public boolean isCompatibleWithRightNumber(Ficha otraFicha) {
        boolean isCompatible =  (this.leftNumber == otraFicha.rightNumber) || (this.rightNumber == otraFicha.rightNumber);
        if (isCompatible) {
            if (this.leftNumber != otraFicha.rightNumber) {  //Si no es igual el lado izquierdo se devuelve el lado derecho
                this.invert();
            }
        }
        
        return isCompatible;
    }
    
    //Comprueba si contiene uno de los números y devuelve un boolean tanto 
    //si encuentra el número del lado derecho como izquierdo 
    public boolean containsNumber(int fragment) {
        return (this.leftNumber == fragment) || (this.rightNumber == fragment);
    }
    
    //Resdie en la clase Object, mira si 2 objetos de clase Ficha son iguales
    @Override
    public boolean equals(Object otroObjeto) {  //Puedo recibir cualquier cosa porque heredan de la clase Object
        if ((otroObjeto == null) || (!(otroObjeto instanceof Ficha))) { //instanceof pregunta si otroObjeto es de clase Ficha
            return false; //Si no es de clase ficha o el Objeto esta vacio devuelvo un falso
        }
        
        Ficha otraFicha = (Ficha)otroObjeto; //Casting: Hago reconversion de otroObjeto en clase Ficha
        
        //Si las 2 referencias apuntan a la misma ficha devuelven la misma ficha y un true
        if (this == otraFicha) {
            return true;
        }
        
        return (((this.leftNumber == otraFicha.leftNumber) && 
                (this.rightNumber == otraFicha.rightNumber)) ||
                ((this.leftNumber == otraFicha.rightNumber) && 
                (this.rightNumber == otraFicha.leftNumber)));
    }
}
