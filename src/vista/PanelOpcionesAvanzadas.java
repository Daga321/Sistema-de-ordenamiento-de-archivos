package vista;

import control.Control;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PanelOpcionesAvanzadas extends JPanel {
	 private JLabel txtEstiloCarpetas;
	 private ButtonGroup bgEstiloCarpetas;
	 private JCheckBox cbEstiloCarpetas1;
	 private JCheckBox cbEstiloCarpetas2;
	 private JCheckBox cbEstiloCarpetas3;
	 private JLabel txtCarpetaPersonalizada;
	 private JTextField tfCarpetaPersonalizada;
	 private JCheckBox cbFiltro;
	 private JLabel txtFiltro;
	 private JTextField tfFiltro;
  
  public PanelOpcionesAvanzadas() {
	  setLayout((LayoutManager)null);
	  inicializar();
	  agregar();
  }
  
  private void inicializar() {
	  txtEstiloCarpetas = new JLabel("<html><body>Selecciona una opcion de guardado</body></html>");
	  txtEstiloCarpetas.setBounds(0, 10, 424, 20);
    
	  bgEstiloCarpetas = new ButtonGroup();
    
	  cbEstiloCarpetas1 = new JCheckBox("<html><body>Ordenar por carpetas año/mes</body></html>");
	  cbEstiloCarpetas1.setBounds(0, 30, 141, 45);
	  cbEstiloCarpetas1.setSelected(true);
	  bgEstiloCarpetas.add(cbEstiloCarpetas1);
    
	  cbEstiloCarpetas2 = new JCheckBox("<html><body>Ordenar por carpetas año</body></html>");
	  cbEstiloCarpetas2.setBounds(141, 30, 141, 45);
	  bgEstiloCarpetas.add(cbEstiloCarpetas2);
    
	  cbEstiloCarpetas3 = new JCheckBox("<html><body>Ordenar en una carpeta de nombre personalizado</body></html>");
	  cbEstiloCarpetas3.setBounds(282, 30, 141, 45);
	  bgEstiloCarpetas.add(cbEstiloCarpetas3);
    
	  txtCarpetaPersonalizada = new JLabel("<html><body>por favor ingresa el nombre que deseas para la carpeta</body></html>");
	  txtCarpetaPersonalizada.setForeground(Color.gray);
	  txtCarpetaPersonalizada.setBounds(0, 85, 424, 20);
    
	  tfCarpetaPersonalizada = new JTextField();
	  tfCarpetaPersonalizada.setBounds(0, 115, 424, 30);
	  tfCarpetaPersonalizada.setEnabled(false);
    
	  cbFiltro = new JCheckBox("<html><body>Ordenar unicamnete un tipo de archivo</body></html>");
	  cbFiltro.setBounds(0, 145, 424, 30);
    
	  txtFiltro = new JLabel("<html><body>Por favor ingresa la extencion del tipo de archivo que deseas ordenar. EJ(.pdf   .txt    .dock    .jpg    .mp3    .mp4    .ppt)</body></html>");
    
	  txtFiltro.setForeground(Color.GRAY);
	  txtFiltro.setBounds(0, 185, 424, 30);
    
	  tfFiltro = new JTextField();
	  tfFiltro.setBounds(0, 225, 424, 30);
	  tfFiltro.setEnabled(false);
  }


  
  private void agregar() {
	  add(txtEstiloCarpetas);
	  add(cbEstiloCarpetas1);
	  add(cbEstiloCarpetas2);
	  add(cbEstiloCarpetas3);
	  add(txtCarpetaPersonalizada);
	  add(tfCarpetaPersonalizada);
	  add(cbFiltro);
	  add(txtFiltro);
	  add(tfFiltro);
  }

  
  public void asignarListener(Control control) {
	  cbEstiloCarpetas1.setActionCommand("checkboxcarpetapersonalizada");
	  cbEstiloCarpetas1.addActionListener((ActionListener)control);
    
	cbEstiloCarpetas2.setActionCommand("checkboxcarpetapersonalizada");
	cbEstiloCarpetas2.addActionListener((ActionListener)control);
    
		cbEstiloCarpetas3.setActionCommand("checkboxcarpetapersonalizada");
		cbEstiloCarpetas3.addActionListener((ActionListener)control);
    
		tfCarpetaPersonalizada.addMouseListener((MouseListener)control);
    
		cbFiltro.setActionCommand("checkboxfiltro");
		cbFiltro.addActionListener((ActionListener)control);
    
		tfFiltro.addMouseListener((MouseListener)control);
  }
  
  public void activar() {
	cbEstiloCarpetas1.setEnabled(true);
	cbEstiloCarpetas2.setEnabled(true);
	cbEstiloCarpetas3.setEnabled(true);
	if (cbEstiloCarpetas3.isSelected()) {
		tfCarpetaPersonalizada.setEnabled(true);
    }
	cbFiltro.setEnabled(true);
	if (cbFiltro.isSelected()) {
		tfFiltro.setEnabled(true);
    }
  }
  
  public void desactivar() {
	cbEstiloCarpetas1.setEnabled(false);
	cbEstiloCarpetas2.setEnabled(false);
	cbEstiloCarpetas3.setEnabled(false);
	tfCarpetaPersonalizada.setEnabled(false);
	cbFiltro.setEnabled(false);
	tfFiltro.setEnabled(false);
  }
  
  public void activarCarpetaPersonalizada() {
	txtCarpetaPersonalizada.setForeground(Color.black);
	tfCarpetaPersonalizada.setEnabled(true);
  }
  
  public void desactivarCarpetaPersonalizada() {
	tfCarpetaPersonalizada.setText("");
	txtCarpetaPersonalizada.setForeground(Color.gray);
	tfCarpetaPersonalizada.setEnabled(false);
  }
  
  public void activarFiltro() {
	txtFiltro.setForeground(Color.black);
	tfFiltro.setEnabled(true);
  }
  
  public void desactivarFiltro() {
	tfFiltro.setText("");
	txtFiltro.setForeground(Color.gray);
	tfFiltro.setEnabled(false);
  }


  
  public boolean continuar() {
	boolean estado = true;
	if (cbEstiloCarpetas3.isSelected() && 
		tfCarpetaPersonalizada.getText().trim().length() == 0) {
		estado = false;
    }
	if (cbFiltro.isSelected() && 
		tfFiltro.getText().trim().length() == 0) {
		estado = false;
    }
	return estado;
  }
  
  public String[] getCbOpciones() {
	String[] datos = new String[2];
		if (cbEstiloCarpetas3.isSelected()) {
			datos[0] = "true";
		} else {
			datos[0] = "false";
		} 
		if (cbFiltro.isSelected()) {
			datos[1] = "true";
		} else {
			datos[1] = "false";
		} 
	 return datos;
  }
  
  public String[] capturar() {
	  String filtro = "";
	  if (cbFiltro.isSelected()) {
		  if (tfFiltro.getText().charAt(0) != '.') {
			  	filtro = "." + tfFiltro.getText().toLowerCase();
		  } else {
			  	filtro = tfFiltro.getText().toLowerCase();
		  } 
	  }
	  String[] datos = {
			  ""+cbEstiloCarpetas1.isSelected(), ""+cbEstiloCarpetas2.isSelected(), ""+cbEstiloCarpetas3.isSelected(), tfCarpetaPersonalizada.getText(), 
			  ""+cbFiltro.isSelected(), filtro
      };
	  return datos;
  }
}
