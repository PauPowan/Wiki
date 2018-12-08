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
    public static final String SUSTANTIVOS="Diccionarios/Sustantivos.txt";
    public static final String VERBOS="Diccionarios/Verbos.txt";
    public static final String NPROPIOS="Diccionarios/Nombres Propios.txt";
    public static final String TRIPLETAS="Diccionarios/Trip.txt";
    Arbol arbolS;
    Arbol arbolV;
    Arbol arbolNP;
    ArbolTri arbolTriV;
    ArbolTri arbolTriS1;
    ArbolTri arbolTriS2;
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
        arbolTriV=new ArbolTri();
        arbolTriS1=new ArbolTri();
        arbolTriS2=new ArbolTri();
        arbolNP=new Arbol();
        this.idNP= new ArrayList<Integer>();
        sust=new Archivo(SUSTANTIVOS);
        verb=new Archivo(VERBOS);
        nomP=new Archivo(NPROPIOS);
        tri=new Archivo(TRIPLETAS);

        //crearDiccionarios();
        //crearTripletas();

        //System.out.println(arbolS.getId("Arag�n"));
        //111872    modo
    }
    public void setDicc(String url){
        this.url=url;
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
                    arbolS.insertar(Integer.parseInt(linea[3]),linea[0].toLowerCase());                    
                }else{
                    arbolNP.insertar(idNP.get(idNP.size()-1),linea[0]); 
                    idNP.remove(idNP.size()-1);
                }
            }
            if(linea.length>2&&linea[2].charAt(0)=='V'){
                arbolV.insertar(Integer.parseInt(linea[3]),linea[0].toLowerCase());
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
        int ln=0;
        int lnTemp=0;
        String [] ids=new String[2];
        String[] linea;
        String[] lineaTemp;
        String[] s1=new String[4];
        String[] v=new String[4];
        String[] s2=new String[4];;
        FileReader f = new FileReader(url);
        FileReader fTemp;
        BufferedReader b = new BufferedReader(f);
        BufferedReader bTemp;
        while((cadena = b.readLine())!=null) {
            linea= cadena.split(" ");
            ln++;
            if(linea.length>2&&linea[2].charAt(0)=='N'){
                s1=linea;
            }
            if(linea.length>2&&linea[2].charAt(0)=='V'){
                fTemp = new FileReader(url);
                bTemp = new BufferedReader(fTemp);
                lnTemp=0;
                s2=null;
                v=linea;                
                while((cadenaTemp = bTemp.readLine())!=null&& s2==null) {
                    lineaTemp= cadenaTemp.split(" "); 
                    if(lnTemp>=ln){
                        if(lineaTemp.length>2&&lineaTemp[2].charAt(0)=='N'){
                            s2=lineaTemp;
                        }   
                    }else{
                        lnTemp++;
                    }
                }
                
                if(s1[3].equals("0")){
                    s1[3]=Integer.toString(arbolNP.getId(s1[0]));
                }
                if(s2==null){
                    s2=s1;
                }
                if(s2[3].equals("0")){
                    s2[3]=Integer.toString(arbolNP.getId(s2[0]));
                }
                
                ids=new String[2];
                ids[0]=s2[3];
                ids[1]=s1[3];
                arbolTriV.insertar(Integer.parseInt(v[3]),ids);
                ids=new String[2];
                ids[0]=v[3];
                ids[1]=s2[3];
                arbolTriS1.insertar(Integer.parseInt(s1[3]),ids);
                ids=new String[2];
                ids[0]=s1[3];
                ids[1]=v[3];
                arbolTriS2.insertar(Integer.parseInt(s2[3]),ids);

            }            
            //System.out.println(linea[0]);

        }
        tri.crear(arbolTriS1.toString());
    }

    public void calcularNP()throws FileNotFoundException, IOException {
        String cadena;
        int cantidadNP=-1;
        idNP.clear();
        String[] linea; 
        FileReader f = new FileReader(url);
        BufferedReader b = new BufferedReader(f);

        while((cadena = b.readLine())!=null) {
            linea= cadena.split(" "); 
            if(linea.length>2&&linea[2].charAt(0)=='N'&&linea[3].matches("0")){
                idNP.add(cantidadNP);
                cantidadNP--;
            }

        }
        Collections.shuffle(idNP);
    }

    public String buscarTripleta(String[]campos,boolean nP1,boolean nP2){
        String tripletas="";
        String lineas[];
        String ids[];
        ArrayList<String> traduccion;
        int id1;
        String id2;
        if(campos[0]==""){
            id1=arbolV.getId(campos[1]);
            if(nP2){
                id2=String.format("%08d",arbolNP.getId(campos[2]));
            }else{
                id2=String.format("%08d",arbolS.getId(campos[2].toLowerCase()));
            }
            tripletas+=arbolTriV.buscarTripleta(id1,id2);
            lineas=tripletas.split("\n");
            // if(lineas.length<=1){
                // lineas=tripletas;
            // }
            tripletas="";
            for(int i=1;i<lineas.length;i++){
                ids=lineas[i].split(" ");  
                if(Integer.parseInt(ids[2])<0){
                    traduccion=arbolNP.getPalabras(Integer.parseInt(ids[2]));
                    for(int j=0;j<traduccion.size();j++){
                        tripletas+=traduccion.get(j)+"\t"+campos[1]+"\t"+campos[2]+"\n";
                    }                    
                }else{
                    traduccion=arbolS.getPalabras(Integer.parseInt(ids[2]));
                    for(int j=0;j<traduccion.size();j++){
                        tripletas+=traduccion.get(j)+"\t"+campos[1]+"\t"+campos[2]+"\n";
                    }
                }
            }
        }
        if(campos[1]==""){
            if(nP1){
                id2=String.format("%08d",arbolNP.getId(campos[0]));
            }else{
                id2=String.format("%08d",arbolS.getId(campos[0]));
            }
            if(nP2){                
                id1=arbolNP.getId(campos[2]);
            }else{
                id1=arbolS.getId(campos[2]);
            }
            tripletas+=arbolTriS2.buscarTripleta(id1,id2);
            lineas=tripletas.split("\n");
            // if(lineas.length<=1){
                // lineas=tripletas;
            // }
            tripletas="";
            for(int i=1;i<lineas.length;i++){
                ids=lineas[i].split(" ");  
                traduccion=arbolV.getPalabras(Integer.parseInt(ids[2]));
                for(int j=0;j<traduccion.size();j++){
                    tripletas+=campos[0]+"\t"+traduccion.get(j)+"\t"+campos[2]+"\n";
                }                    

            }
        }

        if(campos[2]==""){
            id2=String.format("%08d",arbolV.getId(campos[1].toLowerCase()));
            if(nP1){
                id1=arbolNP.getId(campos[0]);
            }else{
                id1=arbolS.getId(campos[0].toLowerCase());
            }
            tripletas+=arbolTriS1.buscarTripleta(id1,id2);
            lineas=tripletas.split("\n");
            // if(lineas.length<=1){
                // lineas={tripletas};
            // }
            tripletas="";
            for(int i=1;i<lineas.length;i++){
                ids=lineas[i].split(" ");  
                if(Integer.parseInt(ids[2])<0){
                    traduccion=arbolNP.getPalabras(Integer.parseInt(ids[2]));
                    for(int j=0;j<traduccion.size();j++){
                        tripletas+=campos[0]+"\t"+campos[1]+"\t"+traduccion.get(j)+"\n";
                    }                    
                }else{
                    traduccion=arbolS.getPalabras(Integer.parseInt(ids[2]));
                    for(int j=0;j<traduccion.size();j++){
                        tripletas+=campos[0]+"\t"+campos[1]+"\t"+traduccion.get(j)+"\n";
                    }
                }
            }
        }
        System.out.println(tripletas);
        return tripletas;
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
