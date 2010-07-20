package net.sourcewalker.xpclock;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JFrame;

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
        contentPane.add(new XpClock(), BorderLayout.CENTER);

        mainFrame.setVisible(true);
    }
}
