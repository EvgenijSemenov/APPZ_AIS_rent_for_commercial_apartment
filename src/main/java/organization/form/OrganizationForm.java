package organization.form;

import entity.Organization;
import organization.form.renter.ListForm;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OrganizationForm extends JFrame {
    private Organization organization;
    private JButton button = new JButton("Підсумкова сума оплат за поточний місяць");
    private JButton RenterListButton = new JButton("Список орендарів");
    private JButton NotRentedRoomButton = new JButton("Приміщення, що не здані в оренду");

    public OrganizationForm(LoginForm mainForm, Organization organization) {
        super(organization.getName());
        this.organization = organization;

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
                new NotRentedApartmentForm(organization.getApartmentList());
            }
        });

        RenterListButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ListForm(organization.getId());
            }
        });
    }

    @Override
    public void dispose() {
        super.dispose();
        LoginForm.form.setVisible(true);
    }
}
