
package mx.edu.utng.tics.arbol;

/**
 * Controlador del árbol
 * @author Susana Cárdenas Vázquez
 * @author cardenas.vazquez.0506@gmail.com
 */

public class ArbolBinario {
    private NodoArbol raiz;
    
    public ArbolBinario(){
        this.raiz = null;
    }
    
    /** 
     * Método Público de Inserción: Punto de entrada para el usuario.
     * Inicia la recursión desde la raíz.
     */
    
    public void insertar(int valor){
        this.raiz = insertarRecursivo(this.raiz, valor);
    }
    
    /**
* Método Privado y Recursivo de Inserción.
* @param actual El nodo que se está examinando en la recursión.
* @param valor El valor a insertar.
* @return El nodo modificado o el nuevo nodo creado.
*/
    private NodoArbol insertarRecursivo(NodoArbol actual, int valor){
        /**
         * Caso Base: si el nodo actual es null, encontramos la posición, 
         * creamos el nuevo nodo y lo retornamos.
         */
        if(actual == null){
            return new NodoArbol(valor);
        }
        
        if(valor < actual.getDato()){
            actual.hijoIzquierdo = insertarRecursivo(actual.hijoIzquierdo, valor);
        } else if (valor > actual.getDato()){
            actual.hijoDerecho = insertarRecursivo(actual.hijoDerecho, valor);
        }
        
        /**
         * Si valor == actual.getDato(), se ignora, ya que no permite duplicados.
         */
        return actual;
    }
    
    /**
     * Método Público de recorrido inorden
     * Inicia la recursión desde la raíz y muestra el resultado.
     */
    
    public void recorrerInorden(){
        System.out.print("Recorrido Inorden: ");
        recorrerInordenRecursivo(this.raiz);
        System.out.println();     
    }
    
    /**
     * Método privado y recursivo de recorrido Inorden (Izquierda a Raiz a Derecha)
     */
    private void recorrerInordenRecursivo(NodoArbol nodo){
        if(nodo != null){
            recorrerInordenRecursivo(nodo.hijoIzquierdo); //Recorrer izquierda
            System.out.println(nodo.getDato()+" "); //Raiz
            recorrerInordenRecursivo(nodo.hijoDerecho); //Recorrer derecha
        }
    }
}
