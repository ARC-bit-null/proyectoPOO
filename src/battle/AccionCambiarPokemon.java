package src.battle;

import src.model.Jugador;

// Esta clase representa una acción de cambiar el pokemon activo
public class AccionCambiarPokemon extends AccionCombate {

    private int indicePokemonCambio;

    public AccionCambiarPokemon(Jugador jugador, int indicePokemonCambio) {
        super(jugador);
        this.indicePokemonCambio = indicePokemonCambio;
    }

    public int getIndicePokemonCambio() {
        return indicePokemonCambio;
    }

    public void setIndicePokemonCambio(int indicePokemonCambio) {
        this.indicePokemonCambio = indicePokemonCambio;
    }

    @Override
    public String getNombreAccion() {
        return "Cambio";
    }

    @Override
    public boolean esAccionPrioritaria() {
        return true;
    }
}