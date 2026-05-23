package src.model;

import java.util.ArrayList;

// Esta clase guarda la configuración de un rival del modo PvE
public class RivalPve {

    private ModoPve modo;
    private int numeroRival;
    private int nivel;
    private int experienciaOtorgada;
    private ArrayList<Integer> idsPokemon;

    public RivalPve(ModoPve modo, int numeroRival, int nivel, int experienciaOtorgada, ArrayList<Integer> idsPokemon) {
        this.modo = modo;
        this.numeroRival = numeroRival;
        this.nivel = nivel;
        this.experienciaOtorgada = experienciaOtorgada;
        this.idsPokemon = (idsPokemon != null) ? new ArrayList<>(idsPokemon) : new ArrayList<>();
    }

    public ModoPve getModo() {
        return modo;
    }

    public void setModo(ModoPve modo) {
        this.modo = modo;
    }

    public int getNumeroRival() {
        return numeroRival;
    }

    public void setNumeroRival(int numeroRival) {
        this.numeroRival = numeroRival;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public int getExperienciaOtorgada() {
        return experienciaOtorgada;
    }

    public void setExperienciaOtorgada(int experienciaOtorgada) {
        this.experienciaOtorgada = experienciaOtorgada;
    }

    public ArrayList<Integer> getIdsPokemon() {
        return idsPokemon;
    }

    public void setIdsPokemon(ArrayList<Integer> idsPokemon) {
        this.idsPokemon = (idsPokemon != null) ? new ArrayList<>(idsPokemon) : new ArrayList<>();
    }

    // Metodo para obtener cuántos pokemon tiene este rival
    public int getCantidadPokemon() {
        return idsPokemon.size();
    }

    // Metodo para devolver un texto simple del rival
    public String getNombreVisual() {
        return "Rival " + numeroRival;
    }
}