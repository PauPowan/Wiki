import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
public class Analizador
{
    String url;
    int x;


    // public void insertar(String archivo){
    // try {
    // int elemento=0;
    // Scanner entrada = new Scanner(new FileInputStream(archivo)); 
    // while(entrada.hasNext()){
    // elemento = entrada.nextInt();
    // this.insertar(elemento);
    // }
    // }
    // catch(Exception e){
    // System.err.println("error al cargar archivo"); 
    // }
    // }

    /**
     * Constructor for objects of class Analizador
     */
    Arbol arbolS;
    Arbol arbolV;
    Arbol arbolNP;
    Diccionario sust;
    Diccionario verb;
    Diccionario nomP;
    public Analizador(String url) throws IOException
    {
        init(url);

    }

    public Analizador() throws IOException
    {   
        url="peq.txt";
        init(url);        

    }

    private void init(String url) throws IOException{
        this.url=url;
        arbolS=new Arbol();
        arbolV=new Arbol();
        arbolNP=new Arbol();
        sust=new Diccionario("Sustantivos.txt");
        verb=new Diccionario("Verbos.txt");
        nomP=new Diccionario("Nombres Propios.txt");
        leer();
    }

    public void leer()throws FileNotFoundException, IOException {    
        String cadena;
        String[] linea;        

        FileReader f = new FileReader(url);
        BufferedReader b = new BufferedReader(f);
        while((cadena = b.readLine())!=null) {
            linea= cadena.split(" ");
            if(linea.length>1&&linea[2].charAt(0)=='N'){
                arbolS.insertar(linea[0]);
            }
            //System.out.println(linea[0]);

        }
        // System.out.print(arbol.toString());
        sust.crear(arbolS.toString());
    }
    // public void leer()throws FileNotFoundException, IOException {    
    // String cadena;
    // FileReader f = new FileReader("peq.txt");
    // BufferedReader b = new BufferedReader(f);
    // while((cadena = b.readLine())!=null) {
    // System.out.println(cadena);
    // }
    // }
}
