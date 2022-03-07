 package Control;
 
 import java.awt.event.ActionEvent;
 import java.awt.event.ActionListener;
 import java.awt.event.MouseEvent;
 import java.awt.event.MouseListener;

import Model.Management;
import View.Actions;
 
 
 
 
 public class Controller implements ActionListener, MouseListener {
   Actions actions;
   Management management;
   
   public Controller(Actions actions) {
	   this.actions = actions;
		management = new Management();
   }
   
   public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()){ 
	 
			case Actions.CONTINUE:
	    	   	management.generateFiles(actions.capture(Actions.CONTINUE));
	         	actions.desactivate(Actions.PROGRESSBAR); 
	        break;
		    
			case Actions.ORIGINFILECHOOSER:
	    	   if (actions.capture(Actions.ORIGINFILECHOOSER)[0].equals("0")) { 
	    		   actions.show(Actions.ORIGINFILECHOOSER, actions.capture(Actions.PATH)); 
	    		   actions.message(""); 
	    	   }  
	    	break; 
	    }  
	} 
   public void mouseExited(MouseEvent e) { 
	   actions.message(""); 
   }
   
   public void mouseClicked(MouseEvent e) {}
   public void mouseEntered(MouseEvent e) {}
   public void mousePressed(MouseEvent e) {}
   public void mouseReleased(MouseEvent e) {}
   
   public void progressbar() {
	   actions.show(Actions.PROGRESSBAR, management.getProgress());
	   if (!management.getStatus()) {
		   actions.show(Actions.PROGRESSBAR, management.getProgress());
		   actions.message("La operacion finalizo exitosamente en "+management.getTime());
		   actions.activate(Actions.PROGRESSBAR);
	   } 
   }
 }
