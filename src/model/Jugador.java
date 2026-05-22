package src.model;

import java.io.Serializable;
import java.util.ArrayList;

// Esta clase abstracta representa la base de cualquier jugador del juego
public abstract class Jugador implements Serializable {

    // Atributos principales del jugador
    protected String nombre;
    protected Equipo equipo;
    protected ArrayList<Pokemon> pokemonesCapturados;
    protected Pokemon pokemonInicial;

    public Jugador(String nombre, Pokemon pokemonInicial) {
        this.nombre = nombre;
        this.pokemonInicial = pokemonInicial;

        // Inicializamos el equipo del jugador
        this.equipo = new Equipo();

        // Inicializamos la lista de pokemones capturados
        this.pokemonesCapturados = new ArrayList<>();

        // Agregamos automáticamente el pokemon inicial al equipo y a la lista de capturados
        this.equipo.agregarPokemon(pokemonInicial);
        this.pokemonesCapturados.add(pokemonInicial);
    }

    // Getter y Setter del nombre del jugador
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Getter y Setter del equipo del jugador
    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    // Getter y Setter de la lista de pokemones capturados
    public ArrayList<Pokemon> getPokemonesCapturados() {
        return pokemonesCapturados;
    }

    public void setPokemonesCapturados(ArrayList<Pokemon> pokemonesCapturados) {
        this.pokemonesCapturados = pokemonesCapturados;
    }

    // Getter y Setter del pokemon inicial
    public Pokemon getPokemonInicial() {
        return pokemonInicial;
    }

    public void setPokemonInicial(Pokemon pokemonInicial) {
        this.pokemonInicial = pokemonInicial;
    }
}