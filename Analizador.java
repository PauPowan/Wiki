import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.io.*;
public class Analizador
{
    String url;
    /**
     * Constructor for objects of class Analizador
     */
    public static final String SUSTANTIVOS="Sustantivos.txt";
    public static final String VERBOS="Verbos.txt";
    public static final String NPROPIOS="Nombres Propios.txt";
    public static final String TRIPLETAS="Tripletas.txt";
    Arbol arbolS;
    Arbol arbolV;
    Arbol arbolNP;
    Arbol arbolTri;
    Archivo tri;
    Archivo sust;
    Archivo verb;
    Archivo nomP; 
    ArrayList<Integer> idNP;
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
        arbolTri=new Arbol();
        arbolNP=new Arbol();
        this.idNP= new ArrayList<Integer>();
        sust=new Archivo(SUSTANTIVOS);
        verb=new Archivo(VERBOS);
        nomP=new Archivo(NPROPIOS);
        tri=new Archivo(TRIPLETAS);

        crearDiccionarios();
        crearTripletas();
    }

    public void crearDiccionarios()throws FileNotFoundException, IOException {    
        String cadena;
        String[] linea;

        calcularNP();
        FileReader f = new FileReader(url);
        BufferedReader b = new BufferedReader(f);
        while((cadena = b.readLine())!=null) {
            linea= cadena.split(" ");        
            if(linea.length>2&&linea[2].charAt(0)=='N'){
                if(!linea[3].matches("0")){  
                    arbolS.insertar(Integer.parseInt(linea[3]),linea[0].toLowerCase(),false);                    
                }else{
                    arbolNP.insertar(idNP.get(idNP.size()-1),linea[0],false); 
                    idNP.remove(idNP.size()-1);
                }
            }
            if(linea.length>2&&linea[2].charAt(0)=='V'){
                arbolV.insertar(Integer.parseInt(linea[3]),linea[0].toLowerCase(),false);
            }            
            //System.out.println(linea[0]);
        }
        // System.out.print(arbol.toString());
        sust.crear(arbolS.toString());
        verb.crear(arbolV.toString());
        nomP.crear(arbolNP.toString());
    }

    private void crearTripletas()throws FileNotFoundException, IOException {
        String cadena;
        String cadenaTemp;
        String[] linea;  
        String[] lineaTemp; 
        String[] sustantivo1=new String[4];
        String[] sustantivo2=new String[4];
        String idS1;
        String idV;
        String idS2;
        String idTrip;
        boolean salir=false;
        FileReader f = new FileReader(url);
        BufferedReader b = new BufferedReader(f);
        BufferedReader bTemp = new BufferedReader(f);
        while((cadena = b.readLine())!=null) {
            linea= cadena.split(" ");          
            if(linea.length>2&&linea[2].charAt(0)=='N'){
                sustantivo1=linea;
            }
            if(linea.length>2&&linea[2].charAt(0)=='V'){
                idTrip="";
                bTemp=b;
                salir=false;
                while((cadenaTemp = bTemp.readLine())!=null &&!salir ) {
                    lineaTemp= cadenaTemp.split(" ");
                    if(lineaTemp.length>2&&lineaTemp[2].charAt(0)=='N'){
                        sustantivo2=lineaTemp;                        
                        salir=true;
                    }
                }

                if(sustantivo1[3].matches("0")){ 
                    idS1=Integer.toString(arbolNP.getId(sustantivo1[0]));
                    idTrip+=Integer.toString(arbolNP.getId(sustantivo1[0])*-1);
                }else{
                    idTrip+=sustantivo1[3];
                    idS1=sustantivo1[3];
                }
                idTrip=linea[3];
                if(sustantivo2[3].matches("0")){ 
                    idS2=Integer.toString(arbolNP.getId(sustantivo2[0]));
                    idTrip+=Integer.toString(arbolNP.getId(sustantivo2[0])*-1);
                }else{
                    idTrip+=sustantivo2[3];
                    idS2=sustantivo2[3];
                }
                arbolTri.insertar(Integer.parseInt(idTrip),idS1,true);
                arbolTri.insertar(Integer.parseInt(idTrip),linea[3],true);
                arbolTri.insertar(Integer.parseInt(idTrip),idS2,true);
            }
        }
        tri.crear(arbolTri.toString());
    }

    public void calcularNP()throws FileNotFoundException, IOException {
        String cadena;
        int cantidadNP=-1;
        idNP.clear();
        String[] linea; 
        FileReader f = new FileReader(url);
        BufferedReader b = new BufferedReader(f);
        BufferedReader bTemp = new BufferedReader(f);
        while((cadena = b.readLine())!=null) {
            linea= cadena.split(" "); 
            if(linea.length>2&&linea[2].charAt(0)=='N'&&linea[3].matches("0")){
                idNP.add(cantidadNP);
                cantidadNP--;
            }

        }
        Collections.shuffle(idNP);
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
