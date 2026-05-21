package model;

public class AtaqueElectrico implements Habilidad {
    private String nombre;
    private int poderBase;

    public AtaqueElectrico(String nombre, int poderBase) {
        this.nombre = nombre;
        this.poderBase = poderBase;
    }

    @Override public String getNombre() { return nombre; }
    @Override public Tipo getTipo() { return Tipo.ELECTRICO; }
    @Override public int getPoderBase() { return poderBase; }

    @Override
    public void aplicarEfectoEspecial(Pokemon atacante, Pokemon defensor) {
        // Reducimos la velocidad del rival un 30% debido a la parálisis elécrica
        int nuevaVelocidad = (int) (defensor.getVelocidad() * 0.70);

        defensor.setVelocidad(Math.max(1, nuevaVelocidad)); 
        
        System.out.println("¡La descarga eléctrica paralizó a " + defensor.getNombre() + " y redujo su velocidad!");
    }
}
