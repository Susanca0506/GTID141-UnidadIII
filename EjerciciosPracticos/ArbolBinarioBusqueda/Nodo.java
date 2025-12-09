
package mx.edu.utng.tics.ejerciciospracticos;

/**
 *
 * @author Cárdenas Vázquez Susana
 * @author cardenas.vazquez.0506@gmail.com
 * Clase que simula los nodos del árbol binario de búsqueda
 */

public class Nodo {
    
    private int valor; /** Almacenara el valor */
    private Nodo izq; /** Almacenara el enlance al nodo izquerdo */
    private Nodo der; /** Almacenara el enlace al nodo derecho */

    /** Constructor */
    public Nodo(int v) {
        valor = v;
        izq = der = null;
    }

    /** Getters y Setters */
    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public Nodo getIzq() {
        return izq;
    }

    public void setIzq(Nodo izq) {
        this.izq = izq;
    }

    public Nodo getDer() {
        return der;
    }

    public void setDer(Nodo der) {
        this.der = der;
    }
     
}

