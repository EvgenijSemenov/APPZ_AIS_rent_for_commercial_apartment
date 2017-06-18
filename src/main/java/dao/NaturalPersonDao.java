package dao;

import entity.LegalPerson;
import entity.NaturalPerson;
import hibernate.HibernateConnector;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Collection;
import java.util.List;

public class NaturalPersonDao {

    public NaturalPerson create(NaturalPerson naturalPerson) {
        Session session = null;
        Transaction transaction  = null;
        try {
            session = HibernateConnector.getInstance().getSession();
            transaction = session.beginTransaction();
            session.persist(naturalPerson);
            transaction.commit();

            return naturalPerson;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }

    public List<NaturalPerson> allByOrganizationId(int id) {
        Session session = null;
        try {
            session = HibernateConnector.getInstance().getSession();
            Query query = session.createQuery("from NaturalPerson o where o.organization.id = :id");
            query.setParameter("id", id);

            List queryList = query.list();
            if (queryList != null && queryList.isEmpty()) {
                return null;
            } else {
                return (List<NaturalPerson>) queryList;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }
}
