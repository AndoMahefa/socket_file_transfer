package listener;

import aff.Client_ServerP;
import aff.ConsoleFrame;
import aff.ChooseComponent;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileFilter;

import client.FileClient;
import server.FileServer;
import java.net.Socket;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import aff.Fenetre;

import javax.swing.JButton;
import java.sql.Timestamp;
import java.io.File;
import java.net.InetAddress;

import javax.swing.JFileChooser;


public class ServerConnect implements ActionListener {
    Client_ServerP CSP;
    ConsoleFrame consoleFrame;
	FileClient fileC;
	FileServer fs;
	ChooseComponent chooseComponent;
	
	//Getters && Setters
	public FileClient getFileC() {
		return fileC;
	}
	public void setFileC(FileClient fileC) {
		this.fileC = fileC;
	}
	public FileServer getFs() {
		return fs;
	}
	public ConsoleFrame getConsoleFrame() {
		return consoleFrame;
	}
	public void setConsoleFrame(ConsoleFrame consoleFrame) {
		this.consoleFrame = consoleFrame;
	}
	public ChooseComponent getChooseComponent() {
		return chooseComponent;
	}
	public void setChooseComponent(ChooseComponent chooseComponent) {
		this.chooseComponent = chooseComponent;
	}
	public void setFs(FileServer fs) {
		this.fs = fs;
	}  
    public void setLc(ConsoleFrame consoleFrame) {
        this.consoleFrame = consoleFrame;
    }
    public void setCSP(Client_ServerP csp) {
        this.CSP = csp;
    }
    public ConsoleFrame getLc() {
        return this.consoleFrame;
    }
    public Client_ServerP getCSP() {
        return this.CSP;
    }

	//Constructeurs
    public ServerConnect(Client_ServerP csp, ConsoleFrame consoleFrame) {
        setCSP(csp);
        setLc(consoleFrame);
    }

    public ServerConnect(ConsoleFrame consoleFrame, ChooseComponent chooseComponent) {
		setLc(consoleFrame);
		setChooseComponent(chooseComponent);
    }
	
	//fonction miselecte fichier
	public File[] getSelectedFiles(String filter) {
		JFileChooser fc = new JFileChooser();
		System.out.println("filter" + filter);
		
		if(filter != "") {
			FileFilter filtre = new FileNameExtensionFilter("",filter);
			fc.addChoosableFileFilter(filtre);
		}
		
		fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		
		fc.setMultiSelectionEnabled(true);
		System.out.println(fc.isMultiSelectionEnabled());
		int n = fc.showOpenDialog(null);
		File[] files = null;
		
		if(n == JFileChooser.APPROVE_OPTION) {
			files = fc.getSelectedFiles();
		}
		
		return files;
	}

    public void actionPerformed(ActionEvent e) {
        boolean b = e.getSource().getClass() == JButton.class; 
		String ms = null;
		Timestamp d = new Timestamp(System.currentTimeMillis());
        
		//connexion
        if( b && ((JButton)e.getSource()).getText().equals("Connection")) {
            try {
                String host = getCSP().getHostV().getText();
                int port = Integer.parseInt(getCSP().getPortV().getText());
                fileC = new FileClient(host,port);
				
                ms = "Connexion etablie avec succes";
                consoleFrame.setColor(Color.GREEN);
            }
            catch(Exception exp) {
                ms = "Erreur de connexion";
                consoleFrame.setColor(Color.RED);
				exp.printStackTrace();
            }
			
			consoleFrame.setString(d + " " + ms);
			consoleFrame.repaint();
        }

		//creation serveur
        if(b && ((JButton)e.getSource()).getText().equals("New Server")){
            Fenetre formulaireServer = new Fenetre("Creation nouveau server",400,100,consoleFrame,this);  
        }

		//mchoisir anle fichier + mandefa ilay fichier
		if(b && ((JButton)e.getSource()).getText().equals("Choisir un fichier ...")){
			if(fileC != null) {
				try {
					System.out.println("Envoi des fichiers");
					getChooseComponent().clear();
					File[] files = getSelectedFiles("");
					sendFiles(files);	
					ms = "Transfert reussi";
					consoleFrame.setColor(Color.GREEN);
				}
				catch(Exception exp) {
					ms = "Erreur lors du transfert";
					consoleFrame.setColor(Color.RED);
					exp.printStackTrace();
				}
				
				consoleFrame.setString(d + " " + ms);
				consoleFrame.repaint();
			}
			else {
				ms = "Pas encore connecte au serveur";
				consoleFrame.setColor(Color.RED);
				consoleFrame.setString(d + " " + ms);
				consoleFrame.repaint();
			}
        }
		
    }
	
	//fonction misend anle fichier
	public void sendFiles(File[] files) throws Exception {
		InetAddress cI = fileC.getS().getInetAddress();
		System.out.println(cI.getHostAddress());
		System.out.println(fileC.getS().getPort());
		int port = fileC.getS().getPort();
		
		for(int i=0; i <  files.length; i++) {
			FileClient newF = new FileClient(cI.getHostAddress(),port,files[i].getPath(),getChooseComponent());
			Thread nF = new Thread(newF);
			nF.start();
			System.out.println("thread actif " + Thread.activeCount());
		}
	}
}