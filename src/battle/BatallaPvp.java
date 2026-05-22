package src.battle;

import src.model.Habilidad;
import src.model.InventarioCombate;
import src.model.JugadorHumano;
import src.model.Pokemon;

import java.util.ArrayList;

// Esta clase representa un combate PvP entre dos jugadores humanos
public class BatallaPvp extends Batalla {

    // Guardamos los inventarios temporales de combate de ambos jugadores
    private InventarioCombate inventarioJugador1;
    private InventarioCombate inventarioJugador2;

    // Guardamos el índice del pokemon activo de cada jugador
    private int indiceActivoJugador1;
    private int indiceActivoJugador2;

    // Guardamos si alguno de los jugadores está obligado a cambiar
    private boolean cambioObligatorioJugador1;
    private boolean cambioObligatorioJugador2;

    // Lista de mensajes generados durante la ronda
    private ArrayList<String> mensajesRonda;

    public BatallaPvp(JugadorHumano jugadorHumano1, JugadorHumano jugadorHumano2) {
        super(jugadorHumano1, jugadorHumano2);
        this.inventarioJugador1 = new InventarioCombate();
        this.inventarioJugador2 = new InventarioCombate();
        this.indiceActivoJugador1 = 0;
        this.indiceActivoJugador2 = 0;
        this.cambioObligatorioJugador1 = false;
        this.cambioObligatorioJugador2 = false;
        this.mensajesRonda = new ArrayList<>();
    }

    // Metodo para obtener los mensajes generados en la ronda actual
    public ArrayList<String> getMensajesRonda() {
        return mensajesRonda;
    }

    // Metodo para limpiar los mensajes de la ronda
    public void limpiarMensajesRonda() {
        mensajesRonda.clear();
    }

    // Metodo para agregar un mensaje al registro de la ronda
    private void agregarMensaje(String mensaje) {
        mensajesRonda.add(mensaje);
    }

    // Metodo para obtener el pokemon activo del Jugador 1
    public Pokemon getPokemonActivoJugador1() {
        return jugador1.getEquipo().getPokemones().get(indiceActivoJugador1);
    }

    // Metodo para obtener el pokemon activo del Jugador 2
    public Pokemon getPokemonActivoJugador2() {
        return jugador2.getEquipo().getPokemones().get(indiceActivoJugador2);
    }

    public int getIndiceActivoJugador1() {
        return indiceActivoJugador1;
    }

    public int getIndiceActivoJugador2() {
        return indiceActivoJugador2;
    }

    public InventarioCombate getInventarioJugador1() {
        return inventarioJugador1;
    }

    public InventarioCombate getInventarioJugador2() {
        return inventarioJugador2;
    }

    public boolean isCambioObligatorioJugador1() {
        return cambioObligatorioJugador1;
    }

    public boolean isCambioObligatorioJugador2() {
        return cambioObligatorioJugador2;
    }

    @Override
    public void resolverRonda() {
        if (batallaTerminada) return;
        if (!accionesListas()) return;

        limpiarMensajesRonda();

        // Primero resolvemos las acciones prioritarias del Jugador 1
        if (accionJugador1.esAccionPrioritaria()) {
            resolverAccionPrioritaria(accionJugador1, true);
            verificarFinBatalla();
            if (batallaTerminada) return;
        }

        // Luego resolvemos las acciones prioritarias del Jugador 2
        if (accionJugador2.esAccionPrioritaria()) {
            resolverAccionPrioritaria(accionJugador2, false);
            verificarFinBatalla();
            if (batallaTerminada) return;
        }

        // Si ambos eligieron atacar, resolvemos por velocidad
        if (accionJugador1 instanceof AccionAtacar && accionJugador2 instanceof AccionAtacar) {
            resolverAtaquesPorVelocidad();
        }
        // Si solo el Jugador 1 atacó
        else if (accionJugador1 instanceof AccionAtacar) {
            ejecutarAtaque((AccionAtacar) accionJugador1, true);
        }
        // Si solo el Jugador 2 atacó
        else if (accionJugador2 instanceof AccionAtacar) {
            ejecutarAtaque((AccionAtacar) accionJugador2, false);
        }

        revisarPokemonDerrotados();
        verificarFinBatalla();
        limpiarAcciones();
    }

    // Metodo para resolver una acción prioritaria
    private void resolverAccionPrioritaria(AccionCombate accion, boolean esJugador1) {
        if (accion instanceof AccionCambiarPokemon) {
            ejecutarCambio((AccionCambiarPokemon) accion, esJugador1);
        }

        if (accion instanceof AccionUsarObjeto) {
            usarObjeto((AccionUsarObjeto) accion, esJugador1);
        }
    }

    // Metodo para ejecutar un cambio de pokemon
    private void ejecutarCambio(AccionCambiarPokemon accionCambio, boolean esJugador1) {
        int nuevoIndice = accionCambio.getIndicePokemonCambio();

        if (esJugador1) {
            Pokemon anterior = getPokemonActivoJugador1();
            indiceActivoJugador1 = nuevoIndice;
            Pokemon nuevo = getPokemonActivoJugador1();
            cambioObligatorioJugador1 = false;
            agregarMensaje("Jugador 1 cambió a " + nuevo.getNombre() + " por " + anterior.getNombre() + ".");
        } else {
            Pokemon anterior = getPokemonActivoJugador2();
            indiceActivoJugador2 = nuevoIndice;
            Pokemon nuevo = getPokemonActivoJugador2();
            cambioObligatorioJugador2 = false;
            agregarMensaje("Jugador 2 cambió a " + nuevo.getNombre() + " por " + anterior.getNombre() + ".");
        }
    }

    // Metodo para usar un objeto de combate
    private void usarObjeto(AccionUsarObjeto accionObjeto, boolean esJugador1) {
        int indiceObjetivo = accionObjeto.getIndicePokemonObjetivo();
        TipoObjeto tipoObjeto = accionObjeto.getTipoObjeto();

        Pokemon pokemonObjetivo;
        InventarioCombate inventario;

        if (esJugador1) {
            pokemonObjetivo = jugador1.getEquipo().getPokemones().get(indiceObjetivo);
            inventario = inventarioJugador1;
        } else {
            pokemonObjetivo = jugador2.getEquipo().getPokemones().get(indiceObjetivo);
            inventario = inventarioJugador2;
        }

        if (tipoObjeto == TipoObjeto.SPRAY_CURATIVO) {
            if (inventario.usarSprayCurativo()) {
                pokemonObjetivo.curarHp(20);
                agregarMensaje((esJugador1 ? "Jugador 1" : "Jugador 2") + " usó Spray curativo en " + pokemonObjetivo.getNombre() + ".");
            }
        }

        if (tipoObjeto == TipoObjeto.REVIVIR) {
            if (inventario.usarRevivir()) {
                pokemonObjetivo.revivir();
                agregarMensaje((esJugador1 ? "Jugador 1" : "Jugador 2") + " usó Revivir en " + pokemonObjetivo.getNombre() + ".");
            }
        }

        if (tipoObjeto == TipoObjeto.BANDA_ESPECIAL) {
            if (inventario.usarBandaEspecial()) {
                pokemonObjetivo.aumentarDano(10);
                agregarMensaje((esJugador1 ? "Jugador 1" : "Jugador 2") + " usó Banda especial en " + pokemonObjetivo.getNombre() + ". Su daño aumentó.");
            }
        }

        if (tipoObjeto == TipoObjeto.X_SPEED) {
            if (inventario.usarXSpeed()) {
                pokemonObjetivo.aumentarVelocidad(10);
                agregarMensaje((esJugador1 ? "Jugador 1" : "Jugador 2") + " usó X Speed en " + pokemonObjetivo.getNombre() + ". Su velocidad aumentó.");
            }
        }
    }

    // Metodo para resolver los ataques cuando ambos jugadores atacan
    private void resolverAtaquesPorVelocidad() {
        Pokemon pokemonJ1 = getPokemonActivoJugador1();
        Pokemon pokemonJ2 = getPokemonActivoJugador2();

        // Si el pokemon del Jugador 1 es más rápido o empatan, ataca primero
        if (pokemonJ1.getVelocidad() >= pokemonJ2.getVelocidad()) {
            ejecutarAtaque((AccionAtacar) accionJugador1, true);

            if (!getPokemonActivoJugador2().estaDerrotado()) {
                ejecutarAtaque((AccionAtacar) accionJugador2, false);
            }
        } else {
            ejecutarAtaque((AccionAtacar) accionJugador2, false);

            if (!getPokemonActivoJugador1().estaDerrotado()) {
                ejecutarAtaque((AccionAtacar) accionJugador1, true);
            }
        }
    }

    // Metodo para ejecutar un ataque usando una habilidad concreta
    private void ejecutarAtaque(AccionAtacar accionAtaque, boolean esJugador1) {
        Pokemon atacante = esJugador1 ? getPokemonActivoJugador1() : getPokemonActivoJugador2();
        Pokemon defensor = esJugador1 ? getPokemonActivoJugador2() : getPokemonActivoJugador1();
        Habilidad habilidad = accionAtaque.getHabilidadSeleccionada();

        if (atacante.estaDerrotado()) {
            agregarMensaje((esJugador1 ? "Jugador 1" : "Jugador 2") + " no pudo atacar porque su Pokémon está derrotado.");
            return;
        }

        int danio = CalculadoraDanio.calcular(atacante, defensor, habilidad);
        defensor.recibirDano(danio);

        agregarMensaje(atacante.getNombre() + " usó " + habilidad.getNombre() + " contra " + defensor.getNombre() + " e hizo " + danio + " de daño.");

        // Aplicamos el efecto especial de la habilidad después del daño
        habilidad.aplicarEfectoEspecial(atacante, defensor);

        if (defensor.estaDerrotado()) {
            agregarMensaje(defensor.getNombre() + " fue derrotado.");
        }
    }

    // Metodo para revisar si alguno de los pokemones activos fue derrotado
    private void revisarPokemonDerrotados() {
        if (getPokemonActivoJugador1().estaDerrotado()) {
            cambioObligatorioJugador1 = true;
        }

        if (getPokemonActivoJugador2().estaDerrotado()) {
            cambioObligatorioJugador2 = true;
        }
    }

    @Override
    public void verificarFinBatalla() {
        if (todosLosPokemonesDerrotados(jugador1.getEquipo().getPokemones())) {
            batallaTerminada = true;
            ganador = jugador2;
            agregarMensaje("Todos los Pokémon del Jugador 1 fueron derrotados.");
            agregarMensaje("¡Jugador 2 gana el combate!");
            return;
        }

        if (todosLosPokemonesDerrotados(jugador2.getEquipo().getPokemones())) {
            batallaTerminada = true;
            ganador = jugador1;
            agregarMensaje("Todos los Pokémon del Jugador 2 fueron derrotados.");
            agregarMensaje("¡Jugador 1 gana el combate!");
        }
    }

    // Metodo para saber si todos los pokemones de un equipo están derrotados
    private boolean todosLosPokemonesDerrotados(ArrayList<Pokemon> equipo) {
        for (Pokemon pokemon : equipo) {
            if (!pokemon.estaDerrotado()) {
                return false;
            }
        }
        return true;
    }

    // Metodo para forzar manualmente el cambio del Jugador 1 después de quedar derrotado
    public void forzarCambioJugador1(int nuevoIndice) {
        if (!batallaTerminada && cambioObligatorioJugador1) {
            indiceActivoJugador1 = nuevoIndice;
            cambioObligatorioJugador1 = false;
            agregarMensaje("Jugador 1 envió a " + getPokemonActivoJugador1().getNombre() + ".");
        }
    }

    // Metodo para forzar manualmente el cambio del Jugador 2 después de quedar derrotado
    public void forzarCambioJugador2(int nuevoIndice) {
        if (!batallaTerminada && cambioObligatorioJugador2) {
            indiceActivoJugador2 = nuevoIndice;
            cambioObligatorioJugador2 = false;
            agregarMensaje("Jugador 2 envió a " + getPokemonActivoJugador2().getNombre() + ".");
        }
    }
}