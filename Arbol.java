import java.util.*;
import java.io.*;

public class Arbol {
    public static final int IZQ = 0;
    public static final int DER = 1;
    public static final int NODOS = 2;
    
    private class Nodo {
        public int id;
        public ArrayList<String> palabras;
        public Nodo hijo[];

        public Nodo(int id,String palabra){
            this.id= id;
            this.palabras.add(palabra);
            Collections.sort(palabras);
            hijo = new Nodo[NODOS];
            // hijo[IZQ] y hijo[DER] son null
        }

        public String toString(){
            String tira="";
            int lado = IZQ;
            if(hijo[lado]!=null){
                tira += hijo[lado].toString();   
            }
            tira+="\n"+elemento;
            lado = DER;
            if(hijo[lado]!=null){
                tira += hijo[lado].toString();   
            }
            return tira;
        }

        public void insertar(int id,String palabra){
            int lado = IZQ;
            if(this.id==id){
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
            this.palabras.add(palabra);
            Collections.sort(palabras);
            }
        }     
    }
    private Nodo raiz;
    public void insertar(String elemento){
        if(raiz==null){
            raiz = new Nodo(elemento); 
        }
        else {
            raiz.insertar(elemento); 
        }
    }

    public String toString(){
        String tira ="";
        if(raiz!=null){
            tira+=raiz.toString(); 
        }
        return tira;
    }
}
