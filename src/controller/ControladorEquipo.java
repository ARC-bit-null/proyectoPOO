package src.controller;

import src.model.Partida;
import src.model.Pokemon;
import src.persistance.DataManager;
import src.view.VentanaEquipo;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class ControladorEquipo {

    private VentanaEquipo ventana;

    private Pokemon pokemonSeleccionadoLista;
    private Pokemon pokemonSeleccionadoEquipo;

    public ControladorEquipo(VentanaEquipo ventana) {
        this.ventana = ventana;
        inicializarEventos();
        registrarEventosListaPokemones();
    }

    private void inicializarEventos() {
        ventana.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                if (ventana.getVentanaGameplay() != null) {
                    ventana.getVentanaGameplay().setVisible(true);
                }
            }
        });

        for (JPanel panelEquipo : ventana.getPanelesEquipo()) {
            panelEquipo.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    seleccionarPokemonEquipo(panelEquipo);
                }
            });
        }

        ventana.getBtnAgregar().addActionListener(e -> agregarPokemonAlEquipo());
        ventana.getBtnQuitar().addActionListener(e -> quitarPokemonDelEquipo());
    }

    private void registrarEventosListaPokemones() {
        Component[] componentes = ventana.getPanelListaPokemones().getComponents();

        for (Component componente : componentes) {
            if (componente instanceof JPanel tarjeta) {
                Object pokemon = tarjeta.getClientProperty("pokemon");

                if (pokemon instanceof Pokemon) {
                    tarjeta.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            seleccionarPokemonLista(tarjeta);
                        }
                    });
                }
            }
        }
    }

    private void seleccionarPokemonLista(JPanel tarjetaSeleccionada) {
        ventana.restaurarSeleccionLista();

        tarjetaSeleccionada.setBorder(new LineBorder(new Color(80, 160, 90), 3, true));

        pokemonSeleccionadoLista = (Pokemon) tarjetaSeleccionada.getClientProperty("pokemon");
        pokemonSeleccionadoEquipo = null;

        ventana.setTextoPokemonSeleccionado("Pokémon seleccionado: " + pokemonSeleccionadoLista.getNombre() + " (lista)");
        ventana.restaurarSeleccionEquipo();
    }

    private void seleccionarPokemonEquipo(JPanel panelSeleccionado) {
        ventana.restaurarSeleccionEquipo();

        panelSeleccionado.setBorder(new LineBorder(new Color(220, 120, 120), 3, true));

        pokemonSeleccionadoEquipo = (Pokemon) panelSeleccionado.getClientProperty("pokemon");
        pokemonSeleccionadoLista = null;

        if (pokemonSeleccionadoEquipo != null) {
            ventana.setTextoPokemonSeleccionado("Pokémon seleccionado: " + pokemonSeleccionadoEquipo.getNombre() + " (equipo)");
        } else {
            ventana.setTextoPokemonSeleccionado("Pokémon seleccionado: Espacio vacío");
        }

        ventana.restaurarSeleccionLista();
    }

    private void agregarPokemonAlEquipo() {
        if (pokemonSeleccionadoLista == null) {
            ventana.mostrarDialogo("Debes seleccionar un Pokémon de la lista.");
            return;
        }

        Partida partida = ventana.getPartida();
        ArrayList<Pokemon> equipoActual = partida.getJugador().getEquipo().getPokemones();

        if (equipoActual.contains(pokemonSeleccionadoLista)) {
            ventana.mostrarDialogo("Ese Pokémon ya está dentro del equipo.");
            return;
        }

        if (partida.getJugador().getEquipo().estaLleno()) {
            ventana.mostrarDialogo("El equipo ya tiene 6 Pokémon.");
            return;
        }

        partida.getJugador().getEquipo().agregarPokemon(pokemonSeleccionadoLista);
        DataManager.guardarPartida(partida);

        ventana.actualizarVistaEquipo();
        ventana.setTextoPokemonSeleccionado("Pokémon agregado al equipo: " + pokemonSeleccionadoLista.getNombre());

        pokemonSeleccionadoLista = null;
        pokemonSeleccionadoEquipo = null;
        ventana.restaurarSeleccionLista();
        ventana.restaurarSeleccionEquipo();
    }

    private void quitarPokemonDelEquipo() {
        if (pokemonSeleccionadoEquipo == null) {
            ventana.mostrarDialogo("Debes seleccionar un Pokémon del equipo.");
            return;
        }

        Partida partida = ventana.getPartida();
        partida.getJugador().getEquipo().eliminarPokemon(pokemonSeleccionadoEquipo);
        DataManager.guardarPartida(partida);

        ventana.actualizarVistaEquipo();
        ventana.setTextoPokemonSeleccionado("Pokémon eliminado del equipo: " + pokemonSeleccionadoEquipo.getNombre());

        pokemonSeleccionadoEquipo = null;
        pokemonSeleccionadoLista = null;
        ventana.restaurarSeleccionLista();
        ventana.restaurarSeleccionEquipo();
    }
}