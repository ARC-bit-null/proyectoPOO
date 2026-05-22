package engine; 

import model.Pokemon;
import model.Habilidad;
import battle.CalculadoraDanio;

public class GestorBatalla {
    private Pokemon jugador;
    private Pokemon rival;
    private boolean esTurnoJugador;

    public GestorBatalla(Pokemon jugador, Pokemon rival) {
        this.jugador = jugador;
        this.rival = rival;
        this.esTurnoJugador = jugador.getVelocidad() >= rival.getVelocidad();
    }

    public void procesarTurno(Habilidad habilidadSeleccionada) {
        if (batallaTerminada()) return;

        if (esTurnoJugador) {
            int danio = CalculadoraDanio.calcular(jugador, rival, habilidadSeleccionada);
            rival.recibirDano(danio);
            habilidadSeleccionada.aplicarEfectoEspecial(jugador, rival);
            esTurnoJugador = false;
        }
    }

    public void procesarTurnoCPU(Habilidad habilidadCPU) {
        if (batallaTerminada() || esTurnoJugador) return;

        int danio = CalculadoraDanio.calcular(rival, jugador, habilidadCPU);
        jugador.recibirDano(danio);
        habilidadCPU.aplicarEfectoEspecial(rival, jugador);
        esTurnoJugador = true;
    }

    public boolean batallaTerminada() {
        return jugador.estaDerrotado() || rival.estaDerrotado();
    }

    public Pokemon getJugador() { return jugador; }
    public Pokemon getRival() { return rival; }
    public boolean esTurnoJugador() { return esTurnoJugador; }
}
