package src.controller;

import src.battle.BatallaPvp;
import src.model.JugadorHumano;
import src.model.Pokedex;
import src.model.Pokemon;
import src.view.VentanaCombate;
import src.view.VentanaPvp;

import java.util.ArrayList;

public class ControladorPvp {

    private VentanaPvp ventana;

    public ControladorPvp(VentanaPvp ventana) {
        this.ventana = ventana;
        inicializarEventos();
    }

    private void inicializarEventos() {
        ventana.getBtnAgregarJ1().addActionListener(e -> agregarPokemonAEquipo(ventana.getEquipoJugador1(), "Jugador 1"));
        ventana.getBtnAgregarJ2().addActionListener(e -> agregarPokemonAEquipo(ventana.getEquipoJugador2(), "Jugador 2"));
        ventana.getBtnQuitarJ1().addActionListener(e -> quitarPokemonDeJugador1());
        ventana.getBtnQuitarJ2().addActionListener(e -> quitarPokemonDeJugador2());
        ventana.getBtnListo().addActionListener(e -> validarEquiposYContinuar());
    }

    private void agregarPokemonAEquipo(ArrayList<Pokemon> equipoDestino, String nombreJugador) {
        Pokemon seleccionado = ventana.getPokemonSeleccionadoLista();

        if (seleccionado == null) {
            ventana.mostrarDialogo("Debes seleccionar un Pokémon de la lista.");
            return;
        }

        if (equipoDestino.size() >= 6) {
            ventana.mostrarDialogo("El equipo del " + nombreJugador + " ya tiene 6 Pokémon.");
            return;
        }

        Pokemon copiaPokemon = crearCopiaPokemonSeleccionado(seleccionado);
        equipoDestino.add(copiaPokemon);

        ventana.actualizarEquipos();
        ventana.setTextoSeleccionActual("Pokémon agregado al equipo del " + nombreJugador + ": " + copiaPokemon.getNombre());
        ventana.setPokemonSeleccionadoLista(null);
        ventana.restaurarSeleccionLista();
    }

    private Pokemon crearCopiaPokemonSeleccionado(Pokemon seleccionado) {
        return Pokedex.crearPokemon(seleccionado.getId(), seleccionado.getNivel());
    }

    private void quitarPokemonDeJugador1() {
        Pokemon seleccionado = ventana.getPokemonSeleccionadoJ1();

        if (seleccionado == null) {
            ventana.mostrarDialogo("Debes seleccionar un Pokémon del equipo del Jugador 1.");
            return;
        }

        ventana.getEquipoJugador1().remove(seleccionado);
        ventana.actualizarEquipos();
        ventana.setTextoSeleccionActual("Pokémon eliminado del equipo del Jugador 1: " + seleccionado.getNombre());
        ventana.setPokemonSeleccionadoJ1(null);
    }

    private void quitarPokemonDeJugador2() {
        Pokemon seleccionado = ventana.getPokemonSeleccionadoJ2();

        if (seleccionado == null) {
            ventana.mostrarDialogo("Debes seleccionar un Pokémon del equipo del Jugador 2.");
            return;
        }

        ventana.getEquipoJugador2().remove(seleccionado);
        ventana.actualizarEquipos();
        ventana.setTextoSeleccionActual("Pokémon eliminado del equipo del Jugador 2: " + seleccionado.getNombre());
        ventana.setPokemonSeleccionadoJ2(null);
    }

    private void validarEquiposYContinuar() {
        if (ventana.getEquipoJugador1().isEmpty()) {
            ventana.mostrarDialogo("El Jugador 1 debe tener al menos un Pokémon.");
            return;
        }

        if (ventana.getEquipoJugador2().isEmpty()) {
            ventana.mostrarDialogo("El Jugador 2 debe tener al menos un Pokémon.");
            return;
        }

        JugadorHumano jugador1 = crearJugadorParaCombate("Jugador 1", ventana.getEquipoJugador1());
        JugadorHumano jugador2 = crearJugadorParaCombate("Jugador 2", ventana.getEquipoJugador2());

        BatallaPvp batalla = new BatallaPvp(jugador1, jugador2);

        ventana.setVisible(false);

        VentanaCombate ventanaCombate = new VentanaCombate(ventana, "Combate PvP");
        new ControladorCombate(ventanaCombate, batalla);
    }

    private JugadorHumano crearJugadorParaCombate(String nombreJugador, ArrayList<Pokemon> equipoSeleccionado) {
        JugadorHumano jugador = new JugadorHumano(nombreJugador, equipoSeleccionado.get(0));

        for (int i = 1; i < equipoSeleccionado.size(); i++) {
            jugador.getEquipo().agregarPokemon(equipoSeleccionado.get(i));
        }

        return jugador;
    }
}