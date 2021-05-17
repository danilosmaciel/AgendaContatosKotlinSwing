package ui;

import business.ContactBusiness;
import entity.ContactEntity;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import java.awt.event.ActionEvent;
import java.util.List;

public class MainForm extends Base{
    private JPanel pnlMain;
    private JButton btnAdd;
    private JButton btnRemove;
    private JTextField tfdSearch;
    private JTable tblData;
    private JLabel ttvCounter;
    private ContactBusiness _contactBusiness;

    public MainForm(){
        iniConfig(pnlMain);
        setExitOnClose();
        _contactBusiness = new ContactBusiness();
        loadTable();
        ttvCounter.setText(_contactBusiness.getTextContactsCounter());
    }

    private void loadTable() {
        final List<ContactEntity> list = _contactBusiness.getList();
        String[] columnNames = {"Nome","Endereco","Telefone"};
        DefaultTableModel tableModel = new DefaultTableModel(new Object[0][0], columnNames);
        for(ContactEntity contact : list){
            tableModel.addRow(getObjectContact(contact));
        }

        tblData.clearSelection();
        tblData.setModel(tableModel);
    }

    private Object[] getObjectContact(ContactEntity contact) {
        final Object[] o = new Object[3];
        o[0] = contact.getName();
        o[1] = contact.getAdress();
        o[2] = contact.getPhone();

        return o;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(  e.getSource().hashCode() == btnAdd.hashCode()){
            new ContactForm(_contactBusiness);
            this.dispose();
            return;
        }

        if(  e.getSource().hashCode() == btnRemove.hashCode()){
            if(tblData.getSelectedRow() > 0){
                try{
                    final String name = tblData.getValueAt(tblData.getSelectedRow(), 0).toString();
                    final String adress = tblData.getValueAt(tblData.getSelectedRow(), 1).toString();
                    final String phone = tblData.getValueAt(tblData.getSelectedRow(), 2).toString();
                    _contactBusiness.delete(new ContactEntity(name,adress,phone));

                    JOptionPane.showMessageDialog(this,"Contato excluído com sucesso");
                    loadTable();
                    return;
                }catch (Exception excep){
                    JOptionPane.showMessageDialog(this,excep.getMessage());
                }
            }

            JOptionPane.showMessageDialog(this,"Selectione um registro para excluir!");
        }
    }

    @Override
    public void setListerners() {
        btnAdd.addActionListener(this);
        btnRemove.addActionListener(this);
    }
}
