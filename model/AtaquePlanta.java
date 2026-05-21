package model;

public class AtaquePlanta implements Habilidad {
    private String nombre;
    private int poderBase;

    public AtaquePlanta(String nombre, int poderBase) {
        this.nombre = nombre;
        this.poderBase = poderBase;
    }

    @Override public String getNombre() { return nombre; }
    @Override public Tipo getTipo() { return Tipo.PLANTA; }
    @Override public int getPoderBase() { return poderBase; }

    @Override
    public void aplicarEfectoEspecial(Pokemon atacante, Pokemon defensor) {
        // El atacante drena energía y se cura 15 HP usando su propio método curarHp
        atacante.curarHp(15);
        System.out.println("¡" + atacante.getNombre() + " absorbió energía de las plantas y recuperó salud!");
    }
}
