package ch.heigvd.sitr.gui.simulation;

import javax.swing.*;
import java.awt.*;

public class MapPanel extends JPanel {

    private static final String backgroundColor = "0x006400";
    public static final int WIDTH = 800;
    public static final int HEIGHT = 800;

    public MapPanel() {
        Dimension d = new Dimension(WIDTH, HEIGHT);
        this.setMinimumSize(d);
        this.setMaximumSize(d);
        this.setPreferredSize(d);
        this.setBackground(Color.decode(backgroundColor));
    }
}
