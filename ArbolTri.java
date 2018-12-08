import java.util.*;
import java.io.*;
//Estructura de datos especial para las tripletas. Un árbol con vectores de elementos
public class ArbolTri {
    public static final int IZQ = 0;
    public static final int DER = 1;
    public static final int NODOS = 2;

    private class Nodo {
        public int id;
        public ArrayList<String[]> palabras;
        public Nodo hijo[];

        public Nodo(int id,String[] palabra){
            this.id= id;
            this.palabras= new ArrayList<String[]>();
            this.palabras.add(palabra);

            hijo = new Nodo[NODOS];

        }

        public String toString(){
            String tira="";
            int lado = IZQ;
            if(hijo[lado]!=null){
                tira += hijo[lado].toString();   
            }
            for(int i=0;i<palabras.size();i++){
                tira+=this.id+"\t"+this.palabras.get(i)[0]+"\t"+this.palabras.get(i)[1]+"\n";
            }
            lado = DER;
            if(hijo[lado]!=null){
                tira += hijo[lado].toString();   
            }
            return tira;
        }

        public String buscarTripleta(int id1,String id2){
            String tripletas="";
            int lado = IZQ;
            if(this.id!=id1){
                if(id1>this.id){
                    lado = DER;
                }
                if(hijo[lado]!=null){
                    tripletas+=hijo[lado].buscarTripleta(id1,id2);
                }

            }else{
                for(int i=0;i<palabras.size();i++){
                    if(palabras.get(i)[0].equals(id2)){
                        tripletas+="\n"+id+" "+id2+" "+palabras.get(i)[1];
                    }
                }

            }
            return tripletas;        
        }


        public void insertar(int id,String palabra[]){
            int lado = IZQ;
            boolean esta=false;
            if(this.id!=id){
                if(id>this.id){
                    lado = DER;
                }
                if(hijo[lado]==null){
                    hijo[lado] = new Nodo(id,palabra);   
                }
                else {
                    hijo[lado].insertar(id,palabra);   
                }
            }else{
                for(int i=0;i<palabras.size();i++){
                    if(palabras.get(i)[0].equals(palabra[0])&&palabras.get(i)[1].equals(palabra[1])){
                        esta=true;
                    }
                }
                if(!esta){
                    this.palabras.add(palabra);
                }
            }
        }     
    }
    private Nodo raiz;
    public void insertar(int id,String[] palabra){
        if(raiz==null){
            raiz = new Nodo(id,palabra); 
        }
        else {
            raiz.insertar(id,palabra); 
        }
    }

    public String toString(){
        String tira ="";
        if(raiz!=null){
            tira+=raiz.toString(); 
        }
        return tira;
    }

    public String buscarTripleta(int id1,String id2){
        String tripletas="";
        tripletas=raiz.buscarTripleta( id1,id2);
        return tripletas;        
    }

}
