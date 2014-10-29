package com.donkeigy.coach.ui.dialogs;

import com.donkeigy.coach.ui.panels.TeamComparePanel;
import com.yahoo.objects.team.Team;
import com.yahoo.objects.team.TeamStat;

import javax.swing.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class TeamCompareDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private TeamComparePanel teamComparePanel1;
    private Map<String, List<TeamStat>> teamStatMap = new HashMap<String, List<TeamStat>>();
    private List<Team> teams = new LinkedList<Team>();
    public TeamCompareDialog(List<Team> teams, Map<String, List<TeamStat>> teamStatMap)
    {
        this.teams = teams;
        this.teamStatMap = teamStatMap;
        teamComparePanel1 = new TeamComparePanel(teams, teamStatMap);

        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

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
       teamComparePanel1 = new TeamComparePanel(new LinkedList<Team>(), new HashMap<String, List<TeamStat>>());
    }
}
