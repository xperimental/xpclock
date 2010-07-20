package net.sourcewalker.xpclock;

import java.awt.Color;
import java.util.Date;

import javax.swing.JPanel;

public class XpClock extends JPanel {

    private static final long serialVersionUID = -6314735624389927332L;
    private Date time;

    public XpClock() {
        super(true);
        setBackground(Color.BLACK);
        setForeground(Color.WHITE);

        time = new Date();
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
        repaint();
    }

}
