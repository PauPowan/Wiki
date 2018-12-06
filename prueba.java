
/**
 * Write a description of class prueba here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
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
    public prueba()
    {
        arrayList = new ArrayList<String>();

        // Guardo datos en el ArrayList
        arrayList.add("Z"); 	
        arrayList.add("e");
        arrayList.add("q");	
        arrayList.add("h");
        arrayList.add("B");	
        arrayList.add("a");
        arrayList.add("A");
       arrayList.add("Z"); 	
        arrayList.add("e");
        arrayList.add("q");	
        arrayList.add("h");
        arrayList.add("B");	
        arrayList.add("a");
        arrayList.add("A");
        Collections.sort(arrayList);
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
