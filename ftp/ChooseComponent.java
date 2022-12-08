package aff;

import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.GridBagLayout; 
import java.awt.Insets; 
import java.util.Vector;

public  class ChooseComponent extends JPanel {
	GridBagLayout gd = new GridBagLayout();
	GridBagConstraints c = new GridBagConstraints();
	Vector a = new Vector();
	Vector jpbs = new Vector();

	public void addLabel(JLabel s) {
		this.a.add(s);
		c.gridwidth = GridBagConstraints.RELATIVE;
		c.insets = new Insets(20,0,0,50);
		gd.setConstraints((JLabel)a.elementAt(a.indexOf(s)),c);
		this.add((JLabel)a.elementAt(a.indexOf(s)));
	}

	public void clear() {
		removeAll();
		a.removeAllElements();
		jpbs.removeAllElements();
	}

	public void addJPB(JProgressBar jpb) {
		this.jpbs.add(jpb);
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(20,0,0,50);
		gd.setConstraints((JProgressBar)jpbs.elementAt(jpbs.indexOf(jpb)),c);
		this.add((JProgressBar)jpbs.elementAt(jpbs.indexOf(jpb)));	
	}

	public ChooseComponent() {
		setLayout(gd);
		c.fill = GridBagConstraints.BOTH;
	}
}