package View;

import Control.Controller;

public class RunnableProgressBar implements Runnable{
  private Controller control;
  private boolean estado;
  
  public void asignarControl(Controller control) {
	this.control = control;
  }

  
  public void run() {
	estado = true;
    try {
      while (true) {
    	  Thread.sleep(200);
    	  while (estado) {
			  control.progressbar();
//			  Thread.sleep(200);
    	  } 
      } 
	} catch (InterruptedException interruptedException) {
      return;
    } 
  } 
  
  public void iniciar() {
	  estado = true;
  }
  
  public void finalizar() {
	  estado = false;
  }
}