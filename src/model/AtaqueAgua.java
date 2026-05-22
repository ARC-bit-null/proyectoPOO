package src.model;

public class AtaqueAgua implements Habilidad {
    private String nombre;
    private int poderBase;

    public AtaqueAgua(String nombre, int poderBase) {
        this.nombre = nombre;
        this.poderBase = poderBase;
    }

    @Override
    public String getNombre() {
        return nombre;
    }

    @Override
    public TipoPokemon getTipo() {
        return TipoPokemon.AGUA;
    }

    @Override
    public int getPoderBase() {
        return poderBase;
    }

    @Override
    public void aplicarEfectoEspecial(Pokemon atacante, Pokemon defensor) {
        // Bajamos el daño base del rival un 20%
        int nuevoDano = (int) (defensor.getDano() * 0.80);
        defensor.setDano(Math.max(1, nuevoDano));

        System.out.println("¡La presión del agua redujo la fuerza de ataque de " + defensor.getNombre() + "!");
    }
}
