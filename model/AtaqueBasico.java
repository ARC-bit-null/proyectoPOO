package model;

public class AtaqueBasico implements Habilidad {
    private String nombre;
    private Tipo tipo;
    private int poderBase;

    // Constructor que te permite moldear cualquier ataque normal "en el aire"
    public AtaqueBasico(String nombre, Tipo tipo, int poderBase) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.poderBase = poderBase;
    }

    @Override public String getNombre() { return nombre; }
    @Override public Tipo getTipo() { return tipo; }
    @Override public int getPoderBase() { return poderBase; }

    @Override
    public void aplicarEfectoEspecial(Pokemon atacante, Pokemon defensor) {
        // Al ser un ataque básico neutro, no altera estadísticas
        System.out.println("¡Un golpe limpio y directo!");
    }
}
