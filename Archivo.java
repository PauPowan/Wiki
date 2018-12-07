import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
/**
 * Write a description of class Archivo here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Archivo
{
    // instance variables - replace the example below with your own
    String ruta;
    File archivo;
    BufferedWriter bw;
    /**
     * Constructor for objects of class Archivo
     */
    public Archivo(String ruta)throws IOException 
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
