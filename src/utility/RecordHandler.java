package utility;

import javax.swing.table.DefaultTableModel;

public class RecordHandler {
    public static void addRecord(DefaultTableModel model, Object[] rowData) {
        model.addRow(rowData);
    }

    public static void updateRecord(DefaultTableModel model, int rowIndex, Object[] rowData) {
        for (int i = 0; i < rowData.length; i++) {
            model.setValueAt(rowData[i], rowIndex, i);
        }
    }

    public static void deleteRecord(DefaultTableModel model, int rowIndex) {
        model.removeRow(rowIndex);
    }
}