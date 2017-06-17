package dao;

import entity.Employee;
import entity.Organization;
import hibernate.HibernateConnector;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class EmployeeDao {

    public Employee create(Employee employee) {
        Session session = null;
        Transaction transaction  = null;
        try {
            session = HibernateConnector.getInstance().getSession();
            transaction = session.beginTransaction();
            session.persist(employee);
            transaction.commit();

            return employee;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }

    public Employee findByCode(String code) {
        Session session = null;
        try {
            session = HibernateConnector.getInstance().getSession();
            Query query = session.createQuery("from Employee o where o.code = :code");
            query.setParameter("code", code);

            List queryList = query.list();
            if (queryList != null && queryList.isEmpty()) {
                return null;
            } else {
                return (Employee) queryList.get(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }

}
