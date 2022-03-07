package View;

import Control.Controller;

public class RunnableProgressBar implements Runnable{
  private Controller controller;
  private boolean status;
  
  public void asignarControl(Controller controller) {
	this.controller = controller;
  }

  
  public void run() {
	status = true;
    try {
      while (true) {
    	  Thread.sleep(200);
    	  while (status) {
			  controller.progressbar();
//			  Thread.sleep(200);
    	  } 
      } 
	} catch (InterruptedException interruptedException) {
      return;
    } 
  } 
  
  public void activate() {
	  status = true;
  }
  
  public void desactivate() {
	  status = false;
  }
}