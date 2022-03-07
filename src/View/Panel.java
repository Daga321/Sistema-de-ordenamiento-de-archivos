package View;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.LineBorder;

import Control.Controller;


public class Panel extends JPanel{
	private JLabel txtInformacionUbicacion;
	private RoundJTextField tfUbicacion;
	private JButton btnExaminarUbicacion;
	private JLabel txtInformacionCantidad;
	private SpinnerNumberModel smModel;
	private JSpinner sCantidad;
	private JButton btnContinuar;
	private JLabel txtComentario;
	private JProgressBar pbProgreso;
	
	public Panel() {
		setLayout((LayoutManager)null);
		setBackground(Color.WHITE);
		inicializar();
		agregar();
	}
	
	private void inicializar() {
		txtInformacionUbicacion = new JLabel("<html><body>Por favor selecciona la ubicacion donde desea generar los archivos de prueba</body></html>");
		txtInformacionUbicacion.setBounds(80, 20, 424, 30);
		
		
		tfUbicacion = new RoundJTextField(20);
		tfUbicacion.setBounds(80, 60, 325, 25);
		ImageIcon imgIcontxt = new ImageIcon("Imagenes/comp.png");
	    Image imgEscaladatxt = imgIcontxt.getImage().getScaledInstance(tfUbicacion.getWidth(),tfUbicacion.getHeight(), Image.SCALE_SMOOTH);
	    Icon iconoEscaladotxt = new ImageIcon(imgEscaladatxt);
	    tfUbicacion.setIcon(iconoEscaladotxt);
		tfUbicacion.setEditable(false);
		tfUbicacion.setBorder(new RoundedBorder(20));
		tfUbicacion.setForeground(Color.BLUE);
		tfUbicacion.setBackground(Color.WHITE);
		tfUbicacion.setBorder(new LineBorder(Color.BLACK));
		
		btnExaminarUbicacion = new JButton();
		btnExaminarUbicacion.setBounds(414, 60, 25, 25);
		btnExaminarUbicacion.setContentAreaFilled(false);
		btnExaminarUbicacion.setBorder(new RoundedBorder(20));
		ImageIcon imgIconBoton = new ImageIcon("Imagenes/fileCabinet.png");
	    Image imgEscaladaBoton = imgIconBoton.getImage().getScaledInstance(btnExaminarUbicacion.getWidth(),btnExaminarUbicacion.getHeight(), Image.SCALE_SMOOTH);
	    Icon iconoEscaladoBoton = new ImageIcon(imgEscaladaBoton);
	    btnExaminarUbicacion.setIcon(iconoEscaladoBoton);
	    btnExaminarUbicacion.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		txtInformacionCantidad = new JLabel("<html><body>Por favor selecciona la cantidad de archivos de prueba a generar</body></html>");
		txtInformacionCantidad.setBounds(80, 90, 424, 30);
		
		smModel = new SpinnerNumberModel(5000, 5000, 15000, 50);
		sCantidad = new JSpinner(smModel);
		sCantidad.setBounds(80,130,90,25);
		
		btnContinuar = new JButton("Continuar");
		btnContinuar.setBounds(247, 165, 100, 25);
		btnContinuar.setBorder(new RoundedBorder(20));
		btnContinuar.setContentAreaFilled(false);
		btnContinuar.setEnabled(false);
		btnContinuar.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		txtComentario = new JLabel("<html><body>0 de ... archivos de testeo generados</body></html>");
		txtComentario.setBounds(80, 190, 424, 20);
		
		pbProgreso = new JProgressBar(0, 0);
		pbProgreso.setBounds(80, 220, 424, 30);
		pbProgreso.setBackground(Color.WHITE);
	}

	
	private void agregar() {
		add(txtInformacionUbicacion);
		add(tfUbicacion);
		add(btnExaminarUbicacion);
		add(txtInformacionCantidad);
		add(sCantidad);
		add(btnContinuar);
		add(txtComentario);
		add(pbProgreso);
	}
	
	public void asignarControl(Controller control) {
		btnExaminarUbicacion.setActionCommand("filechooser");
		btnExaminarUbicacion.addActionListener((ActionListener)control);
		
		btnContinuar.setActionCommand("continuar");
		btnContinuar.addActionListener((ActionListener)control);
		
	}
	
	public void setUbicacionOrigen(String ubicacion) {
		tfUbicacion.setText(ubicacion);
	}
	
	public void continuar() {
		if (tfUbicacion.getText().length() > 0 ) {
			btnContinuar.setEnabled(true);
		} else {
			btnContinuar.setEnabled(false);
		} 
	}

	
	public String[] capturar() {
		String ubicacion = tfUbicacion.getText();
		String limite = ""+sCantidad.getValue();
		String[] datos = {
				ubicacion, limite
		};
		return datos;
	}
	
	public void progressBar(String[] datos) {
		int min = Integer.parseInt(datos[0]);
		int max = Integer.parseInt(datos[1]);
		System.out.println(String.valueOf(min) + " de " + max + " archivos de testeo generados");
		txtComentario.setText("<html><body>" + min + " de " + max + " archivos de testeo generados</body></html>");
		pbProgreso.setValue(min);
		pbProgreso.setMaximum(max);
	}
	
	public void activar() {
		tfUbicacion.setEnabled(true);
		sCantidad.setEnabled(true);
		btnExaminarUbicacion.setEnabled(true);
		btnContinuar.setEnabled(true);
	}
	
	public void desactivar() {
		txtComentario.setText("0 de ... archivos de testeo generados");
		pbProgreso.setValue(0);
		tfUbicacion.setEnabled(false);
		sCantidad.setEnabled(false);
		btnExaminarUbicacion.setEnabled(false);
		btnContinuar.setEnabled(false);
	}
	
}
