
package mx.edu.utng.tics.ejerciciospracticos;

/**
 *
 * @author Cárdenas Vázquez Susana
 * @author cardenas.vazquez.0506@gmail.com
 * 
 * Clase que implementa un Árbol Binario de Búsqueda (ABB).
 * Permite insertar, buscar, eliminar nodos y realizar recorridos del árbol.
 */

class ArbolBinarioBusqueda {
    /** Nodo raíz del árbol */
    Nodo raiz;
    
    /**
     * Método público para insertar un valor en el árbol.
     */
    public void insertar(int v) {
        raiz = insertarRec(raiz, v);
    }
    
    /**
     * Método recursivo privado que inserta un valor en el árbol.
     * Si el valor es menor que el nodo actual, se inserta en el subárbol izquierdo.
     * Si el valor es mayor, se inserta en el subárbol derecho.
     */
    private Nodo insertarRec(Nodo actual, int v) {
        /** Si el nodo actual es nulo, crea un nuevo nodo con el valor */
        if (actual == null) {
            return new Nodo(v);
        }
        
        /** Si el valor es menor que el nodo actual, inserta en el subárbol izquierdo */
        if (v < actual.getValor())
            actual.setIzq(insertarRec(actual.getIzq(), v));
        
        /** Si el valor es mayor que el nodo actual, inserta en el subárbol derecho */
        else if (v > actual.getValor())
            actual.setDer(insertarRec(actual.getDer(), v));
        
        return actual;
    }
    
    /**
     * Método público para buscar un valor en el árbol.
     */
    public Nodo buscar(int v) {
        return buscarRec(raiz, v);
    }
    
    /**
     * Método recursivo privado que busca un valor en el árbol.
     * Utiliza las propiedades del ABB para búsqueda eficiente.
     */
    private Nodo buscarRec(Nodo actual, int v) {
        /** Si el nodo es nulo o contiene el valor buscado, retorna el nodo */
        if (actual == null || actual.getValor() == v)
            return actual;
        
        /** Si el valor es menor, busca en el subárbol izquierdo, sino en el derecho */
        return v < actual.getValor()
                ? buscarRec(actual.getIzq(), v)
                : buscarRec(actual.getDer(), v);
    }
    
    /**
     * Método público para eliminar un valor del árbol.
     */
    public void eliminar(int v) {
        raiz = eliminarRec(raiz, v);
    }
    
    /**
     * Método recursivo privado que elimina un nodo del árbol.
     * Maneja tres casos: nodo sin hijos, nodo con un hijo, y nodo con dos hijos.
     */
    private Nodo eliminarRec(Nodo actual, int v) {
        
        /** Si el nodo es nulo, no hay nada que eliminar */
        if (actual == null) return null;
        
        /** Si el valor es menor, busca en el subárbol izquierdo */
        if (v < actual.getValor())
            actual.setIzq(eliminarRec(actual.getIzq(), v));
        
        /** Si el valor es mayor, busca en el subárbol derecho */
        else if (v > actual.getValor())
            actual.setDer(eliminarRec(actual.getDer(), v));
        
        /** Si encontró el nodo a eliminar */
        else {
            
            /** Caso 1: Nodo sin hijo izquierdo, retorna el hijo derecho */
            if (actual.getIzq() == null) return actual.getDer();
            
            /** Caso 2: Nodo sin hijo derecho, retorna el hijo izquierdo */
            if (actual.getDer() == null) return actual.getIzq();
            
            /** Caso 3: Nodo con dos hijos */
            /** Encuentra el valor mínimo del subárbol derecho (sucesor inorden) */
            actual.setValor(minValor(actual.getDer()));
            
            /** Elimina el sucesor del subárbol derecho */
            actual.setDer(eliminarRec(actual.getDer(), actual.getValor()));
        }
        return actual;
    }
    
    /**
     * Encuentra el valor mínimo en un subárbol.
     * El mínimo siempre está en el nodo más a la izquierda.
     */
    private int minValor(Nodo nodo) {
        /** Recorre hacia la izquierda hasta encontrar el nodo más a la izquierda */
        while (nodo.getIzq() != null)
            nodo = nodo.getIzq();
        return nodo.getValor();
    }
   
    /**
     * Método público para recorrer el árbol en inorden (izquierda-raíz-derecha).
     * Devuelve los valores ordenados de menor a mayor.
     */
    public String inOrden() {
        return inO(raiz).trim();
    }
    
    /**
     * Método recursivo privado para recorrido inorden.
     */
    private String inO(Nodo a) {
        /** Caso base: si el nodo es nulo, retorna cadena vacía */
        if (a == null) return "";
        
        /** Recorre: izquierda + raíz + derecha */
        return inO(a.getIzq()) + a.getValor() + " " + inO(a.getDer());
    }
    
    /**
     * Método público para recorrer el árbol en preorden (raíz-izquierda-derecha).
     */
    public String preOrden() {
        return preO(raiz).trim();
    }
    
    /**
     * Método recursivo privado para recorrido preorden.
     */
    private String preO(Nodo a) {
        /** Caso base: si el nodo es nulo, retorna cadena vacía */
        if (a == null) return "";
        
        /** Recorre: raíz + izquierda + derecha */
        return a.getValor() + " " + preO(a.getIzq()) + preO(a.getDer());
    }
    
    /**
     * Método público para recorrer el árbol en postorden (izquierda-derecha-raíz).
     */
    public String postOrden() {
        return postO(raiz).trim();
    }
    
    /**
     * Método recursivo privado para recorrido postorden.
     */
    private String postO(Nodo a) {
        /** Caso base: si el nodo es nulo, retorna cadena vacía */
        if (a == null) return "";
        
        /** Recorre: izquierda + derecha + raíz */
        return postO(a.getIzq()) + postO(a.getDer()) + a.getValor() + " ";
    }
}