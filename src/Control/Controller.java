 package Control;
 
 import java.awt.event.ActionEvent;
 import java.awt.event.ActionListener;
 import java.awt.event.MouseEvent;
 import java.awt.event.MouseListener;

import Model.Management;
import View.Actions;
 
 
 
 
 public class Controller implements ActionListener, MouseListener {
   Actions acciones;
   Management gestion;
   
   public Controller(Actions acciones) {
	   this.acciones = acciones;
		gestion = new Management();
   }
   
   public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()){ 
	 
			case "continuar":
	    	   	gestion.generateFiles(acciones.capturar("continuar"));
	         	acciones.desactivar("progressbar"); 
	        break;
		   
	       case "filechooser":
	    	   if (acciones.capturar("filechooser")[0].equals("0")) { 
	    		   acciones.mostrar("filechooser", acciones.capturar("ubicacion")); 
	    		   acciones.mensaje(""); 
	    	   }  
	       break; 
	    }  
	} 
   public void mouseExited(MouseEvent e) { 
	   acciones.mensaje(""); 
   }
   
   public void mouseClicked(MouseEvent e) {}
   public void mouseEntered(MouseEvent e) {}
   public void mousePressed(MouseEvent e) {}
   public void mouseReleased(MouseEvent e) {}
   
   public void progressbar() {
	   acciones.mostrar("progressbar", gestion.obtenerProgreso());
	   if (!gestion.getStatus()) {
		   acciones.mensaje("La operacion ha finalizado exitozamente");
		   acciones.activar("progressbar");
	   } 
   }
 }
