import dao.EmployeeDao;
import dao.LegalPersonDao;
import dao.NaturalPersonDao;
import dao.OrganizationDAO;
import entity.Employee;
import entity.LegalPerson;
import entity.NaturalPerson;
import entity.Organization;
import entity.employee.Role;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class TestData {

    private static OrganizationDAO organizationDAO = new OrganizationDAO();
    private static EmployeeDao employeeDao = new EmployeeDao();
    private static LegalPersonDao legalPersonDao = new LegalPersonDao();
    private static NaturalPersonDao naturalPersonDao = new NaturalPersonDao();


    public static void initTestDatabaseData() {
        Organization organization = initOrganization();
        List<Employee> employees = initEmployees(organization);
        List<LegalPerson> legalPeople = initLegalPersons(organization);
        List<NaturalPerson> naturalPeople = initNaturalPerson(organization);
    }

    private static Organization initOrganization() {
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

    private static List<LegalPerson> initLegalPersons(Organization organization) {
        LegalPerson ekoTreid = new LegalPerson();
        ekoTreid.setName("ТОВ \"ЕКО ТРЕЙД\"");
        ekoTreid.setIndividualTaxNumber(157654943);
        ekoTreid.setAddress("м.Черкаси, вул. Героїв УПА 140");
        ekoTreid.setLicenseNumber(327549);
        ekoTreid.setLicenseDate(LocalDateTime.of(2016, Month.APRIL, 5, 0, 0));
        ekoTreid.setOrganization(organization);

        LegalPerson gammaFlex = new LegalPerson();
        gammaFlex.setName("ТОВ \"ГАММА ФЛЕКС\"");
        gammaFlex.setIndividualTaxNumber(649275047);
        gammaFlex.setAddress("м.Черкаси, бул. Шевченка 312-А");
        gammaFlex.setLicenseNumber(735048);
        gammaFlex.setLicenseDate(LocalDateTime.of(2015, Month.JULY, 22, 0, 0));
        gammaFlex.setOrganization(organization);

        LegalPerson deCristal = new LegalPerson();
        deCristal.setName("Школа краси \"DE CRISTAL\"");
        deCristal.setIndividualTaxNumber(293732846);
        deCristal.setAddress("м.Черкаси, вул. Гагаріна 16");
        deCristal.setLicenseNumber(211423);
        deCristal.setLicenseDate(LocalDateTime.of(2017, Month.FEBRUARY, 12, 0, 0));
        deCristal.setOrganization(organization);

        legalPersonDao.create(ekoTreid);
        legalPersonDao.create(gammaFlex);
        legalPersonDao.create(deCristal);

        List<LegalPerson> legalPeople = new ArrayList<>();
        legalPeople.add(ekoTreid);
        legalPeople.add(gammaFlex);
        legalPeople.add(deCristal);

        return legalPeople;
    }

    private static List<NaturalPerson> initNaturalPerson(Organization organization) {
        NaturalPerson mihnovskii = new NaturalPerson();
        mihnovskii.setFirstName("Петро");
        mihnovskii.setLastName("Міхновський");
        mihnovskii.setSurname("Іванович");
        mihnovskii.setIndividualTaxNumber(15468547);
        mihnovskii.setAddress("м.Золотоноша, вул. Обухова 16");
        mihnovskii.setPassportIssued("Золотоніським УМВС в Черкаській області");
        mihnovskii.setPassportSeries("НЕ");
        mihnovskii.setPassportNumber(426387);
        mihnovskii.setPassportIssueDate(LocalDateTime.of(2014, Month.MARCH, 12, 0, 0));
        mihnovskii.setOrganization(organization);

        NaturalPerson salnitskii = new NaturalPerson();
        salnitskii.setFirstName("Дмитро");
        salnitskii.setLastName("Сальницький");
        salnitskii.setSurname("Володимирович");
        salnitskii.setIndividualTaxNumber(25373345);
        salnitskii.setAddress("м.Черкаси, вул. Звенигородська 221");
        salnitskii.setPassportIssued("Черкаським УМВС");
        salnitskii.setPassportSeries("СА");
        salnitskii.setPassportNumber(248547);
        salnitskii.setPassportIssueDate(LocalDateTime.of(2013, Month.DECEMBER, 2, 0, 0));
        salnitskii.setOrganization(organization);

        naturalPersonDao.create(mihnovskii);
        naturalPersonDao.create(salnitskii);

        List<NaturalPerson> naturalPeople = new ArrayList<>();
        naturalPeople.add(mihnovskii);
        naturalPeople.add(salnitskii);

        return naturalPeople;
    }

}
