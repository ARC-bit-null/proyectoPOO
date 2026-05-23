package src.controller;

import src.battle.BatallaPve;
import src.model.*;
import src.persistance.DataManager;
import src.view.VentanaCombate;
import src.view.VentanaPve;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class ControladorPve {

    private VentanaPve ventana;
    private Partida partida;

    private ModoPve modoSeleccionado;
    private int numeroRivalActual;

    public ControladorPve(VentanaPve ventana, Partida partida) {
        this.ventana = ventana;
        this.partida = partida;
        this.modoSeleccionado = null;
        this.numeroRivalActual = 1;

        inicializarEventos();
    }

    private void inicializarEventos() {
        ventana.getBtnFacil().addActionListener(e -> seleccionarModo(ModoPve.FACIL));
        ventana.getBtnNormal().addActionListener(e -> seleccionarModo(ModoPve.NORMAL));
        ventana.getBtnDificil().addActionListener(e -> seleccionarModo(ModoPve.DIFICIL));
        ventana.getBtnComenzar().addActionListener(e -> comenzarSecuencia());
    }

    private void seleccionarModo(ModoPve modo) {
        this.modoSeleccionado = modo;
        this.numeroRivalActual = 1;

        RivalPve rival = PveManager.obtenerRival(modo, numeroRivalActual);

        if (rival == null) {
            ventana.mostrarDialogo("No se pudo cargar el rival inicial.");
            return;
        }

        ventana.mostrarRivalActual(modo, rival, obtenerNombresEquipoRival(rival));
    }

    private ArrayList<String> obtenerNombresEquipoRival(RivalPve rival) {
        ArrayList<String> nombres = new ArrayList<>();

        for (Integer idPokemon : rival.getIdsPokemon()) {
            Pokemon pokemon = Pokedex.crearPokemon(idPokemon, rival.getNivel());
            nombres.add(pokemon.getNombre());
        }

        return nombres;
    }

    private void comenzarSecuencia() {
        if (modoSeleccionado == null) {
            ventana.mostrarDialogo("Debes seleccionar un modo.");
            return;
        }

        iniciarCombateContraRivalActual();
    }

    private void iniciarCombateContraRivalActual() {
        RivalPve rival = PveManager.obtenerRival(modoSeleccionado, numeroRivalActual);

        if (rival == null) {
            ventana.mostrarDialogo("No se encontró el rival actual.");
            return;
        }

        JugadorCPU jugadorCPU = PveManager.crearJugadorCPU(modoSeleccionado, numeroRivalActual);

        if (jugadorCPU == null) {
            ventana.mostrarDialogo("No se pudo crear el rival CPU.");
            return;
        }

        BatallaPve batalla = new BatallaPve(partida.getJugador(), jugadorCPU);
        batalla.setExperienciaVictoria(rival.getExperienciaOtorgada());

        ventana.setVisible(false);

        VentanaCombate ventanaCombate = new VentanaCombate(ventana, "Combate PvE - Rival " + numeroRivalActual);
        ventanaCombate.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                procesarResultadoSecuencia(batalla);
            }
        });

        new ControladorCombate(ventanaCombate, batalla);
    }

    // Metodo para procesar el resultado del combate y seguir la secuencia
    private void procesarResultadoSecuencia(BatallaPve batalla) {
        DataManager.guardarPartida(partida);

        if (batalla.ganoJugadorHumano()) {
            if (numeroRivalActual < 5) {
                numeroRivalActual++;

                RivalPve siguienteRival = PveManager.obtenerRival(modoSeleccionado, numeroRivalActual);

                if (siguienteRival != null) {
                    ventana.mostrarDialogo("Ganaste el combate. Ahora sigue el Rival " + numeroRivalActual + ".");
                    ventana.mostrarRivalActual(modoSeleccionado, siguienteRival, obtenerNombresEquipoRival(siguienteRival));
                    ventana.setVisible(true);
                }
            } else {
                recuperarEquipoJugadorAlFinal();
                DataManager.guardarPartida(partida);

                ventana.mostrarDialogo("¡Felicidades! Completaste el modo " + textoModo(modoSeleccionado) + ".");
                ventana.dispose();

                if (ventana.getVentanaAnterior() != null) {
                    ventana.getVentanaAnterior().setVisible(true);
                }
            }
            return;
        }

        if (batalla.ganoJugadorCPU()) {
            recuperarEquipoJugadorAlFinal();
            DataManager.guardarPartida(partida);

            ventana.mostrarDialogo("Perdiste la secuencia del modo " + textoModo(modoSeleccionado) + ".");
            ventana.setVisible(true);
            return;
        }

        ventana.setVisible(true);
    }

    // Metodo para dejar listo el equipo del jugador cuando ya terminó toda la secuencia
    private void recuperarEquipoJugadorAlFinal() {
        for (Pokemon pokemon : partida.getJugador().getEquipo().getPokemones()) {
            pokemon.revivir();
            pokemon.curarHp(pokemon.getHpMax());
            pokemon.reiniciarModificadoresTemporales();
        }
    }

    private String textoModo(ModoPve modo) {
        if (modo == ModoPve.FACIL) return "Fácil";
        if (modo == ModoPve.NORMAL) return "Normal";
        return "Difícil";
    }
}