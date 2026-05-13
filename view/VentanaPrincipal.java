package view;
import javax.swing.*;
import java.awt.*;

public class VentanaPrincipal extends JFrame {

    private JButton btnPeleas;
    private JButton btnPokedex;

    // Constructor que carga todos los elementos de la ventana
    public VentanaPrincipal() {
        configurarVentana();
        inicializarComponentes();
        inicializarEventos();
        setVisible(true);
    }

    // Creamos y delimitamos la ventana principal
    private void configurarVentana() {
        setTitle("Pokémon");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    // Declaramos y inicializamos los elementos de la ventana
    private void inicializarComponentes() {
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BorderLayout());
        panelPrincipal.setBackground(new Color(245, 245, 245));

        // Título Principal
        JLabel lblTitulo = new JLabel("Pokémon", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 108));
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(30, 0, 20, 0));

        // Panel central para botones
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridBagLayout());
        panelBotones.setBackground(new Color(245, 245, 245));

        JPanel contenedorBotones = new JPanel();
        contenedorBotones.setLayout(new GridLayout(2, 1, 0, 20));
        contenedorBotones.setBackground(new Color(245, 245, 245));

        // Botones que iran dentro del panel central
        btnPeleas = new JButton("Jugar");
        btnPokedex = new JButton("Pokedex");

        // Dimenciones basicas de los botones
        Dimension tamañoBoton = new Dimension(220, 50);
        btnPeleas.setPreferredSize(tamañoBoton);
        btnPokedex.setPreferredSize(tamañoBoton);

        // Tipografia del texto de los botones
        btnPeleas.setFont(new Font("Arial", Font.PLAIN, 20));
        btnPokedex.setFont(new Font("Arial", Font.PLAIN, 20));

        btnPeleas.setFocusPainted(false);
        btnPokedex.setFocusPainted(false);

        // Los agregamos al Panel
        contenedorBotones.add(btnPeleas);
        contenedorBotones.add(btnPokedex);

        panelBotones.add(contenedorBotones);

        panelPrincipal.add(lblTitulo, BorderLayout.NORTH);
        panelPrincipal.add(panelBotones, BorderLayout.CENTER);

        add(panelPrincipal);
    }

    // Metodo para inicializar los eventos de los botones, para abrir las ventanas secundarias
    private void inicializarEventos() {
        btnPeleas.addActionListener(e -> {
            setVisible(false);
            new VentanaPartidas(this);
        });

        btnPokedex.addActionListener(e -> {
            setVisible(false);
            new VentanaPokedex(this);
        });
    }
}