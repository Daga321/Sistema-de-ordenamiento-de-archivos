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
			case Actions.CHECKBOXOPTIONS:
				if (actions.capture(Actions.CHECKBOXOPTIONS)[0].equals("true")) {
					actions.desactivate(Actions.CHECKBOXOPTIONS);
				} else {
					actions.activate(Actions.CHECKBOXOPTIONS);
				} 
				actions.message("");
			break;
			case Actions.CONTINUE:
				actions.desactivate(Actions.PROGRESSBAR); 
	    	   	management.organize(actions.capture(Actions.CONTINUE));
	        break;
	        
	       	case Actions.CHECKBOXCUSTOMIZEDDIRECTORY: 
	       		if (actions.capture(Actions.CHECKBOXCUSTOMIZEDDIRECTORY)[2].equals("true")){ 
	       			actions.activate(Actions.CHECKBOXCUSTOMIZEDDIRECTORY); 
	       		}else{ 
	       			actions.desactivate(Actions.CHECKBOXCUSTOMIZEDDIRECTORY); 
	       		}  
	       		actions.message(""); 
	    	break;
	    	
	       	case Actions.DESTINYFILECHOOSER:
	       		if (actions.capture(Actions.DESTINYFILECHOOSER)[0].equals("0")) {
	       			actions.show(Actions.DESTINYFILECHOOSER, actions.capture(Actions.PATH)); 
	       			actions.message(""); 
	       		}  
	        break;
	        
	       	case Actions.CHECKBOXADVANCEDOPTIONS:
	       		if (actions.capture(Actions.CHECKBOXADVANCEDOPTIONS)[1].equals("true")) { 
	       			actions.activate(Actions.CHECKBOXADVANCEDOPTIONS); 
	       		} else { 
	       			actions.desactivate(Actions.CHECKBOXADVANCEDOPTIONS); 
	       		}  
	       		actions.message(""); 
	       	break;
	       	
	       case Actions.CHECKBOXFILTER:
		        if (actions.capture(Actions.CHECKBOXFILTER)[3].equals("true")) { 
		        	actions.activate(Actions.CHECKBOXFILTER); 
		        } else { 
		        	actions.desactivate(Actions.CHECKBOXFILTER); 
		        }  
		        actions.message(""); 
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
