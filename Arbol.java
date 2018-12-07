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
        boolean tripleta;
        public Nodo(int id,String palabra,boolean tripleta){
            this.id= id;
            this.palabras= new ArrayList<String>();
            this.palabras.add(palabra);
            System.out.println(palabras.size());
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
                tira+="\n"+this.id+"\t"+palabras.get(i);

            }
            lado = DER;
            if(hijo[lado]!=null){
                tira += hijo[lado].toString();   
            }
            return tira;
        }

        public int getId(String busqueda){
            int id=0;
            int lado = IZQ;
            
            if(hijo[lado]!=null){
                    id=hijo[lado].getId(busqueda);   
            }
            if(id==0&&this.palabras.contains(busqueda)){
                    id=this.id;
            }
            lado=DER;
            if(id==0&&hijo[lado]!=null){
                id=hijo[lado].getId(busqueda); 
            }
            return id;
        }

        public void insertar(int id,String palabra,boolean tripleta){
            int lado = IZQ;
            if(this.id!=id){
                if(id>this.id){
                    lado = DER;
                }
                if(hijo[lado]==null){
                    hijo[lado] = new Nodo(id,palabra,tripleta);   
                }
                else {
                    hijo[lado].insertar(id,palabra,tripleta);   
                }
            }else{
                if(tripleta||!palabras.contains(palabra)){
                    this.palabras.add(palabra);
                    Collections.sort(palabras);
                }
            }
        }     
    }
    private Nodo raiz;
    public void insertar(int id,String palabra,boolean tripleta){
        if(raiz==null){
            raiz = new Nodo(id,palabra,tripleta); 
        }
        else {
            raiz.insertar(id,palabra,tripleta); 
        }
    }

    public String toString(){
        String tira ="";
        if(raiz!=null){
            tira+=raiz.toString(); 
        }
        return tira;
    }

    public int getId(String busqueda){
        int id=0;
        raiz.getId(busqueda);
        return id;
    }
}
