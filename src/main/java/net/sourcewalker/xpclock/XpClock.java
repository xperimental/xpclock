package net.sourcewalker.xpclock;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Date;

import javax.swing.JPanel;

public class XpClock extends JPanel {

    private static final long serialVersionUID = -6314735624389927332L;
    private static final double HOUR_CIRCLE = Math.PI * 2;
    private static final double MINUTE_ARC = HOUR_CIRCLE / 60;
    private static final double SECOND_ARC = MINUTE_ARC / 60;
    private static final String[] HOURS;
    private static final String[] MINUTES;
    private static final String[] SECONDS;
    private static final int CENTER_SIZE = 10;
    private static final double SECOND_RING = 0.2;
    private static final double MINUTE_RING = 0.5;
    private static final double HOUR_RING = 0.8;

    static {
        HOURS = new String[24];
        for (int i = 0; i < 24; i++) {
            HOURS[i] = Integer.toString(i);
        }
        MINUTES = new String[12];
        for (int i = 0; i < 12; i++) {
            MINUTES[i] = Integer.toString(i * 5);
        }
        SECONDS = new String[4];
        for (int i = 0; i < 4; i++) {
            SECONDS[i] = Integer.toString(i * 15);
        }
    }

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

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        double centerX = getWidth() / 2;
        double centerY = getHeight() / 2;
        double hourAngle = getHourFraction() * HOUR_CIRCLE
                + getMinuteFraction() * MINUTE_ARC + getSecondFraction()
                * SECOND_ARC;
        double minuteAngle = hourAngle + getMinuteFraction() * HOUR_CIRCLE
                + getSecondFraction() * SECOND_ARC;
        double secondAngle = minuteAngle + getSecondFraction() * HOUR_CIRCLE;

        g.setColor(getForeground());
        drawCenterDot(g, centerX, centerY);
        drawRings(g, centerX, centerY);
        drawHands(g, centerX, centerY, hourAngle, minuteAngle, secondAngle);
        drawText(g, centerX, centerY, hourAngle, minuteAngle);
    }

    private void drawText(Graphics g, double centerX, double centerY,
            double hourAngle, double minuteAngle) {
        drawText(g, centerX, centerY, 0, HOUR_RING, HOURS);
        drawText(g, centerX, centerY, hourAngle, MINUTE_RING, MINUTES);
        drawText(g, centerX, centerY, minuteAngle, SECOND_RING, SECONDS);
    }

    private void drawText(Graphics g, double centerX, double centerY,
            double startAngle, double size, String[] labels) {
        for (int i = 0; i < labels.length; i++) {
            double frac = (double) i / labels.length;
            double angle = startAngle + frac * HOUR_CIRCLE;
            double x = centerX + Math.sin(angle) * centerX * size;
            double y = centerY - Math.cos(angle) * centerY * size;
            g.drawString(labels[i], (int) x, (int) y);
        }
    }

    private void drawCenterDot(Graphics g, double centerX, double centerY) {
        double x = centerX - CENTER_SIZE / 2;
        double y = centerY - CENTER_SIZE / 2;
        g.fillOval((int) x, (int) y, CENTER_SIZE, CENTER_SIZE);
    }

    private void drawRings(Graphics g, double centerX, double centerY) {
        drawRing(g, centerX, centerY, SECOND_RING);
        drawRing(g, centerX, centerY, MINUTE_RING);
        drawRing(g, centerX, centerY, HOUR_RING);
    }

    private void drawRing(Graphics g, double centerX, double centerY,
            double relSize) {
        double x = centerX - centerX * relSize;
        double y = centerY - centerY * relSize;
        double width = 2 * relSize * centerX;
        double height = 2 * relSize * centerY;
        g.drawOval((int) x, (int) y, (int) width, (int) height);
    }

    private void drawHands(Graphics g, double centerX, double centerY,
            double hourAngle, double minuteAngle, double secondAngle) {
        drawHand(g, centerX, centerY, HOUR_RING, MINUTE_RING, hourAngle);
        drawHand(g, centerX, centerY, SECOND_RING, MINUTE_RING, minuteAngle);
        drawHand(g, centerX, centerY, 0, SECOND_RING, secondAngle);
    }

    @SuppressWarnings("deprecation")
    private double getHourFraction() {
        return (double) time.getHours() / 24;
    }

    @SuppressWarnings("deprecation")
    private double getMinuteFraction() {
        return (double) time.getMinutes() / 60;
    }

    @SuppressWarnings("deprecation")
    private double getSecondFraction() {
        return ((double) time.getSeconds()) / 60;
    }

    private void drawHand(Graphics g, double centerX, double centerY,
            double start, double end, double position) {
        double startX = centerX + Math.sin(position) * start * centerX;
        double startY = centerY - Math.cos(position) * start * centerY;
        double endX = centerX + Math.sin(position) * end * centerX;
        double endY = centerY - Math.cos(position) * end * centerY;
        g.drawLine((int) startX, (int) startY, (int) endX, (int) endY);
    }

}
