package form.organization;

import entity.Organization;
import form.login.MainForm;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OrganizationForm extends JFrame {
    private Organization organization;
    private JButton button = new JButton("Підсумкова сума оплат за поточний місяць");
    private JButton button1 = new JButton("Список орендарів");
    private JButton NotRentedRoomButton = new JButton("Приміщення, що не здані в оренду");

    public OrganizationForm(MainForm mainForm, Organization organization) {
        super(organization.getName());
        this.organization = organization;

        this.setBounds(100,100,530,220);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        this.add(button);
        this.add(button1);
        this.add(NotRentedRoomButton);

        button.setBounds(20, 10, 485, 25);
        button1.setBounds(20, 40, 485, 25);
        NotRentedRoomButton.setBounds(20, 70, 485, 25);

        this.setLayout(null);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        NotRentedRoomButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new NotRentedRoomForm(organization.getApartmentList());
            }
        });
    }

    @Override
    public void dispose() {
        super.dispose();
        MainForm.form.setVisible(true);
    }
}
