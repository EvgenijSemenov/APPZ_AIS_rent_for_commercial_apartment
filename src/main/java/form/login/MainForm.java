package form.login;

import form.organization.OrganizationForm;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class MainForm extends JFrame{
    public static MainForm form;
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
        this.setLocationRelativeTo(null);

        loginTypeComboBox.addItemListener(new LoginTypeItemListener());
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Login login = new Login(loginField.getText(), passwordField.getText());
                if (login.isValid()) {
                    new OrganizationForm(form, login.organization());
                    form.setVisible(false);
                } else {
                    msgbox("Невірний логін та/або пароль");
                }
            }
        });
    }

    private void msgbox(String s){
        JOptionPane.showMessageDialog(null, s);
    }

    public static void main(String[] args) {
        MainForm.form = new MainForm();
        MainForm.form.setVisible(true);
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
