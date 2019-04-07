/*
 * Filename : App.java
 * Creation date : 23.03.2019
 */

package ch.heigvd.sitr;

import ch.heigvd.sitr.gui.settings.SettingsWindow;

/**
 * Main entrypoint for SiTR
 *
 * @author Luc Wachter
 */
public class App {
    public static void main(String[] args) {
        SettingsWindow.getInstance();
    }
}
