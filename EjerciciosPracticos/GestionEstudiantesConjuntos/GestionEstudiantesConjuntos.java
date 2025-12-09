
package mx.edu.utng.tics.ejerciciospracticos;

/**
 *
 * @author Cárdenas Vázquez Susana
 * @author cardenas.vazquez.0506@gmail.com
 * 
 * Aplicación de gestión de estudiantes usando conjuntos (Set) de Java.
 * Permite administrar estudiantes de una clase con operaciones de conjuntos.
 */
import javax.swing.*;
import java.awt.*;
import java.util.HashSet;
import java.util.Set;

public class GestionEstudiantesConjuntos extends JFrame {
    
    /** Conjunto principal que almacena los estudiantes del grupo A */
    private Set<Estudiante> grupoA;
    
    /** Conjunto auxiliar que almacena los estudiantes del grupo B */
    private Set<Estudiante> grupoB;
    
    /** Área de texto para mostrar los estudiantes */
    private JTextArea areaEstudiantes;
    
    /** Campos de texto para capturar los 5 datos del estudiante */
    private JTextField txtMatricula;
    private JTextField txtNombre;
    private JTextField txtEdad;
    private JTextField txtCarrera;
    private JTextField txtPromedio;
    
    /** ComboBox para seleccionar el grupo */
    private JComboBox<String> comboGrupo;
    
    /**
     * Constructor principal que inicializa la interfaz gráfica
     * y los conjuntos de datos.
     */
    public GestionEstudiantesConjuntos() {
        
        /** Inicializa los conjuntos HashSet */
        grupoA = new HashSet<>();
        grupoB = new HashSet<>();
        
        /** Configuración de la ventana principal */
        setTitle("Gestor de Estudiantes - Conjuntos Java");
        setSize(1000, 650);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        
        /** Panel superior con formulario de entrada */
        JPanel panelEntrada = crearPanelEntrada();
        add(panelEntrada, BorderLayout.NORTH);
        
        /** Panel central con área de visualización */
        areaEstudiantes = new JTextArea();
        areaEstudiantes.setEditable(false);
        areaEstudiantes.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scroll = new JScrollPane(areaEstudiantes);
        add(scroll, BorderLayout.CENTER);
        
        /** Panel inferior con botones de operaciones */
        JPanel panelOperaciones = crearPanelOperaciones();
        add(panelOperaciones, BorderLayout.SOUTH);
        
        /** Agrega algunos estudiantes de ejemplo */
        agregarEstudiantesEjemplo();
        actualizarVisualizacion();
    }
    
    /**
     * Crea el panel superior con los campos de entrada de datos.
     */
    private JPanel crearPanelEntrada() {
        JPanel panel = new JPanel(new GridLayout(4, 4, 5, 5));
        panel.setBorder(BorderFactory.createTitledBorder("Datos del Estudiante (5 campos)"));
        
        /** Campo 1: Matrícula */
        panel.add(new JLabel("Matrícula:"));
        txtMatricula = new JTextField(15);
        panel.add(txtMatricula);
        
        /** Campo 2: Nombre */
        panel.add(new JLabel("Nombre:"));
        txtNombre = new JTextField(15);
        panel.add(txtNombre);
        
        /** Campo 3: Edad */
        panel.add(new JLabel("Edad:"));
        txtEdad = new JTextField(15);
        panel.add(txtEdad);
        
        /** Campo 4: Carrera */
        panel.add(new JLabel("Carrera:"));
        txtCarrera = new JTextField(15);
        panel.add(txtCarrera);
        
        /** Campo 5: Promedio */
        panel.add(new JLabel("Promedio:"));
        txtPromedio = new JTextField(15);
        panel.add(txtPromedio);
        
        /** Selector de grupo */
        panel.add(new JLabel("Grupo:"));
        comboGrupo = new JComboBox<>(new String[]{"Grupo A", "Grupo B"});
        panel.add(comboGrupo);
        
        /** Botón para agregar estudiante */
        JButton btnAgregar = new JButton("Agregar Estudiante");
        btnAgregar.addActionListener(e -> agregarEstudiante());
        panel.add(btnAgregar);
        
        /** Botón para limpiar campos */
        JButton btnLimpiar = new JButton("Limpiar Campos");
        btnLimpiar.addActionListener(e -> limpiarCampos());
        panel.add(btnLimpiar);
        
        return panel;
    }
    
    /**
     * Crea el panel inferior con botones para operaciones de conjuntos.
     */
    private JPanel crearPanelOperaciones() {
        JPanel panel = new JPanel(new GridLayout(2, 4, 5, 5));
        panel.setBorder(BorderFactory.createTitledBorder("Operaciones con Conjuntos"));
        
        /** Operación 1: Mostrar tamaño del conjunto (size) */
        JButton btnTamanio = new JButton("1. Ver Tamaño");
        btnTamanio.addActionListener(e -> mostrarTamanio());
        panel.add(btnTamanio);
        
        /** Operación 2: Verificar si está vacío (isEmpty) */
        JButton btnVacio = new JButton("2. ¿Está Vacío?");
        btnVacio.addActionListener(e -> verificarVacio());
        panel.add(btnVacio);
        
        /** Operación 3: Buscar estudiante (contains) */
        JButton btnBuscar = new JButton("3. Buscar Estudiante");
        btnBuscar.addActionListener(e -> buscarEstudiante());
        panel.add(btnBuscar);
        
        /** Operación 4: Eliminar estudiante (remove) */
        JButton btnEliminar = new JButton("4. Eliminar Estudiante");
        btnEliminar.addActionListener(e -> eliminarEstudiante());
        panel.add(btnEliminar);
        
        /** Operación 5: Vaciar conjunto (clear) */
        JButton btnVaciar = new JButton("5. Vaciar Grupo");
        btnVaciar.addActionListener(e -> vaciarGrupo());
        panel.add(btnVaciar);
        
        /** Operación 6: Unión de conjuntos (addAll) */
        JButton btnUnion = new JButton("6. Unir Grupos");
        btnUnion.addActionListener(e -> unirGrupos());
        panel.add(btnUnion);
        
        /** Operación 7: Intersección de conjuntos (retainAll) */
        JButton btnInterseccion = new JButton("7. Intersección");
        btnInterseccion.addActionListener(e -> interseccionGrupos());
        panel.add(btnInterseccion);
        
        /** Operación 8: Diferencia de conjuntos (removeAll) */
        JButton btnDiferencia = new JButton("8. Diferencia A-B");
        btnDiferencia.addActionListener(e -> diferenciaGrupos());
        panel.add(btnDiferencia);
        
        return panel;
    }
    
    /**
     * Operación 1: Agrega un nuevo estudiante al conjunto seleccionado.
     * Utiliza add() del Set, que no permite duplicados.
     */
    private void agregarEstudiante() {
        if (validarCampos()) {
            Estudiante estudiante = crearEstudianteDesdeFormulario();
            Set<Estudiante> grupoSeleccionado = obtenerGrupoSeleccionado();
            
            /** add() retorna false si el elemento ya existe en el conjunto */
            boolean agregado = grupoSeleccionado.add(estudiante);
            
            if (agregado) {
                JOptionPane.showMessageDialog(this, 
                    "Estudiante agregado exitosamente al " + comboGrupo.getSelectedItem());
                limpiarCampos();
                actualizarVisualizacion();
            } else {
                JOptionPane.showMessageDialog(this, 
                    "Este estudiante ya existe (matrícula duplicada)");
            }
        }
    }
    
    /**
     * Operación 2: Muestra el tamaño del conjunto seleccionado.
     * Utiliza size() del Set para contar elementos.
     */
    private void mostrarTamanio() {
        Set<Estudiante> grupo = obtenerGrupoSeleccionado();
        String nombreGrupo = (String) comboGrupo.getSelectedItem();
        
        /** size() retorna el número de elementos en el conjunto */
        int tamanio = grupo.size();
        
        JOptionPane.showMessageDialog(this, 
            nombreGrupo + " contiene " + tamanio + " estudiante(s)");
    }
    
    /**
     * Operación 3: Verifica si el conjunto seleccionado está vacío.
     * Utiliza isEmpty() del Set.
     */
    private void verificarVacio() {
        Set<Estudiante> grupo = obtenerGrupoSeleccionado();
        String nombreGrupo = (String) comboGrupo.getSelectedItem();
        
        /** isEmpty() retorna true si el conjunto no tiene elementos */
        boolean vacio = grupo.isEmpty();
        
        String mensaje = vacio ? 
            nombreGrupo + " está vacío" : 
            nombreGrupo + " tiene " + grupo.size() + " estudiante(s)";
        JOptionPane.showMessageDialog(this, mensaje);
    }
    
    /**
     * Operación 4: Busca un estudiante en el conjunto por matrícula.
     * Utiliza contains() del Set.
     */
    private void buscarEstudiante() {
        String matricula = JOptionPane.showInputDialog(this, 
            "Ingrese la matrícula del estudiante:");
        
        if (matricula != null && !matricula.trim().isEmpty()) {
            Set<Estudiante> grupo = obtenerGrupoSeleccionado();
            
            /** Crea un estudiante temporal solo con la matrícula para buscar */
            Estudiante estudianteBuscar = new Estudiante(matricula, "", "", "", "");
            
            /** contains() verifica si el elemento existe en el conjunto */
            boolean encontrado = grupo.contains(estudianteBuscar);
            
            if (encontrado) {
                
                /** Busca el estudiante completo para mostrar sus datos */
                for (Estudiante est : grupo) {
                    
                    if (est.equals(estudianteBuscar)) {
                        
                        JOptionPane.showMessageDialog(this, 
                            "Estudiante encontrado:\n" + est.toString());
                        break;
                    }
                }
            } else {
                
                JOptionPane.showMessageDialog(this, 
                    "Estudiante no encontrado en el grupo");
            }
        }
    }
    
    /**
     * Operación 5: Elimina un estudiante del conjunto seleccionado.
     * Utiliza remove() del Set.
     */
    private void eliminarEstudiante() {
        String matricula = JOptionPane.showInputDialog(this, 
            "Ingrese la matrícula del estudiante a eliminar:");
        
        if (matricula != null && !matricula.trim().isEmpty()) {
            
            Set<Estudiante> grupo = obtenerGrupoSeleccionado();
            Estudiante estudianteEliminar = new Estudiante(matricula, "", "", "", "");
            
            /** remove() elimina el elemento del conjunto si existe */
            boolean eliminado = grupo.remove(estudianteEliminar);
            
            if (eliminado) {
                
                JOptionPane.showMessageDialog(this, "Estudiante eliminado exitosamente");
                actualizarVisualizacion();
                
            } else {
                
                JOptionPane.showMessageDialog(this, "Estudiante no encontrado");
            }
        }
    }
    
    /**
     * Operación 6: Vacía completamente el conjunto seleccionado.
     * Utiliza clear() del Set.
     */
    private void vaciarGrupo() {
        String nombreGrupo = (String) comboGrupo.getSelectedItem();
        int confirm = JOptionPane.showConfirmDialog(this, 
            "¿Está seguro de vaciar todo el " + nombreGrupo + "?", 
            "Confirmar", 
            JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            Set<Estudiante> grupo = obtenerGrupoSeleccionado();
            
            /** clear() elimina todos los elementos del conjunto */
            grupo.clear();
            
            JOptionPane.showMessageDialog(this, nombreGrupo + " vaciado");
            actualizarVisualizacion();
        }
    }
    
    /**
     * Operación 7: Realiza la unión de los dos grupos.
     * Utiliza addAll() del Set para unir conjuntos.
     * La unión contiene todos los elementos de ambos conjuntos sin duplicados.
     */
    private void unirGrupos() {
        /** Crea un nuevo conjunto para no modificar los originales */
        Set<Estudiante> union = new HashSet<>(grupoA);
        
        /** addAll() agrega todos los elementos de otro conjunto */
        union.addAll(grupoB);
        
        /** Calcula estadísticas */
        int totalA = grupoA.size();
        int totalB = grupoB.size();
        int totalUnion = union.size();
        
        StringBuilder mensaje = new StringBuilder();
        mensaje.append("UNIÓN DE GRUPOS (A ∪ B)\n\n");
        mensaje.append("Grupo A: ").append(totalA).append(" estudiantes\n");
        mensaje.append("Grupo B: ").append(totalB).append(" estudiantes\n");
        mensaje.append("Unión: ").append(totalUnion).append(" estudiantes únicos\n\n");
        
        /** Muestra los estudiantes de la unión */
        int contador = 1;
        for (Estudiante est : union) {
            mensaje.append(contador++).append(". ").append(est.getMatricula())
                   .append(" - ").append(est.getNombre()).append("\n");
        }
        
        JOptionPane.showMessageDialog(this, mensaje.toString());
    }
    
    /**
     * Operación 8: Realiza la intersección de los dos grupos.
     * Utiliza retainAll() del Set para encontrar elementos comunes.
     * La intersección contiene solo los elementos que están en ambos conjuntos.
     */
    private void interseccionGrupos() {
        /** Crea un nuevo conjunto para no modificar los originales */
        Set<Estudiante> interseccion = new HashSet<>(grupoA);
        
        /** retainAll() mantiene solo los elementos que están en ambos conjuntos */
        interseccion.retainAll(grupoB);
        
        StringBuilder mensaje = new StringBuilder();
        mensaje.append("INTERSECCIÓN DE GRUPOS (A ∩ B)\n\n");
        mensaje.append("Estudiantes que están en AMBOS grupos: ")
               .append(interseccion.size()).append("\n\n");
        
        if (interseccion.isEmpty()) {
            mensaje.append("No hay estudiantes en común entre los grupos.");
        } else {
            int contador = 1;
            for (Estudiante est : interseccion) {
                mensaje.append(contador++).append(". ").append(est.getMatricula())
                       .append(" - ").append(est.getNombre()).append("\n");
            }
        }
        
        JOptionPane.showMessageDialog(this, mensaje.toString());
    }
    
    /**
     * Operación 9: Realiza la diferencia de conjuntos (A - B).
     * Utiliza removeAll() del Set para encontrar elementos que están solo en A.
     * La diferencia contiene elementos que están en A pero no en B.
     */
    private void diferenciaGrupos() {
        /** Crea un nuevo conjunto para no modificar los originales */
        Set<Estudiante> diferencia = new HashSet<>(grupoA);
        
        /** removeAll() elimina todos los elementos que están en el otro conjunto */
        diferencia.removeAll(grupoB);
        
        StringBuilder mensaje = new StringBuilder();
        mensaje.append("DIFERENCIA DE GRUPOS (A - B)\n\n");
        mensaje.append("Estudiantes que están SOLO en Grupo A: ")
               .append(diferencia.size()).append("\n\n");
        
        if (diferencia.isEmpty()) {
            mensaje.append("Todos los estudiantes del Grupo A también están en Grupo B.");
        } else {
            int contador = 1;
            for (Estudiante est : diferencia) {
                mensaje.append(contador++).append(". ").append(est.getMatricula())
                       .append(" - ").append(est.getNombre()).append("\n");
            }
        }
        
        JOptionPane.showMessageDialog(this, mensaje.toString());
    }
    
    /**
     * Actualiza el área de texto con todos los estudiantes de ambos grupos.
     */
    private void actualizarVisualizacion() {
        areaEstudiantes.setText("");
        StringBuilder sb = new StringBuilder();
        
        /** Muestra Grupo A */
        sb.append("========== GRUPO A ==========\n");
        sb.append("Total de estudiantes: ").append(grupoA.size()).append("\n\n");
        
        if (grupoA.isEmpty()) {
            sb.append("El grupo está vacío.\n");
        } else {
            int contador = 1;
            for (Estudiante est : grupoA) {
                sb.append(contador++).append(". ").append(est.toString()).append("\n\n");
            }
        }
        
        sb.append("\n");
        
        /** Muestra Grupo B */
        sb.append("========== GRUPO B ==========\n");
        sb.append("Total de estudiantes: ").append(grupoB.size()).append("\n\n");
        
        if (grupoB.isEmpty()) {
            sb.append("El grupo está vacío.\n");
        } else {
            int contador = 1;
            for (Estudiante est : grupoB) {
                sb.append(contador++).append(". ").append(est.toString()).append("\n\n");
            }
        }
        
        areaEstudiantes.setText(sb.toString());
    }
    
    /**
     * Obtiene el conjunto del grupo seleccionado en el ComboBox.
     */
    private Set<Estudiante> obtenerGrupoSeleccionado() {
        return comboGrupo.getSelectedIndex() == 0 ? grupoA : grupoB;
    }
    
    /**
     * Crea un objeto Estudiante a partir de los datos del formulario.
     */
    private Estudiante crearEstudianteDesdeFormulario() {
        return new Estudiante(
            txtMatricula.getText().trim(),
            txtNombre.getText().trim(),
            txtEdad.getText().trim(),
            txtCarrera.getText().trim(),
            txtPromedio.getText().trim()
        );
    }
    
    /**
     * Valida que todos los campos estén llenos.
     */
    private boolean validarCampos() {
        if (txtMatricula.getText().trim().isEmpty() ||
            txtNombre.getText().trim().isEmpty() ||
            txtEdad.getText().trim().isEmpty() ||
            txtCarrera.getText().trim().isEmpty() ||
            txtPromedio.getText().trim().isEmpty()) {
            
            JOptionPane.showMessageDialog(this, 
                "Por favor llene todos los campos");
            return false;
        }
        return true;
    }
    
    /**
     * Limpia todos los campos de entrada.
     */
    private void limpiarCampos() {
        txtMatricula.setText("");
        txtNombre.setText("");
        txtEdad.setText("");
        txtCarrera.setText("");
        txtPromedio.setText("");
    }
    
    /**
     * Agrega algunos estudiantes de ejemplo a ambos grupos.
     */
    private void agregarEstudiantesEjemplo() {
        /** Estudiantes solo en Grupo A */
        grupoA.add(new Estudiante("A001", "Juan Pérez", "20", 
            "Ingeniería en Sistemas", "8.5"));
        grupoA.add(new Estudiante("A002", "María García", "21", 
            "Ingeniería Industrial", "9.0"));
        grupoA.add(new Estudiante("A003", "Carlos López", "19", 
            "Ingeniería Civil", "8.0"));
        
        /** Estudiantes solo en Grupo B */
        grupoB.add(new Estudiante("B001", "Ana Martínez", "22", 
            "Arquitectura", "8.8"));
        grupoB.add(new Estudiante("B002", "Luis Rodríguez", "20", 
            "Ingeniería Mecánica", "7.9"));
        
        /** Estudiante común en ambos grupos (para demostrar intersección) */
        Estudiante comun = new Estudiante("C001", "Pedro Sánchez", "21", 
            "Ingeniería Eléctrica", "9.2");
        grupoA.add(comun);
        grupoB.add(comun);
    }
    
    /**
     * Método principal que inicia la aplicación.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new GestionEstudiantesConjuntos().setVisible(true);
        });
    }
}

/**
 * Clase que representa un estudiante con sus 5 datos principales.
 * Implementa equals() y hashCode() para funcionar correctamente en un Set.
 */
class Estudiante {
    /** Dato 1: Matrícula del estudiante (identificador único) */
    private String matricula;
    
    /** Dato 2: Nombre completo del estudiante */
    private String nombre;
    
    /** Dato 3: Edad del estudiante */
    private String edad;
    
    /** Dato 4: Carrera que estudia */
    private String carrera;
    
    /** Dato 5: Promedio general */
    private String promedio;
    
    /**
     * Constructor que inicializa un estudiante con todos sus datos.
     */
    public Estudiante(String matricula, String nombre, String edad, 
                      String carrera, String promedio) {
        this.matricula = matricula;
        this.nombre = nombre;
        this.edad = edad;
        this.carrera = carrera;
        this.promedio = promedio;
    }
    
    /**
     * Obtiene la matrícula del estudiante.
     */
    public String getMatricula() {
        return matricula;
    }
    
    /**
     * Obtiene el nombre del estudiante.
     */
    public String getNombre() {
        return nombre;
    }
    
    /**
     * Método equals() sobrescrito para comparar estudiantes por matrícula.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Estudiante that = (Estudiante) obj;
        return matricula.equals(that.matricula);
    }
    
    /**
     * Método hashCode() sobrescrito para generar código hash basado en matrícula.
     */
    @Override
    public int hashCode() {
        return matricula.hashCode();
    }
    
    /**
     * Representación en texto del estudiante con todos sus datos.
     */
    @Override
    public String toString() {
        return "🎓 " + nombre + "\n" +
               "   Matrícula: " + matricula + "\n" +
               "   Edad: " + edad + " años\n" +
               "   Carrera: " + carrera + "\n" +
               "   Promedio: " + promedio;
    }
}
