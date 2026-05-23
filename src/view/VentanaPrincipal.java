package src.view;

import javax.swing.*;
import java.awt.*;

public class VentanaPrincipal extends JFrame {

    private JButton btnPeleas;
    private JButton btnPokedex;

    public VentanaPrincipal() {
        configurarVentana();
        inicializarComponentes();
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
        JPanel panelPrincipal = new JPanel() {
            private final Image imagenFondo = new ImageIcon("resources/layout/fondo 1.jpg.jpeg").getImage();

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                if (imagenFondo != null) {
                    g.drawImage(imagenFondo, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };

        panelPrincipal.setLayout(new BorderLayout());
        panelPrincipal.setOpaque(false);

        // Título principal con imagen png
        JLabel lblTitulo = new JLabel("", SwingConstants.CENTER);
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(15, 0, 5, 0));
        cargarImagenTitulo(lblTitulo);

        // Panel central para botones
        JPanel panelBotones = new JPanel(new GridBagLayout());
        panelBotones.setOpaque(false);

        JPanel contenedorBotones = new JPanel(new GridLayout(2, 1, 0, 20));
        contenedorBotones.setOpaque(false);

        // Botones que irán dentro del panel central
        btnPeleas = new JButton("Jugar");
        btnPokedex = new JButton("Pokedex");

        Dimension tamañoBoton = new Dimension(220, 50);
        btnPeleas.setPreferredSize(tamañoBoton);
        btnPokedex.setPreferredSize(tamañoBoton);

        btnPeleas.setFont(new Font("Arial", Font.BOLD, 20));
        btnPokedex.setFont(new Font("Arial", Font.BOLD, 20));

        estilizarBotonAmarillo(btnPeleas);
        estilizarBotonAmarillo(btnPokedex);

        contenedorBotones.add(btnPeleas);
        contenedorBotones.add(btnPokedex);

        panelBotones.add(contenedorBotones);

        panelPrincipal.add(lblTitulo, BorderLayout.NORTH);
        panelPrincipal.add(panelBotones, BorderLayout.CENTER);

        add(panelPrincipal);
    }

    // Metodo para cargar la imagen del titulo
    private void cargarImagenTitulo(JLabel lblTitulo) {
        ImageIcon iconoOriginal = new ImageIcon("resources/layout/InicioPokemon.png");

        if (iconoOriginal.getIconWidth() > 0) {
            int anchoOriginal = iconoOriginal.getIconWidth();
            int altoOriginal = iconoOriginal.getIconHeight();

            int nuevoAncho = 580;
            int nuevoAlto = (altoOriginal * nuevoAncho) / anchoOriginal;

            Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(nuevoAncho, nuevoAlto, Image.SCALE_SMOOTH);
            lblTitulo.setIcon(new ImageIcon(imagenEscalada));
        } else {
            lblTitulo.setText("Pokémon");
            lblTitulo.setFont(new Font("Arial", Font.BOLD, 72));
        }
    }

    // Metodo para dar estilo amarillo a los botones
    private void estilizarBotonAmarillo(JButton boton) {
        boton.setBackground(new Color(255, 204, 0));
        boton.setForeground(Color.BLACK);
        boton.setFocusPainted(false);
        boton.setBorderPainted(false);
        boton.setOpaque(true);
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    public JButton getBtnPeleas() {
        return btnPeleas;
    }

    public JButton getBtnPokedex() {
        return btnPokedex;
    }
}