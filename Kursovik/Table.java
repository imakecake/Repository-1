import java.util.*; 
import javax.swing.table.AbstractTableModel; 
import javax.swing.event.TableModelListener;;

public class Table extends AbstractTableModel{ 
    ArrayList<Exam> exam; 
    private Set<TableModelListener> listeners = new HashSet<TableModelListener>();
    boolean b = false;
    public Table(ArrayList<Exam> exam){ 
        super(); 
        this.exam = exam; 
    } 
    @Override
    public int getRowCount() {return exam.size();} 
    @Override
    public int getColumnCount(){return 3;}
    
    public boolean isCellEditable(int r, int c) {return b;}
    public boolean getIsCellEditable(){return b;}
    public void setIsCellEditable(boolean b){this.b=b;}
    public boolean isEditable(boolean b){return b;} 

    @Override
    public String getColumnName(int c){ 
        String result = ""; 
        switch (c) { 
            case 0: 
                result = "Номер паспорта"; 
            break; 
            case 1: 
                result = "Шифр специальности"; 
            break; 
            case 2: 
                result = "Суммарный балл"; 
            break; 
        } 
        return result; 
    } 
    
    @Override
    public Object getValueAt(int r, int c) 
    { 
        switch (c) 
        { 
            case 0: 
                return exam.get(r).getId(); 
            case 1: 
                return exam.get(r).getCipher(); 
            case 2: 
                return exam.get(r).getBall(); 
            default: 
                return ""; 
        } 
    } 
    
    public void setValueAt(Object aValue, int r,int c) {
        switch (c ) {
            case 0:
                exam.get(r).setId((String) aValue);
                break;
            case 1:
                exam.get(r).setCipher((String) aValue);
                break;
            case 2:
                exam.get(r).setBall((String) aValue);
                break;
        }
        fireTableCellUpdated(r, c);
    }                                                                                     
}

