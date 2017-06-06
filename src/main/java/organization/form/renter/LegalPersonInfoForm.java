package organization.form.renter;

import entity.Apartment;
import entity.Lease;
import entity.LegalPerson;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class LegalPersonInfoForm extends JFrame{
    private JLabel nameLabel = new JLabel();
    private JLabel addressLabel = new JLabel();
    private JLabel individualTaxNumberLabel = new JLabel();
    private JLabel licenseNumberLabel = new JLabel();
    private JLabel licenseDateLabel = new JLabel();
    private Object rowData[][];
    private Object columnNames[] = { "Адреса", "Площа - кв.м.", "Площа підвалу - кв.м.", "Коефіцієнт підвалу", "Коефіцієнт технічного облаштування приміщення" };

    LegalPersonInfoForm(LegalPerson legalPerson){
        super("Інформація про орендаря");
        this.setSize(805, 300);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

        nameLabel.setText("Назва: " + legalPerson.getName());
        addressLabel.setText("Адреса: " + legalPerson.getAddress());
        individualTaxNumberLabel.setText("ІПН: " + legalPerson.getIndividualTaxNumber());
        licenseNumberLabel.setText("Номер ліцензії: " + legalPerson.getLicenseNumber());
        licenseDateLabel.setText("Дата ліцензії: " + legalPerson.getLicenseDate().format(formatter));

        nameLabel.setBounds(10, 5, 788, 20);
        addressLabel.setBounds(10, 25, 788, 20);
        individualTaxNumberLabel.setBounds(10, 45, 788, 20);
        licenseNumberLabel.setBounds(10, 65, 788, 20);
        licenseDateLabel.setBounds(10, 85, 788, 20);

        this.add(nameLabel);
        this.add(addressLabel);
        this.add(individualTaxNumberLabel);
        this.add(licenseNumberLabel);
        this.add(licenseDateLabel);

        updateRowData(legalPerson.getLeaseList());
        JTable table = new JTable(rowData, columnNames);

        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.getColumnModel().getColumn(0).setPreferredWidth(180);
        table.getColumnModel().getColumn(1).setPreferredWidth(80);
        table.getColumnModel().getColumn(2).setPreferredWidth(125);
        table.getColumnModel().getColumn(3).setPreferredWidth(115);
        table.getColumnModel().getColumn(4).setPreferredWidth(285);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(5, 105, 788, 157);
        this.add(scrollPane, BorderLayout.CENTER);

        this.setLayout(null);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private void updateRowData(List<Lease> leaseList){
        List<Lease> activeLease = getActiveLease(leaseList);
        List<Apartment> apartments = getApartmentList(activeLease);
        Object rowData[][] = new String[apartments.size()][5];
        for (int i = 0; i < apartments.size(); i++) {
            rowData[i][0] = apartments.get(i).getAddress();
            rowData[i][1] = Float.toString(apartments.get(i).getArea());
            rowData[i][2] = Float.toString(apartments.get(i).getBasementArea());
            rowData[i][3] = Float.toString(apartments.get(i).getBasementRate());
            rowData[i][4] = Float.toString(apartments.get(i).getTechnicalEquipmentRate());
        }
        this.rowData = rowData;
    }

    private List<Apartment> getApartmentList(List<Lease> leaseList) {
        List<Apartment> apartments = new ArrayList<>();
        for (Lease lease: leaseList) {
            apartments.add(lease.getApartment());
        }
        return apartments;
    }

    private List<Lease> getActiveLease(List<Lease> leaseList) {
        List<Lease> activeLeases = new ArrayList();
        for (Lease lease: leaseList) {
            if (lease.getEndDate().isAfter(LocalDateTime.now())) {
                activeLeases.add(lease);
            }
        }
        return activeLeases;
    }
}
