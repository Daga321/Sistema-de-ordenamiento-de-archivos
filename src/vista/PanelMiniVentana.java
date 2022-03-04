 package vista;
 
 import javax.swing.JFileChooser;
 import javax.swing.JFrame;
 import javax.swing.JPanel;
 
 public class PanelMiniVentana extends JPanel {
   private JFileChooser fcUbicacion;
   
   public PanelMiniVentana() {
     inicializar();
     agregar();
   }
   
   private void inicializar() {
     fcUbicacion = new JFileChooser();
     fcUbicacion.setBounds(80, 15, 1000, 700);
     fcUbicacion.setMultiSelectionEnabled(false);
     fcUbicacion.setFileSelectionMode(1);
   }
   
   private void agregar() {
     add(fcUbicacion);
   }
   
   public int mostrar(JFrame frame) {
     return fcUbicacion.showOpenDialog(frame);
   }
   
   public String obtenerRuta() {
     return fcUbicacion.getSelectedFile().getAbsolutePath();
   }
}