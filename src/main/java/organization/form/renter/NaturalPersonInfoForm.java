package organization.form.renter;

import entity.Apartment;
import entity.Lease;
import entity.NaturalPerson;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import util.*;

public class NaturalPersonInfoForm extends JFrame{
    private JLabel flsLabel = new JLabel();
    private JLabel passportSeriesAndNumberLabel = new JLabel();
    private JLabel passportIssueDateLabel = new JLabel();
    private JLabel passportIssuedLabel = new JLabel();
    private JLabel individualTaxNumberLabel = new JLabel();
    private JLabel addressLabel = new JLabel();
    private Object rowData[][];
    private Object columnNames[] = { "Адреса", "Площа - кв.м.", "Площа підвалу - кв.м.", "Коефіцієнт підвалу", "Коефіцієнт технічного облаштування приміщення" };

    public NaturalPersonInfoForm(NaturalPerson naturalPerson) {
        super("Інформація про орендаря");
        this.setSize(805, 300);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        flsLabel.setText("ПІБ: " + naturalPerson.getLastName() + " " + naturalPerson.getFirstName() + " " + naturalPerson.getSurname());
        passportSeriesAndNumberLabel.setText("Серія та номер паспорта: " + naturalPerson.getPassportSeries() + naturalPerson.getPassportNumber());
        passportIssueDateLabel.setText("Дата видачі паспорта: " + naturalPerson.getPassportIssueDate().format(DateTimeFormat.dateTimeFormatter()));
        passportIssuedLabel.setText("Ким видано паспорт: " + naturalPerson.getPassportIssued());
        individualTaxNumberLabel.setText("ІПН: " + naturalPerson.getIndividualTaxNumber());
        addressLabel.setText("Адреса: " + naturalPerson.getAddress());

        flsLabel.setBounds(10, 5, 788, 20);
        passportSeriesAndNumberLabel.setBounds(10, 25, 788, 20);
        passportIssueDateLabel.setBounds(10, 45, 788, 20);
        passportIssuedLabel.setBounds(10, 65, 788, 20);
        individualTaxNumberLabel.setBounds(10, 85, 788, 20);
        addressLabel.setBounds(10, 105, 788, 20);

        this.add(flsLabel);
        this.add(passportSeriesAndNumberLabel);
        this.add(passportIssueDateLabel);
        this.add(passportIssuedLabel);
        this.add(individualTaxNumberLabel);
        this.add(addressLabel);

        updateRowData(naturalPerson.getLeaseList());
        JTable table = new JTable(rowData, columnNames);

        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.getColumnModel().getColumn(0).setPreferredWidth(180);
        table.getColumnModel().getColumn(1).setPreferredWidth(80);
        table.getColumnModel().getColumn(2).setPreferredWidth(125);
        table.getColumnModel().getColumn(3).setPreferredWidth(115);
        table.getColumnModel().getColumn(4).setPreferredWidth(285);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(5, 125, 788, 137);
        this.add(scrollPane, BorderLayout.CENTER);

        this.setLayout(null);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private void updateRowData(java.util.List<Lease> leaseList){
        java.util.List<Lease> activeLease = LeaseUtil.getActive(leaseList);
        java.util.List<Apartment> apartments = getApartmentList(activeLease);
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

    private java.util.List<Apartment> getApartmentList(java.util.List<Lease> leaseList) {
        java.util.List<Apartment> apartments = new ArrayList<>();
        for (Lease lease: leaseList) {
            apartments.add(lease.getApartment());
        }
        return apartments;
    }
}
