package ejecucion;

import control.Control;
import vista.Acciones;
import vista.VentanaPrincipal;

public class Run {
  public static void main(String[] args) {
	VentanaPrincipal ventanaPrincipal = new VentanaPrincipal();
	Control control = new Control((Acciones)ventanaPrincipal);
	ventanaPrincipal.asignarListener(control);
  }
}
