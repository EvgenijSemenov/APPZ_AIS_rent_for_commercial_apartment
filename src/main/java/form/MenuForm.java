package form;

import entity.Employee;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuForm extends JFrame {

    private Employee employee;
    private JButton button = new JButton("Підсумкова сума оплат за поточний місяць");
    private JButton RenterListButton = new JButton("Список орендарів");
    private JButton NotRentedRoomButton = new JButton("Приміщення, що не здані в оренду");

    public MenuForm(LoginForm mainForm, Employee employee) {
        super(employee.getFirtName());
        this.employee = employee;

        this.setBounds(100,100,530,220);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        this.add(button);
        this.add(RenterListButton);
        this.add(NotRentedRoomButton);

        button.setBounds(20, 10, 485, 25);
        RenterListButton.setBounds(20, 40, 485, 25);
        NotRentedRoomButton.setBounds(20, 70, 485, 25);

        this.setLayout(null);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        NotRentedRoomButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //new NotRentedApartmentForm(organization.getApartmentList());
            }
        });

        RenterListButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //new ListForm(organization.getId());
            }
        });

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //new MonthlyPaymentForm(organization.getLeaseList());
            }
        });
    }

    @Override
    public void dispose() {
        super.dispose();
        LoginForm.form.setVisible(true);
    }

}
