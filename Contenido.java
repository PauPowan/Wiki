import javax.swing.*;
import java.awt.*;

public class Contenido extends JPanel{
    private GridLayout camposTexto = new GridLayout(6,3,15,15); //establece un formato de tabla con 3 columnas y 5 píxeles de separación entre cada elemento 
    private JTextField textField0;
    private JTextField textField1;
    private JTextField textField2;
    private JCheckBox checkBox0;
    private JCheckBox checkBox1;
    private JTextField archivo;
    
    public Contenido(){
       super();
       setLayout(camposTexto);
       JLabel padding0 = new JLabel("");
       archivo = new JTextField(40);
       JLabel padding1 = new JLabel("");
       add(padding0);
       add(archivo);
       add(padding1);
       JLabel label0 = new JLabel("");
       JLabel label1 = new JLabel("Nombre del archivo");
       JLabel label2 = new JLabel("");
       add(label0);
       add(label1);
       add(label2);
       JLabel label3 = new JLabel(" Sujeto:");
       JLabel label4 = new JLabel(" Verbo:");
       JLabel label5 = new JLabel(" Predicado:");
       add(label3);
       add(label4);
       add(label5);
       textField0 = new JTextField(40);
       textField1 = new JTextField(40);
       textField2 = new JTextField(40);
       add(textField0);
       add(textField1);
       add(textField2);
       checkBox0 = new JCheckBox("Sustantivo propio");
       JLabel label6 = new JLabel("");
       checkBox1 = new JCheckBox("Sustantivo propio");
       add(checkBox0);
       add(label6);
       add(checkBox1);
    }
    
    public String[] getTextoUsuario(){
        String[] respuesta = new String[3];
        respuesta[0] = textField0.getText();
        respuesta[1] = textField1.getText();
        respuesta[2] = textField2.getText();
        //quemado y sin ciclo porque son exactos y dependientes de la manera en que se hizo la interfaz
        return respuesta;
    }
    
    public boolean getPrimerCheckBox(){
        return checkBox0.isSelected();
    }
    
    public boolean getSegundoCheckBox(){
        return checkBox1.isSelected();
    }
    
    public String getUrl(){
        String respuesta;
        respuesta = archivo.getText();
        return respuesta;
    }
}
