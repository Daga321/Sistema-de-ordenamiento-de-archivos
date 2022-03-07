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
	super("SOA TEST");
	setLayout((LayoutManager)null);
	setDefaultCloseOperation(3);
	setSize(600, 310);
	setLocationRelativeTo((Component)null);
	setResizable(false);
	initialize();
	assign();
  }
  
  private void initialize() {
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
  
  private void assign() {
	add(panel);
  }
  
  public void asignarListener(Controller controller) {
	panel.asignarControl(controller);
	runnableProgressBar.asignarControl(controller);
	setVisible(true);
  }

  
  public void activate(String section) {
	if (section.equals(Actions.PROGRESSBAR)) {
		panel.activate();
		runnableProgressBar.desactivate();
	} 
	setLocationRelativeTo(null);
	repaint();
  }

  
  public void desactivate(String section) {
	  if (section.equals(Actions.PROGRESSBAR)) {
		panel.desactivate();
		try {
			threadProgressBar.start();
		} catch (Exception e) {
			runnableProgressBar.activate();
      } 
    } 
	setLocationRelativeTo((Component)null);
	 repaint();
  }


  
  public String[] capture(String section) {
	if (section.equals(Actions.ORIGINFILECHOOSER) ) {
		String[] data = {
		""+fileChooserPanel.show(this)
		};
		return data;
	}else if (section.equals(Actions.PATH)) {
		String[] data = {
				fileChooserPanel.getPath()
		};
		return data;
	}else  if (section.equals(Actions.CONTINUE)) {
		return panel.capture(); 
	}
		return null;
  }
  
  public void show(String section, String[] data) {
	switch (section) { 
		case Actions.PROGRESSBAR:
			panel.progressBar(data); 
        break;
        
		case Actions.ORIGINFILECHOOSER:
			panel.setOriginPath(data[0]); 
		break; 
	}
	repaint();
  }

  
  public void message(String message) {
	if (!message.equals("")) {
		JOptionPane.showMessageDialog(null, message);
    } else {
    	panel.continueTo();
    } 
  }
}
