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
        pokemonesBase.add(new Pokemon(1, "Bulbasaur", "Planta", 45, 16, 45, 1, 16, "Ivysaur"));
        pokemonesBase.add(new Pokemon(2, "Ivysaur", "Planta", 60, 21, 60, 1, 32, "Venusaur"));
        pokemonesBase.add(new Pokemon(3, "Venusaur", "Planta", 80, 27, 80, 1, 0, "Ninguna"));

        // Línea de Charmander
        pokemonesBase.add(new Pokemon(4, "Charmander", "Fuego", 39, 17, 65, 1, 16, "Charmeleon"));
        pokemonesBase.add(new Pokemon(5, "Charmeleon", "Fuego", 58, 21, 80, 1, 36, "Charizard"));
        pokemonesBase.add(new Pokemon(6, "Charizard", "Fuego", 78, 28, 100, 1, 0, "Ninguna"));

        // Línea de Squirtle
        pokemonesBase.add(new Pokemon(7, "Squirtle", "Agua", 44, 16, 43, 1, 16, "Wartortle"));
        pokemonesBase.add(new Pokemon(8, "Wartortle", "Agua", 59, 21, 58, 1, 36, "Blastoise"));
        pokemonesBase.add(new Pokemon(9, "Blastoise", "Agua", 79, 28, 78, 1, 0, "Ninguna"));

        // Línea de Caterpie
        pokemonesBase.add(new Pokemon(10, "Caterpie", "Bicho", 45, 10, 45, 1, 7, "Metapod"));
        pokemonesBase.add(new Pokemon(11, "Metapod", "Bicho", 50, 7, 30, 1, 10, "Butterfree"));
        pokemonesBase.add(new Pokemon(12, "Butterfree", "Bicho", 60, 15, 70, 1, 0, "Ninguna"));

        // Línea de Weedle
        pokemonesBase.add(new Pokemon(13, "Weedle", "Bicho", 40, 12, 50, 1, 7, "Kakuna"));
        pokemonesBase.add(new Pokemon(14, "Kakuna", "Bicho", 45, 8, 35, 1, 10, "Beedrill"));
        pokemonesBase.add(new Pokemon(15, "Beedrill", "Bicho", 65, 30, 75, 1, 0, "Ninguna"));

        // Línea de Pidgey
        pokemonesBase.add(new Pokemon(16, "Pidgey", "Normal", 40, 15, 56, 1, 18, "Pidgeotto"));
        pokemonesBase.add(new Pokemon(17, "Pidgeotto", "Normal", 63, 20, 71, 1, 36, "Pidgeot"));
        pokemonesBase.add(new Pokemon(18, "Pidgeot", "Normal", 83, 27, 101, 1, 0, "Ninguna"));

        // Línea de Rattata
        pokemonesBase.add(new Pokemon(19, "Rattata", "Normal", 30, 19, 72, 1, 20, "Raticate"));
        pokemonesBase.add(new Pokemon(20, "Raticate", "Normal", 55, 27, 97, 1, 0, "Ninguna"));

        // Línea de Spearow
        pokemonesBase.add(new Pokemon(21, "Spearow", "Normal", 40, 20, 70, 1, 20, "Fearow"));
        pokemonesBase.add(new Pokemon(22, "Fearow", "Normal", 65, 30, 100, 1, 0, "Ninguna"));

        // Línea de Ekans
        pokemonesBase.add(new Pokemon(23, "Ekans", "Veneno", 35, 20, 55, 1, 22, "Arbok"));
        pokemonesBase.add(new Pokemon(24, "Arbok", "Veneno", 60, 28, 80, 1, 0, "Ninguna"));

        // Línea de Pikachu
        pokemonesBase.add(new Pokemon(25, "Pikachu", "Eléctrico", 35, 18, 90, 1, 30, "Raichu"));
        pokemonesBase.add(new Pokemon(26, "Raichu", "Eléctrico", 60, 30, 110, 1, 0, "Ninguna"));

        // Línea de Sandshrew
        pokemonesBase.add(new Pokemon(27, "Sandshrew", "Tierra", 50, 25, 40, 1, 22, "Sandslash"));
        pokemonesBase.add(new Pokemon(28, "Sandslash", "Tierra", 75, 33, 65, 1, 0, "Ninguna"));

        // Línea de Nidoran♀
        pokemonesBase.add(new Pokemon(29, "Nidoran♀", "Veneno", 55, 16, 41, 1, 16, "Nidorina"));
        pokemonesBase.add(new Pokemon(30, "Nidorina", "Veneno", 70, 21, 56, 1, 28, "Nidoqueen"));
        pokemonesBase.add(new Pokemon(31, "Nidoqueen", "Veneno", 90, 27, 76, 1, 0, "Ninguna"));

        // Línea de Nidoran♂
        pokemonesBase.add(new Pokemon(32, "Nidoran♂", "Veneno", 46, 19, 50, 1, 16, "Nidorino"));
        pokemonesBase.add(new Pokemon(33, "Nidorino", "Veneno", 61, 24, 65, 1, 28, "Nidoking"));
        pokemonesBase.add(new Pokemon(34, "Nidoking", "Veneno", 81, 31, 85, 1, 0, "Ninguna"));

        // Línea de Clefairy
        pokemonesBase.add(new Pokemon(35, "Clefairy", "Normal", 70, 15, 35, 1, 30, "Clefable"));
        pokemonesBase.add(new Pokemon(36, "Clefable", "Normal", 95, 23, 60, 1, 0, "Ninguna"));

        // Línea de Vulpix
        pokemonesBase.add(new Pokemon(37, "Vulpix", "Fuego", 38, 14, 65, 1, 30, "Ninetales"));
        pokemonesBase.add(new Pokemon(38, "Ninetales", "Fuego", 73, 25, 100, 1, 0, "Ninguna"));

        // Línea de Jigglypuff
        pokemonesBase.add(new Pokemon(39, "Jigglypuff", "Normal", 115, 15, 20, 1, 30, "Wigglytuff"));
        pokemonesBase.add(new Pokemon(40, "Wigglytuff", "Normal", 140, 23, 45, 1, 0, "Ninguna"));

        // Línea de Zubat
        pokemonesBase.add(new Pokemon(41, "Zubat", "Veneno", 40, 15, 55, 1, 22, "Golbat"));
        pokemonesBase.add(new Pokemon(42, "Golbat", "Veneno", 75, 27, 90, 1, 0, "Ninguna"));

        // Línea de Oddish
        pokemonesBase.add(new Pokemon(43, "Oddish", "Planta", 45, 17, 30, 1, 21, "Gloom"));
        pokemonesBase.add(new Pokemon(44, "Gloom", "Planta", 60, 22, 40, 1, 32, "Vileplume"));
        pokemonesBase.add(new Pokemon(45, "Vileplume", "Planta", 75, 27, 50, 1, 0, "Ninguna"));

        // Línea de Paras
        pokemonesBase.add(new Pokemon(46, "Paras", "Bicho", 35, 23, 25, 1, 24, "Parasect"));
        pokemonesBase.add(new Pokemon(47, "Parasect", "Bicho", 60, 32, 30, 1, 0, "Ninguna"));

        // Línea de Venonat
        pokemonesBase.add(new Pokemon(48, "Venonat", "Bicho", 60, 18, 45, 1, 31, "Venomoth"));
        pokemonesBase.add(new Pokemon(49, "Venomoth", "Bicho", 70, 22, 90, 1, 0, "Ninguna"));

        // Línea de Diglett
        pokemonesBase.add(new Pokemon(50, "Diglett", "Tierra", 10, 18, 95, 1, 26, "Dugtrio"));
        pokemonesBase.add(new Pokemon(51, "Dugtrio", "Tierra", 35, 27, 120, 1, 0, "Ninguna"));

        // Línea de Meowth
        pokemonesBase.add(new Pokemon(52, "Meowth", "Normal", 40, 15, 90, 1, 28, "Persian"));
        pokemonesBase.add(new Pokemon(53, "Persian", "Normal", 65, 23, 115, 1, 0, "Ninguna"));

        // Línea de Psyduck
        pokemonesBase.add(new Pokemon(54, "Psyduck", "Agua", 50, 17, 55, 1, 33, "Golduck"));
        pokemonesBase.add(new Pokemon(55, "Golduck", "Agua", 80, 27, 85, 1, 0, "Ninguna"));

        // Línea de Mankey
        pokemonesBase.add(new Pokemon(56, "Mankey", "Lucha", 40, 27, 70, 1, 28, "Primeape"));
        pokemonesBase.add(new Pokemon(57, "Primeape", "Lucha", 65, 35, 95, 1, 0, "Ninguna"));

        // Línea de Growlithe
        pokemonesBase.add(new Pokemon(58, "Growlithe", "Fuego", 55, 23, 60, 1, 32, "Arcanine"));
        pokemonesBase.add(new Pokemon(59, "Arcanine", "Fuego", 90, 37, 95, 1, 0, "Ninguna"));

        // Línea de Poliwag
        pokemonesBase.add(new Pokemon(60, "Poliwag", "Agua", 40, 17, 90, 1, 25, "Poliwhirl"));
        pokemonesBase.add(new Pokemon(61, "Poliwhirl", "Agua", 65, 22, 90, 1, 32, "Poliwrath"));
        pokemonesBase.add(new Pokemon(62, "Poliwrath", "Agua", 90, 32, 70, 1, 0, "Ninguna"));
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