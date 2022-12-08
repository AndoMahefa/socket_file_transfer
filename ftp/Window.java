package aff;

import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.BorderFactory;

import aff.Client_ServerP;

import listener.ServerConnect;

import formulaire.*;
import listener.*;

public class Fenetre extends JFrame {
    JPanel container;

    public void init() {
        container.setSize(getWidth(),getHeight());
        Client_ServerP csp = new Client_ServerP(container.getWidth(),50);
        ConsoleFrame consoleFrame = new ConsoleFrame(container.getWidth(),110);
		FileChooserFilter fcf = new FileChooserFilter(container.getWidth(),40);
		ChooseComponent chooseComponent = new ChooseComponent();
        JPanel down = new JPanel();
        down.setPreferredSize(new Dimension(getWidth(),10));
        container.add(csp); 
        container.add(consoleFrame);
        container.add(fcf);
        container.add(down);
        container.add(chooseComponent);
		
        System.out.println("New serverConnect! ");
		ServerConnect sc = new ServerConnect(consoleFrame,chooseComponent);
		sc.setCSP(csp);
		fcf.getFileChooseB().addActionListener(sc);
        csp.getConnectionB().addActionListener(sc);
        csp.getCreateSB().addActionListener(sc);
	}
	
	public Fenetre(String title, int w, int h) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Rectangle rt = ge.getMaximumWindowBounds();
        setBounds((rt.width/2)-(w/2),(rt.height/2)-(h/2),w,h);
        setTitle(title);
        container = new JPanel();
		setContentPane(container);
		init();
		setVisible(true);
	}

    public Fenetre(String title, int w, int h, ConsoleFrame consoleFrame, ServerConnect sc){
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Rectangle rt = ge.getMaximumWindowBounds();
        setBounds((rt.width/2)-(w/2),(rt.height/2)-(h/2),w,h);
        setTitle(title);
        container = new JPanel();
		setContentPane(container);
        creerFormulaireServer(consoleFrame,sc);
		setVisible(true);
    }

    public void creerFormulaireServer(ConsoleFrame consoleFrame, ServerConnect sc){
        FormulaireServer fs = new FormulaireServer();
        container.add(fs);
        fs.getAnnuler().addActionListener(new ButtonListener(fs,consoleFrame,sc));
        fs.getValider().addActionListener(new ButtonListener(fs,consoleFrame,sc));
    }
}