
package mx.edu.utng.tics.arbol;

/**
 * Clase main para pruebas de árbol
 * @author Susana Cárdenas Vázquez
 * @author cardenas.vazquez.0506@gmail.com
 */
public class PurebaArbol {
    public static void main(String[] args) {
        /**
         * Crear instancia de la clase gestora del árbol
         */
        ArbolBinario arbol = new ArbolBinario();
        
        System.out.println("Insertando valores: 50, 30, 70, 20, 40");
        
        /**
         * Insertar valores
         */
        arbol.insertar(50);
        arbol.insertar(30);
        arbol.insertar(70);
        arbol.insertar(20);
        arbol.insertar(40);
        
        /**
         * Ejecutar recorrido ara verificar el orden
         */
        arbol.recorrerInorden(); 
    }
    
}
