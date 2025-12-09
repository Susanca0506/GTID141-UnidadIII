
package mx.edu.utng.tics.ejerciciospracticos;

/**
 *
 * @author Cárdenas Vázquez Susana
 * @author cardenas.vazquez.0506@gmail.com
 * 
 * Representación visual de un Árbol Binario de Búsqueda.
 * Dibuja los nodos como círculos y las conexiones como líneas.
 */

import java.awt.*;
import javax.swing.JPanel;

public class PanelArbol extends JPanel {
    
    /** Referencia al árbol binario que se va a visualizar */
    ArbolBinarioBusqueda arbol;
    
    /**
     * Constructor que inicializa el panel con un árbol específico.
     */
    public PanelArbol(ArbolBinarioBusqueda a) {
        this.arbol = a;
    }
    
    /**
     * Método sobrescrito que se encarga de pintar el componente.
     * Se llama automáticamente cuando el panel necesita ser redibujado.
     */
    @Override
    protected void paintComponent(Graphics g) {
        /** Llama al método padre para limpiar el panel */
        super.paintComponent(g);
        
        /** Si el árbol tiene nodos, comienza a dibujar desde la raíz */
        if (arbol.raiz != null) {
            
            /** Dibuja el árbol comenzando desde el centro horizontal del panel */
            dibujarNodo(g, arbol.raiz, getWidth() / 2, 40, getWidth() / 4);
        }
    }
    
    /**
     * Método recursivo privado que dibuja un nodo y sus conexiones con sus hijos.
     * Utiliza recursión para dibujar todo el subárbol.
     */
    private void dibujarNodo(Graphics g, Nodo n, int x, int y, int espacio) {
        /** Dibuja el círculo del nodo en color azul */
        g.setColor(Color.BLUE);
        g.fillOval(x - 20, y - 20, 40, 40);
        
        /** Dibuja el valor del nodo en color blanco dentro del círculo */
        g.setColor(Color.WHITE);
        g.drawString(String.valueOf(n.getValor()), x - 6, y + 4);
        
        /** Cambia el color a negro para dibujar las líneas de conexión */
        g.setColor(Color.BLACK);
        
        /** Si existe hijo izquierdo, dibuja la línea y el subárbol izquierdo */
        if (n.getIzq() != null) {
            
            /** Dibuja línea desde el nodo actual hacia abajo a la izquierda */
            g.drawLine(x, y, x - espacio, y + 60);
            
            /** Recursivamente dibuja el subárbol izquierdo */
            dibujarNodo(g, n.getIzq(), x - espacio, y + 60, espacio / 2);
        }
        
        /** Si existe hijo derecho, dibuja la línea y el subárbol derecho */
        if (n.getDer() != null) {
            
            /** Dibuja línea desde el nodo actual hacia abajo a la derecha */
            g.drawLine(x, y, x + espacio, y + 60);
            
            /** Recursivamente dibuja el subárbol derecho */
            dibujarNodo(g, n.getDer(), x + espacio, y + 60, espacio / 2);
        }
    }
}