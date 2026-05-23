package src.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

// Esta clase guarda toda la configuración del modo PvE
public class PveManager {

    // Aqui guardamos los 5 rivales de cada modo
    private static final Map<ModoPve, ArrayList<RivalPve>> rivalesPorModo = new HashMap<>();

    static {
        inicializarRivales();
    }

    // Metodo para cargar una sola vez todos los rivales del PvE
    private static void inicializarRivales() {
        if (!rivalesPorModo.isEmpty()) return;

        // -------------------------
        // MODO FACIL
        // -------------------------
        ArrayList<RivalPve> rivalesFacil = new ArrayList<>();
        rivalesFacil.add(new RivalPve(ModoPve.FACIL, 1, 5, 100, crearListaIds(10)));                 // Pikachu
        rivalesFacil.add(new RivalPve(ModoPve.FACIL, 2, 8, 150, crearListaIds(1, 4)));               // Bulbasaur, Charmander
        rivalesFacil.add(new RivalPve(ModoPve.FACIL, 3, 10, 225, crearListaIds(7, 12)));             // Squirtle, Sandshrew
        rivalesFacil.add(new RivalPve(ModoPve.FACIL, 4, 12, 338, crearListaIds(1, 4, 7)));           // Bulbasaur, Charmander, Squirtle
        rivalesFacil.add(new RivalPve(ModoPve.FACIL, 5, 15, 505, crearListaIds(10, 14, 21)));        // Pikachu, Oddish, Geodude

        // -------------------------
        // MODO NORMAL
        // -------------------------
        ArrayList<RivalPve> rivalesNormal = new ArrayList<>();
        rivalesNormal.add(new RivalPve(ModoPve.NORMAL, 1, 18, 505, crearListaIds(2, 8, 10, 12)));    // Ivysaur, Wartortle, Pikachu, Sandshrew
        rivalesNormal.add(new RivalPve(ModoPve.NORMAL, 2, 20, 758, crearListaIds(5, 19, 24, 14)));   // Charmeleon, Psyduck, Magnemite, Oddish
        rivalesNormal.add(new RivalPve(ModoPve.NORMAL, 3, 23, 1137, crearListaIds(2, 5, 8, 21)));    // Ivysaur, Charmeleon, Wartortle, Geodude
        rivalesNormal.add(new RivalPve(ModoPve.NORMAL, 4, 25, 1706, crearListaIds(15, 18, 20, 25))); // Gloom, Ninetales, Golduck, Magneton
        rivalesNormal.add(new RivalPve(ModoPve.NORMAL, 5, 28, 2559, crearListaIds(5, 8, 15, 11, 22)));// Charmeleon, Wartortle, Gloom, Raichu, Graveler

        // -------------------------
        // MODO DIFICIL
        // -------------------------
        ArrayList<RivalPve> rivalesDificil = new ArrayList<>();
        rivalesDificil.add(new RivalPve(ModoPve.DIFICIL, 1, 30, 1500, crearListaIds(3, 5, 9, 11, 13, 17)));   // Venusaur, Charmeleon, Blastoise, Raichu, Sandslash, Vulpix
        rivalesDificil.add(new RivalPve(ModoPve.DIFICIL, 2, 32, 2250, crearListaIds(2, 18, 20, 25, 22, 15))); // Ivysaur, Ninetales, Golduck, Magneton, Graveler, Gloom
        rivalesDificil.add(new RivalPve(ModoPve.DIFICIL, 3, 35, 3375, crearListaIds(3, 6, 8, 11, 13, 16)));   // Venusaur, Charizard, Wartortle, Raichu, Sandslash, Vileplume
        rivalesDificil.add(new RivalPve(ModoPve.DIFICIL, 4, 38, 5063, crearListaIds(6, 9, 3, 25, 23, 18)));   // Charizard, Blastoise, Venusaur, Magneton, Golem, Ninetales
        rivalesDificil.add(new RivalPve(ModoPve.DIFICIL, 5, 40, 7595, crearListaIds(3, 6, 9, 11, 23, 16)));   // Venusaur, Charizard, Blastoise, Raichu, Golem, Vileplume

        rivalesPorModo.put(ModoPve.FACIL, rivalesFacil);
        rivalesPorModo.put(ModoPve.NORMAL, rivalesNormal);
        rivalesPorModo.put(ModoPve.DIFICIL, rivalesDificil);
    }

    // Metodo auxiliar para crear listas de ids más rápido
    private static ArrayList<Integer> crearListaIds(int... ids) {
        ArrayList<Integer> lista = new ArrayList<>();

        for (int id : ids) {
            lista.add(id);
        }

        return lista;
    }

    // Metodo para obtener todos los rivales de un modo
    public static ArrayList<RivalPve> obtenerRivalesPorModo(ModoPve modo) {
        ArrayList<RivalPve> rivales = rivalesPorModo.get(modo);

        if (rivales == null) {
            return new ArrayList<>();
        }

        return new ArrayList<>(rivales);
    }

    // Metodo para obtener un rival concreto por modo y número
    public static RivalPve obtenerRival(ModoPve modo, int numeroRival) {
        ArrayList<RivalPve> rivales = rivalesPorModo.get(modo);

        if (rivales == null) {
            return null;
        }

        for (RivalPve rival : rivales) {
            if (rival.getNumeroRival() == numeroRival) {
                return rival;
            }
        }

        return null;
    }

    // Metodo para construir el jugador CPU con el equipo del rival elegido
    public static JugadorCPU crearJugadorCPU(ModoPve modo, int numeroRival) {
        RivalPve rival = obtenerRival(modo, numeroRival);

        if (rival == null || rival.getIdsPokemon().isEmpty()) {
            return null;
        }

        ArrayList<Integer> idsPokemon = rival.getIdsPokemon();
        Pokemon pokemonInicial = Pokedex.crearPokemon(idsPokemon.get(0), rival.getNivel());

        JugadorCPU jugadorCPU = new JugadorCPU("CPU", pokemonInicial);

        for (int i = 1; i < idsPokemon.size(); i++) {
            Pokemon pokemonEquipo = Pokedex.crearPokemon(idsPokemon.get(i), rival.getNivel());
            jugadorCPU.getEquipo().agregarPokemon(pokemonEquipo);
        }

        return jugadorCPU;
    }

    // Metodo para obtener la xp que da un rival concreto
    public static int obtenerExperienciaRival(ModoPve modo, int numeroRival) {
        RivalPve rival = obtenerRival(modo, numeroRival);

        if (rival == null) {
            return 0;
        }

        return rival.getExperienciaOtorgada();
    }

    // Metodo para saber si un rival existe dentro del modo
    public static boolean existeRival(ModoPve modo, int numeroRival) {
        return obtenerRival(modo, numeroRival) != null;
    }
}