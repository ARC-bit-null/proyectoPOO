package src.view;

import src.model.Partida;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.CompoundBorder;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class VentanaGameplay extends JFrame {

    // Variable donde guardaremos la partida cargada o recién creada
    private Partida partida;

    // Declaramos los paneles principales de opciones
    private JPanel panelEquipo;
    private JPanel panelPve;
    private JPanel panelPvp;

    public VentanaGameplay(VentanaPartidas ventanaPartidas, Partida partida) {
        // Guardamos la partida para usarla después en la ventana de gameplay
        this.partida = partida;

        setTitle("Menu Principal");
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                ventanaPartidas.setVisible(true);
            }
        });

        inicializarComponentes();

        setVisible(true);
    }

    // Metodo donde inicializamos todos los elementos visuales de la ventana
    private void inicializarComponentes() {
        JPanel panelPrincipal = new JPanel(new BorderLayout());
        panelPrincipal.setBackground(new Color(245, 245, 245));
        panelPrincipal.setBorder(new EmptyBorder(30, 30, 30, 30));

        JLabel lblTitulo = new JLabel("Menú Principal", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 34));
        lblTitulo.setForeground(new Color(50, 50, 50));
        lblTitulo.setBorder(new EmptyBorder(10, 0, 30, 0));

        JPanel panelInfo = new JPanel(new GridLayout(2, 1, 10, 10));
        panelInfo.setBackground(Color.WHITE);
        panelInfo.setBorder(new CompoundBorder(
                new LineBorder(new Color(210, 210, 210), 2, true),
                new EmptyBorder(20, 20, 20, 20)
        ));

        JLabel lblJugador = new JLabel("Jugador: " + partida.getJugador().getNombre(), SwingConstants.CENTER);
        lblJugador.setFont(new Font("Arial", Font.BOLD, 22));
        lblJugador.setForeground(new Color(40, 40, 40));

        JLabel lblPokemonInicial = new JLabel("Pokémon inicial: " + partida.getJugador().getPokemonInicial().getNombre(), SwingConstants.CENTER);
        lblPokemonInicial.setFont(new Font("Arial", Font.PLAIN, 20));
        lblPokemonInicial.setForeground(new Color(100, 100, 100));

        panelInfo.add(lblJugador);
        panelInfo.add(lblPokemonInicial);

        JPanel panelOpciones = new JPanel(new GridLayout(1, 3, 25, 0));
        panelOpciones.setBackground(new Color(245, 245, 245));
        panelOpciones.setBorder(new EmptyBorder(40, 0, 0, 0));

        panelEquipo = crearPanelOpcion("Equipo", "Administra los Pokémon de tu equipo");
        panelPve = crearPanelOpcion("PvE (Historia)", "Juega contra enemigos del juego");
        panelPvp = crearPanelOpcion("PvP", "Combate contra otro jugador");

        panelOpciones.add(panelEquipo);
        panelOpciones.add(panelPve);
        panelOpciones.add(panelPvp);

        panelPrincipal.add(lblTitulo, BorderLayout.NORTH);
        panelPrincipal.add(panelInfo, BorderLayout.CENTER);
        panelPrincipal.add(panelOpciones, BorderLayout.SOUTH);

        add(panelPrincipal);
    }

    // Metodo para crear cada panel de opción del menú principal
    private JPanel crearPanelOpcion(String titulo, String descripcion) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.WHITE);
        panel.setBorder(new LineBorder(new Color(200, 200, 200), 2, true));
        panel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panel.setPreferredSize(new Dimension(250, 250));

        JLabel lblTitulo = new JLabel(titulo, SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitulo.setForeground(new Color(40, 40, 40));
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblDescripcion = new JLabel(
                "<html><div style='text-align: center;'>" + descripcion + "</div></html>",
                SwingConstants.CENTER
        );
        lblDescripcion.setFont(new Font("Arial", Font.PLAIN, 17));
        lblDescripcion.setForeground(new Color(110, 110, 110));
        lblDescripcion.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(Box.createVerticalGlue());
        panel.add(lblTitulo);
        panel.add(Box.createVerticalStrut(20));
        panel.add(lblDescripcion);
        panel.add(Box.createVerticalGlue());

        return panel;
    }

    // Metodo para cambiar el estilo visual de un panel cuando el mouse entra sobre él
    public void resaltarPanel(JPanel panel) {
        panel.setBorder(new LineBorder(new Color(100, 170, 255), 3, true));
        panel.setBackground(new Color(235, 245, 255));
    }

    // Metodo para restaurar el estilo original de un panel cuando el mouse sale de él
    public void restaurarPanel(JPanel panel) {
        panel.setBorder(new LineBorder(new Color(200, 200, 200), 2, true));
        panel.setBackground(Color.WHITE);
    }

    public Partida getPartida() {
        return partida;
    }

    public JPanel getPanelEquipo() {
        return panelEquipo;
    }

    public JPanel getPanelPve() {
        return panelPve;
    }

    public JPanel getPanelPvp() {
        return panelPvp;
    }
}