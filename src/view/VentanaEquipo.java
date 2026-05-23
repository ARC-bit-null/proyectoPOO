package src.view;

import src.model.Partida;
import src.model.Pokemon;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
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

    public VentanaEquipo(VentanaGameplay ventanaGameplay, Partida partida) {
        this.ventanaGameplay = ventanaGameplay;
        this.partida = partida;

        setTitle("Equipo");
        setSize(1100, 750);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        inicializarComponentes();

        setVisible(true);
    }

    // Metodo donde inicializamos todos los elementos visuales de la ventana
    private void inicializarComponentes() {
        JPanel panelPrincipal = new JPanel(new BorderLayout(20, 20));
        panelPrincipal.setBackground(new Color(245, 245, 245));
        panelPrincipal.setBorder(new EmptyBorder(20, 20, 20, 20));

        JLabel lblTitulo = new JLabel("Administrar Equipo", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 32));
        lblTitulo.setForeground(new Color(50, 50, 50));

        JPanel panelCentro = new JPanel(new GridLayout(1, 2, 20, 0));
        panelCentro.setBackground(new Color(245, 245, 245));

        JPanel panelEquipo = new JPanel(new GridLayout(6, 1, 10, 10));
        panelEquipo.setBackground(Color.WHITE);
        panelEquipo.setBorder(new EmptyBorder(15, 15, 15, 15));

        panelesEquipo = new JPanel[6];

        for (int i = 0; i < 6; i++) {
            panelesEquipo[i] = crearPanelEquipo(i);
            panelEquipo.add(panelesEquipo[i]);
        }

        panelListaPokemones = new JPanel();
        panelListaPokemones.setLayout(new BoxLayout(panelListaPokemones, BoxLayout.Y_AXIS));
        panelListaPokemones.setBackground(Color.WHITE);
        panelListaPokemones.setBorder(new EmptyBorder(15, 15, 15, 15));

        JScrollPane scrollLista = new JScrollPane(panelListaPokemones);
        scrollLista.setBorder(null);
        scrollLista.getVerticalScrollBar().setUnitIncrement(12);

        panelCentro.add(panelEquipo);
        panelCentro.add(scrollLista);

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

        panelPrincipal.add(lblTitulo, BorderLayout.NORTH);
        panelPrincipal.add(panelCentro, BorderLayout.CENTER);
        panelPrincipal.add(panelInferior, BorderLayout.SOUTH);

        add(panelPrincipal);

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

        panel.putClientProperty("indice", indice);

        return panel;
    }

    // Metodo para actualizar visualmente los 6 espacios del equipo
    public void actualizarVistaEquipo() {
        ArrayList<Pokemon> equipoActual = partida.getJugador().getEquipo().getPokemones();

        for (int i = 0; i < 6; i++) {
            JPanel panel = panelesEquipo[i];
            panel.removeAll();

            if (i < equipoActual.size()) {
                Pokemon pokemon = equipoActual.get(i);
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
                panel.putClientProperty("pokemon", null);

                JLabel lblVacio = new JLabel("Vacío", SwingConstants.CENTER);
                lblVacio.setFont(new Font("Arial", Font.BOLD, 20));
                lblVacio.setForeground(new Color(150, 150, 150));

                panel.add(lblVacio, BorderLayout.CENTER);
            }

            panel.revalidate();
            panel.repaint();
        }
    }

    // Metodo para actualizar la lista de pokemones capturados por el jugador
    public void actualizarListaPokemones() {
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

        tarjeta.putClientProperty("pokemon", pokemon);

        return tarjeta;
    }

    public void restaurarSeleccionLista() {
        Component[] componentes = panelListaPokemones.getComponents();

        for (Component componente : componentes) {
            if (componente instanceof JPanel) {
                componente.setBackground(new Color(252, 252, 252));
                ((JPanel) componente).setBorder(new LineBorder(new Color(210, 210, 210), 2, true));
            }
        }
    }

    public void restaurarSeleccionEquipo() {
        for (JPanel panel : panelesEquipo) {
            panel.setBackground(new Color(252, 252, 252));
            panel.setBorder(new LineBorder(new Color(200, 200, 200), 2, true));
        }
    }

    public void setTextoPokemonSeleccionado(String texto) {
        lblPokemonSeleccionado.setText(texto);
    }

    public void mostrarDialogo(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    public Partida getPartida() {
        return partida;
    }

    public VentanaGameplay getVentanaGameplay() {
        return ventanaGameplay;
    }

    public JPanel[] getPanelesEquipo() {
        return panelesEquipo;
    }

    public JPanel getPanelListaPokemones() {
        return panelListaPokemones;
    }

    public JButton getBtnAgregar() {
        return btnAgregar;
    }

    public JButton getBtnQuitar() {
        return btnQuitar;
    }
}
