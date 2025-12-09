
package mx.edu.utng.tics.ejerciciospracticos;

/**
 *
 * @author Cárdenas Vázquez Susana
 * @author cardenas.vazquez.0506@gmail.com
 * 
 * Clase principal que proporciona una interfaz gráfica completa para interactuar
 * con un Árbol Binario de Búsqueda. Permite insertar, eliminar, buscar valores
 * y visualizar diferentes recorridos del árbol.
 * 
 */
import javax.swing.*;
import java.awt.*;


public class VisualArbolBinarioBusqueda extends JFrame {
    
    /** Instancia del árbol binario de búsqueda que se manipulará */
    ArbolBinarioBusqueda arbol = new ArbolBinarioBusqueda();
    
    /** Panel personalizado que dibuja visualmente el árbol */
    PanelArbol panel = new PanelArbol(arbol);
    
    /**
     * Constructor que inicializa toda la interfaz gráfica y sus componentes.
     * Configura la ventana, los controles y los eventos asociados a cada botón.
     */
    public VisualArbolBinarioBusqueda() {
        /** Configuración básica de la ventana principal */
        setTitle("Visualizador de Árbol Binario de Búsqueda");
        setSize(900, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        /** Panel superior de controles */
        JPanel arriba = new JPanel();
        
        /** Campo de texto para ingresar valores numéricos */
        JTextField txtValor = new JTextField(5);
        
        /** Botones para las operaciones del árbol */
        JButton btnInsertar = new JButton("Insertar");
        JButton btnEliminar = new JButton("Eliminar");
        JButton btnBuscar = new JButton("Buscar");
        JButton btnLimpiar = new JButton("Limpiar Árbol");
        
        /** Botones para los diferentes tipos de recorrido */
        JButton btnIn = new JButton("InOrden");
        JButton btnPre = new JButton("PreOrden");
        JButton btnPost = new JButton("PostOrden");
        
        /** Agrega todos los componentes al panel superior */
        arriba.add(new JLabel("Valor:"));
        arriba.add(txtValor);
        arriba.add(btnInsertar);
        arriba.add(btnEliminar);
        arriba.add(btnBuscar);
        arriba.add(btnLimpiar);
        arriba.add(new JLabel("Recorridos:"));
        arriba.add(btnIn);
        arriba.add(btnPre);
        arriba.add(btnPost);
        
        /** Agrega el panel de controles en la parte superior de la ventana */
        add(arriba, BorderLayout.NORTH);
        
        /** Agrega el panel de visualización del árbol en el centro */
        add(panel, BorderLayout.CENTER);
        
        /** Etiqueta para mostrar los resultados de los recorridos */
        JLabel lblRecorrido = new JLabel(" ");
        add(lblRecorrido, BorderLayout.SOUTH);
        
        /**
         * Evento del botón Insertar.
         * Obtiene el valor del campo de texto, lo inserta en el árbol
         * y actualiza la visualización.
         */
        btnInsertar.addActionListener(e -> {
            int v = Integer.parseInt(txtValor.getText());
            arbol.insertar(v);
            panel.repaint();
        });
        
        /**
         * Evento del botón Eliminar.
         * Obtiene el valor del campo de texto, lo elimina del árbol
         * y actualiza la visualización.
         */
        btnEliminar.addActionListener(e -> {
            int v = Integer.parseInt(txtValor.getText());
            arbol.eliminar(v);
            panel.repaint();
        });
        
        /**
         * Evento del botón Buscar.
         * Busca el valor en el árbol y muestra un mensaje indicando
         * si fue encontrado o no.
         */
        btnBuscar.addActionListener(e -> {
            int v = Integer.parseInt(txtValor.getText());
            Nodo res = arbol.buscar(v);
            JOptionPane.showMessageDialog(this,
                res != null ? "Encontrado" : "No encontrado");
        });
        
        /**
         * Evento del botón Limpiar Árbol.
         * Elimina todos los nodos del árbol estableciendo la raíz en null
         * y actualiza la visualización.
         */
        btnLimpiar.addActionListener(e -> {
            arbol.raiz = null;
            panel.repaint();
        });
        
        /**
         * Evento del botón InOrden.
         * Muestra el recorrido inorden del árbol en la etiqueta inferior.
         * Recorrido: Izquierda - Raíz - Derecha (valores ordenados).
         */
        btnIn.addActionListener(e -> lblRecorrido.setText("InOrden: " + arbol.inOrden()));
        
        /**
         * Evento del botón PreOrden.
         * Muestra el recorrido preorden del árbol en la etiqueta inferior.
         * Recorrido: Raíz - Izquierda - Derecha.
         */
        btnPre.addActionListener(e -> lblRecorrido.setText("PreOrden: " + arbol.preOrden()));
        
        /**
         * Evento del botón PostOrden.
         * Muestra el recorrido postorden del árbol en la etiqueta inferior.
         * Recorrido: Izquierda - Derecha - Raíz.
         */
        btnPost.addActionListener(e -> lblRecorrido.setText("PostOrden: " + arbol.postOrden()));
    }
    
    /**
     * Método principal que inicia la aplicación.
     * Crea una instancia de la ventana y la hace visible.
     */
    public static void main(String[] args) {
        new VisualArbolBinarioBusqueda().setVisible(true);
    }
}