package Execution;

import Control.Controller;
import View.Actions;
import View.PrincipalWindow;

public class Run {
  public static void main(String[] args) {
	PrincipalWindow principalWindow = new PrincipalWindow();
	Controller controller = new Controller((Actions)principalWindow);
	principalWindow.asignarListener(controller);
  }
}
