package ejecucion;

import control.Control;

public class HiloProgressBar
  extends Thread {
  private Control control;
  private boolean estado;
  
  public void asignarControl(Control control) {
	this.control = control;
  }

  
  public void run() {
	super.run();
	estado = true;
    try {
      while (true) {
    	  Thread.sleep(1000);
    	  while (estado) {
			  control.progressbar();
			  Thread.sleep(200);
    	  } 
      } 
	} catch (InterruptedException interruptedException) {
      return;
    } 
  } public void iniciar() {
	  estado = true;
  }
  
  public void finalizar() {
	  estado = false;
  }
}