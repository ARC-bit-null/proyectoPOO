package src.model;

public class AtaquePlanta implements Habilidad {
    private String nombre;
    private int poderBase;

    public AtaquePlanta(String nombre, int poderBase) {
        this.nombre = nombre;
        this.poderBase = poderBase;
    }

    @Override
    public String getNombre() {
        return nombre;
    }

    @Override
    public TipoPokemon getTipo() {
        return TipoPokemon.PLANTA;
    }

    @Override
    public int getPoderBase() {
        return poderBase;
    }

    @Override
    public void aplicarEfectoEspecial(Pokemon atacante, Pokemon defensor) {
        // El atacante drena energía y se cura 15 HP
        atacante.curarHp(15);
        System.out.println("¡" + atacante.getNombre() + " absorbió energía de las plantas y recuperó salud!");
    }
}