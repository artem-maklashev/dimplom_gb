package org.maklashev.goldengroup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import javax.swing.*;

@SpringBootApplication
public class GoldengroupApplication {

	public static void main(String[] args) {
//		SwingUtilities.invokeLater(() -> {
//			JFrame frame = new JFrame("Shift Information");
//			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//			ConfigurableApplicationContext context = SpringApplication.run(GoldengroupApplication.class, args);
//			ShiftConsoleReader shiftConsoleReader = context.getBean(ShiftConsoleReader.class);
//			shiftConsoleReader.readAndPrintShifts(frame);
			ConfigurableApplicationContext context = SpringApplication.run(GoldengroupApplication.class, args);
//		});
	}



}
