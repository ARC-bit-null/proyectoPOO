package Model;

import java.io.Serializable;
import java.util.ArrayList;

public class Partida implements Serializable {
    private int slot;
    private String nombreJugador;
    private Equipo equipo;
    private ArrayList<Pokemon> pokemonesCapturados;
    private Pokemon pokemonInicial;

    // Constructor
    public Partida(int slot, String nombreJugador, Pokemon pokemonInicial) {
        this.slot = slot;
        this.nombreJugador = nombreJugador;
        this.pokemonInicial = pokemonInicial;
        this.equipo = new Equipo();
        this.pokemonesCapturados = new ArrayList<>();

        if (pokemonInicial != null) {
            this.equipo.agregarPokemon(pokemonInicial);
            this.pokemonesCapturados.add(pokemonInicial);
        }
    }

    // Metodos Getter y Setter
    public int getSlot() {
        return slot;
    }

    public String getNombreJugador() {
        return nombreJugador;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public ArrayList<Pokemon> getPokemonesCapturados() {
        return pokemonesCapturados;
    }

    public Pokemon getPokemonInicial() {
        return pokemonInicial;
    }

    public void setNombreJugador(String nombreJugador) {
        this.nombreJugador = nombreJugador;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    public void setPokemonesCapturados(ArrayList<Pokemon> pokemonesCapturados) {
        this.pokemonesCapturados = pokemonesCapturados;
    }

    public void setPokemonInicial(Pokemon pokemonInicial) {
        this.pokemonInicial = pokemonInicial;
    }

    public void agregarPokemonCapturado(Pokemon pokemon) {
        if (pokemon != null) {
            pokemonesCapturados.add(pokemon);
        }
    }
}