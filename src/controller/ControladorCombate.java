package src.controller;

import src.battle.*;
import src.model.Habilidad;
import src.model.InventarioCombate;
import src.model.Pokemon;
import src.view.VentanaCombate;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class ControladorCombate {

    protected VentanaCombate ventana;
    protected Batalla batalla;

    public ControladorCombate(VentanaCombate ventana, Batalla batalla) {
        this.ventana = ventana;
        this.batalla = batalla;

        configurarVentana();
        inicializarEventos();
        actualizarVistaCompleta();
        mostrarMensajesIniciales();
    }

    // Metodo para configurar detalles iniciales de la ventana
    private void configurarVentana() {
        ventana.setTituloCombate(batalla.esModoPve() ? "Combate PvE" : "Combate PvP");
        ventana.setModoPveVisual(batalla.esModoPve());

        if (ventana.getVentanaAnterior() != null) {
            ventana.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    ventana.getVentanaAnterior().setVisible(true);
                }
            });
        }
    }

    // Metodo para conectar botones con acciones
    private void inicializarEventos() {
        ventana.getBtnAtacarJ1().addActionListener(e -> seleccionarAtaqueJugador1());
        ventana.getBtnMochilaJ1().addActionListener(e -> seleccionarMochilaJugador1());
        ventana.getBtnCambioJ1().addActionListener(e -> seleccionarCambioJugador1());

        if (!batalla.esModoPve()) {
            ventana.getBtnAtacarJ2().addActionListener(e -> seleccionarAtaqueJugador2());
            ventana.getBtnMochilaJ2().addActionListener(e -> seleccionarMochilaJugador2());
            ventana.getBtnCambioJ2().addActionListener(e -> seleccionarCambioJugador2());
        }
    }

    // Metodo para mostrar mensajes iniciales
    private void mostrarMensajesIniciales() {
        ventana.agregarMensaje(batalla.esModoPve() ? "¡Comienza el combate PvE!" : "¡Comienza el combate PvP!");
        ventana.agregarMensaje("Jugador 1 inicia con " + batalla.getPokemonActivoJugador1().getNombre() + ".");
        ventana.agregarMensaje((batalla.esModoPve() ? "CPU" : "Jugador 2") + " inicia con " + batalla.getPokemonActivoJugador2().getNombre() + ".");
    }

    private void seleccionarAtaqueJugador1() {
        if (batalla.isBatallaTerminada()) return;
        if (batalla.isCambioObligatorioJugador1()) return;

        Habilidad habilidadElegida = seleccionarHabilidad(
                batalla.getPokemonActivoJugador1(),
                "Ataque Jugador 1"
        );

        if (habilidadElegida == null) return;

        batalla.registrarAccionJugador1(new AccionAtacar(batalla.getJugador1(), habilidadElegida));
        ventana.agregarMensaje("Jugador 1 eligió atacar con " + habilidadElegida.getNombre() + ".");

        intentarResolverRonda();
    }

    private void seleccionarAtaqueJugador2() {
        if (batalla.esModoPve()) return;
        if (batalla.isBatallaTerminada()) return;
        if (batalla.isCambioObligatorioJugador2()) return;

        Habilidad habilidadElegida = seleccionarHabilidad(
                batalla.getPokemonActivoJugador2(),
                "Ataque Jugador 2"
        );

        if (habilidadElegida == null) return;

        batalla.registrarAccionJugador2(new AccionAtacar(batalla.getJugador2(), habilidadElegida));
        ventana.agregarMensaje("Jugador 2 eligió atacar con " + habilidadElegida.getNombre() + ".");

        intentarResolverRonda();
    }

    private Habilidad seleccionarHabilidad(Pokemon pokemonActivo, String titulo) {
        ArrayList<Habilidad> habilidades = pokemonActivo.getHabilidades();

        if (habilidades == null || habilidades.isEmpty()) {
            ventana.mostrarDialogo("Este Pokémon no tiene habilidades disponibles.");
            return null;
        }

        String[] opciones = new String[habilidades.size()];
        for (int i = 0; i < habilidades.size(); i++) {
            opciones[i] = habilidades.get(i).getNombre();
        }

        String seleccion = (String) JOptionPane.showInputDialog(
                ventana,
                "Selecciona un ataque:",
                titulo,
                JOptionPane.PLAIN_MESSAGE,
                null,
                opciones,
                opciones[0]
        );

        if (seleccion == null) return null;

        for (Habilidad habilidad : habilidades) {
            if (habilidad.getNombre().equals(seleccion)) {
                return habilidad;
            }
        }

        return null;
    }

    private void seleccionarMochilaJugador1() {
        if (batalla.isBatallaTerminada()) return;
        if (batalla.isCambioObligatorioJugador1()) return;

        AccionUsarObjeto accion = construirAccionObjetoJugador1();
        if (accion == null) return;

        batalla.registrarAccionJugador1(accion);
        ventana.agregarMensaje("Jugador 1 eligió usar un objeto.");

        intentarResolverRonda();
    }

    private void seleccionarMochilaJugador2() {
        if (batalla.esModoPve()) return;
        if (batalla.isBatallaTerminada()) return;
        if (batalla.isCambioObligatorioJugador2()) return;

        AccionUsarObjeto accion = construirAccionObjetoJugador2();
        if (accion == null) return;

        batalla.registrarAccionJugador2(accion);
        ventana.agregarMensaje("Jugador 2 eligió usar un objeto.");

        intentarResolverRonda();
    }

    private AccionUsarObjeto construirAccionObjetoJugador1() {
        String seleccion = ventana.seleccionarObjeto("Mochila Jugador 1");
        if (seleccion == null) return null;

        return construirAccionObjeto(
                batalla.getJugador1(),
                batalla.getInventarioJugador1(),
                batalla.getPokemonActivoJugador1(),
                batalla.getIndiceActivoJugador1(),
                batalla.getJugador1().getEquipo().getPokemones(),
                seleccion,
                "Jugador 1"
        );
    }

    private AccionUsarObjeto construirAccionObjetoJugador2() {
        String seleccion = ventana.seleccionarObjeto("Mochila Jugador 2");
        if (seleccion == null) return null;

        return construirAccionObjeto(
                batalla.getJugador2(),
                batalla.getInventarioJugador2(),
                batalla.getPokemonActivoJugador2(),
                batalla.getIndiceActivoJugador2(),
                batalla.getJugador2().getEquipo().getPokemones(),
                seleccion,
                "Jugador 2"
        );
    }

    private AccionUsarObjeto construirAccionObjeto(src.model.Jugador jugador, InventarioCombate inventario,
                                                   Pokemon pokemonActivo, int indiceActivo,
                                                   ArrayList<Pokemon> equipo, String seleccion, String nombreJugador) {
        int indiceObjetivo = -1;
        TipoObjeto tipoObjeto = null;

        if (seleccion.equals("Spray curativo")) {
            if (!inventario.puedeUsarSprayCurativo()) {
                ventana.mostrarDialogo(nombreJugador + " ya no tiene Spray curativo.");
                return null;
            }

            if (pokemonActivo.estaDerrotado()) {
                ventana.mostrarDialogo("No puedes usar Spray curativo sobre un Pokémon derrotado.");
                return null;
            }

            tipoObjeto = TipoObjeto.SPRAY_CURATIVO;
            indiceObjetivo = indiceActivo;
        }

        if (seleccion.equals("Revivir")) {
            if (!inventario.puedeUsarRevivir()) {
                ventana.mostrarDialogo(nombreJugador + " ya no tiene Revivir.");
                return null;
            }

            indiceObjetivo = ventana.seleccionarPokemonDerrotado(
                    equipo,
                    nombreJugador + " - Selecciona un Pokémon para revivir"
            );

            if (indiceObjetivo == -1) return null;
            tipoObjeto = TipoObjeto.REVIVIR;
        }

        if (seleccion.equals("Banda especial")) {
            if (!inventario.puedeUsarBandaEspecial()) {
                ventana.mostrarDialogo(nombreJugador + " ya no tiene Banda especial.");
                return null;
            }

            tipoObjeto = TipoObjeto.BANDA_ESPECIAL;
            indiceObjetivo = indiceActivo;
        }

        if (seleccion.equals("X speed")) {
            if (!inventario.puedeUsarXSpeed()) {
                ventana.mostrarDialogo(nombreJugador + " ya no tiene X speed.");
                return null;
            }

            tipoObjeto = TipoObjeto.X_SPEED;
            indiceObjetivo = indiceActivo;
        }

        if (tipoObjeto == null || indiceObjetivo == -1) return null;

        return new AccionUsarObjeto(jugador, tipoObjeto, indiceObjetivo);
    }

    private void seleccionarCambioJugador1() {
        if (batalla.isBatallaTerminada()) return;
        if (batalla.isCambioObligatorioJugador1()) return;

        int indice = ventana.seleccionarPokemonVivo(
                batalla.getJugador1().getEquipo().getPokemones(),
                batalla.getIndiceActivoJugador1(),
                "Jugador 1 - Selecciona un Pokémon para cambiar"
        );

        if (indice == -1) return;

        batalla.registrarAccionJugador1(new AccionCambiarPokemon(batalla.getJugador1(), indice));
        ventana.agregarMensaje("Jugador 1 eligió cambiar de Pokémon.");

        intentarResolverRonda();
    }

    private void seleccionarCambioJugador2() {
        if (batalla.esModoPve()) return;
        if (batalla.isBatallaTerminada()) return;
        if (batalla.isCambioObligatorioJugador2()) return;

        int indice = ventana.seleccionarPokemonVivo(
                batalla.getJugador2().getEquipo().getPokemones(),
                batalla.getIndiceActivoJugador2(),
                "Jugador 2 - Selecciona un Pokémon para cambiar"
        );

        if (indice == -1) return;

        batalla.registrarAccionJugador2(new AccionCambiarPokemon(batalla.getJugador2(), indice));
        ventana.agregarMensaje("Jugador 2 eligió cambiar de Pokémon.");

        intentarResolverRonda();
    }

    // Metodo principal para resolver ronda y disparar animaciones
    private void intentarResolverRonda() {
        if (!batalla.accionesListas()) return;

        EstadoVisual previo = capturarEstadoVisual();

        batalla.resolverRonda();

        EstadoVisual posterior = capturarEstadoVisual();

        Queue<Runnable> colaAnimaciones = construirColaAnimaciones(previo, posterior);
        reproducirColaAnimaciones(colaAnimaciones, () -> {
            actualizarVistaCompleta();
            mostrarMensajesRonda();
            manejarCambiosObligatorios();
            actualizarVistaCompleta();
            verificarFinCombate();
        });
    }

    // Metodo para mostrar mensajes de la ronda
    private void mostrarMensajesRonda() {
        for (String mensaje : batalla.getMensajesRonda()) {
            ventana.agregarMensaje(mensaje);
        }
        batalla.limpiarMensajesRonda();
    }

    private void manejarCambiosObligatorios() {
        if (!batalla.isBatallaTerminada() && batalla.isCambioObligatorioJugador1()) {
            int indice = ventana.seleccionarPokemonVivo(
                    batalla.getJugador1().getEquipo().getPokemones(),
                    batalla.getIndiceActivoJugador1(),
                    "Jugador 1 - Tu Pokémon fue derrotado, selecciona el siguiente"
            );

            if (indice != -1) {
                batalla.forzarCambioJugador1(indice);
                ventana.animarCambioJugador1(null);
            }
        }

        if (!batalla.esModoPve() && !batalla.isBatallaTerminada() && batalla.isCambioObligatorioJugador2()) {
            int indice = ventana.seleccionarPokemonVivo(
                    batalla.getJugador2().getEquipo().getPokemones(),
                    batalla.getIndiceActivoJugador2(),
                    "Jugador 2 - Tu Pokémon fue derrotado, selecciona el siguiente"
            );

            if (indice != -1) {
                batalla.forzarCambioJugador2(indice);
                ventana.animarCambioJugador2(null);
            }
        }

        mostrarMensajesRonda();
    }

    private void verificarFinCombate() {
        if (batalla.isBatallaTerminada()) {
            ventana.setBotonesJugador1Habilitados(false);
            ventana.setBotonesJugador2Habilitados(false);
        }
    }

    private void actualizarVistaCompleta() {
        ventana.actualizarPanelJugador1(batalla.getPokemonActivoJugador1());
        ventana.actualizarPanelJugador2(batalla.getPokemonActivoJugador2());

        ventana.actualizarObjetosJugador1(batalla.getInventarioJugador1());
        ventana.actualizarObjetosJugador2(batalla.getInventarioJugador2());

        ventana.setBotonesJugador1Habilitados(!batalla.isBatallaTerminada() && !batalla.isCambioObligatorioJugador1());

        if (batalla.esModoPve()) {
            ventana.setBotonesJugador2Habilitados(false);
        } else {
            ventana.setBotonesJugador2Habilitados(!batalla.isBatallaTerminada() && !batalla.isCambioObligatorioJugador2());
        }
    }

    // Metodo para capturar el estado visual antes o despues de resolver
    private EstadoVisual capturarEstadoVisual() {
        EstadoVisual estado = new EstadoVisual();

        Pokemon activoJ1 = batalla.getPokemonActivoJugador1();
        Pokemon activoJ2 = batalla.getPokemonActivoJugador2();

        estado.nombreJ1 = activoJ1.getNombre();
        estado.nombreJ2 = activoJ2.getNombre();

        estado.hpJ1 = activoJ1.getHp();
        estado.hpJ2 = activoJ2.getHp();

        estado.derrotadoJ1 = activoJ1.estaDerrotado();
        estado.derrotadoJ2 = activoJ2.estaDerrotado();

        return estado;
    }

    // Metodo para construir la secuencia simple de animaciones
    private Queue<Runnable> construirColaAnimaciones(EstadoVisual previo, EstadoVisual posterior) {
        Queue<Runnable> cola = new LinkedList<>();

        boolean cambioJ1 = !previo.nombreJ1.equals(posterior.nombreJ1);
        boolean cambioJ2 = !previo.nombreJ2.equals(posterior.nombreJ2);

        boolean recibioDanioJ1 = posterior.hpJ1 < previo.hpJ1;
        boolean recibioDanioJ2 = posterior.hpJ2 < previo.hpJ2;

        boolean fueDerrotadoJ1 = !previo.derrotadoJ1 && posterior.derrotadoJ1;
        boolean fueDerrotadoJ2 = !previo.derrotadoJ2 && posterior.derrotadoJ2;

        // Si J2 recibió daño o cayó, asumimos que J1 atacó
        if (recibioDanioJ2 || fueDerrotadoJ2) {
            cola.add(() -> ventana.animarAtaqueJugador1(this::continuarAnimacionActual));
            cola.add(() -> ventana.animarDanioJugador2(this::continuarAnimacionActual));
        }

        // Si J1 recibió daño o cayó, asumimos que J2 atacó
        if (recibioDanioJ1 || fueDerrotadoJ1) {
            cola.add(() -> ventana.animarAtaqueJugador2(this::continuarAnimacionActual));
            cola.add(() -> ventana.animarDanioJugador1(this::continuarAnimacionActual));
        }

        if (fueDerrotadoJ1) {
            cola.add(() -> ventana.animarDerrotaJugador1(this::continuarAnimacionActual));
        }

        if (fueDerrotadoJ2) {
            cola.add(() -> ventana.animarDerrotaJugador2(this::continuarAnimacionActual));
        }

        if (cambioJ1) {
            cola.add(() -> ventana.animarCambioJugador1(this::continuarAnimacionActual));
        }

        if (cambioJ2) {
            cola.add(() -> ventana.animarCambioJugador2(this::continuarAnimacionActual));
        }

        return cola;
    }

    private Queue<Runnable> colaActualAnimaciones;
    private Runnable callbackFinalAnimaciones;

    // Metodo para ejecutar animaciones una por una
    private void reproducirColaAnimaciones(Queue<Runnable> colaAnimaciones, Runnable alFinal) {
        this.colaActualAnimaciones = colaAnimaciones;
        this.callbackFinalAnimaciones = alFinal;
        continuarAnimacionActual();
    }

    // Metodo para seguir con la siguiente animacion
    private void continuarAnimacionActual() {
        if (colaActualAnimaciones == null || colaActualAnimaciones.isEmpty()) {
            if (callbackFinalAnimaciones != null) {
                Runnable fin = callbackFinalAnimaciones;
                callbackFinalAnimaciones = null;
                colaActualAnimaciones = null;
                fin.run();
            }
            return;
        }

        Runnable animacion = colaActualAnimaciones.poll();
        if (animacion != null) {
            animacion.run();
        }
    }

    // Clase simple para guardar estado visual
    private static class EstadoVisual {
        private String nombreJ1;
        private String nombreJ2;
        private int hpJ1;
        private int hpJ2;
        private boolean derrotadoJ1;
        private boolean derrotadoJ2;
    }
}