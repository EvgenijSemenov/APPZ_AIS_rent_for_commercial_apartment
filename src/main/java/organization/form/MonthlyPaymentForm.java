package organization.form;

import dao.BaseRateDocumentDAO;
import entity.*;
import util.DateTimeFormat;
import util.LeaseUtil;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MonthlyPaymentForm extends JFrame {
    private JLabel dayLabel = new JLabel("День місяця:");
    private JTextField dayTextField = new JTextField();
    private JButton calculateButton = new JButton("Перерахувати");
    private JLabel sumTextLabel = new JLabel("Загальна сума на вказаний день:");
    private JLabel sumLabel = new JLabel();
    private Object rowData[][];
    private Object columnNames[] = { "Номер договору", "Дата договору", "Початок аренди", "Кінець аренди", "Сума на вказану дату" };
    private BaseRateDocumentDAO baseRateDocumentDAO = new BaseRateDocumentDAO();
    private List<Lease> leaseList;
    private JTable table;

    MonthlyPaymentForm(List<Lease> leaseList) {
        super("Список приміщень, що не здані в оренду");
        this.setSize(805,300);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        this.leaseList = leaseList;

        dayTextField.setText("" + LocalDateTime.now().getMonth().length(false));


//        table.getColumnModel().getColumn(0).setPreferredWidth(180);
//        table.getColumnModel().getColumn(1).setPreferredWidth(80);
//        table.getColumnModel().getColumn(2).setPreferredWidth(125);
//        table.getColumnModel().getColumn(3).setPreferredWidth(115);
//        table.getColumnModel().getColumn(4).setPreferredWidth(285);
        createTable();


        dayLabel.setBounds(10, 5, 60, 20);
        dayTextField.setBounds(70, 5, 100, 20);
        calculateButton.setBounds(170, 5, 120, 20);
        sumTextLabel.setBounds(320, 5, 150, 20);
        sumLabel.setBounds(470, 5, 100, 20);


        this.add(dayLabel);
        this.add(dayTextField);
        this.add(calculateButton);
        this.add(sumTextLabel);
        this.add(sumLabel);


        this.setLayout(null);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createTable();
            }
        });
    }

    private void createTable() {
        updateRowData(LeaseUtil.getActive(leaseList));
        table = new JTable(rowData, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 30, 788, 150);
        this.add(scrollPane);
    }

    private void updateRowData(List<Lease> leaseList){
        Object rowData[][] = new String[leaseList.size()][5];
        int sum = 0;
        for (int i = 0; i < leaseList.size(); i++) {
            rowData[i][0] = String.valueOf(leaseList.get(i).getNumber());
            rowData[i][1] = leaseList.get(i).getDocumentDate().format(DateTimeFormat.dateTimeFormatter());
            rowData[i][2] = leaseList.get(i).getStartDate().format(DateTimeFormat.dateTimeFormatter());
            rowData[i][3] = leaseList.get(i).getEndDate().format(DateTimeFormat.dateTimeFormatter());
            sum += calculatePayment(leaseList.get(i).getApartment(), payDayCount(leaseList.get(i), Integer.parseInt(dayTextField.getText())));
            rowData[i][4] = String.valueOf(calculatePayment(leaseList.get(i).getApartment(), payDayCount(leaseList.get(i), Integer.parseInt(dayTextField.getText()))));
        }
        sumLabel.setText(String.valueOf(sum));
        this.rowData = rowData;
    }

    private String renterShortInfo(Lease lease) {
        String shortInfo = null;
        if (lease.getLegalPersonRenter() != null) {
            legalPersonShortInfo(lease.getLegalPersonRenter());
        } else if (lease.getNaturalPersonRenter() != null) {
            naturalPersonShortInfo(lease.getNaturalPersonRenter());
        }

        return shortInfo;
    }

    private String legalPersonShortInfo(LegalPerson legalPerson) {

        return  legalPerson.getName();
    }

    private String naturalPersonShortInfo(NaturalPerson naturalPerson) {

        return naturalPerson.getFirstName();
    }

    private float calculatePayment(Apartment apartment, int dayCount) {
        float baseRate = getBaseRateDocument().getRate();
        float monthSum = (baseRate / 12 * apartment.getArea() + baseRate / 12 * apartment.getBasementArea() * apartment.getBasementRate()) * apartment.getTechnicalEquipmentRate();

        return monthSum / 30 * dayCount;
    }

    private int payDayCount(Lease lease, int lastMonthDay) {
        int lastDay = lease.getStartDate().getMonth().length(false);
        int dayCount = lastMonthDay;
        if (lease.getEndDate().getYear() == LocalDateTime.now().getYear() && lease.getEndDate().getMonth() == LocalDateTime.now().getMonth()) {
            if (lastDay != LocalDateTime.now().getMonth().length(false)) {
                if (lastMonthDay > lease.getEndDate().getDayOfMonth()) {
                    dayCount = 0;

                    return dayCount;
                } else if (lastMonthDay < lease.getEndDate().getDayOfMonth()){
                    lastDay = lease.getEndDate().getDayOfMonth();
                }
            }
        }
        if (lease.getStartDate().getYear() == LocalDateTime.now().getYear() && lease.getStartDate().getMonth() == LocalDateTime.now().getMonth()) {
            if (lease.getStartDate().getDayOfMonth() > 1) {
                dayCount = dayCount - (lease.getStartDate().getDayOfMonth() - 1);
                if (dayCount < 1) {
                    dayCount = 0;
                }
            }
        }

        return dayCount;
    }

    private BaseRateDocument getBaseRateDocument() {
        BaseRateDocument baseRateDocument = null;
        List<BaseRateDocument> baseRateDocumentList = baseRateDocumentDAO.all();
        Collections.sort(baseRateDocumentList, new BaseRateDocumentComparator());
        if (baseRateDocumentList != null && !baseRateDocumentList.isEmpty()) {
            baseRateDocument = baseRateDocumentList.get(0);
            if (baseRateDocumentList.size() == 1) {

                return baseRateDocumentList.get(0);
            }
            for (int i = 1; i < baseRateDocumentList.size(); i++) {
                if (!isCurrentMonth(baseRateDocumentList.get(i).getDate())) {
                    baseRateDocument = baseRateDocumentList.get(i);
                }
            }
        }

        return baseRateDocument;
    }

    private boolean isCurrentMonth(LocalDateTime dateTime) {
        return (dateTime.getYear() == LocalDateTime.now().getYear() && dateTime.getMonth() == LocalDateTime.now().getMonth());
    }

    public class BaseRateDocumentComparator implements Comparator<BaseRateDocument> {
        @Override
        public int compare(BaseRateDocument o1, BaseRateDocument o2) {
            return o1.getDate().compareTo(o2.getDate());
        }
    }
}
