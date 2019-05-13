/*
 * Filename : App.java
 * Creation date : 23.03.2019
 */

package ch.heigvd.sitr;

import ch.heigvd.sitr.gui.settings.SettingsWindow;

import javax.swing.*;
import java.awt.*;

/**
 * Main entry point for SiTR
 *
 * @author Luc Wachter
 */
public class App {
    public static void main(String[] args) {
        // fix the font for each Button
        UIManager.put("Button.font", new Font("Helvetica", Font.BOLD, 11));

        SettingsWindow.getInstance();
    }
}
