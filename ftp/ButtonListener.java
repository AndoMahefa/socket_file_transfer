package listener;

import aff.Window;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import java.awt.event.WindowEvent;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import server.FileServer;
import aff.ConsoleFrame;
import aff.ConsoleFrame;

import java.sql.Timestamp;

import formulaire.*;

public class ButtonListener implements ActionListener{
    FormulaireServer fs;
	ConsoleFrame consoleFrame;
	ServerConnect sc;
	
	//Getters && Setters
	public void setLc(ConsoleFrame consoleFrame) {
		this.consoleFrame = consoleFrame;
	}
	
	public ConsoleFrame getLc() {
		return this.consoleFrame;
	}

    public void setFs(FormulaireServer fs){
        this.fs = fs;
    }
    public FormulaireServer getFs(){
        return this.fs;
    }

	//Constructeurs
	public ButtonListener(FormulaireServer fs, ConsoleFrame consoleFrame, ServerConnect sc){
        setFs(fs);
		setLc(consoleFrame);
		this.sc = sc;
	}
	
	//functions
	public void closeWindow() {
		JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(getFs());
		topFrame.dispatchEvent(new WindowEvent(topFrame,WindowEvent.WINDOW_CLOSING));
	}

    public void actionPerformed(ActionEvent e) {
        boolean b = e.getSource().getClass() == JButton.class; 
        if(b==true && ((JButton)e.getSource()).getText().equals("valider")){
			String messResult = null;
			Timestamp d = new Timestamp(System.currentTimeMillis());	
			try {
				System.out.print("create server with ");
				int port = Integer.parseInt(getFs().getPortValue().getText());
				System.out.println("port: " + port);
				FileServer fileServeur = new FileServer(port);
				Thread fs = new Thread(fileServeur);
				fs.start();
				this.sc.setFs(fileServeur);
				messResult = "Serveur cree avec succes";
                consoleFrame.setColor(Color.GREEN);
			}
			catch(Exception exp) {
				messResult = "Erreur lors de la creation du serveur";
                consoleFrame.setColor(Color.RED);
			}
			
			consoleFrame.setString(d + " " + messResult);
            consoleFrame.repaint();
		}		
		closeWindow();
    }
}