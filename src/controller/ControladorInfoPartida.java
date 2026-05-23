package src.controller;

import src.model.JugadorHumano;
import src.model.Partida;
import src.model.Pokedex;
import src.model.Pokemon;
import src.persistance.DataManager;
import src.view.VentanaGameplay;
import src.view.VentanaInfoPartida;
import src.view.VentanaPartidas;

public class ControladorInfoPartida {

    private VentanaInfoPartida ventana;
    private VentanaPartidas ventanaPartidas;

    public ControladorInfoPartida(VentanaInfoPartida ventana, VentanaPartidas ventanaPartidas) {
        this.ventana = ventana;
        this.ventanaPartidas = ventanaPartidas;

        inicializarEventos();
    }

    private void inicializarEventos() {
        ventana.getBtnCrear().addActionListener(e -> crearPartida());
    }

    private void crearPartida() {
        String nombreJugador = ventana.getTxtNombreJugador().getText().trim();

        if (nombreJugador.isEmpty()) {
            ventana.mostrarDialogo("Debes ingresar el nombre del jugador.");
            return;
        }

        if (ventana.getIdPokemonSeleccionado() == -1) {
            ventana.mostrarDialogo("Debes seleccionar un Pokémon inicial.");
            return;
        }

        int idInicialSeleccionado = ventana.getIdPokemonSeleccionado();

        // Creamos el pokemon inicial a nivel 5
        Pokemon pokemonInicial = Pokedex.crearPokemon(idInicialSeleccionado, 5);

        // Creamos al jugador y la partida
        JugadorHumano jugadorHumano = new JugadorHumano(nombreJugador, pokemonInicial);
        Partida partida = new Partida(ventana.getSlot(), jugadorHumano);

        // Agregamos las otras primeras formas a capturados
        agregarPokemonesBaseIniciales(jugadorHumano, idInicialSeleccionado);

        // Guardamos la partida
        DataManager.guardarPartida(partida);

        // Cerramos esta ventana y abrimos gameplay
        ventana.setAbriendoGameplay(true);
        ventana.dispose();

        VentanaGameplay ventanaGameplay = new VentanaGameplay(ventanaPartidas, partida);
        new ControladorGameplay(ventanaGameplay, partida);
    }

    // Metodo para agregar los primeros pokemon de cada linea evolutiva
    private void agregarPokemonesBaseIniciales(JugadorHumano jugadorHumano, int idInicialSeleccionado) {
        int[] idsBase = {1, 4, 7, 10, 12, 14, 17, 19, 21, 24, 26};

        for (int idPokemon : idsBase) {
            // El inicial elegido ya se agregó solo en el constructor de Jugador
            if (idPokemon == idInicialSeleccionado) {
                continue;
            }

            // Si es uno de los otros 2 iniciales, no lo agregamos
            if (esInicialKanto(idPokemon)) {
                continue;
            }

            // Agregamos el resto a la lista de capturados a nivel 5
            Pokemon pokemonCapturado = Pokedex.crearPokemon(idPokemon, 5);
            jugadorHumano.getPokemonesCapturados().add(pokemonCapturado);
        }
    }

    // Metodo para saber si un pokemon es uno de los 3 iniciales principales
    private boolean esInicialKanto(int idPokemon) {
        return idPokemon == 1 || idPokemon == 4 || idPokemon == 7;
    }
}