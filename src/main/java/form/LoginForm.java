package form;

import util.Login;
import util.MessageBox;
import javax.swing.*;
import java.util.logging.Logger;

public class LoginForm extends JFrame{

    public static LoginForm form;
    private String loginType[] = {"Менеджер", "Бухгалтер"};
    private JComboBox loginTypeComboBox = new JComboBox(loginType);
    private JLabel loginLabel = new JLabel("Номер працівника:");
    private JTextField loginField = new JTextField();
    private JLabel passwordLabel = new JLabel("Пароль:");
    private JTextField passwordField = new JTextField();
    private JButton loginButton = new JButton("Увійти");
    private Logger logger = Logger.getLogger(LoginForm.class.getName());

    public LoginForm() {
        super("АІС «Орендна плата за нежитлові приміщення» - Вхід до системи");

        initForm();
        initElement();
        initAction();
    }

    private void initForm() {
        this.setSize(530,220);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setLayout(null);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
    }

    private void initElement() {
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
