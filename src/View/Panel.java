package View;

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

import Control.Controller;


public class Panel extends JPanel{
	private JLabel txtSignature;
	private ImageIcon signature;
	private URL url;
	private JLabel txtOriginInformatio;
	private JTextField tfOriginPath;
	private JButton btnSearchOriginPath;
	private JLabel txtDestinyInformation;
	private JTextField tfDestinyPath;
	private JButton btnSearchDestinyPath;
	private JCheckBox cbOpcions;
	private JCheckBox cbAdvancedOptions;
	private AdvancedOptionsPanel advancedOptionsPanel;
	private JButton btnContinue;
	private JLabel txtComment;
	private JProgressBar pbProgress;
	
	public Panel() {
		setLayout((LayoutManager)null);
		initialize();
		assign();
	}
	
	private void initialize() {
		txtSignature = new JLabel();
		txtSignature.setBounds(-10, 0, 30, 230);
		url = Panel.class.getResource("/signature.png");
		signature = new ImageIcon(url);
		txtSignature.setIcon(new ImageIcon(signature.getImage().getScaledInstance(50, 230, 4)));
		
		txtOriginInformatio = new JLabel("<html><body>Por favor selecciona la path donde se encuentran los archivos que deseas organizar</body></html>");
		
		txtOriginInformatio.setBounds(80, 20, 424, 30);
		
		tfOriginPath = new JTextField();
		tfOriginPath.setBounds(80, 60, 314, 25);
		tfOriginPath.setEditable(false);
		
		btnSearchOriginPath = new JButton("Examinar");
		btnSearchOriginPath.setBounds(414, 60, 90, 25);
		
		cbOpcions = new JCheckBox("<html><body>Utilizar path de origen como path de destino</body></html>", true);
		cbOpcions.setBounds(80, 95, 424, 30);
		
		txtDestinyInformation = new JLabel("<html><body>Por favor selecciona la path donde deseas que se ubiquen los archivos</body></html>");
		txtDestinyInformation.setBounds(80, 135, 424, 30);
		
		tfDestinyPath = new JTextField();
		tfDestinyPath.setBounds(80, 175, 314, 25);
		tfDestinyPath.setEditable(false);
		
		btnSearchDestinyPath = new JButton("Examinar");
		btnSearchDestinyPath.setBounds(414, 175, 90, 25);
		
		cbAdvancedOptions = new JCheckBox("<html><body>Opciones avanzadas</body></html>", false);
		cbAdvancedOptions.setBounds(80, 135, 424, 20);
		
		advancedOptionsPanel = new AdvancedOptionsPanel();
		advancedOptionsPanel.setSize(424, 270);
		
		btnContinue = new JButton("Continuar");
		btnContinue.setBounds(247, 165, 90, 25);
		btnContinue.setEnabled(false);
		
		txtComment = new JLabel("<html><body>0 de ... elementos procesados</body></html>");
		txtComment.setBounds(80, 190, 424, 20);
		
		pbProgress = new JProgressBar(0, 0);
		pbProgress.setBounds(80, 220, 424, 30);
	}

	
	private void assign() {
		add(txtSignature);
		add(txtOriginInformatio);
		add(tfOriginPath);
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
		tfOriginPath.setText(path);
	}
	
	public void setDestinyPath(String path) {
		tfDestinyPath.setText(path);
	}
	
	public void continueTo() {
		if (tfOriginPath.getText().length() > 0 && cbOpcions.isSelected()) {
			btnContinue.setEnabled(true);
			if (cbAdvancedOptions.isSelected()) {
				if (advancedOptionsPanel.continueTo()) {
					btnContinue.setEnabled(true);
				} else {
					btnContinue.setEnabled(false);
				} 
			}
		} else if (tfOriginPath.getText().length() == 0 && cbOpcions.isSelected()) {
			btnContinue.setEnabled(false);
		} else if (tfOriginPath.getText().length() > 0 && tfDestinyPath.getText().length() > 0) {
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
		String destiny, origin = tfOriginPath.getText();
		
		if (cbOpcions.isSelected()) {
			destiny = tfOriginPath.getText();
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
		tfOriginPath.setEnabled(true);
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
		tfOriginPath.setEnabled(false);
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
		cbAdvancedOptions.setLocation(80, 210);
		btnContinue.setLocation(247, 245);
		txtComment.setLocation(80, 275);
		pbProgress.setLocation(80, 315);
		btnContinue.setEnabled(false);
	}
	
	public void desactivateOptions() {
		remove(txtDestinyInformation);
		remove(tfDestinyPath);
		remove(btnSearchDestinyPath);
		tfDestinyPath.setText("");
		cbAdvancedOptions.setLocation(80, 130);
		btnContinue.setLocation(247, 165);
		txtComment.setLocation(80, 190);
		pbProgress.setLocation(80, 220);
	}
	
	public void activateAdvancedOptions() {
		add(advancedOptionsPanel);
		int y = (int)cbAdvancedOptions.getLocation().getY() + 20;
		advancedOptionsPanel.setLocation(80, y);
		y += (int)advancedOptionsPanel.getSize().getHeight();
		btnContinue.setLocation(247, y);
		y += (int)btnContinue.getSize().getHeight();
		txtComment.setLocation(80, y);
		y += (int)txtComment.getSize().getHeight() + 20;
		pbProgress.setLocation(80, y);
	}
	
	public void desactivateAdvancedOptions() {
		remove(advancedOptionsPanel);
		int y = (int)cbAdvancedOptions.getLocation().getY() + 20;
		btnContinue.setLocation(247, y);
		y += (int)btnContinue.getSize().getHeight();
		txtComment.setLocation(80, y);
		y += (int)txtComment.getSize().getHeight() + 20;
		pbProgress.setLocation(80, y);
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
