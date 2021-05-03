/*    */ package vista;
/*    */ 
/*    */ import javax.swing.JFileChooser;
/*    */ import javax.swing.JFrame;
/*    */ import javax.swing.JPanel;
/*    */ 
/*    */ public class PanelMiniVentana extends JPanel {
/*    */   private JFileChooser fcUbicacion;
/*    */   
/*    */   public PanelMiniVentana() {
/* 11 */     inicializar();
/* 12 */     agregar();
/*    */   }
/*    */   
/*    */   private void inicializar() {
/* 16 */     fcUbicacion = new JFileChooser();
/* 17 */     fcUbicacion.setBounds(80, 15, 1000, 700);
/* 18 */     fcUbicacion.setMultiSelectionEnabled(false);
/* 19 */     fcUbicacion.setFileSelectionMode(1);
/*    */   }
/*    */   
/*    */   private void agregar() {
/* 23 */     add(fcUbicacion);
/*    */   }
/*    */   
/*    */   public int mostrar(JFrame frame) {
/* 27 */     return fcUbicacion.showOpenDialog(frame);
/*    */   }
/*    */   
/*    */   public String obtenerRuta() {
/* 31 */     return fcUbicacion.getSelectedFile().getAbsolutePath();
/*    */   }
/*    */ }


/* Location:              C:\Users\Daga\Desktop\SOA\!\vista\PanelMiniVentana.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */