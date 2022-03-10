package Execution;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import Control.Controller;
import View.Actions;
import View.PrincipalWindow;

public class Run {
  public static void main(String[] args) {
	try {
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	} catch (ClassNotFoundException 
			| InstantiationException
			| IllegalAccessException 
			| UnsupportedLookAndFeelException e) {
	}
	PrincipalWindow principalWindow = new PrincipalWindow();
	Controller controller = new Controller((Actions)principalWindow);
	principalWindow.asignarListener(controller);
  }
}
