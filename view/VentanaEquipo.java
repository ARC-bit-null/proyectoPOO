package view;

import persistance.DataManager;
import model.Partida;
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

public class VentanaEquipo extends JFrame {

    // Variable donde guardaremos la partida actual
    private Partida partida;

    // Variable donde guardaremos la ventana anterior
    private VentanaGameplay ventanaGameplay;

    // Arreglo de paneles para mostrar los 6 espacios del equipo
    private JPanel[] panelesEquipo;

    // Panel que contendrá la lista de pokemones capturados
    private JPanel panelListaPokemones;

    // Label que muestra el pokemon disponible seleccionado
    private JLabel lblPokemonSeleccionado;

    // Botones principales de la ventana
    private JButton btnAgregar;
    private JButton btnQuitar;

    // Variables para guardar el pokemon seleccionado en la lista y en el equipo
    private Pokemon pokemonSeleccionadoLista;
    private Pokemon pokemonSeleccionadoEquipo;

    public VentanaEquipo(VentanaGameplay ventanaGameplay, Partida partida) {
        // Guardamos la ventana anterior y la partida actual
        this.ventanaGameplay = ventanaGameplay;
        this.partida = partida;

        setTitle("Equipo");
        setSize(1100, 750);
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
        // Panel principal de la ventana
        JPanel panelPrincipal = new JPanel(new BorderLayout(20, 20));
        panelPrincipal.setBackground(new Color(245, 245, 245));
        panelPrincipal.setBorder(new EmptyBorder(20, 20, 20, 20));

        // Título de la ventana
        JLabel lblTitulo = new JLabel("Administrar Equipo", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 32));
        lblTitulo.setForeground(new Color(50, 50, 50));

        // Panel central que contendrá la sección del equipo y la lista de pokemones
        JPanel panelCentro = new JPanel(new GridLayout(1, 2, 20, 0));
        panelCentro.setBackground(new Color(245, 245, 245));

        // Panel izquierdo para mostrar los 6 espacios del equipo
        JPanel panelEquipo = new JPanel(new GridLayout(6, 1, 10, 10));
        panelEquipo.setBackground(Color.WHITE);
        panelEquipo.setBorder(new EmptyBorder(15, 15, 15, 15));

        panelesEquipo = new JPanel[6];

        for (int i = 0; i < 6; i++) {
            panelesEquipo[i] = crearPanelEquipo(i);
            panelEquipo.add(panelesEquipo[i]);
        }

        // Panel derecho para mostrar los pokemones capturados del jugador
        panelListaPokemones = new JPanel();
        panelListaPokemones.setLayout(new BoxLayout(panelListaPokemones, BoxLayout.Y_AXIS));
        panelListaPokemones.setBackground(Color.WHITE);
        panelListaPokemones.setBorder(new EmptyBorder(15, 15, 15, 15));

        JScrollPane scrollLista = new JScrollPane(panelListaPokemones);
        scrollLista.setBorder(null);
        scrollLista.getVerticalScrollBar().setUnitIncrement(12);

        panelCentro.add(panelEquipo);
        panelCentro.add(scrollLista);

        // Panel inferior con información y botones de acción
        JPanel panelInferior = new JPanel(new BorderLayout(20, 20));
        panelInferior.setBackground(new Color(245, 245, 245));

        lblPokemonSeleccionado = new JLabel("Pokémon seleccionado: Ninguno");
        lblPokemonSeleccionado.setFont(new Font("Arial", Font.PLAIN, 18));
        lblPokemonSeleccionado.setForeground(new Color(70, 70, 70));

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        panelBotones.setBackground(new Color(245, 245, 245));

        btnAgregar = new JButton("Agregar al equipo");
        btnAgregar.setFont(new Font("Arial", Font.BOLD, 16));
        btnAgregar.setFocusPainted(false);

        btnQuitar = new JButton("Quitar del equipo");
        btnQuitar.setFont(new Font("Arial", Font.BOLD, 16));
        btnQuitar.setFocusPainted(false);

        panelBotones.add(btnAgregar);
        panelBotones.add(btnQuitar);

        panelInferior.add(lblPokemonSeleccionado, BorderLayout.WEST);
        panelInferior.add(panelBotones, BorderLayout.EAST);

        // Agregamos los elementos al panel principal
        panelPrincipal.add(lblTitulo, BorderLayout.NORTH);
        panelPrincipal.add(panelCentro, BorderLayout.CENTER);
        panelPrincipal.add(panelInferior, BorderLayout.SOUTH);

        add(panelPrincipal);

        // Cargamos la información visual del equipo y de los pokemones del jugador
        actualizarVistaEquipo();
        actualizarListaPokemones();
    }

    // Metodo para crear cada panel visual del equipo
    private JPanel crearPanelEquipo(int indice) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(252, 252, 252));
        panel.setBorder(new LineBorder(new Color(200, 200, 200), 2, true));
        panel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panel.setPreferredSize(new Dimension(400, 85));

        // Guardamos el índice del panel para identificar su posición visual
        panel.putClientProperty("indice", indice);

        return panel;
    }

    // Metodo para actualizar visualmente los 6 espacios del equipo
    private void actualizarVistaEquipo() {
        ArrayList<Pokemon> equipoActual = partida.getJugador().getEquipo().getPokemones();

        for (int i = 0; i < 6; i++) {
            JPanel panel = panelesEquipo[i];
            panel.removeAll();

            if (i < equipoActual.size()) {
                Pokemon pokemon = equipoActual.get(i);

                // Guardamos el pokemon mostrado en el panel
                panel.putClientProperty("pokemon", pokemon);

                JLabel lblNombre = new JLabel(pokemon.getNombre() + "  |  Nv." + pokemon.getNivel());
                lblNombre.setFont(new Font("Arial", Font.BOLD, 18));
                lblNombre.setBorder(new EmptyBorder(10, 15, 5, 10));

                JLabel lblDatos = new JLabel("HP: " + pokemon.getHp() + "  |  Daño: " + pokemon.getDano() + "  |  Velocidad: " + pokemon.getVelocidad());
                lblDatos.setFont(new Font("Arial", Font.PLAIN, 15));
                lblDatos.setBorder(new EmptyBorder(0, 15, 10, 10));
                lblDatos.setForeground(new Color(90, 90, 90));

                panel.add(lblNombre, BorderLayout.NORTH);
                panel.add(lblDatos, BorderLayout.CENTER);
            } else {
                // Si no hay pokemon en esa posición, mostramos el texto Vacío
                panel.putClientProperty("pokemon", null);

                JLabel lblVacio = new JLabel("Vacío", SwingConstants.CENTER);
                lblVacio.setFont(new Font("Arial", Font.BOLD, 20));
                lblVacio.setForeground(new Color(150, 150, 150));

                panel.add(lblVacio, BorderLayout.CENTER);
            }

            panel.revalidate();
            panel.repaint();
        }

        restaurarSeleccionEquipo();
    }

    // Metodo para actualizar la lista de pokemones capturados por el jugador
    private void actualizarListaPokemones() {
        panelListaPokemones.removeAll();

        ArrayList<Pokemon> pokemonesCapturados = partida.getJugador().getPokemonesCapturados();

        JLabel lblSubtitulo = new JLabel("Pokémon del jugador");
        lblSubtitulo.setFont(new Font("Arial", Font.BOLD, 24));
        lblSubtitulo.setForeground(new Color(50, 50, 50));
        lblSubtitulo.setAlignmentX(Component.LEFT_ALIGNMENT);

        panelListaPokemones.add(lblSubtitulo);
        panelListaPokemones.add(Box.createVerticalStrut(15));

        for (Pokemon pokemon : pokemonesCapturados) {
            JPanel tarjeta = crearTarjetaPokemonDisponible(pokemon);
            panelListaPokemones.add(tarjeta);
            panelListaPokemones.add(Box.createVerticalStrut(10));
        }

        panelListaPokemones.revalidate();
        panelListaPokemones.repaint();

        restaurarSeleccionLista();
    }

    // Metodo para crear la tarjeta visual de cada pokemon disponible
    private JPanel crearTarjetaPokemonDisponible(Pokemon pokemon) {
        JPanel tarjeta = new JPanel(new BorderLayout());
        tarjeta.setBackground(new Color(252, 252, 252));
        tarjeta.setBorder(new LineBorder(new Color(210, 210, 210), 2, true));
        tarjeta.setCursor(new Cursor(Cursor.HAND_CURSOR));
        tarjeta.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));

        JLabel lblNombre = new JLabel(pokemon.getNombre() + "  |  Nv." + pokemon.getNivel());
        lblNombre.setFont(new Font("Arial", Font.BOLD, 17));
        lblNombre.setBorder(new EmptyBorder(10, 15, 5, 10));

        JLabel lblDatos = new JLabel("ID: " + pokemon.getId() + "  |  HP: " + pokemon.getHp() + "  |  Tipo: " + pokemon.getTipo());
        lblDatos.setFont(new Font("Arial", Font.PLAIN, 14));
        lblDatos.setForeground(new Color(90, 90, 90));
        lblDatos.setBorder(new EmptyBorder(0, 15, 10, 10));

        tarjeta.add(lblNombre, BorderLayout.NORTH);
        tarjeta.add(lblDatos, BorderLayout.CENTER);

        // Guardamos el pokemon en la tarjeta para poder seleccionarlo al hacer clic
        tarjeta.putClientProperty("pokemon", pokemon);

        tarjeta.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                seleccionarPokemonLista(tarjeta);
            }
        });

        return tarjeta;
    }

    // Metodo donde inicializamos los eventos principales de la ventana
    private void inicializarEventos() {
        for (JPanel panelEquipo : panelesEquipo) {
            panelEquipo.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    seleccionarPokemonEquipo(panelEquipo);
                }
            });
        }

        btnAgregar.addActionListener(e -> agregarPokemonAlEquipo());
        btnQuitar.addActionListener(e -> quitarPokemonDelEquipo());
    }

    // Metodo para seleccionar un pokemon de la lista de pokemones disponibles
    private void seleccionarPokemonLista(JPanel tarjetaSeleccionada) {
        restaurarSeleccionLista();

        tarjetaSeleccionada.setBorder(new LineBorder(new Color(80, 160, 90), 3, true));

        pokemonSeleccionadoLista = (Pokemon) tarjetaSeleccionada.getClientProperty("pokemon");
        pokemonSeleccionadoEquipo = null;

        lblPokemonSeleccionado.setText("Pokémon seleccionado: " + pokemonSeleccionadoLista.getNombre() + " (lista)");
        restaurarSeleccionEquipo();
    }

    // Metodo para seleccionar un pokemon que ya está dentro del equipo
    private void seleccionarPokemonEquipo(JPanel panelSeleccionado) {
        restaurarSeleccionEquipo();

        panelSeleccionado.setBorder(new LineBorder(new Color(220, 120, 120), 3, true));

        pokemonSeleccionadoEquipo = (Pokemon) panelSeleccionado.getClientProperty("pokemon");
        pokemonSeleccionadoLista = null;

        if (pokemonSeleccionadoEquipo != null) {
            lblPokemonSeleccionado.setText("Pokémon seleccionado: " + pokemonSeleccionadoEquipo.getNombre() + " (equipo)");
        } else {
            lblPokemonSeleccionado.setText("Pokémon seleccionado: Espacio vacío");
        }

        restaurarSeleccionLista();
    }

    // Metodo para agregar un pokemon seleccionado de la lista al equipo
    private void agregarPokemonAlEquipo() {
        if (pokemonSeleccionadoLista == null) {
            JOptionPane.showMessageDialog(this, "Debes seleccionar un Pokémon de la lista.");
            return;
        }

        ArrayList<Pokemon> equipoActual = partida.getJugador().getEquipo().getPokemones();

        if (equipoActual.contains(pokemonSeleccionadoLista)) {
            JOptionPane.showMessageDialog(this, "Ese Pokémon ya está dentro del equipo.");
            return;
        }

        if (partida.getJugador().getEquipo().estaLleno()) {
            JOptionPane.showMessageDialog(this, "El equipo ya tiene 6 Pokémon.");
            return;
        }

        partida.getJugador().getEquipo().agregarPokemon(pokemonSeleccionadoLista);

        // Guardamos los cambios en la partida
        DataManager.guardarPartida(partida);

        // Actualizamos la interfaz
        actualizarVistaEquipo();
        lblPokemonSeleccionado.setText("Pokémon agregado al equipo: " + pokemonSeleccionadoLista.getNombre());

        pokemonSeleccionadoLista = null;
    }

    // Metodo para quitar un pokemon seleccionado del equipo
    private void quitarPokemonDelEquipo() {
        if (pokemonSeleccionadoEquipo == null) {
            JOptionPane.showMessageDialog(this, "Debes seleccionar un Pokémon del equipo.");
            return;
        }

        partida.getJugador().getEquipo().eliminarPokemon(pokemonSeleccionadoEquipo);

        // Guardamos los cambios en la partida
        DataManager.guardarPartida(partida);

        // Actualizamos la interfaz
        actualizarVistaEquipo();
        lblPokemonSeleccionado.setText("Pokémon eliminado del equipo: " + pokemonSeleccionadoEquipo.getNombre());

        pokemonSeleccionadoEquipo = null;
    }

    // Metodo para restaurar el estilo visual de la lista de pokemones disponibles
    private void restaurarSeleccionLista() {
        Component[] componentes = panelListaPokemones.getComponents();

        for (Component componente : componentes) {
            if (componente instanceof JPanel) {
                componente.setBackground(new Color(252, 252, 252));
                ((JPanel) componente).setBorder(new LineBorder(new Color(210, 210, 210), 2, true));
            }
        }
    }

    // Metodo para restaurar el estilo visual de los paneles del equipo
    private void restaurarSeleccionEquipo() {
        for (JPanel panel : panelesEquipo) {
            panel.setBackground(new Color(252, 252, 252));
            panel.setBorder(new LineBorder(new Color(200, 200, 200), 2, true));
        }
    }
}
