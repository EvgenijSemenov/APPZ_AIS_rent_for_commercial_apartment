package form.organization;

import entity.Apartment;
import entity.Lease;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class NotRentedRoomForm extends JFrame {
    private Object rowData[][];
    private Object columnNames[] = { "Адреса", "Площа - кв.м.", "Площа підвалу - кв.м.", "Коефіцієнт підвалу", "Коефіцієнт технічного облаштування приміщення" };

    NotRentedRoomForm(List<Apartment> apartments) {
        super("Список приміщень, що не здані в оренду");
        this.setSize(805,300);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        updateRowData(notRentedRoom(apartments));
        JTable table = new JTable(rowData, columnNames);

        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.getColumnModel().getColumn(0).setPreferredWidth(180);
        table.getColumnModel().getColumn(1).setPreferredWidth(80);
        table.getColumnModel().getColumn(2).setPreferredWidth(125);
        table.getColumnModel().getColumn(3).setPreferredWidth(115);
        table.getColumnModel().getColumn(4).setPreferredWidth(285);

        JScrollPane scrollPane = new JScrollPane(table);
        this.add(scrollPane, BorderLayout.CENTER);

        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private void updateRowData(List<Apartment> apartments){
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

    private List<Apartment> notRentedRoom(List<Apartment> apartments) {
        List<Apartment> notRentedApartmentList = new ArrayList<>();
        for (Apartment apartment: apartments) {
            if (isAllLeaseEnd(apartment.getLeaseList())) {
                notRentedApartmentList.add(apartment);
            }
        }
        return notRentedApartmentList;
    }

    private boolean isAllLeaseEnd(List<Lease> leases) {
        boolean result = true;
        for (Lease leaase: leases) {
            if (leaase.getEndDate().isAfter(LocalDateTime.now())) {
                result = false;
                break;
            }
        }
        return result;
    }
}
