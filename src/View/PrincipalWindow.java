package View;

import java.awt.Component;
import java.awt.LayoutManager;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Control.Controller;



public class PrincipalWindow extends JFrame implements Actions{
	Panel panel;
	FileChooserPanel fileChooserPanel;
	Thread threadProgressBar;
	RunnableProgressBar runnableProgressBar;
	URL url;
	ImageIcon icono;
  
	public PrincipalWindow() {
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
	    
	    
	runnableProgressBar = new RunnableProgressBar();
	threadProgressBar = new Thread(runnableProgressBar);
	fileChooserPanel = new FileChooserPanel();
	    
	try {
		url = PrincipalWindow.class.getResource("/archivador.png");
		      
		icono = new ImageIcon(url);
		setIconImage(icono.getImage());
	} catch (Exception exception) {}
  }
  
  private void agregar() {
	add(panel);
  }
  
  public void asignarListener(Controller controller) {
	panel.asignarControl(controller);
	runnableProgressBar.asignarControl(controller);
	setVisible(true);
  }

  
  public void activar(String seccion) {
	if (seccion.equals("progressbar")) {
		panel.activar();
		runnableProgressBar.finalizar();
	} 
	setLocationRelativeTo(null);
	repaint();
  }

  
  public void desactivar(String seccion) {
	  if (seccion.equals("progressbar")) {
		panel.desactivar();
		try {
			threadProgressBar.start();
		} catch (Exception e) {
			runnableProgressBar.iniciar();
      } 
    } 
	setLocationRelativeTo((Component)null);
	 repaint();
  }


  
  public String[] capturar(String seccion) {
	if (seccion.equals("filechooser") ) {
		String[] datos = {
		""+fileChooserPanel.mostrar(this)
		};
		return datos;
	}else if (seccion.equals("ubicacion")) {
		String[] datos = {
				fileChooserPanel.obtenerRuta()
		};
		return datos;
	}else  if (seccion.equals("continuar")) {
		return panel.capturar(); 
	}
		return null;
  }
  
  public void mostrar(String seccion, String[] datos) {
	switch (seccion) { 
		case "progressbar":
			panel.progressBar(datos); 
        break;
        
		case "filechooser":
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
