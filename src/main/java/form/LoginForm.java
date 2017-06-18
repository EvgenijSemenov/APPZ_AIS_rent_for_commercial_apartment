package form;

import util.Login;
import util.MessageBox;
import javax.swing.*;

public class LoginForm extends JFrame{

    public static LoginForm form;
    private JLabel loginLabel = new JLabel("Номер працівника:");
    private JTextField loginField = new JTextField();
    private JLabel passwordLabel = new JLabel("Пароль:");
    private JTextField passwordField = new JTextField();
    private JButton loginButton = new JButton("Увійти");

    public LoginForm() {
        super("АІС «Орендна плата за нежитлові приміщення» - Вхід до системи");

        initForm();
        initElement();
        initAction();
    }

    private void initForm() {
        this.setSize(530,170);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
    }

    private void initElement() {
        this.add(loginLabel);
        this.add(loginField);
        this.add(passwordLabel);
        this.add(passwordField);
        this.add(loginButton);

        loginLabel.setBounds(20, 5, 485, 25);
        loginField.setBounds(20, 25, 485, 25);
        passwordLabel.setBounds(20, 50, 485, 25);
        passwordField.setBounds(20, 70, 485, 25);
        loginButton.setBounds(20, 100, 485, 25);
    }

    private void initAction() {
        loginButton.addActionListener(e -> {
            Login login = new Login(loginField.getText(), passwordField.getText());
            if (login.isValid()) {
                new MenuForm(form, login.employee());
                form.setVisible(false);
            } else {
                new MessageBox("Невірний логін та/або пароль");
            }
        });
    }

}
