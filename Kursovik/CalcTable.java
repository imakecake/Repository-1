import java.util.*; 
import javax.swing.table.AbstractTableModel; 
import javax.swing.event.TableModelListener;

public class CalcTable extends AbstractTableModel{ 
    ArrayList<Budget> budget; 
    private Set<TableModelListener> listeners = new HashSet<TableModelListener>();
    boolean b = false;
    public CalcTable(ArrayList<Budget> budget){ 
        super(); 
        this.budget = budget; 
    } 
    @Override
    public int getRowCount() {return budget.size();} 
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
                return budget.get(r).getId(); 
            case 1: 
                return budget.get(r).getCipher(); 
            case 2: 
                return budget.get(r).getBall(); 
            default: 
                return ""; 
        } 
    }                                                                        
}