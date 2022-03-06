 package View;
 
 import javax.swing.JFileChooser;
 import javax.swing.JFrame;
 import javax.swing.JPanel;
 
 public class FileChooserPanel extends JPanel {
   private JFileChooser fcUbicacion;
   
   public FileChooserPanel() {
     initialize();
     assign();
   }
   
   private void initialize() {
     fcUbicacion = new JFileChooser();
     fcUbicacion.setBounds(80, 15, 1000, 700);
     fcUbicacion.setMultiSelectionEnabled(false);
     fcUbicacion.setFileSelectionMode(1);
   }
   
   private void assign() {
     add(fcUbicacion);
   }
   
   public int show(JFrame frame) {
     return fcUbicacion.showOpenDialog(frame);
   }
   
   public String getPath() {
     return fcUbicacion.getSelectedFile().getAbsolutePath();
   }
}