package dao;

import entity.LegalPerson;
import entity.NaturalPerson;
import hibernate.HibernateConnector;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

public class RenterDAO {

    public List rentersByOrganizationId(int id) {
        List renterList = legalPersonByOrganizationId(id);
        renterList.addAll(naturalPersonByOrganizationId(id));
        return  renterList;
    }

    public List<LegalPerson> legalPersonByOrganizationId(int id) {
        Session session = null;
        try {
            session = HibernateConnector.getInstance().getSession();
            Query query = session.createQuery("from LegalPerson l where l.organization.id = :id");
            query.setParameter("id", id);

            List queryList = query.list();
            if (queryList != null && queryList.isEmpty()) {
                return null;
            } else {
                return queryList;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }

    public List<NaturalPerson> naturalPersonByOrganizationId(int id) {
        Session session = null;
        try {
            session = HibernateConnector.getInstance().getSession();
            Query query = session.createQuery("from NaturalPerson n where n.organization.id = :id");
            query.setParameter("id", id);

            List queryList = query.list();
            if (queryList != null && queryList.isEmpty()) {
                return null;
            } else {
                return queryList;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }

}
