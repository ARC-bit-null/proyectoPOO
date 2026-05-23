package src.view;

import src.animation.AnimadorCombate;
import src.animation.PanelFlash;
import src.model.InventarioCombate;
import src.model.Pokemon;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.Image;
import java.util.ArrayList;

public class VentanaCombate extends JFrame {

    // Guardamos una referencia genérica a la ventana anterior
    private JFrame ventanaAnterior;

    // Título principal de la ventana
    private JLabel lblTitulo;

    // Labels del Jugador 1
    private JLabel lblNombreJ1;
    private JLabel lblNivelJ1;
    private JLabel lblHpJ1;
    private JLabel lblStatsJ1;
    private JProgressBar barraHpJ1;
    private JLabel lblSpriteJ1;

    // Labels del Jugador 2
    private JLabel lblNombreJ2;
    private JLabel lblNivelJ2;
    private JLabel lblHpJ2;
    private JLabel lblStatsJ2;
    private JProgressBar barraHpJ2;
    private JLabel lblSpriteJ2;

    // Paneles donde vive el sprite para poder moverlo libremente
    private JPanel panelSpriteJ1;
    private JPanel panelSpriteJ2;

    // Overlay rojo para flash de daño
    private PanelFlash flashJ1;
    private PanelFlash flashJ2;

    // Labels para mostrar los objetos restantes
    private JLabel lblObjetosJ1;
    private JLabel lblObjetosJ2;

    // Area de texto para mensajes
    private JTextArea txtMensajes;

    // Botones del Jugador 1
    private JButton btnAtacarJ1;
    private JButton btnMochilaJ1;
    private JButton btnCambioJ1;

    // Botones del Jugador 2
    private JButton btnAtacarJ2;
    private JButton btnMochilaJ2;
    private JButton btnCambioJ2;

    // Animador principal del combate
    private AnimadorCombate animadorCombate;

    public VentanaCombate(JFrame ventanaAnterior, String tituloVentana) {
        this.ventanaAnterior = ventanaAnterior;

        setTitle(tituloVentana);
        setSize(1600, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        inicializarComponentes();
        setVisible(true);

        SwingUtilities.invokeLater(() -> {
            animadorCombate.guardarPosicionesBase();
            animarEntradaInicial();
        });
    }

    // Metodo donde inicializamos todos los elementos visuales
    private void inicializarComponentes() {
        JPanel panelPrincipal = new JPanel(new BorderLayout(20, 20));
        panelPrincipal.setBackground(new Color(245, 245, 245));
        panelPrincipal.setBorder(new EmptyBorder(20, 20, 20, 20));

        lblTitulo = new JLabel("Combate", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 34));
        lblTitulo.setForeground(new Color(50, 50, 50));

        JPanel panelContenido = new JPanel(new BorderLayout(20, 20));
        panelContenido.setBackground(new Color(245, 245, 245));

        JPanel panelAccionesJ1 = crearPanelAccionesJugador(1);
        JPanel panelAccionesJ2 = crearPanelAccionesJugador(2);

        JPanel panelCentro = new JPanel(new GridLayout(3, 1, 20, 20));
        panelCentro.setBackground(new Color(245, 245, 245));

        JPanel panelPokemonJ2 = crearPanelPokemonActivo(2);

        JPanel panelMensajes = new JPanel(new BorderLayout());
        panelMensajes.setBackground(Color.WHITE);
        panelMensajes.setBorder(new LineBorder(new Color(200, 200, 200), 2, true));

        txtMensajes = new JTextArea();
        txtMensajes.setEditable(false);
        txtMensajes.setFont(new Font("Monospaced", Font.PLAIN, 18));
        txtMensajes.setLineWrap(true);
        txtMensajes.setWrapStyleWord(true);
        txtMensajes.setBackground(Color.WHITE);
        txtMensajes.setMargin(new Insets(12, 12, 12, 12));

        JScrollPane scrollMensajes = new JScrollPane(txtMensajes);
        scrollMensajes.setBorder(null);

        panelMensajes.add(scrollMensajes, BorderLayout.CENTER);

        JPanel panelPokemonJ1 = crearPanelPokemonActivo(1);

        panelCentro.add(panelPokemonJ2);
        panelCentro.add(panelMensajes);
        panelCentro.add(panelPokemonJ1);

        panelContenido.add(panelAccionesJ1, BorderLayout.WEST);
        panelContenido.add(panelCentro, BorderLayout.CENTER);
        panelContenido.add(panelAccionesJ2, BorderLayout.EAST);

        panelPrincipal.add(lblTitulo, BorderLayout.NORTH);
        panelPrincipal.add(panelContenido, BorderLayout.CENTER);

        add(panelPrincipal);

        animadorCombate = new AnimadorCombate(lblSpriteJ1, lblSpriteJ2, flashJ1, flashJ2);
    }

    // Metodo para crear el panel de acciones de cada jugador
    private JPanel crearPanelAccionesJugador(int jugador) {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(Color.WHITE);
        panel.setBorder(new LineBorder(new Color(200, 200, 200), 2, true));
        panel.setPreferredSize(new Dimension(250, 200));

        JLabel lblTituloPanel = new JLabel("Acciones Jugador " + jugador, SwingConstants.CENTER);
        lblTituloPanel.setFont(new Font("Arial", Font.BOLD, 22));
        lblTituloPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JLabel lblObjetos = new JLabel();
        lblObjetos.setFont(new Font("Arial", Font.PLAIN, 15));
        lblObjetos.setBorder(new EmptyBorder(10, 10, 10, 10));

        JPanel panelBotones = new JPanel(new GridLayout(3, 1, 10, 10));
        panelBotones.setBackground(Color.WHITE);
        panelBotones.setBorder(new EmptyBorder(10, 10, 10, 10));

        JButton btnAtacar = new JButton("Atacar");
        JButton btnMochila = new JButton("Mochila");
        JButton btnCambio = new JButton("Cambio");

        btnAtacar.setFont(new Font("Arial", Font.BOLD, 18));
        btnMochila.setFont(new Font("Arial", Font.BOLD, 18));
        btnCambio.setFont(new Font("Arial", Font.BOLD, 18));

        panelBotones.add(btnAtacar);
        panelBotones.add(btnMochila);
        panelBotones.add(btnCambio);

        panel.add(lblTituloPanel, BorderLayout.NORTH);
        panel.add(panelBotones, BorderLayout.CENTER);
        panel.add(lblObjetos, BorderLayout.SOUTH);

        if (jugador == 1) {
            btnAtacarJ1 = btnAtacar;
            btnMochilaJ1 = btnMochila;
            btnCambioJ1 = btnCambio;
            lblObjetosJ1 = lblObjetos;
        } else {
            btnAtacarJ2 = btnAtacar;
            btnMochilaJ2 = btnMochila;
            btnCambioJ2 = btnCambio;
            lblObjetosJ2 = lblObjetos;
        }

        return panel;
    }

    // Metodo para crear el panel del pokemon activo
    private JPanel crearPanelPokemonActivo(int jugador) {
        JPanel panel = new JPanel(new BorderLayout(15, 15));
        panel.setBackground(Color.WHITE);
        panel.setBorder(new LineBorder(new Color(200, 200, 200), 2, true));

        JPanel panelInfo = new JPanel();
        panelInfo.setLayout(new BoxLayout(panelInfo, BoxLayout.Y_AXIS));
        panelInfo.setBackground(Color.WHITE);
        panelInfo.setBorder(new EmptyBorder(20, 20, 20, 20));

        JLabel lblNombre = new JLabel("Nombre");
        lblNombre.setFont(new Font("Arial", Font.BOLD, 26));

        JLabel lblNivel = new JLabel("Nivel");
        lblNivel.setFont(new Font("Arial", Font.PLAIN, 18));

        JProgressBar barraHp = new JProgressBar();
        barraHp.setStringPainted(true);
        barraHp.setFont(new Font("Arial", Font.BOLD, 14));
        barraHp.setPreferredSize(new Dimension(300, 30));

        JLabel lblHp = new JLabel("HP");
        lblHp.setFont(new Font("Arial", Font.PLAIN, 18));

        JLabel lblStats = new JLabel("Daño / Velocidad");
        lblStats.setFont(new Font("Arial", Font.PLAIN, 16));

        panelInfo.add(lblNombre);
        panelInfo.add(Box.createVerticalStrut(10));
        panelInfo.add(lblNivel);
        panelInfo.add(Box.createVerticalStrut(15));
        panelInfo.add(barraHp);
        panelInfo.add(Box.createVerticalStrut(10));
        panelInfo.add(lblHp);
        panelInfo.add(Box.createVerticalStrut(10));
        panelInfo.add(lblStats);

        JPanel panelSprite = new JPanel(null);
        panelSprite.setBackground(Color.WHITE);
        panelSprite.setPreferredSize(new Dimension(260, 220));

        JLabel lblSprite = new JLabel("", SwingConstants.CENTER);
        lblSprite.setFont(new Font("Arial", Font.BOLD, 24));
        lblSprite.setForeground(new Color(130, 130, 130));
        lblSprite.setBounds(20, 0, 220, 220);
        lblSprite.setText("Sin imagen");

        PanelFlash flash = new PanelFlash(new Color(255, 60, 60, 90));
        flash.setBounds(20, 0, 220, 220);

        panelSprite.add(lblSprite);
        panelSprite.add(flash);

        // El flash debe quedar encima del sprite
        panelSprite.setComponentZOrder(lblSprite, 1);
        panelSprite.setComponentZOrder(flash, 0);

        if (jugador == 2) {
            panel.add(panelInfo, BorderLayout.WEST);
            panel.add(panelSprite, BorderLayout.CENTER);

            lblNombreJ2 = lblNombre;
            lblNivelJ2 = lblNivel;
            lblHpJ2 = lblHp;
            lblStatsJ2 = lblStats;
            barraHpJ2 = barraHp;
            lblSpriteJ2 = lblSprite;
            panelSpriteJ2 = panelSprite;
            flashJ2 = flash;
        } else {
            panel.add(panelSprite, BorderLayout.WEST);
            panel.add(panelInfo, BorderLayout.CENTER);

            lblNombreJ1 = lblNombre;
            lblNivelJ1 = lblNivel;
            lblHpJ1 = lblHp;
            lblStatsJ1 = lblStats;
            barraHpJ1 = barraHp;
            lblSpriteJ1 = lblSprite;
            panelSpriteJ1 = panelSprite;
            flashJ1 = flash;
        }

        return panel;
    }

    // Metodo para animar la entrada de ambos sprites
    private void animarEntradaInicial() {
        animadorCombate.animarEntradaJugador2(null);
        animadorCombate.animarEntradaJugador1(null);
    }

    public void animarAtaqueJugador1(Runnable alFinal) {
        animadorCombate.animarAtaqueJugador1(alFinal);
    }

    public void animarAtaqueJugador2(Runnable alFinal) {
        animadorCombate.animarAtaqueJugador2(alFinal);
    }

    public void animarDanioJugador1(Runnable alFinal) {
        animadorCombate.animarDanioJugador1(alFinal);
    }

    public void animarDanioJugador2(Runnable alFinal) {
        animadorCombate.animarDanioJugador2(alFinal);
    }

    public void animarDerrotaJugador1(Runnable alFinal) {
        animadorCombate.animarDerrotaJugador1(alFinal);
    }

    public void animarDerrotaJugador2(Runnable alFinal) {
        animadorCombate.animarDerrotaJugador2(alFinal);
    }

    public void animarCambioJugador1(Runnable alFinal) {
        animadorCombate.animarCambioJugador1(alFinal);
    }

    public void animarCambioJugador2(Runnable alFinal) {
        animadorCombate.animarCambioJugador2(alFinal);
    }

    // Metodo para actualizar el título principal
    public void setTituloCombate(String titulo) {
        lblTitulo.setText(titulo);
    }

    // Metodo para actualizar el panel del Jugador 1
    public void actualizarPanelJugador1(Pokemon pokemon) {
        if (pokemon == null) return;

        lblNombreJ1.setText(pokemon.getNombre());
        lblNivelJ1.setText("Nivel: " + pokemon.getNivel());
        lblHpJ1.setText("HP: " + pokemon.getHp() + "/" + pokemon.getHpMax());
        lblStatsJ1.setText("Daño: " + pokemon.getDanoEfectivo() + " | Velocidad: " + pokemon.getVelocidadEfectiva());

        barraHpJ1.setMaximum(pokemon.getHpMax());
        barraHpJ1.setValue(pokemon.getHp());
        barraHpJ1.setString(pokemon.getHp() + "/" + pokemon.getHpMax());

        actualizarSprite(lblSpriteJ1, pokemon.getImagenTrasera(), 220, 220);
        lblSpriteJ1.setVisible(true);
        flashJ1.setVisible(false);
        animadorCombate.restaurarPosicionesBase();
    }

    // Metodo para actualizar el panel del Jugador 2
    public void actualizarPanelJugador2(Pokemon pokemon) {
        if (pokemon == null) return;

        lblNombreJ2.setText(pokemon.getNombre());
        lblNivelJ2.setText("Nivel: " + pokemon.getNivel());
        lblHpJ2.setText("HP: " + pokemon.getHp() + "/" + pokemon.getHpMax());
        lblStatsJ2.setText("Daño: " + pokemon.getDanoEfectivo() + " | Velocidad: " + pokemon.getVelocidadEfectiva());

        barraHpJ2.setMaximum(pokemon.getHpMax());
        barraHpJ2.setValue(pokemon.getHp());
        barraHpJ2.setString(pokemon.getHp() + "/" + pokemon.getHpMax());

        actualizarSprite(lblSpriteJ2, pokemon.getImagenFrontal(), 220, 220);
        lblSpriteJ2.setVisible(true);
        flashJ2.setVisible(false);
        animadorCombate.restaurarPosicionesBase();
    }

    public void actualizarObjetosJugador1(InventarioCombate inventario) {
        if (inventario == null) return;

        lblObjetosJ1.setText(
                "<html>Spray: " + inventario.getSprayCurativo() +
                        " | Revivir: " + inventario.getRevivir() +
                        "<br>Banda: " + inventario.getBandaEspecial() +
                        " | X Speed: " + inventario.getXSpeed() + "</html>"
        );
    }

    public void actualizarObjetosJugador2(InventarioCombate inventario) {
        if (inventario == null) return;

        lblObjetosJ2.setText(
                "<html>Spray: " + inventario.getSprayCurativo() +
                        " | Revivir: " + inventario.getRevivir() +
                        "<br>Banda: " + inventario.getBandaEspecial() +
                        " | X Speed: " + inventario.getXSpeed() + "</html>"
        );
    }

    public void setBotonesJugador1Habilitados(boolean habilitado) {
        btnAtacarJ1.setEnabled(habilitado);
        btnMochilaJ1.setEnabled(habilitado);
        btnCambioJ1.setEnabled(habilitado);
    }

    public void setBotonesJugador2Habilitados(boolean habilitado) {
        btnAtacarJ2.setEnabled(habilitado);
        btnMochilaJ2.setEnabled(habilitado);
        btnCambioJ2.setEnabled(habilitado);
    }

    public void setModoPveVisual(boolean esPve) {
        btnAtacarJ2.setVisible(!esPve);
        btnMochilaJ2.setVisible(!esPve);
        btnCambioJ2.setVisible(!esPve);
        lblObjetosJ2.setVisible(!esPve);
    }

    public void agregarMensaje(String mensaje) {
        txtMensajes.append(mensaje + "\n");
        txtMensajes.setCaretPosition(txtMensajes.getDocument().getLength());
    }

    public void limpiarMensajes() {
        txtMensajes.setText("");
    }

    public int seleccionarPokemonVivo(ArrayList<Pokemon> equipo, int indiceActual, String mensaje) {
        ArrayList<String> opciones = new ArrayList<>();
        ArrayList<Integer> indices = new ArrayList<>();

        for (int i = 0; i < equipo.size(); i++) {
            Pokemon pokemon = equipo.get(i);

            if (!pokemon.estaDerrotado() && i != indiceActual) {
                opciones.add(pokemon.getNombre() + " - HP: " + pokemon.getHp() + "/" + pokemon.getHpMax());
                indices.add(i);
            }
        }

        if (opciones.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay otros Pokémon disponibles para cambiar.");
            return -1;
        }

        String seleccion = (String) JOptionPane.showInputDialog(
                this,
                mensaje,
                "Cambio de Pokémon",
                JOptionPane.PLAIN_MESSAGE,
                null,
                opciones.toArray(),
                opciones.get(0)
        );

        if (seleccion == null) {
            return -1;
        }

        for (int i = 0; i < opciones.size(); i++) {
            if (opciones.get(i).equals(seleccion)) {
                return indices.get(i);
            }
        }

        return -1;
    }

    public int seleccionarPokemonDerrotado(ArrayList<Pokemon> equipo, String mensaje) {
        ArrayList<String> opciones = new ArrayList<>();
        ArrayList<Integer> indices = new ArrayList<>();

        for (int i = 0; i < equipo.size(); i++) {
            Pokemon pokemon = equipo.get(i);

            if (pokemon.estaDerrotado()) {
                opciones.add(pokemon.getNombre() + " - Derrotado");
                indices.add(i);
            }
        }

        if (opciones.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay Pokémon derrotados para revivir.");
            return -1;
        }

        String seleccion = (String) JOptionPane.showInputDialog(
                this,
                mensaje,
                "Revivir Pokémon",
                JOptionPane.PLAIN_MESSAGE,
                null,
                opciones.toArray(),
                opciones.get(0)
        );

        if (seleccion == null) {
            return -1;
        }

        for (int i = 0; i < opciones.size(); i++) {
            if (opciones.get(i).equals(seleccion)) {
                return indices.get(i);
            }
        }

        return -1;
    }

    public String seleccionarObjeto(String titulo) {
        String[] opciones = {"Spray curativo", "Revivir", "Banda especial", "X speed"};

        return (String) JOptionPane.showInputDialog(
                this,
                "Selecciona un objeto:",
                titulo,
                JOptionPane.PLAIN_MESSAGE,
                null,
                opciones,
                opciones[0]
        );
    }

    public void mostrarDialogo(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    // Metodo para actualizar el sprite del pokemon
    private void actualizarSprite(JLabel label, String rutaImagen, int ancho, int alto) {
        if (rutaImagen == null || rutaImagen.isEmpty()) {
            label.setIcon(null);
            label.setText("Sin imagen");
            return;
        }

        ImageIcon iconoOriginal = new ImageIcon(rutaImagen);

        if (iconoOriginal.getIconWidth() <= 0) {
            label.setIcon(null);
            label.setText("Imagen no encontrada");
            return;
        }

        Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
        label.setIcon(new ImageIcon(imagenEscalada));
        label.setText("");
    }

    public JFrame getVentanaAnterior() {
        return ventanaAnterior;
    }

    public JButton getBtnAtacarJ1() {
        return btnAtacarJ1;
    }

    public JButton getBtnMochilaJ1() {
        return btnMochilaJ1;
    }

    public JButton getBtnCambioJ1() {
        return btnCambioJ1;
    }

    public JButton getBtnAtacarJ2() {
        return btnAtacarJ2;
    }

    public JButton getBtnMochilaJ2() {
        return btnMochilaJ2;
    }

    public JButton getBtnCambioJ2() {
        return btnCambioJ2;
    }
}