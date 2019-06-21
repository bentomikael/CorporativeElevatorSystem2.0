package br.ufsc.ine5605.Screen;

import javax.swing.JPanel;
import javax.swing.JTable;


public class Table extends JPanel implements IPanel{

    private JTable table;

    public Table() {
       Object[] index_list = {"Name","Code","Occupation","Actual Floor"};
       Object[] index_report = {"Name","Action","Altered","Date","Hour","Floor"};
       
    }
    
    
    @Override
    public Signal getSignal() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void resetSignal() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
