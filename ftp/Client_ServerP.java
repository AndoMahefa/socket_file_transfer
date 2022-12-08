package aff;

import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.JButton;

import java.awt.Dimension;
import java.awt.Color;

import listener.ServerConnect;

public class Client_ServerP extends JPanel {
    JLabel hostL;
    JTextField hostV;
    JLabel portL;
    JTextField portV;
    JButton connectionB;
    JButton createSB;

    public Client_ServerP(int w, int h) {
        setPreferredSize(new Dimension(w,h));
        setBorder(BorderFactory.createLineBorder(Color.BLUE));
        setHostL(new JLabel("host"));
        setHostV(new JTextField(10));
        setPortL(new JLabel("port"));
        setPortV(new JTextField(10));
        setConnectionB(new JButton("Connection"));
        setCreateSB(new JButton("New Server"));

        add(hostL);
        add(hostV);
        add(portL);
        add(portV);
        add(connectionB);
        add(createSB);
    }

    public JButton getConnectionB() {
        return this.connectionB;
    }

    public void setConnectionB(JButton connectionB) {
        this.connectionB = connectionB;
    }
    
    public JButton getCreateSB() {
        return this.createSB;
    }

    public void setCreateSB(JButton CreateSB) {
        this.createSB = CreateSB;
    }

    public JLabel getHostL() {
        return this.hostL;
    }

    public void setHostL(JLabel hostL) {
        this.hostL = hostL;
    }

    public JTextField getHostV() {
        return this.hostV;
    }

    public void setHostV(JTextField hostV) {
        this.hostV = hostV;
    }

    public JLabel getPortL() {
        return this.portL;
    }

    public void setPortL(JLabel portL) {
        this.portL = portL;
    }

    public JTextField getPortV() {
        return this.portV;
    }

    public void setPortV(JTextField portV) {
        this.portV = portV;
    }
}