package model;

import battle.SistemaTipos;

public class AtaqueAgua implements Habilidad {
    private String nombre;
    private int poderBase;

    public AtaqueAgua(String nombre, int poderBase) {
        this.nombre = nombre;
        this.poderBase = poderBase;
    }

    @Override public String getNombre() { return nombre; }
    @Override public SistemaTipos getTipo() { return SistemaTipos.AGUA; }
    @Override public int getPoderBase() { return poderBase; }

    @Override
    public void aplicarEfectoEspecial(Pokemon atacante, Pokemon defensor) {
        // Bajamos el daño base del rival un 20%
        int nuevoDano = (int) (defensor.getDano() * 0.80);
        //nose 
        System.out.println("¡La presión del agua redujo la fuerza de ataque de " + defensor.getNombre() + "!");
    }
//nose}
