package src.model;

import java.util.ArrayList;

public class Pokedex {

    private static final ArrayList<Pokemon> pokemonesBase = new ArrayList<>();

    static {
        inicializarPokedex();
    }

    // Método para cargar una sola vez los Pokémon base de la pokedex
    private static void inicializarPokedex() {
        if (!pokemonesBase.isEmpty()) return;

        pokemonesBase.add(new PokemonBuilder().setId(1).setNombre("Bulbasaur").setTipo(TipoPokemon.PLANTA).setHp(45).setDano(49).setDefensa(49).setVelocidad(45).setNivel(1).setNivelEvolucion(16).setNombreEvolucion("Ivysaur").setImagenFrontal("resources/front/bulbasaur.png").setImagenTrasera("resources/back/bulbasaur.png").agregarAtaquesIniciales("Látigo Cepa", 45).build());
        pokemonesBase.add(new PokemonBuilder().setId(2).setNombre("Ivysaur").setTipo(TipoPokemon.PLANTA).setHp(60).setDano(62).setDefensa(63).setVelocidad(60).setNivel(1).setNivelEvolucion(32).setNombreEvolucion("Venusaur").setImagenFrontal("resources/front/ivysaur.png").setImagenTrasera("resources/back/ivysaur.png").agregarAtaquesIniciales("Hoja Afilada", 50).build());
        pokemonesBase.add(new PokemonBuilder().setId(3).setNombre("Venusaur").setTipo(TipoPokemon.PLANTA).setHp(80).setDano(82).setDefensa(83).setVelocidad(80).setNivel(1).setNivelEvolucion(0).setNombreEvolucion("Ninguna").setImagenFrontal("resources/front/venusaur.png").setImagenTrasera("resources/back/venusaur.png").agregarAtaquesIniciales("Tormenta Floral", 60).build());

        pokemonesBase.add(new PokemonBuilder().setId(4).setNombre("Charmander").setTipo(TipoPokemon.FUEGO).setHp(39).setDano(52).setDefensa(43).setVelocidad(65).setNivel(1).setNivelEvolucion(16).setNombreEvolucion("Charmeleon").setImagenFrontal("resources/front/charmander.png").setImagenTrasera("resources/back/charmander.png").agregarAtaquesIniciales("Ascuas", 45).build());
        pokemonesBase.add(new PokemonBuilder().setId(5).setNombre("Charmeleon").setTipo(TipoPokemon.FUEGO).setHp(58).setDano(64).setDefensa(58).setVelocidad(80).setNivel(1).setNivelEvolucion(36).setNombreEvolucion("Charizard").setImagenFrontal("resources/front/charmeleon.png").setImagenTrasera("resources/back/charmeleon.png").agregarAtaquesIniciales("Llama", 55).build());
        pokemonesBase.add(new PokemonBuilder().setId(6).setNombre("Charizard").setTipo(TipoPokemon.FUEGO).setHp(78).setDano(84).setDefensa(78).setVelocidad(100).setNivel(1).setNivelEvolucion(0).setNombreEvolucion("Ninguna").setImagenFrontal("resources/front/charizard.png").setImagenTrasera("resources/back/charizard.png").agregarAtaquesIniciales("Lanzallamas", 65).build());

        pokemonesBase.add(new PokemonBuilder().setId(7).setNombre("Squirtle").setTipo(TipoPokemon.AGUA).setHp(44).setDano(48).setDefensa(65).setVelocidad(43).setNivel(1).setNivelEvolucion(16).setNombreEvolucion("Wartortle").setImagenFrontal("resources/front/squirtle.png").setImagenTrasera("resources/back/squirtle.png").agregarAtaquesIniciales("Pistola Agua", 45).build());
        pokemonesBase.add(new PokemonBuilder().setId(8).setNombre("Wartortle").setTipo(TipoPokemon.AGUA).setHp(59).setDano(63).setDefensa(80).setVelocidad(58).setNivel(1).setNivelEvolucion(36).setNombreEvolucion("Blastoise").setImagenFrontal("resources/front/wartortle.png").setImagenTrasera("resources/back/wartortle.png").agregarAtaquesIniciales("Hidrobomba", 55).build());
        pokemonesBase.add(new PokemonBuilder().setId(9).setNombre("Blastoise").setTipo(TipoPokemon.AGUA).setHp(79).setDano(83).setDefensa(100).setVelocidad(78).setNivel(1).setNivelEvolucion(0).setNombreEvolucion("Ninguna").setImagenFrontal("resources/front/blastoise.png").setImagenTrasera("resources/back/blastoise.png").agregarAtaquesIniciales("Tsunami", 65).build());

        pokemonesBase.add(new PokemonBuilder().setId(10).setNombre("Pikachu").setTipo(TipoPokemon.ELECTRICO).setHp(35).setDano(55).setDefensa(30).setVelocidad(90).setNivel(1).setNivelEvolucion(30).setNombreEvolucion("Raichu").setImagenFrontal("resources/front/pikachu.png").setImagenTrasera("resources/back/pikachu.png").agregarAtaquesIniciales("Impactrueno", 45).build());
        pokemonesBase.add(new PokemonBuilder().setId(11).setNombre("Raichu").setTipo(TipoPokemon.ELECTRICO).setHp(60).setDano(90).setDefensa(55).setVelocidad(110).setNivel(1).setNivelEvolucion(0).setNombreEvolucion("Ninguna").setImagenFrontal("resources/front/raichu.png").setImagenTrasera("resources/back/raichu.png").agregarAtaquesIniciales("Rayo", 60).build());

        pokemonesBase.add(new PokemonBuilder().setId(12).setNombre("Sandshrew").setTipo(TipoPokemon.TIERRA).setHp(50).setDano(75).setDefensa(85).setVelocidad(40).setNivel(1).setNivelEvolucion(22).setNombreEvolucion("Sandslash").setImagenFrontal("resources/front/sandshrew.png").setImagenTrasera("resources/back/sandshrew.png").agregarAtaquesIniciales("Golpe Arena", 45).build());
        pokemonesBase.add(new PokemonBuilder().setId(13).setNombre("Sandslash").setTipo(TipoPokemon.TIERRA).setHp(75).setDano(100).setDefensa(110).setVelocidad(65).setNivel(1).setNivelEvolucion(0).setNombreEvolucion("Ninguna").setImagenFrontal("resources/front/sandslash.png").setImagenTrasera("resources/back/sandslash.png").agregarAtaquesIniciales("Terremoto", 65).build());

        pokemonesBase.add(new PokemonBuilder().setId(14).setNombre("Oddish").setTipo(TipoPokemon.PLANTA).setHp(45).setDano(50).setDefensa(55).setVelocidad(30).setNivel(1).setNivelEvolucion(21).setNombreEvolucion("Gloom").setImagenFrontal("resources/front/oddish.png").setImagenTrasera("resources/back/oddish.png").agregarAtaquesIniciales("Hoja Navaja", 45).build());
        pokemonesBase.add(new PokemonBuilder().setId(15).setNombre("Gloom").setTipo(TipoPokemon.PLANTA).setHp(60).setDano(65).setDefensa(70).setVelocidad(40).setNivel(1).setNivelEvolucion(35).setNombreEvolucion("Vileplume").setImagenFrontal("resources/front/gloom.png").setImagenTrasera("resources/back/gloom.png").agregarAtaquesIniciales("Espora Verde", 55).build());
        pokemonesBase.add(new PokemonBuilder().setId(16).setNombre("Vileplume").setTipo(TipoPokemon.PLANTA).setHp(75).setDano(80).setDefensa(85).setVelocidad(50).setNivel(1).setNivelEvolucion(0).setNombreEvolucion("Ninguna").setImagenFrontal("resources/front/vileplume.png").setImagenTrasera("resources/back/vileplume.png").agregarAtaquesIniciales("Tormenta Floral", 65).build());

        pokemonesBase.add(new PokemonBuilder().setId(17).setNombre("Vulpix").setTipo(TipoPokemon.FUEGO).setHp(38).setDano(41).setDefensa(40).setVelocidad(65).setNivel(1).setNivelEvolucion(30).setNombreEvolucion("Ninetales").setImagenFrontal("resources/front/vulpix.png").setImagenTrasera("resources/back/vulpix.png").agregarAtaquesIniciales("Ascuas", 45).build());
        pokemonesBase.add(new PokemonBuilder().setId(18).setNombre("Ninetales").setTipo(TipoPokemon.FUEGO).setHp(73).setDano(76).setDefensa(75).setVelocidad(100).setNivel(1).setNivelEvolucion(0).setNombreEvolucion("Ninguna").setImagenFrontal("resources/front/ninetales.png").setImagenTrasera("resources/back/ninetales.png").agregarAtaquesIniciales("Infierno", 65).build());

        pokemonesBase.add(new PokemonBuilder().setId(19).setNombre("Psyduck").setTipo(TipoPokemon.AGUA).setHp(50).setDano(52).setDefensa(48).setVelocidad(55).setNivel(1).setNivelEvolucion(33).setNombreEvolucion("Golduck").setImagenFrontal("resources/front/psyduck.png").setImagenTrasera("resources/back/psyduck.png").agregarAtaquesIniciales("Pistola Agua", 45).build());
        pokemonesBase.add(new PokemonBuilder().setId(20).setNombre("Golduck").setTipo(TipoPokemon.AGUA).setHp(80).setDano(82).setDefensa(78).setVelocidad(85).setNivel(1).setNivelEvolucion(0).setNombreEvolucion("Ninguna").setImagenFrontal("resources/front/golduck.png").setImagenTrasera("resources/back/golduck.png").agregarAtaquesIniciales("Hidrobomba", 60).build());

        pokemonesBase.add(new PokemonBuilder().setId(21).setNombre("Geodude").setTipo(TipoPokemon.TIERRA).setHp(40).setDano(80).setDefensa(100).setVelocidad(20).setNivel(1).setNivelEvolucion(25).setNombreEvolucion("Graveler").setImagenFrontal("resources/front/geodude.png").setImagenTrasera("resources/back/geodude.png").agregarAtaquesIniciales("Golpe Arena", 45).build());
        pokemonesBase.add(new PokemonBuilder().setId(22).setNombre("Graveler").setTipo(TipoPokemon.TIERRA).setHp(55).setDano(95).setDefensa(115).setVelocidad(35).setNivel(1).setNivelEvolucion(40).setNombreEvolucion("Golem").setImagenFrontal("resources/front/graveler.png").setImagenTrasera("resources/back/graveler.png").agregarAtaquesIniciales("Avalancha", 55).build());
        pokemonesBase.add(new PokemonBuilder().setId(23).setNombre("Golem").setTipo(TipoPokemon.TIERRA).setHp(80).setDano(120).setDefensa(130).setVelocidad(45).setNivel(1).setNivelEvolucion(0).setNombreEvolucion("Ninguna").setImagenFrontal("resources/front/golem.png").setImagenTrasera("resources/back/golem.png").agregarAtaquesIniciales("Terremoto", 70).build());

        pokemonesBase.add(new PokemonBuilder().setId(24).setNombre("Magnemite").setTipo(TipoPokemon.ELECTRICO).setHp(25).setDano(35).setDefensa(70).setVelocidad(45).setNivel(1).setNivelEvolucion(30).setNombreEvolucion("Magneton").setImagenFrontal("resources/front/magnemite.png").setImagenTrasera("resources/back/magnemite.png").agregarAtaquesIniciales("Chispa", 45).build());
        pokemonesBase.add(new PokemonBuilder().setId(25).setNombre("Magneton").setTipo(TipoPokemon.ELECTRICO).setHp(50).setDano(60).setDefensa(95).setVelocidad(70).setNivel(1).setNivelEvolucion(0).setNombreEvolucion("Ninguna").setImagenFrontal("resources/front/magneton.png").setImagenTrasera("resources/back/magneton.png").agregarAtaquesIniciales("Rayo", 60).build());

        pokemonesBase.add(new PokemonBuilder().setId(26).setNombre("Voltorb").setTipo(TipoPokemon.ELECTRICO).setHp(40).setDano(30).setDefensa(50).setVelocidad(100).setNivel(1).setNivelEvolucion(30).setNombreEvolucion("Electrode").setImagenFrontal("resources/front/voltorb.png").setImagenTrasera("resources/back/voltorb.png").agregarAtaquesIniciales("Impactrueno", 45).build());
        pokemonesBase.add(new PokemonBuilder().setId(27).setNombre("Electrode").setTipo(TipoPokemon.ELECTRICO).setHp(60).setDano(50).setDefensa(70).setVelocidad(150).setNivel(1).setNivelEvolucion(0).setNombreEvolucion("Ninguna").setImagenFrontal("resources/front/electrode.png").setImagenTrasera("resources/back/electrode.png").agregarAtaquesIniciales("Voltio Cruel", 65).build());
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

    // Método para buscar un Pokémon base dentro de la pokedex por nombre
    public static Pokemon buscarPokemonBasePorNombre(String nombre) {
        for (Pokemon pokemonBase : pokemonesBase) {
            if (pokemonBase.getNombre().equalsIgnoreCase(nombre)) {
                return pokemonBase;
            }
        }
        return null;
    }

    // Método para crear un nuevo objeto Pokémon usando como referencia un Pokémon base de la pokedex
    public static Pokemon crearPokemon(int id, int nivel) {
        Pokemon pokemonBase = buscarPokemonBasePorId(id);

        if (pokemonBase == null) {
            Pokemon missingNo = new PokemonBuilder()
                    .setId(0)
                    .setNombre("MissingNo")
                    .setTipo(TipoPokemon.NORMAL)
                    .setHp(10)
                    .setDano(10)
                    .setDefensa(10)
                    .setVelocidad(10)
                    .setNivel(1)
                    .setNivelEvolucion(0)
                    .setNombreEvolucion("Ninguna")
                    .setImagenFrontal("resources/front/missingno.png")
                    .setImagenTrasera("resources/back/missingno.png")
                    .agregarAtaquesIniciales("Golpe básico", 30)
                    .build();

            missingNo.setNivel(nivel);
            missingNo.recalcularEstadisticasPorNivel();
            return missingNo;
        }

        Pokemon nuevoPokemon = new PokemonBuilder()
                .setId(pokemonBase.getId())
                .setNombre(pokemonBase.getNombre())
                .setTipo(pokemonBase.getTipo())
                .setHp(pokemonBase.getHpBase())
                .setDano(pokemonBase.getDanoBase())
                .setDefensa(pokemonBase.getDefensaBase())
                .setVelocidad(pokemonBase.getVelocidadBase())
                .setNivel(1)
                .setNivelEvolucion(pokemonBase.getNivelEvolucion())
                .setNombreEvolucion(pokemonBase.getNombreEvolucion())
                .setImagenFrontal(pokemonBase.getImagenFrontal())
                .setImagenTrasera(pokemonBase.getImagenTrasera())
                .setHabilidades(pokemonBase.getHabilidades())
                .build();

        nuevoPokemon.setNivel(nivel);
        nuevoPokemon.recalcularEstadisticasPorNivel();

        while (nuevoPokemon.puedeEvolucionar()) {
            nuevoPokemon.evolucionar();
        }

        nuevoPokemon.recalcularEstadisticasPorNivel();
        return nuevoPokemon;
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