package ch.heigvd.sitr.gui;

import javax.swing.*;

public class SettingsWindow {
    private JPanel SettingsPanel;
    private JComboBox ListScenario;
    private JComboBox ListOneControlor;
    private JComboBox ListTwoControlor;
    private JComboBox ListThreeControlor;
    private JSpinner SpinOneControlor;
    private JSpinner SpinTwoControlor;
    private JComboBox ListEndDestination;
    private JButton Validate;
    private JSpinner SpinThreeControlor;
    private JLabel Titre;
    private JLabel SousTitre;
    private JLabel ChoiceParameter;
    private JLabel ChoiceControlor;
    private JLabel ChoiceDestinatio;
    private JLabel ChoiceScenario;

    public static void main(String[] args) {
        JFrame frame = new JFrame("test");
        frame.setContentPane(new SettingsWindow().SettingsPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
