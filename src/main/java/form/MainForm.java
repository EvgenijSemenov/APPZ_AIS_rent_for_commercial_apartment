package form;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class MainForm extends JFrame{
    private String loginType[] = {"Організація", "Юредична/фізична особа"};
    private JComboBox loginTypeComboBox = new JComboBox(loginType);
    private JLabel loginLabel = new JLabel("Код організації:");
    private JTextField loginField = new JTextField();
    private JLabel passwordLabel = new JLabel("Пароль:");
    private JTextField passwordField = new JTextField();
    private JButton loginButton = new JButton("Увійти");

    public MainForm() {
        super("АІС «Орендна плата за нежитлові приміщення» - Вхід до системи");
        this.setBounds(100,100,530,220);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.add(loginTypeComboBox);
        this.add(loginLabel);
        this.add(loginField);
        this.add(passwordLabel);
        this.add(passwordField);
        this.add(loginButton);

        loginTypeComboBox.setBounds(20, 10, 485, 25);
        loginLabel.setBounds(20, 40, 485, 25);
        loginField.setBounds(20, 65, 485, 25);
        passwordLabel.setBounds(20, 95, 485, 25);
        passwordField.setBounds(20, 120, 485, 25);
        loginButton.setBounds(20, 155, 485, 25);

        this.setLayout(null);
        this.setResizable(false);

        loginTypeComboBox.addItemListener(new LoginTypeItemListener());
    }

    public static void main(String[] args) {
        MainForm app = new MainForm();
        app.setVisible(true);
    }

    class LoginTypeItemListener implements ItemListener {
        @Override
        public void itemStateChanged(ItemEvent e) {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                if (loginTypeComboBox.getSelectedIndex() == 0) {
                    loginLabel.setText("Код організації:");
                } else if (loginTypeComboBox.getSelectedIndex() == 1) {
                    loginLabel.setText("Індивідуальний податковий номер:");
                }
            }
        }
    }
}
