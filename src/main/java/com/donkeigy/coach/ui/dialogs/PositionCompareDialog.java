package com.donkeigy.coach.ui.dialogs;

import com.donkeigy.coach.services.PlayerDataServices;
import com.donkeigy.coach.ui.panels.PositionComparePanel;
import com.yahoo.objects.team.Team;

import javax.swing.*;
import java.awt.event.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class PositionCompareDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private PositionComparePanel positionComparePanel1;
    private PlayerDataServices playerDataServices;

    public PositionCompareDialog(List<Team> teams ,PlayerDataServices playerDataServices) {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        positionComparePanel1.init(teams, playerDataServices);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

// call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

// call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
// add your code here
        dispose();
    }

    private void onCancel() {
// add your code here if necessary
        dispose();
    }



    private void createUIComponents() {
        positionComparePanel1 = new PositionComparePanel();
    }
}
