package org.maklashev.goldengroup;

import org.maklashev.goldengroup.model.MyService;
import org.maklashev.goldengroup.model.entity.Shift;
import org.maklashev.goldengroup.model.entity.Types;
import org.maklashev.goldengroup.model.entity.gypsumboard.GypsumBoard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.util.List;

@Component
public class ShiftConsoleReader {
    private final MyService myService;

    @Autowired
    public ShiftConsoleReader(MyService myService) {
        this.myService = myService;
    }

    public void readAndPrintShifts(JFrame frame) {
        List<Shift> shifts = myService.getAllShifts();
//        List<Types> types = myService.getAllTypes();
//        JTextArea textArea = new JTextArea();
//        textArea.setEditable(false);
//
//        for (Shift shift : shifts) {
//            String shiftInfo = "Shift ID: " + shift.getId() + ", Name: " + shift.getName() + "\n";
//            textArea.append(shiftInfo);
////            System.out.println(shiftInfo);
//        }

//        for (Types type: types) {
//            System.out.println(type);
//        }
//        Shift shift = myService.getShiftById(1);
//        System.out.println("мена с индексом 1: " + shift);
//        JScrollPane scrollPane = new JScrollPane(textArea);
        //---
        List<GypsumBoard> gypsumBoards = myService.getAllGypsumBoards();
        DefaultTableModel tableModel = new DefaultTableModel();
        JTable table = new JTable(tableModel);

        tableModel.addColumn("Shift ID");
        tableModel.addColumn("Name");

        for (GypsumBoard gb : gypsumBoards) {
            tableModel.addRow(new Object[] { gb.getId(), gb.toString() });
        }

        TableColumn tableColumn = table.getColumnModel().getColumn(0);
        tableColumn.setPreferredWidth(50);
        TableColumn tableColumn2 = table.getColumnModel().getColumn(1);
        tableColumn2.setPreferredWidth(350);
        JScrollPane scrollPane = new JScrollPane(table);
        //---
        scrollPane.setPreferredSize(new Dimension(400, 300));
//
//        for (GypsumBoard gb: gypsumBoards) {
//            System.out.println(gb);
//        }


        frame.getContentPane().add(scrollPane);
        frame.pack();
        frame.setVisible(true);
    }
}
