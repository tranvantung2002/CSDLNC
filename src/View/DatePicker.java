package View;

import com.raven.datechooser.DateChooser;
import com.raven.datechooser.EventDateChooser;
import com.raven.datechooser.SelectedAction;
import com.raven.datechooser.SelectedDate;

import javax.swing.*;
import java.awt.*;

public class DatePicker extends JPanel {
    private final DateChooser dateChooser;
    private final JTextField txtDate;
    private final JButton btnPick;

    public DatePicker() {
        setVisible(true);
        setLayout(new FlowLayout());

        dateChooser = new DateChooser();
        txtDate = new JTextField(15); // Set width for the text field
        btnPick = new JButton("...");

        dateChooser.setTextRefernce(txtDate);

        btnPick.addActionListener(evt -> showDateChooser());

        // Add components to the panel
        add(txtDate);
        add(btnPick);

        // Configure the date chooser
        dateChooser.addEventDateChooser(new EventDateChooser() {
            @Override
            public void dateSelected(SelectedAction action, SelectedDate date) {
                System.out.println(date.getDay() + "-" + date.getMonth() + "-" + date.getYear());
                if (action.getAction() == SelectedAction.DAY_SELECTED) {
                    txtDate.setText(date.getDay() + "-" + date.getMonth() + "-" + date.getYear());
                    dateChooser.hidePopup();
                }
            }
        });
    }

    private void showDateChooser() {
        dateChooser.showPopup();
    }
}