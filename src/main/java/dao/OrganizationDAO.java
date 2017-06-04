package dao;

import entity.Organization;
import hibernate.HibernateConnector;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

public class OrganizationDAO {
    public Organization findByCode(String code) {
        Session session = null;
        try {
            session = HibernateConnector.getInstance().getSession();
            Query query = session.createQuery("from Organization o where o.code = :code");
            query.setParameter("code", code);

            List queryList = query.list();
            if (queryList != null && queryList.isEmpty()) {
                return null;
            } else {
                return (Organization) queryList.get(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }
}
