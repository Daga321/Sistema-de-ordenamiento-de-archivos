package View;

import java.awt.Component;
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
		super("sistema de ordenamiento de archivos - Un hilos");
		setLayout(null);
		setDefaultCloseOperation(3);
		setSize(570, 410);
		setLocationRelativeTo(null);
		setResizable(false);
		initialize();
		assign();
	  }
  
  private void initialize() {
	  panel = new Panel();
	  panel.setBounds(0, 0, 570, 410);
		    
	  fileChooserPanel = new FileChooserPanel();
		    
	  runnableProgressBar = new RunnableProgressBar();
	  threadProgressBar = new Thread(runnableProgressBar);
		    
	  try {
		  url = PrincipalWindow.class.getResource("/icono.png");
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
		  if (panel.getCbOpciones()[1].equals("true")) {
			  panel.setSize(570, 833);
			  setSize(this.getWidth(), 833);
		  }else {
			  panel.setSize(570, 500);
			  setSize(570, 500); 
		  }
		  
	  } else if (section.equals(Actions.CHECKBOXADVANCEDOPTIONS)) {
		  panel.activateAdvancedOptions();
		  if (panel.getCbOpciones()[0].equals("false")) {
			  panel.setSize(570, 833);
			  setSize(this.getWidth(), 833);
		  }else {
			  panel.setSize(570, 743);
			  setSize(570, 743);  
		  }
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
		  if (panel.getCbOpciones()[1].equals("true")) {
			  panel.setSize(570, 743);
			  setSize(570, 743);
		  }else {
			  panel.setSize(570, 410);
			  setSize(570, 410);
		  }
	  } else if (section.equals(Actions.CHECKBOXADVANCEDOPTIONS)) {
		  panel.desactivateAdvancedOptions();
		  if (panel.getCbOpciones()[0].equals("false")) {
			  panel.setSize(570, 500);
			  setSize(570, 500);
		  }else {
			  panel.setSize(570, 410);
			  setSize(570, 410);
		  }
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
