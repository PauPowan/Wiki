
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.io.*;

import java.util.ArrayList;
import java.util.Collections;
public class prueba
{
    // instance variables - replace the example below with your own
    private int x;
    ArrayList<String> arrayList;
    /**
     * Constructor for objects of class prueba
     */
    public prueba()throws IOException
    {        
        String cadena;
        String cadenaTemp;
        int ln=0;
        String [] ids=new String[2];
        String[] linea;
        String[] lineaTemp;
        String[] s1=new String[4];
        String[] v=new String[4];
        String[] s2=new String[4];;
        FileReader f = new FileReader("eje.txt");
        BufferedReader b = new BufferedReader(f);
        BufferedReader bTemp = new BufferedReader(f);
        while((cadena = b.readLine())!=null) {
            ln++;
            if(ln==2){                
                System.out.println(cadena);
            }
        }
        // arrayList = new ArrayList<String>();

        // // Guardo datos en el ArrayList
        // arrayList.add("Z"); 	
        // arrayList.add("e");
        // arrayList.add("q");	
        // arrayList.add("h");
        // arrayList.add("B");	
        // arrayList.add("a");
        // arrayList.add("A");
        // arrayList.add("Z"); 	
        // arrayList.add("e");
        // arrayList.add("q");	
        // arrayList.add("h");
        // arrayList.add("B");	
        // arrayList.add("a");
        // arrayList.add("A");
        // Collections.sort(arrayList);
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public int sampleMethod(int y)
    {
        // put your code here
        return x + y;
    }
}
