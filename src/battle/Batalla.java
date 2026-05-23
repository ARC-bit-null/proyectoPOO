package src.battle;

import src.model.InventarioCombate;
import src.model.Jugador;
import src.model.Pokemon;

import java.util.ArrayList;

// Esta clase abstracta representa la base de cualquier combate del juego
public abstract class Batalla {

    // Guardamos a los dos participantes del combate
    protected Jugador jugador1;
    protected Jugador jugador2;

    // Guardamos las acciones elegidas por cada jugador en la ronda actual
    protected AccionCombate accionJugador1;
    protected AccionCombate accionJugador2;

    // Guardamos si la batalla ya terminó y quién fue el ganador
    protected boolean batallaTerminada;
    protected Jugador ganador;

    public Batalla(Jugador jugador1, Jugador jugador2) {
        this.jugador1 = jugador1;
        this.jugador2 = jugador2;
        this.batallaTerminada = false;
        this.ganador = null;
    }

    public Jugador getJugador1() {
        return jugador1;
    }

    public void setJugador1(Jugador jugador1) {
        this.jugador1 = jugador1;
    }

    public Jugador getJugador2() {
        return jugador2;
    }

    public void setJugador2(Jugador jugador2) {
        this.jugador2 = jugador2;
    }

    public AccionCombate getAccionJugador1() {
        return accionJugador1;
    }

    public void setAccionJugador1(AccionCombate accionJugador1) {
        this.accionJugador1 = accionJugador1;
    }

    public AccionCombate getAccionJugador2() {
        return accionJugador2;
    }

    public void setAccionJugador2(AccionCombate accionJugador2) {
        this.accionJugador2 = accionJugador2;
    }

    public boolean isBatallaTerminada() {
        return batallaTerminada;
    }

    public void setBatallaTerminada(boolean batallaTerminada) {
        this.batallaTerminada = batallaTerminada;
    }

    public Jugador getGanador() {
        return ganador;
    }

    public void setGanador(Jugador ganador) {
        this.ganador = ganador;
    }

    public void registrarAccionJugador1(AccionCombate accion) {
        this.accionJugador1 = accion;
    }

    public void registrarAccionJugador2(AccionCombate accion) {
        this.accionJugador2 = accion;
    }

    public boolean accionesListas() {
        return accionJugador1 != null && accionJugador2 != null;
    }

    public void limpiarAcciones() {
        accionJugador1 = null;
        accionJugador2 = null;
    }

    public abstract Pokemon getPokemonActivoJugador1();

    public abstract Pokemon getPokemonActivoJugador2();

    public abstract int getIndiceActivoJugador1();

    public abstract int getIndiceActivoJugador2();

    public abstract InventarioCombate getInventarioJugador1();

    public abstract InventarioCombate getInventarioJugador2();

    public abstract boolean isCambioObligatorioJugador1();

    public abstract boolean isCambioObligatorioJugador2();

    public abstract void forzarCambioJugador1(int nuevoIndice);

    public abstract void forzarCambioJugador2(int nuevoIndice);

    public abstract ArrayList<String> getMensajesRonda();

    public abstract void limpiarMensajesRonda();

    public abstract boolean esModoPve();

    public abstract void resolverRonda();

    public abstract void verificarFinBatalla();
}