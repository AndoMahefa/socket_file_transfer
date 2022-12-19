package aff;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.BorderFactory;
import java.awt.Dimension;
import java.awt.Color;

public class FileChooserFilter extends JPanel {
	JButton fileChooseB;
	
	public FileChooserFilter(int w, int h) {
		setPreferredSize(new Dimension(w,h));
		setBorder(BorderFactory.createLineBorder(Color.BLUE));
		setFileChooseB(new JButton("Choisir un fichier ..."));
		add(fileChooseB);
	}
	
	public void setFileChooseB(JButton fcb) {
		this.fileChooseB = fcb;
	}
	
	public JButton getFileChooseB() {
		return this.fileChooseB;
	}
}