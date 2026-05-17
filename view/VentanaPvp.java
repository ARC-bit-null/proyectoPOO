package view;

import model.Pokemon;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import model.InventarioCombate;

public class VentanaPvp extends JFrame {

    // Guardamos la ventana anterior
    private VentanaPvpEquipos ventanaPvpEquipos;

    // Guardamos los equipos de ambos jugadores
    private ArrayList<Pokemon> equipoJugador1;
    private ArrayList<Pokemon> equipoJugador2;

    // Guardamos el índice del pokemon activo de cada jugador
    private int indiceActivoJ1;
    private int indiceActivoJ2;

    // Guardamos las acciones elegidas por cada jugador
    private String accionJugador1;
    private String accionJugador2;

    // Guardamos el objeto seleccionado por cada jugador cuando use mochila
    private String objetoJugador1;
    private String objetoJugador2;

    // Guardamos el índice del pokemon seleccionado para cambio o revivir
    private int indiceSeleccionadoJ1;
    private int indiceSeleccionadoJ2;

    // Guardamos si el cambio del jugador es obligatorio porque su pokemon fue derrotado
    private boolean cambioObligatorioJ1;
    private boolean cambioObligatorioJ2;

    // Guardamos si el combate ya terminó
    private boolean combateTerminado;

    // Inventarios temporales de combate de ambos jugadores
    private InventarioCombate inventarioJugador1;
    private InventarioCombate inventarioJugador2;

    // Declaramos labels para mostrar la información del pokemon activo del Jugador 1
    private JLabel lblNombreJ1;
    private JLabel lblNivelJ1;
    private JLabel lblHpJ1;
    private JProgressBar barraHpJ1;

    // Declaramos labels para mostrar la información del pokemon activo del Jugador 2
    private JLabel lblNombreJ2;
    private JLabel lblNivelJ2;
    private JLabel lblHpJ2;
    private JProgressBar barraHpJ2;

    // Declaramos labels para mostrar los objetos restantes del Jugador 1
    private JLabel lblObjetosJ1;

    // Declaramos labels para mostrar los objetos restantes del Jugador 2
    private JLabel lblObjetosJ2;

    // Area de texto para mostrar los mensajes del combate
    private JTextArea txtMensajes;

    // Botones de acciones del Jugador 1
    private JButton btnAtacarJ1;
    private JButton btnMochilaJ1;
    private JButton btnCambioJ1;

    // Botones de acciones del Jugador 2
    private JButton btnAtacarJ2;
    private JButton btnMochilaJ2;
    private JButton btnCambioJ2;

    public VentanaPvp(VentanaPvpEquipos ventanaPvpEquipos, ArrayList<Pokemon> equipoJugador1, ArrayList<Pokemon> equipoJugador2) {
        // Guardamos la ventana anterior
        this.ventanaPvpEquipos = ventanaPvpEquipos;

        // Guardamos los equipos recibidos
        this.equipoJugador1 = equipoJugador1;
        this.equipoJugador2 = equipoJugador2;

        // Creamos los inventarios temporales de combate de ambos jugadores
        inventarioJugador1 = new InventarioCombate();
        inventarioJugador2 = new InventarioCombate();

        // El primer pokemon del equipo será el pokemon activo inicial
        indiceActivoJ1 = 0;
        indiceActivoJ2 = 0;

        // Inicializamos variables de control
        accionJugador1 = null;
        accionJugador2 = null;
        objetoJugador1 = null;
        objetoJugador2 = null;
        indiceSeleccionadoJ1 = -1;
        indiceSeleccionadoJ2 = -1;
        cambioObligatorioJ1 = false;
        cambioObligatorioJ2 = false;
        combateTerminado = false;

        setTitle("Combate PvP");
        setSize(1600, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                ventanaPvpEquipos.setVisible(true);
            }
        });

        inicializarComponentes();
        inicializarEventos();
        actualizarVistaCombate();

        agregarMensaje("¡Comienza el combate PvP!");
        agregarMensaje("Jugador 1 inicia con " + obtenerPokemonActivoJ1().getNombre() + ".");
        agregarMensaje("Jugador 2 inicia con " + obtenerPokemonActivoJ2().getNombre() + ".");

        setVisible(true);
    }

    // Metodo donde inicializamos todos los elementos visuales de la ventana
    private void inicializarComponentes() {
        // Panel principal de la ventana
        JPanel panelPrincipal = new JPanel(new BorderLayout(20, 20));
        panelPrincipal.setBackground(new Color(245, 245, 245));
        panelPrincipal.setBorder(new EmptyBorder(20, 20, 20, 20));

        // Título principal
        JLabel lblTitulo = new JLabel("Combate PvP", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 34));
        lblTitulo.setForeground(new Color(50, 50, 50));

        // Panel central general donde irán las acciones de ambos jugadores y el combate al centro
        JPanel panelContenido = new JPanel(new BorderLayout(20, 20));
        panelContenido.setBackground(new Color(245, 245, 245));

        // Panel de acciones del Jugador 1 a la izquierda
        JPanel panelAccionesJ1 = crearPanelAccionesJugador(1);

        // Panel de acciones del Jugador 2 a la derecha
        JPanel panelAccionesJ2 = crearPanelAccionesJugador(2);

        // Panel central donde se muestra la información principal del combate
        JPanel panelCentro = new JPanel(new GridLayout(3, 1, 20, 20));
        panelCentro.setBackground(new Color(245, 245, 245));

        // Panel del pokemon activo del Jugador 2
        JPanel panelPokemonJ2 = crearPanelPokemonActivo(2);

        // Panel donde mostraremos los mensajes del combate
        JPanel panelMensajes = new JPanel(new BorderLayout());
        panelMensajes.setBackground(Color.WHITE);
        panelMensajes.setBorder(new LineBorder(new Color(200, 200, 200), 2, true));

        txtMensajes = new JTextArea();
        txtMensajes.setEditable(false);
        txtMensajes.setFont(new Font("Monospaced", Font.PLAIN, 18));
        txtMensajes.setLineWrap(true);
        txtMensajes.setWrapStyleWord(true);
        txtMensajes.setBackground(Color.WHITE);
        txtMensajes.setMargin(new Insets(12, 12, 12, 12));

        JScrollPane scrollMensajes = new JScrollPane(txtMensajes);
        scrollMensajes.setBorder(null);

        panelMensajes.add(scrollMensajes, BorderLayout.CENTER);

        // Panel del pokemon activo del Jugador 1
        JPanel panelPokemonJ1 = crearPanelPokemonActivo(1);

        panelCentro.add(panelPokemonJ2);
        panelCentro.add(panelMensajes);
        panelCentro.add(panelPokemonJ1);

        // Agregamos la distribución nueva:
        // Jugador 1 a la izquierda, combate al centro y Jugador 2 a la derecha
        panelContenido.add(panelAccionesJ1, BorderLayout.WEST);
        panelContenido.add(panelCentro, BorderLayout.CENTER);
        panelContenido.add(panelAccionesJ2, BorderLayout.EAST);

        // Agregamos los elementos al panel principal
        panelPrincipal.add(lblTitulo, BorderLayout.NORTH);
        panelPrincipal.add(panelContenido, BorderLayout.CENTER);

        add(panelPrincipal);
    }

    // Metodo para crear el panel de acciones de cada jugador
    private JPanel crearPanelAccionesJugador(int jugador) {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(Color.WHITE);
        panel.setBorder(new LineBorder(new Color(200, 200, 200), 2, true));
        panel.setPreferredSize(new Dimension(250, 200));

        // Título del panel de acciones
        JLabel lblTitulo = new JLabel("Acciones Jugador " + jugador, SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 22));
        lblTitulo.setBorder(new EmptyBorder(10, 10, 10, 10));

        // Label donde mostraremos los objetos disponibles
        JLabel lblObjetos = new JLabel();
        lblObjetos.setFont(new Font("Arial", Font.PLAIN, 15));
        lblObjetos.setBorder(new EmptyBorder(10, 10, 10, 10));

        // Panel donde pondremos los botones
        JPanel panelBotones = new JPanel(new GridLayout(3, 1, 10, 10));
        panelBotones.setBackground(Color.WHITE);
        panelBotones.setBorder(new EmptyBorder(10, 10, 10, 10));

        JButton btnAtacar = new JButton("Atacar");
        JButton btnMochila = new JButton("Mochila");
        JButton btnCambio = new JButton("Cambio");

        btnAtacar.setFont(new Font("Arial", Font.BOLD, 18));
        btnMochila.setFont(new Font("Arial", Font.BOLD, 18));
        btnCambio.setFont(new Font("Arial", Font.BOLD, 18));

        panelBotones.add(btnAtacar);
        panelBotones.add(btnMochila);
        panelBotones.add(btnCambio);

        panel.add(lblTitulo, BorderLayout.NORTH);
        panel.add(panelBotones, BorderLayout.CENTER);
        panel.add(lblObjetos, BorderLayout.SOUTH);

        // Guardamos referencias según el jugador
        if (jugador == 1) {
            btnAtacarJ1 = btnAtacar;
            btnMochilaJ1 = btnMochila;
            btnCambioJ1 = btnCambio;
            lblObjetosJ1 = lblObjetos;
        } else {
            btnAtacarJ2 = btnAtacar;
            btnMochilaJ2 = btnMochila;
            btnCambioJ2 = btnCambio;
            lblObjetosJ2 = lblObjetos;
        }

        return panel;
    }

    // Metodo para crear el panel donde se muestra el pokemon activo de cada jugador
    private JPanel crearPanelPokemonActivo(int jugador) {
        JPanel panel = new JPanel(new BorderLayout(15, 15));
        panel.setBackground(Color.WHITE);
        panel.setBorder(new LineBorder(new Color(200, 200, 200), 2, true));

        // Panel donde se muestra la información del pokemon
        JPanel panelInfo = new JPanel();
        panelInfo.setLayout(new BoxLayout(panelInfo, BoxLayout.Y_AXIS));
        panelInfo.setBackground(Color.WHITE);
        panelInfo.setBorder(new EmptyBorder(20, 20, 20, 20));

        JLabel lblNombre = new JLabel("Nombre");
        lblNombre.setFont(new Font("Arial", Font.BOLD, 26));

        JLabel lblNivel = new JLabel("Nivel");
        lblNivel.setFont(new Font("Arial", Font.PLAIN, 18));

        JProgressBar barraHp = new JProgressBar();
        barraHp.setStringPainted(true);
        barraHp.setFont(new Font("Arial", Font.BOLD, 14));
        barraHp.setPreferredSize(new Dimension(300, 30));

        JLabel lblHp = new JLabel("HP");
        lblHp.setFont(new Font("Arial", Font.PLAIN, 18));

        panelInfo.add(lblNombre);
        panelInfo.add(Box.createVerticalStrut(10));
        panelInfo.add(lblNivel);
        panelInfo.add(Box.createVerticalStrut(15));
        panelInfo.add(barraHp);
        panelInfo.add(Box.createVerticalStrut(10));
        panelInfo.add(lblHp);

        // Label decorativo donde luego podrías poner sprites o imágenes
        JLabel lblSprite = new JLabel("Pokémon Jugador " + jugador, SwingConstants.CENTER);
        lblSprite.setFont(new Font("Arial", Font.BOLD, 24));
        lblSprite.setForeground(new Color(130, 130, 130));
        lblSprite.setBorder(new EmptyBorder(20, 20, 20, 20));

        // Si es Jugador 2 lo ponemos con información primero
        // Si es Jugador 1 lo ponemos con sprite primero para parecer un combate pokemon
        if (jugador == 2) {
            panel.add(panelInfo, BorderLayout.WEST);
            panel.add(lblSprite, BorderLayout.CENTER);

            lblNombreJ2 = lblNombre;
            lblNivelJ2 = lblNivel;
            lblHpJ2 = lblHp;
            barraHpJ2 = barraHp;
        } else {
            panel.add(lblSprite, BorderLayout.WEST);
            panel.add(panelInfo, BorderLayout.CENTER);

            lblNombreJ1 = lblNombre;
            lblNivelJ1 = lblNivel;
            lblHpJ1 = lblHp;
            barraHpJ1 = barraHp;
        }

        return panel;
    }

    // Metodo donde inicializamos todos los eventos de los botones
    private void inicializarEventos() {
        // Eventos del Jugador 1
        btnAtacarJ1.addActionListener(e -> seleccionarAtaqueJugador1());
        btnMochilaJ1.addActionListener(e -> seleccionarMochilaJugador1());
        btnCambioJ1.addActionListener(e -> seleccionarCambioJugador1());

        // Eventos del Jugador 2
        btnAtacarJ2.addActionListener(e -> seleccionarAtaqueJugador2());
        btnMochilaJ2.addActionListener(e -> seleccionarMochilaJugador2());
        btnCambioJ2.addActionListener(e -> seleccionarCambioJugador2());
    }

    // Metodo para obtener el pokemon activo del Jugador 1
    private Pokemon obtenerPokemonActivoJ1() {
        return equipoJugador1.get(indiceActivoJ1);
    }

    // Metodo para obtener el pokemon activo del Jugador 2
    private Pokemon obtenerPokemonActivoJ2() {
        return equipoJugador2.get(indiceActivoJ2);
    }

    // Metodo para actualizar toda la información visual del combate
    private void actualizarVistaCombate() {
        actualizarPanelJugador1();
        actualizarPanelJugador2();
        actualizarContadoresObjetos();
        actualizarEstadoBotones();
    }

    // Metodo para actualizar la información visual del pokemon activo del Jugador 1
    private void actualizarPanelJugador1() {
        Pokemon pokemon = obtenerPokemonActivoJ1();

        lblNombreJ1.setText(pokemon.getNombre());
        lblNivelJ1.setText("Nivel: " + pokemon.getNivel());
        lblHpJ1.setText("HP: " + pokemon.getHp() + "/" + pokemon.getHpMax());

        barraHpJ1.setMaximum(pokemon.getHpMax());
        barraHpJ1.setValue(pokemon.getHp());
        barraHpJ1.setString(pokemon.getHp() + "/" + pokemon.getHpMax());
    }

    // Metodo para actualizar la información visual del pokemon activo del Jugador 2
    private void actualizarPanelJugador2() {
        Pokemon pokemon = obtenerPokemonActivoJ2();

        lblNombreJ2.setText(pokemon.getNombre());
        lblNivelJ2.setText("Nivel: " + pokemon.getNivel());
        lblHpJ2.setText("HP: " + pokemon.getHp() + "/" + pokemon.getHpMax());

        barraHpJ2.setMaximum(pokemon.getHpMax());
        barraHpJ2.setValue(pokemon.getHp());
        barraHpJ2.setString(pokemon.getHp() + "/" + pokemon.getHpMax());
    }

    // Metodo para actualizar los contadores de objetos de ambos jugadores
    private void actualizarContadoresObjetos() {
        lblObjetosJ1.setText(
                "<html>Spray: " + inventarioJugador1.getSprayCurativo() +
                        " | Revivir: " + inventarioJugador1.getRevivir() +
                        "<br>Banda: " + inventarioJugador1.getBandaEspecial() +
                        " | X Speed: " + inventarioJugador1.getXSpeed() + "</html>"
        );

        lblObjetosJ2.setText(
                "<html>Spray: " + inventarioJugador2.getSprayCurativo() +
                        " | Revivir: " + inventarioJugador2.getRevivir() +
                        "<br>Banda: " + inventarioJugador2.getBandaEspecial() +
                        " | X Speed: " + inventarioJugador2.getXSpeed() + "</html>"
        );
    }

    // Metodo para actualizar si los botones deben o no seguir activos
    private void actualizarEstadoBotones() {
        boolean habilitadoJ1 = !combateTerminado && !cambioObligatorioJ1;
        boolean habilitadoJ2 = !combateTerminado && !cambioObligatorioJ2;

        btnAtacarJ1.setEnabled(habilitadoJ1);
        btnMochilaJ1.setEnabled(habilitadoJ1);
        btnCambioJ1.setEnabled(habilitadoJ1);

        btnAtacarJ2.setEnabled(habilitadoJ2);
        btnMochilaJ2.setEnabled(habilitadoJ2);
        btnCambioJ2.setEnabled(habilitadoJ2);
    }

    // Metodo para agregar un mensaje al cuadro de texto del combate
    private void agregarMensaje(String mensaje) {
        txtMensajes.append(mensaje + "\n");
        txtMensajes.setCaretPosition(txtMensajes.getDocument().getLength());
    }

    // Metodo para guardar que el Jugador 1 eligió atacar
    private void seleccionarAtaqueJugador1() {
        if (combateTerminado) return;

        accionJugador1 = "ATACAR";
        objetoJugador1 = null;
        indiceSeleccionadoJ1 = -1;

        agregarMensaje("Jugador 1 eligió atacar.");
        intentarResolverRonda();
    }

    // Metodo para guardar que el Jugador 2 eligió atacar
    private void seleccionarAtaqueJugador2() {
        if (combateTerminado) return;

        accionJugador2 = "ATACAR";
        objetoJugador2 = null;
        indiceSeleccionadoJ2 = -1;

        agregarMensaje("Jugador 2 eligió atacar.");
        intentarResolverRonda();
    }

    // Metodo para mostrar las opciones de mochila del Jugador 1
    private void seleccionarMochilaJugador1() {
        if (combateTerminado) return;

        String[] opciones = {"Spray curativo", "Revivir", "Banda especial", "X speed"};
        String seleccion = (String) JOptionPane.showInputDialog(
                this,
                "Selecciona un objeto para Jugador 1:",
                "Mochila Jugador 1",
                JOptionPane.PLAIN_MESSAGE,
                null,
                opciones,
                opciones[0]
        );

        if (seleccion == null) return;

        if (seleccion.equals("Spray curativo")) {
            if (!inventarioJugador1.puedeUsarSprayCurativo()) {
                JOptionPane.showMessageDialog(this, "Jugador 1 ya no tiene Spray curativo.");
                return;
            }

            if (obtenerPokemonActivoJ1().estaDerrotado()) {
                JOptionPane.showMessageDialog(this, "No puedes usar Spray curativo sobre un Pokémon derrotado.");
                return;
            }

            accionJugador1 = "OBJETO";
            objetoJugador1 = "SPRAY";
            indiceSeleccionadoJ1 = indiceActivoJ1;
        }

        if (seleccion.equals("Revivir")) {
            if (!inventarioJugador1.puedeUsarRevivir()) {
                JOptionPane.showMessageDialog(this, "Jugador 1 ya no tiene Revivir.");
                return;
            }

            int indice = seleccionarPokemonDerrotado(equipoJugador1, "Jugador 1 - Selecciona un Pokémon para revivir");
            if (indice == -1) return;

            accionJugador1 = "OBJETO";
            objetoJugador1 = "REVIVIR";
            indiceSeleccionadoJ1 = indice;
        }

        if (seleccion.equals("Banda especial")) {
            if (!inventarioJugador1.puedeUsarBandaEspecial()) {
                JOptionPane.showMessageDialog(this, "Jugador 1 ya no tiene Banda especial.");
                return;
            }

            accionJugador1 = "OBJETO";
            objetoJugador1 = "BANDA";
            indiceSeleccionadoJ1 = indiceActivoJ1;
        }

        if (seleccion.equals("X speed")) {
            if (!inventarioJugador1.puedeUsarXSpeed()) {
                JOptionPane.showMessageDialog(this, "Jugador 1 ya no tiene X speed.");
                return;
            }

            accionJugador1 = "OBJETO";
            objetoJugador1 = "XSPEED";
            indiceSeleccionadoJ1 = indiceActivoJ1;
        }

        agregarMensaje("Jugador 1 eligió usar un objeto.");
        intentarResolverRonda();
    }

    // Metodo para mostrar las opciones de mochila del Jugador 2
    private void seleccionarMochilaJugador2() {
        if (combateTerminado) return;

        String[] opciones = {"Spray curativo", "Revivir", "Banda especial", "X speed"};
        String seleccion = (String) JOptionPane.showInputDialog(
                this,
                "Selecciona un objeto para Jugador 2:",
                "Mochila Jugador 2",
                JOptionPane.PLAIN_MESSAGE,
                null,
                opciones,
                opciones[0]
        );

        if (seleccion == null) return;

        if (seleccion.equals("Spray curativo")) {
            if (!inventarioJugador2.puedeUsarSprayCurativo()) {
                JOptionPane.showMessageDialog(this, "Jugador 2 ya no tiene Spray curativo.");
                return;
            }

            if (obtenerPokemonActivoJ2().estaDerrotado()) {
                JOptionPane.showMessageDialog(this, "No puedes usar Spray curativo sobre un Pokémon derrotado.");
                return;
            }

            accionJugador2 = "OBJETO";
            objetoJugador2 = "SPRAY";
            indiceSeleccionadoJ2 = indiceActivoJ2;
        }

        if (seleccion.equals("Revivir")) {
            if (!inventarioJugador2.puedeUsarRevivir()) {
                JOptionPane.showMessageDialog(this, "Jugador 2 ya no tiene Revivir.");
                return;
            }

            int indice = seleccionarPokemonDerrotado(equipoJugador2, "Jugador 2 - Selecciona un Pokémon para revivir");
            if (indice == -1) return;

            accionJugador2 = "OBJETO";
            objetoJugador2 = "REVIVIR";
            indiceSeleccionadoJ2 = indice;
        }

        if (seleccion.equals("Banda especial")) {
            if (!inventarioJugador2.puedeUsarBandaEspecial()) {
                JOptionPane.showMessageDialog(this, "Jugador 2 ya no tiene Banda especial.");
                return;
            }

            accionJugador2 = "OBJETO";
            objetoJugador2 = "BANDA";
            indiceSeleccionadoJ2 = indiceActivoJ2;
        }

        if (seleccion.equals("X speed")) {
            if (!inventarioJugador2.puedeUsarXSpeed()) {
                JOptionPane.showMessageDialog(this, "Jugador 2 ya no tiene X speed.");
                return;
            }

            accionJugador2 = "OBJETO";
            objetoJugador2 = "XSPEED";
            indiceSeleccionadoJ2 = indiceActivoJ2;
        }

        agregarMensaje("Jugador 2 eligió usar un objeto.");
        intentarResolverRonda();
    }

    // Metodo para permitir que el Jugador 1 seleccione un cambio
    private void seleccionarCambioJugador1() {
        if (combateTerminado) return;

        int indice = seleccionarPokemonVivo(equipoJugador1, indiceActivoJ1, "Jugador 1 - Selecciona un Pokémon para cambiar");

        if (indice == -1) return;

        accionJugador1 = "CAMBIO";
        objetoJugador1 = null;
        indiceSeleccionadoJ1 = indice;

        agregarMensaje("Jugador 1 eligió cambiar de Pokémon.");
        intentarResolverRonda();
    }

    // Metodo para permitir que el Jugador 2 seleccione un cambio
    private void seleccionarCambioJugador2() {
        if (combateTerminado) return;

        int indice = seleccionarPokemonVivo(equipoJugador2, indiceActivoJ2, "Jugador 2 - Selecciona un Pokémon para cambiar");

        if (indice == -1) return;

        accionJugador2 = "CAMBIO";
        objetoJugador2 = null;
        indiceSeleccionadoJ2 = indice;

        agregarMensaje("Jugador 2 eligió cambiar de Pokémon.");
        intentarResolverRonda();
    }

    // Metodo para intentar resolver la ronda cuando ambos jugadores ya eligieron una acción
    private void intentarResolverRonda() {
        if (cambioObligatorioJ1 || cambioObligatorioJ2) {
            return;
        }

        if (accionJugador1 != null && accionJugador2 != null) {
            resolverRonda();
        }
    }

    // Metodo principal para resolver una ronda completa del combate
    private void resolverRonda() {
        if (combateTerminado) return;

        // Primero resolvemos las acciones prioritarias del Jugador 1
        if (esAccionPrioritaria(accionJugador1)) {
            resolverAccionPrioritariaJugador1();
            if (verificarFinCombate()) return;
        }

        // Luego resolvemos las acciones prioritarias del Jugador 2
        if (esAccionPrioritaria(accionJugador2)) {
            resolverAccionPrioritariaJugador2();
            if (verificarFinCombate()) return;
        }

        // Si los dos eligieron atacar, resolvemos por velocidad
        if ("ATACAR".equals(accionJugador1) && "ATACAR".equals(accionJugador2)) {
            resolverAtaquesPorVelocidad();
        }
        // Si solo el Jugador 1 atacó
        else if ("ATACAR".equals(accionJugador1)) {
            ejecutarAtaqueJugador1();
        }
        // Si solo el Jugador 2 atacó
        else if ("ATACAR".equals(accionJugador2)) {
            ejecutarAtaqueJugador2();
        }

        actualizarVistaCombate();

        // Revisamos si algún pokemon quedó derrotado y forzamos el cambio
        revisarPokemonDerrotados();

        if (verificarFinCombate()) return;

        // Limpiamos las acciones para la siguiente ronda
        limpiarAcciones();
    }

    // Metodo para saber si una acción tiene prioridad sobre los ataques
    private boolean esAccionPrioritaria(String accion) {
        return "OBJETO".equals(accion) || "CAMBIO".equals(accion);
    }

    // Metodo para resolver la acción prioritaria del Jugador 1
    private void resolverAccionPrioritariaJugador1() {
        if ("CAMBIO".equals(accionJugador1)) {
            Pokemon anterior = obtenerPokemonActivoJ1();
            indiceActivoJ1 = indiceSeleccionadoJ1;
            agregarMensaje("Jugador 1 cambió a " + obtenerPokemonActivoJ1().getNombre() + " por " + anterior.getNombre() + ".");
        }

        if ("OBJETO".equals(accionJugador1)) {
            usarObjetoJugador1();
        }

        actualizarVistaCombate();
    }

    // Metodo para resolver la acción prioritaria del Jugador 2
    private void resolverAccionPrioritariaJugador2() {
        if ("CAMBIO".equals(accionJugador2)) {
            Pokemon anterior = obtenerPokemonActivoJ2();
            indiceActivoJ2 = indiceSeleccionadoJ2;
            agregarMensaje("Jugador 2 cambió a " + obtenerPokemonActivoJ2().getNombre() + " por " + anterior.getNombre() + ".");
        }

        if ("OBJETO".equals(accionJugador2)) {
            usarObjetoJugador2();
        }

        actualizarVistaCombate();
    }

    // Metodo para resolver los ataques cuando ambos jugadores atacan
    private void resolverAtaquesPorVelocidad() {
        Pokemon pokemonJ1 = obtenerPokemonActivoJ1();
        Pokemon pokemonJ2 = obtenerPokemonActivoJ2();

        // Si el pokemon del Jugador 1 es más rápido o empatan, ataca primero
        if (pokemonJ1.getVelocidad() >= pokemonJ2.getVelocidad()) {
            ejecutarAtaqueJugador1();

            if (!obtenerPokemonActivoJ2().estaDerrotado()) {
                ejecutarAtaqueJugador2();
            }
        } else {
            ejecutarAtaqueJugador2();

            if (!obtenerPokemonActivoJ1().estaDerrotado()) {
                ejecutarAtaqueJugador1();
            }
        }
    }

    // Metodo para que el Jugador 1 ataque al pokemon activo del Jugador 2
    private void ejecutarAtaqueJugador1() {
        Pokemon atacante = obtenerPokemonActivoJ1();
        Pokemon defensor = obtenerPokemonActivoJ2();

        if (atacante.estaDerrotado()) {
            agregarMensaje("Jugador 1 no pudo atacar porque su Pokémon está derrotado.");
            return;
        }

        agregarMensaje(atacante.getNombre() + " atacó a " + defensor.getNombre() + " e hizo " + atacante.getDano() + " de daño.");
        defensor.recibirDano(atacante.getDano());
        actualizarVistaCombate();

        if (defensor.estaDerrotado()) {
            agregarMensaje(defensor.getNombre() + " fue derrotado.");
        }
    }

    // Metodo para que el Jugador 2 ataque al pokemon activo del Jugador 1
    private void ejecutarAtaqueJugador2() {
        Pokemon atacante = obtenerPokemonActivoJ2();
        Pokemon defensor = obtenerPokemonActivoJ1();

        if (atacante.estaDerrotado()) {
            agregarMensaje("Jugador 2 no pudo atacar porque su Pokémon está derrotado.");
            return;
        }

        agregarMensaje(atacante.getNombre() + " atacó a " + defensor.getNombre() + " e hizo " + atacante.getDano() + " de daño.");
        defensor.recibirDano(atacante.getDano());
        actualizarVistaCombate();

        if (defensor.estaDerrotado()) {
            agregarMensaje(defensor.getNombre() + " fue derrotado.");
        }
    }

    // Metodo para usar el objeto seleccionado por el Jugador 1
    private void usarObjetoJugador1() {
        Pokemon pokemonObjetivo = equipoJugador1.get(indiceSeleccionadoJ1);

        if ("SPRAY".equals(objetoJugador1)) {
            if (inventarioJugador1.usarSprayCurativo()) {
                pokemonObjetivo.curarHp(20);
                agregarMensaje("Jugador 1 usó Spray curativo en " + pokemonObjetivo.getNombre() + ".");
            }
        }

        if ("REVIVIR".equals(objetoJugador1)) {
            if (inventarioJugador1.usarRevivir()) {
                pokemonObjetivo.revivir();
                agregarMensaje("Jugador 1 usó Revivir en " + pokemonObjetivo.getNombre() + ".");
            }
        }

        if ("BANDA".equals(objetoJugador1)) {
            if (inventarioJugador1.usarBandaEspecial()) {
                pokemonObjetivo.aumentarDano(10);
                agregarMensaje("Jugador 1 usó Banda especial en " + pokemonObjetivo.getNombre() + ". Su daño aumentó.");
            }
        }

        if ("XSPEED".equals(objetoJugador1)) {
            if (inventarioJugador1.usarXSpeed()) {
                pokemonObjetivo.aumentarVelocidad(10);
                agregarMensaje("Jugador 1 usó X speed en " + pokemonObjetivo.getNombre() + ". Su velocidad aumentó.");
            }
        }
    }

    // Metodo para usar el objeto seleccionado por el Jugador 2
    private void usarObjetoJugador2() {
        Pokemon pokemonObjetivo = equipoJugador2.get(indiceSeleccionadoJ2);

        if ("SPRAY".equals(objetoJugador2)) {
            if (inventarioJugador2.usarSprayCurativo()) {
                pokemonObjetivo.curarHp(20);
                agregarMensaje("Jugador 2 usó Spray curativo en " + pokemonObjetivo.getNombre() + ".");
            }
        }

        if ("REVIVIR".equals(objetoJugador2)) {
            if (inventarioJugador2.usarRevivir()) {
                pokemonObjetivo.revivir();
                agregarMensaje("Jugador 2 usó Revivir en " + pokemonObjetivo.getNombre() + ".");
            }
        }

        if ("BANDA".equals(objetoJugador2)) {
            if (inventarioJugador2.usarBandaEspecial()) {
                pokemonObjetivo.aumentarDano(10);
                agregarMensaje("Jugador 2 usó Banda especial en " + pokemonObjetivo.getNombre() + ". Su daño aumentó.");
            }
        }

        if ("XSPEED".equals(objetoJugador2)) {
            if (inventarioJugador2.usarXSpeed()) {
                pokemonObjetivo.aumentarVelocidad(10);
                agregarMensaje("Jugador 2 usó X speed en " + pokemonObjetivo.getNombre() + ". Su velocidad aumentó.");
            }
        }
    }

    // Metodo para revisar si alguno de los pokemones activos fue derrotado
    private void revisarPokemonDerrotados() {
        if (obtenerPokemonActivoJ1().estaDerrotado()) {
            cambioObligatorioJ1 = true;
            actualizarEstadoBotones();
            forzarCambioJugador1();
        }

        if (obtenerPokemonActivoJ2().estaDerrotado()) {
            cambioObligatorioJ2 = true;
            actualizarEstadoBotones();
            forzarCambioJugador2();
        }

        actualizarVistaCombate();
    }

    // Metodo para obligar al Jugador 1 a seleccionar otro pokemon si el actual fue derrotado
    private void forzarCambioJugador1() {
        if (todosLosPokemonesDerrotados(equipoJugador1)) {
            return;
        }

        agregarMensaje("Jugador 1 debe elegir un nuevo Pokémon.");

        int indice = seleccionarPokemonVivo(equipoJugador1, indiceActivoJ1, "Jugador 1 - Tu Pokémon fue derrotado, selecciona el siguiente");

        if (indice != -1) {
            indiceActivoJ1 = indice;
            cambioObligatorioJ1 = false;
            agregarMensaje("Jugador 1 envió a " + obtenerPokemonActivoJ1().getNombre() + ".");
        }
    }

    // Metodo para obligar al Jugador 2 a seleccionar otro pokemon si el actual fue derrotado
    private void forzarCambioJugador2() {
        if (todosLosPokemonesDerrotados(equipoJugador2)) {
            return;
        }

        agregarMensaje("Jugador 2 debe elegir un nuevo Pokémon.");

        int indice = seleccionarPokemonVivo(equipoJugador2, indiceActivoJ2, "Jugador 2 - Tu Pokémon fue derrotado, selecciona el siguiente");

        if (indice != -1) {
            indiceActivoJ2 = indice;
            cambioObligatorioJ2 = false;
            agregarMensaje("Jugador 2 envió a " + obtenerPokemonActivoJ2().getNombre() + ".");
        }
    }

    // Metodo para verificar si alguno de los dos jugadores ya perdió el combate
    private boolean verificarFinCombate() {
        if (todosLosPokemonesDerrotados(equipoJugador1)) {
            combateTerminado = true;
            actualizarEstadoBotones();
            agregarMensaje("Todos los Pokémon del Jugador 1 fueron derrotados.");
            agregarMensaje("¡Jugador 2 gana el combate!");
            return true;
        }

        if (todosLosPokemonesDerrotados(equipoJugador2)) {
            combateTerminado = true;
            actualizarEstadoBotones();
            agregarMensaje("Todos los Pokémon del Jugador 2 fueron derrotados.");
            agregarMensaje("¡Jugador 1 gana el combate!");
            return true;
        }

        return false;
    }

    // Metodo para limpiar las acciones elegidas y preparar la siguiente ronda
    private void limpiarAcciones() {
        accionJugador1 = null;
        accionJugador2 = null;
        objetoJugador1 = null;
        objetoJugador2 = null;
        indiceSeleccionadoJ1 = -1;
        indiceSeleccionadoJ2 = -1;
    }

    // Metodo para saber si todos los pokemones de un equipo están derrotados
    private boolean todosLosPokemonesDerrotados(ArrayList<Pokemon> equipo) {
        for (Pokemon pokemon : equipo) {
            if (!pokemon.estaDerrotado()) {
                return false;
            }
        }
        return true;
    }

    // Metodo para mostrar un selector de pokemon vivos para cambio
    private int seleccionarPokemonVivo(ArrayList<Pokemon> equipo, int indiceActual, String mensaje) {
        ArrayList<String> opciones = new ArrayList<>();
        ArrayList<Integer> indices = new ArrayList<>();

        for (int i = 0; i < equipo.size(); i++) {
            Pokemon pokemon = equipo.get(i);

            if (!pokemon.estaDerrotado() && i != indiceActual) {
                opciones.add(pokemon.getNombre() + " - HP: " + pokemon.getHp() + "/" + pokemon.getHpMax());
                indices.add(i);
            }
        }

        if (opciones.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay otros Pokémon disponibles para cambiar.");
            return -1;
        }

        String seleccion = (String) JOptionPane.showInputDialog(
                this,
                mensaje,
                "Cambio de Pokémon",
                JOptionPane.PLAIN_MESSAGE,
                null,
                opciones.toArray(),
                opciones.get(0)
        );

        if (seleccion == null) {
            return -1;
        }

        for (int i = 0; i < opciones.size(); i++) {
            if (opciones.get(i).equals(seleccion)) {
                return indices.get(i);
            }
        }

        return -1;
    }

    // Metodo para mostrar un selector de pokemon derrotados para revivir
    private int seleccionarPokemonDerrotado(ArrayList<Pokemon> equipo, String mensaje) {
        ArrayList<String> opciones = new ArrayList<>();
        ArrayList<Integer> indices = new ArrayList<>();

        for (int i = 0; i < equipo.size(); i++) {
            Pokemon pokemon = equipo.get(i);

            if (pokemon.estaDerrotado()) {
                opciones.add(pokemon.getNombre() + " - Derrotado");
                indices.add(i);
            }
        }

        if (opciones.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay Pokémon derrotados para revivir.");
            return -1;
        }

        String seleccion = (String) JOptionPane.showInputDialog(
                this,
                mensaje,
                "Revivir Pokémon",
                JOptionPane.PLAIN_MESSAGE,
                null,
                opciones.toArray(),
                opciones.get(0)
        );

        if (seleccion == null) {
            return -1;
        }

        for (int i = 0; i < opciones.size(); i++) {
            if (opciones.get(i).equals(seleccion)) {
                return indices.get(i);
            }
        }

        return -1;
    }
}