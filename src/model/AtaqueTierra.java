package src.model;

public class AtaqueTierra implements Habilidad {
    private static final long serialVersionUID = 1L;

    private String nombre;
    private int poderBase;

    public AtaqueTierra(String nombre, int poderBase) {
        this.nombre = nombre;
        this.poderBase = poderBase;
    }

    @Override
    public String getNombre() {
        return nombre;
    }

    @Override
    public TipoPokemon getTipo() {
        return TipoPokemon.TIERRA;
    }

    @Override
    public int getPoderBase() {
        return poderBase;
    }

    @Override
    public void aplicarEfectoEspecial(Pokemon atacante, Pokemon defensor) {
        if (atacante.yaUsoEfectoEspecial(nombre)) {
            return;
        }

        // El impacto terrestre reduce en 15% la velocidad del rival
        int nuevaVelocidad = (int) (defensor.getVelocidad() * 0.85);
        defensor.setVelocidad(Math.max(1, nuevaVelocidad));

        System.out.println("¡El impacto de tierra sacudió a " + defensor.getNombre() + " y redujo su velocidad!");


        atacante.marcarEfectoEspecialComoUsado(nombre);
    }
}