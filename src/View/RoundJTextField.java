package View;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class RoundJTextField extends JTextField {
    private Shape shape;
	private Icon icon;
	private Image image;
	
    public RoundJTextField(int size) {
        super(size);
        setOpaque(false); // As suggested by @AVD in comment.
    }
    protected void paintComponent(Graphics g) {
         g.setColor(getBackground());
         g.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, 15, 15);
         g.drawImage(getImage(), 5, 2, getHeight()-3, getHeight()-3, null); 
         setBorder(new EmptyBorder(0,(int)(getHeight()*1.2),0,2)); 
         super.paintComponent(g);
    }
	protected void paintBorder(Graphics g) {
         g.setColor(Color.BLACK);
         g.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 15, 15);
    }
    public boolean contains(int x, int y) {
         if (shape == null || !shape.getBounds().equals(getBounds())) {
             shape = new RoundRectangle2D.Float(0, 0, getWidth()-1, getHeight()-1, 15, 15);
         }
         return shape.contains(x, y);
    }
    public void setIcon(Icon icon){ 
        this.icon=icon; 
        setImage(((ImageIcon)icon).getImage()); 
    } 

    public void setImage(Image image) { 
        this.image = image; 
    }
	public Image getImage() {
		return image;
	} 
    
}