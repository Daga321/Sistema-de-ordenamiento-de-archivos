 package View;
 
 import javax.swing.JFileChooser;
 import javax.swing.JFrame;
 import javax.swing.JPanel;
 
 public class FileChooserPanel extends JPanel {
   private JFileChooser fcPath;
   
   public FileChooserPanel() {
     initialize();
     assign();
   }
   
   private void initialize() {
     fcPath = new JFileChooser();
     fcPath.setBounds(80, 15, 1000, 700);
     fcPath.setMultiSelectionEnabled(false);
     fcPath.setFileSelectionMode(1);
   }
   
   private void assign() {
     add(fcPath);
   }
   
   public int show(JFrame frame) {
     return fcPath.showOpenDialog(frame);
   }
   
   public String getPath() {
     return fcPath.getSelectedFile().getAbsolutePath();
   }
}