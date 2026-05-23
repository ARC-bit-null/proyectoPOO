package src.view;

import src.model.ModoPve;
import src.model.Partida;
import src.model.RivalPve;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class VentanaPve extends JFrame {

    private Partida partida;
    private JFrame ventanaAnterior;

    private JButton btnFacil;
    private JButton btnNormal;
    private JButton btnDificil;
    private JButton btnComenzar;

    private JLabel lblModoSeleccionado;
    private JLabel lblRivalActual;
    private JLabel lblNivelRival;
    private JLabel lblCantidadPokemon;
    private JLabel lblXpRecompensa;

    private JTextArea txtEquipoRival;

    private ModoPve modoSeleccionado;

    public VentanaPve(JFrame ventanaAnterior, Partida partida) {
        this.ventanaAnterior = ventanaAnterior;
        this.partida = partida;

        setTitle("Modo PvE");
        setSize(1100, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                if (VentanaPve.this.ventanaAnterior != null) {
                    VentanaPve.this.ventanaAnterior.setVisible(true);
                }
            }
        });

        inicializarComponentes();
        setVisible(true);
    }

    // Metodo donde armamos todos los componentes de la ventana
    private void inicializarComponentes() {
        JPanel panelPrincipal = new JPanel(new BorderLayout(20, 20));
        panelPrincipal.setBackground(new Color(245, 245, 245));
        panelPrincipal.setBorder(new EmptyBorder(25, 25, 25, 25));

        JLabel lblTitulo = new JLabel("Modo PvE", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 34));
        lblTitulo.setForeground(new Color(50, 50, 50));

        JPanel panelCentro = new JPanel(new GridLayout(1, 2, 20, 20));
        panelCentro.setBackground(new Color(245, 245, 245));

        JPanel panelModos = new JPanel();
        panelModos.setLayout(new BoxLayout(panelModos, BoxLayout.Y_AXIS));
        panelModos.setBackground(Color.WHITE);
        panelModos.setBorder(new LineBorder(new Color(200, 200, 200), 2, true));
        panelModos.setPreferredSize(new Dimension(350, 500));

        JLabel lblModos = new JLabel("Selecciona un modo");
        lblModos.setFont(new Font("Arial", Font.BOLD, 26));
        lblModos.setAlignmentX(Component.CENTER_ALIGNMENT);

        btnFacil = new JButton("Fácil");
        btnNormal = new JButton("Normal");
        btnDificil = new JButton("Difícil");

        btnFacil.setFont(new Font("Arial", Font.BOLD, 22));
        btnNormal.setFont(new Font("Arial", Font.BOLD, 22));
        btnDificil.setFont(new Font("Arial", Font.BOLD, 22));

        btnFacil.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnNormal.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnDificil.setAlignmentX(Component.CENTER_ALIGNMENT);

        panelModos.add(Box.createVerticalStrut(40));
        panelModos.add(lblModos);
        panelModos.add(Box.createVerticalStrut(40));
        panelModos.add(btnFacil);
        panelModos.add(Box.createVerticalStrut(25));
        panelModos.add(btnNormal);
        panelModos.add(Box.createVerticalStrut(25));
        panelModos.add(btnDificil);

        JPanel panelInfo = new JPanel(new BorderLayout(15, 15));
        panelInfo.setBackground(Color.WHITE);
        panelInfo.setBorder(new LineBorder(new Color(200, 200, 200), 2, true));

        JPanel panelDatos = new JPanel();
        panelDatos.setLayout(new BoxLayout(panelDatos, BoxLayout.Y_AXIS));
        panelDatos.setBackground(Color.WHITE);
        panelDatos.setBorder(new EmptyBorder(20, 20, 20, 20));

        lblModoSeleccionado = new JLabel("Modo: Ninguno");
        lblRivalActual = new JLabel("Rival actual: -");
        lblNivelRival = new JLabel("Nivel: -");
        lblCantidadPokemon = new JLabel("Cantidad de Pokémon: -");
        lblXpRecompensa = new JLabel("XP recompensa: -");

        lblModoSeleccionado.setFont(new Font("Arial", Font.BOLD, 24));
        lblRivalActual.setFont(new Font("Arial", Font.PLAIN, 20));
        lblNivelRival.setFont(new Font("Arial", Font.PLAIN, 20));
        lblCantidadPokemon.setFont(new Font("Arial", Font.PLAIN, 20));
        lblXpRecompensa.setFont(new Font("Arial", Font.PLAIN, 20));

        panelDatos.add(lblModoSeleccionado);
        panelDatos.add(Box.createVerticalStrut(20));
        panelDatos.add(lblRivalActual);
        panelDatos.add(Box.createVerticalStrut(10));
        panelDatos.add(lblNivelRival);
        panelDatos.add(Box.createVerticalStrut(10));
        panelDatos.add(lblCantidadPokemon);
        panelDatos.add(Box.createVerticalStrut(10));
        panelDatos.add(lblXpRecompensa);

        txtEquipoRival = new JTextArea();
        txtEquipoRival.setEditable(false);
        txtEquipoRival.setFont(new Font("Monospaced", Font.PLAIN, 18));
        txtEquipoRival.setLineWrap(true);
        txtEquipoRival.setWrapStyleWord(true);
        txtEquipoRival.setBackground(new Color(250, 250, 250));
        txtEquipoRival.setMargin(new Insets(12, 12, 12, 12));
        txtEquipoRival.setText("Selecciona un modo para ver al primer rival.");

        JScrollPane scrollEquipo = new JScrollPane(txtEquipoRival);
        scrollEquipo.setBorder(new TitledBorderSimple("Equipo del rival"));

        panelInfo.add(panelDatos, BorderLayout.NORTH);
        panelInfo.add(scrollEquipo, BorderLayout.CENTER);

        panelCentro.add(panelModos);
        panelCentro.add(panelInfo);

        btnComenzar = new JButton("Comenzar secuencia");
        btnComenzar.setFont(new Font("Arial", Font.BOLD, 22));
        btnComenzar.setEnabled(false);

        JPanel panelSur = new JPanel();
        panelSur.setBackground(new Color(245, 245, 245));
        panelSur.add(btnComenzar);

        panelPrincipal.add(lblTitulo, BorderLayout.NORTH);
        panelPrincipal.add(panelCentro, BorderLayout.CENTER);
        panelPrincipal.add(panelSur, BorderLayout.SOUTH);

        add(panelPrincipal);
    }

    // Metodo para mostrar la informacion del rival actual
    public void mostrarRivalActual(ModoPve modo, RivalPve rival, ArrayList<String> nombresPokemon) {
        if (modo == null || rival == null) {
            return;
        }

        this.modoSeleccionado = modo;

        lblModoSeleccionado.setText("Modo: " + capitalizarModo(modo));
        lblRivalActual.setText("Rival actual: Rival " + rival.getNumeroRival());
        lblNivelRival.setText("Nivel: " + rival.getNivel());
        lblCantidadPokemon.setText("Cantidad de Pokémon: " + rival.getCantidadPokemon());
        lblXpRecompensa.setText("XP recompensa: " + rival.getExperienciaOtorgada());

        StringBuilder builder = new StringBuilder();
        builder.append("Equipo del Rival ").append(rival.getNumeroRival()).append(":\n\n");

        for (String nombrePokemon : nombresPokemon) {
            builder.append("- ").append(nombrePokemon).append("\n");
        }

        txtEquipoRival.setText(builder.toString());
        btnComenzar.setEnabled(true);
    }

    private String capitalizarModo(ModoPve modo) {
        if (modo == ModoPve.FACIL) return "Fácil";
        if (modo == ModoPve.NORMAL) return "Normal";
        return "Difícil";
    }

    public void mostrarDialogo(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    public Partida getPartida() {
        return partida;
    }

    public JFrame getVentanaAnterior() {
        return ventanaAnterior;
    }

    public JButton getBtnFacil() {
        return btnFacil;
    }

    public JButton getBtnNormal() {
        return btnNormal;
    }

    public JButton getBtnDificil() {
        return btnDificil;
    }

    public JButton getBtnComenzar() {
        return btnComenzar;
    }

    public ModoPve getModoSeleccionado() {
        return modoSeleccionado;
    }

    // Borde simple para no meter otra clase aparte
    private static class TitledBorderSimple extends LineBorder {
        public TitledBorderSimple(String titulo) {
            super(new Color(200, 200, 200), 1, true);
        }
    }
}