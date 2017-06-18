package dao;

import entity.Employee;
import entity.LegalPerson;
import entity.Organization;
import hibernate.HibernateConnector;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Collection;
import java.util.List;

public class LegalPersonDao {

    public LegalPerson create(LegalPerson legalPerson) {
        Session session = null;
        Transaction transaction  = null;
        try {
            session = HibernateConnector.getInstance().getSession();
            transaction = session.beginTransaction();
            session.persist(legalPerson);
            transaction.commit();

            return legalPerson;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }

    public List<LegalPerson> allByOrganizationId(int id) {
        Session session = null;
        try {
            session = HibernateConnector.getInstance().getSession();
            Query query = session.createQuery("from LegalPerson o where o.organization.id = :id");
            query.setParameter("id", id);

            List queryList = query.list();
            if (queryList != null && queryList.isEmpty()) {
                return null;
            } else {
                return (List<LegalPerson>) queryList;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }
}
