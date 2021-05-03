 package control;
 
 import java.awt.event.ActionEvent;
 import java.awt.event.ActionListener;
 import java.awt.event.MouseEvent;
 import java.awt.event.MouseListener;
 import modelo.Gestion;
 import vista.Acciones;
 
 
 
 
 public class Control implements ActionListener, MouseListener {
   Acciones acciones;
   Gestion gestion;
   
   public Control(Acciones acciones) {
	   this.acciones = acciones;
		gestion = new Gestion();
   }
   
   public void actionPerformed(ActionEvent e) {
	String str;
	switch ((str = e.getActionCommand()).hashCode()) { case -1766288873: if (!str.equals("checkboxopciones")) {
           break;
         }
	if (acciones.capturar("checkboxopciones")[0].equals("true")) {
		acciones.desactivar("checkboxopciones");
	} else {
		acciones.activar("checkboxopciones");
	} 
	if (acciones.capturar("checkboxopcionesavanzadas")[1].equals("true")) {
		acciones.activar("checkboxopcionesavanzadas");
	}
	acciones.mensaje("");
         break;
 
       case -403412945:
         if (!str.equals("continuar")) {
           break;
         }
         gestion.ordenar(acciones.capturar("continuar"));
         acciones.desactivar("progressbar"); break;
       case -47855974: if (!str.equals("checkboxcarpetapersonalizada"))
           break;  if (acciones.capturar("checkboxcarpetapersonalizada")[2].equals("true")) { acciones.activar("checkboxcarpetapersonalizada"); } else { acciones.desactivar("checkboxcarpetapersonalizada"); }  acciones.mensaje(""); break;
       case 53318889:
         if (!str.equals("filechooserdestino"))
           break;  if (acciones.capturar("filechooserdestino")[0].equals("0")) { acciones.mostrar("filechooserdestino", acciones.capturar("ubicacion")); acciones.mensaje(""); }  break;
       case 720628982:
         if (!str.equals("checkboxopcionesavanzadas"))
           break;  if (acciones.capturar("checkboxopcionesavanzadas")[1].equals("true")) { acciones.activar("checkboxopcionesavanzadas"); } else { acciones.desactivar("checkboxopcionesavanzadas"); }  acciones.mensaje(""); break;
       case 1124256395:
         if (!str.equals("checkboxfiltro"))
           break;  if (acciones.capturar("checkboxfiltro")[3].equals("true")) { acciones.activar("checkboxfiltro"); } else { acciones.desactivar("checkboxfiltro"); }  acciones.mensaje(""); break;
       case 1852356521:
         if (!str.equals("filechooserorigen"))
        	 break;  if (acciones.capturar("filechooserorigen")[0].equals("0")) { acciones.mostrar("filechooserorigen", acciones.capturar("ubicacion")); acciones.mensaje(""); }  break; }  } public void mouseExited(MouseEvent e) { acciones.mensaje(""); }
   
   public void mouseClicked(MouseEvent e) {}
   public void mouseEntered(MouseEvent e) {}
   
   public void mousePressed(MouseEvent e) {}
   
   public void mouseReleased(MouseEvent e) {}
   
   public void progressbar() {
	   acciones.mostrar("progressbar", gestion.obtenerProgreso());
	   if (!gestion.getEstado()) {
		   acciones.mensaje("La operacion ha finalizado exitozamente");
		   acciones.activar("progressbar");
	   } 
   }
 }
