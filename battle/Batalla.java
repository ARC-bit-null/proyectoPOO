package battle;

import model.Jugador;

// Esta clase representa la base de cualquier combate del juego
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

    // Metodo para obtener al Jugador 1
    public Jugador getJugador1() {
        return jugador1;
    }

    public void setJugador1(Jugador jugador1) {
        this.jugador1 = jugador1;
    }

    // Metodo para obtener al Jugador 2
    public Jugador getJugador2() {
        return jugador2;
    }

    public void setJugador2(Jugador jugador2) {
        this.jugador2 = jugador2;
    }

    // Metodo para obtener la acción elegida por el Jugador 1
    public AccionCombate getAccionJugador1() {
        return accionJugador1;
    }

    public void setAccionJugador1(AccionCombate accionJugador1) {
        this.accionJugador1 = accionJugador1;
    }

    // Metodo para obtener la acción elegida por el Jugador 2
    public AccionCombate getAccionJugador2() {
        return accionJugador2;
    }

    public void setAccionJugador2(AccionCombate accionJugador2) {
        this.accionJugador2 = accionJugador2;
    }

    // Metodo para saber si la batalla ya terminó
    public boolean isBatallaTerminada() {
        return batallaTerminada;
    }

    public void setBatallaTerminada(boolean batallaTerminada) {
        this.batallaTerminada = batallaTerminada;
    }

    // Metodo para obtener el ganador de la batalla
    public Jugador getGanador() {
        return ganador;
    }

    public void setGanador(Jugador ganador) {
        this.ganador = ganador;
    }

    // Metodo para registrar la acción del Jugador 1
    public void registrarAccionJugador1(AccionCombate accion) {
        this.accionJugador1 = accion;
    }

    // Metodo para registrar la acción del Jugador 2
    public void registrarAccionJugador2(AccionCombate accion) {
        this.accionJugador2 = accion;
    }

    // Metodo para saber si ambos jugadores ya eligieron una acción
    public boolean accionesListas() {
        return accionJugador1 != null && accionJugador2 != null;
    }

    // Metodo para limpiar las acciones actuales y preparar la siguiente ronda
    public void limpiarAcciones() {
        accionJugador1 = null;
        accionJugador2 = null;
    }

    // Metodo abstracto para resolver una ronda del combate
    public abstract void resolverRonda();

    // Metodo abstracto para verificar si la batalla terminó
    public abstract void verificarFinBatalla();
}
