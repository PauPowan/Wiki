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
    String ruta;
    File archivo;
    BufferedWriter bw;

    public Archivo(String ruta)throws IOException 
    {
        this.ruta=ruta;
        this.archivo = new File(ruta);

    }

     /**
     * Genera un archivo de texto a partir de un String, usualmente un toString de un árbol 
     * en este programa
     * 
     * @param  String texto el string que se proporciona para crear el archivo
     * @return No posee
     */
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
