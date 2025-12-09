package mx.edu.utng.tics.ejerciciospracticos;

/**
 *
 * @author Cárdenas Vázquez Susana
 * @author cardenas.vazquez.0506@gmail.com
 * 
 * Clase principal que simula un árbol DOM HTML con interfaz gráfica.
 * Permite agregar nodos HTML de forma visual y genera el código HTML correspondiente.
 * 
 */

import javax.swing.*;
import javax.swing.tree.*;

/** 
 * Clase principal que simula un árbol DOM HTML con interfaz gráfica.
 * Permite agregar nodos HTML de forma visual y genera el código HTML correspondiente.
 */
public class SimuladorDOMconArbol extends JFrame {

    /** Componente visual que muestra el árbol DOM */
    private JTree tree;
    
    /** Modelo de datos que controla la estructura del árbol */
    private DefaultTreeModel treeModel;
    
    /** Área de texto que muestra el código HTML generado */
    private JTextArea htmlPreview;
    
    /** ComboBox para seleccionar el tipo de etiqueta HTML */
    private JComboBox<String> cbEtiqueta;
    
    /** Campo de texto para ingresar el contenido de la etiqueta */
    private JTextField txtTexto;

    /** 
     * Constructor que inicializa la interfaz gráfica del simulador DOM 
     */
    public SimuladorDOMconArbol() {
        /** Configuración básica de la ventana */
        setTitle("Simulador DOM HTML");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new java.awt.BorderLayout());

        /** ------- ÁRBOL ------- */
        /** Crea el nodo raíz del árbol con la etiqueta html */
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("html");
        treeModel = new DefaultTreeModel(root);
        tree = new JTree(treeModel);
        JScrollPane scrollArbol = new JScrollPane(tree);

        /** ------- PREVIEW HTML ------- */
        /** Configura el área de texto para mostrar el código HTML generado */
        htmlPreview = new JTextArea();
        htmlPreview.setEditable(false);
        htmlPreview.setFont(new java.awt.Font("Monospaced", 0, 14));
        JScrollPane scrollHTML = new JScrollPane(htmlPreview);

        /** ------- SPLITPANE ------- */
        /** Divide la ventana en dos paneles: árbol (izquierda) y preview HTML (derecha) */
        JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, scrollArbol, scrollHTML);
        split.setDividerLocation(300); // Ancho panel izquierdo
        add(split, java.awt.BorderLayout.CENTER);

        /** ------- PANEL INFERIOR ------- */
        /** Panel que contiene los controles para agregar nuevos nodos */
        JPanel panelInferior = new JPanel();

        panelInferior.add(new JLabel("Etiqueta:"));

        /** ComboBox con las etiquetas HTML disponibles */
        cbEtiqueta = new JComboBox<>(new String[]{
                "p", "h1", "h2", "footer", "header", "div", "span"
        });
        panelInferior.add(cbEtiqueta);

        panelInferior.add(new JLabel("Texto:"));
        txtTexto = new JTextField(20);
        panelInferior.add(txtTexto);

        /** Botón para que inicializa la acción para agregar un nodo */
        JButton btnAgregarNodo = new JButton("Agregar Nodo");
        panelInferior.add(btnAgregarNodo);

        add(panelInferior, java.awt.BorderLayout.SOUTH);

        /** ------- ACCIÓN AGREGAR ------- */
        /** Listener que maneja la acción de agregar un nuevo nodo al árbol */
        btnAgregarNodo.addActionListener(e -> {
            /** Obtiene el nodo actualmente seleccionado en el árbol */
            DefaultMutableTreeNode seleccionado =
                    (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();

            /** Valida que haya un nodo seleccionado */
            if (seleccionado == null) {
                JOptionPane.showMessageDialog(this, "Selecciona un nodo del árbol.");
                return;
            }

            /** Obtiene la etiqueta y el texto ingresados por el usuario */
            String tag = cbEtiqueta.getSelectedItem().toString();
            String texto = txtTexto.getText().trim();

            /** Valida que se haya ingresado texto */
            if (texto.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Escribe un texto.");
                return;
            }

            /** Crea un nuevo nodo con formato "etiqueta – texto" */
            DefaultMutableTreeNode nuevo = new DefaultMutableTreeNode(tag + " – " + texto);
            
            /** Agrega el nuevo nodo como hijo del nodo seleccionado */
            seleccionado.add(nuevo);
            
            /** Recarga el árbol para mostrar los cambios */
            treeModel.reload();

            /** Actualiza la vista previa del HTML */
            actualizarPreview();
        });

        /** Listener que actualiza la vista previa cuando se selecciona un nodo diferente */
        tree.addTreeSelectionListener(e -> actualizarPreview());
    }

    /** 
     * Genera el código HTML correspondiente a partir de un nodo del árbol.
     * Método recursivo que recorre todo el árbol y construye el HTML con formato.
     */
    private String generarHTML(DefaultMutableTreeNode node, int nivel) {

    StringBuilder html = new StringBuilder();

    /** Calcula la sangría según el nivel de profundidad (4 espacios por nivel) */
    String indent = "    ".repeat(nivel); /** 4 espacios por nivel (Sangría aproximada)*/

    /** Obtiene la etiqueta y el texto del nodo parseando su contenido */
    String nodo = node.toString();
    String[] partes = nodo.split(" – ");
    String tag = partes[0];
    String texto = partes.length > 1 ? partes[1] : "";

    /** Genera la etiqueta de apertura con la sangría correspondiente */
    html.append(indent).append("<").append(tag).append(">");

    /** Agrega el texto del nodo si existe */
    if (!texto.isEmpty()) {
        html.append(texto);
    }
    html.append("\n");

    /** Procesa recursivamente todos los nodos hijos */
    for (int i = 0; i < node.getChildCount(); i++) {
        html.append(generarHTML((DefaultMutableTreeNode) node.getChildAt(i), nivel + 1));
    }

    /** Genera la etiqueta de cierre con la misma sangría */
    html.append(indent).append("</").append(tag).append(">\n");

    return html.toString();
    }

    /** 
     * Método para actualizar el área de texto con el código HTML completo generado desde la raíz del árbol 
     */
    private void actualizarPreview() {
        /** Obtiene el nodo raíz del árbol */
        DefaultMutableTreeNode root = (DefaultMutableTreeNode) treeModel.getRoot();
        
        /** Genera el HTML completo desde la raíz */
        String html = generarHTML(root, 0);
        
        /** Muestra el HTML en el área de texto */
        htmlPreview.setText(html);
    }

    /** 
     * Método main que inicia la aplicación 
     */
    public static void main(String[] args) {
        /** Ejecuta la creación de la interfaz en el hilo de eventos de Swing */
        SwingUtilities.invokeLater(() -> new SimuladorDOMconArbol().setVisible(true));
    }
}