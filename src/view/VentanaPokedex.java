package src.view;

import src.model.Pokedex;
import src.model.Pokemon;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;

public class VentanaPokedex extends JFrame {

    private JFrame ventanaAnterior;

    public VentanaPokedex(JFrame ventanaAnterior) {
        this.ventanaAnterior = ventanaAnterior;

        setTitle("Pokedex");
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        inicializarComponentes();

        setVisible(true);
    }

    // Declaramos y inicializamos los elementos de la ventana
    private void inicializarComponentes() {
        JPanel panelPrincipal = new JPanel(new BorderLayout());
        panelPrincipal.setBackground(new Color(245, 245, 245));

        JLabel lblTitulo = new JLabel("POKEDEX", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 40));
        lblTitulo.setForeground(new Color(160, 100, 100));
        lblTitulo.setBorder(new EmptyBorder(20, 0, 20, 0));

        JPanel panelGrid = new JPanel(new GridLayout(0, 5, 20, 20));
        panelGrid.setBackground(new Color(245, 245, 245));
        panelGrid.setBorder(new EmptyBorder(20, 20, 20, 20));

        ArrayList<Pokemon> listaPokemon = Pokedex.obtenerTodosLosPokemon();

        for (Pokemon pokemon : listaPokemon) {
            panelGrid.add(crearTarjetaPokemon(pokemon));
        }

        JScrollPane scrollPane = new JScrollPane(panelGrid);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        panelPrincipal.add(lblTitulo, BorderLayout.NORTH);
        panelPrincipal.add(scrollPane, BorderLayout.CENTER);

        add(panelPrincipal);
    }

    // Paneles donde va a ir la informacion de los pokemones asi como sus imagenes
    private JPanel crearTarjetaPokemon(Pokemon pokemon) {
        JPanel tarjeta = new JPanel();
        tarjeta.setPreferredSize(new Dimension(160, 220));
        tarjeta.setBackground(Color.WHITE);
        tarjeta.setBorder(new LineBorder(new Color(220, 190, 190), 1, true));
        tarjeta.setLayout(new BorderLayout());

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
            Image imagenEscalada = icono.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            lblImagen.setIcon(new ImageIcon(imagenEscalada));
        } else {
            lblImagen.setText("Sin imagen");
            lblImagen.setFont(new Font("Arial", Font.PLAIN, 14));
        }

        JLabel lblNombre = new JLabel(pokemon.getNombre(), SwingConstants.CENTER);
        lblNombre.setFont(new Font("Arial", Font.BOLD, 22));
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

        return tarjeta;
    }

    // Metodo para cargar la imagen desde la ruta guardada en el pokemon
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

    public JFrame getVentanaAnterior() {
        return ventanaAnterior;
    }
}