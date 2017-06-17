package util;

import dao.EmployeeDao;
import dao.OrganizationDAO;
import entity.Employee;
import entity.Organization;
import entity.employee.Role;

public class Login {

    private String code;
    private String password;
    private EmployeeDao employeeDao = new EmployeeDao();

    public Login(String code, String password) {
        this.code = code;
        this.password = password;
    }

    public boolean isValid() {
        Employee employee = employeeDao.findByCode(code);

        return (employee != null && employee.getPassword().equals(password));
    }

    public Employee employee(){
        return employeeDao.findByCode(code);
    }

}
