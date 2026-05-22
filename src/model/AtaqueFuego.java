package src.model;

public class AtaqueFuego implements Habilidad {
    private String nombre;
    private int poderBase;

    public AtaqueFuego(String nombre, int poderBase) {
        this.nombre = nombre;
        this.poderBase = poderBase;
    }

    @Override
    public String getNombre() {
        return nombre;
    }

    @Override
    public TipoPokemon getTipo() {
        return TipoPokemon.FUEGO;
    }

    @Override
    public int getPoderBase() {
        return poderBase;
    }

    @Override
    public void aplicarEfectoEspecial(Pokemon atacante, Pokemon defensor) {
        // Reducimos la defensa del rival un 15% debido al calor extremo
        int nuevaDefensa = (int) (defensor.getDefensa() * 0.85);
        defensor.setDefensa(Math.max(1, nuevaDefensa));

        System.out.println("¡El calor abrasador redujo la defensa de " + defensor.getNombre() + "!");
    }
}