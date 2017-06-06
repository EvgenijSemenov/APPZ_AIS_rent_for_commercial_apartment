package dao;

import entity.Lease;
import entity.Organization;
import hibernate.HibernateConnector;
import org.hibernate.Query;
import org.hibernate.Session;

import java.time.LocalDateTime;
import java.util.List;

public class LeaseDAO {
    public List<Lease> findActiveByOrganizationId(int id) {
        Session session = null;
        try {
            session = HibernateConnector.getInstance().getSession();
            Query query = session.createQuery("from Lease l where l.organization_id = :id AND l.endDate < :dateNow");
            query.setParameter("organization_id", id);
            query.setParameter("dateNow", LocalDateTime.now());

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

    public List<Lease> findActiveRenterByOrganizationId(int id) {
        Session session = null;
        try {
            session = HibernateConnector.getInstance().getSession();
            Query query = session.createQuery("from Lease l where l.organization_id = :id AND l.endDate < :dateNow");
            query.setParameter("organization_id", id);
            query.setParameter("dateNow", LocalDateTime.now());

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
