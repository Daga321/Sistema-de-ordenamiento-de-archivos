package vista;

import control.Control;
import ejecucion.HiloProgressBar;
import java.awt.Component;
import java.awt.LayoutManager;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;



public class VentanaPrincipal extends JFrame implements Acciones{
	Panel panel;
	PanelMiniVentana panelMiniVentana;
	HiloProgressBar hiloProgressBar;
	URL url;
	ImageIcon icono;
  
	public VentanaPrincipal() {
	super("sistema de ordenamiento de archivos");
	setLayout((LayoutManager)null);
	setDefaultCloseOperation(3);
	setSize(600, 310);
	setLocationRelativeTo((Component)null);
	setResizable(false);
	inicializar();
	agregar();
  }
  
  private void inicializar() {
	panel = new Panel();
	panel.setBounds(0, 0, 584, 280);
	    
	panelMiniVentana = new PanelMiniVentana();
	    
	hiloProgressBar = new HiloProgressBar();
	    
	try {
		url = VentanaPrincipal.class.getResource("/archivador.png");
		      
		icono = new ImageIcon(url);
		setIconImage(icono.getImage());
	} catch (Exception exception) {}
  }
  
  private void agregar() {
	add(panel);
  }
  
  public void asignarListener(Control control) {
	panel.asignarControl(control);
	hiloProgressBar.asignarControl(control);
	setVisible(true);
  }

  
  public void activar(String seccion) {
	if (seccion.equals("checkboxopciones")) {
		panel.activarOpciones();
		panel.setSize(584, 360);
		setSize(600, 400);
	} else if (seccion.equals("checkboxopcionesavanzadas")) {
		panel.activarOpcionesAvanzadas();
		int y = (int)panel.getSize().getHeight() + 270;
		panel.setSize(584, y);
		y = (int)getSize().getHeight() + 250;
		setSize(600, y);
	} else if (seccion.equals("checkboxcarpetapersonalizada")) {
		panel.activarCarpetaPersonalizada();
	} else if (seccion.equals("checkboxfiltro")) {
		panel.activarFiltro();
	} else if (seccion.equals("progressbar")) {
		panel.activar();
		hiloProgressBar.finalizar();
	} 
	setLocationRelativeTo((Component)null);
	repaint();
  }

  
  public void desactivar(String seccion) {
	  if (seccion.equals("checkboxopciones")) {
		  	panel.desactivarOpciones();
		  	panel.setSize(584, 280);
		  		setSize(600, 310);
	  } else if (seccion.equals("checkboxopcionesavanzadas")) {
			panel.desactivarOpcionesAvanzadas();
			int y = (int)panel.getSize().getHeight() - 230;
			panel.setSize(584, y);
			y = (int)getSize().getHeight() - 230;
			setSize(600, y);
	} else if (seccion.equals("checkboxcarpetapersonalizada")) {
			panel.desactivarCarpetaPersonalizada();
	} else if (seccion.equals("checkboxfiltro")) {
			panel.desactivarFiltro();
	} else if (seccion.equals("progressbar")) {
		panel.desactivar();
		try {
			hiloProgressBar.start();
		} catch (Exception e) {
			hiloProgressBar.iniciar();
      } 
    } 
	setLocationRelativeTo((Component)null);
	 repaint();
  }


  
  public String[] capturar(String seccion) {
	if (seccion.equals("filechooserorigen") || seccion.equals("filechooserdestino")) {
		String[] datos = {
		""+panelMiniVentana.mostrar(this)
		};
		return datos;
	}else if (seccion.equals("ubicacion")) {
		String[] datos = {
		 panelMiniVentana.obtenerRuta()
		};
		return datos;
	}else  if (seccion.equals("continuar")) {
		return panel.capturar(); 
	}else if (seccion.equals("checkboxopciones") || seccion.equals("checkboxopcionesavanzadas") || 
		seccion.equals("checkboxcarpetapersonalizada") || seccion.equals("checkboxfiltro")) {
		return panel.getCbOpciones();
	}
	return null;
  }
  
  public void mostrar(String seccion, String[] datos) {
	switch (seccion) { 
		case "filechooserdestino": 
			panel.setUbicacionDestino(datos[0]); 
        break;
		case "progressbar":
			panel.progressBar(datos); 
        break;
		case "filechooserorigen":
			panel.setUbicacionOrigen(datos[0]); 
		break; 
	}
	repaint();
  }

  
  public void mensaje(String mensaje) {
	if (!mensaje.equals("")) {
		JOptionPane.showMessageDialog(null, mensaje);
    } else {
    	panel.continuar();
    } 
  }
}
