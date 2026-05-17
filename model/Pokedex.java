package model;

import java.util.ArrayList;

public class Pokedex {

    private static final ArrayList<Pokemon> pokemonesBase = new ArrayList<>();

    static {
        inicializarPokedex();
    }

    // Método para cargar una sola vez los Pokémon base de la pokedex
    private static void inicializarPokedex() {
        if (!pokemonesBase.isEmpty()) {
            return;
        }

        // Línea de Bulbasaur
        pokemonesBase.add(new Pokemon(1, "Bulbasaur", TipoPokemon.PLANTA, 45, 49, 49, 45, 1, 16, "Ivysaur"));
        pokemonesBase.add(new Pokemon(2, "Ivysaur", TipoPokemon.PLANTA, 60, 62, 63, 60, 1, 32, "Venusaur"));
        pokemonesBase.add(new Pokemon(3, "Venusaur", TipoPokemon.PLANTA, 80, 82, 83, 80, 1, 0, "Ninguna"));

        // Línea de Charmander
        pokemonesBase.add(new Pokemon(4, "Charmander", TipoPokemon.FUEGO, 39, 52, 43, 65, 1, 16, "Charmeleon"));
        pokemonesBase.add(new Pokemon(5, "Charmeleon", TipoPokemon.FUEGO, 58, 64, 58, 80, 1, 36, "Charizard"));
        pokemonesBase.add(new Pokemon(6, "Charizard", TipoPokemon.FUEGO, 78, 84, 78, 100, 1, 0, "Ninguna"));

        // Línea de Squirtle
        pokemonesBase.add(new Pokemon(7, "Squirtle", TipoPokemon.AGUA, 44, 48, 65, 43, 1, 16, "Wartortle"));
        pokemonesBase.add(new Pokemon(8, "Wartortle", TipoPokemon.AGUA, 59, 63, 80, 58, 1, 36, "Blastoise"));
        pokemonesBase.add(new Pokemon(9, "Blastoise", TipoPokemon.AGUA, 79, 83, 100, 78, 1, 0, "Ninguna"));

        // Línea de Pikachu
        pokemonesBase.add(new Pokemon(10, "Pikachu", TipoPokemon.ELECTRICO, 35, 55, 30, 90, 1, 30, "Raichu"));
        pokemonesBase.add(new Pokemon(11, "Raichu", TipoPokemon.ELECTRICO, 60, 90, 55, 110, 1, 0, "Ninguna"));

        // Línea de Sandshrew
        pokemonesBase.add(new Pokemon(12, "Sandshrew", TipoPokemon.TIERRA, 50, 75, 85, 40, 1, 22, "Sandslash"));
        pokemonesBase.add(new Pokemon(13, "Sandslash", TipoPokemon.TIERRA, 75, 100, 110, 65, 1, 0, "Ninguna"));

        // Línea de Oddish
        pokemonesBase.add(new Pokemon(14, "Oddish", TipoPokemon.PLANTA, 45, 50, 55, 30, 1, 21, "Gloom"));
        pokemonesBase.add(new Pokemon(15, "Gloom", TipoPokemon.PLANTA, 60, 65, 70, 40, 1, 35, "Vileplume"));
        pokemonesBase.add(new Pokemon(16, "Vileplume", TipoPokemon.PLANTA, 75, 80, 85, 50, 1, 0, "Ninguna"));

        // Línea de Vulpix
        pokemonesBase.add(new Pokemon(17, "Vulpix", TipoPokemon.FUEGO, 38, 41, 40, 65, 1, 30, "Ninetales"));
        pokemonesBase.add(new Pokemon(18, "Ninetales", TipoPokemon.FUEGO, 73, 76, 75, 100, 1, 0, "Ninguna"));

        // Línea de Psyduck
        pokemonesBase.add(new Pokemon(19, "Psyduck", TipoPokemon.AGUA, 50, 52, 48, 55, 1, 33, "Golduck"));
        pokemonesBase.add(new Pokemon(20, "Golduck", TipoPokemon.AGUA, 80, 82, 78, 85, 1, 0, "Ninguna"));

        // Línea de Geodude
        pokemonesBase.add(new Pokemon(21, "Geodude", TipoPokemon.TIERRA, 40, 80, 100, 20, 1, 25, "Graveler"));
        pokemonesBase.add(new Pokemon(22, "Graveler", TipoPokemon.TIERRA, 55, 95, 115, 35, 1, 40, "Golem"));
        pokemonesBase.add(new Pokemon(23, "Golem", TipoPokemon.TIERRA, 80, 120, 130, 45, 1, 0, "Ninguna"));

        // Línea de Magnemite
        pokemonesBase.add(new Pokemon(24, "Magnemite", TipoPokemon.ELECTRICO, 25, 35, 70, 45, 1, 30, "Magneton"));
        pokemonesBase.add(new Pokemon(25, "Magneton", TipoPokemon.ELECTRICO, 50, 60, 95, 70, 1, 0, "Ninguna"));

        // Línea de Voltorb
        pokemonesBase.add(new Pokemon(26, "Voltorb", TipoPokemon.ELECTRICO, 40, 30, 50, 100, 1, 30, "Electrode"));
        pokemonesBase.add(new Pokemon(27, "Electrode", TipoPokemon.ELECTRICO, 60, 50, 70, 150, 1, 0, "Ninguna"));
    }

    // Método para buscar un Pokémon base dentro de la pokedex por medio de su id
    private static Pokemon buscarPokemonBasePorId(int id) {
        for (Pokemon pokemonBase : pokemonesBase) {
            if (pokemonBase.getId() == id) {
                return pokemonBase;
            }
        }
        return null;
    }

    // Método para crear un nuevo objeto Pokémon usando como referencia un Pokémon base de la pokedex
    public static Pokemon crearPokemon(int id, int nivel) {
        Pokemon pokemonBase = buscarPokemonBasePorId(id);

        if (pokemonBase == null) {
            return new Pokemon(0, "MissingNo", TipoPokemon.NORMAL, 10, 10, 10, 10, 1, 0, "Ninguna");
        }

        return new Pokemon(
                pokemonBase.getId(),
                pokemonBase.getNombre(),
                pokemonBase.getTipo(),
                pokemonBase.getHpMax(),
                pokemonBase.getDano(),
                pokemonBase.getDefensa(),
                pokemonBase.getVelocidad(),
                nivel,
                pokemonBase.getNivelEvolucion(),
                pokemonBase.getNombreEvolucion()
        );
    }

    // Método para obtener todos los Pokémon de la pokedex como nuevas copias
    public static ArrayList<Pokemon> obtenerTodosLosPokemon() {
        ArrayList<Pokemon> listaPokemon = new ArrayList<>();

        for (Pokemon pokemonBase : pokemonesBase) {
            listaPokemon.add(crearPokemon(pokemonBase.getId(), 1));
        }

        return listaPokemon;
    }
}
