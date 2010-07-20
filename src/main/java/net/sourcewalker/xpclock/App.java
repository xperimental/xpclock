package net.sourcewalker.xpclock;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.Timer;

/**
 * Hello world!
 */
public class App {

    public static void main(String[] args) {
        JFrame mainFrame = new JFrame("XP Clock");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setMinimumSize(new Dimension(300, 300));

        Container contentPane = mainFrame.getContentPane();
        contentPane.setLayout(new BorderLayout());
        final XpClock clock = new XpClock();
        contentPane.add(clock, BorderLayout.CENTER);
        Timer clockTimer = new Timer(500, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                clock.setTime(new Date());
            }
        });
        clockTimer.start();

        mainFrame.setVisible(true);
    }
}
