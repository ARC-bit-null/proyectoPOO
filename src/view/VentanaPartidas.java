package src.view;

import src.persistance.DataManager;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class VentanaPartidas extends JFrame {

    // Declaramos los elementos principales
    private JPanel slot1;
    private JPanel slot2;
    private JPanel slot3;

    private JFrame ventanaAnterior;

    public VentanaPartidas(VentanaPrincipal ventanaPrincipal) {
        this.ventanaAnterior = ventanaPrincipal;

        setTitle("Partidas");
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                if (ventanaAnterior != null) {
                    ventanaAnterior.setVisible(true);
                }
            }
        });

        inicializarComponentes();

        setVisible(true);
    }

    // Inicializamos los elementos de la ventana
    private void inicializarComponentes() {
        JPanel panelPrincipal = new JPanel(new BorderLayout());
        panelPrincipal.setBackground(new Color(245, 245, 245));
        panelPrincipal.setBorder(new EmptyBorder(30, 30, 30, 30));

        // Título principal de la ventana
        JLabel lblTitulo = new JLabel("Selecciona una partida", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 40));
        lblTitulo.setForeground(new Color(50, 50, 50));
        lblTitulo.setBorder(new EmptyBorder(10, 0, 40, 0));

        // Grid donde van a ir dentro los paneles de las partidas creadas y vacías
        JPanel panelPartidas = new JPanel(new GridLayout(1, 3, 30, 0));
        panelPartidas.setBackground(new Color(245, 245, 245));

        // Rellenamos los slots con los paneles de las partidas
        slot1 = crearRecuadroPartida("Partida 1", DataManager.existePartida(1) ? "Ocupada" : "Vacía");
        slot2 = crearRecuadroPartida("Partida 2", DataManager.existePartida(2) ? "Ocupada" : "Vacía");
        slot3 = crearRecuadroPartida("Partida 3", DataManager.existePartida(3) ? "Ocupada" : "Vacía");

        panelPartidas.add(slot1);
        panelPartidas.add(slot2);
        panelPartidas.add(slot3);

        panelPrincipal.add(lblTitulo, BorderLayout.NORTH);
        panelPrincipal.add(panelPartidas, BorderLayout.CENTER);

        add(panelPrincipal);
    }

    // Inicializamos los paneles que tendrá el Grid
    private JPanel crearRecuadroPartida(String titulo, String estado) {
        JPanel recuadro = new JPanel();
        recuadro.setLayout(new BoxLayout(recuadro, BoxLayout.Y_AXIS));
        recuadro.setBackground(Color.WHITE);
        recuadro.setBorder(new LineBorder(new Color(180, 180, 180), 2, true));
        recuadro.setPreferredSize(new Dimension(250, 300));
        recuadro.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JLabel lblTitulo = new JLabel(titulo, SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitulo.setForeground(new Color(40, 40, 40));
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblEstado = new JLabel(estado, SwingConstants.CENTER);
        lblEstado.setFont(new Font("Arial", Font.PLAIN, 20));
        lblEstado.setForeground(new Color(120, 120, 120));
        lblEstado.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblTexto = new JLabel("Haz clic para continuar", SwingConstants.CENTER);
        lblTexto.setFont(new Font("Arial", Font.PLAIN, 16));
        lblTexto.setForeground(new Color(150, 150, 150));
        lblTexto.setAlignmentX(Component.CENTER_ALIGNMENT);

        recuadro.add(Box.createVerticalGlue());
        recuadro.add(lblTitulo);
        recuadro.add(Box.createVerticalStrut(20));
        recuadro.add(lblEstado);
        recuadro.add(Box.createVerticalStrut(15));
        recuadro.add(lblTexto);
        recuadro.add(Box.createVerticalGlue());

        return recuadro;
    }

    public void mostrarDialogo(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    public JFrame getVentanaAnterior() {
        return ventanaAnterior;
    }

    public JPanel getSlot1() {
        return slot1;
    }

    public JPanel getSlot2() {
        return slot2;
    }

    public JPanel getSlot3() {
        return slot3;
    }
}