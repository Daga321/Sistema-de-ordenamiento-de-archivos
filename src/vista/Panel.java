package vista;

import control.Control;
import java.awt.LayoutManager;
import java.awt.event.ActionListener;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;


public class Panel extends JPanel{
	private JLabel txtFirma;
	private ImageIcon firma;
	private URL url;
	private JLabel txtInformacionOrigen;
	private JTextField tfUbicacionOrigen;
	private JButton btnExaminarOrigen;
	private JLabel txtInformacionDestino;
	private JTextField tfUbicacionDestino;
	private JButton btnExaminarDestino;
	private JCheckBox cbOpcion;
	private JCheckBox cbOpcionesAvanzadas;
	private PanelOpcionesAvanzadas panelOpcionesAvanzadas;
	private JButton btnContinuar;
	private JLabel txtComentario;
	private JProgressBar pbProgreso;
	
	public Panel() {
		setLayout((LayoutManager)null);
		inicializar();
		agregar();
	}
	
	private void inicializar() {
		txtFirma = new JLabel();
		txtFirma.setBounds(-10, 0, 30, 230);
		url = Panel.class.getResource("/firma.png");
		firma = new ImageIcon(url);
		txtFirma.setIcon(new ImageIcon(firma.getImage().getScaledInstance(50, 230, 4)));
		
		txtInformacionOrigen = new JLabel("<html><body>Por favor selecciona la ubicacion donde se encuentran los archivos que deseas organizar</body></html>");
		
		txtInformacionOrigen.setBounds(80, 20, 424, 30);
		
		tfUbicacionOrigen = new JTextField();
		tfUbicacionOrigen.setBounds(80, 60, 314, 25);
		tfUbicacionOrigen.setEditable(false);
		
		btnExaminarOrigen = new JButton("Examinar");
		btnExaminarOrigen.setBounds(414, 60, 90, 25);
		
		cbOpcion = new JCheckBox("<html><body>Utilizar ubicacion de origen como ubicacion de destino</body></html>", true);
		cbOpcion.setBounds(80, 95, 424, 30);
		
		txtInformacionDestino = new JLabel("<html><body>Por favor selecciona la ubicacion donde deseas que se ubiquen los archivos</body></html>");
		txtInformacionDestino.setBounds(80, 135, 424, 30);
		
		tfUbicacionDestino = new JTextField();
		tfUbicacionDestino.setBounds(80, 175, 314, 25);
		tfUbicacionDestino.setEditable(false);
		
		btnExaminarDestino = new JButton("Examinar");
		btnExaminarDestino.setBounds(414, 175, 90, 25);
		
		cbOpcionesAvanzadas = new JCheckBox("<html><body>Opciones avanzadas</body></html>", false);
		cbOpcionesAvanzadas.setBounds(80, 135, 424, 20);
		
		panelOpcionesAvanzadas = new PanelOpcionesAvanzadas();
		panelOpcionesAvanzadas.setSize(424, 270);
		
		btnContinuar = new JButton("Continuar");
		btnContinuar.setBounds(247, 165, 90, 25);
		btnContinuar.setEnabled(false);
		
		txtComentario = new JLabel("<html><body>0 de ... elementos procesados</body></html>");
		txtComentario.setBounds(80, 190, 424, 20);
		
		pbProgreso = new JProgressBar(0, 0);
		pbProgreso.setBounds(80, 220, 424, 30);
	}

	
	private void agregar() {
		add(txtFirma);
		add(txtInformacionOrigen);
		add(tfUbicacionOrigen);
		add(btnExaminarOrigen);
		add(cbOpcion);
		add(cbOpcionesAvanzadas);
		add(btnContinuar);
		add(txtComentario);
		add(pbProgreso);
	}
	
	public void asignarControl(Control control) {
		btnExaminarOrigen.setActionCommand("filechooserorigen");
		btnExaminarOrigen.addActionListener((ActionListener)control);
		
		btnExaminarDestino.setActionCommand("filechooserdestino");
		btnExaminarDestino.addActionListener((ActionListener)control);
		
		cbOpcion.setActionCommand("checkboxopciones");
		cbOpcion.addActionListener((ActionListener)control);
		
		btnContinuar.setActionCommand("continuar");
		btnContinuar.addActionListener((ActionListener)control);
		
		cbOpcionesAvanzadas.setActionCommand("checkboxopcionesavanzadas");
		cbOpcionesAvanzadas.addActionListener((ActionListener)control);
		
		panelOpcionesAvanzadas.asignarListener(control);
	}

	
	public String[] getCbOpciones() {
		String[] datos = new String[4];
		if (cbOpcion.isSelected()) {
			datos[0] = "true";
		} else {
			datos[0] = "false";
		} 
		if (cbOpcionesAvanzadas.isSelected()) {
			datos[1] = "true";
		} else {
			datos[1] = "false";
		} 
		datos[2] = panelOpcionesAvanzadas.getCbOpciones()[0];
		datos[3] = panelOpcionesAvanzadas.getCbOpciones()[1];
		return datos;
	}
	
	public void setUbicacionOrigen(String ubicacion) {
		tfUbicacionOrigen.setText(ubicacion);
	}
	
	public void setUbicacionDestino(String ubicacion) {
		tfUbicacionDestino.setText(ubicacion);
	}
	
	public void continuar() {
		if (tfUbicacionOrigen.getText().length() > 0 && cbOpcion.isSelected()) {
			btnContinuar.setEnabled(true);
			if (cbOpcionesAvanzadas.isSelected()) {
				if (panelOpcionesAvanzadas.continuar()) {
					btnContinuar.setEnabled(true);
				} else {
					btnContinuar.setEnabled(false);
				} 
			}
		} else if (tfUbicacionOrigen.getText().length() == 0 && cbOpcion.isSelected()) {
			btnContinuar.setEnabled(false);
		} else if (tfUbicacionOrigen.getText().length() > 0 && tfUbicacionDestino.getText().length() > 0) {
			btnContinuar.setEnabled(true);
			if (cbOpcionesAvanzadas.isSelected()) {
				if (panelOpcionesAvanzadas.continuar()) {
					btnContinuar.setEnabled(true);
				} else {
					btnContinuar.setEnabled(false);
				} 
			}
		} 
	}

	
	public String[] capturar() {
		String destino, origen = tfUbicacionOrigen.getText();
		
		if (cbOpcion.isSelected()) {
			destino = tfUbicacionOrigen.getText();
		} else {
			destino = tfUbicacionDestino.getText();
		} 
			String[] opcionesAvanzadas = panelOpcionesAvanzadas.capturar();
			String[] datos = {
					origen, destino, opcionesAvanzadas[0], opcionesAvanzadas[1], opcionesAvanzadas[2], opcionesAvanzadas[3], 
					opcionesAvanzadas[4], opcionesAvanzadas[5]
			};
		return datos;
	}
	
	public void progressBar(String[] datos) {
		int min = Integer.parseInt(datos[0]);
		int max = Integer.parseInt(datos[1]);
		System.out.println(String.valueOf(min) + " de " + max + " elementos procesados");
		txtComentario.setText("<html><body>" + min + " de " + max + " elementos procesados</body></html>");
		pbProgreso.setValue(min);
		pbProgreso.setMaximum(max);
	}
	
	public void activar() {
		tfUbicacionOrigen.setEnabled(true);
		btnExaminarOrigen.setEnabled(true);
		cbOpcion.setEnabled(true);
		tfUbicacionDestino.setEnabled(true);
		btnExaminarDestino.setEnabled(true);
		cbOpcionesAvanzadas.setEnabled(true);
		btnContinuar.setEnabled(true);
		panelOpcionesAvanzadas.activar();
	}
	
	public void desactivar() {
		txtComentario.setText("0 de ... elementos procesados");
		pbProgreso.setValue(0);
		tfUbicacionOrigen.setEnabled(false);
		btnExaminarOrigen.setEnabled(false);
		tfUbicacionDestino.setEnabled(false);
		btnExaminarDestino.setEnabled(false);
		cbOpcion.setEnabled(false);
		cbOpcionesAvanzadas.setEnabled(false);
		btnContinuar.setEnabled(false);
		panelOpcionesAvanzadas.desactivar();
	}

	
	public void activarOpciones() {
		add(txtInformacionDestino);
		add(tfUbicacionDestino);
		add(btnExaminarDestino);
		cbOpcionesAvanzadas.setLocation(80, 210);
		btnContinuar.setLocation(247, 245);
		txtComentario.setLocation(80, 275);
		pbProgreso.setLocation(80, 315);
		btnContinuar.setEnabled(false);
	}
	
	public void desactivarOpciones() {
		remove(txtInformacionDestino);
		remove(tfUbicacionDestino);
		remove(btnExaminarDestino);
		tfUbicacionDestino.setText("");
		cbOpcionesAvanzadas.setLocation(80, 130);
		btnContinuar.setLocation(247, 165);
		txtComentario.setLocation(80, 190);
		pbProgreso.setLocation(80, 220);
	}
	
	public void activarOpcionesAvanzadas() {
		add(panelOpcionesAvanzadas);
		int y = (int)cbOpcionesAvanzadas.getLocation().getY() + 20;
		panelOpcionesAvanzadas.setLocation(80, y);
		y += (int)panelOpcionesAvanzadas.getSize().getHeight();
		btnContinuar.setLocation(247, y);
		y += (int)btnContinuar.getSize().getHeight();
		txtComentario.setLocation(80, y);
		y += (int)txtComentario.getSize().getHeight() + 20;
		pbProgreso.setLocation(80, y);
	}
	
	public void desactivarOpcionesAvanzadas() {
		remove(panelOpcionesAvanzadas);
		int y = (int)cbOpcionesAvanzadas.getLocation().getY() + 20;
		btnContinuar.setLocation(247, y);
		y += (int)btnContinuar.getSize().getHeight();
		txtComentario.setLocation(80, y);
		y += (int)txtComentario.getSize().getHeight() + 20;
		pbProgreso.setLocation(80, y);
	}
	
	public void activarCarpetaPersonalizada() {
		panelOpcionesAvanzadas.activarCarpetaPersonalizada();
	}
	
	public void desactivarCarpetaPersonalizada() {
		panelOpcionesAvanzadas.desactivarCarpetaPersonalizada();
	}
	
	public void activarFiltro() {
		panelOpcionesAvanzadas.activarFiltro();
	}
	
	public void desactivarFiltro() {
		panelOpcionesAvanzadas.desactivarFiltro();
	}
}
