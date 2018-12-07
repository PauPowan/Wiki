import java.util.*;
import java.io.*;

public class ArbolTri {
    public static final int IZQ = 0;
    public static final int DER = 1;
    public static final int NODOS = 2;

    private class Nodo {
        public int id;
        public ArrayList<String[]> palabras;
        public Nodo hijo[];
        boolean tripleta;
        public Nodo(int id,String[] palabra){
            this.id= id;
            this.palabras= new ArrayList<String[]>();
            this.palabras.add(palabra);
            this.tripleta=tripleta;
            hijo = new Nodo[NODOS];
            // hijo[IZQ] y hijo[DER] son null
        }

        public String toString(){
            String tira="";
            int lado = IZQ;
            if(hijo[lado]!=null){
                tira += hijo[lado].toString();   
            }
            for(int i=0;i<palabras.size();i++){
                tira+="\n"+this.id+"\t"+palabras.get(i)[0]+"\t"+palabras.get(i)[1];
            }
            lado = DER;
            if(hijo[lado]!=null){
                tira += hijo[lado].toString();   
            }
            return tira;
        }

        // public int getId(int id,String busqueda){

            // int lado = IZQ;            
            // if(hijo[lado]!=null){
                // id=hijo[lado].getId(id,busqueda);   
            // }
            // if(id==0&&this.palabras.contains(busqueda)){
                // id=this.id;
            // }
            // lado=DER;
            // if(id==0&&hijo[lado]!=null){
                // id=hijo[lado].getId(id,busqueda); 
            // }
            // return id;
        // }

        // // // // public ArrayList getPalabras(int id){
            // // // // ArrayList<String> busqueda=new ArrayList<String>();;
            // // // // int lado=IZQ;
            // // // // if(this.id!=id){
                // // // // if(id>this.id){
                    // // // // lado = DER;
                // // // // }
                // // // // if(hijo[lado]!=null){
                    // // // // busqueda=hijo[lado].getPalabras(id);   
                // // // // }
            // // // // }else{
                // // // // busqueda = (ArrayList) this.palabras.clone();
            // // // // }

            // // // // return busqueda;
        // // // // }

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

    // public int getId(String busqueda){
        // int id=0;
        // id=raiz.getId(id,busqueda);
        // return id;
    // }

    // public String getPalabras(int id){
        // ArrayList<String> busqueda;
        // busqueda=raiz.getPalabras(id); 
        // String palabras="";
        // for(int i=0;i<busqueda.size();i++){
            // palabras+=busqueda.get(i)+"\n";
        // }
        // return palabras;
    // }
}