package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Equipo implements Serializable {
    private ArrayList<Pokemon> pokemones; // Arreglo donde están los pokemones del equipo
    private static final int MAX_POKEMONES = 6; // Delimitamos el máximo de pokemones por equipo

    // Creamos el Arreglo vacio donde están los pokemones del equipo
    public Equipo() {
        pokemones = new ArrayList<>();
    }

    // Metodo para agregar un Pokemon al Arreglo-Equipo
    public boolean agregarPokemon(Pokemon pokemon) {
        if (pokemon != null && pokemones.size() < MAX_POKEMONES) {
            pokemones.add(pokemon);
            return true;
        }
        return false;
    }

    // Meotdo para sacar a un Pokemon del Arreglo-Equipo
    public boolean eliminarPokemon(Pokemon pokemon) {
        return pokemones.remove(pokemon);
    }

    // Consulta de los pokemones dentro del Arreglo-Equipo
    public ArrayList<Pokemon> getPokemones() {
        return pokemones;
    }

    // Consulta para saber si el equipo está lleno
    public boolean estaLleno() {
        return pokemones.size() >= MAX_POKEMONES;
    }

    // Consulta para saber con seguridad los pokemones que tenemos
    public int cantidadPokemones() {
        return pokemones.size();
    }
}