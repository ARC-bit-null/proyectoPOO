package view;

import model.Pokedex;
import model.Pokemon;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class VentanaPokedex extends JFrame {

    public VentanaPokedex(VentanaPrincipal ventanaPrincipal) {
        setTitle("Pokedex");
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                ventanaPrincipal.setVisible(true);
            }
        });

        inicializarComponentes();

        setVisible(true);
    }

    // Declaramos y inicializamos los elementos de la ventana
    private void inicializarComponentes() {
        JPanel panelPrincipal = new JPanel(new BorderLayout());
        panelPrincipal.setBackground(new Color(245, 245, 245));

        // Titulo Pincipal
        JLabel lblTitulo = new JLabel("POKEDEX", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 40));
        lblTitulo.setForeground(new Color(160, 100, 100));
        lblTitulo.setBorder(new EmptyBorder(20, 0, 20, 0));

        // Grid donde vamos a motrar a los pokemones
        JPanel panelGrid = new JPanel(new GridLayout(0, 5, 20, 20));
        panelGrid.setBackground(new Color(245, 245, 245));
        panelGrid.setBorder(new EmptyBorder(20, 20, 20, 20));

        // Traemos los pokemones y su informacion desde el Array que creamos den la clade Pokedex
        ArrayList<Pokemon> listaPokemon = Pokedex.obtenerTodosLosPokemon();

        for (Pokemon pokemon : listaPokemon) {
            panelGrid.add(crearTarjetaPokemon(pokemon));
        }

        // Barra lateral con la cual scroleamos
        JScrollPane scrollPane = new JScrollPane(panelGrid);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        panelPrincipal.add(lblTitulo, BorderLayout.NORTH);
        panelPrincipal.add(scrollPane, BorderLayout.CENTER);

        add(panelPrincipal);
    }

    // Paneles donde va a ir la informacion de los pokemones asi como sus imagenes en un principio
    private JPanel crearTarjetaPokemon(Pokemon pokemon) {
        // decalramos y inicializamos el panel
        JPanel tarjeta = new JPanel();
        tarjeta.setPreferredSize(new Dimension(160, 220));
        tarjeta.setBackground(Color.WHITE);
        tarjeta.setBorder(new LineBorder(new Color(220, 190, 190), 1, true));
        tarjeta.setLayout(new BorderLayout());

        // Label superior donde mostraremos el nuemro de id del pokemon
        JLabel lblId = new JLabel("#" + pokemon.getId());
        lblId.setFont(new Font("Arial", Font.BOLD, 16));
        lblId.setForeground(Color.WHITE);
        lblId.setOpaque(true);
        lblId.setBackground(new Color(210, 140, 120));
        lblId.setBorder(new EmptyBorder(5, 10, 5, 10));

        JPanel panelSuperior = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        panelSuperior.setOpaque(false);
        panelSuperior.add(lblId);

        // Label donde iria la imagen en caso de tenerla
        JLabel lblImagen = new JLabel();
        lblImagen.setHorizontalAlignment(SwingConstants.CENTER);

        ImageIcon icono = cargarImagenPokemon(pokemon.getNombre());
        if (icono != null) {
            Image imagenEscalada = icono.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            lblImagen.setIcon(new ImageIcon(imagenEscalada));
        } else {
            lblImagen.setText("Sin imagen");
            lblImagen.setFont(new Font("Arial", Font.PLAIN, 14));
        }

        // Labels donde  mostramos el nombre y tipo del pokemon
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

        // Inicalizamos cada elemento anteriormente declarado
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

    private ImageIcon cargarImagenPokemon(String nombrePokemon) {
        String ruta = "assets/" + nombrePokemon.toLowerCase() + ".png";
        return new ImageIcon(ruta);
    }
}