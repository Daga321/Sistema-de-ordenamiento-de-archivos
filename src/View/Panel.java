package View;

import java.awt.LayoutManager;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import Control.Controller;


public class Panel extends JPanel{
	private JLabel txtPathInformation;
	private JTextField tfPath;
	private JButton btnSearchPath;
	private JLabel txtAmountInformation;
	private SpinnerNumberModel smModel;
	private JSpinner sAmount;
	private JButton btnContinue;
	private JLabel txtComment;
	private JProgressBar pbProgress;
	
	public Panel() {
		setLayout((LayoutManager)null);
		initialize();
		assign();
	}
	
	private void initialize() {
		txtPathInformation = new JLabel("<html><body>Por favor selecciona la ubicacion donde desea generar los archivos de prueba</body></html>");
		txtPathInformation.setBounds(80, 20, 424, 30);
		
		tfPath = new JTextField();
		tfPath.setBounds(80, 60, 314, 25);
		tfPath.setEditable(false);
		
		btnSearchPath = new JButton("Examinar");
		btnSearchPath.setBounds(414, 60, 90, 25);
		
		txtAmountInformation = new JLabel("<html><body>Por favor selecciona la cantidad de archivos de prueba a generar</body></html>");
		txtAmountInformation.setBounds(80, 90, 424, 30);
		
		smModel = new SpinnerNumberModel(5000, 5000, 15000, 50);
		sAmount = new JSpinner(smModel);
		sAmount.setBounds(80,130,90,25);
		
		btnContinue = new JButton("Continuar");
		btnContinue.setBounds(247, 165, 90, 25);
		btnContinue.setEnabled(false);
		
		txtComment = new JLabel("<html><body>0 de ... archivos de testeo generados</body></html>");
		txtComment.setBounds(80, 190, 424, 20);
		
		pbProgress = new JProgressBar(0, 0);
		pbProgress.setBounds(80, 220, 424, 30);
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
