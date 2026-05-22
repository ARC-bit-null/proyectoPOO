package src.model;

public interface Habilidad {
    String getNombre();
    TipoPokemon getTipo();
    int getPoderBase();
    void aplicarEfectoEspecial(Pokemon atacante, Pokemon defensor);
}

