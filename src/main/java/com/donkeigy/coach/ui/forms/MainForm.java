package com.donkeigy.coach.ui.forms;

import com.donkeigy.coach.ui.dialogs.YahooOauthDialog;
import com.donkeigy.coach.ui.panels.LeaguePanel;
import com.yahoo.engine.YahooFantasyEngine;
import com.yahoo.objects.api.YahooApiInfo;
import com.yahoo.services.YahooServiceFactory;
import com.yahoo.utils.oauth.OAuthConnection;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by cedric on 10/28/14.
 */
public class MainForm extends JFrame {
    private JTabbedPane tabbedPane1;
    private JPanel mainPanel;
    private JButton loadYahooButton;
    private JPanel loadPanel;
    private LeaguePanel leaguePanel;
    private JPanel leagueContainerPanel;
    private JPanel teamsPanel;


    private OAuthConnection conn;
   // private YQLQueryUtil yqlQueryUtil;

    public MainForm(String title) {
        super(title);

        YahooApiInfo info =
                new YahooApiInfo("dj0yJmk9MWNNeHFyMVZneFdFJmQ9WVdrOVNqVm9hSGQ2TXpZbWNHbzlNVEU0TURVM09UYzJNZy0tJnM9Y29uc3VtZXJzZWNyZXQmeD0wYQ--",
                        "9e1bb2700b79696770c9c931b182bf12260eb4e6");

        YahooFantasyEngine engine = new YahooFantasyEngine(info);
        conn = YahooFantasyEngine.getoAuthConn();
        YahooServiceFactory factory = YahooFantasyEngine.getServiceFactory();

        ((LeaguePanel) leaguePanel).init(factory);

        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        addActionListeners();

    }

    private void createUIComponents() {
        tabbedPane1 = new JTabbedPane();
        leaguePanel = new LeaguePanel();
        // tabbedPane1.addTab("Leagues", leaguePanel);
    }

    private void addActionListeners() {

        loadYahooButton.setActionCommand("LOAD_YAHOO");
        loadYahooButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cmd = e.getActionCommand();
                if (cmd.equals("LOAD_YAHOO")) //action for load button;
                {

                    if (!conn.connect())
                    {
                        showYahooOauthDialog();

                    }
                    else
                    {
                        // showYahooLoadDialog();
                        populateOAuthInfo();
                    }
                }
            }
        });
    }

    private void showYahooOauthDialog() {
        YahooOauthDialog dialog = new YahooOauthDialog(conn, this);
        dialog.pack();
        dialog.setVisible(true);
    }

    public void populateOAuthInfo()
    {
        ((LeaguePanel) leaguePanel).populateData();
    }

}
