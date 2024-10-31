package com.raven.datechooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public final class Button extends JButton {

    public boolean isPaintBackground() {
        return paintBackground;
    }

    public void setPaintBackground(boolean paintBackground) {
        this.paintBackground = paintBackground;
    }

    private com.raven.datechooser.Event event;
    private boolean paintBackground = true;
    private Color colorSelected;

    public Button() {
        setBorder(null);
        setContentAreaFilled(false);
        setFocusable(false);
        setBackground(Color.WHITE);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {
                if (!getText().equals("") && getName() != null) {
                    if (getName().equals("day") || getName().equals("year")) {
                        event.execute(me, Integer.valueOf(getText()));
                    } else {
                        event.execute(me, Integer.valueOf(getName()));
                    }
                    setBackground(getColorSelected());
                    setForeground(new Color(255, 255, 255));
                }
            }
        });
    }

    public com.raven.datechooser.Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    @Override
    public void paint(Graphics grphcs) {
        if (paintBackground) {
            int width = getWidth();
            int height = getHeight();
            int size = Math.min(width, height);
            int x = (width - size) / 2;
            int y = (height - size) / 2;
            Graphics2D g2 = (Graphics2D) grphcs;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(getBackground());
            g2.fillOval(x, y, size, size);
        }
        super.paint(grphcs);
    }

    public Color getColorSelected() {
        return colorSelected;
    }

    public void setColorSelected(Color colorSelected) {
        this.colorSelected = colorSelected;
    }
}
