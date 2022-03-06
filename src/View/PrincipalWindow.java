package View;

import java.awt.Component;
import java.awt.LayoutManager;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Control.Controller;



public class PrincipalWindow extends JFrame implements Actions{
	
	private Panel panel;
	private FileChooserPanel fileChooserPanel;
	private RunnableProgressBar runnableProgressBar;
	private Thread threadProgressBar;
	private URL url;
	private ImageIcon icon;
  
	public PrincipalWindow() {
		super("sistema de ordenamiento de archivos");
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
		    
	  fileChooserPanel = new FileChooserPanel();
		    
	  runnableProgressBar = new RunnableProgressBar();
	  threadProgressBar = new Thread(runnableProgressBar);
		    
	  try {
		  url = PrincipalWindow.class.getResource("/fileCabinet.jpg");
			      
		  icon = new ImageIcon(url);
		  setIconImage(icon.getImage());
	  } catch (Exception exception) {}
  }
  
  private void assign() {
	  add(panel);
  }
  
  public void assignController(Controller control) {
	  panel.assignController(control);
	  runnableProgressBar.assignController(control);
	  setVisible(true);
  }

  
  public void activate(String section) {
	  if (section.equals(Actions.CHECKBOXOPTIONS)) {
		  panel.activateOptions();
		  panel.setSize(584, 360);
		  setSize(600, 400);
	  } else if (section.equals(Actions.CHECKBOXADVANCEDOPTIONS)) {
		  panel.activateAdvancedOptions();
		  int y = (int)panel.getSize().getHeight() + 270;
		  panel.setSize(584, y);
		  y = (int)getSize().getHeight() + 250;
		  setSize(600, y);
	  } else if (section.equals(Actions.CHECKBOXCUSTOMIZEDDIRECTORY)) {
		  panel.activateCustomizedDirectory();
	  } else if (section.equals(Actions.CHECKBOXFILTER)) {
		  panel.activateFilter();
	  } else if (section.equals(Actions.PROGRESSBAR)) {
		  panel.activate();
		  runnableProgressBar.desactivate();
	  } 	
	  setLocationRelativeTo((Component)null);
	  repaint();
  }
  
  public void desactivate(String section) {
	  if (section.equals(Actions.CHECKBOXOPTIONS)) {
		  panel.desactivateOptions();
		  panel.setSize(584, 280);
		  setSize(600, 310);
	  } else if (section.equals(Actions.CHECKBOXADVANCEDOPTIONS)) {
		  panel.desactivateAdvancedOptions();
		  int y = (int)panel.getSize().getHeight() - 230;
		  panel.setSize(584, y);
		  y = (int)getSize().getHeight() - 230;
		  setSize(600, y);
	  } else if (section.equals(Actions.CHECKBOXCUSTOMIZEDDIRECTORY)) {
		  panel.desactivateCustomizedDirectory();
	  } else if (section.equals(Actions.CHECKBOXFILTER)) {
		  panel.desactivateFilter();
	  } else if (section.equals(Actions.PROGRESSBAR)) {
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
	  if (section.equals(Actions.ORIGINFILECHOOSER) || section.equals(Actions.DESTINYFILECHOOSER)) {
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
	  }else if (section.equals(Actions.CHECKBOXOPTIONS) || section.equals(Actions.CHECKBOXADVANCEDOPTIONS) || 
			  section.equals(Actions.CHECKBOXCUSTOMIZEDDIRECTORY) || section.equals(Actions.CHECKBOXFILTER)) {
		  return panel.getCbOpciones();
	  }
	  return null;
  }
  
  public void show(String section, String[] data) {
	  switch (section) { 
			case Actions.DESTINYFILECHOOSER: 
				panel.setDestinyPath(data[0]); 
				break;
        
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
		  panel.continueTo();//checkea que esten todos los campos diligenciados para asi habilitar el boton de continuar
	  } 
  }
}
