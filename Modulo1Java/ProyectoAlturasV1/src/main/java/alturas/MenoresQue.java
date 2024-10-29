package alturas;
/// @Author: Christian Berdejo
/// @Version 1.0
public class MenoresQue implements  Seleccion{

    private double altura;
    public MenoresQue(double altura){
        this.altura = altura;
    }
    /// comprobar que la altura del pais es menor que la altura dada
    /// @param pais pais a comprobar
    @Override
    public boolean test(Pais pais) {
        return pais.altura() < altura;
    }
}
