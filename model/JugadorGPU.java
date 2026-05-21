package model;

import java.util.List;
import java.util.Random;

public class JugadorCPU extends Jugador {
    private Random random;

    public JugadorCPU() {
        super(); // Llama al constructor de la clase Jugador que ya tienen
        this.random = new Random();
    }

       public Habilidad seleccionarMovimientoAleatorio(Pokemon pokemonActivo) {
        List<Habilidad> ataques = pokemonActivo.getHabilidades();
        if (ataques == null || ataques.isEmpty()) {
            return null;         }
        
        int indiceAzar = random.nextInt(ataques.size());
        return ataques.get(indiceAzar);
    }

    // Si el Pokémon actual de la CPU se debilita, busca en su equipo al siguiente vivo
    public Pokemon elegirSiguientePokemon() {
        /        for (Pokemon p : this.getEquipo().getPokemones()) {
            if (!p.estaDerrotado()) {
                return p;
            }
        }
        return null; // Significa que perdiste y la CPU ya no tiene pokémones para pelear
    }
}
