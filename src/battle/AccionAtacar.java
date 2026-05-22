package src.battle;

import src.model.Habilidad;
import src.model.Jugador;

// Esta clase representa una acción de ataque durante el combate
public class AccionAtacar extends AccionCombate {

    private Habilidad habilidadSeleccionada;

    public AccionAtacar(Jugador jugador, Habilidad habilidadSeleccionada) {
        super(jugador);
        this.habilidadSeleccionada = habilidadSeleccionada;
    }

    public Habilidad getHabilidadSeleccionada() {
        return habilidadSeleccionada;
    }

    public void setHabilidadSeleccionada(Habilidad habilidadSeleccionada) {
        this.habilidadSeleccionada = habilidadSeleccionada;
    }

    @Override
    public String getNombreAccion() {
        return "Atacar";
    }

    @Override
    public boolean esAccionPrioritaria() {
        return false;
    }
}