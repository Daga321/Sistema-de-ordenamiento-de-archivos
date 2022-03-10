package View;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.border.LineBorder;

import Control.Controller;


public class Panel extends JPanel{
	private URL url;
	private JLabel lblSignature;
	private JLabel lblOriginInformatio;
	private RoundJTextField txtfOriginPath;
	private JButton btnSearchOriginPath;
	private JLabel txtDestinyInformation;
	private RoundJTextField tfDestinyPath;
	private JButton btnSearchDestinyPath;
	private JCheckBox cbOpcions;
	private JCheckBox cbAdvancedOptions;
	private AdvancedOptionsPanel advancedOptionsPanel;
	private JButton btnContinue;
	private JLabel txtComment;
	private JProgressBar pbProgress;
	
	public Panel() {
		setLayout(null);
		setBackground(Color.WHITE);
		initialize();
		assign();
	}
	
	private void initialize() {
		lblSignature = new JLabel();
		lblSignature.setBounds(8, 0, 59, 350);
		url = Panel.class.getResource("/signature.png");
		ImageIcon imgIcon = new ImageIcon(url);
	    Image imgEscalada = imgIcon.getImage().getScaledInstance(lblSignature.getWidth(),lblSignature.getHeight(), Image.SCALE_SMOOTH);
	    Icon iconoEscalado = new ImageIcon(imgEscalada);
        lblSignature.setIcon(iconoEscalado);
		
		lblOriginInformatio = new JLabel("<html><body>Por favor seleccione la ubicación de los archivos que desea organizar</body></html>");
		lblOriginInformatio.setBounds(92, 39, 440, 20);
		
		txtfOriginPath = new RoundJTextField(20);
		txtfOriginPath.setBounds(92, 79, 398, 32);
		url = Panel.class.getResource("/comp.png");
		ImageIcon imgIcontxt = new ImageIcon(url);
	    Image imgEscaladatxt = imgIcontxt.getImage().getScaledInstance(txtfOriginPath.getWidth(),txtfOriginPath.getHeight(), Image.SCALE_SMOOTH);
	    Icon iconoEscaladotxt = new ImageIcon(imgEscaladatxt);
	    txtfOriginPath.setIcon(iconoEscaladotxt);
		txtfOriginPath.setEditable(false);
		txtfOriginPath.setBorder(new RoundedBorder(20));
		txtfOriginPath.setForeground(Color.BLUE);
		txtfOriginPath.setBackground(Color.WHITE);
		txtfOriginPath.setBorder(new LineBorder(Color.BLACK));
		
		btnSearchOriginPath = new JButton();
		btnSearchOriginPath.setBounds(500, 79, 32, 32);
		btnSearchOriginPath.setContentAreaFilled(false);
		btnSearchOriginPath.setBorder(new RoundedBorder(20));
		url = Panel.class.getResource("/fileCabinet.png");
		ImageIcon imgIconBoton = new ImageIcon(url);
	    Image imgEscaladaBoton = imgIconBoton.getImage().getScaledInstance(btnSearchOriginPath.getWidth(),btnSearchOriginPath.getHeight(), Image.SCALE_SMOOTH);
	    Icon iconoEscaladoBoton = new ImageIcon(imgEscaladaBoton);
	    btnSearchOriginPath.setIcon(iconoEscaladoBoton);
	    btnSearchOriginPath.setCursor(new Cursor(Cursor.HAND_CURSOR));
	    
		cbOpcions = new JCheckBox("<html><body>Utilizar ubicación de origen como ubicación de destino</body></html>", true);
		cbOpcions.setBounds(92, 131, 362, 20);
		cbOpcions.setBackground(Color.WHITE);
		cbOpcions.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		txtDestinyInformation = new JLabel("<html><body>Por favor selecciona la path donde deseas que se ubiquen los archivos</body></html>");
		txtDestinyInformation.setBounds(92, 170, 455, 31);
		
		tfDestinyPath = new RoundJTextField(20);
		tfDestinyPath.setBounds(92, 204, 398, 32);
		tfDestinyPath.setIcon(iconoEscaladotxt);
		tfDestinyPath.setEditable(false);
		tfDestinyPath.setBorder(new RoundedBorder(20));
		tfDestinyPath.setForeground(Color.BLUE);
		tfDestinyPath.setBackground(Color.WHITE);
		tfDestinyPath.setBorder(new LineBorder(Color.BLACK));
		tfDestinyPath.setEditable(false);
		
		btnSearchDestinyPath = new JButton();
		btnSearchDestinyPath.setBounds(494, 204, 32, 32);
		btnSearchDestinyPath.setContentAreaFilled(false);
		btnSearchDestinyPath.setBorder(new RoundedBorder(20));
		btnSearchDestinyPath.setIcon(iconoEscaladoBoton);
		
		cbAdvancedOptions = new JCheckBox("<html><body>Opciones avanzadas</body></html>", false);
		cbAdvancedOptions.setBounds(92, 171, 160, 20);
		cbAdvancedOptions.setBackground(Color.WHITE);
		cbAdvancedOptions.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		advancedOptionsPanel = new AdvancedOptionsPanel();
		advancedOptionsPanel.setSize(456, 383);
		
		btnContinue = new JButton("Continuar");
		btnContinue.setBounds(251, 220, 120, 32);
		btnContinue.setBorder(new RoundedBorder(20));
		btnContinue.setContentAreaFilled(false);
		btnContinue.setEnabled(false);
		btnContinue.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		txtComment = new JLabel("<html><body>0 de ... elementos procesados</body></html>");
		txtComment.setBounds(92, 277, 424, 20);
		
		pbProgress = new JProgressBar(0, 0);
		pbProgress.setBounds(92, 317, 434, 30);
		pbProgress.setBackground(Color.WHITE);
		pbProgress.setBorder(new LineBorder(Color.BLACK));
		
	}

	
	private void assign() {
		add(lblSignature);
		add(lblOriginInformatio);
		add(txtfOriginPath);
		add(btnSearchOriginPath);
		add(cbOpcions);
		add(cbAdvancedOptions);
		add(btnContinue);
		add(txtComment);
		add(pbProgress);
	}
	
	public void assignController(Controller control) {
		btnSearchOriginPath.setActionCommand(Actions.ORIGINFILECHOOSER);
		btnSearchOriginPath.addActionListener((ActionListener)control);
		
		btnSearchDestinyPath.setActionCommand(Actions.DESTINYFILECHOOSER);
		btnSearchDestinyPath.addActionListener((ActionListener)control);
		
		cbOpcions.setActionCommand(Actions.CHECKBOXOPTIONS);
		cbOpcions.addActionListener((ActionListener)control);
		
		btnContinue.setActionCommand(Actions.CONTINUE);
		btnContinue.addActionListener((ActionListener)control);
		
		cbAdvancedOptions.setActionCommand(Actions.CHECKBOXADVANCEDOPTIONS);
		cbAdvancedOptions.addActionListener((ActionListener)control);
		
		advancedOptionsPanel.assignController(control);
	}

	
	public String[] getCbOpciones() {
		String[] data = new String[4];
		if (cbOpcions.isSelected()) {
			data[0] = "true";
		} else {
			data[0] = "false";
		} 
		if (cbAdvancedOptions.isSelected()) {
			data[1] = "true";
		} else {
			data[1] = "false";
		} 
		data[2] = advancedOptionsPanel.getCbOpciones()[0];
		data[3] = advancedOptionsPanel.getCbOpciones()[1];
		return data;
	}
	
	public void setOriginPath(String path) {
		txtfOriginPath.setText(path);
	}
	
	public void setDestinyPath(String path) {
		tfDestinyPath.setText(path);
	}
	
	public void continueTo() {
		if (txtfOriginPath.getText().length() > 0 && cbOpcions.isSelected()) {
			btnContinue.setEnabled(true);
			if (cbAdvancedOptions.isSelected()) {
				if (advancedOptionsPanel.continueTo()) {
					btnContinue.setEnabled(true);
				} else {
					btnContinue.setEnabled(false);
				} 
			}
		} else if (txtfOriginPath.getText().length() == 0 && cbOpcions.isSelected()) {
			btnContinue.setEnabled(false);
		} else if (txtfOriginPath.getText().length() > 0 && tfDestinyPath.getText().length() > 0) {
			btnContinue.setEnabled(true);
			if (cbAdvancedOptions.isSelected()) {
				if (advancedOptionsPanel.continueTo()) {
					btnContinue.setEnabled(true);
				} else {
					btnContinue.setEnabled(false);
				} 
			}
		} 
	}

	
	public String[] capture() {
		String destiny, origin = txtfOriginPath.getText();
		
		if (cbOpcions.isSelected()) {
			destiny = txtfOriginPath.getText();
		} else {
			destiny = tfDestinyPath.getText();
		} 
			String[] advancedOptions = advancedOptionsPanel.capture();
			String[] data = {
					origin, destiny, advancedOptions[0], advancedOptions[1], advancedOptions[2], advancedOptions[3], 
					advancedOptions[4], advancedOptions[5]
			};
		return data;
	}
	
	public void progressBar(String[] data) {
		int min = Integer.parseInt(data[0]);
		int max = Integer.parseInt(data[1]);
		System.out.println(String.valueOf(min) + " de " + max + " elementos procesados");
		txtComment.setText("<html><body>" + min + " de " + max + " elementos procesados</body></html>");
		pbProgress.setValue(min);
		pbProgress.setMaximum(max);
	}
	
	public void activate() {
		txtfOriginPath.setEnabled(true);
		btnSearchOriginPath.setEnabled(true);
		cbOpcions.setEnabled(true);
		tfDestinyPath.setEnabled(true);
		btnSearchDestinyPath.setEnabled(true);
		cbAdvancedOptions.setEnabled(true);
		btnContinue.setEnabled(true);
		advancedOptionsPanel.activate();
	}
	
	public void desactivate() {
		txtComment.setText("0 de ... elementos procesados");
		pbProgress.setValue(0);
		txtfOriginPath.setEnabled(false);
		btnSearchOriginPath.setEnabled(false);
		tfDestinyPath.setEnabled(false);
		btnSearchDestinyPath.setEnabled(false);
		cbOpcions.setEnabled(false);
		cbAdvancedOptions.setEnabled(false);
		btnContinue.setEnabled(false);
		advancedOptionsPanel.desactivate();
	}

	
	public void activateOptions() {
		add(txtDestinyInformation);
		add(tfDestinyPath);
		add(btnSearchDestinyPath);
		if (getCbOpciones()[1].equals("true")) {
			cbAdvancedOptions.setLocation(cbAdvancedOptions.getX(), cbAdvancedOptions.getY()+85);
			advancedOptionsPanel.setLocation(84, advancedOptionsPanel.getY()+85);
			btnContinue.setLocation(245, btnContinue.getY()+85);
			txtComment.setLocation(94, txtComment.getY()+85);
			pbProgress.setLocation(92, pbProgress.getY()+85);
		}else {
			cbAdvancedOptions.setLocation(cbAdvancedOptions.getX(), cbAdvancedOptions.getY()+93);
			btnContinue.setLocation(btnContinue.getX(), btnContinue.getY()+78);
			txtComment.setLocation(txtComment.getX(), txtComment.getY()+75);
			pbProgress.setLocation(pbProgress.getX(), pbProgress.getY()+67);
			btnContinue.setEnabled(false);
		}
	}
	
	public void desactivateOptions() {
		remove(txtDestinyInformation);
		remove(tfDestinyPath);
		remove(btnSearchDestinyPath);
		tfDestinyPath.setText("");
		if (getCbOpciones()[1].equals("true")) {
			cbAdvancedOptions.setLocation(cbAdvancedOptions.getX(), cbAdvancedOptions.getY()-85);
			advancedOptionsPanel.setLocation(84, advancedOptionsPanel.getY()-85);
			btnContinue.setLocation(245, btnContinue.getY()-85);
			txtComment.setLocation(94, txtComment.getY()-85);
			pbProgress.setLocation(92, pbProgress.getY()-85);
		}else {
			cbAdvancedOptions.setLocation(92, 171); 
			btnContinue.setLocation(251, 220);
			txtComment.setLocation(92, 277);
			pbProgress.setLocation(92, 317);
		}
	}
	
	public void activateAdvancedOptions() {
		add(advancedOptionsPanel);
		if (getCbOpciones()[0].equals("false")) {
			advancedOptionsPanel.setLocation(84, 295);
			btnContinue.setLocation(245, btnContinue.getY()+380);
			txtComment.setLocation(94, txtComment.getY()+368);
			pbProgress.setLocation(92, pbProgress.getY()+353);
		}else {
			advancedOptionsPanel.setLocation(84, 205);
			btnContinue.setLocation(245, btnContinue.getY()+366);
			txtComment.setLocation(94, txtComment.getY()+356);
			pbProgress.setLocation(92, pbProgress.getY()+334);
		}
	}
	
	public void desactivateAdvancedOptions() {
		remove(advancedOptionsPanel);
		if (getCbOpciones()[0].equals("false")) {
			btnContinue.setLocation(245, btnContinue.getY()-380);
			txtComment.setLocation(94, txtComment.getY()-368);
			pbProgress.setLocation(92, pbProgress.getY()-353);
		}else {
			cbAdvancedOptions.setLocation(92, 171);
			btnContinue.setLocation(251, 220);
			txtComment.setLocation(92, 277);
			pbProgress.setLocation(92, 317);
		}
	}
	
	public void activateCustomizedDirectory() {
		advancedOptionsPanel.activateCustomizedDirectory();
	}
	
	public void desactivateCustomizedDirectory() {
		advancedOptionsPanel.desactivateCustomizedDirectory();
	}
	
	public void activateFilter() {
		advancedOptionsPanel.activateFilter();
	}
	
	public void desactivateFilter() {
		advancedOptionsPanel.desactivateFilter();
	}
}
