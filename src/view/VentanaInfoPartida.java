package src.view;

import src.model.Partida;
import src.model.Pokedex;
import src.model.Pokemon;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class VentanaInfoPartida extends JFrame {

    private JTextField txtNombreJugador;
    private JButton btnCrear;

    private JPanel panelBulbasaur;
    private JPanel panelCharmander;
    private JPanel panelSquirtle;

    private int idPokemonSeleccionado = -1;
    private int slot;

    private boolean abriendoGameplay = false;

    public VentanaInfoPartida(VentanaPartidas ventanaPartidas, int slot) {
        this.slot = slot;

        setTitle("Crear Partida");
        setSize(900, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                if (!abriendoGameplay) {
                    ventanaPartidas.setVisible(true);
                }
            }
        });

        inicializarComponentes(slot);
        inicializarEventos();

        setVisible(true);
    }

    private void inicializarComponentes(int slot) {
        JPanel panelPrincipal = new JPanel(new BorderLayout());
        panelPrincipal.setBackground(new Color(245, 245, 245));
        panelPrincipal.setBorder(new EmptyBorder(30, 30, 30, 30));

        JLabel lblTitulo = new JLabel("Crear Partida - Slot " + slot, SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 32));
        lblTitulo.setBorder(new EmptyBorder(10, 0, 30, 0));

        JPanel panelContenido = new JPanel();
        panelContenido.setLayout(new BorderLayout(0, 30));
        panelContenido.setBackground(new Color(245, 245, 245));

        JPanel panelNombre = new JPanel(new BorderLayout(10, 10));
        panelNombre.setBackground(new Color(245, 245, 245));

        JLabel lblNombreJugador = new JLabel("Nombre del jugador:");
        lblNombreJugador.setFont(new Font("Arial", Font.PLAIN, 20));

        txtNombreJugador = new JTextField();
        txtNombreJugador.setFont(new Font("Arial", Font.PLAIN, 20));

        panelNombre.add(lblNombreJugador, BorderLayout.NORTH);
        panelNombre.add(txtNombreJugador, BorderLayout.CENTER);

        JPanel panelIniciales = new JPanel(new GridLayout(1, 3, 20, 20));
        panelIniciales.setBackground(new Color(245, 245, 245));

        panelBulbasaur = crearTarjetaPokemon(Pokedex.crearPokemon(1, 5));
        panelCharmander = crearTarjetaPokemon(Pokedex.crearPokemon(4, 5));
        panelSquirtle = crearTarjetaPokemon(Pokedex.crearPokemon(7, 5));

        panelIniciales.add(panelBulbasaur);
        panelIniciales.add(panelCharmander);
        panelIniciales.add(panelSquirtle);

        btnCrear = new JButton("Crear Partida");
        btnCrear.setFont(new Font("Arial", Font.BOLD, 20));
        btnCrear.setFocusPainted(false);
        btnCrear.setEnabled(false);

        JPanel panelBoton = new JPanel();
        panelBoton.setBackground(new Color(245, 245, 245));
        panelBoton.add(btnCrear);

        panelContenido.add(panelNombre, BorderLayout.NORTH);
        panelContenido.add(panelIniciales, BorderLayout.CENTER);
        panelContenido.add(panelBoton, BorderLayout.SOUTH);

        panelPrincipal.add(lblTitulo, BorderLayout.NORTH);
        panelPrincipal.add(panelContenido, BorderLayout.CENTER);

        add(panelPrincipal);
    }

    private JPanel crearTarjetaPokemon(Pokemon pokemon) {
        JPanel tarjeta = new JPanel();
        tarjeta.setBackground(Color.WHITE);
        tarjeta.setBorder(new LineBorder(new Color(220, 190, 190), 2, true));
        tarjeta.setLayout(new BorderLayout());
        tarjeta.setCursor(new Cursor(Cursor.HAND_CURSOR));
        tarjeta.setPreferredSize(new Dimension(200, 280));

        JLabel lblId = new JLabel("#" + pokemon.getId());
        lblId.setFont(new Font("Arial", Font.BOLD, 16));
        lblId.setForeground(Color.WHITE);
        lblId.setOpaque(true);
        lblId.setBackground(new Color(210, 140, 120));
        lblId.setBorder(new EmptyBorder(5, 10, 5, 10));

        JPanel panelSuperior = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        panelSuperior.setOpaque(false);
        panelSuperior.add(lblId);

        JLabel lblImagen = new JLabel();
        lblImagen.setHorizontalAlignment(SwingConstants.CENTER);

        ImageIcon icono = cargarImagenPokemon(pokemon.getImagenFrontal());
        if (icono != null && icono.getIconWidth() > 0) {
            Image imagenEscalada = icono.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
            lblImagen.setIcon(new ImageIcon(imagenEscalada));
        } else {
            lblImagen.setText("Sin imagen");
            lblImagen.setFont(new Font("Arial", Font.PLAIN, 14));
        }

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

        tarjeta.putClientProperty("pokemon", pokemon);

        return tarjeta;
    }

    private void inicializarEventos() {
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
    }

    private void seleccionarPokemon(JPanel panelSeleccionado) {
        restaurarBordes();

        panelSeleccionado.setBorder(new LineBorder(new Color(80, 160, 90), 4, true));

        Pokemon pokemon = (Pokemon) panelSeleccionado.getClientProperty("pokemon");
        idPokemonSeleccionado = pokemon.getId();

        btnCrear.setEnabled(true);
    }

    public void restaurarBordes() {
        panelBulbasaur.setBorder(new LineBorder(new Color(220, 190, 190), 2, true));
        panelCharmander.setBorder(new LineBorder(new Color(220, 190, 190), 2, true));
        panelSquirtle.setBorder(new LineBorder(new Color(220, 190, 190), 2, true));
    }

    private ImageIcon cargarImagenPokemon(String rutaImagen) {
        if (rutaImagen == null || rutaImagen.isEmpty()) {
            return null;
        }

        ImageIcon icono = new ImageIcon(rutaImagen);

        if (icono.getIconWidth() <= 0) {
            return null;
        }

        return icono;
    }

    public void mostrarDialogo(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    public JTextField getTxtNombreJugador() {
        return txtNombreJugador;
    }

    public JButton getBtnCrear() {
        return btnCrear;
    }

    public int getIdPokemonSeleccionado() {
        return idPokemonSeleccionado;
    }

    public int getSlot() {
        return slot;
    }

    public void setAbriendoGameplay(boolean abriendoGameplay) {
        this.abriendoGameplay = abriendoGameplay;
    }
}