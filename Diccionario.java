import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
/**
 * Write a description of class Diccionario here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Diccionario
{
    // instance variables - replace the example below with your own
    String ruta;
    File archivo;
    BufferedWriter bw;
    /**
     * Constructor for objects of class Diccionario
     */
    public Diccionario(String ruta)throws IOException 
    {
        this.ruta=ruta;
        this.archivo = new File(ruta);

    }

    public void crear(String texto)throws IOException 
    {
        if(archivo.exists()) {
            archivo.delete();
            bw = new BufferedWriter(new FileWriter(archivo));
            bw.write(texto);
        } else {
            bw = new BufferedWriter(new FileWriter(archivo));
            bw.write(texto);
        }
        bw.close();
    }
}
