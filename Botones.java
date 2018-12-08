import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
public class Botones extends JPanel implements ActionListener {
    public static final String nombre[]={"CARGAR ARCHIVO","GENERAR TRIPLETAS"};
    public JButton boton[];
    public Contenido contenido;
    public JFrame madre;
    public Analizador analizador;
    public Botones(JFrame madre,Contenido contenido) {
       this.contenido = contenido;
       this.madre = madre;
       boton = new JButton[nombre.length];
       for(int i=0;i<nombre.length;++i){ 
          boton[i] = new JButton( nombre[i]);
          boton[i].addActionListener(this);
          add(boton[i]);
       }
    }

    public void actionPerformed(ActionEvent evento){
       switch(evento.getActionCommand()){ 
          case "CARGAR ARCHIVO":
            String url = contenido.getUrl();
            try{
            analizador = new Analizador(url);
        }
        catch(IOException e){
            System.out.print("Ha habido un error con el archivo");
        }
          break;
          
          case "GENERAR TRIPLETAS":
             String[] textoUsuario;
             boolean primeroEsPropio;
             boolean segundoEsPropio;
             textoUsuario = contenido.getTextoUsuario();
             primeroEsPropio = contenido.getPrimerCheckBox();
             segundoEsPropio = contenido.getSegundoCheckBox();
             String tripletas = analizador.buscarTripleta(textoUsuario, primeroEsPropio,segundoEsPropio);
             System.out.print(tripletas); //por si acaso
             JTextArea resultado = new JTextArea(tripletas);
             contenido.add(resultado);
          break;     
       }
       //madre.repaint();
    }
}
