package src.model;

import src.battle.AccionAtacar;
import src.battle.AccionCombate;
import src.battle.AccionUsarObjeto;
import src.battle.SistemaTipos;
import src.battle.TipoObjeto;

import java.util.Random;

// Esta clase representa a un jugador controlado por la computadora
public class JugadorCPU extends Jugador {

    private Random random;

    public JugadorCPU(String nombre, Pokemon pokemonInicial) {
        super(nombre, pokemonInicial);
        this.random = new Random();
    }

    // Metodo principal para decidir la acción de la CPU durante el combate
    public AccionCombate decidirAccion(Pokemon pokemonActivo, Pokemon pokemonRival, InventarioCombate inventario) {
        if (pokemonActivo == null || pokemonActivo.estaDerrotado()) {
            return null;
        }

        AccionCombate accionObjeto = intentarUsarObjeto(pokemonActivo, inventario);
        if (accionObjeto != null) {
            return accionObjeto;
        }

        Habilidad habilidadElegida = seleccionarAtaqueSegunContexto(pokemonActivo, pokemonRival);
        return new AccionAtacar(this, habilidadElegida);
    }

    // Metodo para intentar usar un objeto de forma estratégica y aleatoria
    private AccionCombate intentarUsarObjeto(Pokemon pokemonActivo, InventarioCombate inventario) {
        int hpActual = pokemonActivo.getHp();
        int hpMaximo = pokemonActivo.getHpMax();
        double porcentajeVida = (double) hpActual / hpMaximo;

        if (!pokemonActivo.estaDerrotado() && porcentajeVida <= 0.30 && inventario.puedeUsarSprayCurativo()) {
            if (random.nextInt(100) < 35) {
                int indiceObjetivo = this.getEquipo().getPokemones().indexOf(pokemonActivo);
                return new AccionUsarObjeto(this, TipoObjeto.SPRAY_CURATIVO, indiceObjetivo);
            }
        }

        if (inventario.puedeUsarRevivir()) {
            int indiceRevivir = elegirIndicePokemonDerrotado();
            if (indiceRevivir != -1 && random.nextInt(100) < 30) {
                return new AccionUsarObjeto(this, TipoObjeto.REVIVIR, indiceRevivir);
            }
        }

        if (!pokemonActivo.estaDerrotado() && inventario.puedeUsarBandaEspecial()) {
            if (random.nextInt(100) < 20) {
                int indiceObjetivo = this.getEquipo().getPokemones().indexOf(pokemonActivo);
                return new AccionUsarObjeto(this, TipoObjeto.BANDA_ESPECIAL, indiceObjetivo);
            }
        }

        if (!pokemonActivo.estaDerrotado() && inventario.puedeUsarXSpeed()) {
            if (random.nextInt(100) < 20) {
                int indiceObjetivo = this.getEquipo().getPokemones().indexOf(pokemonActivo);
                return new AccionUsarObjeto(this, TipoObjeto.X_SPEED, indiceObjetivo);
            }
        }

        return null;
    }

    // Metodo para elegir el ataque según la ventaja o desventaja de tipos
    public Habilidad seleccionarAtaqueSegunContexto(Pokemon pokemonActivo, Pokemon pokemonRival) {
        Habilidad ataqueBasico = pokemonActivo.getAtaqueBasico();
        Habilidad ataqueTipo = pokemonActivo.getHabilidadPorTipo(pokemonActivo.getTipo());

        if (ataqueBasico == null && ataqueTipo == null) {
            return null;
        }

        double efectividad = SistemaTipos.obtenerEfectividad(pokemonActivo.getTipo(), pokemonRival.getTipo());

        if (efectividad > 1.0) {
            if (ataqueTipo != null) return ataqueTipo;
            if (ataqueBasico != null) return ataqueBasico;
        }

        if (efectividad == 1.0) {
            if (ataqueBasico != null && ataqueTipo != null) {
                return random.nextBoolean() ? ataqueBasico : ataqueTipo;
            }
            if (ataqueTipo != null) return ataqueTipo;
            if (ataqueBasico != null) return ataqueBasico;
        }

        if (efectividad < 1.0) {
            if (ataqueBasico != null) return ataqueBasico;
            if (ataqueTipo != null) return ataqueTipo;
        }

        return ataqueBasico != null ? ataqueBasico : ataqueTipo;
    }

    // Metodo para elegir el índice del siguiente pokemon vivo
    public int elegirIndiceSiguientePokemon() {
        for (int i = 0; i < this.getEquipo().getPokemones().size(); i++) {
            Pokemon pokemon = this.getEquipo().getPokemones().get(i);
            if (!pokemon.estaDerrotado()) {
                return i;
            }
        }
        return -1;
    }

    // Metodo para buscar un pokemon derrotado y usar Revivir sobre él
    public int elegirIndicePokemonDerrotado() {
        for (int i = 0; i < this.getEquipo().getPokemones().size(); i++) {
            Pokemon pokemon = this.getEquipo().getPokemones().get(i);
            if (pokemon.estaDerrotado()) {
                return i;
            }
        }
        return -1;
    }
}