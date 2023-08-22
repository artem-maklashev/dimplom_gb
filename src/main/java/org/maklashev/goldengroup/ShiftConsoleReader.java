package org.maklashev.goldengroup;

import org.maklashev.goldengroup.model.MyService;
import org.maklashev.goldengroup.model.entity.Shift;
import org.maklashev.goldengroup.model.entity.Types;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.swing.*;
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
        List<Types> types = myService.getAllTypes();
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);

        for (Shift shift : shifts) {
            String shiftInfo = "Shift ID: " + shift.getId() + ", Name: " + shift.getName() + "\n";
            textArea.append(shiftInfo);
            System.out.println(shiftInfo);
        }

        for (Types type: types) {
            System.out.println(type);
        }
        Shift shift = myService.getShiftById(1);
        System.out.println("мена с индексом 1: " + shift);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(400, 300));

        frame.getContentPane().add(scrollPane);
        frame.pack();
        frame.setVisible(true);
    }
}
