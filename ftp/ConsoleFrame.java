package aff;

import java.awt.Graphics;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.BorderFactory;
import java.util.Vector;

public class ConsoleFrame extends JPanel {
	Vector a = new Vector();
	Vector colors = new Vector();

	public ConsoleFrame(int w, int h) {
		setPreferredSize(new Dimension(w,h));
		setBorder(BorderFactory.createLineBorder(Color.RED));
	}
	
	public void setString(String s) {
		this.a.add(s);
	}
	
	public void clear() {
		a.removeAllElements();
	}

	public void setColor(Color cl) {
		this.colors.add(cl);
	}

	public Color getColor(int i) {
		return (Color)this.colors.elementAt(i);
	}

	public void paintComponent(Graphics g) {
		g.clearRect(0,0,this.getWidth(),this.getHeight());
		g.setColor(Color.BLACK);
		g.fillRect(0,0,getWidth(),getHeight());
		int y = 20;
		for(int i=0; i < a.size(); i++) {
			g.setColor(getColor(i));
			String m = (String)a.elementAt(i);
			g.drawString(m,20,y);
			y = y + 20;
		}
		if(y > getHeight()) {
			a.remove(0);
			colors.remove(0);
		}
	}
}