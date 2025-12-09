
package mx.edu.utng.tics.arbol;

/**
 * Estructura del nodo del árbol
 * @author Susana Cárdenas Vázquez
 * @author cardenas.vazquez.0506@gmail.com
 */
public class NodoArbol {
    private int dato;
    
    public NodoArbol hijoIzquierdo;
    public NodoArbol hijoDerecho;
    
    //Constructor

    public NodoArbol(int valor) {
        this.dato = valor;
        this.hijoIzquierdo = null;
        this.hijoDerecho = null;
    }

    /** Getters y setters */
    
    /** Getter para obtener el dato*/
    public int getDato() {
        return dato;
    }

    /** Setter para modificar el dado */
    public void setDato(int nuevoDato) {
        this.dato = nuevoDato;
    }
}
