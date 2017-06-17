import dao.EmployeeDao;
import dao.OrganizationDAO;
import entity.Employee;
import entity.Organization;
import entity.employee.Role;

import java.util.ArrayList;
import java.util.List;

public class TestData {

    private static OrganizationDAO organizationDAO = new OrganizationDAO();
    private static EmployeeDao employeeDao = new EmployeeDao();

    public static void initTestDatabaseData() {
        Organization organization = initOrganizationTable();
        List<Employee> employees = initEmployees(organization);
    }

    private static Organization initOrganizationTable() {
        Organization organization = new Organization();
        organization.setName("Товариство з обмеженою відповідальністю \"Протек\"");
        organization.setShortName("ТОВ \"ПРОТЕК\"");
        organization.setAddress("м. Черкаси, вул. Куренівська 12");
        organization.setContactNumber("+380671114512");
        organization.setEmail("protec@gmail.com");

        return organizationDAO.create(organization);
    }

    private static List<Employee> initEmployees(Organization organization) {
        List<Employee> employees = new ArrayList();

        Employee manager = new Employee();
        manager.setFirtName("Семенов");
        manager.setLastName("Євгеній");
        manager.setCode("1000");
        manager.setPassword("qwerty");
        manager.setRole(Role.MANAGER);
        manager.setOrganization(organization);

        Employee accountant = new Employee();
        accountant.setFirtName("Бондаренко");
        accountant.setLastName("Олександр");
        accountant.setCode("1001");
        accountant.setPassword("asdfgh");
        accountant.setRole(Role.ACCOUNTANT);
        accountant.setOrganization(organization);

        employeeDao.create(manager);
        employeeDao.create(accountant);

        employees.add(manager);
        employees.add(accountant);

        return employees;
    }

}
