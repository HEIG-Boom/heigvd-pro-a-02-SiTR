package ch.heigvd.sitr.gui.simulation;

import javax.swing.*;
import java.awt.*;

public class MapPanel extends JPanel {
    public MapPanel() {
        Dimension d = new Dimension(800, 800);
        this.setMinimumSize(d);
        this.setMaximumSize(d);
        this.setPreferredSize(d);
        this.setBackground(Color.decode("0x006400"));
    }
}
