package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import logic.Pokemon;

/*
 * Clase VentanaBatalla
 * * Utiliza la librería Swing para la visualización gráfica y AWT para
 * la gestión de eventos y diseño de contenedores.
*/
public class VentanaBatalla extends JFrame {

    // Componentes de la interfaz
    private JLabel lblPokemonJugador, lblPokemonRival;
    private JLabel lblHpJugador, lblHpRival;
    private JButton btnAtacar, btnHabilidad, btnHuir;
    private JTextArea txtLog; // Para mostrar qué pasa en la batalla

    // Referencias a la lógica (clases que definimos antes)
    private Pokemon miPokemon;
    private Pokemon pokemonRival;

    public VentanaBatalla(Pokemon jugador, Pokemon rival) {
        this.miPokemon = jugador;
        this.pokemonRival = rival;

        // Configuración básica de la ventana
        setTitle("Proyecto 2 - Batalla Pokemon");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout()); // Usamos BorderLayout para organizar las zonas

        // 1. Panel Superior: Información y Vida
        JPanel panelInfo = new JPanel(new GridLayout(1, 2));
        
        // Info Jugador
        JPanel infoJugador = new JPanel();
        lblHpJugador = new JLabel(miPokemon.getNombre() + " HP: " + miPokemon.getHp());
        infoJugador.add(lblHpJugador);
        
        // Info Rival
        JPanel infoRival = new JPanel();
        lblHpRival = new JLabel(pokemonRival.getNombre() + " HP: " + pokemonRival.getHp());
        infoRival.add(lblHpRival);

        panelInfo.add(infoJugador);
        panelInfo.add(infoRival);
        add(panelInfo, BorderLayout.NORTH);

        // 2. Panel Central: Las Imágenes (Aquí está el truco de las imágenes)
        JPanel panelImagenes = new JPanel(new GridLayout(1, 2));
        
        // Cargar imagen del Jugador
        ImageIcon iconJugador = cargarImagen("res/" + miPokemon.getNombre().toLowerCase() + ".png");
        lblPokemonJugador = new JLabel(iconJugador);
        panelImagenes.add(lblPokemonJugador);

        // Cargar imagen del Rival
        ImageIcon iconRival = cargarImagen("res/" + pokemonRival.getNombre().toLowerCase() + ".png");
        lblPokemonRival = new JLabel(iconRival);
        panelImagenes.add(lblPokemonRival);

        add(panelImagenes, BorderLayout.CENTER);

        // 3. Panel Inferior: Controles y Log
        JPanel panelInferior = new JPanel(new BorderLayout());

        // Log de batalla (área de texto)
        txtLog = new JTextArea(5, 30);
        txtLog.setEditable(false); // Que el usuario no pueda escribir
        JScrollPane scrollLog = new JScrollPane(txtLog); // Añadir scrollbar
        panelInferior.add(scrollLog, BorderLayout.CENTER);

        // Botones de acción
        JPanel panelBotones = new JPanel(new GridLayout(3, 1)); // 3 botones, uno sobre otro
        btnAtacar = new JButton("Atacar");
        btnHabilidad = new JButton("Habilidad");
        btnHuir = new JButton("Huir");
        
        panelBotones.add(btnAtacar);
        panelBotones.add(btnHabilidad);
        panelBotones.add(btnHuir);
        panelInferior.add(panelBotones, BorderLayout.EAST);

        add(panelInferior, BorderLayout.SOUTH);

        // --- Lógica de los botones (ActionListeners) ---
        btnAtacar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 1. Jugador Ataca
                miPokemon.atacar(pokemonRival);
                actualizarInterfaz();
                
                // 2. Turno de la PC (Si el rival sigue vivo)
                if (pokemonRival.estaVivo()) {
                    // Aquí llamamos a la lógica de la PC que definimos antes
                    // Por simplicidad, que ataque siempre
                    pokemonRival.atacar(miPokemon);
                    actualizarInterfaz();
                } else {
                    txtLog.append("\n¡" + pokemonRival.getNombre() + " se debilitó! ¡Has ganado!");
                    btnAtacar.setEnabled(false); // Desactivar botón
                }

                // 3. Verificar si el jugador perdió
                if (!miPokemon.estaVivo()) {
                    txtLog.append("\n¡" + miPokemon.getNombre() + " se debilitó! Has perdido...");
                    btnAtacar.setEnabled(false);
                }
            }
        });

        // Mostrar la ventana al final
        setVisible(true);
    }

    // --- Métodos Auxiliares ---

    // Este método lee la imagen y la ajusta al tamaño correcto
    private ImageIcon cargarImagen(String path) {
        try {
            ImageIcon iconOriginal = new ImageIcon(path);
            // Escalamos la imagen (p.ej. a 200x200 píxeles) para que no deforme la ventana
            Image imgEscalada = iconOriginal.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
            return new ImageIcon(imgEscalada);
        } catch (Exception e) {
            System.err.println("No se pudo cargar la imagen en: " + path);
            // Devolver un icono por defecto o nulo si falla
            return null;
        }
    }

    // Actualiza los textos de HP y log después de cada turno
    private void actualizarInterfaz() {
        lblHpJugador.setText(miPokemon.getNombre() + " HP: " + miPokemon.getHp());
        lblHpRival.setText(pokemonRival.getNombre() + " HP: " + pokemonRival.getHp());
        
        // Esto asume que actualizaste la lógica de atacar() para que devuelva un String con el log
        // Por ahora, solo añadiremos una línea genérica al log.
        txtLog.append("\nSe ha intercambiado daño...");
    }
}
