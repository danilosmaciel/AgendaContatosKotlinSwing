package ui;

import business.ContactBusiness;
import entity.ContactEntity;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class ContactForm extends Base{
    private JPanel pnlMain;
    private JTextField ttfName;
    private JTextField ttfAdress;
    private JTextField ttfPhone;
    private JButton btnContactCancel;
    private JButton btnContactAdd;
    private final ContactBusiness _contactBusiness;

    public ContactForm(ContactBusiness _contactBusiness){
        iniConfig(pnlMain);
        setDisposedOnClose();
        this._contactBusiness = _contactBusiness;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(  e.getSource().hashCode() == btnContactCancel.hashCode()){
            new MainForm();
            this.dispose();
            return;
        }

        if(  e.getSource().hashCode() == btnContactAdd.hashCode()){
            try{
                _contactBusiness.save(new ContactEntity(ttfName.getText(), ttfAdress.getText(), ttfPhone.getText()));
                new MainForm();
                this.dispose();
                return;
            }catch (Exception excp){
                JOptionPane.showMessageDialog(this,excp.getMessage());
            }
        }
    }

    @Override
    public void setListerners() {
        btnContactCancel.addActionListener(this);
        btnContactAdd.addActionListener(this);
    }
}
