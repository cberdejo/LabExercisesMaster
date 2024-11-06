package amigos;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/// @Author: Christian Berdejo Sánchez
/// @Version 1.0
public class Club {
    protected Set<Persona> socios;
    private static Random alea = new Random();

    public Club() {
        socios = new TreeSet<>();
    }

    /// Lee el fichero y añade los socios al `Club`
    /// @param  fEntrada fichero de entrada
    /// @param  delim delimitador
    /// @throws  IOException
    public void  lee(String fEntrada, String delim) throws IOException {
        try (Scanner sc = new Scanner(Path.of(fEntrada))){
            while(sc.hasNextLine()){
                leeSocios(sc.nextLine(), delim);

            }

        }
    }

    /// Lee una linea  y añade socios según el delimitador pasado
    /// @param linea linea del fichero
    /// @param delim delimitador
    /// @return Optional con el pais
    private void leeSocios(String linea, String delim) {

        try (Scanner scLinea = new Scanner(linea)){
            scLinea.useDelimiter( "["+delim+"]+");
            creaSocioDesdeString(scLinea.next());
        } catch (NoSuchElementException e) {
            //No se especifica nada de excepciones, por lo que la linea no es válida, se ignora y ya.
        }

    }

    /// Crea un socio a partir de una cadena
    /// @param  nombre nombre del socio
    protected void creaSocioDesdeString(String nombre){
        socios.add(new Persona(nombre));

    }

    ///De forma aleatoria, se asigna a cada persona un amigo invisible.
    protected void hacerAmigos(){
        if (socios.size() < 2)throw new AmigoException("No hay suficientes socios para hacer amigos");

        List<Integer> posAmigos = new ArrayList<>(Stream.iterate(0, x -> x < socios.size(), x -> x + 1).toList());

        do{
        Collections.shuffle(posAmigos);
        } while(hayCoincidencias(posAmigos));

        List<Persona> listaSocios = socios.stream().toList();
        //Continuar

        for (int i = 0; i < socios.size(); i++){
            listaSocios.get(i).setAmigo(listaSocios.get(posAmigos.get(i)));
        }


    }

    ///Devuelve verdadero si la lista de posiciones de amigos coincide con la lista de posiciones de socios
    /// @param  posAmigos lista de posiciones de amigos
    /// @return verdadero si la lista de posiciones de amigos coincide con la lista de posiciones de socios
    private boolean hayCoincidencias(List<Integer> posAmigos) {
        for (int i = 0; i < socios.size(); i++){
            if (posAmigos.get(i) == i){
                return true;
            }
        }
        return false;
    }
    /// Presenta los socios en el fichero de salida
    /// @param  fSalida fichero de salida
    public void presentaAmigos(String fSalida) throws FileNotFoundException{
        try (PrintWriter pw = new PrintWriter(fSalida)) {
            presentaAmigos(pw);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("ERROR: falta el nombre del fichero");
        } catch (IOException e) {
            System.out.println("ERROR: no se puede escribir el fichero");
        }
    }
    /// Imprime las lineas de cada socio
    /// @param  pw fichero de salida
    public void presentaAmigos(PrintWriter pw){

        for (Persona p : socios){
            pw.println(p);
        }
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for (Persona p : socios){
            sb.append(p.toString()).append("\n");
        }
        return sb.toString();
    }
}
