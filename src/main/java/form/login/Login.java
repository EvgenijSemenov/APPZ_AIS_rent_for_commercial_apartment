package form.login;

import dao.OrganizationDAO;
import entity.Organization;

public class Login {
    private String organizationCode;
    private String password;
    private OrganizationDAO organizationDAO = new OrganizationDAO();

    Login(String organizationCode, String password) {
        this.organizationCode = organizationCode;
        this.password = password;
    }

    public boolean isValid() {
        Organization organization = organizationDAO.findByCode(organizationCode);

        return (organization != null && organization.getPassword().equals(password));
    }

    public Organization organization(){
        return organizationDAO.findByCode(organizationCode);
    }
}
