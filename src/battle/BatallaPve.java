package src.battle;

import src.model.Habilidad;
import src.model.InventarioCombate;
import src.model.JugadorCPU;
import src.model.JugadorHumano;
import src.model.Pokemon;

import java.util.ArrayList;

// Esta clase representa un combate PvE entre un jugador humano y un jugador controlado por la CPU
public class BatallaPve extends Batalla {

    // Referencias tipadas para trabajar más fácil con cada jugador
    private JugadorHumano jugadorHumano;
    private JugadorCPU jugadorCPU;

    // Inventarios temporales del combate
    private InventarioCombate inventarioJugadorHumano;
    private InventarioCombate inventarioJugadorCPU;

    // Índices de los pokemon activos
    private int indiceActivoJugadorHumano;
    private int indiceActivoJugadorCPU;

    // Control de cambios obligatorios
    private boolean cambioObligatorioJugadorHumano;
    private boolean cambioObligatorioJugadorCPU;

    // Mensajes de la ronda
    private ArrayList<String> mensajesRonda;

    // Xp que da este rival si el jugador gana
    private int experienciaVictoria;

    // Para no dar xp 2 veces
    private boolean recompensaAplicada;

    public BatallaPve(JugadorHumano jugadorHumano, JugadorCPU jugadorCPU) {
        super(jugadorHumano, jugadorCPU);
        this.jugadorHumano = jugadorHumano;
        this.jugadorCPU = jugadorCPU;
        this.inventarioJugadorHumano = new InventarioCombate();
        this.inventarioJugadorCPU = new InventarioCombate();
        this.indiceActivoJugadorHumano = 0;
        this.indiceActivoJugadorCPU = 0;
        this.cambioObligatorioJugadorHumano = false;
        this.cambioObligatorioJugadorCPU = false;
        this.mensajesRonda = new ArrayList<>();
        this.experienciaVictoria = 0;
        this.recompensaAplicada = false;
    }

    // Metodo para guardar cuanta xp dará este combate
    public void setExperienciaVictoria(int experienciaVictoria) {
        this.experienciaVictoria = experienciaVictoria;
    }

    // Metodo para saber si ganó el jugador humano
    public boolean ganoJugadorHumano() {
        return batallaTerminada && ganador == jugadorHumano;
    }

    // Metodo para saber si ganó la CPU
    public boolean ganoJugadorCPU() {
        return batallaTerminada && ganador == jugadorCPU;
    }

    // Metodo para obtener los mensajes de la ronda
    public ArrayList<String> getMensajesRonda() {
        return mensajesRonda;
    }

    // Metodo para limpiar los mensajes de la ronda
    public void limpiarMensajesRonda() {
        mensajesRonda.clear();
    }

    // Metodo para agregar mensajes
    private void agregarMensaje(String mensaje) {
        mensajesRonda.add(mensaje);
    }

    // Metodo para obtener el pokemon activo del jugador humano
    public Pokemon getPokemonActivoJugadorHumano() {
        return jugadorHumano.getEquipo().getPokemones().get(indiceActivoJugadorHumano);
    }

    // Metodo para obtener el pokemon activo de la CPU
    public Pokemon getPokemonActivoJugadorCPU() {
        return jugadorCPU.getEquipo().getPokemones().get(indiceActivoJugadorCPU);
    }

    public int getIndiceActivoJugadorHumano() {
        return indiceActivoJugadorHumano;
    }

    public int getIndiceActivoJugadorCPU() {
        return indiceActivoJugadorCPU;
    }

    public InventarioCombate getInventarioJugadorHumano() {
        return inventarioJugadorHumano;
    }

    public InventarioCombate getInventarioJugadorCPU() {
        return inventarioJugadorCPU;
    }

    public boolean isCambioObligatorioJugadorHumano() {
        return cambioObligatorioJugadorHumano;
    }

    public boolean isCambioObligatorioJugadorCPU() {
        return cambioObligatorioJugadorCPU;
    }

    // Metodo para registrar la acción del jugador y hacer que la CPU decida automáticamente
    @Override
    public void registrarAccionJugador1(AccionCombate accion) {
        this.accionJugador1 = accion;

        if (!batallaTerminada && !cambioObligatorioJugadorCPU) {
            Pokemon pokemonCPU = getPokemonActivoJugadorCPU();
            Pokemon pokemonHumano = getPokemonActivoJugadorHumano();
            this.accionJugador2 = jugadorCPU.decidirAccion(pokemonCPU, pokemonHumano, inventarioJugadorCPU);
        }
    }

    @Override
    public void resolverRonda() {
        if (batallaTerminada) return;
        if (cambioObligatorioJugadorHumano || cambioObligatorioJugadorCPU) return;
        if (!accionesListas()) return;

        limpiarMensajesRonda();

        boolean accionHumanoPrioritaria = accionJugador1.esAccionPrioritaria();
        boolean accionCPUPrioritaria = accionJugador2.esAccionPrioritaria();

        if (accionHumanoPrioritaria && accionCPUPrioritaria) {
            resolverAccionPrioritaria(accionJugador1, true);
            verificarFinBatalla();
            if (batallaTerminada) return;

            resolverAccionPrioritaria(accionJugador2, false);
            verificarFinBatalla();
            if (batallaTerminada) return;
        } else if (accionHumanoPrioritaria) {
            resolverAccionPrioritaria(accionJugador1, true);
            verificarFinBatalla();
            if (batallaTerminada) return;
        } else if (accionCPUPrioritaria) {
            resolverAccionPrioritaria(accionJugador2, false);
            verificarFinBatalla();
            if (batallaTerminada) return;
        }

        if (accionJugador1 instanceof AccionAtacar && accionJugador2 instanceof AccionAtacar) {
            resolverAtaquesPorVelocidad();
        } else if (accionJugador1 instanceof AccionAtacar) {
            ejecutarAtaque((AccionAtacar) accionJugador1, true);
        } else if (accionJugador2 instanceof AccionAtacar) {
            ejecutarAtaque((AccionAtacar) accionJugador2, false);
        }

        revisarPokemonDerrotados();
        verificarFinBatalla();
        limpiarAcciones();
    }

    // Metodo para resolver acciones prioritarias
    private void resolverAccionPrioritaria(AccionCombate accion, boolean esHumano) {
        if (accion instanceof AccionCambiarPokemon) {
            ejecutarCambio((AccionCambiarPokemon) accion, esHumano);
        }

        if (accion instanceof AccionUsarObjeto) {
            usarObjeto((AccionUsarObjeto) accion, esHumano);
        }
    }

    // Metodo para hacer un cambio de pokemon
    private void ejecutarCambio(AccionCambiarPokemon accionCambio, boolean esHumano) {
        int nuevoIndice = accionCambio.getIndicePokemonCambio();

        if (esHumano) {
            Pokemon anterior = getPokemonActivoJugadorHumano();
            indiceActivoJugadorHumano = nuevoIndice;
            Pokemon nuevo = getPokemonActivoJugadorHumano();
            cambioObligatorioJugadorHumano = false;
            agregarMensaje("Jugador cambió a " + nuevo.getNombre() + " por " + anterior.getNombre() + ".");
        } else {
            Pokemon anterior = getPokemonActivoJugadorCPU();
            indiceActivoJugadorCPU = nuevoIndice;
            Pokemon nuevo = getPokemonActivoJugadorCPU();
            cambioObligatorioJugadorCPU = false;
            agregarMensaje("CPU cambió a " + nuevo.getNombre() + " por " + anterior.getNombre() + ".");
        }
    }

    // Metodo para usar objetos en combate
    private void usarObjeto(AccionUsarObjeto accionObjeto, boolean esHumano) {
        int indiceObjetivo = accionObjeto.getIndicePokemonObjetivo();
        TipoObjeto tipoObjeto = accionObjeto.getTipoObjeto();

        Pokemon pokemonObjetivo;
        InventarioCombate inventario;

        if (esHumano) {
            pokemonObjetivo = jugadorHumano.getEquipo().getPokemones().get(indiceObjetivo);
            inventario = inventarioJugadorHumano;
        } else {
            pokemonObjetivo = jugadorCPU.getEquipo().getPokemones().get(indiceObjetivo);
            inventario = inventarioJugadorCPU;
        }

        if (tipoObjeto == TipoObjeto.SPRAY_CURATIVO) {
            if (inventario.usarSprayCurativo()) {
                pokemonObjetivo.curarHp(20);
                agregarMensaje((esHumano ? "Jugador" : "CPU") + " usó Spray curativo en " + pokemonObjetivo.getNombre() + ".");
            }
        }

        if (tipoObjeto == TipoObjeto.REVIVIR) {
            if (inventario.usarRevivir()) {
                pokemonObjetivo.revivir();
                agregarMensaje((esHumano ? "Jugador" : "CPU") + " usó Revivir en " + pokemonObjetivo.getNombre() + ".");
            }
        }

        if (tipoObjeto == TipoObjeto.BANDA_ESPECIAL) {
            if (inventario.usarBandaEspecial()) {
                pokemonObjetivo.aplicarMultiplicadorDano(1.5);
                agregarMensaje((esHumano ? "Jugador" : "CPU") + " usó Banda especial en " + pokemonObjetivo.getNombre() + ". Su daño aumentó x1.5 durante el combate.");
            }
        }

        if (tipoObjeto == TipoObjeto.X_SPEED) {
            if (inventario.usarXSpeed()) {
                pokemonObjetivo.aplicarMultiplicadorVelocidad(1.5);
                agregarMensaje((esHumano ? "Jugador" : "CPU") + " usó X Speed en " + pokemonObjetivo.getNombre() + ". Su velocidad aumentó x1.5 durante el combate.");
            }
        }
    }

    // Metodo para resolver quien ataca primero
    private void resolverAtaquesPorVelocidad() {
        Pokemon pokemonHumano = getPokemonActivoJugadorHumano();
        Pokemon pokemonCPU = getPokemonActivoJugadorCPU();

        if (pokemonHumano.getVelocidadEfectiva() >= pokemonCPU.getVelocidadEfectiva()) {
            ejecutarAtaque((AccionAtacar) accionJugador1, true);

            if (!getPokemonActivoJugadorCPU().estaDerrotado()) {
                ejecutarAtaque((AccionAtacar) accionJugador2, false);
            }
        } else {
            ejecutarAtaque((AccionAtacar) accionJugador2, false);

            if (!getPokemonActivoJugadorHumano().estaDerrotado()) {
                ejecutarAtaque((AccionAtacar) accionJugador1, true);
            }
        }
    }

    // Metodo para hacer un ataque
    private void ejecutarAtaque(AccionAtacar accionAtaque, boolean esHumano) {
        Pokemon atacante = esHumano ? getPokemonActivoJugadorHumano() : getPokemonActivoJugadorCPU();
        Pokemon defensor = esHumano ? getPokemonActivoJugadorCPU() : getPokemonActivoJugadorHumano();
        Habilidad habilidad = accionAtaque.getHabilidadSeleccionada();

        if (atacante.estaDerrotado()) {
            agregarMensaje((esHumano ? "Jugador" : "CPU") + " no pudo atacar porque su Pokémon está derrotado.");
            return;
        }

        if (habilidad == null) {
            agregarMensaje((esHumano ? "Jugador" : "CPU") + " no pudo atacar porque no tiene una habilidad válida.");
            return;
        }

        int danio = CalculadoraDanio.calcular(atacante, defensor, habilidad);
        defensor.recibirDano(danio);

        agregarMensaje(atacante.getNombre() + " usó " + habilidad.getNombre() + " contra " + defensor.getNombre() + " e hizo " + danio + " de daño.");

        habilidad.aplicarEfectoEspecial(atacante, defensor);

        if (defensor.estaDerrotado()) {
            agregarMensaje(defensor.getNombre() + " fue derrotado.");
        }
    }

    // Metodo para revisar pokemones derrotados
    private void revisarPokemonDerrotados() {
        if (getPokemonActivoJugadorHumano().estaDerrotado()) {
            cambioObligatorioJugadorHumano = true;
        }

        if (getPokemonActivoJugadorCPU().estaDerrotado()) {
            cambioObligatorioJugadorCPU = true;
            forzarCambioCPU();
        }
    }

    // Metodo para hacer que la CPU cambie sola
    private void forzarCambioCPU() {
        if (todosLosPokemonesDerrotados(jugadorCPU.getEquipo().getPokemones())) {
            return;
        }

        int nuevoIndice = jugadorCPU.elegirIndiceSiguientePokemon();

        if (nuevoIndice != -1 && !jugadorCPU.getEquipo().getPokemones().get(nuevoIndice).estaDerrotado()) {
            indiceActivoJugadorCPU = nuevoIndice;
            cambioObligatorioJugadorCPU = false;
            agregarMensaje("CPU envió a " + getPokemonActivoJugadorCPU().getNombre() + ".");
        }
    }

    @Override
    public void verificarFinBatalla() {
        if (todosLosPokemonesDerrotados(jugadorHumano.getEquipo().getPokemones())) {
            batallaTerminada = true;
            ganador = jugadorCPU;
            agregarMensaje("Todos los Pokémon del jugador fueron derrotados.");
            agregarMensaje("¡La CPU gana el combate!");
            return;
        }

        if (todosLosPokemonesDerrotados(jugadorCPU.getEquipo().getPokemones())) {
            batallaTerminada = true;
            ganador = jugadorHumano;
            agregarMensaje("Todos los Pokémon de la CPU fueron derrotados.");
            agregarMensaje("¡El jugador gana el combate!");
            otorgarExperienciaEquipoJugador();
        }
    }

    // Metodo para dar xp al equipo del jugador cuando gana
    private void otorgarExperienciaEquipoJugador() {
        if (recompensaAplicada) {
            return;
        }

        for (Pokemon pokemon : jugadorHumano.getEquipo().getPokemones()) {
            pokemon.ganarExperiencia(experienciaVictoria);
            agregarMensaje(pokemon.getNombre() + " ganó " + experienciaVictoria + " puntos de experiencia.");
        }

        recompensaAplicada = true;
    }

    // Metodo para saber si todo el equipo cayó
    private boolean todosLosPokemonesDerrotados(ArrayList<Pokemon> equipo) {
        for (Pokemon pokemon : equipo) {
            if (!pokemon.estaDerrotado()) {
                return false;
            }
        }
        return true;
    }

    // Metodo para forzar el cambio del jugador humano
    public void forzarCambioJugadorHumano(int nuevoIndice) {
        if (!batallaTerminada && cambioObligatorioJugadorHumano) {
            indiceActivoJugadorHumano = nuevoIndice;
            cambioObligatorioJugadorHumano = false;
            agregarMensaje("Jugador envió a " + getPokemonActivoJugadorHumano().getNombre() + ".");
        }
    }

    @Override
    public Pokemon getPokemonActivoJugador1() {
        return getPokemonActivoJugadorHumano();
    }

    @Override
    public Pokemon getPokemonActivoJugador2() {
        return getPokemonActivoJugadorCPU();
    }

    @Override
    public int getIndiceActivoJugador1() {
        return indiceActivoJugadorHumano;
    }

    @Override
    public int getIndiceActivoJugador2() {
        return indiceActivoJugadorCPU;
    }

    @Override
    public InventarioCombate getInventarioJugador1() {
        return inventarioJugadorHumano;
    }

    @Override
    public InventarioCombate getInventarioJugador2() {
        return inventarioJugadorCPU;
    }

    @Override
    public boolean isCambioObligatorioJugador1() {
        return cambioObligatorioJugadorHumano;
    }

    @Override
    public boolean isCambioObligatorioJugador2() {
        return cambioObligatorioJugadorCPU;
    }

    @Override
    public void forzarCambioJugador1(int nuevoIndice) {
        forzarCambioJugadorHumano(nuevoIndice);
    }

    @Override
    public void forzarCambioJugador2(int nuevoIndice) {
        // En PvE la CPU cambia sola
    }

    @Override
    public boolean esModoPve() {
        return true;
    }
}