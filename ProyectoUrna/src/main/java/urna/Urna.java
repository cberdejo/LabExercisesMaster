package urna;

import java.util.Random;
/// @Author Christian Berdejo
///  @Version 1.0
public class Urna {
    ///Representa el color de una bola
    static public enum ColorBola {Blanca, Negra};
    ///Número de bolas negras
    private int bolasNegras;
    ///Número de bolas blancas
    private int bolasBlancas;

    private Random random;

    ///Crea una urna con un número determinado de bolas negras y blancas.
    /// @param bolasNegras el número de bolas negras
    /// @param bolasBlancas el número de bolas blancas
    public Urna(int bolasNegras, int bolasBlancas) {
        this.bolasNegras = bolasNegras;
        this.bolasBlancas = bolasBlancas;
        this.random = new Random();

    }
    /// Agrega una bola negra a `this`
    public void agregarBolaNegra() {
        bolasNegras++;
    }

    /// Agrega una bola blanca a `this`
    public void agregarBolaBlanca() {
        bolasBlancas++;
    }

    /// Quita una bola negra de `this`
    /// @throw IllegalStateException si `this` no tiene bolas negras
    public void quitarBolaNegra (){
        if (bolasNegras==0) throw new IllegalStateException("No quedan bolas negras en la urna.");

        bolasNegras--;
    }

    /// Quita una bola blanca de `this`
    /// @throw IllegalStateException si `this` no tiene bolas blancas
    public void quitarBolaBlanca() {
        if (bolasBlancas==0) throw new IllegalStateException("No quedan bolas blancas en la urna.");

        bolasBlancas--;
    }

    /// Quita una bola de `this` al azar.
    /// @return el color de la bola obtenida con un el `enum ColorBola {Blanca,Negra}`
    /// @throw IllegalStateException en caso de que no queden bolas
    public ColorBola quitarBolaAlAzar(){
        if (bolasBlancas == 0 && bolasNegras == 0) throw new IllegalStateException("No quedan bolas en la urna.");


        if (bolasBlancas > 0 && bolasNegras > 0) {
            int numBolasTotal = bolasBlancas + bolasNegras;
            boolean esBolaBlanca = random.nextDouble(numBolasTotal) < bolasBlancas;

            if (esBolaBlanca) {
                bolasBlancas--;
                return ColorBola.Blanca;
            } else {
                bolasNegras--;
                return ColorBola.Negra;
            }
        } else if (bolasNegras > 0) {
            bolasNegras--;
            return ColorBola.Negra;
        } else {
            bolasBlancas--;
            return ColorBola.Blanca;
        }

    }

    @Override
    public String toString() {
        return "Bolas negras:" + bolasNegras + " \n bolas blancas: " + bolasBlancas +"\n";
    }

}
