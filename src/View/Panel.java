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

	private JLabel txtPathInformation;
	private RoundJTextField tfPath;
	private JButton btnSearchPath;
	private JLabel txtAmountInformation;
	private SpinnerNumberModel smModel;
	private JSpinner sAmount;
	private JButton btnContinue;
	private JLabel txtComment;
	private JProgressBar pbProgress;
	
	public Panel() {
		setLayout((LayoutManager)null);
		setBackground(Color.WHITE);
		initialize();
		assign();
	}
	
	private void initialize() {
		txtPathInformation = new JLabel("<html><body>Por favor selecciona la ubicacion donde desea generar los archivos de prueba</body></html>");
		txtPathInformation.setBounds(80, 20, 424, 30);
		
		tfPath = new RoundJTextField(20);
		tfPath.setBounds(80, 60, 380, 25);
		ImageIcon imgIcontxt = new ImageIcon("Imagenes/comp.png");
	    Image imgEscaladatxt = imgIcontxt.getImage().getScaledInstance(tfPath.getWidth(),tfPath.getHeight(), Image.SCALE_SMOOTH);
	    Icon iconoEscaladotxt = new ImageIcon(imgEscaladatxt);
	    tfPath.setIcon(iconoEscaladotxt);
		tfPath.setEditable(false);
		tfPath.setBorder(new RoundedBorder(20));
		tfPath.setForeground(Color.BLUE);
		tfPath.setBackground(Color.WHITE);
		tfPath.setBorder(new LineBorder(Color.BLACK));
		
		btnSearchPath = new JButton();
		btnSearchPath.setBounds(470, 60, 25, 25);
		btnSearchPath.setContentAreaFilled(false);
		btnSearchPath.setBorder(new RoundedBorder(20));
		ImageIcon imgIconBoton = new ImageIcon("Imagenes/fileCabinet.png");
	    Image imgEscaladaBoton = imgIconBoton.getImage().getScaledInstance(btnSearchPath.getWidth(),btnSearchPath.getHeight(), Image.SCALE_SMOOTH);
	    Icon iconoEscaladoBoton = new ImageIcon(imgEscaladaBoton);
	    btnSearchPath.setIcon(iconoEscaladoBoton);
	    btnSearchPath.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		txtAmountInformation = new JLabel("<html><body>Por favor selecciona la cantidad de archivos de prueba a generar</body></html>");
		txtAmountInformation.setBounds(80, 90, 424, 30);
		
		smModel = new SpinnerNumberModel(5000, 5000, 15000, 50);
		sAmount = new JSpinner(smModel);
		sAmount.setBounds(80,130,90,25);
		
		btnContinue = new JButton("Continuar");
		btnContinue.setBounds(247, 165, 100, 25);
		btnContinue.setBorder(new RoundedBorder(20));
		btnContinue.setContentAreaFilled(false);
		btnContinue.setEnabled(false);
		btnContinue.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		txtComment = new JLabel("<html><body>0 de ... archivos de testeo generados</body></html>");
		txtComment.setBounds(80, 190, 424, 20);
		
		pbProgress = new JProgressBar(0, 0);
		pbProgress.setBounds(80, 220, 424, 30);
		pbProgress.setBackground(Color.WHITE);
	}

	
	private void assign() {
		add(txtPathInformation);
		add(tfPath);
		add(btnSearchPath);
		add(txtAmountInformation);
		add(sAmount);
		add(btnContinue);
		add(txtComment);
		add(pbProgress);
	}
	
	public void asignarControl(Controller control) {
		btnSearchPath.setActionCommand(Actions.ORIGINFILECHOOSER);
		btnSearchPath.addActionListener((ActionListener)control);
		
		btnContinue.setActionCommand(Actions.CONTINUE);
		btnContinue.addActionListener((ActionListener)control);
		
	}
	
	public void setOriginPath(String ubicacion) {
		tfPath.setText(ubicacion);
	}
	
	public void continueTo() {
		if (tfPath.getText().length() > 0 ) {
			btnContinue.setEnabled(true);
		} else {
			btnContinue.setEnabled(false);
		} 
	}

	
	public String[] capture() {
		String ubicacion = tfPath.getText();
		String limite = ""+sAmount.getValue();
		String[] datos = {
				ubicacion, limite
		};
		return datos;
	}
	
	public void progressBar(String[] datos) {
		int min = Integer.parseInt(datos[0]);
		int max = Integer.parseInt(datos[1]);
//		System.out.println(String.valueOf(min) + " de " + max + " archivos de testeo generados");
		txtComment.setText("<html><body>" + min + " de " + max + " archivos de testeo generados</body></html>");
		pbProgress.setValue(min);
		pbProgress.setMaximum(max);
	}
	
	public void activate() {
		tfPath.setEnabled(true);
		sAmount.setEnabled(true);
		btnSearchPath.setEnabled(true);
		btnContinue.setEnabled(true);
	}
	
	public void desactivate() {
		txtComment.setText("0 de ... archivos de testeo generados");
		pbProgress.setValue(0);
		tfPath.setEnabled(false);
		sAmount.setEnabled(false);
		btnSearchPath.setEnabled(false);
		btnContinue.setEnabled(false);
	}
	
}
