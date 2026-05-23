package src.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Pokemon implements Serializable {
    private static final long serialVersionUID = 1L;

    private int xp = 0;
    private int xpMax = 100;

    private int id;
    private String nombre;
    private TipoPokemon tipo;

    // Estadísticas base reales del pokemon
    private int hpBase;
    private int danoBase;
    private int defensaBase;
    private int velocidadBase;

    // Estadísticas actuales ya escaladas
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

    // Multiplicadores temporales para el combate
    private double multiplicadorDanoTemporal;
    private double multiplicadorVelocidadTemporal;

    // Constructor original para mantener compatibilidad
    public Pokemon(int id, String nombre, TipoPokemon tipo, int hp, int dano, int defensa, int velocidad, int nivel, int nivelEvolucion, String nombreEvolucion) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;

        this.hpBase = hp;
        this.danoBase = dano;
        this.defensaBase = defensa;
        this.velocidadBase = velocidad;

        this.hp = hp;
        this.hpMax = hp;
        this.dano = dano;
        this.defensa = defensa;
        this.velocidad = velocidad;

        this.nivel = nivel;
        this.nivelEvolucion = nivelEvolucion;
        this.nombreEvolucion = nombreEvolucion;

        this.habilidades = new ArrayList<>();
        this.imagenFrontal = "";
        this.imagenTrasera = "";

        this.multiplicadorDanoTemporal = 1.0;
        this.multiplicadorVelocidadTemporal = 1.0;
    }

    // Constructor completo pensado para trabajar mejor con Builder
    public Pokemon(int id, String nombre, TipoPokemon tipo, int hp, int hpMax, int dano, int defensa, int velocidad,
                   int nivel, int nivelEvolucion, String nombreEvolucion,
                   ArrayList<Habilidad> habilidades, String imagenFrontal, String imagenTrasera) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;

        this.hpBase = hpMax;
        this.danoBase = dano;
        this.defensaBase = defensa;
        this.velocidadBase = velocidad;

        this.hp = hp;
        this.hpMax = hpMax;
        this.dano = dano;
        this.defensa = defensa;
        this.velocidad = velocidad;

        this.nivel = nivel;
        this.nivelEvolucion = nivelEvolucion;
        this.nombreEvolucion = nombreEvolucion;
        this.habilidades = (habilidades != null) ? new ArrayList<>(habilidades) : new ArrayList<>();
        this.imagenFrontal = imagenFrontal != null ? imagenFrontal : "";
        this.imagenTrasera = imagenTrasera != null ? imagenTrasera : "";

        this.multiplicadorDanoTemporal = 1.0;
        this.multiplicadorVelocidadTemporal = 1.0;
    }

    // Metodo para restar daño recibido
    public void recibirDano(int dano) {
        this.hp -= dano;
        if (this.hp < 0) this.hp = 0;
    }

    // Metodo para ver si ya le toca evolucionar
    public boolean puedeEvolucionar() {
        return nivelEvolucion > 0 && nivel >= nivelEvolucion && !nombreEvolucion.equals("Ninguna");
    }

    // Metodo para ver si el pokemon ya fue derrotado
    public boolean estaDerrotado() {
        return hp == 0;
    }

    // Metodo para curar hp sin pasarse del maximo
    public void curarHp(int cantidad) {
        if (hp > 0) {
            hp += cantidad;
            if (hp > hpMax) {
                hp = hpMax;
            }
        }
    }

    // Metodo para revivir al pokemon con toda su vida
    public void revivir() {
        if (hp == 0) {
            hp = hpMax;
        }
    }

    // Metodo para aplicar multiplicador temporal al daño
    public void aplicarMultiplicadorDano(double multiplicador) {
        this.multiplicadorDanoTemporal *= multiplicador;
    }

    // Metodo para aplicar multiplicador temporal a la velocidad
    public void aplicarMultiplicadorVelocidad(double multiplicador) {
        this.multiplicadorVelocidadTemporal *= multiplicador;
    }

    // Metodo para reiniciar modificadores temporales del combate
    public void reiniciarModificadoresTemporales() {
        this.multiplicadorDanoTemporal = 1.0;
        this.multiplicadorVelocidadTemporal = 1.0;
    }

    // Metodo para obtener el daño real contando el buff temporal
    public int getDanoEfectivo() {
        return Math.max(1, (int) Math.round(this.dano * this.multiplicadorDanoTemporal));
    }

    // Metodo para obtener la velocidad real contando el buff temporal
    public int getVelocidadEfectiva() {
        return Math.max(1, (int) Math.round(this.velocidad * this.multiplicadorVelocidadTemporal));
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public int getXpMax() {
        return xpMax;
    }

    public void setXpMax(int xpMax) {
        this.xpMax = xpMax;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public TipoPokemon getTipo() {
        return tipo;
    }

    public void setTipo(TipoPokemon tipo) {
        this.tipo = tipo;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getDano() {
        return dano;
    }

    public void setDano(int dano) {
        this.dano = dano;
    }

    public int getHpMax() {
        return hpMax;
    }

    public void setHpMax(int hpMax) {
        this.hpMax = hpMax;
    }

    public int getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }

    public int getDefensa() {
        return defensa;
    }

    public void setDefensa(int defensa) {
        this.defensa = defensa;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public int getNivelEvolucion() {
        return nivelEvolucion;
    }

    public void setNivelEvolucion(int nivelEvolucion) {
        this.nivelEvolucion = nivelEvolucion;
    }

    public String getNombreEvolucion() {
        return nombreEvolucion;
    }

    public void setNombreEvolucion(String nombreEvolucion) {
        this.nombreEvolucion = nombreEvolucion;
    }

    public ArrayList<Habilidad> getHabilidades() {
        return habilidades;
    }

    public void setHabilidades(ArrayList<Habilidad> habilidades) {
        this.habilidades = (habilidades != null) ? new ArrayList<>(habilidades) : new ArrayList<>();
    }

    public void agregarHabilidad(Habilidad habilidad) {
        if (habilidad != null) {
            this.habilidades.add(habilidad);
        }
    }

    public String getImagenFrontal() {
        return imagenFrontal;
    }

    public void setImagenFrontal(String imagenFrontal) {
        this.imagenFrontal = imagenFrontal;
    }

    public String getImagenTrasera() {
        return imagenTrasera;
    }

    public void setImagenTrasera(String imagenTrasera) {
        this.imagenTrasera = imagenTrasera;
    }

    public int getHpBase() {
        return hpBase;
    }

    public void setHpBase(int hpBase) {
        this.hpBase = hpBase;
    }

    public int getDanoBase() {
        return danoBase;
    }

    public void setDanoBase(int danoBase) {
        this.danoBase = danoBase;
    }

    public int getDefensaBase() {
        return defensaBase;
    }

    public void setDefensaBase(int defensaBase) {
        this.defensaBase = defensaBase;
    }

    public int getVelocidadBase() {
        return velocidadBase;
    }

    public void setVelocidadBase(int velocidadBase) {
        this.velocidadBase = velocidadBase;
    }

    public void subirNivel() {
        this.nivel++;
    }

    public Habilidad getAtaqueBasico() {
        for (Habilidad habilidad : habilidades) {
            if (habilidad.getTipo() == TipoPokemon.NORMAL) {
                return habilidad;
            }
        }
        return null;
    }

    public Habilidad getHabilidadPorTipo(TipoPokemon tipoBuscado) {
        for (Habilidad habilidad : habilidades) {
            if (habilidad.getTipo() == tipoBuscado) {
                return habilidad;
            }
        }
        return null;
    }

    // Metodo para recalcular stats segun la especie actual y el nivel actual
    public void recalcularEstadisticasPorNivel() {
        boolean estabaDerrotado = this.estaDerrotado();

        this.hpMax = this.hpBase + ((this.nivel - 1) * 8);
        this.dano = this.danoBase + ((this.nivel - 1) * 3);
        this.defensa = this.defensaBase + ((this.nivel - 1) * 2);
        this.velocidad = this.velocidadBase + ((this.nivel - 1) * 2);

        if (estabaDerrotado) {
            this.hp = 0;
        } else {
            this.hp = this.hpMax;
        }
    }

    // Metodo para evolucionar cambiando los datos del mismo objeto
    public void evolucionar() {
        if (!puedeEvolucionar()) {
            return;
        }

        boolean estabaDerrotado = this.estaDerrotado();

        Pokemon pokemonEvolucion = Pokedex.buscarPokemonBasePorNombre(this.nombreEvolucion);

        if (pokemonEvolucion == null) {
            return;
        }

        int nivelActual = this.nivel;
        int xpActual = this.xp;
        int xpMaxActual = this.xpMax;

        this.id = pokemonEvolucion.getId();
        this.nombre = pokemonEvolucion.getNombre();
        this.tipo = pokemonEvolucion.getTipo();

        this.hpBase = pokemonEvolucion.getHpBase();
        this.danoBase = pokemonEvolucion.getDanoBase();
        this.defensaBase = pokemonEvolucion.getDefensaBase();
        this.velocidadBase = pokemonEvolucion.getVelocidadBase();

        this.nivelEvolucion = pokemonEvolucion.getNivelEvolucion();
        this.nombreEvolucion = pokemonEvolucion.getNombreEvolucion();

        this.habilidades = new ArrayList<>(pokemonEvolucion.getHabilidades());
        this.imagenFrontal = pokemonEvolucion.getImagenFrontal();
        this.imagenTrasera = pokemonEvolucion.getImagenTrasera();

        this.nivel = nivelActual;
        this.xp = xpActual;
        this.xpMax = xpMaxActual;

        // Al evolucionar reseteamos buffs temporales para evitar cosas raras
        reiniciarModificadoresTemporales();
        recalcularEstadisticasPorNivel();

        if (estabaDerrotado) {
            this.hp = 0;
        }
    }

    // Metodo para darle experiencia al pokemon
    public void ganarExperiencia(int cantidadXp) {
        this.xp += cantidadXp;
        System.out.println(this.nombre + " obtuvo " + cantidadXp + " puntos de XP.");

        while (this.xp >= this.xpMax) {
            this.xp -= this.xpMax;
            this.nivel++;
            this.xpMax = (int) (this.xpMax * 1.5);

            System.out.println("¡Felicidades! ¡" + this.nombre + " subió al nivel " + this.nivel + "!");

            recalcularEstadisticasPorNivel();

            while (puedeEvolucionar()) {
                System.out.println("¡Atención! " + this.nombre + " está evolucionando en " + this.nombreEvolucion + "!");
                evolucionar();
            }
        }
    }
}