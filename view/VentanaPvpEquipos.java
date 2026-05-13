package view;

import model.Partida;
import model.Pokedex;
import model.Pokemon;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class VentanaPvpEquipos extends JFrame {

    // Variable para guardar la ventana anterior
    private VentanaGameplay ventanaGameplay;

    // Variable para guardar la partida actual
    private Partida partida;

    // Arreglos de paneles para mostrar los equipos de ambos jugadores
    private JPanel[] panelesJugador1;
    private JPanel[] panelesJugador2;

    // Panel que contendrá la lista de pokemones disponibles
    private JPanel panelListaPokemones;

    // Botones de acciones
    private JButton btnAgregarJ1;
    private JButton btnAgregarJ2;
    private JButton btnQuitarJ1;
    private JButton btnQuitarJ2;
    private JButton btnListo;

    // Label para mostrar información de selección
    private JLabel lblSeleccionActual;

    // Variables para guardar selecciones
    private Pokemon pokemonSeleccionadoLista;
    private Pokemon pokemonSeleccionadoJ1;
    private Pokemon pokemonSeleccionadoJ2;

    // Equipos temporales de ambos jugadores para el combate PvP
    private ArrayList<Pokemon> equipoJugador1;
    private ArrayList<Pokemon> equipoJugador2;

    public VentanaPvpEquipos(VentanaGameplay ventanaGameplay, Partida partida) {
        // Guardamos referencias necesarias
        this.ventanaGameplay = ventanaGameplay;
        this.partida = partida;

        // Inicializamos los equipos vacíos de ambos jugadores
        equipoJugador1 = new ArrayList<>();
        equipoJugador2 = new ArrayList<>();

        setTitle("PvP - Selección de Equipos");
        setSize(1300, 780);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                ventanaGameplay.setVisible(true);
            }
        });

        inicializarComponentes();
        inicializarEventos();

        setVisible(true);
    }

    // Metodo donde inicializamos todos los elementos visuales de la ventana
    private void inicializarComponentes() {
        // Panel principal
        JPanel panelPrincipal = new JPanel(new BorderLayout(20, 20));
        panelPrincipal.setBackground(new Color(245, 245, 245));
        panelPrincipal.setBorder(new EmptyBorder(20, 20, 20, 20));

        // Título principal
        JLabel lblTitulo = new JLabel("Seleccion de Equipos", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 32));
        lblTitulo.setForeground(new Color(50, 50, 50));

        // Panel central con equipos y lista de pokemones
        JPanel panelCentro = new JPanel(new GridLayout(1, 3, 20, 20));
        panelCentro.setBackground(new Color(245, 245, 245));

        // Panel del Jugador 1
        JPanel panelJ1 = new JPanel(new BorderLayout(10, 10));
        panelJ1.setBackground(Color.WHITE);
        panelJ1.setBorder(new EmptyBorder(15, 15, 15, 15));

        JLabel lblJugador1 = new JLabel("Equipo Jugador 1", SwingConstants.CENTER);
        lblJugador1.setFont(new Font("Arial", Font.BOLD, 24));

        JPanel panelEquipoJ1 = new JPanel(new GridLayout(6, 1, 10, 10));
        panelEquipoJ1.setBackground(Color.WHITE);

        panelesJugador1 = new JPanel[6];
        for (int i = 0; i < 6; i++) {
            panelesJugador1[i] = crearPanelEquipo(i, 1);
            panelEquipoJ1.add(panelesJugador1[i]);
        }

        panelJ1.add(lblJugador1, BorderLayout.NORTH);
        panelJ1.add(panelEquipoJ1, BorderLayout.CENTER);

        // Panel de lista de pokemones disponibles
        JPanel panelCentroLista = new JPanel(new BorderLayout(10, 10));
        panelCentroLista.setBackground(Color.WHITE);
        panelCentroLista.setBorder(new EmptyBorder(15, 15, 15, 15));

        JLabel lblLista = new JLabel("Pokémon Disponibles", SwingConstants.CENTER);
        lblLista.setFont(new Font("Arial", Font.BOLD, 24));

        panelListaPokemones = new JPanel();
        panelListaPokemones.setLayout(new BoxLayout(panelListaPokemones, BoxLayout.Y_AXIS));
        panelListaPokemones.setBackground(Color.WHITE);

        JScrollPane scrollLista = new JScrollPane(panelListaPokemones);
        scrollLista.setBorder(null);
        scrollLista.getVerticalScrollBar().setUnitIncrement(12);

        panelCentroLista.add(lblLista, BorderLayout.NORTH);
        panelCentroLista.add(scrollLista, BorderLayout.CENTER);

        // Panel del Jugador 2
        JPanel panelJ2 = new JPanel(new BorderLayout(10, 10));
        panelJ2.setBackground(Color.WHITE);
        panelJ2.setBorder(new EmptyBorder(15, 15, 15, 15));

        JLabel lblJugador2 = new JLabel("Equipo Jugador 2", SwingConstants.CENTER);
        lblJugador2.setFont(new Font("Arial", Font.BOLD, 24));

        JPanel panelEquipoJ2 = new JPanel(new GridLayout(6, 1, 10, 10));
        panelEquipoJ2.setBackground(Color.WHITE);

        panelesJugador2 = new JPanel[6];
        for (int i = 0; i < 6; i++) {
            panelesJugador2[i] = crearPanelEquipo(i, 2);
            panelEquipoJ2.add(panelesJugador2[i]);
        }

        panelJ2.add(lblJugador2, BorderLayout.NORTH);
        panelJ2.add(panelEquipoJ2, BorderLayout.CENTER);

        panelCentro.add(panelJ1);
        panelCentro.add(panelCentroLista);
        panelCentro.add(panelJ2);

        // Panel inferior con acciones
        JPanel panelInferior = new JPanel(new BorderLayout(20, 20));
        panelInferior.setBackground(new Color(245, 245, 245));

        lblSeleccionActual = new JLabel("Pokémon seleccionado: Ninguno");
        lblSeleccionActual.setFont(new Font("Arial", Font.PLAIN, 18));

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
        panelBotones.setBackground(new Color(245, 245, 245));

        btnAgregarJ1 = new JButton("Agregar a Jugador 1");
        btnAgregarJ2 = new JButton("Agregar a Jugador 2");
        btnQuitarJ1 = new JButton("Quitar de Jugador 1");
        btnQuitarJ2 = new JButton("Quitar de Jugador 2");
        btnListo = new JButton("Listo");

        btnAgregarJ1.setFont(new Font("Arial", Font.BOLD, 14));
        btnAgregarJ2.setFont(new Font("Arial", Font.BOLD, 14));
        btnQuitarJ1.setFont(new Font("Arial", Font.BOLD, 14));
        btnQuitarJ2.setFont(new Font("Arial", Font.BOLD, 14));
        btnListo.setFont(new Font("Arial", Font.BOLD, 16));

        panelBotones.add(btnAgregarJ1);
        panelBotones.add(btnAgregarJ2);
        panelBotones.add(btnQuitarJ1);
        panelBotones.add(btnQuitarJ2);
        panelBotones.add(btnListo);

        panelInferior.add(lblSeleccionActual, BorderLayout.WEST);
        panelInferior.add(panelBotones, BorderLayout.EAST);

        panelPrincipal.add(lblTitulo, BorderLayout.NORTH);
        panelPrincipal.add(panelCentro, BorderLayout.CENTER);
        panelPrincipal.add(panelInferior, BorderLayout.SOUTH);

        add(panelPrincipal);

        // Cargamos la información visual inicial
        actualizarListaPokemones();
        actualizarEquipos();
    }

    // Metodo para crear cada panel visual de los equipos de los jugadores
    private JPanel crearPanelEquipo(int indice, int jugador) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(252, 252, 252));
        panel.setBorder(new LineBorder(new Color(200, 200, 200), 2, true));
        panel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panel.setPreferredSize(new Dimension(300, 85));

        panel.putClientProperty("indice", indice);
        panel.putClientProperty("jugador", jugador);

        return panel;
    }

    // Metodo para actualizar visualmente ambos equipos
    private void actualizarEquipos() {
        actualizarEquipoJugador(panelesJugador1, equipoJugador1);
        actualizarEquipoJugador(panelesJugador2, equipoJugador2);

        restaurarSeleccionJugador1();
        restaurarSeleccionJugador2();
    }

    // Metodo para actualizar un equipo específico
    private void actualizarEquipoJugador(JPanel[] paneles, ArrayList<Pokemon> equipo) {
        for (int i = 0; i < 6; i++) {
            JPanel panel = paneles[i];
            panel.removeAll();

            if (i < equipo.size()) {
                Pokemon pokemon = equipo.get(i);

                panel.putClientProperty("pokemon", pokemon);

                JLabel lblNombre = new JLabel(pokemon.getNombre() + "  |  Nv." + pokemon.getNivel());
                lblNombre.setFont(new Font("Arial", Font.BOLD, 16));
                lblNombre.setBorder(new EmptyBorder(10, 15, 5, 10));

                JLabel lblDatos = new JLabel("HP: " + pokemon.getHp() + "  |  Daño: " + pokemon.getDano() + "  |  Velocidad: " + pokemon.getVelocidad());
                lblDatos.setFont(new Font("Arial", Font.PLAIN, 14));
                lblDatos.setBorder(new EmptyBorder(0, 15, 10, 10));
                lblDatos.setForeground(new Color(90, 90, 90));

                panel.add(lblNombre, BorderLayout.NORTH);
                panel.add(lblDatos, BorderLayout.CENTER);
            } else {
                panel.putClientProperty("pokemon", null);

                JLabel lblVacio = new JLabel("Vacío", SwingConstants.CENTER);
                lblVacio.setFont(new Font("Arial", Font.BOLD, 18));
                lblVacio.setForeground(new Color(150, 150, 150));

                panel.add(lblVacio, BorderLayout.CENTER);
            }

            panel.revalidate();
            panel.repaint();
        }
    }

    // Metodo para actualizar la lista de pokemones disponibles
    private void actualizarListaPokemones() {
        panelListaPokemones.removeAll();

        for (Pokemon pokemon : partida.getJugador().getPokemonesCapturados()) {
            JPanel tarjeta = crearTarjetaPokemonDisponible(pokemon);
            panelListaPokemones.add(tarjeta);
            panelListaPokemones.add(Box.createVerticalStrut(10));
        }

        panelListaPokemones.revalidate();
        panelListaPokemones.repaint();

        restaurarSeleccionLista();
    }

    // Metodo para crear una tarjeta visual de un pokemon disponible
    private JPanel crearTarjetaPokemonDisponible(Pokemon pokemon) {
        JPanel tarjeta = new JPanel(new BorderLayout());
        tarjeta.setBackground(new Color(252, 252, 252));
        tarjeta.setBorder(new LineBorder(new Color(210, 210, 210), 2, true));
        tarjeta.setCursor(new Cursor(Cursor.HAND_CURSOR));
        tarjeta.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));

        JLabel lblNombre = new JLabel(pokemon.getNombre() + "  |  Nv." + pokemon.getNivel());
        lblNombre.setFont(new Font("Arial", Font.BOLD, 16));
        lblNombre.setBorder(new EmptyBorder(10, 15, 5, 10));

        JLabel lblDatos = new JLabel("ID: " + pokemon.getId() + "  |  Tipo: " + pokemon.getTipo() + "  |  HP: " + pokemon.getHp());
        lblDatos.setFont(new Font("Arial", Font.PLAIN, 14));
        lblDatos.setForeground(new Color(90, 90, 90));
        lblDatos.setBorder(new EmptyBorder(0, 15, 10, 10));

        tarjeta.add(lblNombre, BorderLayout.NORTH);
        tarjeta.add(lblDatos, BorderLayout.CENTER);

        panelListaPokemones.revalidate();
        panelListaPokemones.repaint();

        tarjeta.putClientProperty("pokemon", pokemon);

        tarjeta.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                seleccionarPokemonLista(tarjeta);
            }
        });

        return tarjeta;
    }

    // Metodo donde inicializamos los eventos principales
    private void inicializarEventos() {
        for (JPanel panel : panelesJugador1) {
            panel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    seleccionarPokemonJugador1(panel);
                }
            });
        }

        for (JPanel panel : panelesJugador2) {
            panel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    seleccionarPokemonJugador2(panel);
                }
            });
        }

        btnAgregarJ1.addActionListener(e -> agregarPokemonAJugador1());
        btnAgregarJ2.addActionListener(e -> agregarPokemonAJugador2());
        btnQuitarJ1.addActionListener(e -> quitarPokemonJugador1());
        btnQuitarJ2.addActionListener(e -> quitarPokemonJugador2());
        btnListo.addActionListener(e -> validarEquiposYContinuar());
    }

    // Metodo para seleccionar un pokemon de la lista disponible
    private void seleccionarPokemonLista(JPanel tarjetaSeleccionada) {
        restaurarSeleccionLista();

        tarjetaSeleccionada.setBorder(new LineBorder(new Color(80, 160, 90), 3, true));

        pokemonSeleccionadoLista = (Pokemon) tarjetaSeleccionada.getClientProperty("pokemon");
        pokemonSeleccionadoJ1 = null;
        pokemonSeleccionadoJ2 = null;

        lblSeleccionActual.setText("Pokémon seleccionado: " + pokemonSeleccionadoLista.getNombre() + " (lista)");
        restaurarSeleccionJugador1();
        restaurarSeleccionJugador2();
    }

    // Metodo para seleccionar un pokemon del equipo del Jugador 1
    private void seleccionarPokemonJugador1(JPanel panelSeleccionado) {
        restaurarSeleccionJugador1();

        panelSeleccionado.setBorder(new LineBorder(new Color(220, 120, 120), 3, true));

        pokemonSeleccionadoJ1 = (Pokemon) panelSeleccionado.getClientProperty("pokemon");
        pokemonSeleccionadoLista = null;
        pokemonSeleccionadoJ2 = null;

        if (pokemonSeleccionadoJ1 != null) {
            lblSeleccionActual.setText("Pokémon seleccionado: " + pokemonSeleccionadoJ1.getNombre() + " (Jugador 1)");
        } else {
            lblSeleccionActual.setText("Pokémon seleccionado: Espacio vacío de Jugador 1");
        }

        restaurarSeleccionLista();
        restaurarSeleccionJugador2();
    }

    // Metodo para seleccionar un pokemon del equipo del Jugador 2
    private void seleccionarPokemonJugador2(JPanel panelSeleccionado) {
        restaurarSeleccionJugador2();

        panelSeleccionado.setBorder(new LineBorder(new Color(220, 120, 120), 3, true));

        pokemonSeleccionadoJ2 = (Pokemon) panelSeleccionado.getClientProperty("pokemon");
        pokemonSeleccionadoLista = null;
        pokemonSeleccionadoJ1 = null;

        if (pokemonSeleccionadoJ2 != null) {
            lblSeleccionActual.setText("Pokémon seleccionado: " + pokemonSeleccionadoJ2.getNombre() + " (Jugador 2)");
        } else {
            lblSeleccionActual.setText("Pokémon seleccionado: Espacio vacío de Jugador 2");
        }

        restaurarSeleccionLista();
        restaurarSeleccionJugador1();
    }

    // Metodo para agregar un pokemon al equipo del Jugador 1
    private void agregarPokemonAJugador1() {
        if (pokemonSeleccionadoLista == null) {
            JOptionPane.showMessageDialog(this, "Debes seleccionar un Pokémon de la lista.");
            return;
        }

        if (equipoJugador1.size() >= 6) {
            JOptionPane.showMessageDialog(this, "El equipo del Jugador 1 ya tiene 6 Pokémon.");
            return;
        }

        equipoJugador1.add(Pokedex.crearPokemon(pokemonSeleccionadoLista.getId(), pokemonSeleccionadoLista.getNivel()));
        actualizarEquipos();
        lblSeleccionActual.setText("Pokémon agregado al equipo del Jugador 1: " + pokemonSeleccionadoLista.getNombre());
        pokemonSeleccionadoLista = null;
        restaurarSeleccionLista();
    }

    // Metodo para agregar un pokemon al equipo del Jugador 2
    private void agregarPokemonAJugador2() {
        if (pokemonSeleccionadoLista == null) {
            JOptionPane.showMessageDialog(this, "Debes seleccionar un Pokémon de la lista.");
            return;
        }

        if (equipoJugador2.size() >= 6) {
            JOptionPane.showMessageDialog(this, "El equipo del Jugador 2 ya tiene 6 Pokémon.");
            return;
        }

        equipoJugador2.add(Pokedex.crearPokemon(pokemonSeleccionadoLista.getId(), pokemonSeleccionadoLista.getNivel()));
        actualizarEquipos();
        lblSeleccionActual.setText("Pokémon agregado al equipo del Jugador 2: " + pokemonSeleccionadoLista.getNombre());
        pokemonSeleccionadoLista = null;
        restaurarSeleccionLista();
    }

    // Metodo para quitar un pokemon del equipo del Jugador 1
    private void quitarPokemonJugador1() {
        if (pokemonSeleccionadoJ1 == null) {
            JOptionPane.showMessageDialog(this, "Debes seleccionar un Pokémon del equipo del Jugador 1.");
            return;
        }

        equipoJugador1.remove(pokemonSeleccionadoJ1);
        actualizarEquipos();
        lblSeleccionActual.setText("Pokémon eliminado del equipo del Jugador 1: " + pokemonSeleccionadoJ1.getNombre());
        pokemonSeleccionadoJ1 = null;
    }

    // Metodo para quitar un pokemon del equipo del Jugador 2
    private void quitarPokemonJugador2() {
        if (pokemonSeleccionadoJ2 == null) {
            JOptionPane.showMessageDialog(this, "Debes seleccionar un Pokémon del equipo del Jugador 2.");
            return;
        }

        equipoJugador2.remove(pokemonSeleccionadoJ2);
        actualizarEquipos();
        lblSeleccionActual.setText("Pokémon eliminado del equipo del Jugador 2: " + pokemonSeleccionadoJ2.getNombre());
        pokemonSeleccionadoJ2 = null;
    }

    // Metodo para validar que ambos jugadores tengan al menos un pokemon antes de continuar
    private void validarEquiposYContinuar() {
        if (equipoJugador1.isEmpty()) {
            JOptionPane.showMessageDialog(this, "El Jugador 1 debe tener al menos un Pokémon.");
            return;
        }

        if (equipoJugador2.isEmpty()) {
            JOptionPane.showMessageDialog(this, "El Jugador 2 debe tener al menos un Pokémon.");
            return;
        }

        setVisible(false);
        new VentanaPvp(this, equipoJugador1, equipoJugador2);
    }

    // Metodo para restaurar el estilo visual de la lista de pokemones
    private void restaurarSeleccionLista() {
        Component[] componentes = panelListaPokemones.getComponents();

        for (Component componente : componentes) {
            if (componente instanceof JPanel) {
                ((JPanel) componente).setBorder(new LineBorder(new Color(210, 210, 210), 2, true));
                componente.setBackground(new Color(252, 252, 252));
            }
        }
    }

    // Metodo para restaurar el estilo visual del equipo del Jugador 1
    private void restaurarSeleccionJugador1() {
        for (JPanel panel : panelesJugador1) {
            panel.setBorder(new LineBorder(new Color(200, 200, 200), 2, true));
            panel.setBackground(new Color(252, 252, 252));
        }
    }

    // Metodo para restaurar el estilo visual del equipo del Jugador 2
    private void restaurarSeleccionJugador2() {
        for (JPanel panel : panelesJugador2) {
            panel.setBorder(new LineBorder(new Color(200, 200, 200), 2, true));
            panel.setBackground(new Color(252, 252, 252));
        }
    }
}