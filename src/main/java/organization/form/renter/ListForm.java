package organization.form.renter;

import dao.RenterDAO;
import entity.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.LocalDateTime;
import java.util.List;

public class ListForm extends JFrame {
    private Object rowData[][];
    private Object columnNames[] = {"Орендар", "Орендованих приміщень"};
    private RenterDAO renterDAO = new RenterDAO();
    private List renterList;

    public ListForm(int organizationId) {
        super("Список орендарів");
        this.setSize(805, 300);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        renterList = renterDAO.rentersByOrganizationId(organizationId);
        updateRowData(renterList);
        JTable table = new JTable(rowData, columnNames) {
            private static final long serialVersionUID = 1L;

            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };


        JScrollPane scrollPane = new JScrollPane(table);
        this.add(scrollPane, BorderLayout.CENTER);

        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        table.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    JTable table =(JTable) e.getSource();
                    Point p = e.getPoint();
                    int row = table.rowAtPoint(p);
                    if (renterList.get(row) instanceof LegalPerson) {
                        new LegalPersonInfoForm((LegalPerson)renterList.get(row));
                    } else if(renterList.get(row) instanceof NaturalPerson) {
                        new NaturalPersonInfoForm((NaturalPerson)renterList.get(row));
                    }
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }

    private void updateRowData(List renterList) {
        Object rowData[][] = new String[renterList.size()][2];
        for (int i = 0; i < renterList.size(); i++) {
            if (renterList.get(i) instanceof LegalPerson) {
                LegalPerson legalPerson = (LegalPerson) renterList.get(i);
                String personInfo = legalPerson.getName();
                personInfo += ", " + legalPerson.getAddress();
                rowData[i][0] = personInfo;
                rowData[i][1] = "" + rentApartmentCount(legalPerson);
            } else if (renterList.get(i) instanceof NaturalPerson) {
                NaturalPerson naturalPerson = (NaturalPerson) renterList.get(i);
                String personInfo = naturalPerson.getFirstName();
                personInfo += " " + naturalPerson.getLastName();
                personInfo += " " + naturalPerson.getSurname();
                personInfo += ", " + naturalPerson.getAddress();
                rowData[i][0] = personInfo;
                rowData[i][1] = "" + rentApartmentCount(naturalPerson);
            } else {
                System.out.println("Error");
            }
        }
        this.rowData = rowData;
    }

    private int rentApartmentCount(LegalPerson legalPerson) {
        int result = 0;
        for (Lease lease: legalPerson.getLeaseList()) {
            if (isActiveLease(lease)) {
                result++;
            }
        }
        return result;
    }

    private int rentApartmentCount(NaturalPerson naturalPerson) {
        int result = 0;
        for (Lease lease: naturalPerson.getLeaseList()) {
            if (isActiveLease(lease)) {
                result++;
            }
        }
        return result;
    }

    private boolean isActiveLease(Lease lease) {
        return lease.getEndDate().isAfter(LocalDateTime.now());
    }
}
