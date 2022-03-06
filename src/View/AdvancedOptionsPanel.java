package View;

import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Control.Controller;

public class AdvancedOptionsPanel extends JPanel {
	 private JLabel txtDirectoryOption;
	 private ButtonGroup bgDirectoryOption;
	 private JCheckBox cbDirectoryOption1;
	 private JCheckBox cbDirectoryOption2;
	 private JCheckBox cbDirectoryOption3;
	 private JLabel txtCustomizedDirectory;
	 private JTextField tfCustomizedDirectory;
	 private JCheckBox cbFilter;
	 private JLabel txtFilter;
	 private JTextField tfFilter;
  
  public AdvancedOptionsPanel() {
	  setLayout((LayoutManager)null);
	  inicializar();
	  assign();
  }
  
  private void inicializar() {
	  txtDirectoryOption = new JLabel("<html><body>Selecciona una opcion de guardado</body></html>");
	  txtDirectoryOption.setBounds(0, 10, 424, 20);
    
	  bgDirectoryOption = new ButtonGroup();
    
	  cbDirectoryOption1 = new JCheckBox("<html><body>Ordenar por carpetas año/mes</body></html>");
	  cbDirectoryOption1.setBounds(0, 30, 141, 45);
	  cbDirectoryOption1.setSelected(true);
	  bgDirectoryOption.add(cbDirectoryOption1);
    
	  cbDirectoryOption2 = new JCheckBox("<html><body>Ordenar por carpetas año</body></html>");
	  cbDirectoryOption2.setBounds(141, 30, 141, 45);
	  bgDirectoryOption.add(cbDirectoryOption2);
    
	  cbDirectoryOption3 = new JCheckBox("<html><body>Ordenar en una carpeta de nombre personalizado</body></html>");
	  cbDirectoryOption3.setBounds(282, 30, 141, 45);
	  bgDirectoryOption.add(cbDirectoryOption3);
    
	  txtCustomizedDirectory = new JLabel("<html><body>por favor ingresa el nombre que deseas para la carpeta</body></html>");
	  txtCustomizedDirectory.setForeground(Color.gray);
	  txtCustomizedDirectory.setBounds(0, 85, 424, 20);
    
	  tfCustomizedDirectory = new JTextField();
	  tfCustomizedDirectory.setBounds(0, 115, 424, 30);
	  tfCustomizedDirectory.setEnabled(false);
    
	  cbFilter = new JCheckBox("<html><body>Ordenar unicamnete un tipo de archivo</body></html>");
	  cbFilter.setBounds(0, 145, 424, 30);
    
	  txtFilter = new JLabel("<html><body>Por favor ingresa la extencion del tipo de archivo que deseas ordenar. EJ(.pdf   .txt    .dock    .jpg    .mp3    .mp4    .ppt)</body></html>");
    
	  txtFilter.setForeground(Color.GRAY);
	  txtFilter.setBounds(0, 185, 424, 30);
    
	  tfFilter = new JTextField();
	  tfFilter.setBounds(0, 225, 424, 30);
	  tfFilter.setEnabled(false);
  }


  
  private void assign() {
	  add(txtDirectoryOption);
	  add(cbDirectoryOption1);
	  add(cbDirectoryOption2);
	  add(cbDirectoryOption3);
	  add(txtCustomizedDirectory);
	  add(tfCustomizedDirectory);
	  add(cbFilter);
	  add(txtFilter);
	  add(tfFilter);
  }

  
  public void assignController(Controller control) {
	  cbDirectoryOption1.setActionCommand(Actions.CHECKBOXCUSTOMIZEDDIRECTORY);
	  cbDirectoryOption1.addActionListener((ActionListener)control);
    
	  cbDirectoryOption2.setActionCommand(Actions.CHECKBOXCUSTOMIZEDDIRECTORY);
	  cbDirectoryOption2.addActionListener((ActionListener)control);
    
	  cbDirectoryOption3.setActionCommand(Actions.CHECKBOXCUSTOMIZEDDIRECTORY);
	  cbDirectoryOption3.addActionListener((ActionListener)control);
    
	  tfCustomizedDirectory.addMouseListener((MouseListener)control);
    
	  cbFilter.setActionCommand("checkboxfiltro");
	  cbFilter.addActionListener((ActionListener)control);
    
	  tfFilter.addMouseListener((MouseListener)control);
  }
  
  public void activate() {
	cbDirectoryOption1.setEnabled(true);
	cbDirectoryOption2.setEnabled(true);
	cbDirectoryOption3.setEnabled(true);
	if (cbDirectoryOption3.isSelected()) {
		tfCustomizedDirectory.setEnabled(true);
    }
	cbFilter.setEnabled(true);
	if (cbFilter.isSelected()) {
		tfFilter.setEnabled(true);
    }
  }
  
  public void desactivate() {
	cbDirectoryOption1.setEnabled(false);
	cbDirectoryOption2.setEnabled(false);
	cbDirectoryOption3.setEnabled(false);
	tfCustomizedDirectory.setEnabled(false);
	cbFilter.setEnabled(false);
	tfFilter.setEnabled(false);
  }
  
  public void activateCustomizedDirectory() {
	txtCustomizedDirectory.setForeground(Color.black);
	tfCustomizedDirectory.setEnabled(true);
  }
  
  public void desactivateCustomizedDirectory() {
	tfCustomizedDirectory.setText("");
	txtCustomizedDirectory.setForeground(Color.gray);
	tfCustomizedDirectory.setEnabled(false);
  }
  
  public void activateFilter() {
	txtFilter.setForeground(Color.black);
	tfFilter.setEnabled(true);
  }
  
  public void desactivateFilter() {
	tfFilter.setText("");
	txtFilter.setForeground(Color.gray);
	tfFilter.setEnabled(false);
  }


  
  public boolean continueTo() {
	boolean status = true;
	if (cbDirectoryOption3.isSelected() && tfCustomizedDirectory.getText().trim().length() == 0) {
		status = false;
    }
	if (cbFilter.isSelected() && tfFilter.getText().trim().length() == 0) {
		status = false;
    }
	return status;
  }
  
  public String[] getCbOpciones() {
	String[] data = new String[2];
		if (cbDirectoryOption3.isSelected()) {
			data[0] = "true";
		} else {
			data[0] = "false";
		} 
		if (cbFilter.isSelected()) {
			data[1] = "true";
		} else {
			data[1] = "false";
		} 
	 return data;
  }
  
  public String[] capture() {
	  String filter = "";
	  if (cbFilter.isSelected()) {
		  if (tfFilter.getText().charAt(0) != '.') {
			  	filter = "." + tfFilter.getText().toLowerCase();
		  } else {
			  	filter = tfFilter.getText().toLowerCase();
		  } 
	  }
	  String[] data = {
			  ""+cbDirectoryOption1.isSelected(), ""+cbDirectoryOption2.isSelected(), ""+cbDirectoryOption3.isSelected(), tfCustomizedDirectory.getText(), 
			  ""+cbFilter.isSelected(), filter
      };
	  return data;
  }
}
