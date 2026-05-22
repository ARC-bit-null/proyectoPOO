package src.model;

import java.util.ArrayList;

// Esta clase se encarga de construir objetos Pokemon de forma más clara y flexible
public class PokemonBuilder {

    private int id;
    private String nombre;
    private TipoPokemon tipo;
    private int hp;
    private int hpMax;
    private int dano;
    private int defensa;
    private int velocidad;
    private int nivel;
    private int nivelEvolucion;
    private String nombreEvolucion;
    private ArrayList<Habilidad> habilidades;
    private String imagenFrontal;
    private String imagenTrasera;

    public PokemonBuilder() {
        this.id = 0;
        this.nombre = "MissingNo";
        this.tipo = TipoPokemon.NORMAL;
        this.hp = 10;
        this.hpMax = 10;
        this.dano = 10;
        this.defensa = 10;
        this.velocidad = 10;
        this.nivel = 1;
        this.nivelEvolucion = 0;
        this.nombreEvolucion = "Ninguna";
        this.habilidades = new ArrayList<>();
        this.imagenFrontal = "";
        this.imagenTrasera = "";
    }

    public PokemonBuilder setId(int id) {
        this.id = id;
        return this;
    }

    public PokemonBuilder setNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public PokemonBuilder setTipo(TipoPokemon tipo) {
        this.tipo = tipo;
        return this;
    }

    public PokemonBuilder setHp(int hp) {
        this.hp = hp;
        this.hpMax = hp;
        return this;
    }

    public PokemonBuilder setHpMax(int hpMax) {
        this.hpMax = hpMax;
        return this;
    }

    public PokemonBuilder setDano(int dano) {
        this.dano = dano;
        return this;
    }

    public PokemonBuilder setDefensa(int defensa) {
        this.defensa = defensa;
        return this;
    }

    public PokemonBuilder setVelocidad(int velocidad) {
        this.velocidad = velocidad;
        return this;
    }

    public PokemonBuilder setNivel(int nivel) {
        this.nivel = nivel;
        return this;
    }

    public PokemonBuilder setNivelEvolucion(int nivelEvolucion) {
        this.nivelEvolucion = nivelEvolucion;
        return this;
    }

    public PokemonBuilder setNombreEvolucion(String nombreEvolucion) {
        this.nombreEvolucion = nombreEvolucion;
        return this;
    }

    public PokemonBuilder setImagenFrontal(String imagenFrontal) {
        this.imagenFrontal = imagenFrontal;
        return this;
    }

    public PokemonBuilder setImagenTrasera(String imagenTrasera) {
        this.imagenTrasera = imagenTrasera;
        return this;
    }

    public PokemonBuilder setHabilidades(ArrayList<Habilidad> habilidades) {
        this.habilidades = new ArrayList<>(habilidades);
        return this;
    }

    public PokemonBuilder agregarHabilidad(Habilidad habilidad) {
        if (habilidad != null) {
            this.habilidades.add(habilidad);
        }
        return this;
    }

    // Agrega el ataque básico que todos los pokémon tendrán
    public PokemonBuilder agregarAtaqueBasicoPorDefecto() {
        this.habilidades.add(new AtaqueBasico("Placaje", TipoPokemon.NORMAL, 35));
        return this;
    }

    // Agrega el ataque elemental del tipo del pokémon
    public PokemonBuilder agregarAtaqueTipoPorDefecto(String nombreAtaque, int poder) {
        Habilidad habilidadTipo = crearAtaqueSegunTipo(nombreAtaque, poder);
        if (habilidadTipo != null) {
            this.habilidades.add(habilidadTipo);
        }
        return this;
    }

    // Agrega ambos ataques base del sistema: básico + tipo
    public PokemonBuilder agregarAtaquesIniciales(String nombreAtaqueTipo, int poderAtaqueTipo) {
        return this.agregarAtaqueBasicoPorDefecto().agregarAtaqueTipoPorDefecto(nombreAtaqueTipo, poderAtaqueTipo);
    }

    // Metodo interno para construir el ataque correspondiente al tipo actual del pokémon
    private Habilidad crearAtaqueSegunTipo(String nombreAtaque, int poder) {
        switch (tipo) {
            case AGUA:
                return new AtaqueAgua(nombreAtaque, poder);
            case FUEGO:
                return new AtaqueFuego(nombreAtaque, poder);
            case PLANTA:
                return new AtaquePlanta(nombreAtaque, poder);
            case ELECTRICO:
                return new AtaqueElectrico(nombreAtaque, poder);
            case TIERRA:
                return new AtaqueTierra(nombreAtaque, poder);
            case NORMAL:
                return new AtaqueBasico(nombreAtaque, TipoPokemon.NORMAL, poder);
            default:
                return null;
        }
    }

    public Pokemon build() {
        return new Pokemon(
                id,
                nombre,
                tipo,
                hp,
                hpMax,
                dano,
                defensa,
                velocidad,
                nivel,
                nivelEvolucion,
                nombreEvolucion,
                habilidades,
                imagenFrontal,
                imagenTrasera
        );
    }
}