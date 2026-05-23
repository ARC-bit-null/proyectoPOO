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

    // Variable para no dar experiencia dos veces
    private boolean experienciaOtorgada;

    public BatallaPvp(JugadorHumano jugadorHumano1, JugadorHumano jugadorHumano2) {
        super(jugadorHumano1, jugadorHumano2);
        this.inventarioJugador1 = new InventarioCombate();
        this.inventarioJugador2 = new InventarioCombate();
        this.indiceActivoJugador1 = 0;
        this.indiceActivoJugador2 = 0;
        this.cambioObligatorioJugador1 = false;
        this.cambioObligatorioJugador2 = false;
        this.mensajesRonda = new ArrayList<>();
        this.experienciaOtorgada = false;
    }

    // Metodo para obtener los mensajes generados en la ronda actual
    public ArrayList<String> getMensajesRonda() {
        return mensajesRonda;
    }

    // Metodo para limpiar los mensajes de la ronda
    public void limpiarMensajesRonda() {
        mensajesRonda.clear();
    }

    // Metodo para agregar mensajes a la ronda
    private void agregarMensaje(String mensaje) {
        mensajesRonda.add(mensaje);
    }

    @Override
    public Pokemon getPokemonActivoJugador1() {
        return jugador1.getEquipo().getPokemones().get(indiceActivoJugador1);
    }

    @Override
    public Pokemon getPokemonActivoJugador2() {
        return jugador2.getEquipo().getPokemones().get(indiceActivoJugador2);
    }

    @Override
    public int getIndiceActivoJugador1() {
        return indiceActivoJugador1;
    }

    @Override
    public int getIndiceActivoJugador2() {
        return indiceActivoJugador2;
    }

    @Override
    public InventarioCombate getInventarioJugador1() {
        return inventarioJugador1;
    }

    @Override
    public InventarioCombate getInventarioJugador2() {
        return inventarioJugador2;
    }

    @Override
    public boolean isCambioObligatorioJugador1() {
        return cambioObligatorioJugador1;
    }

    @Override
    public boolean isCambioObligatorioJugador2() {
        return cambioObligatorioJugador2;
    }

    @Override
    public boolean esModoPve() {
        return false;
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

        // Si ambos atacan resolvemos por velocidad
        if (accionJugador1 instanceof AccionAtacar && accionJugador2 instanceof AccionAtacar) {
            resolverAtaquesPorVelocidad();
        }
        // Si solo ataca J1
        else if (accionJugador1 instanceof AccionAtacar) {
            ejecutarAtaque((AccionAtacar) accionJugador1, true);
        }
        // Si solo ataca J2
        else if (accionJugador2 instanceof AccionAtacar) {
            ejecutarAtaque((AccionAtacar) accionJugador2, false);
        }

        revisarPokemonDerrotados();
        verificarFinBatalla();
        limpiarAcciones();
    }

    // Metodo para resolver acciones prioritarias
    private void resolverAccionPrioritaria(AccionCombate accion, boolean esJugador1) {
        if (accion instanceof AccionCambiarPokemon) {
            ejecutarCambio((AccionCambiarPokemon) accion, esJugador1);
        }

        if (accion instanceof AccionUsarObjeto) {
            usarObjeto((AccionUsarObjeto) accion, esJugador1);
        }
    }

    // Metodo para ejecutar cambio de pokemon
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

    // Metodo para usar un objeto del inventario
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
                pokemonObjetivo.aplicarMultiplicadorDano(1.5);
                agregarMensaje((esJugador1 ? "Jugador 1" : "Jugador 2") + " usó Banda especial en " + pokemonObjetivo.getNombre() + ". Su daño aumentó x1.5 durante el combate.");
            }
        }

        if (tipoObjeto == TipoObjeto.X_SPEED) {
            if (inventario.usarXSpeed()) {
                pokemonObjetivo.aplicarMultiplicadorVelocidad(1.5);
                agregarMensaje((esJugador1 ? "Jugador 1" : "Jugador 2") + " usó X Speed en " + pokemonObjetivo.getNombre() + ". Su velocidad aumentó x1.5 durante el combate.");
            }
        }
    }

    // Metodo para decidir quien ataca primero segun velocidad efectiva
    private void resolverAtaquesPorVelocidad() {
        Pokemon pokemonJ1 = getPokemonActivoJugador1();
        Pokemon pokemonJ2 = getPokemonActivoJugador2();

        if (pokemonJ1.getVelocidadEfectiva() >= pokemonJ2.getVelocidadEfectiva()) {
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

    // Metodo para ejecutar un ataque con la habilidad elegida
    private void ejecutarAtaque(AccionAtacar accionAtaque, boolean esJugador1) {
        Pokemon atacante = esJugador1 ? getPokemonActivoJugador1() : getPokemonActivoJugador2();
        Pokemon defensor = esJugador1 ? getPokemonActivoJugador2() : getPokemonActivoJugador1();
        Habilidad habilidad = accionAtaque.getHabilidadSeleccionada();

        if (atacante.estaDerrotado()) {
            agregarMensaje((esJugador1 ? "Jugador 1" : "Jugador 2") + " no pudo atacar porque su Pokémon está derrotado.");
            return;
        }

        if (habilidad == null) {
            agregarMensaje((esJugador1 ? "Jugador 1" : "Jugador 2") + " no pudo atacar porque no tiene una habilidad válida.");
            return;
        }

        int danio = CalculadoraDanio.calcular(atacante, defensor, habilidad);
        defensor.recibirDano(danio);

        agregarMensaje(atacante.getNombre() + " usó " + habilidad.getNombre() + " contra " + defensor.getNombre() + " e hizo " + danio + " de daño.");

        // Luego aplicamos el efecto especial de la habilidad
        habilidad.aplicarEfectoEspecial(atacante, defensor);

        if (defensor.estaDerrotado()) {
            agregarMensaje(defensor.getNombre() + " fue derrotado.");
        }
    }

    // Metodo para ver si algun pokemon activo cayó derrotado
    private void revisarPokemonDerrotados() {
        if (getPokemonActivoJugador1().estaDerrotado()) {
            cambioObligatorioJugador1 = true;
        }

        if (getPokemonActivoJugador2().estaDerrotado()) {
            cambioObligatorioJugador2 = true;
        }
    }

    // Metodo para dar xp al equipo ganador
    private void otorgarExperienciaEquipoGanador() {
        if (experienciaOtorgada || ganador == null) {
            return;
        }

        for (Pokemon pokemon : ganador.getEquipo().getPokemones()) {
            pokemon.ganarExperiencia(50);
            agregarMensaje(pokemon.getNombre() + " ganó 50 puntos de experiencia.");
        }

        experienciaOtorgada = true;
    }

    @Override
    public void verificarFinBatalla() {
        if (todosLosPokemonesDerrotados(jugador1.getEquipo().getPokemones())) {
            batallaTerminada = true;
            ganador = jugador2;
            agregarMensaje("Todos los Pokémon del Jugador 1 fueron derrotados.");
            agregarMensaje("¡Jugador 2 gana el combate!");
            otorgarExperienciaEquipoGanador();
            return;
        }

        if (todosLosPokemonesDerrotados(jugador2.getEquipo().getPokemones())) {
            batallaTerminada = true;
            ganador = jugador1;
            agregarMensaje("Todos los Pokémon del Jugador 2 fueron derrotados.");
            agregarMensaje("¡Jugador 1 gana el combate!");
            otorgarExperienciaEquipoGanador();
        }
    }

    // Metodo para revisar si todo el equipo ya cayó
    private boolean todosLosPokemonesDerrotados(ArrayList<Pokemon> equipo) {
        for (Pokemon pokemon : equipo) {
            if (!pokemon.estaDerrotado()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void forzarCambioJugador1(int nuevoIndice) {
        if (!batallaTerminada && cambioObligatorioJugador1) {
            indiceActivoJugador1 = nuevoIndice;
            cambioObligatorioJugador1 = false;
            agregarMensaje("Jugador 1 envió a " + getPokemonActivoJugador1().getNombre() + ".");
        }
    }

    @Override
    public void forzarCambioJugador2(int nuevoIndice) {
        if (!batallaTerminada && cambioObligatorioJugador2) {
            indiceActivoJugador2 = nuevoIndice;
            cambioObligatorioJugador2 = false;
            agregarMensaje("Jugador 2 envió a " + getPokemonActivoJugador2().getNombre() + ".");
        }
    }
}