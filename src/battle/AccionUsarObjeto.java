package src.battle;

import src.model.Jugador;

// Esta clase representa una acción de usar un objeto en combate
public class AccionUsarObjeto extends AccionCombate {

    private TipoObjeto tipoObjeto;
    private int indicePokemonObjetivo;

    public AccionUsarObjeto(Jugador jugador, TipoObjeto tipoObjeto, int indicePokemonObjetivo) {
        super(jugador);
        this.tipoObjeto = tipoObjeto;
        this.indicePokemonObjetivo = indicePokemonObjetivo;
    }

    public TipoObjeto getTipoObjeto() {
        return tipoObjeto;
    }

    public void setTipoObjeto(TipoObjeto tipoObjeto) {
        this.tipoObjeto = tipoObjeto;
    }

    public int getIndicePokemonObjetivo() {
        return indicePokemonObjetivo;
    }

    public void setIndicePokemonObjetivo(int indicePokemonObjetivo) {
        this.indicePokemonObjetivo = indicePokemonObjetivo;
    }

    @Override
    public String getNombreAccion() {
        return "Usar objeto";
    }

    @Override
    public boolean esAccionPrioritaria() {
        return true;
    }
}