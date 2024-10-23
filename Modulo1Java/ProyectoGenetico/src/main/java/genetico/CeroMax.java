package genetico;

public class CeroMax implements Problema {


    /// Devuelve el fitness de `cromosoma`
    /// En este caso a mas nº de ceros, mayor es el fitness
    /// @param cromosoma el cromosoma a evaluar
    /// @return el fitness
    @Override
    public double evalua(Cromosoma cromosoma) {
        double numCeros = 0;
        for(int gen :cromosoma.datos) {
            if (gen == 0) numCeros++;
        }
        return numCeros;
    }
}
