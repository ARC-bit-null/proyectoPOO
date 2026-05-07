package Model;

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
        pokemonesBase.add(new Pokemon(1, "Bulbasaur", "Planta", 45, 49, 45, 1, 16, "Ivysaur"));
        pokemonesBase.add(new Pokemon(2, "Ivysaur", "Planta", 60, 62, 60, 1, 32, "Venusaur"));
        pokemonesBase.add(new Pokemon(3, "Venusaur", "Planta", 80, 82, 80, 1, 0, "Ninguna"));

        // Línea de Charmander
        pokemonesBase.add(new Pokemon(4, "Charmander", "Fuego", 39, 52, 65, 1, 16, "Charmeleon"));
        pokemonesBase.add(new Pokemon(5, "Charmeleon", "Fuego", 58, 64, 80, 1, 36, "Charizard"));
        pokemonesBase.add(new Pokemon(6, "Charizard", "Fuego", 78, 84, 100, 1, 0, "Ninguna"));

        // Línea de Squirtle
        pokemonesBase.add(new Pokemon(7, "Squirtle", "Agua", 44, 48, 43, 1, 16, "Wartortle"));
        pokemonesBase.add(new Pokemon(8, "Wartortle", "Agua", 59, 63, 58, 1, 36, "Blastoise"));
        pokemonesBase.add(new Pokemon(9, "Blastoise", "Agua", 79, 83, 78, 1, 0, "Ninguna"));

        // Línea de Pichu
        pokemonesBase.add(new Pokemon(10, "Pichu", "Electrico", 20, 40, 60, 1, 10, "Pikachu"));
        pokemonesBase.add(new Pokemon(11, "Pikachu", "Electrico", 35, 55, 90, 1, 20, "Raichu"));
        pokemonesBase.add(new Pokemon(12, "Raichu", "Electrico", 60, 90, 110, 1, 0, "Ninguna"));
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
            return new Pokemon(0, "MissingNo", "Normal", 10, 10, 10, 1, 0, "Ninguna");
        }

        return new Pokemon(
                pokemonBase.getId(),
                pokemonBase.getNombre(),
                pokemonBase.getTipo(),
                pokemonBase.getHp(),
                pokemonBase.getDano(),
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