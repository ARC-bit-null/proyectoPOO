package view;

import persistance.DataManager;
import model.Partida;
import model.Pokedex;
import model.Pokemon;
import model.Jugador;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class VentanaInfoPartida extends JFrame {

    // Declaramos los elementos de la ventana
    private JTextField txtNombreJugador;
    private JButton btnCrear;

    private JPanel panelBulbasaur;
    private JPanel panelCharmander;
    private JPanel panelSquirtle;

    private int idPokemonSeleccionado = -1;

    // Variable para guardar el slot en el que se creará la partida
    private int slot;

    public VentanaInfoPartida(VentanaPartidas ventanaPartidas, int slot) {
        // Guardamos el slot recibido para poder usarlo al crear la partida
        this.slot = slot;

        setTitle("Crear Partida");
        setSize(900, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                ventanaPartidas.setVisible(true);
            }
        });

        inicializarComponentes(slot);
        inicializarEventos(ventanaPartidas);

        setVisible(true);
    }

    // Inicializamos los elementos de la ventana
    private void inicializarComponentes(int slot) {
        // Declaramos las dimensiones del panel principal
        JPanel panelPrincipal = new JPanel(new BorderLayout());
        panelPrincipal.setBackground(new Color(245, 245, 245));
        panelPrincipal.setBorder(new EmptyBorder(30, 30, 30, 30));

        // Título principal de la ventana
        JLabel lblTitulo = new JLabel("Crear Partida - Slot " + slot, SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 32));
        lblTitulo.setBorder(new EmptyBorder(10, 0, 30, 0));

        // Panel principal donde pondremos los paneles de los principales y el textbox del nombre del jugador
        JPanel panelContenido = new JPanel();
        panelContenido.setLayout(new BorderLayout(0, 30));
        panelContenido.setBackground(new Color(245, 245, 245));

        // Determinamos las dimensiones del label NombreJugador
        JPanel panelNombre = new JPanel(new BorderLayout(10, 10));
        panelNombre.setBackground(new Color(245, 245, 245));

        JLabel lblNombreJugador = new JLabel("Nombre del jugador:");
        lblNombreJugador.setFont(new Font("Arial", Font.PLAIN, 20));

        // Creamos el textbox donde escribiremos el nombre del jugador
        txtNombreJugador = new JTextField();
        txtNombreJugador.setFont(new Font("Arial", Font.PLAIN, 20));

        panelNombre.add(lblNombreJugador, BorderLayout.NORTH);
        panelNombre.add(txtNombreJugador, BorderLayout.CENTER);

        // Panel donde pondremos los recuadros de los pokemosnes
        JPanel panelIniciales = new JPanel(new GridLayout(1, 3, 20, 20));
        panelIniciales.setBackground(new Color(245, 245, 245));

        panelBulbasaur = crearTarjetaPokemon(Pokedex.crearPokemon(1, 5));
        panelCharmander = crearTarjetaPokemon(Pokedex.crearPokemon(4, 5));
        panelSquirtle = crearTarjetaPokemon(Pokedex.crearPokemon(7, 5));

        panelIniciales.add(panelBulbasaur);
        panelIniciales.add(panelCharmander);
        panelIniciales.add(panelSquirtle);

        // Declaramos y creamos el boton Crear
        btnCrear = new JButton("Crear Partida");
        btnCrear.setFont(new Font("Arial", Font.BOLD, 20));
        btnCrear.setFocusPainted(false);
        btnCrear.setEnabled(false);

        JPanel panelBoton = new JPanel();
        panelBoton.setBackground(new Color(245, 245, 245));
        panelBoton.add(btnCrear);

        // Inicializamos los elementos de la ventana
        panelContenido.add(panelNombre, BorderLayout.NORTH);
        panelContenido.add(panelIniciales, BorderLayout.CENTER);
        panelContenido.add(panelBoton, BorderLayout.SOUTH);

        panelPrincipal.add(lblTitulo, BorderLayout.NORTH);
        panelPrincipal.add(panelContenido, BorderLayout.CENTER);

        add(panelPrincipal);
    }

    // Metodo donde inicializaremos y crearemos los paneles de los iniciales
    private JPanel crearTarjetaPokemon(Pokemon pokemon) {
        // Delimitamos sus dimensions, color y forma de las tarjetas
        JPanel tarjeta = new JPanel();
        tarjeta.setBackground(Color.WHITE);
        tarjeta.setBorder(new LineBorder(new Color(220, 190, 190), 2, true));
        tarjeta.setLayout(new BorderLayout());
        tarjeta.setCursor(new Cursor(Cursor.HAND_CURSOR));
        tarjeta.setPreferredSize(new Dimension(200, 280));

        // Creamos el lbl superior donde nos mostrara el id del pokemon
        JLabel lblId = new JLabel("#" + pokemon.getId());
        lblId.setFont(new Font("Arial", Font.BOLD, 16));
        lblId.setForeground(Color.WHITE);
        lblId.setOpaque(true);
        lblId.setBackground(new Color(210, 140, 120));
        lblId.setBorder(new EmptyBorder(5, 10, 5, 10));

        JPanel panelSuperior = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        panelSuperior.setOpaque(false);
        panelSuperior.add(lblId);

        // lbl donde mostraremos una futura imagen del pokemon
        JLabel lblImagen = new JLabel();
        lblImagen.setHorizontalAlignment(SwingConstants.CENTER);

        ImageIcon icono = cargarImagenPokemon(pokemon.getNombre());
        if (icono != null && icono.getIconWidth() > 0) {
            Image imagenEscalada = icono.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
            lblImagen.setIcon(new ImageIcon(imagenEscalada));
        } else {
            lblImagen.setText("Sin imagen");
            lblImagen.setFont(new Font("Arial", Font.PLAIN, 14));
        }

        // lbls donde mostraremos la información del pokemones como el nombre y tipo
        JLabel lblNombre = new JLabel(pokemon.getNombre(), SwingConstants.CENTER);
        lblNombre.setFont(new Font("Arial", Font.BOLD, 24));
        lblNombre.setForeground(new Color(50, 50, 50));

        JLabel lblTipo = new JLabel(String.valueOf(pokemon.getTipo()), SwingConstants.CENTER);
        lblTipo.setFont(new Font("Arial", Font.PLAIN, 16));
        lblTipo.setForeground(new Color(180, 130, 130));

        JPanel panelCentro = new JPanel();
        panelCentro.setBackground(Color.WHITE);
        panelCentro.setLayout(new BoxLayout(panelCentro, BoxLayout.Y_AXIS));
        panelCentro.setBorder(new EmptyBorder(10, 10, 10, 10));

        // inicializamos los elementos de los paneles
        lblImagen.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblNombre.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblTipo.setAlignmentX(Component.CENTER_ALIGNMENT);

        panelCentro.add(Box.createVerticalStrut(10));
        panelCentro.add(lblImagen);
        panelCentro.add(Box.createVerticalStrut(10));
        panelCentro.add(lblNombre);
        panelCentro.add(Box.createVerticalStrut(5));
        panelCentro.add(lblTipo);

        tarjeta.add(panelSuperior, BorderLayout.NORTH);
        tarjeta.add(panelCentro, BorderLayout.CENTER);

        // Guardamos el objeto Pokemon en la tarjeta para poder recuperarlo cuando se haga clic
        tarjeta.putClientProperty("pokemon", pokemon);

        return tarjeta;
    }

    // Metodo que nos permite dar clic sobre las tarjetas
    private void inicializarEventos(VentanaPartidas ventanaPartidas) {
        panelBulbasaur.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                seleccionarPokemon(panelBulbasaur);
            }
        });

        panelCharmander.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                seleccionarPokemon(panelCharmander);
            }
        });

        panelSquirtle.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                seleccionarPokemon(panelSquirtle);
            }
        });

        // Evento del botón para crear la partida, guardarla y abrir la ventana de gameplay
        btnCrear.addActionListener(e -> crearPartida(ventanaPartidas));
    }

    // Metodo para guardar el pokemon seleccionado y remarcar visualmente la tarjeta elegida
    private void seleccionarPokemon(JPanel panelSeleccionado) {
        restaurarBordes();

        panelSeleccionado.setBorder(new LineBorder(new Color(80, 160, 90), 4, true));

        Pokemon pokemon = (Pokemon) panelSeleccionado.getClientProperty("pokemon");
        idPokemonSeleccionado = pokemon.getId();

        btnCrear.setEnabled(true);
    }

    // Metodo para rastaurar los bordes si es que cambiamos de pokemon seleccionado
    private void restaurarBordes() {
        panelBulbasaur.setBorder(new LineBorder(new Color(220, 190, 190), 2, true));
        panelCharmander.setBorder(new LineBorder(new Color(220, 190, 190), 2, true));
        panelSquirtle.setBorder(new LineBorder(new Color(220, 190, 190), 2, true));
    }

    // Metodo para cargar una futura imagen de los pokemones
    private ImageIcon cargarImagenPokemon(String nombrePokemon) {
        String ruta = "assets/" + nombrePokemon.toLowerCase() + ".png";
        ImageIcon icono = new ImageIcon(ruta);

        if (icono.getIconWidth() == -1) {
            return null;
        }

        return icono;
    }

    // Metodo para crear el objeto partida, guardarlo en archivo y abrir la ventana de gameplay
    private void crearPartida(VentanaPartidas ventanaPartidas) {
        // Obtenemos el nombre del jugador escrito en el textbox
        String nombreJugador = txtNombreJugador.getText().trim();

        // Validamos que el nombre del jugador no esté vacío
        if (nombreJugador.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debes ingresar el nombre del jugador.");
            return;
        }

        // Validamos que exista un pokemon inicial seleccionado
        if (idPokemonSeleccionado == -1) {
            JOptionPane.showMessageDialog(this, "Debes seleccionar un Pokémon inicial.");
            return;
        }

        // Creamos el pokemon inicial usando el id seleccionado y los datos base de la pokedex
        Pokemon pokemonInicial = Pokedex.crearPokemon(idPokemonSeleccionado, 5);

        // Creamos el jugador con el nombre del jugador y su pokemon inicial
        Jugador jugador = new Jugador(nombreJugador, pokemonInicial);

        // Creamos el objeto partida con el slot actual y el jugador creado
        Partida partida = new Partida(slot, jugador);

        // Guardamos la partida en su archivo correspondiente
        DataManager.guardarPartida(partida);

        // Cerramos esta ventana y abrimos la ventana de gameplay con la partida creada
        dispose();
        new VentanaGameplay(ventanaPartidas, partida);
    }
}