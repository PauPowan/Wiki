import javax.swing.JFrame; 
import javax.swing.ImageIcon;
import java.awt.Container;
import java.awt.BorderLayout;


public class Ventana extends JFrame {
    private Contenido contenido;
    private Botones botones;
    
    public Ventana() {
        super("Tarea programada 4");
        setSize(600,300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setIconImage((new ImageIcon("foto.jpg")).getImage());
        contenido = new Contenido();
        Container c = getContentPane();
        c.add(BorderLayout.CENTER, contenido );
        Botones botones = new Botones(this,contenido);
        c.add(BorderLayout.SOUTH, botones);
        setVisible(true);
    }
}
