package formulaire; 

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;

public class FormulaireServer extends JPanel{
    JLabel portL;
    JTextField portValue;
    JButton valider;
    JButton annuler;

    public FormulaireServer(JLabel portL,JTextField portValue,JButton valider,JButton annuler){
        setPortL(portL);
        setPortValue(portValue);
        setValider(valider);
        setAnnuler(annuler);
    }

    public FormulaireServer(){
        setPortL(new JLabel("port"));
        setPortValue(new JTextField(10));
        setValider(new JButton("valider"));
        setAnnuler(new JButton("annuler"));
        add(portL);
        add(portValue);
        add(valider);
        add(annuler);
    }

    public void setPortL(JLabel portL){
        this.portL = portL;
    }
    public JLabel getPortL(){
        return this.portL;
    }

    public void setPortValue(JTextField portValue){
        this.portValue = portValue;
    }
    public JTextField getPortValue(){
        return this.portValue;
    }

    public void setValider(JButton valider){
        this.valider = valider;
    }
    public JButton getValider(){
        return this.valider;
    }

    public void setAnnuler(JButton annuler){
        this.annuler = annuler;
    }
    public JButton getAnnuler(){
        return this.annuler;
    }
}